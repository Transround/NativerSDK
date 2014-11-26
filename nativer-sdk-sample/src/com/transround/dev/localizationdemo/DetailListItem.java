package com.transround.dev.localizationdemo;

public class DetailListItem {
	String mTitle;
	String mValue;

	public DetailListItem(String title, String value) {
		super();
		this.mTitle = title;
		this.mValue = value;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public String getValue() {
		return mValue;
	}

	public void setValue(String value) {
		this.mValue = value;
	}

}