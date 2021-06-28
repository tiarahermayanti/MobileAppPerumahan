package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class DindingItem{

	@SerializedName("id_kondisi_dinding")
	private String idKondisiDinding;

	@SerializedName("kondisi_dinding")
	private String kondisiDinding;

	public String getIdKondisiDinding(){
		return idKondisiDinding;
	}

	public String getKondisiDinding(){
		return kondisiDinding;
	}
}