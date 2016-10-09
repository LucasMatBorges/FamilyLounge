package lucasmatborges.familylounge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void voltar (View view){
        Intent openInfoActivity = new Intent(this, LoginScreen.class);
        startActivity(openInfoActivity);
    }
}
