package com.example.agenda.conversor;

import com.example.agenda.modelo.Pessoa;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;


//Se usarmos o método GET, todos os parâmetros terão que ir com a url do servidor, seguido pelos parâmetro chave e valor.
//Isto tem um certo limite, a partir de um certo tamanho (2KB, por exemplo) não será enviado o excedente.
// Para este tipo de caso, temos o método POST, no qual enviamos os dados no corpo da requisição.

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
