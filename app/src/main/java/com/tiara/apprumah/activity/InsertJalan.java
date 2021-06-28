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
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.tiara.apprumah.R;
import com.tiara.apprumah.SharedPrefManager;
import com.tiara.apprumah.model.DataDaerahTerlayani;
import com.tiara.apprumah.model.DataKecamatan;
import com.tiara.apprumah.model.DataKelurahan;
import com.tiara.apprumah.model.DataPerkerasan;
import com.tiara.apprumah.model.ResponseGetDaerahTerlayani;
import com.tiara.apprumah.model.ResponseGetKelurahan;
import com.tiara.apprumah.model.ResponseGetPerkerasan;
import com.tiara.apprumah.model.ResponseKecamatan;
import com.tiara.apprumah.model.ResponsePost;
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

public class InsertJalan extends AppCompatActivity {

    @BindView(R.id.btnLokasi)
    Button btnLokasi;
    @BindView(R.id.edtLat)
    EditText edtLat;
    @BindView(R.id.edtLong)
    EditText edtLong;
    @BindView(R.id.edtAlamat)
    EditText edtAlamat;
    @BindView(R.id.spinKcmt)
    Spinner spinKcmt;
    @BindView(R.id.spinKlrh)
    Spinner spinKlrh;
    @BindView(R.id.spinPerkerasan)
    Spinner spinPerkerasan;
    @BindView(R.id.spinTerlayani)
    Spinner spinTerlayani;
    @BindView(R.id.edtPanjang)
    EditText edtPanjang;
    @BindView(R.id.edtB)
    EditText edtB;
    @BindView(R.id.edtRS)
    EditText edtRS;
    @BindView(R.id.edtRB)
    EditText edtRB;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;
    @BindView(R.id.btnPilihImgA)
    Button btnPilihImgA;
    @BindView(R.id.imgA)
    ImageView imgA;
    @BindView(R.id.btnPilihImgB)
    Button btnPilihImgB;
    @BindView(R.id.imgB)
    ImageView imgB;

    String alamat, lat, longitude, idKcmt, idKlrh, idPerkerasan, idTerlayani, idUser;
    SharedPrefManager spf;
    @BindView(R.id.toolbar_satu)
    Toolbar toolbarSatu;
    @BindView(R.id.ft1)
    TextView ft1;

    private int PICK_IMAGE_A = 1;
    private int PICK_IMAGE_B = 3;
    private int PLACE_PICKER_REQUEST = 2;
    private static final int STORAGE_PERMISSION_CODE = 123;
    int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private Uri filePathA, filePathB;
    private String pathA, pathB;

