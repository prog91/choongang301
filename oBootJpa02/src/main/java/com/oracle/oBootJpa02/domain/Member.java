package com.oracle.oBootJpa02.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@SequenceGenerator(
				name 		   = "member_seq_gen",		  // 객체Seq
				sequenceName   = "member_seq_generate",  // DB Seq
				initialValue   = 1,
				allocationSize = 1
				)


@Table(name = "member2") // db에서 테이블 이름을 member2로 생성
public class Member {
	// 프라이머리 키가 빠져있는 상태여서 어노테이션으로 아이디를 걸어줌
	@Id
	@GeneratedValue(
				strategy  = GenerationType.SEQUENCE,
				generator = "member_seq_gen"
			)
	@Column(name = "member_id", precision = 10)
	private Long   id;
	@Column(name = "user_name", length = 50)
	private String name;
	private Long   sal;
	
	// member2 테이블과 team 테이블간의 일대다 관계 설정
	@ManyToOne // foreign key를 걸어야 하는 곳에 설정
	@JoinColumn(name = "team_id")
	private Team team;
	
	// 실제 Column은 아니지만 Buffer용도로만 사용하고 싶을 때 걸어주는 어노테이션 @Transient
	@Transient
	private String teamname;
	
	@Transient
	// memberModify.html에서 받아올 파라미터 설정
	private Long teamid;
}
