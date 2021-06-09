package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

//public class DetailNeighbourFragment extends ListFragment {
public class DetailNeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    //private List<Neighbour> mNeighbours
    private String msg;
    private RecyclerView mRecyclerView;
    /**
     * Create and return a new instance
     * @return @{@link DetailNeighbourFragment}
     */
    public static DetailNeighbourFragment newInstance(final String msg) {
        DetailNeighbourFragment fragment = new DetailNeighbourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        //getFragmentManager().beginTransaction().add(R.id.main_content, new PlaceholderFragment()).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_detail_neighbour, container, false);
        //Context context = view.getContext();
        View view = inflater.inflate(R.layout.activity_detail_neighbour, container,false);
        //mRecyclerView = (RecyclerView) view;
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //initList();
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
        //initList();
    }

    /**
     * Surchargee pour gérer clic sur élément liste si extend listfragment
     */
    /**@Override
    public void onListItemClick(ListView l, View v, int position, long id){
        String item = (String) getListAdapter().getItem(position);
        //DetailFragment fragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.DetailFragment);
    }*/

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
}
