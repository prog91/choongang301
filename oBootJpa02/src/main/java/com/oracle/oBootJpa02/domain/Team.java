package com.oracle.oBootJpa02.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@SequenceGenerator(
		name 			= "team_seq_gen",        // 객체 Seq
		sequenceName 	= "team_seq_generator",  // DB Seq
		initialValue    = 1,					 // 시퀀스 시작 값
		allocationSize  = 1						 // 시퀀스 증가 값
		)
public class Team {
	@Id
	@GeneratedValue(
			strategy  = GenerationType.SEQUENCE,
			generator = "team_seq_gen" 
			)
	private Long team_id;
	@Column(name = "teamname")
	private String name;
}
