package com.tiara.apprumah.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tiara.apprumah.R;
import com.tiara.apprumah.adapter.AdapterJalan;
import com.tiara.apprumah.model.DataGetListJalan;
import com.tiara.apprumah.model.ResponseGetListJalan;
import com.tiara.apprumah.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListJalan extends AppCompatActivity {

    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    AdapterJalan adapterJalan;
    @BindView(R.id.btnTambah)
    Button btnTambah;
    @BindView(R.id.toolbar_satu)
    Toolbar toolbarSatu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarSatu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt.setVisibility(View.GONE);

        ConfigRetrofit.getInstance().getListJalan().enqueue(new Callback<ResponseGetListJalan>() {
            @Override
            public void onResponse(Call<ResponseGetListJalan> call, Response<ResponseGetListJalan> response) {
                int s = response.body().getStatus();
                String p = response.body().getPesan();
                if (s == 1) {
                    txt.setVisibility(View.GONE);
                    List<DataGetListJalan> data = response.body().getData();
                    adapterJalan = new AdapterJalan(ListJalan.this, data);
                    recycler.setAdapter(adapterJalan);
                    recycler.setLayoutManager(new LinearLayoutManager(ListJalan.this));
                } else {
                    txt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetListJalan> call, Throwable t) {
                Toast.makeText(ListJalan.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListJalan.this, InsertJalan.class));
            }
        });
    }
}
