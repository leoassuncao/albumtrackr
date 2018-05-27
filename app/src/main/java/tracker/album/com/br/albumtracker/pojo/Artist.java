package tracker.album.com.br.albumtracker.pojo;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.List;

/**
 * Created by leonardo.filho on 15/05/2018.
 */

public class Artist implements Parcelable{

    private List<Artist> artist;
    private String id;
    private String name;
    private String type;
    private String country;

    public Artist()  {

    }
    public Artist(Parcel in) {
        id = in.readString();
        name = in.readString();
        country = in.readString();
        type = in.readString();
    }

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(country);
        dest.writeString(type);
    }

    @SuppressWarnings("unused")
    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };
}
