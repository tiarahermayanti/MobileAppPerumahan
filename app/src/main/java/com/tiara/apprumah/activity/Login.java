package com.tiara.apprumah.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tiara.apprumah.R;
import com.tiara.apprumah.SharedPrefManager;
import com.tiara.apprumah.model.DataLogin;
import com.tiara.apprumah.model.ResponseLogin;
import com.tiara.apprumah.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    @BindView(R.id.login_email)
    EditText loginEmail;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.rg_user_adm_sign)
    RadioButton rgUserAdmSign;
    @BindView(R.id.rg_user_operator_sign)
    RadioButton rgUserOperatorSign;
    @BindView(R.id.sign_in)
    Button signIn;

    SharedPrefManager sharedPrefManager;
    String email, password, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sharedPrefManager = new SharedPrefManager(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = loginEmail.getText().toString();
                password = pass.getText().toString();

                if(rgUserAdmSign.isChecked()){
                    level = "1";
                } else if(rgUserOperatorSign.isChecked()){
                    level = "2";
                }

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(level)){
                    Toast.makeText(Login.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else{
                    cekLogin();
                }
            }
        });

        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(Login.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }

    private void cekLogin() {
        ConfigRetrofit.getInstance().login(email,password,level).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                String msg = response.body().getPesan();
                int status = response.body().getStatus();

                if(status == 1){
                    List<DataLogin> data = response.body().getData();
                    String id = data.get(0).getIdUser();

                    sharedPrefManager.setSP_SUDAH_LOGIN(true);
                    sharedPrefManager.setSP_IDUSER(id);

                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(Login.this, "Tidak Ada jaringan", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
