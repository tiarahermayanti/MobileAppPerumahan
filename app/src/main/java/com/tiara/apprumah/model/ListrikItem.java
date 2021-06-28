package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class ListrikItem{

	@SerializedName("sumber_listrik")
	private String sumberListrik;

	@SerializedName("id_sumber_listrik")
	private String idSumberListrik;

	public void setSumberListrik(String sumberListrik){
		this.sumberListrik = sumberListrik;
	}

	public String getSumberListrik(){
		return sumberListrik;
	}

	public void setIdSumberListrik(String idSumberListrik){
		this.idSumberListrik = idSumberListrik;
	}

	public String getIdSumberListrik(){
		return idSumberListrik;
	}
}