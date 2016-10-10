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
public class GalleryFragment extends Fragment {

    private Firebase mRef; //Firebase

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View galleryView = inflater.inflate(R.layout.fragment_gallery, container, false);
        final String myStr = getArguments().getString("my_key");

        final Button BtnSala = (Button) galleryView.findViewById(R.id.buttonSala);
        final Button BtnMedico = (Button) galleryView.findViewById(R.id.buttonMedico);
        final Button BtnCirurgia = (Button) galleryView.findViewById(R.id.buttonCirurgia);
        final Button BtnInfo = (Button) galleryView.findViewById(R.id.buttonInfo);

        mRef = new Firebase("https://familylounge-aaa1e.firebaseio.com/cirurgias");

        final Firebase novaRef = mRef.child(myStr);  //acessar um "child"

        novaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());

                Map<String,String> mapa = snapshot.getValue(Map.class);

                String cirurgia = mapa.get("cirurgia");
                String sala = mapa.get("sala");
                String medico = mapa.get("medico");

                BtnCirurgia.setText(cirurgia);
                BtnMedico.setText(" MÉDICO: " + medico + " ");
                BtnSala.setText(" SALA: " + sala+ " ");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


        BtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cirurgia = (String) BtnCirurgia.getText();
                DetalheFragment fragment = new DetalheFragment();
                Bundle mainBundle = new Bundle();
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle.putString("my_key", "anestesia");
                bundle2.putString("my_key2", "amigdalectomia");
                mainBundle.putBundle("my_b", bundle);
                mainBundle.putBundle("my_b2", bundle2);
                fragment.setArguments(mainBundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });
        return galleryView;
    }




}
