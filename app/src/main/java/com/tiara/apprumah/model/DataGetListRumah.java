package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class DataGetListRumah {

	@SerializedName("status_rumah")
	private String statusRumah;

	@SerializedName("nama")
	private String nama;

	@SerializedName("id_rumah")
	private String idRumah;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("img_a")
	private String imgA;

	public void setStatusRumah(String statusRumah){
		this.statusRumah = statusRumah;
	}

	public String getStatusRumah(){
		return statusRumah;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setIdRumah(String idRumah){
		this.idRumah = idRumah;
	}

	public String getIdRumah(){
		return idRumah;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setImgA(String imgA){
		this.imgA = imgA;
	}

	public String getImgA(){
		return imgA;
	}
}