package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class PekerjaanItem{

	@SerializedName("pekerjaan_utama")
	private String pekerjaanUtama;

	@SerializedName("id_pekerjaan_utama")
	private String idPekerjaanUtama;

	public void setPekerjaanUtama(String pekerjaanUtama){
		this.pekerjaanUtama = pekerjaanUtama;
	}

	public String getPekerjaanUtama(){
		return pekerjaanUtama;
	}

	public void setIdPekerjaanUtama(String idPekerjaanUtama){
		this.idPekerjaanUtama = idPekerjaanUtama;
	}

	public String getIdPekerjaanUtama(){
		return idPekerjaanUtama;
	}
}