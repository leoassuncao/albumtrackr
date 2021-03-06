package tracker.album.com.br.albumtracker.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by leonardo.filho on 07/05/2018.
 */

public class ArtistsContract {

    private ArtistsContract() {}

    public static final String AUTHORITY = "tracker.album.com.br.albumtracker";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH = "artists";
    public static final String PATH2 = "albuns";


    public static final class ArtistsEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

        public static final String TABLE_NAME = "artists";
        public static final String COLUMN_ARTIST_ID = "artistId";
        public static final String COLUMN_ARTIST_NAME = "artistName";
        public static final String COLUMN_ARTIST_STYLE = "artistStyle";
        public static final String COLUMN_ARTIST_COUNTRY = "artistCountry";

        public static final String CREATE_TABLE_ARTISTS = "CREATE TABLE " +
                ArtistsEntry.TABLE_NAME + "(" +
                ArtistsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ArtistsEntry.COLUMN_ARTIST_ID + " INTEGER NOT NULL," +
                ArtistsEntry.COLUMN_ARTIST_NAME + " TEXT NOT NULL," +
                ArtistsEntry.COLUMN_ARTIST_STYLE + " TEXT NOT NULL," +
                ArtistsEntry.COLUMN_ARTIST_COUNTRY + " TEXT NOT NULL" +
                ");";
    }

    public static final class AlbunsEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH2).build();

        public static final String TABLE_NAME = "albuns";
        public static final String COLUMN_ARTIST_ID = "artistId";
        public static final String COLUMN_ALBUM_ID = "albumId";
        public static final String COLUMN_ALBUM_NAME = "albumName";
        public static final String COLUMN_RELEASE_DATE = "releaseDate";

        public static final String CREATE_TABLE_ALBUNS = "CREATE TABLE " +
                AlbunsEntry.TABLE_NAME + "(" +
                AlbunsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AlbunsEntry.COLUMN_ARTIST_ID + " INTEGER NOT NULL," +
                AlbunsEntry.COLUMN_ALBUM_ID + " TEXT NOT NULL," +
                AlbunsEntry.COLUMN_ALBUM_NAME + " TEXT NOT NULL," +
                AlbunsEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL" +
                ");";
    }

}

