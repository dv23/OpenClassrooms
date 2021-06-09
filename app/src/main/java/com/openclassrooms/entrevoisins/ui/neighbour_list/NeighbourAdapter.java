package com.openclassrooms.entrevoisins.ui.neighbour_list;

//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.*;

//public class NeighbourPagerAdapter extends FragmentPagerAdapter {
public class NeighbourAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<Neighbour> LesVoisins;
    /**public NeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }*/

    public NeighbourAdapter(Context context, String idvoisin) {
        this.inflater = LayoutInflater.from(context);
        //super(fm);
        }

        /**
         * getItem is called to instantiate the fragment for the given page.
         * @param position
         * @return
         */
        /**@Override
        public Fragment getItem ( int position){
            //return DetailNeighbourFragment.newInstance();
            //return ;
        }*/

    private class ViewHolder {
        ImageView item_avatar;
        TextView item_name;
        //TextView txtListNiveau;
        ImageButton item_ad_star_button ;
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 1;
    }

    // create interface for callback
    public interface Listener {
        void onClickDeleteButton(int position);
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        // holder est un objet de la petite classe
        ViewHolder holder ;

        if (view == null) {
            holder = new ViewHolder() ;
            // view est construit à partir de la structure de la ligne
            //inflater.inflate(R.layout.activity_detail_neighbour, null) ; avatar
            view = inflater.inflate(R.layout.fragment_detail_neighbour, null) ;
            // chaque propriété de holder récupère un des objets graphiques de la ligne
            holder.item_avatar = (ImageView) view.findViewById(R.id.item_avatar) ;
            //holder.item_name = (TextView)view.findViewById(R.id.item_name) ; name
            view = inflater.inflate(R.layout.fragment_name_neighbour, null) ;

            //holder.item_ad_star_button = (ImageButton)view.findViewById(R.id.item_ad_star_button) ;
            //holder.imgListSuppr.setImageResource(R.drawable.suppr);
            // création d'une nouvelle ligne dans view
            view.setTag(holder) ;
        }else{
            // si view est déja rempli, holder récupère le contenu
            holder = (ViewHolder)view.getTag();
        }
        return view ;
    }

    // FOR COMMUNICATION declaration callback
    //private final Listener callback;
}