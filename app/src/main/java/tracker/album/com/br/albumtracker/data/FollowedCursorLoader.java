package tracker.album.com.br.albumtracker.data;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import tracker.album.com.br.albumtracker.adapters.FollowedAdapter;

import static tracker.album.com.br.albumtracker.fragments.LibraryFragment.ID_FOLLOWED_LOADER;

/**
 * Created by leonardo.filho on 16/05/2018.
 */

public class FollowedCursorLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context context;
    private FollowedAdapter followedAdapter;

    public FollowedCursorLoader(Context context, FollowedAdapter followedAdapter) {
        this.context = context;
        this.followedAdapter = followedAdapter;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        switch (loaderId) {
            case ID_FOLLOWED_LOADER:
                String[] projection = {
                        ArtistsContract.ArtistsEntry.COLUMN_ARTIST_NAME,
                        ArtistsContract.ArtistsEntry.COLUMN_ARTIST_ID,
                        ArtistsContract.ArtistsEntry.COLUMN_ARTIST_COUNTRY,
                        ArtistsContract.ArtistsEntry.COLUMN_ARTIST_STYLE

                };
                return new CursorLoader(context,
                        ArtistsContract.ArtistsEntry.CONTENT_URI,
                        projection,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException("Loader failed: " + loaderId);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        followedAdapter.changeCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        followedAdapter.changeCursor(null);
    }
}

