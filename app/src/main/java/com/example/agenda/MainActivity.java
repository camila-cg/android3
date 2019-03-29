package com.example.agenda;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        listaPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pessoa pessoa = (Pessoa) listaPessoas.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,"Pessoa " + pessoa.getNome(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("pessoa", pessoa); // Só é possível enviar objetos quando a classe de domínio é serializável.
                startActivity(intent);

            }
        });

/*        listaPessoas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> lista, View item, int position, long id) {
                Toast.makeText(MainActivity.this,"Clique longo " , Toast.LENGTH_SHORT).show();
                return false; //retornando true o tratamento do clique longo termina neste método. Caso seja retornado false, o evento fica disponível para outros métodos.
            }

        });*/

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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Pessoa pessoa = (Pessoa) listaPessoas.getItemAtPosition(info.position);

        MenuItem itemSMS = menu.add("Enviar SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:" + pessoa.getTelefone()));
        itemSMS.setIntent(intentSMS);

        //TODO: VERIFICAR PQ N ESTÁ FUNCIONANDO
        MenuItem itemMapa = menu.add("Visualizar no Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0 q=" + pessoa.getEndereco()));
        itemMapa.setIntent(intentMapa);


        MenuItem itemSite = menu.add("Visitar site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);

        String site = pessoa.getSite();
        if(!site.startsWith("https://")){
            site = "http://" + site;
        }

        intentSite.setData(Uri.parse(site));
        itemSite.setIntent(intentSite);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) { //Atenção: O item do menu é o deletar, e não o objeto da lista
                PessoaDAO dao = new PessoaDAO(MainActivity.this);
                dao.remover(pessoa);
                dao.close();

                carregarLista();
                return false;
            }
        });//IDE n traz este ponto e vírgula na geração automática de código


/*        itemSite.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(intentSite);
                return false;
            }
        });*/

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
        List<Pessoa> pessoas = dao.pesquisar();
        dao.close();

        ArrayAdapter<Pessoa> adapter = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, pessoas);
        listaPessoas.setAdapter(adapter);
    }
}
