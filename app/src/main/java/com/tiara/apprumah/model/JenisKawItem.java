package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class JenisKawItem{

	@SerializedName("jenis_kawasan_lokasi")
	private String jenisKawasanLokasi;

	@SerializedName("id_jenis_kawasan_lokasi")
	private String idJenisKawasanLokasi;

	public String getJenisKawasanLokasi(){
		return jenisKawasanLokasi;
	}

	public String getIdJenisKawasanLokasi(){
		return idJenisKawasanLokasi;
	}
}