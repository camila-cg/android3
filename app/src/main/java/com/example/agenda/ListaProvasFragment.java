package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agenda.modelo.Prova;

import java.util.Arrays;
import java.util.List;

public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    /**
     * TODO: ENTENDER A RAZÃO DE CADA PARÂMETRO
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        List<String> topicosPort = Arrays.asList("Sujeito", "Objeto direto", "Objeto indireto");
        List<String> topicosMat = Arrays.asList("Geometria", "Equações", "Desenho");

        Prova portugues = new Prova("Portugues", "08/04", topicosPort);
        Prova matematica = new Prova("Matemática", "10/04", topicosMat);
        List<Prova> provas = Arrays.asList(portugues, matematica);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getContext(), android.R.layout.simple_list_item_1, provas);
        ListView lista = view.findViewById(R.id.provas_lista);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) parent.getItemAtPosition(position);
                Toast.makeText(getContext(), "Clicou na prova " + prova ,  Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DetalhesProvaActivity.class);
                intent.putExtra("prova", prova);

                startActivity(intent);
            }
        });


        return view;
    }
}
