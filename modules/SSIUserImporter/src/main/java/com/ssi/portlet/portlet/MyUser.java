package com.ssi.portlet.portlet;

import java.util.Locale;

public class MyUser {

	long userId;
	long companyId;
	String screenName;
	String firstName;
	String lastNamne;
	String email;
	Locale locale;
	String passWord;
	String jobTitle;
	boolean isManager=false;
	boolean isGHSI=false;
	boolean isExecutive=false;
	boolean isHR=false;

	
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	public boolean isGHSI() {
		return isGHSI;
	}
	public void setGHSI(boolean isGHSI) {
		this.isGHSI = isGHSI;
	}
	public boolean isExecutive() {
		return isExecutive;
	}
	public void setExecutive(boolean isExecutive) {
		this.isExecutive = isExecutive;
	}
	public boolean isHR() {
		return isHR;
	}
	public void setHR(boolean isHR) {
		this.isHR = isHR;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastNamne() {
		return lastNamne;
	}
	public void setLastNamne(String lastNamne) {
		this.lastNamne = lastNamne;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
	
}
