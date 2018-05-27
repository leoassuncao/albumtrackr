package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by leonardo.filho on 24/05/2018.
 */

public class Images implements Parcelable {

    private ArrayList<CoverArt> images = null;

    public ArrayList<CoverArt> getImages() {
        return images;
    }

    public void setImages(ArrayList<CoverArt> images) {
        this.images = images;
    }

    protected Images(Parcel in) {
        if (in.readByte() == 0x01) {
            images = new ArrayList<>();
            in.readList(images, ReleaseGroup.class.getClassLoader());
        } else {
            images = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (images == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(images);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Images> CREATOR = new Parcelable.Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel in) {
            return new Images(in);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };
}
