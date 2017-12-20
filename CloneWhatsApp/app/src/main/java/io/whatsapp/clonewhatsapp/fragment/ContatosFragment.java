package io.whatsapp.clonewhatsapp.fragment;import android.os.Bundle;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ArrayAdapter;import android.widget.ListView;import com.google.firebase.database.DataSnapshot;import com.google.firebase.database.DatabaseError;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.ValueEventListener;import java.util.ArrayList;import io.whatsapp.clonewhatsapp.R;import io.whatsapp.clonewhatsapp.firebase.ConfigFireBase;import io.whatsapp.clonewhatsapp.helpers.Preferencias;import io.whatsapp.clonewhatsapp.model.Contato;/** * A simple {@link Fragment} subclass. */public class ContatosFragment extends Fragment {    private ListView lista_contatos;    private ArrayAdapter adapter;    private ArrayList<String> contatos;    private DatabaseReference firebase;    public ContatosFragment() {        // Required empty public constructor    }    @Override    public View onCreateView(LayoutInflater inflater, ViewGroup container,                             Bundle savedInstanceState) {        contatos = new ArrayList<>();        contatos.add("rafael");        contatos.add("rose");        contatos.add("vitor");        contatos.add("Heitor");        // Inflate the layout for this fragment        View v = inflater.inflate(R.layout.fragment_contatos, container, false);        lista_contatos = (ListView) v.findViewById(R.id.lista_contatos);        adapter = new ArrayAdapter(                getActivity(),                R.layout.lista_contatos,                contatos        );        lista_contatos.setAdapter( adapter );        //recuperando o identificador para setar na listview        Preferencias p = new Preferencias( getActivity() );        String uidUserLogado = p.getIdentificador();        firebase = ConfigFireBase.getFirebase()        .child("contatos")        .child( uidUserLogado );        firebase.addValueEventListener(new ValueEventListener() {            @Override            public void onDataChange(DataSnapshot dataSnapshot) {                //limpando lista                contatos.clear();                //listando contatos                for (DataSnapshot data : dataSnapshot.getChildren() ){                    Contato contato = data.getValue( Contato.class );                    contatos.add( contato.getNome() );                }                adapter.notifyDataSetChanged();            }            @Override            public void onCancelled(DatabaseError databaseError) {            }        });        return v;    }}