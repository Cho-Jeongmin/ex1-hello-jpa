package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "MEMBER")
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id;

  @Column(name = "USERNAME")
  private String username;

//  @Column(name = "TEAM_ID")
//  private Long teamId;

  @ManyToOne(fetch = FetchType.LAZY)// 멤버 조회할 때 팀은 조회 안하고, 진짜 팀이 필요할 때 조회함. 즉 select 쿼리를 분리.(지연로딩)
  @JoinColumn(name = "TEAM_ID") // 외래키가 있는 곳이 연관관계의 주인
  private Team team;

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

  public Team getTeam() {
    return team;
  }

  public void changeTeam(Team team) {
    this.team = team;
    team.getMembers().add(this);
    // 양방향으로 값을 넣어주자.
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", team=" + team + // 여기서 Team의 toString이 호출됨(무한 루프)
        '}';
  }
}
