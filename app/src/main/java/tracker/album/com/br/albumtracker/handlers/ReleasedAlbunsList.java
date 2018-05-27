package tracker.album.com.br.albumtracker.handlers;

/**
 * Created by Leonardo Assunção on 04/05/2016.
 */
public class ReleasedAlbunsList {

    private int album_photo;
    private String album_name;
    private String release_date;
    private int ic_release_type;
    private String release_type;
    private String track_number;

    public int getIc_release_type() {
        return ic_release_type;
    }

    public void setIc_release_type(int ic_release_type) {
        this.ic_release_type = ic_release_type;
    }

    public String getRelease_type() {
        return release_type;
    }

    public void setRelease_type(String release_type) {
        this.release_type = release_type;
    }

    public String getTrack_number() {
        return track_number;
    }

    public void setTrack_number(String track_number) {
        this.track_number = track_number;
    }

    public int getAlbum_photo() {
        return album_photo;
    }

    public void setAlbum_photo(int album_photo) {
        this.album_photo = album_photo;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }
}
