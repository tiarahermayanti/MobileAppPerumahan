package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class KondisiAirKItem{

	@SerializedName("kondisi_sistem_pembuangan_air_kotor")
	private String kondisiSistemPembuanganAirKotor;

	@SerializedName("id_kondisi_sistem_pembuangan_air_kotor")
	private String idKondisiSistemPembuanganAirKotor;

	public void setKondisiSistemPembuanganAirKotor(String kondisiSistemPembuanganAirKotor){
		this.kondisiSistemPembuanganAirKotor = kondisiSistemPembuanganAirKotor;
	}

	public String getKondisiSistemPembuanganAirKotor(){
		return kondisiSistemPembuanganAirKotor;
	}

	public void setIdKondisiSistemPembuanganAirKotor(String idKondisiSistemPembuanganAirKotor){
		this.idKondisiSistemPembuanganAirKotor = idKondisiSistemPembuanganAirKotor;
	}

	public String getIdKondisiSistemPembuanganAirKotor(){
		return idKondisiSistemPembuanganAirKotor;
	}
}