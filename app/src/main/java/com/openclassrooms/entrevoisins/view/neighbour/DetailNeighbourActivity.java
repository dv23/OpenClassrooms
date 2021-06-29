package com.openclassrooms.entrevoisins.view.neighbour;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toolbar;
//import android.support.v7.widget.Toolbar;

import com.openclassrooms.entrevoisins.R;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    private long mIdl = 0;
    private String[] mIdStr;

    private TextView mNeighbourTextView;
    private TextView mNeighbourAboutTextView;
    private String mAvatarStringView;
    private long mnLong;
    private ImageView mAvatarImageView;

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

        mApiService = DI.getNeighbourApiService();
        //mNeighbours = mApiService.getNeighbours();
        btHAcceuil();
        Bundle extras = getIntent().getExtras();
        //System.out.println(" DetailNeighbour Bundle extras tostring : " + extras.toString());
        //System.out.println(" DetailNeighbour Bundle extras  : " + extras);
        //mId = Integer.parseInt(intent.getStringExtra(EXTRA_ID));
        if (extras != null) {
            //NeighbourAdapter = new NeighbourAdapter(DetailNeighbourActivity.this,IdVoisin, NeighbourAdapter.getItemId() );

            //mIdstr = getIntent().getSerializableExtra(EXTRA_ID);
            //mIdStr=extras.getString(EXTRA_ID);

            mIdl=getIntent().getLongExtra("EXTRA_ID",0);
            System.out.println(" Test mIdl  :" + mIdl);
            //mId = Integer.parseInt(intent.getStringExtra(EXTRA_ID));
        }
        else
            {
                System.out.println(" Test extra mId  :" + mIdl);
            }

        /**
        if (savedInstanceState != null) {
            mId = savedInstanceState.getInt(EXTRA_NID);
            //mId = savedInstanceState.getString(EXTRA_ID);
            //mId = Integer.parseInt(intent.getStringExtra(EXTRA_NID));
            System.out.println(" Test EXTRA NID  :" + mId);
            //System.out.println(" Test EXTRA NID+string   :" + EXTRA_NID.toString());
        }
        else {
            mId = 0;
        }
        System.out.println(" Test EXTRA NID  :" + mId);
        */

        //Neighbour neighbour = mNeighbours.get(getWallpaperDesiredMinimumWidth());

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
            //.apply(RequestOptions.fitCenterTransform()).into(mAvatarImageView);
            //mAvatarTextView.setText(avatarTxt);

        String nameTxt = mApiService.getNeighbours().get((int) mIdl-1).getName();
        //String nameTxt = intent.getStringExtra(EXTRA_NAME);
        System.out.println(" Test EXTRA NAME  :" + nameTxt);
        mNeighbourTextView = (TextView) findViewById(R.id.n_name);
        mNeighbourTextView.setText(nameTxt);

            //mNeighbourAboutTextView = (TextView) findViewById(R.id.n_aboutme);
            //mNeighbourAboutTextView.setText(aboutTxt);
        }

    /**
     * Select item
     */
    /**@OnClick(R.id.item_list_name)
    void vuNeighbour() {

    }*/

    public void btHAcceuil() {
        ImageButton btBackAccueil = (ImageButton) findViewById(R.id.btAcceuil);
        btBackAccueil.setImageResource(R.drawable.back);
        cmdMenu_clic(btBackAccueil, ListNeighbourActivity.class);
    }

    /**private void displayNeighbour(final Question question) {
        mNeighbourTextView.setText(question.getQuestion());
        mAnswerButton1.setText(question.getChoiceList().get(0));
        mAnswerButton2.setText(question.getChoiceList().get(1));
        mAnswerButton3.setText(question.getChoiceList().get(2));
        mAnswerButton4.setText(question.getChoiceList().get(3));*/

    public void cmdMenu_clic(ImageButton ib, final Class cls) {
        //final Intent it = new Intent(MainActivity.this,cls);
        //startActivity(it);
        ib.setOnClickListener(new ImageButton.OnClickListener() {
            //((ImageButton) findViewById(R.id.ImgBtnNormal).setOnClickListener(new ImageButton.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Intent it = new Intent(DetailNeighbourActivity.this, cls);
                startActivity(it);
            }
        });
    }

    }
