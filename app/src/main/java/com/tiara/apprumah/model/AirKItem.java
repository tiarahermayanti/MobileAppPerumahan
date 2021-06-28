package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class AirKItem{

	@SerializedName("sistem_pembuangan_air_kotor")
	private String sistemPembuanganAirKotor;

	@SerializedName("id_sistem_pembuangan_air_kotor")
	private String idSistemPembuanganAirKotor;

	public void setSistemPembuanganAirKotor(String sistemPembuanganAirKotor){
		this.sistemPembuanganAirKotor = sistemPembuanganAirKotor;
	}

	public String getSistemPembuanganAirKotor(){
		return sistemPembuanganAirKotor;
	}

	public void setIdSistemPembuanganAirKotor(String idSistemPembuanganAirKotor){
		this.idSistemPembuanganAirKotor = idSistemPembuanganAirKotor;
	}

	public String getIdSistemPembuanganAirKotor(){
		return idSistemPembuanganAirKotor;
	}
}