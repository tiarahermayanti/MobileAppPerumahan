package com.tiara.apprumah.model;

import com.google.gson.annotations.SerializedName;

public class ResponseCount{

	@SerializedName("jalan")
	private int jalan;

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("drainase")
	private int drainase;

	@SerializedName("RLTH")
	private int rLTH;

	@SerializedName("Rumah")
	private int rumah;

	@SerializedName("air")
	private int air;

	@SerializedName("status")
	private int status;

	@SerializedName("RLH")
	private int rLH;

	@SerializedName("limbah")
	private int limbah;

	public void setJalan(int jalan){
		this.jalan = jalan;
	}

	public int getJalan(){
		return jalan;
	}

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setDrainase(int drainase){
		this.drainase = drainase;
	}

	public int getDrainase(){
		return drainase;
	}

	public void setRLTH(int rLTH){
		this.rLTH = rLTH;
	}

	public int getRLTH(){
		return rLTH;
	}

	public void setRumah(int rumah){
		this.rumah = rumah;
	}

	public int getRumah(){
		return rumah;
	}

	public void setAir(int air){
		this.air = air;
	}

	public int getAir(){
		return air;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	public void setRLH(int rLH){
		this.rLH = rLH;
	}

	public int getRLH(){
		return rLH;
	}

	public void setLimbah(int limbah){
		this.limbah = limbah;
	}

	public int getLimbah(){
		return limbah;
	}
}