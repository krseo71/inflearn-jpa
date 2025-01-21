package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // 애플리케이션 로딩시점에 emf딱 하나만 만들어 놓는다
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 트랜잭션 단위, 어떤 한 일관적인 단위를 할때마다 em을 만들어야 한다.
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 획득
        tx.begin(); // 트랜잭션 시작

        // code
        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
