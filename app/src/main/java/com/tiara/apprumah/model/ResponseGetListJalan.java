package com.tiara.apprumah.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetListJalan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataGetListJalan> data;

	@SerializedName("status")
	private int status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataGetListJalan> data){
		this.data = data;
	}

	public List<DataGetListJalan> getData(){
		return data;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}