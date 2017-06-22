package com.fmail.bean;

public class Mail{
	
	public long mailid;
	public String sender;
	public String receiver;
	public String subject;
	public String message;
	public String sendingdate;
	public String sendingtime;
	
	public Mail(){}
	
	public long getMailid() {
		return mailid;
	}
	public void setMailid(long mailid) {
		this.mailid = mailid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSendingdate() {
		return sendingdate;
	}
	public void setSendingdate(String sendingdate) {
		this.sendingdate = sendingdate;
	}
	public String getSendingtime() {
		return sendingtime;
	}
	public void setSendingtime(String sendingtime) {
		this.sendingtime = sendingtime;
	}
	

}
