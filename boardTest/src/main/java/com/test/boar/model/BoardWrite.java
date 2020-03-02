package com.test.boar.model;

import org.springframework.web.multipart.MultipartFile;

public class BoardWrite {

	private String title;
	private String content;
	private String author;
	private String fileName;
	private String fileNameOrigin;
	private MultipartFile uploadfile;

	
	public MultipartFile getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}
	@Override
	public String toString() {
		return "BoardWrite [title=" + title + ", content=" + content + ", author=" + author + ", fileName=" + fileName
				+ ", fileNameOrigin=" + fileNameOrigin + ", getTitle()=" + getTitle() + ", getContent()=" + getContent()
				+ ", getFileName()=" + getFileName() + ", getFileNameOrigin()=" + getFileNameOrigin() + ", getAuthor()="
				+ getAuthor() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
