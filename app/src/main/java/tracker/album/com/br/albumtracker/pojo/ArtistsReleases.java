package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ArtistsReleases implements Parcelable {

    @SerializedName("releases")
    private ArrayList<ReleaseGroup> releaseGroups = null;

    public ArrayList<ReleaseGroup> getReleaseGroups() {
        return releaseGroups;
    }

    public void setReleaseGroups(ArrayList<ReleaseGroup> releaseGroups) {
        this.releaseGroups = releaseGroups;
    }

    protected ArtistsReleases(Parcel in) {
        if (in.readByte() == 0x01) {
            releaseGroups = new ArrayList<>();
            in.readList(releaseGroups, ReleaseGroup.class.getClassLoader());
        } else {
            releaseGroups = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (releaseGroups == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(releaseGroups);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ArtistsReleases> CREATOR = new Parcelable.Creator<ArtistsReleases>() {
        @Override
        public ArtistsReleases createFromParcel(Parcel in) {
            return new ArtistsReleases(in);
        }

        @Override
        public ArtistsReleases[] newArray(int size) {
            return new ArtistsReleases[size];
        }
    };
}
