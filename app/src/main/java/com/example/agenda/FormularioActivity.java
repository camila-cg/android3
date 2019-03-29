package com.example.agenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.modelo.Pessoa;

public class FormularioActivity extends AppCompatActivity {
    private FormularioHelper helper;

    //TODO: Salvar dados preenchidos independente de mudança de orientação da tela.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);

    }

    //Colocando o botão de salvar formulário na action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Colocando ação no botão do menu da action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Restringindo o finish() apenas quando a opção do menu clicado for para salvar formulário
        switch (item.getItemId()){
            case R.id.menu_formulario:
                Pessoa pessoa = helper.obterPessoa();
                PessoaDAO dao = new PessoaDAO(this);
                dao.cadastraPessoa(pessoa);
                dao.close();
                Toast.makeText(FormularioActivity.this,"Botão" + pessoa.getNome() + "clicado!", Toast.LENGTH_SHORT).show();

                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
