package com.oracle.oBootJpaApi01.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//Entity와 DTO의 차이는?(myBetis는 DTO고, Entity는 JPA)임

//myBetis는 테이블과 객체간에 1:1로 쿼리를 수행
//entity는 JPA를 이용해서 객체


//Entity를 안준 클래스는 JPA와 아무 상관이 없음
@Entity
@Data
@SequenceGenerator( name =         "member_seq_gen5",
               sequenceName = "member_seq_generate5",
               initialValue = 1,
               allocationSize = 1)
@Table(name="member5")
public class Member {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE,
               generator = "member_seq_gen5")
   @Column(name="member_id")
   private Long id;
   
   @NotEmpty //비어있으면 안됨 => 필수임 => name이 없으면 에러를 낼거다.
   @Column(name="userName")
   private String name;
   private Long sal;
   private String status;
   
   //관계설정
   // @ManyToOne(fetch = FetchType.LAZY) 기본은 eager(즉시로딩)
   @ManyToOne
   @JoinColumn(name = "team_id")
   private Team team;
   //team테이블의 team_id컬럼을 외래키로 사용하겠다.
   
   
   //JPA레파지토리에서 사용하기 위한 teamname;
   //@Transient 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
   //실제 column 아니고 -> Buffer용도로 사용한다.
   @Transient
   private String teamname;
   //여기서 teamname은 Team.java에서 받아온 entity 이름을 이용한거
   
   @Transient
   private Long teamid;

}