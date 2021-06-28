package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class LantaiItem{

	@SerializedName("kondisi_lantai")
	private String kondisiLantai;

	@SerializedName("id_kondisi_lantai")
	private String idKondisiLantai;

	public String getKondisiLantai(){
		return kondisiLantai;
	}

	public String getIdKondisiLantai(){
		return idKondisiLantai;
	}
}