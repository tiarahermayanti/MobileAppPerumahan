package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class RumahItem{

	@SerializedName("id_status_kepemilikan_rumah")
	private String idStatusKepemilikanRumah;

	@SerializedName("status_kepemilikan_rumah")
	private String statusKepemilikanRumah;

	public void setIdStatusKepemilikanRumah(String idStatusKepemilikanRumah){
		this.idStatusKepemilikanRumah = idStatusKepemilikanRumah;
	}

	public String getIdStatusKepemilikanRumah(){
		return idStatusKepemilikanRumah;
	}

	public void setStatusKepemilikanRumah(String statusKepemilikanRumah){
		this.statusKepemilikanRumah = statusKepemilikanRumah;
	}

	public String getStatusKepemilikanRumah(){
		return statusKepemilikanRumah;
	}
}