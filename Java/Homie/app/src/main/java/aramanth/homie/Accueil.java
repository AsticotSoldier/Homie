package aramanth.homie;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.AvoidXfermode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.pm.PackageManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Accueil extends AppCompatActivity

    implements NavigationView.OnNavigationItemSelectedListener,frag_kitchen.OnFragmentInteractionListener,
        frag_salon.OnFragmentInteractionListener,frag_restroom.OnFragmentInteractionListener,
        frag_garage.OnFragmentInteractionListener,frag_garden.OnFragmentInteractionListener,
        frag_bedroom.OnFragmentInteractionListener,frag_bathroom.OnFragmentInteractionListener{

    private  BluetoothAdapter BLE_Adaptater;
    private BluetoothLeScanner BLE_scan;
    private BluetoothGatt mGatt;
    private BluetoothDevice BLE_device;
    private BluetoothGattService Ble_service;
    private BluetoothGattCharacteristic txCarac;

    private EditText editText;
    private TextView receiveText;

    private static final int RequestLocationId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button favoris;
        favoris = (Button) findViewById(R.id.favor_button);
        final Button viewed;
        viewed = (Button) findViewById(R.id.most_view_button);
        final Button recently;
        recently = (Button) findViewById(R.id.recently_button);

// ---------bouton exclusif
        favoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewed.setBackgroundColor(Color.TRANSPARENT);
                viewed.setTextColor(Color.rgb(144,198,82));
                favoris.setBackgroundColor(Color.rgb(144,198,82));
                favoris.setTextColor(Color.WHITE);
                recently.setBackgroundColor(Color.TRANSPARENT);
                recently.setTextColor(Color.rgb(144,198,82));
            }
        });
        recently.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewed.setBackgroundColor(Color.TRANSPARENT);
                viewed.setTextColor(Color.rgb(144,198,82));
                recently.setBackgroundColor(Color.rgb(144,198,82));
                recently.setTextColor(Color.WHITE);
                favoris.setBackgroundColor(Color.TRANSPARENT);
                favoris.setTextColor(Color.rgb(144,198,82));
            }
        });
        viewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoris.setBackgroundColor(Color.TRANSPARENT);
                favoris.setTextColor(Color.rgb(144,198,82));
                viewed.setBackgroundColor(Color.rgb(144,198,82));
                viewed.setTextColor(Color.WHITE);
                recently.setBackgroundColor(Color.TRANSPARENT);
                recently.setTextColor(Color.rgb(144,198,82));
            }
        });
//-----------------
// autorisation
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        RequestLocationId);
            }
        } else {

           // BLE_scan.startScan(mScanCallback);
        }
//-----

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,    toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.accueil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        FragmentManager frag_manag = getSupportFragmentManager();
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
        Toolbar toolbar_activity;
        toolbar_activity= (Toolbar) findViewById(R.id.toolbar);

        RelativeLayout def_relative_layout;
        def_relative_layout= (RelativeLayout) findViewById(R.id.content_accueil);

        int id = item.getItemId();
        FragmentManager frag_manag = getSupportFragmentManager();
        if (id == R.id.kitchen) {
            toolbar_activity.setTitle("Cuisine");
            def_relative_layout.setVisibility(View.INVISIBLE);
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_kitchen()).commit();
        } else if (id == R.id.salon) {
            def_relative_layout.setVisibility(View.INVISIBLE);
            toolbar_activity.setTitle("Salon");
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_salon()).commit();
        } else if (id == R.id.restroom) {
            def_relative_layout.setVisibility(View.INVISIBLE);
            toolbar_activity.setTitle("Salle à manger");
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_restroom()).commit();
        } else if (id == R.id.garage) {
            def_relative_layout.setVisibility(View.INVISIBLE);
            toolbar_activity.setTitle("Garage");
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_garage()).commit();
        } else if (id == R.id.garden) {
            def_relative_layout.setVisibility(View.INVISIBLE);
            toolbar_activity.setTitle("Jardin");
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_garden()).commit();
        } else if (id == R.id.bedroom) {
            def_relative_layout.setVisibility(View.INVISIBLE);
            toolbar_activity.setTitle("Chambre");
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_bedroom()).commit();
        } else if (id == R.id.bathroom) {
            def_relative_layout.setVisibility(View.INVISIBLE);
            toolbar_activity.setTitle("Salle de bain");
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_bathroom()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
