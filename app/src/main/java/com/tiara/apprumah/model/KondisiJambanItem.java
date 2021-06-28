package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class KondisiJambanItem{

	@SerializedName("kondisi_jamban")
	private String kondisiJamban;

	@SerializedName("id_kondisi_jamban")
	private String idKondisiJamban;

	public String getKondisiJamban(){
		return kondisiJamban;
	}

	public String getIdKondisiJamban(){
		return idKondisiJamban;
	}
}