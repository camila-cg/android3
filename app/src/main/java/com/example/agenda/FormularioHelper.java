package com.example.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.agenda.modelo.Pessoa;

public class FormularioHelper {

    private final EditText etNome;
    private final EditText etEndereco;
    private final EditText etSite;
    private final EditText etTelefone;
    private final RatingBar rbNota;
    private final ImageView ivFoto;

    private Pessoa pessoa;

    public FormularioHelper(FormularioActivity activity) {
        etNome = activity.findViewById(R.id.etNome);
        etEndereco = activity.findViewById(R.id.etEndereco);
        etSite = activity.findViewById(R.id.etSite);
        etTelefone = activity.findViewById(R.id.etTelefone);
        rbNota = activity.findViewById(R.id.rbNota);
        ivFoto  = activity.findViewById(R.id.iv_foto);
        pessoa = new Pessoa();

    }

    public Pessoa obterPessoa() {
        pessoa.setNome(etNome.getText().toString());
        pessoa.setEndereco(etEndereco.getText().toString());
        pessoa.setTelefone(etTelefone.getText().toString());
        pessoa.setSite(etSite.getText().toString());
        pessoa.setNota(Double.valueOf(rbNota.getProgress()));
        pessoa.setCaminhoFoto((String) ivFoto.getTag());

        return pessoa;
    }

    public void preencheFormulario(Pessoa pessoa) {
        etNome.setText(pessoa.getNome());
        etEndereco.setText(pessoa.getEndereco());
        etSite.setText(pessoa.getSite());
        etTelefone.setText(pessoa.getTelefone());
        rbNota.setProgress(pessoa.getNota().intValue());
        carregarImagem(pessoa.getCaminhoFoto());
        this.pessoa = pessoa; //guardando o id
    }

    public void carregarImagem(String caminhoFoto) {

        if(caminhoFoto != null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            ivFoto.setImageBitmap(bitmapReduzido);

            //TODO: Melhorar exibição de foto, está ficando "esticada"
            ivFoto.setScaleType(ImageView.ScaleType.CENTER_CROP);

            ivFoto.setTag(caminhoFoto); //TODO: EXPLICAR NO JAVADOC

        }

    }
}
