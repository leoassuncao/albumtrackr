package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by leonardo.filho on 28/05/2018.
 */

public class ArtistsLastFm  implements Parcelable{

   private ImageLastFm artist;

    public ImageLastFm getArtist() {
        return artist;
    }

    public void setArtist(ImageLastFm artist) {
        this.artist = artist;
    }
    public ArtistsLastFm()  {

    }

    public ArtistsLastFm(Parcel in) {

    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {



    }



    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ArtistsLastFm> CREATOR = new Parcelable.Creator<ArtistsLastFm>() {
        @Override
        public ArtistsLastFm createFromParcel(Parcel in) {
            return new ArtistsLastFm(in);
        }

        @Override
        public ArtistsLastFm[] newArray(int size) {
            return new ArtistsLastFm[size];
        }
    };
}
