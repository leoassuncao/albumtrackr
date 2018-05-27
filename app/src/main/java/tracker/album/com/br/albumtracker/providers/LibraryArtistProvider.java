package tracker.album.com.br.albumtracker.providers;

import java.util.ArrayList;
import java.util.List;

import tracker.album.com.br.albumtracker.handlers.LibraryArtistsList;
import tracker.album.com.br.albumtracker.R;

/**
 * Created by Leonardo Assunção on 14/04/2016.
 */
public class LibraryArtistProvider {

    public static List<LibraryArtistsList> provideArtistsList() {
        List<LibraryArtistsList> artists = new ArrayList<>();

        artists.add(LibraryArtistProvider.setACDC());
        artists.add(LibraryArtistProvider.setBlackLabelSociety());
        artists.add(LibraryArtistProvider.setACDC());
        artists.add(LibraryArtistProvider.setBlackLabelSociety());
        artists.add(LibraryArtistProvider.setACDC());
        artists.add(LibraryArtistProvider.setBlackLabelSociety());
        artists.add(LibraryArtistProvider.setACDC());
        artists.add(LibraryArtistProvider.setBlackLabelSociety());

        return artists;

    }

    private static LibraryArtistsList setBlackLabelSociety() {
        LibraryArtistsList bls = new LibraryArtistsList();

        bls.setArtist_name("Black Label Society");
        bls.setMusic_style("Rock");
        bls.setArtist_photo(R.drawable.bls);
        bls.setIc_artist_type(R.drawable.ic_artist_type_group);
        bls.setArtist_country("USA");
        bls.setArtist_type("Band");
        bls.setLast_album("The Song Remains Not The Same");

        return bls;
    }


    private static LibraryArtistsList setACDC() {
        LibraryArtistsList acdc = new LibraryArtistsList();

        acdc.setArtist_name("AC/DC");
        acdc.setMusic_style("Hard Rock");
        acdc.setArtist_photo(R.drawable.acdc);
        acdc.setIc_artist_type(R.drawable.ic_artist_type_single);
        acdc.setArtist_country("USA");
        acdc.setArtist_type("Solo");
        acdc.setLast_album("High Voltage");

        return acdc;
    }
}

