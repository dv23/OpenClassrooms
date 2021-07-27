package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.PagerTitleStrip;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.events.ItemClickSupport;
import com.openclassrooms.entrevoisins.view.neighbour.DetailNeighbourActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemSelected;

import static android.app.Activity.RESULT_OK;

public class  NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    //private List<Neighbour> mFavorites;
    private RecyclerView mRecyclerView;

    //private final String EXTRA_AVATAR = "avatar";
    //private final String EXTRA_ABOUT = "avatr";
    private static final int ACTIVITY_REQUEST_CODE = 42;
    private int mPosition;

    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        NeighbourFragment fragment = new NeighbourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        //mPosition = getArguments().getInt("position", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        // 2 - Calling the method that configuring click on RecyclerView
        this.configureOnClickRecyclerView();

        return view;
    }

    /**
     * Init the List of neighbours Liste liée a l'adapter
     */
    private void initList() {
        mNeighbours = mApiService.getNeighbours();
        //mFavorites = mApiService.getFavoriteNeighbours();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));
        // rcv ● élément fav
        //mRecyclerViewFavorites.setAdapter(new MyNeighbourRecyclerViewAdapter(mFavorites));
    }

    @Override
    public void onResume() {
        super.onResume();
        // isUserFavorite();
        initList();
        //initSelection();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    // -----------------
    // ACTION
    // -----------------
    //ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_neighbour_list)
    private void configureOnClickRecyclerView(){
        //ItemClickSupport.addTo(mPagerTitleStrip, R.layout.fragment_neighbour)
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_neighbour)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        // 1 - Get user from adapter
                Neighbour user = mNeighbours.get(position);
        // 2 - Show result in a Toast
                //DetailNeighbourFragment fragmentById = (DetailNeighbourFragment) getFragmentManager().findFragmentById(R.id.container_detail);
                Intent NeighbourActivity = new Intent(getActivity(), DetailNeighbourActivity.class);

                NeighbourActivity.putExtra("EXTRA_ID", user.getId());
                //System.out.println(" NeighbourFragment Test EXTRA :" + NeighbourActivity.getExtras());
                // exec activité detail
                startActivity(NeighbourActivity);
                //startActivityForResult(NeighbourActivity, ACTIVITY_REQUEST_CODE);
                    }
                });
    }

    @Subscribe
    public void onClickNeighbour(OnItemSelected event) {
        //mApiService.deleteNeighbour(event.neighbour);
        initList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
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
