package com.example.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] pessoas = {"01", "02", "06", "01", "02", "06", "01", "02", "06", "01", "02", "06"};
        ListView listaPessoas = findViewById(R.id.lista_pessoas); //TODO: Adicionar ButterKnife
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoas);
        listaPessoas.setAdapter(adapter);

        Button btCadastrar = findViewById(R.id.btAdicionarCadastro);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class );
                startActivity(intent);
            }
        });

    }
}
