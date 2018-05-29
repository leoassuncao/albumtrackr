package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leonardo.filho on 29/05/2018.
 */

public class AlbumLastFm implements Parcelable {

    private ImageAlbumLastFm album;

    public ImageAlbumLastFm getArtist() {
        return album;
    }

    public void setArtist(ImageAlbumLastFm album) {
        this.album = album;
    }
    public AlbumLastFm()  {

    }

    public AlbumLastFm(Parcel in) {

    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {



    }



    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AlbumLastFm> CREATOR = new Parcelable.Creator<AlbumLastFm>() {
        @Override
        public AlbumLastFm createFromParcel(Parcel in) {
            return new AlbumLastFm(in);
        }

        @Override
        public AlbumLastFm[] newArray(int size) {
            return new AlbumLastFm[size];
        }
    };
}
