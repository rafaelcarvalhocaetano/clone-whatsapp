package io.whatsapp.clonewhatsapp.configFireBase;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.FirebaseDatabase;/** * Created by rafaelcarvalho on 19/12/2017. */public final class ConfiguracaoFireBase{    private static DatabaseReference referenciaDataBase;    public static DatabaseReference getFirebase(){        if(referenciaDataBase == null){            referenciaDataBase = FirebaseDatabase.getInstance().getReference();        }        return referenciaDataBase;    }}