package com.tiara.apprumah;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public static final String SP_INDUSTRI_APP = "SIMPAN";
    public static final String SP_IDUSER = "spIdUser";
    public static final String SP_NAMA = "spNama";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_INDUSTRI_APP, Context.MODE_PRIVATE);

    }

    public void setSP_SUDAH_LOGIN (Boolean value) {
        spEditor = sp.edit();
        spEditor.putBoolean(SP_SUDAH_LOGIN, value).apply();
    }


    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

    public void setSP_IDUSER(String id) {
        spEditor = sp.edit();
        spEditor.putString(SP_IDUSER, id).apply();
    }

    public String getSP_IDUSER(){
        return sp.getString(SP_IDUSER, "");
    }
}
