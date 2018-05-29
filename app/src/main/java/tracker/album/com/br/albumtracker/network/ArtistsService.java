package tracker.album.com.br.albumtracker.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leonardo.filho on 15/05/2018.
 */

public class ArtistsService {

    private static ServiceApi retrofit;
    private static final String API_URL = "http://musicbrainz.org/ws/2/";
    private static final String API_URL_IMAGE= "http://ws.audioscrobbler.com/";

    public static  ServiceApi getApi( ) {

        Gson gson = new GsonBuilder().create();
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callFactory(httpClientBuilder.build())
                .build().create(ServiceApi.class);

        return retrofit;
    }

    public static  ServiceApi getApiImage( ) {

        Gson gson = new GsonBuilder().create();
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL_IMAGE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callFactory(httpClientBuilder.build())
                .build().create(ServiceApi.class);

        return retrofit;
    }

}
