package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Placeholder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourActivity extends AppCompatActivity {

    // UI Components
    /**@BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    */
    @BindView(R.id.container_detail)
    ViewPager mViewPager;

    private TextView mNeighbourTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);

        ButterKnife.bind(this);
/**
        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
*/
        // Ajout fragment dynamique
        //getFragmentManager().beginTransaction().add(R.id.container_detail, new DetailNeighbourFragment()).commit();
        //getFragmentManager().beginTransaction().add(R.id.main_content, new Placeholder()).commit();
        getFragmentManager().findFragmentById(R.id.item_detail_name);

        btHAcceuil();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String StrVoisin = extras.getString("value");
            ajouteUneLigneVoisin(StrVoisin);
        }

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
        cmdMenu_clic(btHisAccueil, ListNeighbourActivity.class);
    }

    private void ajouteUneLigneVoisin(String voisin) {
        NeighbourAdapter NeighbourAdapter;
        NeighbourAdapter = new NeighbourAdapter(DetailNeighbourActivity.this, voisin);

        ViewPager VDetailVoisin = (ViewPager) findViewById(R.id.container_detail);
        //VDetailVoisin.setAdapter(NeighbourAdapter);
        //VDetailVoisin.setCurrentItem(voisin);

        /** // construction de la ligne avec la date et l'img (2 zones de texte dans un layout)
        LinearLayout laLigne = new LinearLayout(this); // créaton ligne
        //((LinearLayout) findViewById(R.id.llvListeHisto)).addView(laLigne); // ajout ligne dans layout
        laLigne.setOrientation(LinearLayout.HORIZONTAL); // orientation du contenu de la ligne
        TextView leTexte = new TextView(this); // zone de texte à insérer dans la ligne
        laLigne.addView(leTexte); // ajout de la zone de texte dans la ligne
        // insertion des informations dans les zones de texte de la ligne
        leTexte.setText(MesOutils.convertDateToString(profil.getDateMesure(), "dd/MM/yy HH:mm:ss") + " : IMG = "
                + String.format("%.01f", profil.getImg()));
        */
    }
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
