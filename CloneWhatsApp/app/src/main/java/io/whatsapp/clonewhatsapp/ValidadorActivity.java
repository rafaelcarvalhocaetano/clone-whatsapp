package io.whatsapp.clonewhatsapp;import android.os.Bundle;import android.support.annotation.Nullable;import android.support.v7.app.AppCompatActivity;import android.widget.EditText;import com.github.rtoshiro.util.format.SimpleMaskFormatter;import com.github.rtoshiro.util.format.text.MaskTextWatcher;/** * Created by rafaelcarvalho on 18/12/2017. */public class ValidadorActivity extends AppCompatActivity {    private EditText sms;    @Override    protected void onCreate(@Nullable Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_validador);        sms = (EditText) findViewById(R.id.edit_codigo);        SimpleMaskFormatter SimpleSMS = new SimpleMaskFormatter("N-N-N-N");        MaskTextWatcher maskSMS = new MaskTextWatcher(sms, SimpleSMS);        sms.addTextChangedListener(maskSMS);    }}