    HashMap<String, String> hmTerlayani = new HashMap<String, String>();
    HashMap<String, String> hmPerkerasan = new HashMap<String, String>();
    HashMap<String, String> hmKcmt = new HashMap<String, String>();
    HashMap<String, String> hmKlrh = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_jalan);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarSatu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        spf = new SharedPrefManager(InsertJalan.this);
        idUser = spf.getSP_IDUSER();

        listTerlayani();
        listPerkerasan();
        listKecamatan();

        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    //menjalankan place picker
                    startActivityForResult(builder.build(InsertJalan.this), PLACE_PICKER_REQUEST);

                    // check apabila <a title="Solusi Tidak Bisa Download Google Play Services di Android" href="http://www.twoh.co/2014/11/solusi-tidak-bisa-download-google-play-services-di-android/" target="_blank">Google Play Services tidak terinstall</a> di HP
                } catch (GooglePlayServicesRepairableException e) {
                    Toast.makeText(InsertJalan.this, "catch1", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    Toast.makeText(InsertJalan.this, "catch2", Toast.LENGTH_SHORT).show();
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

//        if(idKcmt == null || idKlrh== null  || idPerkerasan== null || idTerlayani== null || idUser== null ){
//            Toast.makeText(InsertJalan.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
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
        RequestBody rbAlamat = RequestBody.create(MediaType.parse("text/plain"), edtAlamat.getText().toString());
        RequestBody rbKcmt = RequestBody.create(MediaType.parse("text/plain"), idKcmt);
        RequestBody rbKlrh = RequestBody.create(MediaType.parse("text/plain"), idKlrh);
        RequestBody rbTerlayani = RequestBody.create(MediaType.parse("text/plain"), idTerlayani);
        RequestBody rbPerkerasan = RequestBody.create(MediaType.parse("text/plain"), idPerkerasan);
        RequestBody rbPanjang = RequestBody.create(MediaType.parse("text/plain"), edtPanjang.getText().toString());
        RequestBody rbB = RequestBody.create(MediaType.parse("text/plain"), edtB.getText().toString());
        RequestBody rbRS = RequestBody.create(MediaType.parse("text/plain"), edtRS.getText().toString());
        RequestBody rbRB = RequestBody.create(MediaType.parse("text/plain"), edtRB.getText().toString());
        RequestBody rbUser = RequestBody.create(MediaType.parse("text/plain"), idUser);
        RequestBody rbStat = RequestBody.create(MediaType.parse("text/plain"), "1");

        ConfigRetrofit.getInstance().insertJalan(partImageA, partImageB, rbAlamat, rbKcmt, rbKlrh, rbPerkerasan, rbPanjang, rbRB, rbRS, rbB, rbTerlayani, rbLat, rbLong, rbUser, rbStat).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1) {
                    Toast.makeText(InsertJalan.this, pesan, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(InsertJalan.this, ListJalan.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(InsertJalan.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(InsertJalan.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
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

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertJalan.this, android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinKcmt.setAdapter(adapter);

                } else {
                    Toast.makeText(InsertJalan.this, "Gagal Mengambil Data Kecamatan", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseKecamatan> call, Throwable t) {
                Toast.makeText(InsertJalan.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });


        spinKcmt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertJalan.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinKlrh.setPrompt("Pilih Kelurahan/Nagari");
                    spinKlrh.setAdapter(adapter);
                } else {
                    Toast.makeText(InsertJalan.this, "Gagal Mengambil Data Kelurahan", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseGetKelurahan> call, Throwable t) {
                Toast.makeText(InsertJalan.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinKlrh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void listPerkerasan() {
        ConfigRetrofit.getInstance().getPerkerasan().enqueue(new Callback<ResponseGetPerkerasan>() {
            @Override
            public void onResponse(Call<ResponseGetPerkerasan> call, Response<ResponseGetPerkerasan> response) {
                int stat = response.body().getStatus();
                if (stat == 1) {
                    List<DataPerkerasan> data = response.body().getData();
                    List<String> listSpin = new ArrayList<String>();

                    for (int i = 0; i < data.size(); i++) {
                        listSpin.add(data.get(i).getJenisPerkerasan());
                        hmPerkerasan.put(data.get(i).getJenisPerkerasan(), data.get(i).getIdJenisPerkerasan());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertJalan.this, android.R.layout.simple_spinner_item, listSpin);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinPerkerasan.setPrompt("Pilih Jenis Perkerasan");
                    spinPerkerasan.setAdapter(adapter);
                } else {
                    Toast.makeText(InsertJalan.this, "Gagal Mengambil Data Perkerasan", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseGetPerkerasan> call, Throwable t) {

                Toast.makeText(InsertJalan.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinPerkerasan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idPerkerasan = hmPerkerasan.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listTerlayani() {
        ConfigRetrofit.getInstance().getDaerahLayanan().enqueue(new Callback<ResponseGetDaerahTerlayani>() {
            @Override
            public void onResponse(Call<ResponseGetDaerahTerlayani> call, Response<ResponseGetDaerahTerlayani> response) {
                int stat = response.body().getStatus();
                if (stat == 1) {
                    List<DataDaerahTerlayani> data = response.body().getData();
                    List<String> listSpin = new ArrayList<String>();

                    for (int i = 0; i < data.size(); i++) {
                        listSpin.add(data.get(i).getDaerahLayanan());
                        hmTerlayani.put(data.get(i).getDaerahLayanan(), data.get(i).getIdDaerahLayanan());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertJalan.this, android.R.layout.simple_spinner_item, listSpin);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinTerlayani.setPrompt("Pilih Daerah Terlayani");
                    spinTerlayani.setAdapter(adapter);
                } else {
                    Toast.makeText(InsertJalan.this, "Gagal Mengambil Data Daera Terlayani", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseGetDaerahTerlayani> call, Throwable t) {

                Toast.makeText(InsertJalan.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        spinTerlayani.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = adapterView.getItemAtPosition(i).toString();
                idTerlayani = hmTerlayani.get(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
