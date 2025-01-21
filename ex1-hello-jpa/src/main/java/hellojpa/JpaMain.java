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
            Movie movie = new Movie();
            movie.setDirector("aaa");
            movie.setActor("bbb");
            movie.setName("movieName");
            movie.setPrice(10000);
            em.persist(movie);

            em.flush();
            em.clear();
            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
