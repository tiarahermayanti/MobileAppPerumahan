package com.tiara.apprumah.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;
import com.tiara.apprumah.R;
import com.tiara.apprumah.model.DataRumahById;
import com.tiara.apprumah.model.ResponseDelete;
import com.tiara.apprumah.model.ResponseGetRumahById;
import com.tiara.apprumah.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tiara.apprumah.BuildConfig.FOTO;

public class DetailRumah extends AppCompatActivity {

    @BindView(R.id.pmlk)
    TextView pmlk;
    @BindView(R.id.txtNama)
    TextView txtNama;
    @BindView(R.id.imgRumah)
    ImageView imgRumah;
    @BindView(R.id.txtKk)
    TextView txtKk;
    @BindView(R.id.txtNamaPemilik)
    TextView txtNamaPemilik;
    @BindView(R.id.txtSuku)
    TextView txtSuku;
    @BindView(R.id.txtPekerjaan)
    TextView txtPekerjaan;
    @BindView(R.id.txtPenghasilan)
    TextView txtPenghasilan;
    @BindView(R.id.txtAlamat)
    TextView txtAlamat;
    @BindView(R.id.txtKecamatan)
    TextView txtKecamatan;
    @BindView(R.id.txtKelurahan)
    TextView txtKelurahan;
    @BindView(R.id.txtStatTanah)
    TextView txtStatTanah;
    @BindView(R.id.txtStatRumah)
    TextView txtStatRumah;
    @BindView(R.id.txtPerbaikan)
    TextView txtPerbaikan;
    @BindView(R.id.txtTahunB)
    TextView txtTahunB;
    @BindView(R.id.txtNamaB)
    TextView txtNamaB;
    @BindView(R.id.txtNamaPB)
    TextView txtNamaPB;
    @BindView(R.id.txtNominalB)
    TextView txtNominalB;
    @BindView(R.id.txtJenisK)
    TextView txtJenisK;
    @BindView(R.id.txtKamarM)
    TextView txtKamarM;
    @BindView(R.id.txtKondisiKM)
    TextView txtKondisiKM;
    @BindView(R.id.txtJamban)
    TextView txtJamban;
    @BindView(R.id.txtSistemP)
    TextView txtSistemP;
    @BindView(R.id.KondisiP)
    TextView KondisiP;
    @BindView(R.id.txtAir)
    TextView txtAir;
    @BindView(R.id.txtListrik)
    TextView txtListrik;
    @BindView(R.id.txtLuas)
    TextView txtLuas;
    @BindView(R.id.txtPenghuni)
    TextView txtPenghuni;
    @BindView(R.id.txtAtap)
    TextView txtAtap;
    @BindView(R.id.txtDinding)
    TextView txtDinding;
    @BindView(R.id.txtLantai)
    TextView txtLantai;
    @BindView(R.id.txtDokumen)
    TextView txtDokumen;
    @BindView(R.id.imgDok1)
    ImageView imgDok1;
    @BindView(R.id.imgDok2)
    ImageView imgDok2;

