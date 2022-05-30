package com.tukorea.ciwa.domain;

public class FileVO {
	private String title;
	private String userid;
	private String type;
	private String modifyDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "FileVO [title=" + title + ", userid=" + userid + ", type=" + type + ", modifyDate=" + modifyDate + "]";
	}
}
