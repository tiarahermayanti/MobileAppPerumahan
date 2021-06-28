package com.tiara.apprumah.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.tiara.apprumah.R;
import com.tiara.apprumah.SharedPrefManager;
import com.tiara.apprumah.model.AirKItem;
import com.tiara.apprumah.model.AirMItem;
import com.tiara.apprumah.model.AtapItem;
import com.tiara.apprumah.model.BantuanItem;
import com.tiara.apprumah.model.DataKecamatan;
import com.tiara.apprumah.model.DataKelurahan;
import com.tiara.apprumah.model.DindingItem;
import com.tiara.apprumah.model.JambanItem;
import com.tiara.apprumah.model.JenisKawItem;
import com.tiara.apprumah.model.KamarMItem;
import com.tiara.apprumah.model.KondisKamarMItem;
import com.tiara.apprumah.model.KondisiAirKItem;
import com.tiara.apprumah.model.KondisiJambanItem;
import com.tiara.apprumah.model.LantaiItem;
import com.tiara.apprumah.model.ListrikItem;
import com.tiara.apprumah.model.PekerjaanItem;
import com.tiara.apprumah.model.PenghasilanItem;
import com.tiara.apprumah.model.ResponseGetKelurahan;
import com.tiara.apprumah.model.ResponseKecamatan;
import com.tiara.apprumah.model.ResponsePost;
import com.tiara.apprumah.model.ResponseRelasiRumah1;
import com.tiara.apprumah.model.ResponseRelasiRumah2;
import com.tiara.apprumah.model.RumahItem;
import com.tiara.apprumah.model.TanahItem;
import com.tiara.apprumah.restapi.ConfigRetrofit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertRumah extends AppCompatActivity {

    @BindView(R.id.btnLokasi)
    Button btnLokasi;
    @BindView(R.id.edtLat)
    AppCompatEditText edtLat;
    @BindView(R.id.edtLong)
    AppCompatEditText edtLong;
    @BindView(R.id.edtKK)
    EditText edtKK;
    @BindView(R.id.edtNama)
    EditText edtNama;
    @BindView(R.id.edtSuku)
    EditText edtSuku;
    @BindView(R.id.spinPekerjaan)
    Spinner spinPekerjaan;
    @BindView(R.id.spinPenghasilan)
    Spinner spinPenghasilan;
    @BindView(R.id.edtAlamat)
    EditText edtAlamat;
    @BindView(R.id.spinKecamatan)
    Spinner spinKecamatan;
    @BindView(R.id.spinKelurahan)
    Spinner spinKelurahan;
    @BindView(R.id.spinStatTanah)
    Spinner spinStatTanah;
    @BindView(R.id.spinStatRumah)
    Spinner spinStatRumah;
    @BindView(R.id.spinBantuan)
    Spinner spinBantuan;
    @BindView(R.id.edtTahunB)
    EditText edtTahunB;
    @BindView(R.id.edtNamaB)
    EditText edtNamaB;
    @BindView(R.id.edtNamaPB)
    EditText edtNamaPB;
    @BindView(R.id.edtNominalB)
    EditText edtNominalB;
    @BindView(R.id.spinJenisKaw)
    Spinner spinJenisKaw;
    @BindView(R.id.spinKamarM)
    Spinner spinKamarM;
    @BindView(R.id.spinKondKamarM)
    Spinner spinKondKamarM;
    @BindView(R.id.spinJamban)
    Spinner spinJamban;
    @BindView(R.id.spinKondJamban)
    Spinner spinKondJamban;
    @BindView(R.id.spinAirK)
    Spinner spinAirK;
    @BindView(R.id.spinKondAirK)
    Spinner spinKondAirK;
    @BindView(R.id.spinAirM)
    Spinner spinAirM;
    @BindView(R.id.spinListrik)
    Spinner spinListrik;
    @BindView(R.id.edtLuas)
    EditText edtLuas;
    @BindView(R.id.edtJumlahP)
    EditText edtJumlahP;
    @BindView(R.id.spinAtap)
    Spinner spinAtap;
    @BindView(R.id.spinDinding)
    Spinner spinDinding;
    @BindView(R.id.spinLantai)
    Spinner spinLantai;
    @BindView(R.id.btnPilihImgA)
    Button btnPilihImgA;
    @BindView(R.id.imgA)
    ImageView imgA;
    @BindView(R.id.btnPilihImgB)
    Button btnPilihImgB;
    @BindView(R.id.imgB)
    ImageView imgB;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;
    @BindView(R.id.txtTahunB)
    TextView txtTahunB;
    @BindView(R.id.txtNamaB)
    TextView txtNamaB;
    @BindView(R.id.txtNamaPB)
    TextView txtNamaPB;
    @BindView(R.id.txtNominalB)
    TextView txtNominalB;

    SharedPrefManager spf;
    String alamat, lat, longitude, idKcmt, idKlrh, idUser, idPekerjaan, idPenghasilan, idTanah, idStatRumah, idBantuan, idJenisKaw, idKamarM, idKondKM,
            idJamban, idKondJamban, idAirK, idKondAirK, idAirM, idListrik, idAtap, idDinding, idLantai;
    @BindView(R.id.toolbar_satu)
    Toolbar toolbarSatu;


    private int PICK_IMAGE_A = 1;
    private int PICK_IMAGE_B = 3;
    private int PLACE_PICKER_REQUEST = 2;
    private static final int STORAGE_PERMISSION_CODE = 123;
    int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private Uri filePathA, filePathB;
    private String pathA, pathB;


    HashMap<String, String> hmKcmt = new HashMap<String, String>();
    HashMap<String, String> hmKlrh = new HashMap<String, String>();
    HashMap<String, String> hmPekerjaan = new HashMap<String, String>();
    HashMap<String, String> hmPenghasilan = new HashMap<String, String>();
    HashMap<String, String> hmStatTanah = new HashMap<String, String>();
    HashMap<String, String> hmStatRumah = new HashMap<String, String>();
    HashMap<String, String> hmBantuan = new HashMap<String, String>();
    HashMap<String, String> hmJenisKaw = new HashMap<String, String>();
    HashMap<String, String> hmKamarM = new HashMap<String, String>();
    HashMap<String, String> hmKondKM = new HashMap<String, String>();
    HashMap<String, String> hmJamban = new HashMap<String, String>();
    HashMap<String, String> hmKondJamban = new HashMap<String, String>();
    HashMap<String, String> hmAirK = new HashMap<String, String>();
    HashMap<String, String> hmKondAirK = new HashMap<String, String>();
    HashMap<String, String> hmAirM = new HashMap<String, String>();
    HashMap<String, String> hmListrik = new HashMap<String, String>();
    HashMap<String, String> hmAtap = new HashMap<String, String>();
    HashMap<String, String> hmDinding = new HashMap<String, String>();
    HashMap<String, String> hmLantai = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_rumah);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarSatu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spf = new SharedPrefManager(InsertRumah.this);
        idUser = spf.getSP_IDUSER();

        listPekerjaan();
        listPenghasilan();
        listKecamatan();
        listStatTanah();
        listStatRumah();
        listBantuan();
        listJenisKaw();
        listKamarMandi();
        listKondKamarMandi();
        listJamban();
        listKondJamban();
        listAirK();
        listKondAirK();
        listAirM();
        listListrik();
        listAtap();
        listDinding();
        listLantai();

        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    //menjalankan place picker
                    startActivityForResult(builder.build(InsertRumah.this), PLACE_PICKER_REQUEST);

                    // check apabila <a title="Solusi Tidak Bisa Download Google Play Services di Android" href="http://www.twoh.co/2014/11/solusi-tidak-bisa-download-google-play-services-di-android/" target="_blank">Google Play Services tidak terinstall</a> di HP
                } catch (GooglePlayServicesRepairableException e) {
                    Toast.makeText(InsertRumah.this, "catch1", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    Toast.makeText(InsertRumah.this, "catch2", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        btnPilihImgA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pilihImgA();
            }
        });

        btnPilihImgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pilihImgB();
            }
        });

