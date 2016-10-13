package lucasmatborges.familylounge;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalheFragment extends Fragment {

    private Firebase mRef; //Firebase

    public DetalheFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View detalheView = inflater.inflate(R.layout.fragment_detalhe, container, false);

        Bundle mainBundle = getArguments();
        final Bundle b1 = mainBundle.getBundle("my_b");
        final Bundle b2 = mainBundle.getBundle("my_b2");

        final String detalhe = b1.getString("my_key");
        final String cirurgia = b2.getString("my_key2");


        final Button BtnSala = (Button) detalheView.findViewById(R.id.buttonSala);
        final Button BtnMedico = (Button) detalheView.findViewById(R.id.buttonMedico);

        mRef = new Firebase("https://familylounge-aaa1e.firebaseio.com/procedimentos");

        final Firebase novaRef = mRef.child(cirurgia);  //acessar um "child"


        novaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());

                Map<String,String> mapa = snapshot.getValue(Map.class);

                String procedimento = mapa.get(detalhe);

                BtnSala.setText(procedimento);
                BtnMedico.setText("Procedimento: " + detalhe);

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });



        BtnSala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnSala.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
                BtnSala.setText("vai");
            }
        });
        return detalheView;
    }
}
