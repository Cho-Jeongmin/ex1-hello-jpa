package hellojpa;

public class ValueMain {

  public static void main(String[] args) {

    int a = 10;
    int b = 10;

    System.out.println("a == b = " + (a == b)); // true

    Address address1 = new Address("서울시", "고척로52길 48", "08230");
    Address address2 = new Address("서울시", "고척로52길 48", "08230");

    System.out.println(
        "address1 == address2 = " + (address1 == address2)); // false (다른 참조값을 가지기 때문)

    System.out.println("address1.equals(address2) = " + address1.equals(
        address2)); // true (Address 안에 equals 메소드를 오버라이드 해놨기 때문)

    // 객체는 항상 equals로 비교해야 한다.

  }

}
