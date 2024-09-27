package com.smart.lms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smart.lms.util.Pagination;
import com.smart.lms.vo.BoardVO;
import com.smart.lms.vo.CalendarVO;
import com.smart.lms.vo.NoteVO;
@Repository
public class BoardDAO {
	
	  @Autowired 
	  private SqlSessionTemplate mybatis;
	  
	  public void sendNote(String n_sender, String n_reciver, String n_title, String n_info) {
		  Map<String, Object>params = new HashMap<>();
			params.put("n_sender", n_sender); // 페이징 시작점
		    params.put("n_reciver", n_reciver);   // 페이지당 항목 수
		    params.put("n_title", n_title);
		    params.put("n_info", n_info);
		  mybatis.insert("boardDAO.sendNote", params);
		  
	  }

		public List<NoteVO> receivNote() {
			
			return mybatis.selectList("boardDAO.receivNote");
		}


		public NoteVO detailNote(String n_number) {
	
			return mybatis.selectOne("boardDAO.detailNote",n_number);
		}


		public void updateNote(String n_number) {
			mybatis.update("boardDAO.updateNote", n_number);
		}


		public void deleteNote(String n_number) {
			mybatis.delete("boardDAO.deleteNote", n_number);
			
		}


		public List<NoteVO> searchNote(String search) {
			return mybatis.selectList("boardDAO.searchNote", search);
		}


		public List<NoteVO> getNotesWithPagination(int start, int size, String userId) {
			Map<String, Object>params = new HashMap<>();
			params.put("start", start); // 페이징 시작점
		    params.put("size", size);   // 페이지당 항목 수
		    params.put("userId", userId);

		    return mybatis.selectList("boardDAO.getNotesWithPagination", params);
		}


		public int getTotalNoteCount(String userId) {
		    return mybatis.selectOne("boardDAO.getTotalNoteCount", userId);
		}
		
		public int getTotalSearchNoteCount(String search, String userId) {
			Map<String, Object>params = new HashMap<>();
			params.put("search", search);
			params.put("userId", userId);
		    return mybatis.selectOne("boardDAO.getSearchTotalNoteCount", params);
		}


		public List<NoteVO> getSearchNotesWithPagination(int start, int size, String search, String userId) {
			Map<String, Object>params = new HashMap<>();
			params.put("start", start);
			params.put("size", size);
			params.put("search", search);
			params.put("userId", userId);
			return mybatis.selectList("boardDAO.getSearchNotesWithPagination", params);
		}
		
		
//		boardDAO-------

		public List<BoardVO> getBoardList(Pagination pg) {
			return mybatis.selectList("boardDAO.getBoardList", pg);
		}

	  public void insertBoard(BoardVO vo) {
		     mybatis.insert("boardDAO.insertBoard", vo);
		}
		
		
	  public BoardVO getBoard(int b_number) {
		     return mybatis.selectOne("boardDAO.getBoard", b_number);
		}
		
	  public void deleteBoard(int b_number) {
		    mybatis.delete("boardDAO.deleteBoard", b_number);
	    }
	   
	  
	  public void updateBoard(BoardVO vo) {
		  	mybatis.update("boardDAO.updateBoard", vo);
	   }
	  
	  public CalendarVO getCal(CalendarVO vo) {
		     return mybatis.selectOne("boardDAO.getCal", vo);
		}
	  
	  public void insertCal(CalendarVO vo) {
		     mybatis.insert("boardDAO.insertCal", vo);
		}

	public List<CalendarVO> getCalList() {
		return mybatis.selectList("boardDAO.getCalList");

	}
	
	public int getBoardListTotalCnt(Pagination pg) {
		return mybatis.selectOne("boardDAO.getBoardListTotalCnt", pg);
		
	}
	
	 public void deleteCal(CalendarVO vo) {
		    mybatis.delete("boardDAO.deleteCal", vo);
	    }

}
