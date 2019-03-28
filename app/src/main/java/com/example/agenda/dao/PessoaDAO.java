package com.example.agenda.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PessoaDAO extends SQLiteOpenHelper {

    /**
     * Construtor obrigatório
     * @param context contexto da conexão //TODO: MELHORAR DESCRIÇÃO
     */
    public PessoaDAO( Context context) {
        //TODO: PESQUISAR O PAPEL DA FACTORY NA CONEXÃO COM BANCO
        //TODO: PESQUISAR O PAPEL DA VERSÃO NA CONEXÃO COM BANCO
        super(context, "AgendaDB", null, 1);
    }

    /**
     * Método chamado quando o banco de dados é criado.
     * Após a criação criamos a tabela que será usada para persistência do objeto Pessoa.
     * @param db instância do banco de dados criado.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Pessoa (id INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "endereco TEXT," +
                "site TEXT," +
                "nota REAL);";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: FAZER VERIFICAÇÃO DE ATUALIZAÇÃO DO BANCO --> MÉTODO ABAIXO É APENAS UM PALIATIVO!!!
        String sql = "DROP TABLE IF EXISTS Pessoa;";
        db.execSQL(sql);
        onCreate(db);
    }


}
