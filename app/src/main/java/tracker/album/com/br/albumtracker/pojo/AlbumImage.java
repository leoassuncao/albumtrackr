package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leonardo.filho on 29/05/2018.
 */

public class AlbumImage implements Parcelable {

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

    protected AlbumImage(Parcel in) {
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
    public static final Parcelable.Creator<AlbumImage> CREATOR = new Parcelable.Creator<AlbumImage>() {
        @Override
        public AlbumImage createFromParcel(Parcel in) {
            return new AlbumImage(in);
        }

        @Override
        public AlbumImage[] newArray(int size) {
            return new AlbumImage[size];
        }
    };

}

