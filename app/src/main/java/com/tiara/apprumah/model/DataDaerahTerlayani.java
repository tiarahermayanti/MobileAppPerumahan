package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class DataDaerahTerlayani {

	@SerializedName("id_daerah_layanan")
	private String idDaerahLayanan;

	@SerializedName("daerah_layanan")
	private String daerahLayanan;

	public void setIdDaerahLayanan(String idDaerahLayanan){
		this.idDaerahLayanan = idDaerahLayanan;
	}

	public String getIdDaerahLayanan(){
		return idDaerahLayanan;
	}

	public void setDaerahLayanan(String daerahLayanan){
		this.daerahLayanan = daerahLayanan;
	}

	public String getDaerahLayanan(){
		return daerahLayanan;
	}
}