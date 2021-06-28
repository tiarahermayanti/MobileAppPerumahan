package com.tiara.apprumah.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.tiara.apprumah.R;
import com.tiara.apprumah.model.ResponseCount;
import com.tiara.apprumah.restapi.ConfigRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtTotal)
    TextView txtTotal;
    @BindView(R.id.cardTotal)
    CardView cardTotal;
//    @BindView(R.id.txtLayak)
//    TextView txtLayak;
//    @BindView(R.id.cardRLH)
//    CardView cardRLH;
//    @BindView(R.id.txtKumuh)
//    TextView txtKumuh;
//    @BindView(R.id.cardRTLH)
//    CardView cardRTLH;
    @BindView(R.id.txtDrainase)
    TextView txtDrainase;
    @BindView(R.id.cardDrainase)
    CardView cardDrainase;
    @BindView(R.id.txtJalan)
    TextView txtJalan;
    @BindView(R.id.cardJalan)
    CardView cardJalan;
    @BindView(R.id.txtAir)
    TextView txtAir;
    @BindView(R.id.cardAir)
    CardView cardAir;
    @BindView(R.id.txtLimbah)
    TextView txtLimbah;
    @BindView(R.id.cardLimbah)
    CardView cardLimbah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ConfigRetrofit.getInstance().getCount().enqueue(new Callback<ResponseCount>() {
            @Override
            public void onResponse(Call<ResponseCount> call, Response<ResponseCount> response) {
                int s = response.body().getStatus();
                String p = response.body().getPesan();
                Integer rumah = response.body().getRumah();
                Integer RLTH = response.body().getRLTH();
                Integer RLH = response.body().getRLH();
                Integer air = response.body().getAir();
                Integer drainase = response.body().getDrainase();
                Integer limbah = response.body().getLimbah();
                Integer jalan = response.body().getJalan();

                txtTotal.setText(rumah.toString());
//                txtLayak.setText(RLH.toString());
//                txtKumuh.setText(RLTH.toString());
                txtAir.setText(air.toString());
                txtDrainase.setText(drainase.toString());
                txtJalan.setText(jalan.toString());
                txtLimbah.setText(limbah.toString());

            }

            @Override
            public void onFailure(Call<ResponseCount> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        cardTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListRumah.class));
            }
        });

        cardJalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListJalan.class));
            }
        });

    }
}
