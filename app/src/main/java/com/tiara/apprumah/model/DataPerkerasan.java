package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class DataPerkerasan {

	@SerializedName("jenis_perkerasan")
	private String jenisPerkerasan;

	@SerializedName("id_jenis_perkerasan")
	private String idJenisPerkerasan;

	public void setJenisPerkerasan(String jenisPerkerasan){
		this.jenisPerkerasan = jenisPerkerasan;
	}

	public String getJenisPerkerasan(){
		return jenisPerkerasan;
	}

	public void setIdJenisPerkerasan(String idJenisPerkerasan){
		this.idJenisPerkerasan = idJenisPerkerasan;
	}

	public String getIdJenisPerkerasan(){
		return idJenisPerkerasan;
	}
}