package com.test.boar.model;

import org.springframework.web.multipart.MultipartFile;

public class BoardUpdate {
	
	private int boardKey;
	private String title;
	private String content;
	private String fileName;
	private String fileNameOrigin;
	private MultipartFile uploadfile;

	
	public MultipartFile getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
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
	public String getFileNameOrigin() {
		return fileNameOrigin;
	}
	public void setFileNameOrigin(String fileNameOrigin) {
		this.fileNameOrigin = fileNameOrigin;
	}
	
	
}
