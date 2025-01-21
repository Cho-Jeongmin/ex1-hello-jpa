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
      Team team = new Team();
      team.setName("teamA");
      em.persist(team);

      Member member = new Member();
      member.setUsername("member1");
      member.changeTeam(team); // 연관관계 편의 메소드(내부적으로 양쪽에 다 값을 넣어줌)
      em.persist(member);

      // em.flush();
      // em.clear();

      // 만약 flush를 안 하더라도

      Team findTeam = em.find(Team.class, team.getId());
      List<Member> members = findTeam.getMembers(); // team의 members를 조회해보면 잘 조회된다.
      for (Member m : members) {
        System.out.println("m = " + m.getUsername()); // 잘 출력됨
      }

      tx.commit(); // 성공시 커밋

    } catch (Exception e) {
      tx.rollback(); // 실패시 롤백

    } finally {
      em.close(); // DB 변경 완료되면 em 닫기
    }

    emf.close(); // 어플리케이션 종료시 emf 닫기

  }
}
