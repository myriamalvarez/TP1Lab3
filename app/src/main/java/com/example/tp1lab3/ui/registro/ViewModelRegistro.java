package com.example.tp1lab3.ui.registro;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp1lab3.model.Usuario;
import com.example.tp1lab3.request.ApiClient;
import com.example.tp1lab3.ui.login.MainActivity;

public class ViewModelRegistro extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuario;
    private ApiClient apiClient;

    public ViewModelRegistro(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = new ApiClient();
    }
    public LiveData<Usuario> getUsuario() {
        if (usuario == null) {
            usuario = new MutableLiveData<>();
        }
        return usuario;
    }


    public void guardarDatos(Long dni, String nombre, String apellido, String email, String contraseña) {
        Usuario usuario = new Usuario(dni, nombre, apellido, email, contraseña);
        apiClient.guardar(context, usuario);
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void cargarDatos(Boolean login) {
        if(login) {
            Usuario usuario = apiClient.leer(context);
            this.usuario.setValue(usuario);
        }
    }
}