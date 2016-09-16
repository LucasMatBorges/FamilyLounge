package lucasmatborges.familylounge;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Map;
import java.util.Random;

public class LoginScreen extends AppCompatActivity {
    private Firebase mRef; //Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        Firebase.setAndroidContext(this);

        mRef = new Firebase("https://familylounge-aaa1e.firebaseio.com/");

        //final Firebase novaRef = mRef.child("prontuarios");  //acessar um "child"

        EditText editText = (EditText) findViewById(R.id.editTextID);
        final String prontuario = editText.getText().toString();
        final TextView textoverifica = (TextView) findViewById(R.id.textViewProntuario);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());

                Map<String, String> mapa = snapshot.getValue(Map.class);

                String Prontuario = mapa.get("prontuarios");

                String[] arrayWords = Prontuario.split(",");
                int n = arrayWords.length; // Gives n such that 0 <= n < 2 (arrayWords.length)
                for (int i = 0; i < arrayWords.length; i++) {
                    System.out.println("Verifica: " + prontuario);
                    System.out.println("Verifica: " + arrayWords[i]);

                    if (prontuario == arrayWords[i]) { // char é uma letra
                        textoverifica.setText("true");
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


    }

    public void logar (View view){


        EditText editText = (EditText) findViewById(R.id.editTextID);
        final String prontuario = editText.getText().toString();
        final TextView textoverifica = (TextView) findViewById(R.id.textViewProntuario);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());

                Map<String, String> mapa = snapshot.getValue(Map.class);

                String Prontuario = mapa.get("prontuarios");

                String[] arrayWords = Prontuario.split(",");
                int n = arrayWords.length; // Gives n such that 0 <= n < 2 (arrayWords.length)
                for (int i = 0; i < arrayWords.length; i++) {
                    System.out.println("For prontuario: " + prontuario);
                    System.out.println("For array: " + arrayWords[i]);

                    if (prontuario.equals(arrayWords[i])) { // char é uma letra
                        textoverifica.setText("true");
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        // Comanda para criar a splashScreen
        Runnable runnable3secs = new Runnable() {
            @Override
            public void run() {
                verificar();

            }
        };

        Handler myhandler = new Handler();
        myhandler.postDelayed(runnable3secs,500); // 3000 = tempo de execução da splash ( 3000 milisegundos = 3 segundos)

    }


public void verificar (){
    EditText editText = (EditText) findViewById(R.id.editTextID);
    final String prontuario = editText.getText().toString();
    final TextView textoverifica = (TextView) findViewById(R.id.textViewProntuario);
    String verifica = textoverifica.getText().toString();
    System.out.println("Dps da conversão Verifica: " + verifica);
    System.out.println("Colocada: " + prontuario);

    if (verifica.equals("true")){ // NÃO FUNCIONA O "=="
        editText.setText("");
        Intent openGameMulti = new Intent(this, MainActivity.class);
        openGameMulti.putExtra("POINTS_IDENTIFIER", prontuario);
        startActivity(openGameMulti);
        Toast.makeText(this, verifica, Toast.LENGTH_SHORT).show();
        System.out.println("true: " + verifica);}
    else {
        Toast.makeText(this, "Não achamos esse prontuário", Toast.LENGTH_SHORT).show();
        System.out.println("True ou false: " + verifica);

    }
}}


