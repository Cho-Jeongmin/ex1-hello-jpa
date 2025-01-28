package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;


@Entity
@Table(name = "MEMBER")
public class Member extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id;

  @Column(name = "USERNAME")
  private String username;

  @ManyToOne
  @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
  private Team team; // 연관관계의 주인 아님(읽기 전용)

  @OneToOne
  @JoinColumn(name = "LOCKER_ID")
  private Locker locker;

  @OneToMany(mappedBy = "member")
  private List<MemberProduct> memberProducts = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
