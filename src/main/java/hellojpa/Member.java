package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "MEMBER")
public class Member {

  @Id
  private Long id;

  @Column(name = "name")
  private String username;

  private Integer age;

  @Enumerated(EnumType.STRING) // ORDINAL 사용 금지
  private RoleType roleType;

  @Temporal(TemporalType.TIMESTAMP) // 날짜+시간
  private Date createdDate; // Java8 이전

  private LocalDateTime createdDate2; // Java8 이후

  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate;

  @Lob
  private String description;

  //Getter, Setter...
}
