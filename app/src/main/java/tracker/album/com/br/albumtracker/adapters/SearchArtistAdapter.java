package tracker.album.com.br.albumtracker.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tracker.album.com.br.albumtracker.R;
import tracker.album.com.br.albumtracker.activities.ArtistProfileActivity;
import tracker.album.com.br.albumtracker.data.ArtistsContract;
import tracker.album.com.br.albumtracker.pojo.Artist;
import tracker.album.com.br.albumtracker.pojo.SearchArtists;

/**
 * Created by leonardo.filho on 25/05/2018.
 */

public class SearchArtistAdapter extends RecyclerView.Adapter<SearchArtistAdapter.ViewHolder> {


    private ArrayList<Artist> searchArtists;
    private String searchArtistName;
    private String searchArtistType;
    private String searchArtistId;
    private String searchArtistCountry;
    private Context context;


    public SearchArtistAdapter(ArrayList<Artist> searchArtists, Context context) {
        this.searchArtists = searchArtists;
        searchArtistName = searchArtists.get(0).getName();
        searchArtistType = searchArtists.get(0).getType();
        searchArtistId = searchArtists.get(0).getId();
        searchArtistCountry = searchArtists.get(0).getCountry();
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public SearchArtistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_artist_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchArtistAdapter.ViewHolder holder, int position) {

        holder.search_artist_name.setText(this.searchArtists.get(position).getName());
        holder.search_artist_type.setText(this.searchArtists.get(position).getType());

        searchArtistId = this.searchArtists.get(position).getId();
        if (!checkIfArtistIsInDb(searchArtistId)) {
            Log.v("teste", "fora");
            holder.favorite_button.setImageResource(R.drawable.ic_plus);
            holder.favorite_button.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent),
                            PorterDuff.Mode.MULTIPLY);
        } else {
            Log.v("teste", "dentro do db");
            holder.favorite_button.setImageResource(R.drawable.ic_check);
            holder.favorite_button.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryLighter),
                            PorterDuff.Mode.MULTIPLY);
        }

        holder.favorite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String clickSearchArtistId = searchArtists.get(position).getId();
                if (!checkIfArtistIsInDb(clickSearchArtistId)) {
                    Log.v("teste", "adicionado no db");
                    holder.favorite_button.setImageResource(R.drawable.ic_check);
                    holder.favorite_button.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryLighter),
                            PorterDuff.Mode.MULTIPLY);
                    saveArtistInDb(clickSearchArtistId);
                } else {
                    Log.v("teste", "deletado no db");
                    holder.favorite_button.setImageResource(R.drawable.ic_plus);
                    holder.favorite_button.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent),
                            PorterDuff.Mode.MULTIPLY);
                    deleteArtistFromDb(clickSearchArtistId);
                        }
            }
        });

        holder.mCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ArtistProfileActivity.class);
                Log.v("id", "id: " + searchArtists.get(position).getName());
                Artist artist = new Artist();
                artist.setName(searchArtists.get(position).getName());
                artist.setId(searchArtists.get(position).getId());
                artist.setCountry(searchArtists.get(position).getCountry());
                artist.setType(searchArtists.get(position).getType());
                intent.putExtra("artistDetails", artist);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return searchArtists != null ? searchArtists.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView search_artist_name;
        private TextView search_artist_type;
        private ImageView favorite_button;
        private CardView mCardview;


        public ViewHolder(View itemView) {
            super(itemView);

            this.search_artist_name = (TextView) itemView.findViewById(R.id.search_artist_name);
            this.search_artist_type = (TextView) itemView.findViewById(R.id.search_artist_type);
            this.favorite_button = (ImageView) itemView.findViewById(R.id.favorite_button);
            this.mCardview = (CardView) itemView.findViewById(R.id.search_artist_card_view);

        }
    }


    public boolean checkIfArtistIsInDb(String searchArtistId) {
        Cursor cursor = context.getContentResolver().query(
                ArtistsContract.ArtistsEntry.CONTENT_URI, null, ArtistsContract.ArtistsEntry.COLUMN_ARTIST_ID + "='" + searchArtistId + "'", null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String artistIdDb = cursor.getString(cursor.getColumnIndex(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_ID));
            if (artistIdDb.equals(searchArtistId)) {
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

    private void saveArtistInDb(String clickSearchArtistId) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_ID, clickSearchArtistId);
        contentValues.put(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_NAME, searchArtistName);

        if(searchArtistType != null) {
            contentValues.put(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_STYLE, searchArtistType);
        } else {
            this.searchArtistType = "No Type";
            contentValues.put(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_STYLE, searchArtistType);
        }
        if (searchArtistCountry != null) {
            contentValues.put(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_COUNTRY, searchArtistCountry);
        } else {
            this.searchArtistCountry = "US";
        contentValues.put(ArtistsContract.ArtistsEntry.COLUMN_ARTIST_COUNTRY, searchArtistCountry);}

        context.getContentResolver().insert(ArtistsContract.ArtistsEntry.CONTENT_URI, contentValues);
    }



    private void deleteArtistFromDb(String clickSearchArtistId) {
        Log.v("teste1", "artistid: " + clickSearchArtistId);
        String selection = ArtistsContract.ArtistsEntry.COLUMN_ARTIST_ID + "=?";
        String[] selectionArgs = {clickSearchArtistId};
        context.getContentResolver().delete(ArtistsContract.ArtistsEntry.CONTENT_URI, selection, selectionArgs);
    }

}

