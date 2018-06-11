package tracker.album.com.br.albumtracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by leonardo.filho on 07/05/2018.
 */

public class ArtistsDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "artists.db";
    private static final int DATABASE_VERSION = 3;

    public ArtistsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ArtistsContract.ArtistsEntry.CREATE_TABLE_ARTISTS);
        sqLiteDatabase.execSQL(ArtistsContract.AlbunsEntry.CREATE_TABLE_ALBUNS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ArtistsContract.ArtistsEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ArtistsContract.AlbunsEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
