package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class DataKelurahan {

	@SerializedName("nama_kelurahan")
	private String namaKelurahan;

	@SerializedName("kecamatan_id")
	private String kecamatanId;

	@SerializedName("id_kelurahan")
	private String idKelurahan;

	public void setNamaKelurahan(String namaKelurahan){
		this.namaKelurahan = namaKelurahan;
	}

	public String getNamaKelurahan(){
		return namaKelurahan;
	}

	public void setKecamatanId(String kecamatanId){
		this.kecamatanId = kecamatanId;
	}

	public String getKecamatanId(){
		return kecamatanId;
	}

	public void setIdKelurahan(String idKelurahan){
		this.idKelurahan = idKelurahan;
	}

	public String getIdKelurahan(){
		return idKelurahan;
	}
}