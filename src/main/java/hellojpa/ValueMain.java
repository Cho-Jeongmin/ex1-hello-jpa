package hellojpa;

public class ValueMain {

  public static void main(String[] args) {

    int a = 10;
    int b = a;

    a = 20;

    System.out.println("a = " + a); // 20
    System.out.println("b = " + b); // 10

    // primitive 값 타입은 공유되지 않는다. 따라서 사이드 이펙트도 없다.

    Integer c = new Integer(10);
    Integer d = c; // c가 공유됨. 즉 참조값이 들어감.

    // c.setValue(10); // 하지만 Integer나 String 클래스는 이런식으로 속성값을 바꿀 수 있는 방법이 없기 때문에 사이드 이펙트는 없음.
  }

}
