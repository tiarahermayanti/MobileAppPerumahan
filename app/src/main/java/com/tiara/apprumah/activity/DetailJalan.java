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
import com.tiara.apprumah.model.DataGetJalanById;
import com.tiara.apprumah.model.ResponseDelete;
import com.tiara.apprumah.model.ResponseGetJalanById;
import com.tiara.apprumah.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tiara.apprumah.BuildConfig.FOTO;

public class DetailJalan extends AppCompatActivity {

    @BindView(R.id.txtAlamat)
    TextView txtAlamat;
    @BindView(R.id.txtKelurahan)
    TextView txtKelurahan;
    @BindView(R.id.txtKecamatan)
    TextView txtKecamatan;
    @BindView(R.id.txtPerkerasan)
    TextView txtPerkerasan;
    @BindView(R.id.txtPanjang)
    TextView txtPanjang;
    @BindView(R.id.txtKondBaik)
    TextView txtKondBaik;
    @BindView(R.id.txtKondRS)
    TextView txtKondRS;
    @BindView(R.id.txtKondRB)
    TextView txtKondRB;
    @BindView(R.id.txtLayanan)
    TextView txtLayanan;
    @BindView(R.id.imgDok1)
    ImageView imgDok1;
    @BindView(R.id.imgDok2)
    ImageView imgDok2;

    String idJalan;
    @BindView(R.id.btnHapus)
    Button btnHapus;
    @BindView(R.id.txtDokumen)
    TextView txtDokumen;
    @BindView(R.id.ft1)
    TextView ft1;
    @BindView(R.id.toolbar_satu)
    Toolbar toolbarSatu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jalan);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarSatu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle extras = getIntent().getExtras();
        idJalan = extras.getString("idJalan");

        ConfigRetrofit.getInstance().getJalanById(idJalan).enqueue(new Callback<ResponseGetJalanById>() {
            @Override
            public void onResponse(Call<ResponseGetJalanById> call, Response<ResponseGetJalanById> response) {
                int s = response.body().getStatus();
                String p = response.body().getPesan();

                if (s == 1) {
                    List<DataGetJalanById> data = response.body().getData();

                    txtAlamat.setText(data.get(0).getAlamat());
                    txtKelurahan.setText(data.get(0).getNamaKelurahan());
                    txtKecamatan.setText(data.get(0).getNamaKecamatan());
                    txtPerkerasan.setText(data.get(0).getJenisPerkerasan());
                    txtPanjang.setText(data.get(0).getPanjangJalan());
                    txtKondBaik.setText(data.get(0).getKondisiJalanB());
                    txtKondRS.setText(data.get(0).getKondisiJalanRs());
                    txtKondRB.setText(data.get(0).getKondisiJalanRb());
                    txtLayanan.setText(data.get(0).getDaerahLayanan());

                    Picasso.get().load(FOTO + "foto_jalan/" + data.get(0).getImgA()).into(imgDok1);
                    Picasso.get().load(FOTO + "foto_jalan/" + data.get(0).getImgB()).into(imgDok2);

                }
            }

            @Override
            public void onFailure(Call<ResponseGetJalanById> call, Throwable t) {
                Toast.makeText(DetailJalan.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DetailJalan.this);
                alert.setTitle("Konfirmasi");
                alert.setMessage("Anda yakin ingin menghapus data ini ?");
                alert.setCancelable(false);

                alert.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ConfigRetrofit.getInstance().deleteJalan(idJalan).enqueue(new Callback<ResponseDelete>() {
                            @Override
                            public void onResponse(Call<ResponseDelete> call, Response<ResponseDelete> response) {
                                String pesan = response.body().getPesan();
                                int status = response.body().getStatus();
                                if (status == 1) {
                                    Toast.makeText(DetailJalan.this, pesan, Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(DetailJalan.this, ListJalan.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(DetailJalan.this, pesan, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseDelete> call, Throwable t) {
                                Toast.makeText(DetailJalan.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
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
