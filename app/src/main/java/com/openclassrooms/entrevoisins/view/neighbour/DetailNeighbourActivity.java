package com.openclassrooms.entrevoisins.view.neighbour;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;
//import android.support.v7.widget.Toolbar;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.repositories.NeighbourRepository;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.repositories.NeighbourRepository;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.FavoriteFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_list.FavoritePagerAdapter;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourAdapter;

import java.util.List;


import static android.nfc.NfcAdapter.EXTRA_ID;
import static android.support.v7.app.AlertDialog.*;

public class DetailNeighbourActivity extends AppCompatActivity {

    // UI Components
    /**@BindView(R.id.tabs)
    TabLayout mTabLayout;*/

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_AVATAR = "avatar";
    public static final String EXTRA_ABOUT = "about";

    public static final String BUNDLE_EXTRA_FAVORI = "BUNDLE_EXTRA_FAVORI";

    private long mIdl = 0;

    private int mFavori;
    private TextView mNeighbourTextView;
    private TextView mNeighbourAboutTextView;
    private String mAvatarStringView;

    private ImageView mAvatarImageView;

    ImageButton fav;

    // FOR DATA ---
    private FavoritePagerAdapter adapter;
    /**public static Intent navigate(Context context, Book book) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_BOOK, book);

        return intent;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail_neighbour);
        setContentView(R.layout.activity_neighbour);
        mFavori = 0;
        mApiService = DI.getNeighbourApiService();
        //mNeighbours = mApiService.getNeighbours();
        btHAcceuil();
        Bundle extras = getIntent().getExtras();
        //System.out.println(" DetailNeighbour Bundle extras  : " + extras);
        //mId = Integer.parseInt(intent.getStringExtra(EXTRA_ID));
        if (extras != null) {
            //NeighbourAdapter = new NeighbourAdapter(DetailNeighbourActivity.this,IdVoisin, NeighbourAdapter.getItemId() );
            mIdl=getIntent().getLongExtra("EXTRA_ID",0);
            System.out.println(" Test mIdl  :" + mIdl);
            //mId = Integer.parseInt(intent.getStringExtra(EXTRA_ID));
        }
        else
            {
                System.out.println(" Test extra mId  :" + mIdl);
            }
        //Neighbour neighbour = mNeighbours.get(getWallpaperDesiredMinimumWidth());
        if (savedInstanceState != null) {
            mFavori = savedInstanceState.getInt(BUNDLE_EXTRA_FAVORI);
            //mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mFavori = 0;
            //mNumberOfQuestions = 4;
        }
        //String nT = mApiService.getNeighbours().get(idTxt);
        //String aboutTxt = intent.getStringExtra(EXTRA_ABOUT);

        mAvatarImageView = (ImageView) findViewById(R.id.n_avatar);
        mAvatarStringView = mApiService.getNeighbours().get((int) mIdl-1).getAvatarUrl();
        Glide.with(this).load(mAvatarStringView).placeholder(R.drawable.ic_account)
            //.apply(RequestOptions.circleCropTransform()).into(mAvatarImageView);
            //.apply(RequestOptions.centerInsideTransform()).into(mAvatarImageView);
            .apply(RequestOptions.noTransformation()).into(mAvatarImageView);

        String nameTxt = mApiService.getNeighbours().get((int) mIdl-1).getName();
        //System.out.println(" Test EXTRA NAME  :" + nameTxt);
        mNeighbourTextView = (TextView) findViewById(R.id.n_name);
        mNeighbourTextView.setText(nameTxt);

        //configureFav();
        //getFragmentManager().beginTransaction().add(R.id.container, new FavoriteFragment()).addToBackStack(null).commit();
        }

    public void btHAcceuil() {
        ImageButton btBackAccueil = (ImageButton) findViewById(R.id.btAcceuil);
        btBackAccueil.setImageResource(R.drawable.back);
        btBack_click(btBackAccueil, ListNeighbourActivity.class);
    }

    public void btBack_click(ImageButton ibc, final Class cls) {
        //final Intent it = new Intent(MainActivity.this,cls);
        //startActivity(it);
        ibc.setOnClickListener(new ImageButton.OnClickListener() {
            //((ImageButton) findViewById(R.id.ImgBtnNormal).setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                // End the activity
                //Intent intent = new Intent();
                Intent NeighbourFavori = new Intent(DetailNeighbourActivity.this, cls);
                Log.d(DetailNeighbourActivity.class.getName(), "User tries to return with favorit item.");
                startActivity(NeighbourFavori);
                NeighbourFavori.putExtra(BUNDLE_EXTRA_FAVORI, mFavori);
                System.out.println(" Detail Neighbour aCTIVITY ONCLICK  Test EXTRA :" + NeighbourFavori.getExtras());
                setResult(RESULT_OK,NeighbourFavori);
            }
        });
    }

    public void btStarFavorit() {
        ImageButton btStarStrip = (ImageButton) findViewById(R.id.n_star);
        btStarStrip.setImageResource(R.drawable.ic_star_white_24dp);
        //cmdFavoritStrip_click(btStarStrip, ListNeighbourActivity.class);
        cmdFavoritStrip_click(btStarStrip);
    }

    public void cmdFavoritStrip_click(ImageButton ibf) {
        //final Intent it = new Intent(MainActivity.this,cls);
        //startActivity(it);
        ibf.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
            //mApiService.addFavoriteNeighbour(mApiService.getNeighbours().get((int) (mIdl - 1)));
            //Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT);
            Toast.makeText(DetailNeighbourActivity.this, "fav add !", Toast.LENGTH_SHORT).show();
            System.out.println("Detail Neighbour Test mfavori on click  :" + mIdl);
            mFavori = (int) mIdl;
            System.out.println(String.format("Detail Neighbour Test favorit click  :" + mFavori));
            //getSupportFragmentManager().findFragmentById(R.id.pager_title_strip);
            }
                });
    }

   private void configureFav() {
       fav = findViewById(R.id.n_star);
       fav.setOnClickListener(view -> {
           //getUserRepository().generateRandomUser();
           //NeighbourRepository.
           //System.out.println("ListUserActivity::configureFab()getUserRepository().getUsers :"+ getUserRepository().getUsers());
           loadData();
       });
   }
    private void loadData() {
        //System.out.println("ListUserActivity::loadData() "+ getUserRepository().getUsers());
        //adapter.updateList(getUserRepository().getUsers());
    }

    /** private void initListener() {
     myFavorite.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    if (!mApiService.getNeighbours().get((int) mIdl - 1).isFavorite()) {
    mApiService.addFavoriteNeighbour(mApiService.getNeighbours().get((int) (mIdl - 1)));
    Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT);
    System.out.println(" Test N pas favoris  :" + mApiService.getNeighbours().size());
    } else if(mApiService.getNeighbours().get((int) mIdl - 1).isFavorite()) {
    mApiService.deleteNeighbour(mApiService.getNeighbours().get((int) (mIdl-1)));
    Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT);
    System.out.println(" Test favorit N  :" + mApiService.getNeighbours().size());
    }
    }
    });
     }*/

    }
