package tracker.album.com.br.albumtracker.activities;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tracker.album.com.br.albumtracker.R;
import tracker.album.com.br.albumtracker.adapters.FollowedAdapter;
import tracker.album.com.br.albumtracker.adapters.SearchArtistAdapter;
import tracker.album.com.br.albumtracker.data.ArtistsContract;
import tracker.album.com.br.albumtracker.network.ArtistsService;
import tracker.album.com.br.albumtracker.network.ServiceApi;

import tracker.album.com.br.albumtracker.pojo.Artist;
import tracker.album.com.br.albumtracker.pojo.SearchArtists;

import static android.view.View.VISIBLE;


/**
 * Created by Leonardo Assunção on 08/04/2016.
 */
public class SearchActivity extends AppCompatActivity {
        private AdView mAdView;
        private EditText editText;
        private TextView mArtistName;
        private TextView mAlbumType;
        private Toolbar mToolbar;
        private ImageButton mButtonFollow;
        private RecyclerView mRecyclerView;
        private SearchArtistAdapter mAdapter;
        private LinearLayout mLayoutNotFound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        editText = (EditText) findViewById(R.id.edittext);
        mAdView = (AdView) findViewById(R.id.adView);
        mArtistName = (TextView) findViewById(R.id.fd_artist_name);
        mAlbumType = (TextView) findViewById(R.id.search_artist_type);
        mButtonFollow = (ImageButton) findViewById(R.id.favorite_button);
        mLayoutNotFound = (LinearLayout) findViewById(R.id.layout_not_found);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadArtist("");

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(getApplicationContext(),"Buscando: " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    loadArtist(editText.getText().toString());
                }
                return false;
            }});
    }

    public void loadArtist(String searchArtist) {

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_artist_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        ServiceApi service = ArtistsService.getApi();
        final Call<SearchArtists> artist = service.getSearchArtist("artist:" + searchArtist, "json");

        Log.v("url", ArtistsService.getApi().getSearchArtist("artist:" + searchArtist, "json").request().url().toString());
        artist.enqueue(new Callback<SearchArtists>() {
            @Override
            public void onResponse(Call<SearchArtists> call, Response<SearchArtists> response) {
                Integer statusCode = response.code();
                Log.v("status code: ", statusCode.toString());

                final SearchArtists artists = response.body();
                Log.v("teste", "artist:" + artists);
                ArrayList<Artist> artist = new ArrayList<>();

                if (artists != null) {
                    artist = artists.getArtists();
                    if(!(artist.isEmpty())) {
                        mLayoutNotFound.setVisibility(View.INVISIBLE);
                        mAdapter = new SearchArtistAdapter(artist, getApplicationContext());
                        mRecyclerView.setAdapter(mAdapter);
                        mRecyclerView.setVisibility(VISIBLE);
                    } else{
                        mLayoutNotFound.setVisibility(VISIBLE);
                        mRecyclerView.setVisibility(View.INVISIBLE);
                    }
                } else {
                    mLayoutNotFound.setVisibility(VISIBLE);
                    mRecyclerView.setVisibility(View.INVISIBLE);
                }
            }


            @Override
            public void onFailure(Call<SearchArtists> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(getApplicationContext(),"Buscando: " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                loadArtist(editText.getText().toString());
                return true;

            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}