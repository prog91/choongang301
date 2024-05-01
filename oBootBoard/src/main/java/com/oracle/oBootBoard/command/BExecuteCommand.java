package com.oracle.oBootBoard.command;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oracle.oBootBoard.dao.BDao;
import com.oracle.oBootBoard.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BExecuteCommand {
	// Dao 만들기
	private final BDao jdbcDao;
	public BExecuteCommand(BDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	
	public void bListCmd(Model model) {
		// Dao 연결
		ArrayList<BDto> boardDtoList = jdbcDao.boardList();
		System.out.println("BListCommand boardDtoList.size()--> " + boardDtoList.size());
		model.addAttribute("boardList", boardDtoList);
	}

	public void bWriteCmd(Model model) {
//			1) model이용, map 선언
//			2) request 이용 -> bName, bTitle, bContent 추출
//			3) dao instance 선언
//			4) write method 이용하여 저장(bName, bTitle, bContent)
								//asMap은 맵을 꺼내오는 메소드
		Map<String, Object> map = model.asMap();
			HttpServletRequest request = (HttpServletRequest) map.get("request");
			String bName = request.getParameter("bName");
			String bTitle = request.getParameter("bTitle");
			String bContent = request.getParameter("bContent");
			
			jdbcDao.write(bName, bTitle, bContent);
			
		
	}
	// HW2
	public void bContentCmd(Model model) {
		
		// 1. model이를 Map으로 전환
		Map<String, Object> map = model.asMap();
		
		// 2. request -> bId Get
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId")) ;
		
		// 3. HW3
		BDto board = jdbcDao.contentView(bId);
		
		model.addAttribute("mvc_board", board);
		
	}

	public void bModifyCmd(Model model) {
		// 1. model Map 선언
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		// 2. parameter -> bId, bName, bTitle, bContent
		int bId = Integer.parseInt(request.getParameter("bId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		jdbcDao.modify(bId, bName, bTitle, bContent);
	}

	public void bReplyViewCmd(Model model) {
		// 1) model이용 , map 선언
		Map<String, Object> map = model.asMap();
		
		// 2) request 이용 -> bid 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		// 3) reply_view method 이용하여 (bid)
		//- BDto dto = dao.reply_view(bId);
		BDto bDto = jdbcDao.reply_view(bId);
		
		model.addAttribute("reply_view", bDto);
	}

	public void bReplyCmd(Model model) {
		//	1)  model이용 , map 선언
		Map<String, Object> map = model.asMap();
		//  2) request 이용 -> bid,         bName ,  bTitle,
//		                    bContent ,  bGroup , bStep ,
//		                    bIndent 추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		int bGroup = Integer.parseInt(request.getParameter("bGroup"));
		int bStep = Integer.parseInt(request.getParameter("bStep"));
		int bIndent = Integer.parseInt(request.getParameter("bIndent"));
//		  3) reply method 이용하여 댓글저장 
//		    - dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
		
		jdbcDao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
		
		

		
	}

	public void bDeleteCmd(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId"));
		jdbcDao.delete(bId);
		
	}
}
