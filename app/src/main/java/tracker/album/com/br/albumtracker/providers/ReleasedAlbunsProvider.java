package tracker.album.com.br.albumtracker.providers;

import java.util.ArrayList;
import java.util.List;

import tracker.album.com.br.albumtracker.handlers.ReleasedAlbunsList;
import tracker.album.com.br.albumtracker.R;

/**
 * Created by Leonardo Assunção on 04/05/2016.
 */
public class ReleasedAlbunsProvider {

    public static List<ReleasedAlbunsList> provideReleasedAlbuns() {
        List<ReleasedAlbunsList> albuns = new ArrayList<>();

        albuns.add(ReleasedAlbunsProvider.setAlbumExample());
        albuns.add(ReleasedAlbunsProvider.setAlbumExample());
        albuns.add(ReleasedAlbunsProvider.setAlbumExample());
        albuns.add(ReleasedAlbunsProvider.setAlbumExample());
        albuns.add(ReleasedAlbunsProvider.setAlbumExample());
        albuns.add(ReleasedAlbunsProvider.setAlbumExample());

        return albuns;

    }

    private static ReleasedAlbunsList setAlbumExample() {
        ReleasedAlbunsList ex = new ReleasedAlbunsList();

        ex.setAlbum_photo(R.drawable.bls);
        ex.setRelease_date("15/02/2013");
        ex.setAlbum_name("High Voltage");
        ex.setIc_release_type(R.drawable.release_type_album);
        ex.setRelease_type("Album");
        ex.setTrack_number("15 tracks");
        return ex;
    }
}
