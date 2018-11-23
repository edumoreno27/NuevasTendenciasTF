package com.example.xero.cardtradeapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.xero.cardtradeapp.Entities.Subasta;
import com.example.xero.cardtradeapp.Entities.Carta;
import com.example.xero.cardtradeapp.Entities.Orden;

public class MainActivity extends AppCompatActivity
        implements
        SubastasFragment.OnListFragmentInteractionListener,
        SubastaFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        NotificacionesFragment.OnFragmentInteractionListener,
        SubastaSelectedFragment.OnFragmentInteractionListener,
        OrdenFragment.OnListFragmentInteractionListener,
        CartaFragment.OnListFragmentInteractionListener
{

    //MenuItem itemSearch;
    //SearchView searchView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            /*itemSearch.setVisible(false);
            itemSearch.collapseActionView();
            searchView.onActionViewCollapsed();*/
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //itemSearch.setVisible(true);
                    fragment = new SubastasFragment();
                    break;
                case R.id.navigation_auction:
                    fragment = new SubastaFragment();
                    break;
                case R.id.navigation_profile:
                    fragment = new ProfileFragment();
                    break;
                case R.id.navigation_notifications:
                    fragment = new OrdenFragment();
                    break;
                default:
                    /*searchView.setQuery("", false);
                    searchView.clearFocus();
                    searchView.setVisibility(View.GONE);*/
                    return  false;
            }
            if (fragment!=null) getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // navigation bar
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        try {
            int key = (int) getIntent().getExtras().get("fragmentKey");

            switch (key) {
                case 1:
                    CartaFragment cartaFragment = new CartaFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, cartaFragment).commit();
                    break;
                case 2:
                    SubastaFragment subastaFragment = new SubastaFragment();
                    int CardId = (int) getIntent().getExtras().get("CardId");
                    String CardName = (String) getIntent().getExtras().get("CardName");
                    Bundle bundle = new Bundle();
                    bundle.putInt("CardId",CardId );
                    bundle.putString("CardName",CardName );
                    subastaFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, subastaFragment).commit();
                    break;
                default:
                    SubastasFragment homeFragment = new SubastasFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();
                    break;
            }
        }catch (Exception e){
            SubastasFragment homeFragment = new SubastasFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();
        }
        // loading first layout


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(Subasta item) {
        //Toast.makeText(getApplicationContext(),"id: "+item.getName(),Toast.LENGTH_SHORT).show();
        SubastaSelectedFragment subastaSelectedFragment = new SubastaSelectedFragment();
        subastaSelectedFragment.setSubasta_selected(item);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, subastaSelectedFragment).commit();
    }

    @Override
    public void onListFragmentInteraction(Orden item) {

    }

    @Override
    public void onListFragmentInteraction(Carta item) {

    }

    /*
    @Override
    public void OnSeeItemListener() {
        // ver item seleccionado
    }*/


    // UPDATE: SE PASO A SU PROPIO FRAGMENT
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_item, menu);
        itemSearch = menu.findItem(R.id.action_search);
        searchView = (SearchView) itemSearch.getActionView();
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.cardview_light_background));
        // event search...
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {return false;}

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/

}
