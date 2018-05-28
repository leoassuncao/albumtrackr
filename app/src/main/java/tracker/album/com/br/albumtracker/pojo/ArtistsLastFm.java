package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by leonardo.filho on 28/05/2018.
 */

public class ArtistsLastFm  implements Parcelable{

    private ArrayList<ArtistImage> image = null;


    public ArrayList<ArtistImage> getImage() {
        return image;
    }

    public void setImage(ArrayList<ArtistImage> image) {
        this.image = image;
    }

    protected ArtistsLastFm(Parcel in) {
        if (in.readByte() == 0x01) {
            image = new ArrayList<>();
            in.readList(image, ReleaseGroup.class.getClassLoader());
        } else {
            image = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (image == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(image);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ArtistImage> CREATOR = new Parcelable.Creator<ArtistImage>() {
        @Override
        public ArtistImage createFromParcel(Parcel in) {
            return new ArtistImage(in);
        }

        @Override
        public ArtistImage[] newArray(int size) {
            return new ArtistImage[size];
        }
    };
}
