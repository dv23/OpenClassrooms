package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.Placeholder;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toolbar;
//import android.support.v7.widget.Toolbar;



import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_AVATAR = "avatar";
    public static final String EXTRA_ABOUT = "about";

    private TextView mNeighbourTextView;
    private TextView mNeighbourAboutTextView;
    private String mAvatarStringView;
    private TextView mAvatarTextView;
    private ImageView mAvatarImageView;

    //private NeighbourAdapter NeighbourAdapter;
    //DetailNeighbourPagerAdapter nPagerAdapter;
    //NeighbourAdapter nAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail_neighbour);
        setContentView(R.layout.activity_neighbour);
        //NeighbourAdapter = (com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourAdapter) DI.getNewInstanceApiService();
        //NeighbourAdapter = DI.getNewInstanceApiService();

        //nAdapter = new NeighbourAdapter(EXTRA_NAME);
        /**nPagerAdapter = new DetailNeighbourPagerAdapter(getSupportFragmentManager());
        nViewPager.setAdapter(nPagerAdapter);
        mTabLayout.setupWithViewPager(nViewPager);
        nViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(nViewPager));*/

        // Ajout fragment dynamique
        //getFragmentManager().beginTransaction().add(R.id.container_detail, new DetailNeighbourFragment()).commit();
        //getFragmentManager().beginTransaction().add(R.id.main_content, new Placeholder()).commit();
        //ButterKnife.bind(this);
        //btHAcceuil();

        Bundle extras = getIntent().getExtras();
        System.out.println(" Test extra  : " + extras.toString());
        if (extras != null) {
            //NeighbourAdapter = new NeighbourAdapter(DetailNeighbourActivity.this,IdVoisin, NeighbourAdapter.getItemId() );
            Intent intent = getIntent();
            //startActivity(intent);
            String nameTxt = intent.getStringExtra(EXTRA_NAME);
            //String aboutTxt = intent.getStringExtra(EXTRA_ABOUT);
            //System.out.println(" Test getString about  : " + aboutTxt.toString());

            //System.out.println(" Test getString extra  : " + nameTxt.toString());
            String avatarTxt = intent.getStringExtra(EXTRA_AVATAR);

            mAvatarImageView = (ImageView) findViewById(R.id.n_avatar);
            //mAvatarImageView.setImageURI(mAvatarImageView);
            //mAvatarImageView.setImageURI(Uri.parse(avatarTxt));

            mAvatarStringView = avatarTxt;
            Glide.with(this).load(mAvatarStringView).placeholder(R.drawable.ic_account)
            //.apply(RequestOptions.circleCropTransform()).into(mAvatarImageView);
            //.apply(RequestOptions.centerInsideTransform()).into(mAvatarImageView);
            .apply(RequestOptions.noTransformation()).into(mAvatarImageView);
            //.apply(RequestOptions.fitCenterTransform()).into(mAvatarImageView);
            //mAvatarTextView = (TextView) findViewById(R.id.item_avatar);
            //mAvatarTextView.setText(avatarTxt);

            mNeighbourTextView = (TextView) findViewById(R.id.n_name);
            mNeighbourTextView.setText(nameTxt);
            //mNeighbourTextView.setTag(nameTxt);

            //mNeighbourAboutTextView = (TextView) findViewById(R.id.n_aboutme);
            //mNeighbourAboutTextView.setText(aboutTxt);

        }
        //mNeighbourTextView.setText(nameTxt);
    }

    /**
     * Select item
     */
    /**@OnClick(R.id.item_list_name)
    void vuNeighbour() {

    }*/

    public void btHAcceuil() {
        ImageButton btHisAccueil = (ImageButton) findViewById(R.id.btHisAcceuil);
        btHisAccueil.setImageResource(R.drawable.accueil);
        //cmdMenu_clic(btHisAccueil, ListNeighbourActivity.class);
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
