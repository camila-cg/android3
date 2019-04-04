package com.example.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agenda.R;
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
        Pessoa pessoa = pessoas.get(position);

        LayoutInflater inflater = LayoutInflater.from(contexto);
        View view = inflater.inflate(R.layout.list_item, null);

        TextView tvNome = view.findViewById(R.id.tvNome);
        tvNome.setText(pessoa.getNome());

        TextView tvTel = view.findViewById(R.id.tvTel);
        tvTel.setText(pessoa.getTelefone());

        ImageView ivFoto = view.findViewById(R.id.ivFoto);
        String caminhoFoto = pessoa.getCaminhoFoto();

        if(caminhoFoto != null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            ivFoto.setImageBitmap(bitmapReduzido);
            ivFoto.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }

        return view;
    }
}
