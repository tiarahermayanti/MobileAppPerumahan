package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class AtapItem{

	@SerializedName("kondisi_penutup_atap")
	private String kondisiPenutupAtap;

	@SerializedName("id_kondisi_penutup_atap")
	private String idKondisiPenutupAtap;

	public void setKondisiPenutupAtap(String kondisiPenutupAtap){
		this.kondisiPenutupAtap = kondisiPenutupAtap;
	}

	public String getKondisiPenutupAtap(){
		return kondisiPenutupAtap;
	}

	public void setIdKondisiPenutupAtap(String idKondisiPenutupAtap){
		this.idKondisiPenutupAtap = idKondisiPenutupAtap;
	}

	public String getIdKondisiPenutupAtap(){
		return idKondisiPenutupAtap;
	}
}