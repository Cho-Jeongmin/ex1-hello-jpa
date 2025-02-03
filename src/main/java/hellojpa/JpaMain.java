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

      Member member = new Member();

      member.setUsername("조정민");
      member.setLastModifiedBy("관리자1");
      member.setLastModifiedDate(LocalDateTime.now());
      member.setCreatedBy("관리자1");
      member.setCreatedDate(LocalDateTime.now());
      member.setHomeAddress(new Address("서울시", "고척로52길 48", "08230"));
      member.setWorkPeriod(new Period(LocalDateTime.of(2023, 3, 1, 9, 30, 0),
          LocalDateTime.of(2023, 6, 30, 6, 30, 0)));

      em.persist(member);

      tx.commit(); // 성공시 커밋

    } catch (Exception e) {
      tx.rollback(); // 실패시 롤백

    } finally {
      em.close(); // DB 변경 완료되면 em 닫기
    }

    emf.close(); // 어플리케이션 종료시 emf 닫기

  }
}
