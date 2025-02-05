package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

  private String city;

  private String street;

  @Column(name = "ZIPCODE")
  private String zipcode;

  // private House house; // 이런식으로 기본값 타입이 엔티티를 가질 수도 있음

  public Address() {
  }

  public Address(String city, String street, String zipcode) {
    this.city = city;
    this.street = street;
    this.zipcode = zipcode;
  }

  public String getCity() {
    return city;
  }


  public String getStreet() {
    return street;
  }


  public String getZipcode() {
    return zipcode;
  }

  // setter를 모두 지워서 address를 불변객체로 만들면 객체 공유로 인한 사이드이펙트를 방지할 수 있음.
  // setter를 지우지 않고 private으로 만들어도 됨.

}
