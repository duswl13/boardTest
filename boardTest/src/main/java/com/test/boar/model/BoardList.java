package com.test.boar.model;

import java.sql.Date;

public class BoardList {

	private int boardKey;
	private String title;
	private String author;
	private Date writeDate;
	private String fileNameOrigin;
	private int readCount;
	
	
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getBoardKey() {
		return boardKey;
	}
	public void setBoardKey(int boardKey) {
		this.boardKey = boardKey;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public String getFileNameOrigin() {
		return fileNameOrigin;
	}
	public void setFileNameOrigin(String fileNameOrigin) {
		this.fileNameOrigin = fileNameOrigin;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
