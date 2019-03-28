package com.example.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import com.example.agenda.modelo.Pessoa;

public class FormularioHelper {

    private final EditText etNome;
    private final EditText etEndereco;
    private final EditText etSite;
    private final EditText etTelefone;
    private final RatingBar rbNota;

    public FormularioHelper(FormularioActivity activity) {
        etNome = activity.findViewById(R.id.etNome);
        etEndereco = activity.findViewById(R.id.etEndereco);
        etSite = activity.findViewById(R.id.etSite);
        etTelefone = activity.findViewById(R.id.etTelefone);
        rbNota = activity.findViewById(R.id.rbNota);
    }

    public Pessoa obterPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(etNome.getText().toString());
        pessoa.setEndereco(etEndereco.getText().toString());
        pessoa.setTelefone(etTelefone.getText().toString());
        pessoa.setSite(etSite.getText().toString());
        pessoa.setNota(Double.valueOf(rbNota.getProgress()));

        return pessoa;
    }
}
