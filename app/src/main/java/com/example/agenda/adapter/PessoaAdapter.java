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

    /**
     *
     * @param position posição da view.
     * @param convertView
     * @param parent indica qual é o componente "pai" da nossa view.
     * @return
     */
    //TODO: javadoc
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pessoa pessoa = pessoas.get(position);

        View view = convertView;
        LayoutInflater inflater = LayoutInflater.from(contexto);

        if(view == null){
            //TODO: VERSÃO ESTÁ DESATUALIZADA. Se eu altero a altura do layout do list_item, mesmo passando o  parent ao invés de null ele não renderiza esta alteração.
            view = inflater.inflate(R.layout.list_item, null);
            //view = inflater.inflate(R.layout.list_item, null, false);
        }

        /**
         * View view = inflater.inflate(R.layout.list_item, parent);
         * Não podemos passar o parent coomo segundo parâmetro., pq o inflate tentará inflar o xml e contruir o item, em seguida, irá criar a view e adicioná-la no parent.
         * O problema estará quando devolvermos a view. Como a lista pediu para colocarmos a view dentro dela, ela pegará o resultado e tentará reinseri-la novamente.
         * Quando tentamos colocar dois elementos exatamente iguais dentro de um componente do Android, iremos receber uma Exception.
         *
         * View view = inflater.inflate(R.layout.list_item, parent, false);
         * Usaremos o parent como referência, mas não iremos colocar a view na lista. Então, só quando retornarmos a view ela será inserida na lista.
         */


        TextView tvNome = view.findViewById(R.id.tvNome);
        tvNome.setText(pessoa.getNome());

        TextView tvTel = view.findViewById(R.id.tvTel);
        tvTel.setText(pessoa.getTelefone());

        TextView tvEndereco = view.findViewById(R.id.tvEndereco);
        if(tvEndereco!= null){ //validar pq qdo o app é iniciado a orientação é retrato e só teoms este campo na orientação paisagem.
            tvEndereco.setText(pessoa.getEndereco());
        }

        TextView tvSite = view.findViewById(R.id.tvSite);
        if(tvSite != null){ //validar pq qdo o app é iniciado a orientação é retrato e só teoms este campo na orientação paisagem.
            tvSite.setText(pessoa.getSite());
        }

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
