package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class TanahItem{

	@SerializedName("status_kepemilikan_tanah")
	private String statusKepemilikanTanah;

	@SerializedName("id_status_kepemilikan_tanah")
	private String idStatusKepemilikanTanah;

	public void setStatusKepemilikanTanah(String statusKepemilikanTanah){
		this.statusKepemilikanTanah = statusKepemilikanTanah;
	}

	public String getStatusKepemilikanTanah(){
		return statusKepemilikanTanah;
	}

	public void setIdStatusKepemilikanTanah(String idStatusKepemilikanTanah){
		this.idStatusKepemilikanTanah = idStatusKepemilikanTanah;
	}

	public String getIdStatusKepemilikanTanah(){
		return idStatusKepemilikanTanah;
	}
}