package com.example.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.agenda.dao.PessoaDAO;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String formato = (String) intent.getSerializableExtra("format");

        //TODO: OLHAR COMPATIBILIDADE COM API < 23
        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        String telefone = sms.getDisplayOriginatingAddress();
        PessoaDAO dao = new PessoaDAO(context);
        if(dao.estaNaAgenda(telefone)){
            Toast.makeText(context, "Recebi sms!",Toast.LENGTH_SHORT).show();
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.msg);
            mediaPlayer.start();

        }

    }
}
