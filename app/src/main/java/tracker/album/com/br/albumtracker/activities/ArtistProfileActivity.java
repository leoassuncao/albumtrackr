package tracker.album.com.br.albumtracker.activities;

import android.content.ContentValues;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tracker.album.com.br.albumtracker.adapters.ReleasedAlbunsAdapter;
import tracker.album.com.br.albumtracker.data.ArtistsContract;
import tracker.album.com.br.albumtracker.network.ArtistsService;
import tracker.album.com.br.albumtracker.network.ServiceApi;
import tracker.album.com.br.albumtracker.pojo.Artist;
import tracker.album.com.br.albumtracker.pojo.ArtistImage;
import tracker.album.com.br.albumtracker.pojo.ArtistsLastFm;
import tracker.album.com.br.albumtracker.pojo.ArtistsReleases;
import tracker.album.com.br.albumtracker.pojo.ReleaseGroup;
import tracker.album.com.br.albumtracker.R;

import static android.view.View.VISIBLE;

/**
 * Created by Leonardo Assunção on 24/04/2016.
 */
public class ArtistProfileActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private TextView mArtist_name;
    private FloatingActionButton mFab;
    private ReleasedAlbunsAdapter mAdapter;
    private TextView album_name;
    private TextView release_date;
    private TextView release_type;
    private String artistId;
    private ImageView artistHeaderImg;

    private LinearLayout mNoAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_artist_profile);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mArtist_name = (TextView) findViewById(R.id.artist_name);
        album_name = (TextView) findViewById(R.id.album_name);
        release_date = (TextView) findViewById(R.id.release_date);
        release_type = (TextView) findViewById(R.id.release_type);
        artistHeaderImg = (ImageView) findViewById(R.id.header_pic_artist);
        mNoAlbum = (LinearLayout) findViewById(R.id.artist_no_albuns);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Bundle extras = getIntent().getExtras();
        final Artist artist = extras.getParcelable("artistDetails");
        mArtist_name.setText(artist.getName());
        artistId = artist.getId();
        Log.v("teste", "fora do banco" + artist.getType());

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        if (!checkIfArtistIsInDb(artistId)) {
            changeFabOnUnfollow();
            Log.v("teste", "fora do banco");
        } else {
            changeFabOnFollow();
            Log.v("teste", "dentro do banco");
        }

        mFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!checkIfArtistIsInDb(artistId)) {
                    changeFabOnFollow();
                    saveArtistInDb(artist);
                    saveArtistInDb2(artist);
                    Log.v("teste", "adicionado no db");
                } else {
                    changeFabOnUnfollow();
                    deleteArtistFromDb(artist);
                    Log.v("teste", "deletado no db");
                }
            }
        });

        initViews();

        ServiceApi service = ArtistsService.getApiImage();
        final Call<ArtistsLastFm> artist1 = service.getArtistImage("artist.getinfo", artistId ,"966f26ded204772262fdb5bf66767c4b", "json" );

        artist1.enqueue(new Callback<ArtistsLastFm>() {
            @Override
            public void onResponse(Call<ArtistsLastFm> call, Response<ArtistsLastFm> response) {
                Integer statusCode = response.code();
                Log.v("status code: ", statusCode.toString());
                final ArtistsLastFm artists = response.body();
                ArrayList<ArtistImage> images = new ArrayList<>();
                images = artists.getArtist().getImage();

                Picasso.with(artistHeaderImg.getContext())
                        .load(images.get(3).getText())
                        .placeholder(R.drawable.acdc)
                        .into(artistHeaderImg);
            }

            @Override
            public void onFailure(Call<ArtistsLastFm> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
            }
        });
    }

    private void changeFabOnUnfollow() {
        mFab.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        mFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));

    }

    private void changeFabOnFollow() {
        mFab.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
        mFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryLighter)));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
        }
        finish();

        return super.onOptionsItemSelected(item);

    }


    public boolean checkIfArtistIsInDb(String artistId) {

        Cursor cursor = getContentResolver().query(
                ArtistsContract.ArtistsEntry.CONTENT_URI, null, ArtistsContract.ArtistsEntry.COLUMN_ARTIST_ID + "='" + artistId + "'", null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String artistIdDb = cursor.getString(cursor.getColumnIndex(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_ID));
            if (artistIdDb.equals(artistId)) {
                return true;
            } else {
                return false;
            }

        }
        if (cursor != null) {
            cursor.close();
        }
        return false;
    }

    private void saveArtistInDb(Artist artist) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_ID, artist.getId());
        contentValues.put(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_NAME, artist.getName());
        if(artist.getType() != null) {
            contentValues.put(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_STYLE, artist.getType());
        } else {
            artist.setType("No Type Defined");
            contentValues.put(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_STYLE, artist.getType());
        }
        if (artist.getCountry() != null){
            contentValues.put(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_COUNTRY, artist.getCountry());
        } else {
            artist.setCountry("US");
            contentValues.put(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_COUNTRY, artist.getCountry());
        }

        getContentResolver().insert(ArtistsContract.ArtistsEntry.CONTENT_URI, contentValues);
    }

    private void saveArtistInDb2(Artist artist) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ArtistsContract.AlbunsEntry.COLUMN_ARTIST_ID, artist.getId());
        contentValues.put(ArtistsContract.AlbunsEntry.COLUMN_ALBUM_NAME, artist.getName());
        contentValues.put(ArtistsContract.AlbunsEntry.COLUMN_RELEASE_DATE, artist.getType());
        contentValues.put(ArtistsContract.AlbunsEntry.COLUMN_ALBUM_ID, artist.getId());

        getContentResolver().insert(ArtistsContract.AlbunsEntry.CONTENT_URI, contentValues);
    }

    private void deleteArtistFromDb(Artist artist) {
        String selection = ArtistsContract.ArtistsEntry.COLUMN_ARTIST_ID + "=?";
        String[] selectionArgs = {String.valueOf(artist.getId())};
        getContentResolver().delete(ArtistsContract.ArtistsEntry.CONTENT_URI, selection, selectionArgs);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.album_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        loadArtistAlbuns(artistId);
}
    private void loadArtistAlbuns(String artistId) {

        ServiceApi service = ArtistsService.getApi();
        final Call<ArtistsReleases> artist = service.getReleaseAlbuns(artistId, "json");

        artist.enqueue(new Callback<ArtistsReleases>() {
            @Override
            public void onResponse(Call<ArtistsReleases> call, Response<ArtistsReleases> response) {
                Integer statusCode = response.code();
                Log.v("status code: ", statusCode.toString());

                final ArtistsReleases artists = response.body();
                ArrayList<ReleaseGroup> albuns = new ArrayList<>();
                albuns = artists.getReleaseGroups();
                //TODO sort recycler view by date
                if (!(albuns.isEmpty())) {
                    mAdapter = new ReleasedAlbunsAdapter(albuns);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    mNoAlbum.setVisibility(VISIBLE);
                }
                            }

            @Override
            public void onFailure(Call<ArtistsReleases> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
            }
        });

    }

}