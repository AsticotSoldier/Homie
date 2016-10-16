package aramanth.homie;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;

public class Accueil extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,frag_kitchen.OnFragmentInteractionListener,
        frag_salon.OnFragmentInteractionListener,frag_restroom.OnFragmentInteractionListener,
        frag_garage.OnFragmentInteractionListener,frag_garden.OnFragmentInteractionListener,
        frag_bedroom.OnFragmentInteractionListener,frag_bathroom.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
        FragmentManager fm = getSupportFragmentManager();
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
        int id = item.getItemId();
        FragmentManager frag_manag = getSupportFragmentManager();
        if (id == R.id.kitchen) {
            toolbar_activity.setTitle("Cuisine");
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_kitchen()).commit();
        } else if (id == R.id.salon) {
            toolbar_activity.setTitle("Salon");
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_salon()).commit();
        } else if (id == R.id.restroom) {
            toolbar_activity.setTitle("Salle à manger");
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_restroom()).commit();
        } else if (id == R.id.garage) {
            toolbar_activity.setTitle("Garage");
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_garage()).commit();
        } else if (id == R.id.garden) {
            toolbar_activity.setTitle("Jardin");
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_garden()).commit();
        } else if (id == R.id.bedroom) {
            toolbar_activity.setTitle("Chambre");
            frag_manag.beginTransaction().replace(R.id.default_layout, new frag_bedroom()).commit();
        } else if (id == R.id.bathroom) {
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
