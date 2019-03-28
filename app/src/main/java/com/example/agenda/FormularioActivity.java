package com.example.agenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    //TODO: Salvar dados preenchidos independente de mudança de orientação da tela.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

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
                Toast.makeText(FormularioActivity.this,"Botão clicado!", Toast.LENGTH_SHORT).show();
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
