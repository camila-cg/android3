package com.example.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.agenda.conversor.PessoaConverter;
import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.modelo.Pessoa;

import java.util.List;

//Parâmetros do generics: <Object, Object, Object>
// 1º: Indica quais são os parâmetros que estamos passando para o doInBackground através do execute().
// 2º: É usado quando precisamos fazer atualizações no meio da tarefa, precisaria implementar métodos adicionais.
// 3º: Indica o tipo do objeto de saída da tarefa secundária.

public class EnviaPessoaTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog dialog;

    public EnviaPessoaTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... objects) {
        PessoaDAO dao = new PessoaDAO(context);
        List<Pessoa> pessoas = dao.pesquisar();
        dao.close();

        PessoaConverter conversor = new PessoaConverter();
        String json = conversor.converteParaJSON(pessoas);
        WebClient client = new WebClient();
        String resposta = client.post(json);

        return resposta;
    }

    /**
     * Método executado na thread principal após a execução da thread secundária.
     * @param resposta objeto resposta retornado pela thread secundária.
     */
    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();
    }

    /**
     * Método executado antes de começar a execução da thread secundária.
     */
    @Override
    protected void onPreExecute() {
        //ProgressDialog está deprecated -->  TODO: Substituir pela classe ProgressBar.
        dialog = ProgressDialog.show(context, "Aguarde", "Enviando pessoas ...",true,true );
    }
}
