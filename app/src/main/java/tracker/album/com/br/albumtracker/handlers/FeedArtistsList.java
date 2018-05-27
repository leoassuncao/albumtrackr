package tracker.album.com.br.albumtracker.handlers;

/**
 * Created by Leonardo Assunção on 18/04/2016.
 */
public class FeedArtistsList{

    private int fd_album_photo;
    private String fd_album_name;
    private String fd_album_date;
    private String fd_artist_name;
    private String fd_album_description;
    private String fd_release_type;

    public int getFd_ic_release_type() {
        return fd_ic_release_type;
    }

    public void setFd_ic_release_type(int fd_ic_release_type) {
        this.fd_ic_release_type = fd_ic_release_type;
    }

    private int fd_ic_release_type;

    public String getFd_release_type() {
        return fd_release_type;
    }

    public void setFd_release_type(String fd_release_type) {
        this.fd_release_type = fd_release_type;
    }

    public String getFd_album_description() {
        return fd_album_description;
    }

    public void setFd_album_description(String fd_album_description) {
        this.fd_album_description = fd_album_description;
    }

    public int getFd_album_photo() {
        return fd_album_photo;
    }

    public void setFd_album_photo(int fd_album_photo) {
        this.fd_album_photo = fd_album_photo;
    }

    public String getFd_album_name() {
        return fd_album_name;
    }

    public void setFd_album_name(String fd_album_name) {
        this.fd_album_name = fd_album_name;
    }

    public String getFd_album_date() {
        return fd_album_date;
    }

    public void setFd_album_date(String fd_album_date) {
        this.fd_album_date = fd_album_date;
    }

    public String getFd_artist_name() {
        return fd_artist_name;
    }

    public void setFd_artist_name(String fd_artist_name) {
        this.fd_artist_name = fd_artist_name;
    }
}

