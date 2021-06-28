package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class BantuanItem{

	@SerializedName("id_pernah_mendapatkan_bantuan")
	private String idPernahMendapatkanBantuan;

	@SerializedName("pernah_mendapatkan_bantuan")
	private String pernahMendapatkanBantuan;

	public void setIdPernahMendapatkanBantuan(String idPernahMendapatkanBantuan){
		this.idPernahMendapatkanBantuan = idPernahMendapatkanBantuan;
	}

	public String getIdPernahMendapatkanBantuan(){
		return idPernahMendapatkanBantuan;
	}

	public void setPernahMendapatkanBantuan(String pernahMendapatkanBantuan){
		this.pernahMendapatkanBantuan = pernahMendapatkanBantuan;
	}

	public String getPernahMendapatkanBantuan(){
		return pernahMendapatkanBantuan;
	}
}