package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class KamarMItem{

	@SerializedName("id_kamar_mandi")
	private String idKamarMandi;

	@SerializedName("kamar_mandi")
	private String kamarMandi;

	public String getIdKamarMandi(){
		return idKamarMandi;
	}

	public String getKamarMandi(){
		return kamarMandi;
	}
}