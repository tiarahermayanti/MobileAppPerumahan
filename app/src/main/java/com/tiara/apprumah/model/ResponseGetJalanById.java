package com.tiara.apprumah.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetJalanById{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataGetJalanById> data;

	@SerializedName("status")
	private int status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataGetJalanById> data){
		this.data = data;
	}

	public List<DataGetJalanById> getData(){
		return data;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}