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
            //등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("helloB");
//            em.persist(member);


            // 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
            // 수정
//            findMember.setName("helloJPA");

            //삭제
            em.remove(findMember);

            // jpql
            // 테이블 대상으로 쿼리를 짜는게 아니고 객체를 대상으로
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
