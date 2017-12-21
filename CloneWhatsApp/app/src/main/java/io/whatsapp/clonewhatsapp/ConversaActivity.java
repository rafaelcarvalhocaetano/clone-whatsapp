package io.whatsapp.clonewhatsapp;import android.os.Bundle;import android.support.v7.app.AppCompatActivity;import android.support.v7.widget.Toolbar;import android.view.View;import android.widget.ArrayAdapter;import android.widget.EditText;import android.widget.ImageButton;import android.widget.ListView;import android.widget.Toast;import com.google.firebase.database.DataSnapshot;import com.google.firebase.database.DatabaseError;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.ValueEventListener;import java.util.ArrayList;import io.whatsapp.clonewhatsapp.adapter.MensagemAdapter;import io.whatsapp.clonewhatsapp.base64.Codificacao;import io.whatsapp.clonewhatsapp.firebase.ConfigFireBase;import io.whatsapp.clonewhatsapp.helpers.Preferencias;import io.whatsapp.clonewhatsapp.model.Mensagem;public class ConversaActivity extends AppCompatActivity {    private Toolbar tb;    private String nomeUsuarioDestinatario;    private ImageButton btn_img;    private EditText editMensagem;    //dados remetente    private String uidUsuarioRemetente;    private String uidUsuarioDestinatario;    private ArrayList<Mensagem> mensagens;    private ArrayAdapter<Mensagem> adapter;    private ListView listView;    private ValueEventListener valueEventListenerMensagem;    private DatabaseReference firebase;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_conversa);        tb = (Toolbar) findViewById(R.id.tb_conversa);        btn_img = (ImageButton) findViewById(R.id.bt_enviar);        editMensagem = (EditText) findViewById(R.id.edit_mensagem);        listView = (ListView) findViewById(R.id.lv_conversas);        //recebendo dados de outra activity        Bundle extra = getIntent().getExtras();        //recupendo dados do remetente        Preferencias p = new Preferencias(ConversaActivity.this);        uidUsuarioRemetente = p.getIdentificador();        if( extra != null ){            nomeUsuarioDestinatario = extra.getString("nome");            String emailDestinatario = extra.getString("email");            uidUsuarioDestinatario = Codificacao.codificacaoBase64( emailDestinatario );        }        //title        tb.setTitle( nomeUsuarioDestinatario );        tb.setNavigationIcon(R.drawable.ic_action_arrow_left);        setSupportActionBar( tb );        //Monta listView e adapter        mensagens = new ArrayList<>();        adapter = new MensagemAdapter(ConversaActivity.this, mensagens);        /*        adapter = new ArrayAdapter(                ConversaActivity.this,                android.R.layout.simple_list_item_1,                mensagens        );        */        listView.setAdapter( adapter );        //recuperar as mensagens do firebase        firebase = ConfigFireBase.getFirebase()                    .child("mensagens")                    .child( uidUsuarioRemetente )                    .child( uidUsuarioDestinatario );        //cria listener de mensagem        valueEventListenerMensagem = new ValueEventListener() {            @Override            public void onDataChange(DataSnapshot dataSnapshot) {                //limpando as mensagens                mensagens.clear();                for ( DataSnapshot dados :dataSnapshot.getChildren() ){                    Mensagem m = dados.getValue( Mensagem.class );                    mensagens.add( m );                }                adapter.notifyDataSetChanged();            }            @Override            public void onCancelled(DatabaseError databaseError) {            }        };        firebase.addValueEventListener( valueEventListenerMensagem );        //enviando mensagem        btn_img.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                String msn = editMensagem.getText().toString();                if(msn.isEmpty()){                    Toast.makeText(ConversaActivity.this, "DIGITE UMA MENSAGEM", Toast.LENGTH_LONG).show();                }else{                    Mensagem m = new Mensagem();                    m.setUidUsuario( uidUsuarioRemetente );                    m.setMensagem( msn );                    //user user destinatário                    salvarMensagem(uidUsuarioRemetente, uidUsuarioDestinatario, m);                    //user remetente                    salvarMensagem(uidUsuarioDestinatario, uidUsuarioRemetente, m);                    editMensagem.setText("");                }            }        });    }    private boolean salvarMensagem(String idRemetente, String idDestinatario, Mensagem m){        try {            firebase = ConfigFireBase.getFirebase().child("mensagens");            firebase.child( idRemetente )                    .child( idDestinatario )                    .push()                    .setValue( m );            return true;        }catch (Exception e){            e.printStackTrace();            return false;        }    }    @Override    protected void onStop() {        super.onStop();        firebase.removeEventListener( valueEventListenerMensagem );    }}