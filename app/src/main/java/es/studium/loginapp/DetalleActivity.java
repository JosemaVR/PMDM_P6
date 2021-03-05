package es.studium.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleActivity extends AppCompatActivity {

    TextView bienvenida;
    Button btnBorrar;
    SharedPreferences sharedPreferences;
    public static final String MyPreferences = "MyPrefs";
    public static final String usuarioKey = "usuarioKey";
    public static final String claveKey = "claveKey";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        bienvenida = findViewById(R.id.lblBienvenida);
        btnBorrar = findViewById(R.id.btnBorrar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String user = bundle.getString("user");
        String pass = bundle.getString("pass");
        Boolean btn = bundle.getBoolean("btn");
        if(btn==false){
            btnBorrar.setText(R.string.volver);
        }

        bienvenida.setText("Bienvenido "+ user + " (clave = " + pass +").");

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("LoginCredentials", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
            }
        });
    }
}