package tracker.album.com.br.albumtracker.handlers;

/**
 * Created by Leonardo Assunção on 14/04/2016.
 */
public class LibraryArtistsList {

    private int artist_photo;
    private String artist_name;
    private String music_style;
    private int ic_artist_type;
    private String artist_type;
    private String artist_country;
    private String last_album;

    public int getIc_artist_type() {
        return ic_artist_type;
    }

    public void setIc_artist_type(int ic_artist_type) {
        this.ic_artist_type = ic_artist_type;
    }

    public String getArtist_type() {
        return artist_type;
    }

    public void setArtist_type(String artist_type) {
        this.artist_type = artist_type;
    }

    public String getArtist_country() {
        return artist_country;
    }

    public void setArtist_country(String artist_country) {
        this.artist_country = artist_country;
    }

    public String getLast_album() {
        return last_album;
    }

    public void setLast_album(String last_album) {
        this.last_album = last_album;
    }

    public int getArtist_photo() {
        return artist_photo;
    }

    public void setArtist_photo(int artist_photo) {
        this.artist_photo = artist_photo;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getMusic_style() {
        return music_style;
    }

    public void setMusic_style(String music_style) {
        this.music_style = music_style;
    }
}
