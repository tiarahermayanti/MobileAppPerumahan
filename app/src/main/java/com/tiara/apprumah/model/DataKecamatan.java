package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class DataKecamatan {

	@SerializedName("id_kecamatan")
	private String idKecamatan;

	@SerializedName("nama_kecamatan")
	private String namaKecamatan;

	public void setIdKecamatan(String idKecamatan){
		this.idKecamatan = idKecamatan;
	}

	public String getIdKecamatan(){
		return idKecamatan;
	}

	public void setNamaKecamatan(String namaKecamatan){
		this.namaKecamatan = namaKecamatan;
	}

	public String getNamaKecamatan(){
		return namaKecamatan;
	}
}