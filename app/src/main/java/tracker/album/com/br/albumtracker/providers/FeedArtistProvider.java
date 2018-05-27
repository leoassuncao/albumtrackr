package tracker.album.com.br.albumtracker.providers;

import java.util.ArrayList;
import java.util.List;

import tracker.album.com.br.albumtracker.handlers.FeedArtistsList;
import tracker.album.com.br.albumtracker.R;

/**
 * Created by Leonardo Assunção on 18/04/2016.
 */
public class FeedArtistProvider {

    public static List<FeedArtistsList> provideArtistsList() {
        List<FeedArtistsList> artists = new ArrayList<>();

        artists.add(FeedArtistProvider.setBlackLabelSociety());
        artists.add(FeedArtistProvider.setACDC());
        artists.add(FeedArtistProvider.setLynyrdSkynyrd());
        artists.add(FeedArtistProvider.setBlindGuardian());
        artists.add(FeedArtistProvider.setGuns());
        artists.add(FeedArtistProvider.setMetallica());
        artists.add(FeedArtistProvider.setScorpions());

        return artists;

    }

    private static FeedArtistsList setBlackLabelSociety() {
        FeedArtistsList bls = new FeedArtistsList();

        bls.setFd_album_name("The Song Remains Not The Same");
        bls.setFd_album_date("2013");
        bls.setFd_artist_name("Black Label Society");
        bls.setFd_album_photo(R.drawable.bls);
        bls.setFd_album_description("Description");
        bls.setFd_release_type("Other");
        bls.setFd_ic_release_type(R.drawable.release_type_other);

        return bls;
    }

    private static FeedArtistsList setACDC() {
        FeedArtistsList acdc = new FeedArtistsList();

        acdc.setFd_album_name("Rock or Bust");
        acdc.setFd_album_date("2016");
        acdc.setFd_artist_name("AC/DC");
        acdc.setFd_album_photo(R.drawable.acdc);
        acdc.setFd_album_description("Description");
        acdc.setFd_release_type("Album");
        acdc.setFd_ic_release_type(R.drawable.release_type_album);

        return acdc;
    }

    private static FeedArtistsList setLynyrdSkynyrd() {
        FeedArtistsList lynyrd = new FeedArtistsList();

        lynyrd.setFd_album_name("Seocnd Helping");
        lynyrd.setFd_album_date("1974");
        lynyrd.setFd_artist_name("Lynyrd Skynyrd");
        lynyrd.setFd_album_photo(R.drawable.lynyrd);
        lynyrd.setFd_album_description("Description");
        lynyrd.setFd_release_type("Single");
        lynyrd.setFd_ic_release_type(R.drawable.release_type_single);
        return lynyrd;

    }

    private static FeedArtistsList setBlindGuardian() {
        FeedArtistsList blind = new FeedArtistsList();

        blind.setFd_album_name("Nightfall in Middle Earth");
        blind.setFd_album_date("1998");
        blind.setFd_artist_name("Blind Guardian");
        blind.setFd_album_photo(R.drawable.blind);
        blind.setFd_album_description("Description");
        blind.setFd_release_type("Other");
        blind.setFd_ic_release_type(R.drawable.release_type_other);

        return blind;
    }

    private static FeedArtistsList setGuns() {
        FeedArtistsList guns = new FeedArtistsList();

        guns.setFd_album_name("Apettite For Destruction");
        guns.setFd_album_date("1987");
        guns.setFd_artist_name("Guns n' Roses");
        guns.setFd_album_photo(R.drawable.guns);
        guns.setFd_album_description("Description");
        guns.setFd_release_type("Album");
        guns.setFd_ic_release_type(R.drawable.release_type_album);

        return guns;
    }

    private static FeedArtistsList setMetallica() {
        FeedArtistsList metallica = new FeedArtistsList();

        metallica.setFd_album_name("Load");
        metallica.setFd_album_date("1996");
        metallica.setFd_artist_name("Metallica");
        metallica.setFd_album_photo(R.drawable.metallica);
        metallica.setFd_album_description("Description");
        metallica.setFd_release_type("Single");
        metallica.setFd_ic_release_type(R.drawable.release_type_single);

        return metallica;
    }

    private static FeedArtistsList setScorpions() {
        FeedArtistsList scorpions = new FeedArtistsList();

        scorpions.setFd_album_name("Acoustica");
        scorpions.setFd_album_date("2001");
        scorpions.setFd_artist_name("Scorpions");
        scorpions.setFd_album_photo(R.drawable.scorpions);
        scorpions.setFd_album_description("Description");
        scorpions.setFd_release_type("Single");
        scorpions.setFd_ic_release_type(R.drawable.release_type_single);

        return scorpions;
    }
}




