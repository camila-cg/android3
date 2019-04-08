package com.example.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agenda.modelo.Prova;

import java.util.Arrays;
import java.util.List;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        List<String> topicosPort = Arrays.asList("Sujeito", "Objeto direto", "Objeto indireto");
        List<String> topicosMat = Arrays.asList("Geometria", "Equações", "Desenho");

        Prova portugues = new Prova("Portugues", "08/04", topicosPort);
        Prova matematica = new Prova("Matemática", "10/04", topicosMat);
        List<Prova> provas = Arrays.asList(portugues, matematica);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(this, android.R.layout.simple_list_item_1, provas);
        ListView lista = findViewById(R.id.provas_lista);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) parent.getItemAtPosition(position);
                Toast.makeText(ProvasActivity.this, "Clicou na prova " + prova ,  Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProvasActivity.this, DetalhesProvaActivity.class);
                intent.putExtra("prova", prova);

                startActivity(intent);
            }
        });
    }
}
