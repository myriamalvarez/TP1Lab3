package com.example.tp1lab3.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tp1lab3.R;
import com.example.tp1lab3.model.Usuario;
import com.example.tp1lab3.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private Button iniciar, registrar;
    private EditText mail, contra;
    private TextView mensaje;
    private ViewModelMain vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm = new ViewModelProvider(this).get(ViewModelMain.class);
        inicializar();
        vm.getMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mensaje.setText(s);
            }
        });
    }

    public void inicializar(){
        iniciar = findViewById(R.id.btInicio);
        registrar = findViewById(R.id.btRegistro);
        mail = findViewById(R.id.etUsuario);
        contra = findViewById(R.id.etClave);
        mensaje = findViewById(R.id.TVMensajeMain);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.iniciarSesion(mail.getText().toString(),contra.getText().toString());
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.registrar();
            }
        });
    }
}

