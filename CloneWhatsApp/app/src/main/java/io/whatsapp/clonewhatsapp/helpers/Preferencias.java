package io.whatsapp.clonewhatsapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferencias {

    private Context contexto;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "whatsapp.preferencias";
    private final int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_UID = "identificadorUsuarioLogado";

    public Preferencias( Context contextoParametro){

        contexto = contextoParametro;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO, MODE );
        editor = preferences.edit();

    }

    public void salvarDados( String uidUser){

        editor.putString(CHAVE_UID, uidUser);

        editor.commit();

    }



}
