package tracker.album.com.br.albumtracker.network;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tracker.album.com.br.albumtracker.pojo.AlbumLastFm;
import tracker.album.com.br.albumtracker.pojo.Artist;
import tracker.album.com.br.albumtracker.pojo.ArtistsLastFm;
import tracker.album.com.br.albumtracker.pojo.ArtistsReleases;
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
    @GET("release?")
    Call<ArtistsReleases> getReleaseAlbuns(
            @Query("artist") String artistId,
            @Query("fmt") String fmt);


   // ARTISTS IMAGE FROM LASTFM - WORKING
    @GET("2.0/")
    Call<ArtistsLastFm> getArtistImage(
            @Query("method") String method,
            @Query("mbid") String mbid,
            @Query("api_key") String apiKey,
            @Query("format") String fmt);

    //ALBUM IMAGES FROM LASTFM

    //2.0/?method=album.getinfo&api_key=966f26ded204772262fdb5bf66767c4b&mbid=078f2236-685f-4659-a5cf-c50e412445ec&format=json
    @GET("2.0/")
    Call<AlbumLastFm> getAlbumImage(
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("mbid") String mbid,
            @Query("format") String fmt);
}


//get albuns by tooday date
//http://musicbrainz.org/ws/2/release/?query=date:2018-05-05




