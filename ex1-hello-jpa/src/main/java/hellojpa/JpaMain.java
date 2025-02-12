package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
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
            team.setName("teamA");
            em.persist(team);
            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member1.getId());
            System.out.println("m = " + m.getTeam().getClass());
            System.out.println("=================");
            m.getTeam().getName();
            System.out.println("=================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
