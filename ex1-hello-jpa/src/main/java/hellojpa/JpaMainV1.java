package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMainV1 {

    public static void main(String[] args) {
        // 애플리케이션 로딩시점에 emf딱 하나만 만들어 놓는다
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 트랜잭션 단위, 어떤 한 일관적인 단위를 할때마다 em을 만들어야 한다.
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 획득
        tx.begin(); // 트랜잭션 시작

        // code
        try {
            // 비영속
            Member member = new Member();
            member.setId(100L);
//            member.setName("HelloJPA");

            // 영속
            System.out.println("==== BEFORE ====");
            em.persist(member);
            System.out.println("==== AFTER ====");

            Member findMember = em.find(Member.class, 101L);

            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
