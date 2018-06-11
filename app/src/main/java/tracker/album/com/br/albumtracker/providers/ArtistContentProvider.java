package tracker.album.com.br.albumtracker.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import tracker.album.com.br.albumtracker.data.ArtistsContract;
import tracker.album.com.br.albumtracker.data.ArtistsDBHelper;

/**
 * Created by leonardo.filho on 07/05/2018.
 */

public class ArtistContentProvider extends ContentProvider {

    public final static int ARTISTS = 100;
    public final static int ARTISTS_WITH_ID = 101;
    public final static int ALBUNS = 102;
    public final static int ALBUNS_WITH_ID = 103;
    public final static UriMatcher uriMatcher = buildUriMatcher();
    private ArtistsDBHelper dbHelper;

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ArtistsContract.AUTHORITY, ArtistsContract.PATH, ARTISTS);
        uriMatcher.addURI(ArtistsContract.AUTHORITY, ArtistsContract.PATH + "/#", ARTISTS_WITH_ID);
        uriMatcher.addURI(ArtistsContract.AUTHORITY, ArtistsContract.PATH2, ALBUNS);
        uriMatcher.addURI(ArtistsContract.AUTHORITY, ArtistsContract.PATH2 + "/#", ALBUNS_WITH_ID);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new ArtistsDBHelper((getContext()));
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int match = uriMatcher.match(uri);
        Cursor cursor;
        switch (match) {
            case ARTISTS:
                cursor = db.query(ArtistsContract.ArtistsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case ALBUNS:
                cursor = db.query(ArtistsContract.AlbunsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case ARTISTS:
                long id = db.insert(ArtistsContract.ArtistsEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(ArtistsContract.ArtistsEntry.CONTENT_URI, id);
                } else {
                    db.close();
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            case ALBUNS:
                id = db.insert(ArtistsContract.AlbunsEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(ArtistsContract.AlbunsEntry.CONTENT_URI, id);
                } else {
                    db.close();
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                db.close();
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int rowDeleted = 0;
        switch (match) {
            case ARTISTS:
                rowDeleted = db.delete(ArtistsContract.ArtistsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case ALBUNS:
                rowDeleted = db.delete(ArtistsContract.AlbunsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                db.close();
                throw new UnsupportedOperationException("Couldn't delete :(");
        }
        if (rowDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        db.close();
        return rowDeleted;
    }
}
