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
      member.setTeam(team); // member에만 team을 넣어주고,
      em.persist(member);

      // team.getMembers().add(member); // team에는 member를 넣지 말아보자.

      // em.flush();
      // em.clear();

      // 만약 flush를 안 할 경우,

      Team findTeam = em.find(Team.class, team.getId()); // 1차캐시에서 team을 조회하게 되고,
      List<Member> members = findTeam.getMembers(); // team의 members를 조회해보면 아무것도 안나오게 된다.
      for (Member m : members) {
        System.out.println("m = " + m.getUsername()); // (아무것도 출력 안됨)
      }

      // 결론적으로, 양방향으로 값을 다 넣어주는게 좋다. (team에도 member를 넣어주자)

      tx.commit(); // 성공시 커밋

    } catch (Exception e) {
      tx.rollback(); // 실패시 롤백

    } finally {
      em.close(); // DB 변경 완료되면 em 닫기
    }

    emf.close(); // 어플리케이션 종료시 emf 닫기

  }
}
