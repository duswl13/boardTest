package com.test.boar.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.boar.model.BoardList;
import com.test.boar.model.BoardTest;
import com.test.boar.model.BoardUpdate;
import com.test.boar.model.BoardWrite;

@Repository
public class BoardDao {

	@Autowired
	SqlSessionTemplate session;

	public List<BoardList> getAllList(Map<String, Integer> list) {
		return session.selectList("Board.getAllList",list);
	}

	public int insertBoard(BoardWrite boardWrite) {
		return session.insert("Board.insertBoard",boardWrite);
	}

	public void addReadCount(int boardKey) {
		session.update("Board.addReadCount",boardKey);
	}

	public BoardTest getBoardDetail(int boardKey) {
		return session.selectOne("Board.getBoardDetail",boardKey);
	}

	public int boardDelete(int boardKey) {
		return session.delete("Board.deleteBoard",boardKey);
	}

	public int boardUpdate(BoardUpdate board) {
		return session.update("Board.updateBoard",board);
	}

	public int insertDeleteFile(String originFile) {
		return session.insert("Board.insertDeleteFile",originFile);
	}

	public List<String> getdeleteList() {
		return session.selectList("Board.getdeleteList");
	}

	public void deleteList() {
		session.delete("Board.deleteList");
	}
	
	
}
