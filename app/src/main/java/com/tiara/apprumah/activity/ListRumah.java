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
import com.tiara.apprumah.adapter.AdapterRumah;
import com.tiara.apprumah.model.DataGetListRumah;
import com.tiara.apprumah.model.ResponseGetListRumah;
import com.tiara.apprumah.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRumah extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    AdapterRumah adapterRumah;
    @BindView(R.id.txt)
    TextView txt;
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

        ConfigRetrofit.getInstance().getListRumah().enqueue(new Callback<ResponseGetListRumah>() {
            @Override
            public void onResponse(Call<ResponseGetListRumah> call, Response<ResponseGetListRumah> response) {
                int s = response.body().getStatus();
                String p = response.body().getPesan();

                if (s == 1) {
                    txt.setVisibility(View.GONE);
                    List<DataGetListRumah> data = response.body().getData();
                    adapterRumah = new AdapterRumah(ListRumah.this, data);
                    recycler.setAdapter(adapterRumah);
                    recycler.setLayoutManager(new LinearLayoutManager(ListRumah.this));
//                    recycler.addItemDecoration(new DividerItemDecoration(ListRumah.this, DividerItemDecoration.VERTICAL));
                } else {
                    txt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetListRumah> call, Throwable t) {
                Toast.makeText(ListRumah.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListRumah.this, InsertRumah.class));
            }
        });

    }
}
