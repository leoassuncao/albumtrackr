package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leonardo.filho on 25/05/2018.
 */

public class SearchArtists implements Parcelable {

    private ArrayList<Artist> artists = null;

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public SearchArtists()  {

    }
    public SearchArtists(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @SuppressWarnings("unused")
    public static final Creator<SearchArtists> CREATOR = new Creator<SearchArtists>() {
        @Override
        public SearchArtists createFromParcel(Parcel in) {
            return new SearchArtists(in);
        }

        @Override
        public SearchArtists[] newArray(int size) {
            return new SearchArtists[size];
        }
    };
}
