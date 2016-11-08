package lucasmatborges.familylounge;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        final String myStr = getArguments().getString("my_key");
        meunome = (TextView) rootView.findViewById(R.id.textView2);
        textoFirebase = (TextView) rootView.findViewById(R.id.textViewFirebase);

        final Button mainButton = (Button) rootView.findViewById(R.id.buttonStatus);
        final Button BtnPaciente = (Button) rootView.findViewById(R.id.buttonPaciente);
        final Button BtnCirurgia = (Button) rootView.findViewById(R.id.buttonCirurgia);
        final Button BtnLeito = (Button) rootView.findViewById(R.id.buttonLeito);
        final Button BtnDetalhes = (Button) rootView.findViewById(R.id.buttonDetalhes);
        final Button BtnStatus = (Button) rootView.findViewById(R.id.buttonStatus);

        mRef = new Firebase("https://familylounge-aaa1e.firebaseio.com/cirurgias");

        // ARRUMANDO A HORA
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("kk:mm");
        String currentDateTimeString = sdf.format(d);
        String[] arrayWords = currentDateTimeString.split(":");
        int hora = Integer.parseInt(arrayWords[1])*60;
        System.out.println("MainActivity.onCreate: " + hora);

        BtnLeito.setText(currentDateTimeString);

        final Firebase novaRef = mRef.child(myStr);  //acessar um "child"

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
                //BtnLeito.setText(" " + leito+ " ");
                BtnStatus.setText(" " + status+ " ");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


        BtnDetalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Set the fragment initially
                    GalleryFragment fragment = new GalleryFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("my_key", myStr);
                    fragment.setArguments(bundle);
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

        BtnStatus.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));
                Log.i("Video", "Video Playing....");

            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }


}
