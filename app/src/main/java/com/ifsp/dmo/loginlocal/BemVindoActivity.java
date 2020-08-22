package com.ifsp.dmo.loginlocal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.ifsp.dmo.loginlocal.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BemVindoActivity extends AppCompatActivity {

    private TextView mensagemTextView;
    private String usuario;
    private String senha;
    private User mUser;
    private List<User> userList = null;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        mensagemTextView = findViewById(R.id.textview_mensagem);
        //Apresenta navigation up
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Recupera os dados enviados pela MainActivity
        Intent intent = getIntent();
        usuario = intent.getStringExtra(getString(R.string.key_usuario));
        senha = intent.getStringExtra(getString(R.string.key_senha));
        //Vamos anaisar se o usuário pode logar ou não
        mSharedPreferences =
                this.getSharedPreferences(getString(R.string.file_usuario), MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        recuperaUsuarios();
        validarUsuario();
    }

    @Override
    protected void onStart() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onStart()");
        super.onStart();
    }
    @Override
    protected void onRestart() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onRestart()");
        super.onRestart();
    }
    @Override
    protected void onResume() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onResume()");
        super.onResume();
    }
    @Override
    protected void onPause() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onPause()");
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onStop()");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onDestroy()")
        ;super.onDestroy();
    }

    private void validarUsuario(){
        boolean achou = false;
        if(userList != null) {
            for (User u : userList) {
                if (u.getUsername().equals(usuario)) {
                    if (u.getPassword().equals(senha)) {
                        mensagemTextView.setText(R.string.bem_vindo_msg);
                        achou = true;
                    }
                }
            }
        }
        if(!achou){
            mensagemTextView.setText(R.string.erro_login_msg);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void recuperaUsuarios(){
        String users =
                mSharedPreferences.getString(getString(R.string.key_usuarios_db), "");
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        userList = new ArrayList<>();
        try{
            jsonArray = new JSONArray(users);
            for(int i = 0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                mUser = new User(jsonObject.getString("username"),
                        jsonObject.getString("password"));
                userList.add(mUser);
            }
        } catch (JSONException ex){
            userList = null;
        }
        if (userList != null) {
            for (User u : userList) {
                Log.i(getString(R.string.tag), u.toString());
            }
        }
    }
}
