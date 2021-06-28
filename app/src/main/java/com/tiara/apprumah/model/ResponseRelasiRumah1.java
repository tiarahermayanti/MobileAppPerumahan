package com.tiara.apprumah.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseRelasiRumah1{

	@SerializedName("penghasilan")
	private List<PenghasilanItem> penghasilan;

	@SerializedName("jenisKaw")
	private List<JenisKawItem> jenisKaw;

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("jamban")
	private List<JambanItem> jamban;

	@SerializedName("kamarM")
	private List<KamarMItem> kamarM;

	@SerializedName("lantai")
	private List<LantaiItem> lantai;

	@SerializedName("kondisiJamban")
	private List<KondisiJambanItem> kondisiJamban;

	@SerializedName("kondisKamarM")
	private List<KondisKamarMItem> kondisKamarM;

	@SerializedName("dinding")
	private List<DindingItem> dinding;

	@SerializedName("status")
	private int status;

	public List<PenghasilanItem> getPenghasilan(){
		return penghasilan;
	}

	public List<JenisKawItem> getJenisKaw(){
		return jenisKaw;
	}

	public String getPesan(){
		return pesan;
	}

	public List<JambanItem> getJamban(){
		return jamban;
	}

	public List<KamarMItem> getKamarM(){
		return kamarM;
	}

	public List<LantaiItem> getLantai(){
		return lantai;
	}

	public List<KondisiJambanItem> getKondisiJamban(){
		return kondisiJamban;
	}

	public List<KondisKamarMItem> getKondisKamarM(){
		return kondisKamarM;
	}

	public List<DindingItem> getDinding(){
		return dinding;
	}

	public int getStatus(){
		return status;
	}
}