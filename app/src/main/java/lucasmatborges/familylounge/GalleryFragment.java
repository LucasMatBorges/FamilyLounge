package lucasmatborges.familylounge;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

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
        final View galleryView = inflater.inflate(R.layout.fragment_gallery, container, false);
        final String myStr = getArguments().getString("my_key");

        // TABLE LAYOUT
        final TableRow RowCheck = (TableRow)  galleryView.findViewById(R.id.TableRowCheck);
        final TableRow RowExames = (TableRow)  galleryView.findViewById(R.id.TableRowExames);
        final TableRow RowAnestesia = (TableRow)  galleryView.findViewById(R.id.TableRowAnestesia);
        final TableRow RowCirurgia = (TableRow)  galleryView.findViewById(R.id.TableRowCirurgia);
        final TableRow RowFinalizacao = (TableRow)  galleryView.findViewById(R.id.TableRowFinalizacao);
        final TableRow RowCurativos = (TableRow)  galleryView.findViewById(R.id.TableRowCurativos);

        // IMAGE VIEW FINALIZADO/PENDENTE
        final ImageView ImgCheck = (ImageView) galleryView.findViewById(R.id.imageViewCheck);
        final ImageView ImgExames = (ImageView) galleryView.findViewById(R.id.imageViewExames);
        final ImageView ImgAnestesia = (ImageView) galleryView.findViewById(R.id.imageViewAnestesia);
        final ImageView ImgCirurgia = (ImageView) galleryView.findViewById(R.id.imageViewCirurgia);
        final ImageView ImgFinalizacao = (ImageView) galleryView.findViewById(R.id.imageViewFinalizacao);
        final ImageView ImgCurativos = (ImageView) galleryView.findViewById(R.id.imageViewCurativos);

        // TEXT VIEW INICIO PROCEDIMENTO
        final TextView TextCheckInic= (TextView) galleryView.findViewById(R.id.textCheckInicio);
        final TextView TextExamesInic = (TextView) galleryView.findViewById(R.id.textExamesInicio);
        final TextView TextAnestesiaInic = (TextView) galleryView.findViewById(R.id.textAnestesiaInicio);
        final TextView TextCirurgiaInic = (TextView) galleryView.findViewById(R.id.textCirurgiaInicio);
        final TextView TextFinalizacaoInic = (TextView) galleryView.findViewById(R.id.textFinalizacaoInicio);
        final TextView TextCurativosInic = (TextView) galleryView.findViewById(R.id.textCurativosInicio);

        // TEXT VIEW DURACACAO PROCEDIMENTO
        final TextView TextCheckDur = (TextView) galleryView.findViewById(R.id.textCheckDuracao);
        final TextView TextExamesDur = (TextView) galleryView.findViewById(R.id.textExamesDuracao);
        final TextView TextAnestesiaDur = (TextView) galleryView.findViewById(R.id.textAnestesiaDuracao);
        final TextView TextCirurgiaDur = (TextView) galleryView.findViewById(R.id.textCirurgiaDuracao);
        final TextView TextFinalizacaoDur = (TextView) galleryView.findViewById(R.id.textFinalizacaoDuracao);
        final TextView TextCurativosDur = (TextView) galleryView.findViewById(R.id.textCurativosDuracao);


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

                String cirurgia = mapa.get("procedimento");
                String sala = mapa.get("sala");
                String medico = mapa.get("medico");
                String status = mapa.get("status");

                String anestesia = mapa.get("anestesia");
                String[] arrayanestesia = anestesia.split(",");
                TextAnestesiaInic.setText(arrayanestesia[0]);
                TextAnestesiaDur.setText(arrayanestesia[1]);

                String check = mapa.get("check");
                String[] arraycheck = check.split(",");
                TextCheckInic.setText(arraycheck[0]);
                TextCheckDur.setText(arraycheck[1]);

                String cirurgia2 = mapa.get("cirurgia");
                String[] arraycirurgia = cirurgia2.split(",");
                TextCirurgiaInic.setText(arraycirurgia[0]);
                TextCirurgiaDur.setText(arraycirurgia[1]);

                String exames = mapa.get("exames");
                String[] arrayexames = exames.split(",");
                TextExamesInic.setText(arrayexames[0]);
                TextExamesDur.setText(arrayexames[1]);

                String finalizacao = mapa.get("finalizacao");
                String[] arrayfinalizacao = finalizacao.split(",");
                TextFinalizacaoInic.setText(arrayfinalizacao[0]);
                TextFinalizacaoDur.setText(arrayfinalizacao[1]);

                String curativos = mapa.get("curativos");
                String[] arraycurativos = curativos.split(",");
                TextCurativosInic.setText(arraycurativos[0]);
                TextCurativosDur.setText(arraycurativos[1]);

                if (status.equals("check")){
                    RowCheck.setBackgroundColor(getContext().getResources().getColor(R.color.colorOrange));
                    ImgCheck.setBackground(getContext().getResources().getDrawable(R.drawable.check));

                }
                else if (status.equals("exames")){
                    RowCheck.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgCheck.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowExames.setBackgroundColor(getContext().getResources().getColor(R.color.colorOrange));
                    ImgExames.setBackground(getContext().getResources().getDrawable(R.drawable.pendente));
                }
                else if (status.equals("anestesia")){
                    RowCheck.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgCheck.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowExames.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgExames.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowAnestesia.setBackgroundColor(getContext().getResources().getColor(R.color.colorOrange));
                    ImgAnestesia.setBackground(getContext().getResources().getDrawable(R.drawable.pendente));
                }
                else if (status.equals("cirurgia")){
                    RowCheck.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgCheck.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowExames.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgExames.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowAnestesia.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgAnestesia.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowCirurgia.setBackgroundColor(getContext().getResources().getColor(R.color.colorOrange));
                    ImgCirurgia.setBackground(getContext().getResources().getDrawable(R.drawable.pendente));
                }
                else if (status.equals("finalização")){
                    RowCheck.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgCheck.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowExames.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgExames.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowAnestesia.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgAnestesia.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowCirurgia.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgCirurgia.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowFinalizacao.setBackgroundColor(getContext().getResources().getColor(R.color.colorOrange));
                    ImgFinalizacao.setBackground(getContext().getResources().getDrawable(R.drawable.pendente));
                }
                else if (status.equals("curativos")){
                    RowCheck.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgCheck.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowExames.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgExames.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowAnestesia.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgAnestesia.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowCirurgia.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgCirurgia.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowFinalizacao.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                    ImgFinalizacao.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                    RowCurativos.setBackgroundColor(getContext().getResources().getColor(R.color.colorOrange));
                    ImgCurativos.setBackground(getContext().getResources().getDrawable(R.drawable.pendente));
                }


                BtnCirurgia.setText(cirurgia);
                BtnMedico.setText(" MÉDICO: " + medico + " ");
                BtnSala.setText(" " +sala+ " ");
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
                RowCheck.setBackgroundColor(getContext().getResources().getColor(R.color.colorOrange));
                ImgCheck.setBackground(getContext().getResources().getDrawable(R.drawable.check));
                // RowCheck.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
//                android.support.v4.app.FragmentTransaction fragmentTransaction =
//                        getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
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
                fragmentTransaction.addToBackStack(null);
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
                fragmentTransaction.addToBackStack(null);
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
                fragmentTransaction.addToBackStack(null);
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
                fragmentTransaction.addToBackStack(null);
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
                fragmentTransaction.replace(R.id.fragment_container, fragment, "teste");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return galleryView;
    }
}
