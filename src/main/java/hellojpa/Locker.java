package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Locker {


  @Id
  @GeneratedValue
  @Column(name = "LOCKER_ID")
  private Long id;

  private String name;
}
