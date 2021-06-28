package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class JambanItem{

	@SerializedName("jamban")
	private String jamban;

	@SerializedName("id_jamban")
	private String idJamban;

	public String getJamban(){
		return jamban;
	}

	public String getIdJamban(){
		return idJamban;
	}
}