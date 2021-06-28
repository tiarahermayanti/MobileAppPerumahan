package com.tiara.apprumah.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseRelasiRumah2{

	@SerializedName("bantuan")
	private List<BantuanItem> bantuan;

	@SerializedName("pekerjaan")
	private List<PekerjaanItem> pekerjaan;

	@SerializedName("kondisiAirK")
	private List<KondisiAirKItem> kondisiAirK;

	@SerializedName("tanah")
	private List<TanahItem> tanah;

	@SerializedName("airK")
	private List<AirKItem> airK;

	@SerializedName("rumah")
	private List<RumahItem> rumah;

	@SerializedName("airM")
	private List<AirMItem> airM;

	@SerializedName("listrik")
	private List<ListrikItem> listrik;

	@SerializedName("atap")
	private List<AtapItem> atap;

	public void setBantuan(List<BantuanItem> bantuan){
		this.bantuan = bantuan;
	}

	public List<BantuanItem> getBantuan(){
		return bantuan;
	}

	public void setPekerjaan(List<PekerjaanItem> pekerjaan){
		this.pekerjaan = pekerjaan;
	}

	public List<PekerjaanItem> getPekerjaan(){
		return pekerjaan;
	}

	public void setKondisiAirK(List<KondisiAirKItem> kondisiAirK){
		this.kondisiAirK = kondisiAirK;
	}

	public List<KondisiAirKItem> getKondisiAirK(){
		return kondisiAirK;
	}

	public void setTanah(List<TanahItem> tanah){
		this.tanah = tanah;
	}

	public List<TanahItem> getTanah(){
		return tanah;
	}

	public void setAirK(List<AirKItem> airK){
		this.airK = airK;
	}

	public List<AirKItem> getAirK(){
		return airK;
	}

	public void setRumah(List<RumahItem> rumah){
		this.rumah = rumah;
	}

	public List<RumahItem> getRumah(){
		return rumah;
	}

	public void setAirM(List<AirMItem> airM){
		this.airM = airM;
	}

	public List<AirMItem> getAirM(){
		return airM;
	}

	public void setListrik(List<ListrikItem> listrik){
		this.listrik = listrik;
	}

	public List<ListrikItem> getListrik(){
		return listrik;
	}

	public void setAtap(List<AtapItem> atap){
		this.atap = atap;
	}

	public List<AtapItem> getAtap(){
		return atap;
	}
}