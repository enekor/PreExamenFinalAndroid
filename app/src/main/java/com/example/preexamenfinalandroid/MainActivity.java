package com.example.preexamenfinalandroid;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.preexamenfinalandroid.home.HomeActivity;
import com.example.preexamenfinalandroid.home.database.database.BaseDeDatos;
import com.example.preexamenfinalandroid.home.model.Intercambio;
import com.example.preexamenfinalandroid.home.model.Owner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private EditText nombre, password;
    Owner owner = new Owner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createUserIfNecesary();
        Toast.makeText(this, ""+BaseDeDatos.getInstance(this).ownerRepository().getAll().size(), Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        nombre = findViewById(R.id.nombreLogin);
        password = findViewById(R.id.passLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkFields()){
                    if(checkData()){
                        openNewActivity(HomeActivity.class);
                    }else{
                        Toast.makeText(MainActivity.this, "Las credenciales no son correctas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void openNewActivity(Class clase){
        Intent intent = new Intent(this, clase);
        intent.putExtra("usuario",nombre.getText().toString());
        startActivity(intent);
    }

    private void createUserIfNecesary(){
        if(BaseDeDatos.getInstance(this).ownerRepository().getAll().isEmpty()){
            Owner owner = new Owner();
            owner.setName("admin");
            owner.setPassword("admin");
            BaseDeDatos.getInstance(this).ownerRepository().insert(owner);
        }
    }

    private boolean checkData(){
        boolean verificado = false;
        List<Owner> users = BaseDeDatos.getInstance(this).ownerRepository().getAll();

        for(Owner u:users){
            if(nombre.getText().toString().equals(u.getName()) && password.getText().toString().equals(u.getPassword())){
                verificado = true;
                Intercambio.getInstance().setUsuario(nombre.getText().toString());
            }
        }
        return verificado;
    }

    private boolean checkFields(){
        if(nombre.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "No se han metido los datos necesarios", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
}