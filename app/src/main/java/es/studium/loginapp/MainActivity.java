package es.studium.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usuario, clave;
    Button acceder;
    Switch guardar;
    TextView lblAviso;

    String user, pass;

    public static final String MyPreferences = "MyPrefs";
    public static final String usuarioKey = "usuarioKey";
    public static final String claveKey = "claveKey";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        if(sharedPreferences.getAll().size()!=0){
            user = sharedPreferences.getString(usuarioKey, "");
            pass = sharedPreferences.getString(claveKey, "");
            entrarEnDetalle(user, pass);
        } else {
            setContentView(R.layout.activity_main);

            usuario = findViewById(R.id.editUser);
            clave = findViewById(R.id.editPass);
            guardar = findViewById(R.id.switchGuardar);
            acceder = findViewById(R.id.btnAcceder);
            lblAviso = findViewById(R.id.lblAviso);
            lblAviso.setText("");

            if(sharedPreferences.getAll().size()!=0){
                user = sharedPreferences.getString(usuarioKey, "");
                pass = sharedPreferences.getString(claveKey, "");
                entrarEnDetalle(user, pass);
            }

            acceder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user = usuario.getText().toString();
                    pass = clave.getText().toString();
                    if(usuario.getText().length()!=0 && clave.getText().length()!=0){
                        if(guardar.isChecked()){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(usuarioKey, user);
                            editor.putString(claveKey, pass);
                            editor.commit();
                        }
                        entrarEnDetalle(user, pass);
                    } else {
                        lblAviso.setText("Deben rellenarse los dos campos para acceder.");
                    }
                }
            });
        }
    }

    public void entrarEnDetalle(String user, String pass){
        Intent detalleUsuario = new Intent(this, DetalleActivity.class);
        detalleUsuario.putExtra("user", user);
        detalleUsuario.putExtra("pass", pass);
        startActivity(detalleUsuario);
    }
}