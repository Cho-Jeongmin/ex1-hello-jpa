package hellojpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaMain {

  public static void main(String[] args) {

    // emf는 어플리케이션 실행시  1번만 생성
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    // DB를 변경할 때마다 em 생성
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin(); // 항상 트랜젝션 안에서 변경

    try {
      // 멤버 저장
      Member member1 = new Member();
      member1.setId(1L);
      member1.setName("Jamie");

      Member member2 = new Member();
      member2.setId(2L);
      member2.setName("Flora");

      em.persist(member1);
      em.persist(member2);

      // 멤버 리스트 조회
      List<Member> memberList = em.createQuery("select m from Member as m",
              Member.class)
          .setFirstResult(0)
          .setMaxResults(2) // 페이징(0번째부터 2개 가져오라는 뜻)
          .getResultList();

      for (Member member : memberList) {
        System.out.println("member = " + member.getName());
      }

      // 멤버 조회
      Member findMember = em.find(Member.class, 1L);

      // 멤버 수정
      findMember.setName("Jeongmin");

      // 멤버 삭제
      em.remove(findMember);

      tx.commit(); // 성공시 커밋

    } catch (Exception e) {
      tx.rollback(); // 실패시 롤백

    } finally {
      em.close(); // DB 변경 완료되면 em 닫기
    }

    emf.close(); // 어플리케이션 종료시 emf 닫기

  }
}
