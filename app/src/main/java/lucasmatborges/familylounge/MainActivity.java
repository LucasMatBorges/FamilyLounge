package lucasmatborges.familylounge;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
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

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    ActionBarDrawerToggle toggle; //Hamburguer
    boolean doubleBackToExitPressedOnce = false;
    boolean a = false;
    private Firebase mRef; //Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        //Firebase Notification
        System.out.println("MainActivity.onCreate: " + FirebaseInstanceId.getInstance().getToken());

        final String send = getIntent().getStringExtra("POINTS_IDENTIFIER");
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("my_key", send);
        fragment.setArguments(bundle);
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mRef = new Firebase("https://familylounge-aaa1e.firebaseio.com/cirurgias");

        final Firebase novaRef = mRef.child(send);  //acessar um "child"


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, send, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        novaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());

                Map<String,String> mapa = snapshot.getValue(Map.class);

                String paciente = mapa.get("paciente");
                View header = navigationView.getHeaderView(0);
                TextView text = (TextView) header.findViewById(R.id.username);
                TextView text2 = (TextView) header.findViewById(R.id.email);
                text.setText(paciente);
                text2.setText(send);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int count = getSupportFragmentManager().getBackStackEntryCount(); // PRECISAVA COLOCAR O GETSUPPORTFRAGMENTMANAGER -> SEM O SUPPORT NÃO FUNCIONA
//        String strI = String.valueOf(count);
//        Toast.makeText(this, strI, Toast.LENGTH_SHORT).show();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else if (count >0){
            getSupportFragmentManager().popBackStack();
        }
        else if(a==true){
            //Set the fragment initially
            MainFragment fragment = new MainFragment();
            final String send = getIntent().getStringExtra("POINTS_IDENTIFIER");
            Bundle bundle = new Bundle();
            bundle.putString("my_key", send);
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            a=false;

        }

        else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //Set the fragment initially
            MainFragment fragment = new MainFragment();
            final String send = getIntent().getStringExtra("POINTS_IDENTIFIER");
            Bundle bundle = new Bundle();
            bundle.putString("my_key", send);
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_gallery) {
            //Set the fragment initially
            GalleryFragment fragment = new GalleryFragment();
            final String send = getIntent().getStringExtra("POINTS_IDENTIFIER");
            Bundle bundle = new Bundle();
            bundle.putString("my_key", send);
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            a = true;
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_slideshow) {
            //Set the fragment initially
            InfoFragment fragment = new InfoFragment();
            final String send = getIntent().getStringExtra("POINTS_IDENTIFIER");
            Bundle bundle = new Bundle();
            bundle.putString("my_key", send);
            fragment.setArguments(bundle);
            a = true;
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_manage) {
            //Set the fragment initially
            MainFragment fragment = new MainFragment();
            final String send = getIntent().getStringExtra("POINTS_IDENTIFIER");
            Bundle bundle = new Bundle();
            bundle.putString("my_key", send);
            fragment.setArguments(bundle);
            a = true;
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}