package com.example.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.modelo.Pessoa;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PessoaDAO dao = new PessoaDAO(this);
        List<Pessoa> pessoas = dao.pesquisarPessoas();
        dao.close();

        ListView listaPessoas = findViewById(R.id.lista_pessoas); //TODO: Adicionar ButterKnife
        ArrayAdapter<Pessoa> adapter = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, pessoas);
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
