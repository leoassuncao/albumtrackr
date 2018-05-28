package tracker.album.com.br.albumtracker.network;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import tracker.album.com.br.albumtracker.pojo.Artist;
import tracker.album.com.br.albumtracker.pojo.ArtistImage;
import tracker.album.com.br.albumtracker.pojo.ArtistsLastFm;
import tracker.album.com.br.albumtracker.pojo.ArtistsReleases;
import tracker.album.com.br.albumtracker.pojo.CoverArt;
import tracker.album.com.br.albumtracker.pojo.Images;
import tracker.album.com.br.albumtracker.pojo.SearchArtists;

/**
 * Created by leonardo.filho on 15/05/2018.
 */

public interface ServiceApi {
    @GET("artist/ac9a487a-d9d2-4f27-bb23-0f4686488345?inc=aliases&fmt=json")
    Call<Artist> getArtist();

    //Search by id - WORKING
    @GET("artist/{id}?inc=aliases&fmt=json")
    Call<Artist> getSearch(
            @Path("id") String id);

    //Search By Name - Working
    @GET("artist/")
   // http://musicbrainz.org/ws/2/artist/?query=artist:queen&fmt=json
    Call<SearchArtists> getSearchArtist(
            @Query("query") String searchArtist,
            @Query("fmt") String fmt);

    //GET ALBUNS - WORKING
    @GET("release-group?artist=ac9a487a-d9d2-4f27-bb23-0f4686488345&fmt=json")
    Call<ArtistsReleases> getReleases ();

    //GET ALBUNS BY ID - WORKING
    @GET("release-group?")
    Call<ArtistsReleases> getReleaseAlbuns(
            @Query("artist") String artistId,
            @Query("fmt") String fmt);

    //GET ALBUNS IMAGE
    @GET("{id}")
    Call<Images> getAlbumImage(@Path("id") String id);

   // ARTISTS IMAGE FROM LASTFM
    // http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&mbid=bfcc6d75-a6a5-4bc6-8282-47aec8531818&api_key=966f26ded204772262fdb5bf66767c4b&format=json
    //?method=artist.getinfo&mbid=bfcc6d75-a6a5-4bc6-8282-47aec8531818&api_key=966f26ded204772262fdb5bf66767c4b&format=json
    @GET("?method=artist.getinfo&mbid=bfcc6d75-a6a5-4bc6-8282-47aec8531818&api_key=966f26ded204772262fdb5bf66767c4b&format=json")
    Call<ArtistsLastFm> getArtistImage();
}

// url to get cover art  http://coverartarchive.org/release-group/14c5e04e-c2eb-3437-b3d3-9dd42c297e09


