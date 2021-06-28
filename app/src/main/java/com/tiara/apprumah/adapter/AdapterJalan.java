package com.tiara.apprumah.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tiara.apprumah.R;
import com.tiara.apprumah.activity.DetailJalan;
import com.tiara.apprumah.activity.ListJalan;
import com.tiara.apprumah.model.DataGetListJalan;
import com.tiara.apprumah.model.ResponseDelete;
import com.tiara.apprumah.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tiara.apprumah.BuildConfig.FOTO;

public class AdapterJalan extends RecyclerView.Adapter<AdapterJalan.MyHolder> {

    List<DataGetListJalan> dataJalan;
    Context context;
    View view;


    public AdapterJalan(Context context, List<DataGetListJalan> dataJalan) {
        this.context = context;
        this.dataJalan = dataJalan;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jalan, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        final String idJalan = dataJalan.get(position).getIdJalan();
        holder.txtAlamat.setText(dataJalan.get(position).getAlamat());
        holder.txtPanjang.setText(dataJalan.get(position).getPanjangJalan());
        Picasso.get().load(FOTO + "foto_jalan/" + dataJalan.get(position).getImgA()).into(holder.imgRumah);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailJalan.class);
                i.putExtra("idJalan", idJalan);
                context.startActivity(i);
            }
        });

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailJalan.class);
                i.putExtra("idRumah", idJalan);
                context.startActivity(i);
            }
        });

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
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
                                    Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(context, ListJalan.class);
                                    context.startActivity(i);
                                } else {
                                    Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseDelete> call, Throwable t) {
                                Toast.makeText(context, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
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

    @Override
    public int getItemCount() {
        if (dataJalan == null) {
            return 0;
        } else {
            return dataJalan.size();
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgRumah)
        ImageView imgRumah;
        @BindView(R.id.txtAlamat)
        TextView txtAlamat;
        @BindView(R.id.txtPanjang)
        TextView txtPanjang;
        @BindView(R.id.btnDetail)
        Button btnDetail;
        @BindView(R.id.btnHapus)
        Button btnHapus;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
