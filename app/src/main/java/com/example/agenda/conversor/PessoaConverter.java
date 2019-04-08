package com.example.agenda.conversor;

import com.example.agenda.modelo.Pessoa;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

public class PessoaConverter {
    public String converteParaJSON(List<Pessoa> pessoas) {
        JSONStringer jsonStringer = new JSONStringer();

        try {
            jsonStringer.object().key("list").array().object().key("aluno").array();
            for (Pessoa aluno : pessoas) {
                jsonStringer.object();
                jsonStringer.key("nome").value(aluno.getNome());
                jsonStringer.key("nota").value(aluno.getNota());
                jsonStringer.endObject();
            }
            jsonStringer.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonStringer.toString();
    }
}
