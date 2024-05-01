package com.oracle.oBootJpaApi01.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity //이걸 써야지 jpa로 테이블을 만들어줌
//@Table 이거 안써주면 테이블 만들어줄때 Team으로 이름 똑같이 만들어줌
@Data
//name은 객체시퀀스, sequenceName은 DB시퀀스
@SequenceGenerator(name = "team_seq_gen5", sequenceName = "team_seq_genterator5",
               initialValue = 1, allocationSize = 1)
//name은 객체시퀀스, sequenceName = DB시퀀스 이름
@Table(name="team5")
public class Team {
   
   @Id

   //strategy(기본키값을 생성) : sequence - 시퀀스 전략은 데이터베이스에서 시퀀스(또는 일련번호)를 사용하여 고유한 기본 키 값을 생성하는 방법입니다. 
   //generator :  
   @GeneratedValue(strategy = GenerationType.SEQUENCE,
               generator="team_seq_gen5")//generator는 객체시퀀스를 끌고 오자
   private Long team_id;
   //pk인 team_id를 시퀀스방법으로 사용할거고 generator(이름은 = tem_seq_gen)으로 사용하겠따.
   
   @Column(name = "teamname")
   private String name;

}