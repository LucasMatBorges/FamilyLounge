package lucasmatborges.familylounge;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

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
        final ImageButton BtnInfoCheck = (ImageButton) galleryView.findViewById(R.id.buttonInfoCheck);
        final ImageButton BtnInfoExames = (ImageButton) galleryView.findViewById(R.id.buttonInfoExames);
        final ImageButton BtnInfoAnestesia = (ImageButton) galleryView.findViewById(R.id.buttonInfoAnestesia);
        final ImageButton BtnInfoCirurgia = (ImageButton) galleryView.findViewById(R.id.buttonInfoCirurgia);
        final ImageButton BtnInfoFinalizacao = (ImageButton) galleryView.findViewById(R.id.buttonInfoFinalizacao);
        final ImageButton BtnInfoCurativos = (ImageButton) galleryView.findViewById(R.id.buttonInfoCurativos);

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


        BtnInfoCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cirurgia = (String) BtnCirurgia.getText();
                DetalheFragment fragment = new DetalheFragment();
                Bundle mainBundle = new Bundle();
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle.putString("my_key", "check-in");
                bundle2.putString("my_key2", cirurgia.toLowerCase());
                mainBundle.putBundle("my_b", bundle);
                mainBundle.putBundle("my_b2", bundle2);
                fragment.setArguments(mainBundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });
        BtnInfoExames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cirurgia = (String) BtnCirurgia.getText();
                DetalheFragment fragment = new DetalheFragment();
                Bundle mainBundle = new Bundle();
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle.putString("my_key", "exames");
                bundle2.putString("my_key2", cirurgia.toLowerCase());
                mainBundle.putBundle("my_b", bundle);
                mainBundle.putBundle("my_b2", bundle2);
                fragment.setArguments(mainBundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });
        BtnInfoAnestesia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cirurgia = (String) BtnCirurgia.getText();
                DetalheFragment fragment = new DetalheFragment();
                Bundle mainBundle = new Bundle();
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle.putString("my_key", "anestesia");
                bundle2.putString("my_key2", cirurgia.toLowerCase());
                mainBundle.putBundle("my_b", bundle);
                mainBundle.putBundle("my_b2", bundle2);
                fragment.setArguments(mainBundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });
        BtnInfoCirurgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cirurgia = (String) BtnCirurgia.getText();
                DetalheFragment fragment = new DetalheFragment();
                Bundle mainBundle = new Bundle();
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle.putString("my_key", "cirurgia");
                bundle2.putString("my_key2", cirurgia.toLowerCase());
                mainBundle.putBundle("my_b", bundle);
                mainBundle.putBundle("my_b2", bundle2);
                fragment.setArguments(mainBundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });
        BtnInfoFinalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cirurgia = (String) BtnCirurgia.getText();
                DetalheFragment fragment = new DetalheFragment();
                Bundle mainBundle = new Bundle();
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle.putString("my_key", "finalizacao");
                bundle2.putString("my_key2", cirurgia.toLowerCase());
                mainBundle.putBundle("my_b", bundle);
                mainBundle.putBundle("my_b2", bundle2);
                fragment.setArguments(mainBundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });
        BtnInfoCurativos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cirurgia = (String) BtnCirurgia.getText();
                DetalheFragment fragment = new DetalheFragment();
                Bundle mainBundle = new Bundle();
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle.putString("my_key", "curativos");
                bundle2.putString("my_key2", cirurgia.toLowerCase());
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
