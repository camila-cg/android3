package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.modelo.Pessoa;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listaPessoas;

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaPessoas = findViewById(R.id.lista_pessoas); //TODO: Adicionar ButterKnife

        Button btCadastrar = findViewById(R.id.btAdicionarCadastro);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class );
                startActivity(intent);
            }
        });

        //Registrando o componente de tela que possuirá um menu de contexto
        registerForContextMenu(listaPessoas);

    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) { //Atenção: O item do menu é o deletar, e não o objeto da lista

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Pessoa pessoa = (Pessoa) listaPessoas.getItemAtPosition(info.position);

                PessoaDAO dao = new PessoaDAO(MainActivity.this);
                dao.removerPessoa(pessoa);
                dao.close();

                carregarLista();
                return false;
            }
        });//IDE n traz este ponto e vírgula na geração automática de código
    }

    /*
    Poderia ser utilizado se a opção "Deletar" fosse colocada no xml, mas como esta opção está sendo criada no código, não temos um id para este componente,
    logo não podemos identificar quando esta opção foi clicada para inserir um comportamento para o evento de clique,
    portanto não usaremos este método e veremos como tratar casos como esse, quando as opções são geradas via código.
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }
    */

    private void carregarLista() {
        PessoaDAO dao = new PessoaDAO(this);
        List<Pessoa> pessoas = dao.pesquisarPessoas();
        dao.close();

        ArrayAdapter<Pessoa> adapter = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, pessoas);
        listaPessoas.setAdapter(adapter);
    }
}
