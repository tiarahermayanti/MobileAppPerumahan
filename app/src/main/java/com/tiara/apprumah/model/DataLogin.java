package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class DataLogin {

	@SerializedName("nik")
	private String nik;

	@SerializedName("alamat_user")
	private String alamatUser;

	@SerializedName("password")
	private String password;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("level")
	private String level;

	@SerializedName("jabatan")
	private String jabatan;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("email")
	private String email;

	@SerializedName("instansi")
	private String instansi;

	public void setNik(String nik){
		this.nik = nik;
	}

	public String getNik(){
		return nik;
	}

	public void setAlamatUser(String alamatUser){
		this.alamatUser = alamatUser;
	}

	public String getAlamatUser(){
		return alamatUser;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	public void setJabatan(String jabatan){
		this.jabatan = jabatan;
	}

	public String getJabatan(){
		return jabatan;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setInstansi(String instansi){
		this.instansi = instansi;
	}

	public String getInstansi(){
		return instansi;
	}
}