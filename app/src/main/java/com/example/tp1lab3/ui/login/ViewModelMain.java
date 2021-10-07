package com.example.tp1lab3.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp1lab3.model.Usuario;
import com.example.tp1lab3.request.ApiClient;
import com.example.tp1lab3.ui.registro.RegistroActivity;

public class ViewModelMain extends AndroidViewModel {
    private Context context;
    private ApiClient apiClient;
    private MutableLiveData<String> mensaje;


    public MutableLiveData<String> getMensaje() {
        if(mensaje == null){
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }

    public ViewModelMain(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        apiClient = new ApiClient();
    }

    public void iniciarSesion(String mail, String contraseña){
        Usuario u = ApiClient.login(context, mail, contraseña);
        if(u != null){
            Intent intent = new Intent(context, RegistroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("login", true);
            context.startActivity(intent);
        }else {
            mensaje.setValue("Usuario y/o mail incorrectos");
        }
    }

    public void registrar(){
        Intent intent = new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("login", false);
        context.startActivity(intent);
    }
}
