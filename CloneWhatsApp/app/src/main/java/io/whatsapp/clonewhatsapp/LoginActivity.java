package io.whatsapp.clonewhatsapp;import android.os.Bundle;import android.support.design.widget.FloatingActionButton;import android.support.design.widget.Snackbar;import android.support.v7.app.AppCompatActivity;import android.support.v7.widget.Toolbar;import android.view.View;import android.widget.EditText;import com.github.rtoshiro.util.format.SimpleMaskFormatter;import com.github.rtoshiro.util.format.text.MaskTextWatcher;public class LoginActivity extends AppCompatActivity {    private EditText nome, tel, codigoPais, codigoArea;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_login);        nome = (EditText) findViewById(R.id.edit_nome);        tel = (EditText) findViewById(R.id.edit_telefone);        codigoPais = (EditText) findViewById(R.id.edit_cod_pais);        codigoArea = (EditText) findViewById(R.id.edit_cod_area);        //formatando        SimpleMaskFormatter telefone = new SimpleMaskFormatter("NNNNN-NNNN");        SimpleMaskFormatter area = new SimpleMaskFormatter("(NN)");        SimpleMaskFormatter pais = new SimpleMaskFormatter("+NN");        MaskTextWatcher maskTelefone = new MaskTextWatcher(tel, telefone);        MaskTextWatcher maskArea = new MaskTextWatcher(codigoArea, area);        MaskTextWatcher maskPais = new MaskTextWatcher(codigoPais, pais);        tel.addTextChangedListener(maskTelefone);        codigoArea.addTextChangedListener(maskArea);        codigoPais.addTextChangedListener(maskPais);    }}