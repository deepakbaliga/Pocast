package com.deepakbaliga.pocast;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.deepakbaliga.pocast.adapter.CategoryAdapter;
import com.deepakbaliga.pocast.adapter.NavigationRecyclerViewAdapter;
import com.deepakbaliga.pocast.callback.ListItemClick;
import com.deepakbaliga.pocast.manager.MyLinearLayoutManager;
import com.deepakbaliga.pocast.model.CategoryModel;
import com.deepakbaliga.pocast.transform.CircleTransform;

import java.util.LinkedList;

import me.alexrs.prefs.lib.Prefs;

public class ActivityMain extends AppCompatActivity {

    private RecyclerView navRecyclerView;
    private RecyclerView catRecyclerView;
    private ListItemClick listItemClick;
    private NavigationRecyclerViewAdapter adapter;
    private CategoryAdapter categoryAdapter;

    private ImageView profilePicture;
    private TextView profileName;
    private TextView profileEmail;
    private TextView categoryTitle;

    private TextView settings;
    private RelativeLayout settingsRelative;

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FragmentTransaction transaction;

    private LinkedList<CategoryModel> categories = new LinkedList<>();
    private ListItemClick categoriesItemClick;

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        fragment = null;
        transaction = getSupportFragmentManager().beginTransaction();

        fragment = Fragment.instantiate(getApplicationContext(), FragmentHome.class.getName());
        fragment.setRetainInstance(true);
        transaction.replace(R.id.main_content, fragment, "home");
        transaction.commit();


        navigationView = (NavigationView) findViewById(R.id.nav_view);


        listItemClick = new ListItemClick() {
            @Override
            public void clicked(int position) {

                fragment = null;
                transaction = getSupportFragmentManager().beginTransaction();


                switch (position) {
                    case 0:

                        if ((fragment = getSupportFragmentManager().findFragmentByTag("home")) == null) {

                            fragment = Fragment.instantiate(getApplicationContext(), FragmentHome.class.getName());
                            fragment.setRetainInstance(true);
                            transaction.replace(R.id.main_content, fragment, "home");
                        } else {
                            transaction.attach(fragment);
                        }

                        break;
                    case 1:

                        if ((fragment = getSupportFragmentManager().findFragmentByTag("playlist")) == null) {
                            fragment = Fragment.instantiate(getApplicationContext(), FragmentPlaylist.class.getName());
                            fragment.setRetainInstance(true);
                            transaction.replace(R.id.main_content, fragment, "playlist");
                        } else {
                            transaction.attach(fragment);
                        }


                        break;
                    case 2:

                        if ((fragment = getSupportFragmentManager().findFragmentByTag("offline")) == null) {
                            fragment = Fragment.instantiate(getApplicationContext(), FragmentOffline.class.getName());
                            fragment.setRetainInstance(true);
                            transaction.replace(R.id.main_content, fragment, "offline");
                        } else {
                            transaction.attach(fragment);
                        }

                        break;

                }

                transaction.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        };

        navRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_navigation);
        catRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_categories);

        navRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new NavigationRecyclerViewAdapter(this, listItemClick);

        navRecyclerView.setAdapter(adapter);

        profilePicture = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageview_navigationdrawer_profile);
        profileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textview_navigationdrawer_name);
        profileEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textview_navigationdrawer_email);
        settings = (TextView) findViewById(R.id.textview_settings);
        settingsRelative = (RelativeLayout) findViewById(R.id.settings_relative);


        Glide.with(this).load(Prefs.with(this).getString("picture", "")).transform(new CircleTransform(this)).into(profilePicture);
        profileName.setText(Prefs.with(this).getString("name", null));
        profileEmail.setText(Prefs.with(this).getString("email", null));

        profileName.setTypeface(PocastApp.robotoLight);
        profileEmail.setTypeface(PocastApp.robotoLight);
        settings.setTypeface(PocastApp.robotoRegular);

        initCategories();


    }

    private void initCategories() {

        categoryTitle = (TextView) findViewById(R.id.textview_categories);
        categoryTitle.setTypeface(PocastApp.robotoLight);
        catRecyclerView.setLayoutManager(new MyLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        categories.add(new CategoryModel("Arts", 1301));
        categories.add(new CategoryModel("Comdey", 1303));
        categories.add(new CategoryModel("Education", 1304));
        categories.add(new CategoryModel("Kids & Family", 1305));
        categories.add(new CategoryModel("Health", 1307));
        categories.add(new CategoryModel("TV & Film", 1309));
        categories.add(new CategoryModel("Music", 1310));
        categories.add(new CategoryModel("News & Politics", 1311));
        categories.add(new CategoryModel("Religion & Spirituality", 1314));
        categories.add(new CategoryModel("Science & Medicine", 1315));
        categories.add(new CategoryModel("Sports & Recreation", 1316));
        categories.add(new CategoryModel("Technology", 1318));
        categories.add(new CategoryModel("Business", 1321));
        categories.add(new CategoryModel("Games & Hobbies", 1323));
        categories.add(new CategoryModel("Society & Culture", 1324));
        categories.add(new CategoryModel("Government & Organizations", 1325));

        categoriesItemClick =  new ListItemClick() {
            @Override
            public void clicked(int position) {

            }
        };

        categoryAdapter =  new CategoryAdapter(categories, categoriesItemClick);
        catRecyclerView.setAdapter(categoryAdapter);



    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);


        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return true;
    }
}
