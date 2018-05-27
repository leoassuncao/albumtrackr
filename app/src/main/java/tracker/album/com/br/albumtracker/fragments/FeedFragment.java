package tracker.album.com.br.albumtracker.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import tracker.album.com.br.albumtracker.adapters.FeedCardAdapter;
import tracker.album.com.br.albumtracker.adapters.FollowedAdapter;
import tracker.album.com.br.albumtracker.data.FollowedCursorLoader;
import tracker.album.com.br.albumtracker.providers.FeedArtistProvider;

import tracker.album.com.br.albumtracker.R;



/**
 * Created by Leonardo Assunção on 05/04/2016.
 */
public class FeedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeLayout;
    private View mRootView;
    private Activity myActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mRootView = inflater.inflate(R.layout.fragment_feed, container, false);

        mSwipeLayout = (SwipeRefreshLayout) this.mRootView.findViewById(R.id.swipeContainer);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.colorAccent));

        return this.mRootView;
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(),"Refresh!",Toast.LENGTH_SHORT).show();
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.loadProfileArtists();
    }

    private void loadProfileArtists() {
        RecyclerView artist = (RecyclerView) this.mRootView.findViewById(R.id.feed_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.myActivity);
        artist.setLayoutManager(linearLayoutManager);
        FeedCardAdapter adapter = new FeedCardAdapter(FeedArtistProvider.provideArtistsList(), this.myActivity);
        artist.setAdapter(adapter);

    }



}