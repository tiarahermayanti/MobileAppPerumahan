package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class PenghasilanItem{

	@SerializedName("besar_penghasilan")
	private String besarPenghasilan;

	@SerializedName("id_besar_penghasilan")
	private String idBesarPenghasilan;

	public String getBesarPenghasilan(){
		return besarPenghasilan;
	}

	public String getIdBesarPenghasilan(){
		return idBesarPenghasilan;
	}
}