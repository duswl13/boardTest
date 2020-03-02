package com.test.boar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.boar.dao.BoardDao;
import com.test.boar.model.BoardList;
import com.test.boar.model.BoardTest;
import com.test.boar.model.BoardUpdate;
import com.test.boar.model.BoardWrite;

@Service
public class BoardService {

	@Autowired
	BoardDao boardDao;

	public List<BoardList> getAllList(int limit, int page) {
		Map<String, Integer> list = new HashMap<String, Integer>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;

		list.put("startrow", startrow);
		list.put("endrow", endrow);
		return boardDao.getAllList(list);
	}

	public int insertBoard(BoardWrite boardWrite) {

		return boardDao.insertBoard(boardWrite);
	}

	public void addReadCount(int boardKey) {
		boardDao.addReadCount(boardKey);
	}

	public BoardTest getBoardDetail(int boardKey) {
		return boardDao.getBoardDetail(boardKey);
	}

	public int boardDelete(int boardKey) {
		return boardDao.boardDelete(boardKey);
	}

	public int boardUpdate(BoardUpdate board) {
		return boardDao.boardUpdate(board);
	}

	public int insertDeleteFile(String originFile) {
		return boardDao.insertDeleteFile(originFile);
	}

	public List<String> getdeleteList() {
		return boardDao.getdeleteList();
	}

	public void deleteList() {
		boardDao.deleteList();		
	}

}
