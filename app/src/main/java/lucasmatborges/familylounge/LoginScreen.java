package lucasmatborges.familylounge;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.InputStream;
import java.util.Map;
import java.util.Random;

public class LoginScreen extends AppCompatActivity {
    private Firebase mRef; //Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        Firebase.setAndroidContext(this);
        String x = "j";

        // SLIDE INTRO
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences(Config.FLAG, Context.MODE_PRIVATE);

                if(sharedPreferences.getBoolean(Config.FLAG,true)){


                    startActivity(new Intent(LoginScreen.this,DefaultIntro.class));

                    SharedPreferences.Editor e=sharedPreferences.edit();

                    e.putBoolean(Config.FLAG,false);

                    e.apply();
                }
            }
        });
        t.start();


        mRef = new Firebase("https://familylounge-aaa1e.firebaseio.com/");

        //final Firebase novaRef = mRef.child("prontuarios");  //acessar um "child"

        EditText editText = (EditText) findViewById(R.id.editTextID);
        final String prontuario = editText.getText().toString();
        final TextView textoverifica = (TextView) findViewById(R.id.textViewProntuario);
        editText.setRawInputType(Configuration.KEYBOARD_QWERTY);


         editText.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            enter();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
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

    public void enter (){


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
                        break;
                    }
                    else{
                        textoverifica.setText("false");
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
                        break;
                    }
                    else{
                        textoverifica.setText("false");
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        // Comanda para criar a splashScreen
        Runnable runnable3secs =    new Runnable() {
            @Override
            public void run() {
                verificar();

            }
        };

        Handler myhandler = new Handler();
        myhandler.postDelayed(runnable3secs,500); // 3000 = tempo de execução da splash ( 3000 milisegundos = 3 segundos)

    }

    public void info (View view){
        Intent openInfoActivity = new Intent(this, InfoActivity.class);
        startActivity(openInfoActivity);
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
        textoverifica.setText("false");
        openGameMulti.putExtra("POINTS_IDENTIFIER", prontuario);
        startActivity(openGameMulti);
        System.out.println("true: " + verifica);}
    else {
        Toast.makeText(this, "Não achamos esse prontuário", Toast.LENGTH_SHORT).show();
        System.out.println("True ou false: " + verifica);
    }
}}


