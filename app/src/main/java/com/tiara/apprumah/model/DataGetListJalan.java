package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class DataGetListJalan {

	@SerializedName("panjang_jalan")
	private String panjangJalan;

	@SerializedName("id_jalan")
	private String idJalan;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("img_a")
	private String imgA;

	public void setPanjangJalan(String panjangJalan){
		this.panjangJalan = panjangJalan;
	}

	public String getPanjangJalan(){
		return panjangJalan;
	}

	public void setIdJalan(String idJalan){
		this.idJalan = idJalan;
	}

	public String getIdJalan(){
		return idJalan;
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