package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private NeighbourApiService mApiService;
    public static final String sFAVORITE_NAME = "FAVORITE_NAME";
    private List<Neighbour> mFavNeighbours;
    private String msg;
    private RecyclerView mRecyclerViewFavorites;

    /**
     * Create and return a new instance
     * @return @{@link FavoriteFragment}
     */
    //public static FavoriteFragment newInstance(final String name) {
    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        System.out.println(" onCreate Favorit Neighbour :" );
        /**if (getArguments() != null) {
            msg = getArguments().getString(sFAVORITE_NAME);
            //System.out.println(" detail N fra  : " + msg.toString());
        }*/
        //getFragmentManager().beginTransaction().add(R.id.main_content, new PlaceholderFragment()).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup VPviewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, VPviewGroup, false);
        Context context = view.getContext();
        mRecyclerViewFavorites = (RecyclerView) view;
        mRecyclerViewFavorites.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerViewFavorites.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    /**
     * Init the List of neighbours Liste liée a l'adapter
     */
    private void initList() {
        mFavNeighbours = mApiService.getFavoriteNeighbours();
        System.out.println(" init List Favorit Neighbour :" + mFavNeighbours);
        mRecyclerViewFavorites.setAdapter(new MyNeighbourRecyclerViewAdapter(mFavNeighbours));
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

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * Surchargee pour gérer clic sur élément liste si extend fragment
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
        //String item = (String) getListAdapter().getItem(position);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

}
