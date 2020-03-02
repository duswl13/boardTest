package com.test.boar.model;

import java.sql.Date;

public class BoardTest {
	
	private int boardKey;
	private String title;
	private String content;
	private String author;
	private Date writeDate;
	private String fileName;
	private String fileNameOrigin;
	private int readCount;
	

	@Override
	public String toString() {
		return "BoardTest [boardKey=" + boardKey + ", title=" + title + ", content=" + content + ", author=" + author
				+ ", writeDate=" + writeDate + ", fileName=" + fileName + ", fileNameOrigin=" + fileNameOrigin
				+ ", readCount=" + readCount + "]";
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
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
