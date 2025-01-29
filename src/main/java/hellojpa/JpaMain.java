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
      member.setUsername("user1");

      em.persist(member);

      em.flush();
      em.clear();

      Member findMember = em.getReference(Member.class, member.getId()); // 이 시점엔 select 쿼리 안나감
      System.out.println("findMember = " + findMember.getClass()); // findMember는 프록시 엔티티(가짜 엔티티)

      System.out.println("member.id = " + findMember.getId());
      System.out.println(
          "member.username = "
              + findMember.getUsername()); // 이 시점에 select 쿼리 나가서 실제 엔티티 가져오고, 프록시를 통해 접근 가능

      System.out.println(
          "findMember 초기화 여부: " + emf.getPersistenceUnitUtil()
              .isLoaded(findMember)); // 프록시 인스턴스의  초기화 여부 확인

      org.hibernate.Hibernate.initialize(findMember); // 프록시 강제 초기화

      tx.commit(); // 성공시 커밋

    } catch (Exception e) {
      tx.rollback(); // 실패시 롤백

    } finally {
      em.close(); // DB 변경 완료되면 em 닫기
    }

    emf.close(); // 어플리케이션 종료시 emf 닫기

  }
}
