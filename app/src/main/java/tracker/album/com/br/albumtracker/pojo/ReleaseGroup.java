package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;

public class ReleaseGroup implements Parcelable{

    private String id;
    @SerializedName("date")
    private String firstReleaseDate;
    private String title;
    @SerializedName("status")
    private String primaryType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(String firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    protected ReleaseGroup(Parcel in) {
        id = in.readString();
        firstReleaseDate = in.readString();
        title = in.readString();
        primaryType = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(firstReleaseDate);
        dest.writeString(title);
        dest.writeString(primaryType);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ReleaseGroup> CREATOR = new Parcelable.Creator<ReleaseGroup>() {
        @Override
        public ReleaseGroup createFromParcel(Parcel in) {
            return new ReleaseGroup(in);
        }

        @Override
        public ReleaseGroup[] newArray(int size) {
            return new ReleaseGroup[size];
        }
    };

}

