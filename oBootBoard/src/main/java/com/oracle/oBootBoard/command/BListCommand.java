package com.oracle.oBootBoard.command;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.ui.Model;

import com.oracle.oBootBoard.dao.BDao;
import com.oracle.oBootBoard.dao.JdbcDao;
import com.oracle.oBootBoard.dto.BDto;

//Service
public class BListCommand implements BCommand {
	DataSource dataSource;
	
	@Override
	public void execute(Model model) {
		// Dao 연결
		BDao dao = new JdbcDao(dataSource);
		ArrayList<BDto> boardDtoList = dao.boardList();
		System.out.println("BListCommand boardDtoList.size()--> " + boardDtoList.size());
		model.addAttribute("boardList", boardDtoList);
	}

}
