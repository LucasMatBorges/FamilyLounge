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

    private TextView meunome;
    private TextView textoFirebase;
    private Firebase mRef; //Firebase

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        meunome = (TextView) rootView.findViewById(R.id.textView2);
        textoFirebase = (TextView) rootView.findViewById(R.id.textViewFirebase);
        final Bundle bundle = this.getArguments();// Pega os dados enviados
        final String myInt = bundle.getString("lucas", "nada"); // Pega dados enviados

        final Button mainButton = (Button) rootView.findViewById(R.id.buttonStatus);
        final Button BtnPaciente = (Button) rootView.findViewById(R.id.buttonPaciente);
        final Button BtnCirurgia = (Button) rootView.findViewById(R.id.buttonCirurgia);
        final Button BtnLeito = (Button) rootView.findViewById(R.id.buttonLeito);
        final Button BtnDetalhes = (Button) rootView.findViewById(R.id.buttonDetalhes);
        final Button BtnStatus = (Button) rootView.findViewById(R.id.buttonStatus);

        mRef = new Firebase("https://familylounge-aaa1e.firebaseio.com/");

        final Firebase novaRef = mRef.child("cirurgia");  //acessar um "child"


        novaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());

                Map<String,String> mapa = snapshot.getValue(Map.class);

                String cirurgia = mapa.get("cirurgia");
                String paciente = mapa.get("paciente");
                String status = mapa.get("status");
                String leito = mapa.get("leito");

                BtnPaciente.setText(" " + paciente+ " ");
                BtnCirurgia.setText(" " + cirurgia+ " ");
                BtnLeito.setText(" " + leito+ " ");
                BtnStatus.setText(" " + status+ " ");
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
                String worldtoGuess = textoFirebase.getText().toString();
                Toast.makeText(getActivity(),myInt,Toast.LENGTH_SHORT).show();
                mainButton.setText(worldtoGuess);

            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }


}
