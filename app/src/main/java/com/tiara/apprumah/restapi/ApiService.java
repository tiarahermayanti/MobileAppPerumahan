package com.tiara.apprumah.restapi;

import com.tiara.apprumah.model.ResponseCount;
import com.tiara.apprumah.model.ResponseDelete;
import com.tiara.apprumah.model.ResponseGetDaerahTerlayani;
import com.tiara.apprumah.model.ResponseGetJalanById;
import com.tiara.apprumah.model.ResponseGetKelurahan;
import com.tiara.apprumah.model.ResponseGetListJalan;
import com.tiara.apprumah.model.ResponseGetListRumah;
import com.tiara.apprumah.model.ResponseGetPerkerasan;
import com.tiara.apprumah.model.ResponseGetRumahById;
import com.tiara.apprumah.model.ResponseKecamatan;
import com.tiara.apprumah.model.ResponseLogin;
import com.tiara.apprumah.model.ResponsePost;
import com.tiara.apprumah.model.ResponseRelasiRumah1;
import com.tiara.apprumah.model.ResponseRelasiRumah2;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @GET("getListRumah")
    Call<ResponseGetListRumah> getListRumah();

    @FormUrlEncoded
    @POST("getRumahById")
    Call<ResponseGetRumahById> getRumahById(@Field("id_rumah")String id_rumah);

    @GET("countRumah")
    Call<ResponseCount> getCount();

    @GET("getListJalan")
    Call<ResponseGetListJalan> getListJalan();

    @FormUrlEncoded
    @POST("getJalanById")
    Call<ResponseGetJalanById> getJalanById(@Field("id_jalan")String id_jalan);

    @FormUrlEncoded
    @POST("login_user")
    Call<ResponseLogin> login(@Field("email")String email,
                              @Field("password")String pass,
                              @Field("level")String level);

    @GET("getKecamatan")
    Call<ResponseKecamatan> getKecamatan();

    @FormUrlEncoded
    @POST("getKelurahanById")
    Call<ResponseGetKelurahan> getKelurahan(@Field("id_kecamatan")String id_kecamatan);

    @GET("getDaerahLayanan")
    Call<ResponseGetDaerahTerlayani> getDaerahLayanan();

    @GET("getPerkerasan")
    Call<ResponseGetPerkerasan> getPerkerasan();

    @Multipart
    @POST("insertJalan")
    Call<ResponsePost> insertJalan(@Part MultipartBody.Part imga,
                                   @Part MultipartBody.Part imgb,
                                   @Part("alamat") RequestBody alamat,
                                   @Part("kecamatan_id") RequestBody kecamatan_id,
                                   @Part("kelurahan_id") RequestBody kelurahan_id,
                                   @Part("jenis_perkerasan_id") RequestBody jenis_perkerasan_id,
                                   @Part("panjang_jalan") RequestBody panjang_jalan,
                                   @Part("kondisi_jalan_rb") RequestBody kondisi_jalan_rb,
                                   @Part("kondisi_jalan_rs") RequestBody kondisi_jalan_rs,
                                   @Part("kondisi_jalan_b") RequestBody kondisi_jalan_b,
                                   @Part("daerah_terlayani_id") RequestBody daerah_terlayani_id,
                                   @Part("latitude") RequestBody latitude,
                                   @Part("longitude") RequestBody longitude,
                                   @Part("upload_by") RequestBody upload_by,
                                   @Part("status_id") RequestBody status_id
                                   );

    @Multipart
    @POST("insertRumah")
    Call<ResponsePost> insertRumah(@Part MultipartBody.Part imga,
                                   @Part MultipartBody.Part imgb,
                                   @Part("kk") RequestBody kk,
                                   @Part("nama") RequestBody nama,
                                   @Part("suku") RequestBody suku,
                                   @Part("pekerjaan_utama_id") RequestBody pekerjaan,
                                   @Part("besar_penghasilan_perbulan_id") RequestBody penghasilan,
                                   @Part("alamat") RequestBody alamat,
                                   @Part("kecamatan_id") RequestBody kecamatan_id,
                                   @Part("kelurahan_id") RequestBody kelurahan_id,
                                   @Part("status_kepemilikan_tanah_id") RequestBody idStatTanah,
                                   @Part("status_kepemilikan_rumah_id") RequestBody idStatRumah,
                                   @Part("pernah_mendapatkan_bantuan_id") RequestBody pernah_mendapatkan_bantuan_id,
                                   @Part("tahun_bantuan") RequestBody tahun_bantuan,
                                   @Part("nama_bantuan") RequestBody nama_bantuan,
                                   @Part("program_bantuan") RequestBody program_bantuan,
                                   @Part("nominal_bantuan") RequestBody nominal_bantuan,
                                   @Part("jenis_kawasan_id") RequestBody jenis_kawasan_id,
                                   @Part("kamar_mandi_id") RequestBody kamar_mandi_id,
                                   @Part("kondisi_kamar_mandi_id") RequestBody kondisi_kamar_mandi_id,
                                   @Part("jamban_id") RequestBody jamban_id,
                                   @Part("kondisi_jamban_id") RequestBody kondisi_jamban_id,
                                   @Part("sistem_pembuangan_air_kotor_id") RequestBody sistem_pembuangan_air_kotor_id,
                                   @Part("kondisi_sistem_pembuangan_air_kotor_id") RequestBody kondisi_sistem_pembuangan_air_kotor_id,
                                   @Part("sumber_air_minum_id") RequestBody sumber_air_minum_id,
                                   @Part("sumber_listrik_id") RequestBody sumber_listrik_id,
                                   @Part("luas_rumah") RequestBody luas_rumah,
                                   @Part("jumlah_penghuni") RequestBody jumlah_penghuni,
                                   @Part("kondisi_penutup_atap_id") RequestBody kondisi_penutup_atap_id ,
                                   @Part("kondisi_dinding_id") RequestBody kondisi_dinding_id,
                                   @Part("kondisi_lantai_id") RequestBody kondisi_lantai_id,
                                   @Part("latitude") RequestBody latitude,
                                   @Part("longitude") RequestBody longitude,
                                   @Part("upload_by") RequestBody upload_by,
                                   @Part("status_id") RequestBody status_id
    );

    @FormUrlEncoded
    @POST("deleteRumah")
    Call<ResponseDelete> deleteRumah(@Field("id_rumah")String idRumah);

    @FormUrlEncoded
    @POST("deleteJalan")
    Call<ResponseDelete> deleteJalan(@Field("id_jalan")String idJalan);


    @GET("getRelasiRumah1")
    Call<ResponseRelasiRumah1> getRelasiRumah1();

    @GET("getRelasiRumah2")
    Call<ResponseRelasiRumah2> getRelasiRumah2();







}
