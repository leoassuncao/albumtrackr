package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by leonardo.filho on 29/05/2018.
 */

public class ImageAlbumLastFm implements Parcelable {

    private ArrayList<AlbumImage> image = null;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<AlbumImage> getImage() {
        return image;
    }

    public void setImage(ArrayList<AlbumImage> image) {
        this.image = image;
    }

    public ImageAlbumLastFm(Parcel in) {
        if (in.readByte() == 0x01) {
            image = new ArrayList<>();
            in.readList(image, ImageAlbumLastFm.class.getClassLoader());
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
    public static final Parcelable.Creator<ImageAlbumLastFm> CREATOR = new Parcelable.Creator<ImageAlbumLastFm>() {
        @Override
        public ImageAlbumLastFm createFromParcel(Parcel in) {
            return new ImageAlbumLastFm(in);
        }

        @Override
        public ImageAlbumLastFm[] newArray(int size) {
            return new ImageAlbumLastFm[size];
        }
    };
}
