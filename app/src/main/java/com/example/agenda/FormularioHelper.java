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

    private Pessoa pessoa;

    public FormularioHelper(FormularioActivity activity) {
        etNome = activity.findViewById(R.id.etNome);
        etEndereco = activity.findViewById(R.id.etEndereco);
        etSite = activity.findViewById(R.id.etSite);
        etTelefone = activity.findViewById(R.id.etTelefone);
        rbNota = activity.findViewById(R.id.rbNota);
        pessoa = new Pessoa();

    }

    public Pessoa obterPessoa() {
        pessoa.setNome(etNome.getText().toString());
        pessoa.setEndereco(etEndereco.getText().toString());
        pessoa.setTelefone(etTelefone.getText().toString());
        pessoa.setSite(etSite.getText().toString());
        pessoa.setNota(Double.valueOf(rbNota.getProgress()));

        return pessoa;
    }

    public void preencheFormulario(Pessoa pessoa) {
        etNome.setText(pessoa.getNome());
        etEndereco.setText(pessoa.getEndereco());
        etSite.setText(pessoa.getSite());
        etTelefone.setText(pessoa.getTelefone());
        rbNota.setProgress(pessoa.getNota().intValue());
        this.pessoa = pessoa; //guardando o id
    }
}