//        if(idKcmt.isEmpty() || idKlrh.isEmpty() || idUser.isEmpty() || idPekerjaan.isEmpty() || idPenghasilan.isEmpty() || idTanah.isEmpty() ||
//                idStatRumah.isEmpty() || idBantuan.isEmpty() || idJenisKaw.isEmpty() || idKamarM.isEmpty() || idKondKM.isEmpty() ||
//                idJamban.isEmpty() || idKondJamban.isEmpty() || idAirK.isEmpty() || idKondAirK.isEmpty() || idAirM.isEmpty() || idListrik.isEmpty() ||
//                idAtap.isEmpty() || idDinding.isEmpty() || idLantai.isEmpty()){
//            Toast.makeText(InsertRumah.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
//
//        } else{

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan();

            }
        });
//        }
    }

    private void simpan() {
        String patha = getPath(filePathA);
        File imgFileA = new File(patha);
        RequestBody reqA = RequestBody.create(MediaType.parse("multipart/form-file"), imgFileA);
        String postA = "imga";

        String pathb = getPath(filePathB);
        File imgFileB = new File(pathb);
        RequestBody reqB = RequestBody.create(MediaType.parse("multipart/form-file"), imgFileB);
        String postB = "imgb";

        MultipartBody.Part partImageA = MultipartBody.Part.createFormData(postA, imgFileA.getName(), reqA);
        MultipartBody.Part partImageB = MultipartBody.Part.createFormData(postB, imgFileB.getName(), reqB);
        RequestBody rbLat = RequestBody.create(MediaType.parse("text/plain"), edtLat.getText().toString());
        RequestBody rbLong = RequestBody.create(MediaType.parse("text/plain"), edtLong.getText().toString());
        RequestBody rbKK = RequestBody.create(MediaType.parse("text/plain"), edtKK.getText().toString());
        RequestBody rbNama = RequestBody.create(MediaType.parse("text/plain"), edtNama.getText().toString());
        RequestBody rbSuku = RequestBody.create(MediaType.parse("text/plain"), edtSuku.getText().toString());
        RequestBody rbAlamat = RequestBody.create(MediaType.parse("text/plain"), edtAlamat.getText().toString());
        RequestBody rbTahunB = RequestBody.create(MediaType.parse("text/plain"), edtTahunB.getText().toString());
        RequestBody rbNamaB = RequestBody.create(MediaType.parse("text/plain"), edtNamaB.getText().toString());
        RequestBody rbNamaPB = RequestBody.create(MediaType.parse("text/plain"), edtNamaPB.getText().toString());
        RequestBody rbNominalB = RequestBody.create(MediaType.parse("text/plain"), edtNominalB.getText().toString());
        RequestBody rbLuas = RequestBody.create(MediaType.parse("text/plain"), edtLuas.getText().toString());
        RequestBody rbJmlh = RequestBody.create(MediaType.parse("text/plain"), edtJumlahP.getText().toString());
        RequestBody rbPekerjaan = RequestBody.create(MediaType.parse("text/plain"), idPekerjaan);
        RequestBody rbPenghasilan = RequestBody.create(MediaType.parse("text/plain"), idPenghasilan);
        RequestBody rbKcmt = RequestBody.create(MediaType.parse("text/plain"), idKcmt);
        RequestBody rbKlrh = RequestBody.create(MediaType.parse("text/plain"), idKlrh);
        RequestBody rbTanah = RequestBody.create(MediaType.parse("text/plain"), idTanah);
        RequestBody rbRumah = RequestBody.create(MediaType.parse("text/plain"), idStatRumah);
        RequestBody rbBantuan = RequestBody.create(MediaType.parse("text/plain"), idBantuan);
        RequestBody rbJenisKaw = RequestBody.create(MediaType.parse("text/plain"), idJenisKaw);
        RequestBody rbKamarM = RequestBody.create(MediaType.parse("text/plain"), idKamarM);
        RequestBody rbKondKamarM = RequestBody.create(MediaType.parse("text/plain"), idKondKM);
        RequestBody rbJamban = RequestBody.create(MediaType.parse("text/plain"), idJamban);
        RequestBody rbKondJamban = RequestBody.create(MediaType.parse("text/plain"), idKondJamban);
        RequestBody rbAirK = RequestBody.create(MediaType.parse("text/plain"), idAirK);
        RequestBody rbKondAirK = RequestBody.create(MediaType.parse("text/plain"), idKondAirK);
        RequestBody rbAirM = RequestBody.create(MediaType.parse("text/plain"), idAirM);
        RequestBody rbListrik = RequestBody.create(MediaType.parse("text/plain"), idListrik);
        RequestBody rbAtap = RequestBody.create(MediaType.parse("text/plain"), idAtap);
        RequestBody rbDinding = RequestBody.create(MediaType.parse("text/plain"), idDinding);
        RequestBody rbLantai = RequestBody.create(MediaType.parse("text/plain"), idLantai);
        RequestBody rbUser = RequestBody.create(MediaType.parse("text/plain"), idUser);
        RequestBody rbStat = RequestBody.create(MediaType.parse("text/plain"), "1");

        ConfigRetrofit.getInstance().insertRumah(partImageA, partImageB, rbKK, rbNama, rbSuku, rbPekerjaan,
                rbPenghasilan, rbAlamat, rbKcmt, rbKlrh, rbTanah, rbRumah, rbBantuan, rbTahunB,
                rbNamaB, rbNamaPB, rbNominalB, rbJenisKaw, rbKamarM, rbKondKamarM, rbJamban,
                rbKondJamban, rbAirK, rbKondAirK, rbAirM, rbListrik, rbLuas, rbJmlh, rbAtap,
                rbDinding, rbLantai, rbLat, rbLong, rbUser, rbStat).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1) {
                    Toast.makeText(InsertRumah.this, pesan, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(InsertRumah.this, ListRumah.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(InsertRumah.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void listLantai() {
        ConfigRetrofit.getInstance().getRelasiRumah1().enqueue(new Callback<ResponseRelasiRumah1>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah1> call, Response<ResponseRelasiRumah1> response) {
                List<LantaiItem> data = response.body().getLantai();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getKondisiLantai());
                    hmLantai.put(data.get(i).getKondisiLantai(), data.get(i).getIdKondisiLantai());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinLantai.setPrompt("Pilih Kondisi Lantai");
                spinLantai.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah1> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinLantai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idLantai = hmLantai.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listDinding() {
        ConfigRetrofit.getInstance().getRelasiRumah1().enqueue(new Callback<ResponseRelasiRumah1>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah1> call, Response<ResponseRelasiRumah1> response) {
                List<DindingItem> data = response.body().getDinding();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getKondisiDinding());
                    hmDinding.put(data.get(i).getKondisiDinding(), data.get(i).getIdKondisiDinding());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinDinding.setPrompt("Pilih Kondisi Dinding");
                spinDinding.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah1> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinDinding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idDinding = hmDinding.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listAtap() {
        ConfigRetrofit.getInstance().getRelasiRumah2().enqueue(new Callback<ResponseRelasiRumah2>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah2> call, Response<ResponseRelasiRumah2> response) {
                List<AtapItem> data = response.body().getAtap();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getKondisiPenutupAtap());
                    hmAtap.put(data.get(i).getKondisiPenutupAtap(), data.get(i).getIdKondisiPenutupAtap());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinAtap.setPrompt("Pilih Kondisi Penutup Atap");
                spinAtap.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah2> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinAtap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idAtap = hmAtap.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listListrik() {
        ConfigRetrofit.getInstance().getRelasiRumah2().enqueue(new Callback<ResponseRelasiRumah2>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah2> call, Response<ResponseRelasiRumah2> response) {
                List<ListrikItem> data = response.body().getListrik();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getSumberListrik());
                    hmListrik.put(data.get(i).getSumberListrik(), data.get(i).getIdSumberListrik());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinListrik.setPrompt("Pilih Sumber Listrik");
                spinListrik.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah2> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinListrik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idListrik = hmListrik.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listAirM() {
        ConfigRetrofit.getInstance().getRelasiRumah2().enqueue(new Callback<ResponseRelasiRumah2>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah2> call, Response<ResponseRelasiRumah2> response) {
                List<AirMItem> data = response.body().getAirM();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getSumberAirMinum());
                    hmAirM.put(data.get(i).getSumberAirMinum(), data.get(i).getIdSumberAirMinum());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinAirM.setPrompt("Pilih Sumber Air Minum");
                spinAirM.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah2> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinAirM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idAirM = hmAirM.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listKondAirK() {
        ConfigRetrofit.getInstance().getRelasiRumah2().enqueue(new Callback<ResponseRelasiRumah2>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah2> call, Response<ResponseRelasiRumah2> response) {
                List<KondisiAirKItem> data = response.body().getKondisiAirK();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getKondisiSistemPembuanganAirKotor());
                    hmKondAirK.put(data.get(i).getKondisiSistemPembuanganAirKotor(), data.get(i).getIdKondisiSistemPembuanganAirKotor());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinKondAirK.setPrompt("Pilih Kondisi Sistem Pembuangan Air Kotor");
                spinKondAirK.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah2> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinKondAirK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idKondAirK = hmKondAirK.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listAirK() {
        ConfigRetrofit.getInstance().getRelasiRumah2().enqueue(new Callback<ResponseRelasiRumah2>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah2> call, Response<ResponseRelasiRumah2> response) {
                List<AirKItem> data = response.body().getAirK();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getSistemPembuanganAirKotor());
                    hmAirK.put(data.get(i).getSistemPembuanganAirKotor(), data.get(i).getIdSistemPembuanganAirKotor());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinAirK.setPrompt("Pilih Sistem Pembuangan Air Kotor");
                spinAirK.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah2> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinAirK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idAirK = hmAirK.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listKondJamban() {
        ConfigRetrofit.getInstance().getRelasiRumah1().enqueue(new Callback<ResponseRelasiRumah1>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah1> call, Response<ResponseRelasiRumah1> response) {
                List<KondisiJambanItem> data = response.body().getKondisiJamban();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getKondisiJamban());
                    hmKondJamban.put(data.get(i).getKondisiJamban(), data.get(i).getIdKondisiJamban());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinKondJamban.setPrompt("Pilih Kondisi Jamban");
                spinKondJamban.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah1> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinKondJamban.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idKondJamban = hmKondJamban.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listJamban() {
        ConfigRetrofit.getInstance().getRelasiRumah1().enqueue(new Callback<ResponseRelasiRumah1>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah1> call, Response<ResponseRelasiRumah1> response) {
                List<JambanItem> data = response.body().getJamban();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getJamban());
                    hmJamban.put(data.get(i).getJamban(), data.get(i).getIdJamban());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinJamban.setPrompt("Pilih Jamban");
                spinJamban.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah1> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinJamban.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idJamban = hmJamban.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listKondKamarMandi() {
        ConfigRetrofit.getInstance().getRelasiRumah1().enqueue(new Callback<ResponseRelasiRumah1>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah1> call, Response<ResponseRelasiRumah1> response) {
                List<KondisKamarMItem> data = response.body().getKondisKamarM();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getKondisiKamarMandi());
                    hmKondKM.put(data.get(i).getKondisiKamarMandi(), data.get(i).getIdKondisiKamarMandi());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinKondKamarM.setPrompt("Pilih Kondisi Kamar Mandi");
                spinKondKamarM.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah1> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinKondKamarM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idKondKM = hmKondKM.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void listKamarMandi() {
        ConfigRetrofit.getInstance().getRelasiRumah1().enqueue(new Callback<ResponseRelasiRumah1>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah1> call, Response<ResponseRelasiRumah1> response) {
                List<KamarMItem> data = response.body().getKamarM();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getKamarMandi());
                    hmKamarM.put(data.get(i).getKamarMandi(), data.get(i).getIdKamarMandi());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinKamarM.setPrompt("Pilih Kamar Mandi");
                spinKamarM.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah1> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinKamarM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idKamarM = hmKamarM.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listJenisKaw() {
        ConfigRetrofit.getInstance().getRelasiRumah1().enqueue(new Callback<ResponseRelasiRumah1>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah1> call, Response<ResponseRelasiRumah1> response) {
                List<JenisKawItem> data = response.body().getJenisKaw();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getJenisKawasanLokasi());
                    hmJenisKaw.put(data.get(i).getJenisKawasanLokasi(), data.get(i).getIdJenisKawasanLokasi());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinJenisKaw.setPrompt("Pilih Jenis Kawasan Lokasi");
                spinJenisKaw.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah1> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();

            }
        });

        spinJenisKaw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idJenisKaw = hmJenisKaw.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listBantuan() {
        ConfigRetrofit.getInstance().getRelasiRumah2().enqueue(new Callback<ResponseRelasiRumah2>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah2> call, Response<ResponseRelasiRumah2> response) {
                List<BantuanItem> data = response.body().getBantuan();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getPernahMendapatkanBantuan());
                    hmBantuan.put(data.get(i).getPernahMendapatkanBantuan(), data.get(i).getIdPernahMendapatkanBantuan());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinBantuan.setPrompt("Pilih Pernah Mendapatkan Bantuan");
                spinBantuan.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah2> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinBantuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idBantuan = hmBantuan.get(select);

                if (idBantuan.equals("1")) {

                    txtNamaB.setVisibility(View.VISIBLE);
                    txtTahunB.setVisibility(View.VISIBLE);
                    txtNamaPB.setVisibility(View.VISIBLE);
                    txtNominalB.setVisibility(View.VISIBLE);
                    edtNamaB.setVisibility(View.VISIBLE);
                    edtNamaPB.setVisibility(View.VISIBLE);
                    edtTahunB.setVisibility(View.VISIBLE);
                    edtNominalB.setVisibility(View.VISIBLE);

                } else if (idBantuan.equals("2")) {
                    txtNamaB.setVisibility(View.GONE);
                    txtTahunB.setVisibility(View.GONE);
                    txtNamaPB.setVisibility(View.GONE);
                    txtNominalB.setVisibility(View.GONE);
                    edtNamaB.setVisibility(View.GONE);
                    edtNamaPB.setVisibility(View.GONE);
                    edtTahunB.setVisibility(View.GONE);
                    edtNominalB.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                txtNamaB.setVisibility(View.GONE);
                txtTahunB.setVisibility(View.GONE);
                txtNamaPB.setVisibility(View.GONE);
                txtNominalB.setVisibility(View.GONE);
                edtNamaB.setVisibility(View.GONE);
                edtNamaPB.setVisibility(View.GONE);
                edtTahunB.setVisibility(View.GONE);
                edtNominalB.setVisibility(View.GONE);
            }
        });
    }

    private void listStatRumah() {
        ConfigRetrofit.getInstance().getRelasiRumah2().enqueue(new Callback<ResponseRelasiRumah2>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah2> call, Response<ResponseRelasiRumah2> response) {
                List<RumahItem> data = response.body().getRumah();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getStatusKepemilikanRumah());
                    hmStatRumah.put(data.get(i).getStatusKepemilikanRumah(), data.get(i).getIdStatusKepemilikanRumah());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinStatRumah.setPrompt("Pilih Status Kepemilikan Rumah");
                spinStatRumah.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah2> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinStatRumah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idStatRumah = hmStatRumah.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listStatTanah() {
        ConfigRetrofit.getInstance().getRelasiRumah2().enqueue(new Callback<ResponseRelasiRumah2>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah2> call, Response<ResponseRelasiRumah2> response) {
                List<TanahItem> data = response.body().getTanah();
                List<String> listSpinner = new ArrayList<String>();
                for (int i = 0; i < data.size(); i++) {
                    listSpinner.add(data.get(i).getStatusKepemilikanTanah());
                    hmStatTanah.put(data.get(i).getStatusKepemilikanTanah(), data.get(i).getIdStatusKepemilikanTanah());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinStatTanah.setPrompt("Pilih Status Kepemilikan Tanah");
                spinStatTanah.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah2> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinStatTanah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String slcKlrh = parent.getItemAtPosition(position).toString();
                idTanah = hmStatTanah.get(slcKlrh);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void listKecamatan() {
        ConfigRetrofit.getInstance().getKecamatan().enqueue(new Callback<ResponseKecamatan>() {
            @Override
            public void onResponse(Call<ResponseKecamatan> call, Response<ResponseKecamatan> response) {
                int status = response.body().getStatus();
                if (status == 1) {
                    List<DataKecamatan> data = response.body().getData();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < data.size(); i++) {
                        listSpinner.add(data.get(i).getNamaKecamatan());
                        hmKcmt.put(data.get(i).getNamaKecamatan(), data.get(i).getIdKecamatan());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinKecamatan.setPrompt("Pilih Kecamatan");
                    spinKecamatan.setAdapter(adapter);
                } else {
                    Toast.makeText(InsertRumah.this, "Gagal Mengambil Data Kecamatan", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseKecamatan> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });


        spinKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String slcKcmt = parent.getItemAtPosition(position).toString();
                idKcmt = hmKcmt.get(slcKcmt);
                initKelurahan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initKelurahan() {
        ConfigRetrofit.getInstance().getKelurahan(idKcmt).enqueue(new Callback<ResponseGetKelurahan>() {
            @Override
            public void onResponse(Call<ResponseGetKelurahan> call, Response<ResponseGetKelurahan> response) {
                int sts = response.body().getStatus();
                if (sts == 1) {
                    List<DataKelurahan> data = response.body().getData();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < data.size(); i++) {
                        listSpinner.add(data.get(i).getNamaKelurahan());
                        hmKlrh.put(data.get(i).getNamaKelurahan(), data.get(i).getIdKelurahan());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinKelurahan.setPrompt("Pilih Kelurahan/Nagari");
                    spinKelurahan.setAdapter(adapter);
                } else {
                    Toast.makeText(InsertRumah.this, "Gagal Mengambil Data Kelurahan", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseGetKelurahan> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinKelurahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String slcKlrh = parent.getItemAtPosition(position).toString();
                idKlrh = hmKlrh.get(slcKlrh);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void listPenghasilan() {
        ConfigRetrofit.getInstance().getRelasiRumah1().enqueue(new Callback<ResponseRelasiRumah1>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah1> call, Response<ResponseRelasiRumah1> response) {
                List<PenghasilanItem> data = response.body().getPenghasilan();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getBesarPenghasilan());
                    hmPenghasilan.put(data.get(i).getBesarPenghasilan(), data.get(i).getIdBesarPenghasilan());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinPenghasilan.setPrompt("Pilih Penghasilan");
                spinPenghasilan.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah1> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinPenghasilan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idPenghasilan = hmPenghasilan.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listPekerjaan() {
        ConfigRetrofit.getInstance().getRelasiRumah2().enqueue(new Callback<ResponseRelasiRumah2>() {
            @Override
            public void onResponse(Call<ResponseRelasiRumah2> call, Response<ResponseRelasiRumah2> response) {
                List<PekerjaanItem> data = response.body().getPekerjaan();
                List<String> listSpin = new ArrayList<String>();

                for (int i = 0; i < data.size(); i++) {
                    listSpin.add(data.get(i).getPekerjaanUtama());
                    hmPekerjaan.put(data.get(i).getPekerjaanUtama(), data.get(i).getIdPekerjaanUtama());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(InsertRumah.this, android.R.layout.simple_spinner_item, listSpin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinPekerjaan.setPrompt("Pilih Pekerjaan Utama");
                spinPekerjaan.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseRelasiRumah2> call, Throwable t) {
                Toast.makeText(InsertRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();

            }
        });

        spinPekerjaan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idPekerjaan = hmPekerjaan.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void pilihImgB() {
        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
            // do your stuff..
        }
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityIfNeeded(Intent.createChooser(intent, "Pilih Foto"), PICK_IMAGE_B);
    }

    private void pilihImgA() {
        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
            // do your stuff..
        }
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityIfNeeded(Intent.createChooser(intent, "Pilih Foto"), PICK_IMAGE_A);
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{permission},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Image A
        if (requestCode == PICK_IMAGE_A && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePathA = data.getData();
            String[] imageprojection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(filePathA, imageprojection, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int indexImage = cursor.getColumnIndex(imageprojection[0]);
                pathA = cursor.getString(indexImage);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePathA);
                    imgA.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//        Image B
        if (requestCode == PICK_IMAGE_B && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePathB = data.getData();
            String[] imageprojection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(filePathB, imageprojection, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int indexImage = cursor.getColumnIndex(imageprojection[0]);
                pathB = cursor.getString(indexImage);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePathB);
                    imgB.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //         menangkap hasil balikan dari Place Picker, dan menampilkannya pada TextView
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                alamat = place.getAddress().toString();
                Double dlat = place.getLatLng().latitude;
                Double dlong = place.getLatLng().longitude;
                lat = dlat.toString();
                longitude = dlong.toString();

                edtAlamat.setText(alamat);
                edtLat.setText(lat);
                edtLong.setText(longitude);
            }
        }
    }

    public String getPath(Uri uri) {

//        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
//            // do your stuff..
//        }

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();

        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == STORAGE_PERMISSION_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "GET_ACCOUNTS Denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
