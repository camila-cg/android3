package com.example.agenda.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.modelo.Pessoa;

import java.util.List;

public class PessoaAdapter extends BaseAdapter {

    private final List<Pessoa> pessoas;
    private final Context contexto;

    public PessoaAdapter(Context contexto, List<Pessoa> pessoas) {
        this.contexto = contexto;
        this.pessoas = pessoas;
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pessoas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(contexto);
        Pessoa pessoa = pessoas.get(position);
        view.setText(pessoa.toString());
        return view;
    }
}
