package io.whatsapp.clonewhatsapp.model;import com.google.firebase.auth.FirebaseUser;import com.google.firebase.database.DatabaseReference;import io.whatsapp.clonewhatsapp.configFireBase.ConfiguracaoFireBase;/** * Created by rafaelcarvalho on 19/12/2017. */public class User {    private String id;    private String nome;    private String email;    private String senha;    public User(){    }    public String getId() {        return id;    }    public void setId(String id) {        this.id = id;    }    public String getNome() {        return nome;    }    public void setNome(String nome) {        this.nome = nome;    }    public String getEmail() {        return email;    }    public void setEmail(String email) {        this.email = email;    }    public String getSenha() {        return senha;    }    public void setSenha(String senha) {        this.senha = senha;    }    public void salvar(){        DatabaseReference db = ConfiguracaoFireBase.getFirebase();        db.child("usuarios").child( getId() ).setValue(this);    }}