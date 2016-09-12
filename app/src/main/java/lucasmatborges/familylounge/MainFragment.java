package lucasmatborges.familylounge;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    //final TextView meunome = (TextView) findViewById(R.id.textView2);
    private TextView meunome;
    private Firebase mRef; //Firebase

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        meunome = (TextView) rootView.findViewById(R.id.textView2);

        final Button mainButton = (Button) rootView.findViewById(R.id.main_button);

        mRef = new Firebase("https://familylounge-aaa1e.firebaseio.com/");

        //final Firebase novaRef = mRef.child("Pessoa");  //acessar um "child"


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());
                //String mensagem = snapshot.getValue(String.class);
                String chega = "oi";
                String agora = "k";
                //String junto = (mensagem+agora);
                //meunome.setText(mensagem);
                //Toast.makeText(getActivity(), mensagem, Toast.LENGTH_SHORT).show();

                Map<String,String> mapa = snapshot.getValue(Map.class);
//
                String nome = mapa.get("nome");
//
//                // meuTexto.setText(nome+ " "+ sobrenome+ " "+ email+ " " + telefone );
               meunome.setText("ola, "+nome);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainButton.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));


                Toast.makeText(getActivity(),"Text!",Toast.LENGTH_SHORT).show();
                meunome.setText("VAII");

            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }


}
