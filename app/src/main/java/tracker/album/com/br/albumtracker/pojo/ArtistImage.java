package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leonardo.filho on 28/05/2018.
 */

public class ArtistImage implements Parcelable {

    @SerializedName("#text")
    private String text;
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getText() {
        return text;

    }

    public void setText(String text) {
        this.text = text;
    }

    protected ArtistImage(Parcel in) {
        text = in.readString();
        size = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(text);
        dest.writeString(size);

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

