package tracker.album.com.br.albumtracker.adapters;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tracker.album.com.br.albumtracker.data.ArtistsContract;
import tracker.album.com.br.albumtracker.handlers.ReleasedAlbunsList;
import tracker.album.com.br.albumtracker.R;
import tracker.album.com.br.albumtracker.network.ArtistsService;
import tracker.album.com.br.albumtracker.network.ServiceApi;
import tracker.album.com.br.albumtracker.pojo.AlbumImage;
import tracker.album.com.br.albumtracker.pojo.AlbumLastFm;
import tracker.album.com.br.albumtracker.pojo.Artist;
import tracker.album.com.br.albumtracker.pojo.ReleaseGroup;

/**
 * Created by Leonardo Assunção on 04/05/2016.
 */
public class ReleasedAlbunsAdapter extends RecyclerView.Adapter<ReleasedAlbunsAdapter.ViewHolder> {


    private ArrayList<ReleaseGroup> releaseGroups;
    private String albumName;
    private String releaseDate;
    private String releaseType;
    private String albumId;

    public ReleasedAlbunsAdapter() {}

    public ReleasedAlbunsAdapter(ArrayList<ReleaseGroup> releaseGroups) {
        this.releaseGroups = releaseGroups;
        albumName = releaseGroups.get(0).getTitle();
        releaseDate = releaseGroups.get(0).getFirstReleaseDate();
        releaseType = releaseGroups.get(0).getPrimaryType();
        albumId = releaseGroups.get(0).getId();
        notifyDataSetChanged();
    }

    @Override
    public ReleasedAlbunsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.released_albuns, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ReleasedAlbunsAdapter.ViewHolder holder, int position) {

        holder.album_name.setText(this.releaseGroups.get(position).getTitle());
        holder.release_date.setText(this.releaseGroups.get(position).getFirstReleaseDate());
        holder.release_type.setText(this.releaseGroups.get(position).getPrimaryType());
        String ic_type = this.releaseGroups.get(position).getPrimaryType();
        if(ic_type == null){
            Picasso.with(holder.ic_release_type.getContext())
                    .load((R.drawable.release_type_album))
                    .placeholder(R.drawable.release_type_album)
                    .into(holder.ic_release_type);
        }else {

            if (ic_type.equals("Album")) {
                Picasso.with(holder.ic_release_type.getContext())
                        .load((R.drawable.release_type_album))
                        .placeholder(R.drawable.release_type_album)
                        .into(holder.ic_release_type);
            } else if (ic_type.equals("Single")) {
                Picasso.with(holder.ic_release_type.getContext())
                        .load((R.drawable.release_type_single))
                        .placeholder(R.drawable.release_type_single)
                        .into(holder.ic_release_type);
            } else if (ic_type.equals("EP")) {
                Picasso.with(holder.ic_release_type.getContext())
                        .load((R.drawable.release_type_ep))
                        .placeholder(R.drawable.release_type_ep)
                        .into(holder.ic_release_type);
            } else {
                Picasso.with(holder.ic_release_type.getContext())
                        .load((R.drawable.release_type_other))
                        .placeholder(R.drawable.release_type_other)
                        .into(holder.ic_release_type);
            }
        }


        albumId = this.releaseGroups.get(position).getId();
        if(albumId == null) {
            Picasso.with(holder.album_photo.getContext())
                    .load(R.drawable.acdc)
                    .placeholder(R.drawable.acdc)
                    .into(holder.album_photo);
        } else {
            ServiceApi service = ArtistsService.getApiImage();
            final Call<AlbumLastFm> artist = service.getAlbumImage("album.getinfo", "966f26ded204772262fdb5bf66767c4b", albumId, "json");

            artist.enqueue(new Callback<AlbumLastFm>() {
                @Override
                public void onResponse(Call<AlbumLastFm> call, Response<AlbumLastFm> response) {
                    Integer statusCode = response.code();
                    Log.v("status code: ", statusCode.toString());
                    final AlbumLastFm album = response.body();
                    ArrayList<AlbumImage> images = new ArrayList<>();
                        images = album.getArtist().getImage();
                        if (images.get(3).getText().isEmpty()) {
                            Picasso.with(holder.album_photo.getContext())
                                    .load(R.drawable.acdc)
                                    .placeholder(R.drawable.acdc)
                                    .into(holder.album_photo);
                        } else {
                            Picasso.with(holder.album_photo.getContext())
                                    .load(images.get(3).getText())
                                    .placeholder(R.drawable.acdc)
                                    .into(holder.album_photo);
                        }


                }

                @Override
                public void onFailure(Call<AlbumLastFm> call, Throwable t) {
                    Log.v("http fail: ", t.getMessage());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return releaseGroups != null ? releaseGroups.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView album_photo;
        private TextView album_name;
        private TextView release_date;
        private ImageView ic_release_type;
        private TextView release_type;
        private TextView track_number;

        public ViewHolder(View itemView) {
            super(itemView);

            this.album_photo = (ImageView) itemView.findViewById(R.id.album_photo);
            this.album_name = (TextView) itemView.findViewById(R.id.album_name);
            this.release_date = (TextView) itemView.findViewById(R.id.release_date);
            this.ic_release_type = (ImageView) itemView.findViewById(R.id.ic_release_type);
            this.release_type = (TextView) itemView.findViewById(R.id.release_type);
            this.track_number = (TextView) itemView.findViewById(R.id.track_number);

        }
    }
}
