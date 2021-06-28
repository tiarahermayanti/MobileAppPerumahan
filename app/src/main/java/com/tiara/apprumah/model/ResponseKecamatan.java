package com.tiara.apprumah.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKecamatan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataKecamatan> data;

	@SerializedName("status")
	private int status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataKecamatan> data){
		this.data = data;
	}

	public List<DataKecamatan> getData(){
		return data;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}