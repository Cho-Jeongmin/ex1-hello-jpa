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
      Address address = new Address("서울시", "고척로52길 48",
          "08230");

      Member member1 = new Member();
      member1.setUsername("조정민");
      member1.setHomeAddress(address);
      em.persist(member1);

      Member member2 = new Member();
      member2.setUsername("한나현");
      member2.setHomeAddress(address);
      em.persist(member2);

      // member2.getHomeAddress().setCity("서산시"); // Address는 변경 불가능한 불변 객체. 객체 공유로 인한 사이드 이펙트 방지.

      member2.setHomeAddress(new Address("서산시", address.getStreet(),
          address.getZipcode())); // 변경하고 싶다면 아예 새로운 Address 객체를 만들어서 넣어주면 됨.

      tx.commit(); // 성공시 커밋

    } catch (Exception e) {
      tx.rollback(); // 실패시 롤백

    } finally {
      em.close(); // DB 변경 완료되면 em 닫기
    }

    emf.close(); // 어플리케이션 종료시 emf 닫기

  }
}
