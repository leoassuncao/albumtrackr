package tracker.album.com.br.albumtracker.adapters;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.neovisionaries.i18n.CountryCode;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tracker.album.com.br.albumtracker.R;
import tracker.album.com.br.albumtracker.activities.ArtistProfileActivity;
import tracker.album.com.br.albumtracker.data.ArtistsContract;
import tracker.album.com.br.albumtracker.network.ArtistsService;
import tracker.album.com.br.albumtracker.network.ServiceApi;
import tracker.album.com.br.albumtracker.pojo.Artist;
import tracker.album.com.br.albumtracker.pojo.ArtistImage;
import tracker.album.com.br.albumtracker.pojo.ArtistsLastFm;
import tracker.album.com.br.albumtracker.pojo.CoverArt;
import tracker.album.com.br.albumtracker.pojo.Images;

/**
 * Created by leonardo.filho on 16/05/2018.
 */

public class FollowedAdapter  extends RecyclerView.Adapter<FollowedAdapter.FollowedAdapterViewHolder> {

    private Cursor cursor;
    private TextView artist_name;
    private TextView artist_country;
    private TextView music_style;
    private TextView last_album;
    private TextView artist_type;
    public String id;

    public FollowedAdapter() {


    }

    public void changeCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }

    }

    public class FollowedAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ic_artist_type;
        ImageView artist_photo;


        public FollowedAdapterViewHolder(View itemView) {
            super(itemView);
            ic_artist_type = (ImageView) itemView.findViewById(R.id.library_ic_artist_type);
            artist_photo = (ImageView) itemView.findViewById(R.id.library_artist_photo);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ArtistProfileActivity.class);
            cursor.moveToPosition(getAdapterPosition());
            Artist artist = getArtistFromCursor();
            intent.putExtra("artistDetails", artist);
            Toast.makeText(view.getContext(), artist.getName(), Toast.LENGTH_SHORT).show();
            view.getContext().startActivity(intent);
        }

        @NonNull
        private Artist getArtistFromCursor() {
            Artist currentArtist = new Artist();
            currentArtist.setId(cursor.getString(cursor.getColumnIndex(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_ID)));
            currentArtist.setName(cursor.getString(cursor.getColumnIndex(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_NAME)));
            currentArtist.setCountry(cursor.getString(cursor.getColumnIndex(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_COUNTRY)));
            currentArtist.setType(cursor.getString(cursor.getColumnIndex(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_STYLE)));
            return currentArtist;
        }

    }


    @Override
    public FollowedAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.library_artists_card_view, parent, false);
        artist_name = (TextView) view.findViewById(R.id.library_artist_name);
        artist_country = (TextView) view.findViewById(R.id.library_artist_country);
        music_style = (TextView) view.findViewById(R.id.library_music_style);
        last_album = (TextView) view.findViewById(R.id.library_last_album);
        artist_type = (TextView) view.findViewById(R.id.library_artist_type);

        FollowedAdapterViewHolder viewHolder = new FollowedAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FollowedAdapterViewHolder holder, int position) {
        cursor.moveToPosition(position);
        artist_name.setText(cursor.getString(cursor.getColumnIndex(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_NAME)));
        artist_type.setText(cursor.getString(cursor.getColumnIndex(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_STYLE)));
        String ic_type = cursor.getString(cursor.getColumnIndex(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_STYLE));
        id = cursor.getString(cursor.getColumnIndex(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_ID));

        CountryCode cc = CountryCode.getByCode(cursor.getString(cursor.getColumnIndex(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_COUNTRY)));
        artist_country.setText(cc.getName());

        if (ic_type.equals("Group")) {
            Picasso.with(holder.ic_artist_type.getContext())
                    .load((R.drawable.ic_artist_type_group))
                    .placeholder(R.drawable.ic_artist_type_group)
                    .into(holder.ic_artist_type);
        } else if (ic_type.equals("Person")) {
            Picasso.with(holder.ic_artist_type.getContext())
                    .load((R.drawable.ic_artist_type_single))
                    .placeholder(R.drawable.ic_artist_type_single)
                    .into(holder.ic_artist_type);
        } else if (ic_type.equals("Orchestra")) {
            Picasso.with(holder.ic_artist_type.getContext())
                    .load((R.drawable.ic_guitar_acoustic))
                    .placeholder(R.drawable.ic_guitar_acoustic)
                    .into(holder.ic_artist_type);
        } else if (ic_type.equals("Choir")) {
            Picasso.with(holder.ic_artist_type.getContext())
                    .load((R.drawable.ic_artist_type_single))
                    .placeholder(R.drawable.ic_artist_type_single)
                    .into(holder.ic_artist_type);
        } else if (ic_type.equals("Character")) {
            Picasso.with(holder.ic_artist_type.getContext())
                    .load((R.drawable.ic_artist_type_single))
                    .placeholder(R.drawable.ic_artist_type_single)
                    .into(holder.ic_artist_type);
        } else {
            Picasso.with(holder.ic_artist_type.getContext())
                    .load((R.drawable.ic_artist_type_single))
                    .placeholder(R.drawable.ic_artist_type_single)
                    .into(holder.ic_artist_type);
        }

        loadImages();

        Picasso.with(holder.artist_photo.getContext())
                //http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&mbid=bfcc6d75-a6a5-4bc6-8282-47aec8531818&api_key=966f26ded204772262fdb5bf66767c4b&format=json
                .load(ArtistsService.getApiImage() + id)
                .placeholder(R.drawable.acdc)
                .into(holder.artist_photo);

    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    private void loadImages() {

        ServiceApi service = ArtistsService.getApiImage();
        final Call<ArtistsLastFm> artist = service.getArtistImage();

        artist.enqueue(new Callback<ArtistsLastFm>() {
            @Override
            public void onResponse(Call<ArtistsLastFm> call, Response<ArtistsLastFm> response) {
                Integer statusCode = response.code();
                Log.v("status code: ", statusCode.toString());
                final ArtistsLastFm artists = response.body();
                ArrayList<ArtistImage> img = new ArrayList<>();
                img = artists.getImage();
                Log.v("testem2", "artists: " + artists);

            }

            @Override
            public void onFailure(Call<ArtistsLastFm> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
            }
        });
    }
}

