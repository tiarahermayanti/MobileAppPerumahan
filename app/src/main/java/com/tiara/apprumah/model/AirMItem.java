package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class AirMItem{

	@SerializedName("id_sumber_air_minum")
	private String idSumberAirMinum;

	@SerializedName("sumber_air_minum")
	private String sumberAirMinum;

	public void setIdSumberAirMinum(String idSumberAirMinum){
		this.idSumberAirMinum = idSumberAirMinum;
	}

	public String getIdSumberAirMinum(){
		return idSumberAirMinum;
	}

	public void setSumberAirMinum(String sumberAirMinum){
		this.sumberAirMinum = sumberAirMinum;
	}

	public String getSumberAirMinum(){
		return sumberAirMinum;
	}
}