package hellojpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {

  public static void main(String[] args) {

    // emf는 어플리케이션 실행시  1번만 생성
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    // DB를 변경할 때마다 em 생성
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin(); // 항상 트랜젝션 안에서 변경

    try {
      Parent parent = new Parent();

      Child child1 = new Child();
      Child child2 = new Child();

      parent.addChild(child1);
      parent.addChild(child2);

      em.persist(parent);
//      em.persist(child1); // 원래는 이렇게 child도 persist 해줘야 하지만
//      em.persist(child2);

      // cascade를 all로 설정하면 parent가 persist 될 때 child도 자동으로 persist 됨
      // 또한, parent가 삭제될 때 child도 삭제됨

      tx.commit(); // 성공시 커밋

    } catch (Exception e) {
      tx.rollback(); // 실패시 롤백

    } finally {
      em.close(); // DB 변경 완료되면 em 닫기
    }

    emf.close(); // 어플리케이션 종료시 emf 닫기

  }
}
