package hellojpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {

  public static void main(String[] args) {

    // emf는 어플리케이션 실행시  1번만 생성
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    // DB를 변경할 때마다 em 생성
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin(); // 항상 트랜젝션 안에서 변경

    try {

      // 저장
      Member member = new Member();
      member.setUsername("조정민");
      member.setHomeAddress(new Address("North Parramatta", "74 Grose St", "2151"));

      member.getFavoriteFoods().add("피자");
      member.getFavoriteFoods().add("족발");
      member.getFavoriteFoods().add("치킨");

      member.getAddressHistory().add(new AddressEntity("Greeley", "12th Ave Ct", "2300"));
      member.getAddressHistory().add(new AddressEntity("서울시", "고척로52길 48", "08230"));
      // 실무에서는 이런식으로 값 타입을 엔티티로 승격시켜서 사용
      // 값 타입 컬렉션은 진짜 단순한 경우에만 사용 (ex: 피자, 치킨 중에서 체크박스로 선택)

      em.persist(member);

      em.flush();
      em.clear();

      // 조회
      System.out.println("=============");
      Member findMember = em.find(Member.class, member.getId());

      System.out.println("=============");
      List<AddressEntity> addressHistory = findMember.getAddressHistory();
      for (AddressEntity address : addressHistory) {
        System.out.println("address = " + address.getAddress().getCity());
      }

      System.out.println("=============");
      Set<String> favoriteFoods = findMember.getFavoriteFoods();
      for (String favoriteFood : favoriteFoods) {
        System.out.println("favoriteFood = " + favoriteFood);
      }

      // 수정
      // 치킨 -> 파스타
      findMember.getFavoriteFoods().remove("치킨");
      findMember.getFavoriteFoods().add("파스타");

      // 서울시 -> 서산시
//      findMember.getAddressHistory()
//          .remove(new Address("서울시", "고척로52길 48", "08230")); // equals로 비교하여 같으면 삭제됨
//      findMember.getAddressHistory().add(new Address("서산시", "고척로52길 48", "08230"));

      tx.commit(); // 성공시 커밋

    } catch (Exception e) {
      tx.rollback(); // 실패시 롤백

    } finally {
      em.close(); // DB 변경 완료되면 em 닫기
    }

    emf.close(); // 어플리케이션 종료시 emf 닫기

  }
}
