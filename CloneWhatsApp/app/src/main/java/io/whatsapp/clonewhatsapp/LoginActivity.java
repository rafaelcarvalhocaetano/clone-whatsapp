package io.whatsapp.clonewhatsapp;import android.content.Intent;import android.os.Bundle;import android.support.annotation.Nullable;import android.support.v7.app.AppCompatActivity;import android.view.View;import android.widget.TextView;import com.google.firebase.database.DatabaseReference;import io.whatsapp.clonewhatsapp.configFireBase.ConfiguracaoFireBase;public class LoginActivity extends AppCompatActivity {    private DatabaseReference referencia;    private TextView cadastro;    @Override    protected void onCreate(@Nullable Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_login);        cadastro = (TextView) findViewById(R.id.text_cadastro);        referencia = ConfiguracaoFireBase.getFirebase();        referencia.child("pontos").setValue("200");        cadastro.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);                startActivity(intent);            }        });    }    /*    private EditText nome, tel, codigoPais, codigoArea;    private Button btn;    private String [] permissoesNecessarias = new String[]{            Manifest.permission.SEND_SMS    };    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_login);        nome = (EditText) findViewById(R.id.edit_nome);        tel = (EditText) findViewById(R.id.edit_telefone);        codigoPais = (EditText) findViewById(R.id.edit_cod_pais);        codigoArea = (EditText) findViewById(R.id.edit_cod_area);        btn = (Button) findViewById(R.id.btn_cadastrar);        Permissao.validaPermissao(1, this, permissoesNecessarias );        //formatando        SimpleMaskFormatter telefone = new SimpleMaskFormatter("NNNNN-NNNN");        SimpleMaskFormatter area = new SimpleMaskFormatter("NN");        SimpleMaskFormatter pais = new SimpleMaskFormatter("+NN");        MaskTextWatcher maskTelefone = new MaskTextWatcher(tel, telefone);        MaskTextWatcher maskArea = new MaskTextWatcher(codigoArea, area);        MaskTextWatcher maskPais = new MaskTextWatcher(codigoPais, pais);        tel.addTextChangedListener(maskTelefone);        codigoArea.addTextChangedListener(maskArea);        codigoPais.addTextChangedListener(maskPais);        btn.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                String nomeUsuario = nome.getText().toString();                String telefoneUsuario =                        codigoPais.getText().toString() +                        codigoArea.getText().toString() +                        tel.getText().toString();                String telFormatado = telefoneUsuario.replace("+", "");                telFormatado =  telFormatado.replace("-","");                //Log.i("TEL","t:" +telFormatado);                Random gerandoSMS = new Random();                int sms = gerandoSMS.nextInt( 9999 - 1000 ) + 1000;                String token = String.valueOf(sms);                //Log.i("token", "t"+ token);                Preferencias preferencias = new Preferencias(LoginActivity.this);                preferencias.salvarUsuario(nomeUsuario, telFormatado, token);                HashMap<String, String> user = preferencias.getDadosUsuario();                //Log.i("TOKEN", "t:"+user.get("token")+"f"+user.get("telefone")+"n"+user.get("nome"));                //envio do SMS via email                telefoneUsuario = "8135";                boolean enviadoSMS;                String mensagemEnvio = "Código de confirmação: "+token;                enviadoSMS = enviaSMS("+"+telFormatado,mensagemEnvio);                if(enviadoSMS){                    Intent i = new Intent(LoginActivity.this, ValidadorActivity.class);                    startActivity(i);                    finish();                }else{                    Toast.makeText(LoginActivity.this, "Problema o enviar o SMS", Toast.LENGTH_LONG).show();                }            }        });    }    private boolean enviaSMS(String telefone, String mensagem){        try {            SmsManager smsManager = SmsManager.getDefault();            smsManager.sendTextMessage(telefone, null, mensagem, null, null);            return true;        }catch (Exception e){            return false;        }    }    public void onRequestPermissionResult(int requestCode, String[]permission, int [] grantResults){        super.onRequestPermissionsResult(requestCode, permission, grantResults);        for (int resultado : grantResults){            if(resultado == PackageManager.PERMISSION_DENIED){                alerta();            }        }    }    public void alerta(){        AlertDialog.Builder adb = new AlertDialog.Builder(this);        adb.setTitle("Permissões negada");        adb.setMessage("Para utilizar este App é necessário aceitar as permissões");        adb.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {            @Override            public void onClick(DialogInterface dialogInterface, int i) {                finish();            }        });        AlertDialog alert = adb.create();        alert.show();    }    */}