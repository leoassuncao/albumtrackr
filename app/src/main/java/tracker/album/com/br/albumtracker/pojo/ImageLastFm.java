package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by leonardo.filho on 29/05/2018.
 */

public class ImageLastFm implements Parcelable {

    private ArrayList<ArtistImage> image = null;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<ArtistImage> getImage() {
        return image;
    }

    public void setImage(ArrayList<ArtistImage> image) {
        this.image = image;
    }

    public ImageLastFm(Parcel in) {
        if (in.readByte() == 0x01) {
            image = new ArrayList<>();
            in.readList(image, ImageLastFm.class.getClassLoader());
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
    public static final Parcelable.Creator<ImageLastFm> CREATOR = new Parcelable.Creator<ImageLastFm>() {
        @Override
        public ImageLastFm createFromParcel(Parcel in) {
            return new ImageLastFm(in);
        }

        @Override
        public ImageLastFm[] newArray(int size) {
            return new ImageLastFm[size];
        }
    };
}
