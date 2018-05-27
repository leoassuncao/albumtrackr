package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leonardo.filho on 24/05/2018.
 */

public class CoverArt implements Parcelable  {

    private String image;
    private String id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected CoverArt(Parcel in) {
        id = in.readString();
        image = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(image);

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CoverArt> CREATOR = new Parcelable.Creator<CoverArt>() {
        @Override
        public CoverArt createFromParcel(Parcel in) {
            return new CoverArt(in);
        }

        @Override
        public CoverArt[] newArray(int size) {
            return new CoverArt[size];
        }
    };
}