    String idRumah;
    @BindView(R.id.tulisTahunB)
    TextView tulisTahunB;
    @BindView(R.id.tulisNamaB)
    TextView tulisNamaB;
    @BindView(R.id.tulisProgramB)
    TextView tulisProgramB;
    @BindView(R.id.tulisNominalB)
    TextView tulisNominalB;
    @BindView(R.id.btnHapus)
    Button btnHapus;
    @BindView(R.id.ft1)
    TextView ft1;
    @BindView(R.id.toolbar_satu)
    Toolbar toolbarSatu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rumah);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarSatu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        idRumah = extras.getString("idRumah");

        ConfigRetrofit.getInstance().getRumahById(idRumah).enqueue(new Callback<ResponseGetRumahById>() {
            @Override
            public void onResponse(Call<ResponseGetRumahById> call, Response<ResponseGetRumahById> response) {
                int s = response.body().getStatus();
                String p = response.body().getPesan();

                if (s == 1) {
                    final List<DataRumahById> data = response.body().getData();

                    txtNama.setText(data.get(0).getNama());
                    Picasso.get().load(FOTO + "foto_rumah/" + data.get(0).getImgA()).into(imgRumah);


                    txtKk.setText(data.get(0).getKk());
                    txtNamaPemilik.setText(data.get(0).getNama());
                    txtSuku.setText(data.get(0).getSuku());
                    txtPekerjaan.setText(data.get(0).getPekerjaanUtama());
                    txtPenghasilan.setText(data.get(0).getBesarPenghasilan());
                    txtAlamat.setText(data.get(0).getAlamat());
                    txtKecamatan.setText(data.get(0).getNamaKecamatan());
                    txtKelurahan.setText(data.get(0).getNamaKelurahan());
                    txtStatTanah.setText(data.get(0).getStatusKepemilikanTanah());
                    txtStatRumah.setText(data.get(0).getStatusKepemilikanRumah());
                    txtPerbaikan.setText(data.get(0).getPernahMendapatkanBantuan());

                    String bantuan = data.get(0).getPernahMendapatkanBantuanId();
                    if (bantuan.equals("1")) {
                        txtTahunB.setText(data.get(0).getTahunBantuan());
                        txtNamaB.setText(data.get(0).getNamaBantuan());
                        txtNamaPB.setText(data.get(0).getProgramBantuan());
                        txtNominalB.setText(data.get(0).getNominalBantuan());
                    } else if (bantuan.equals("2")) {
                        txtTahunB.setVisibility(View.GONE);
                        txtNamaB.setVisibility(View.GONE);
                        txtNamaPB.setVisibility(View.GONE);
                        txtNominalB.setVisibility(View.GONE);
                        tulisNamaB.setVisibility(View.GONE);
                        tulisTahunB.setVisibility(View.GONE);
                        tulisProgramB.setVisibility(View.GONE);
                        tulisNominalB.setVisibility(View.GONE);
                    }

                    txtJenisK.setText(data.get(0).getJenisKawasanLokasi());

                    txtKamarM.setText(data.get(0).getKamarMandi());
                    txtKondisiKM.setText(data.get(0).getKondisiKamarMandi());
                    txtJamban.setText(data.get(0).getJamban());
                    txtSistemP.setText(data.get(0).getSistemPembuanganAirKotor());
                    KondisiP.setText(data.get(0).getKondisiSistemPembuanganAirKotor());
                    txtAir.setText(data.get(0).getSumberAirMinum());
                    txtListrik.setText(data.get(0).getSumberListrik());

                    txtLuas.setText(data.get(0).getLuasRumah());
                    txtPenghuni.setText(data.get(0).getJumlahPenghuni());

                    txtAtap.setText(data.get(0).getKondisiPenutupAtap());
                    txtDinding.setText(data.get(0).getKondisiDinding());
                    txtLantai.setText(data.get(0).getKondisiLantai());

                    Picasso.get().load(FOTO + "foto_rumah/" + data.get(0).getImgA()).into(imgDok1);
                    Picasso.get().load(FOTO + "foto_rumah/" + data.get(0).getImgB()).into(imgDok2);

                }
            }

            @Override
            public void onFailure(Call<ResponseGetRumahById> call, Throwable t) {
                Toast.makeText(DetailRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DetailRumah.this);
                alert.setTitle("Konfirmasi");
                alert.setMessage("Anda yakin ingin menghapus data ini ?");
                alert.setCancelable(false);

                alert.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ConfigRetrofit.getInstance().deleteRumah(idRumah).enqueue(new Callback<ResponseDelete>() {
                            @Override
                            public void onResponse(Call<ResponseDelete> call, Response<ResponseDelete> response) {
                                String pesan = response.body().getPesan();
                                int status = response.body().getStatus();
                                if (status == 1) {
                                    Toast.makeText(DetailRumah.this, pesan, Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(DetailRumah.this, ListRumah.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(DetailRumah.this, pesan, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseDelete> call, Throwable t) {
                                Toast.makeText(DetailRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alert.show();
            }
        });
    }


}
