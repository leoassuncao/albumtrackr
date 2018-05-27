package tracker.album.com.br.albumtracker.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import tracker.album.com.br.albumtracker.handlers.FeedArtistsList;
import tracker.album.com.br.albumtracker.R;

/**
 * Created by Leonardo Assunção on 18/04/2016.
 */
public class FeedCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FeedArtistsList> mDataSet;
    private Activity mActivity;

    public FeedCardAdapter(List<FeedArtistsList> artist, Activity act) {
        this.mDataSet = artist;
        this.mActivity = act;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.feed_artist_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.fd_album_name.setText(this.mDataSet.get(position).getFd_album_name());
        viewHolder.fd_album_date.setText(this.mDataSet.get(position).getFd_album_date());
        viewHolder.fd_artist_name.setText(this.mDataSet.get(position).getFd_artist_name());
        viewHolder.fd_release_type.setText(this.mDataSet.get(position).getFd_release_type());
        viewHolder.fd_album_description.setText(this.mDataSet.get(position).getFd_album_description());
        viewHolder.fd_album_photo.setImageResource(this.mDataSet.get(position).getFd_album_photo());
        viewHolder.fd_ic_release_type.setImageResource(this.mDataSet.get(position).getFd_ic_release_type());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fd_album_photo;
        private TextView fd_album_name;
        private TextView fd_album_date;
        private TextView fd_artist_name;
        private TextView fd_album_description;
        private TextView fd_release_type;
        private ImageView fd_ic_release_type;


        public ViewHolder(View v) {
            super(v);
            this.fd_album_photo = (ImageView) v.findViewById(R.id.fd_album_photo);
            this.fd_album_name = (TextView) v.findViewById(R.id.fd_album_name);
            this.fd_album_date = (TextView) v.findViewById(R.id.fd_album_date);
            this.fd_artist_name = (TextView) v.findViewById(R.id.fd_artist_name);
            this.fd_album_description = (TextView) v.findViewById(R.id.fd_album_description);
            this.fd_release_type = (TextView) v.findViewById(R.id.fd_release_type);
            this.fd_ic_release_type = (ImageView) v.findViewById(R.id.fd_ic_release_type);
        }
    }
}