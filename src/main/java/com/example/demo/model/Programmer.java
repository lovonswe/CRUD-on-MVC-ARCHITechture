package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // it make the class or object an database entity
public class Programmer {
	
	@Id // it's used to define pId as a primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pId;
	@Column(name="programmer_name")
	private String pName;
	private String pLang;
	public Programmer() {
		super();
	}
	public Programmer(int pId, String pName, String pLang) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pLang = pLang;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpLang() {
		return pLang;
	}
	public void setpLang(String pLang) {
		this.pLang = pLang;
	}
	
	@Override
	public String toString() {
		return "Programmer [pId=" +pId+ ", pName=" + pName + ", pLang="+pLang + "]";
	}
}
