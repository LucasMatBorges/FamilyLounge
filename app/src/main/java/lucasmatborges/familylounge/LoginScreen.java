package lucasmatborges.familylounge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
    }

    public void logar (View view){
        EditText editText = (EditText) findViewById(R.id.editTextID);
        String prontuario = editText.getText().toString();
        editText.setText("");
        Intent openGameMulti = new Intent(this, MainActivity.class);
        openGameMulti.putExtra("POINTS_IDENTIFIER", prontuario);
        startActivity(openGameMulti);
    }

}
