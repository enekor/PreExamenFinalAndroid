package com.example.preexamenfinalandroid;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.preexamenfinalandroid.home.HomeActivity;
import com.example.preexamenfinalandroid.home.model.Intercambio;
import com.example.preexamenfinalandroid.home.model.Owner;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private EditText nombre, password;
    Owner owner = new Owner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        nombre = findViewById(R.id.nombreLogin);
        password = findViewById(R.id.passLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intercambio.getInstance().setUsuario(nombre.getText().toString());
                Log.i("usuario",""+Intercambio.getInstance().getUsuario());
                openNewActivity(HomeActivity.class);
            }
        });
    }

    private void openNewActivity(Class clase){
        Intent intent = new Intent(this, clase);
        intent.putExtra("usuario",nombre.getText().toString());
        startActivity(intent);
    }
}