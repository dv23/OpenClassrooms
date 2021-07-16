package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.repositories.NeighbourRepository;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.view.neighbour.DetailNeighbourActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.VP)
    ViewPager mViewPager;
    //@BindView(R.id.tabItem)
    //TabItem mTabItemNeighbours;
    //@BindView(R.id.tabItem2)
    //TabItem mTabItemFavorites;

    //FloatingActionButton fav;
    ImageButton fav;

    private NeighbourApiService mApiService;
    ListNeighbourPagerAdapter mPagerAdapter;
    FavoritePagerAdapter mFavAdapter;
    //private boolean favorite;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    //private Object NeighbourRepository;
    private NeighbourRepository userRepository;
    //NeighbourRepository NRepository = (com.openclassrooms.entrevoisins.repositories.NeighbourRepository) DI.getNeighbourApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);

        //userRepository= DI.getNewInstanceApiService();
        setSupportActionBar(mToolbar);
        // configure fragment dynamique
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //mFavAdapter = new FavoritePagerAdapter(getSupportFragmentManager());
        //mTabItem2.addOnLayoutChangeListener();
        //mTabItem2.setAdapater(mFavAdapter);
        //FavorisTabItem();
        configureFav();
        mApiService = DI.getNeighbourApiService();
        //NeighbourRepository NRepository = (com.openclassrooms.entrevoisins.repositories.NeighbourRepository) DI.getNeighbourApiService();
        initSelection();

    }

    private void configureFav() {
        fav = findViewById(R.id.n_star);
        fav.setOnClickListener(view -> {
            //getUserRepository().generateRandomUser();
            //System.out.println("ListUserActivity::configureFab()getUserRepository().getUsers :"+ getUserRepository().getUsers());
            loadData();
        });
    }

    private void loadData() {
        //System.out.println("ListUserActivity::loadData() "+ getUserRepository().getUsers());
        //adapter.updateList(getUserRepository().getUsers());
    }


    public void initSelection() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // assuming users is at tab index 0 and favorites is at tab Index 1
                if(tab.getPosition() == 1) {
                    selectFavorites();
                } else {
                    //selectUsers();
                }
            }

            private void selectFavorites() {
                // whichever adapter you were using to show favorites
                //FavoritePagerAdapter mFavAdapter = new FavoritePagerAdapter(mFavorites, context);
                //mRecyclerView.setAdapter(mFavAdapter);
                Toast.makeText(ListNeighbourActivity.this, "select Tab Favoris", Toast.LENGTH_LONG).show();
                //mFavAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        // if you wish to select the users tab at 0 by default
        Objects.requireNonNull(mTabLayout.getTabAt(0)).select();
    }


    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);

    }

    /**@OnClick(R.id.tabItem2)
    void Favorites() {
        System.out.println(" put N fav  : ");
        Toast.makeText(ListNeighbourActivity.this, "TabFAvoris", Toast.LENGTH_SHORT).show();
    }*/

    /**
     * Ecoute sur les tabitem
     * Affiche juste un message graphique temporaire pour confirmer le choix
     */
    public void FavorisTabItem(){
        TabItem tbFav = (TabItem) findViewById(R.id.tabItem2);
        //FavorisTabClick(tbFav, ListNeighbourActivity.class);
    }

    /**
     * Ecoute du bouton tabitem
     * -
     * - s'il y a bien eu click :
     * - affichage de l'onglet (favoris)
     * @param tbFav
     * @param cls
     */
    private void FavorisTabClick(TabItem tbFav, final Class cls) {
        //((TabItem) findViewById(R.id.tabItem2)).setOnClickListener(new TabItem().OnClickListener {
            //public void onCheckedChanged(, int checkedId) {
            //}
        //}
        Toast.makeText(ListNeighbourActivity.this, "Favoris Tab click", Toast.LENGTH_LONG).show();

        getFragmentManager().findFragmentById(R.id.VP);
        //mApiService.getFavoriteNeighbours();
        //NRepository.getFavoriteNeighbours();
        //System.out.println(" Test favorit tab click  :" + NRepository.getNeighbours().size());
        /**tbFav.setOnClickListener(new ImageButton.OnClickListener() {
        //tbFav.setOnClickListener(new TabItem().callOnClick() {
        @Override
        public void onClick(View v) {
                Toast.makeText(ListNeighbourActivity.this, "Favoris ", Toast.LENGTH_SHORT).show();
            }
        }
         );*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int favorite = data.getIntExtra(DetailNeighbourActivity.BUNDLE_EXTRA_FAVORI, 0);
            System.out.println(" on Activity result Neighbour  :" + favorite);
            //NRepository.getFavoriteNeighbours().clear();
            //NRepository.addFavoriteNeighbour(mApiService.getNeighbours().get(favorite));
            //NRepository.getNeighbours().get(favorite);

            //NRepository.addFavoriteNeighbour(mApiService.getNeighbours().get((int)(mIdl - 1)));
        }
    }

}