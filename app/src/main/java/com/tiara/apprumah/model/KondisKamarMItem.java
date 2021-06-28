package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class KondisKamarMItem{

	@SerializedName("kondisi_kamar_mandi")
	private String kondisiKamarMandi;

	@SerializedName("id_kondisi_kamar_mandi")
	private String idKondisiKamarMandi;

	public String getKondisiKamarMandi(){
		return kondisiKamarMandi;
	}

	public String getIdKondisiKamarMandi(){
		return idKondisiKamarMandi;
	}
}