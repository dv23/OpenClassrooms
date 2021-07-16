package com.openclassrooms.entrevoisins.view.neighbour;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.repositories.NeighbourRepository;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.FavoriteFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import java.util.List;


import static android.nfc.NfcAdapter.EXTRA_ID;

public class DetailNeighbourActivity extends AppCompatActivity {

    // UI Components
    /**@BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.container_detail)
    ViewPager nViewPager;

    @BindView(R.id.avatar)
    ImageView avatar;*/

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_AVATAR = "avatar";
    public static final String EXTRA_ABOUT = "about";

    public static final String BUNDLE_EXTRA_FAVORI = "BUNDLE_EXTRA_FAVORI";

    private long mIdl = 0;
    private String[] mIdStr;
    private int mFavori;
    private TextView mNeighbourTextView;
    private TextView mNeighbourAboutTextView;
    private String mAvatarStringView;
    private long mnLong;
    private ImageView mAvatarImageView;
    private View myFavorite;

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
        //mAvatarImageView.setImageURI(mAvatarImageView);
        //mAvatarImageView.setImageURI(Uri.parse(avatarTxt));

        // String avatarTxt = intent.getStringExtra(EXTRA_AVATAR);
        //mAvatarStringView = avatarTxt;
        mAvatarStringView = mApiService.getNeighbours().get((int) mIdl-1).getAvatarUrl();
        Glide.with(this).load(mAvatarStringView).placeholder(R.drawable.ic_account)
            //.apply(RequestOptions.circleCropTransform()).into(mAvatarImageView);
            //.apply(RequestOptions.centerInsideTransform()).into(mAvatarImageView);
            .apply(RequestOptions.noTransformation()).into(mAvatarImageView);

        String nameTxt = mApiService.getNeighbours().get((int) mIdl-1).getName();
        //String nameTxt = intent.getStringExtra(EXTRA_NAME);
        //System.out.println(" Test EXTRA NAME  :" + nameTxt);
        mNeighbourTextView = (TextView) findViewById(R.id.n_name);
        mNeighbourTextView.setText(nameTxt);

        //mNeighbourAboutTextView = (TextView) findViewById(R.id.n_aboutme);
        //mNeighbourAboutTextView.setText(aboutTxt);

        //getFragmentManager().beginTransaction().add(R.id.container, new FavoriteFragment()).addToBackStack(null).commit();
        }

    public void btHAcceuil() {
        ImageButton btBackAccueil = (ImageButton) findViewById(R.id.btAcceuil);
        btBackAccueil.setImageResource(R.drawable.back);
        btBack_click(btBackAccueil, ListNeighbourActivity.class);

    }

    /**private void displayNeighbour(final Question question) {
        mNeighbourTextView.setText(question.getQuestion());
        mAnswerButton1.setText(question.getChoiceList().get(0));
        mAnswerButton2.setText(question.getChoiceList().get(1));
        mAnswerButton3.setText(question.getChoiceList().get(2));
        mAnswerButton4.setText(question.getChoiceList().get(3));*/

    public void btBack_click(ImageButton ibc, final Class cls) {
        //final Intent it = new Intent(MainActivity.this,cls);
        //startActivity(it);
        ibc.setOnClickListener(new ImageButton.OnClickListener() {
            //((ImageButton) findViewById(R.id.ImgBtnNormal).setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NeighbourFavori = new Intent(DetailNeighbourActivity.this, cls);
                startActivity(NeighbourFavori);
                // End the activity
                //Intent intent = new Intent();

                NeighbourFavori.putExtra(BUNDLE_EXTRA_FAVORI, mFavori);
                System.out.println(" NeighbourFragment Test EXTRA :" + NeighbourFavori.getExtras());
                setResult(RESULT_OK,NeighbourFavori);
            }
        });
    }

    public void btStarFavorit() {
        ImageButton btStarStrip = (ImageButton) findViewById(R.id.n_star);
        btStarStrip.setImageResource(R.drawable.ic_star_white_24dp);
        cmdFavoritStrip_click(btStarStrip, ListNeighbourActivity.class);
    }

    public void cmdFavoritStrip_click(ImageButton ibf, final Class cls) {
        //final Intent it = new Intent(MainActivity.this,cls);
        //startActivity(it);
        ibf.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
               //mApiService.addFavoriteNeighbour(mApiService.getNeighbours().get((int) (mIdl - 1)));
               Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT);
               mFavori = (int) mIdl;
               Log.d(DetailNeighbourActivity.class.getName(), "User tries to add favorit item.");
               System.out.println(" Detail Neighbour Test favorit click  :" + mFavori);
               //getSupportFragmentManager().findFragmentById(R.id.pager_title_strip);
            }
        });
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
