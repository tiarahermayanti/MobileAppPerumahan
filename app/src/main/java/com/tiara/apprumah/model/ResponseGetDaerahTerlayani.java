package com.tiara.apprumah.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetDaerahTerlayani{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataDaerahTerlayani> data;

	@SerializedName("status")
	private int status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataDaerahTerlayani> data){
		this.data = data;
	}

	public List<DataDaerahTerlayani> getData(){
		return data;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}