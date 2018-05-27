package tracker.album.com.br.albumtracker.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.net.URI;

import tracker.album.com.br.albumtracker.adapters.FollowedAdapter;
import tracker.album.com.br.albumtracker.data.FollowedCursorLoader;
import tracker.album.com.br.albumtracker.providers.LibraryArtistProvider;
import tracker.album.com.br.albumtracker.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Leonardo Assunção on 05/04/2016.
 */
public class LibraryFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private View mRootView;
    private Activity myActivity;
    private TextView mNickname;
    public static final int ID_FOLLOWED_LOADER = 11;
    private static final int SELECT_PHOTO = 100;
    private  FollowedAdapter mFollowedAdapter;
    private CircularImageView mProfileImage;
    private RecyclerView mRecyclerVyew;
    Uri mUri;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.myActivity = (Activity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mRootView = inflater.inflate(R.layout.fragment_library, container, false);
        return this.mRootView;


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNickname = (TextView) getView().findViewById(R.id.nickname);
        mProfileImage = (CircularImageView) getView().findViewById(R.id.profile_pic);
        this.loadFollowedArtists();
        setupSharedPreferences();

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery, SELECT_PHOTO);
            }
        });

        if (savedInstanceState != null) {
            mUri  =  savedInstanceState.getParcelable("Image");
            Picasso.with(getContext())
                    .load(mUri)
                    .noPlaceholder()
                    .centerCrop()
                    .fit()
                    .into(mProfileImage);
            }
        }


    public void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mNickname.setText(sharedPreferences.getString(getResources().getString(R.string.pref_user_nickname), getResources().getString(R.string.user_nick)));
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
     }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_user_nickname))) {
            mNickname.setText(sharedPreferences.getString(getResources().getString(R.string.pref_user_nickname), getResources().getString(R.string.user_nick)));

        }
    }



    public void loadFollowedArtists() {
        mRecyclerVyew = (RecyclerView) this.mRootView.findViewById(R.id.artist_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.myActivity);
        mRecyclerVyew.setLayoutManager(linearLayoutManager);
        mFollowedAdapter = new FollowedAdapter();
        mRecyclerVyew.setAdapter(mFollowedAdapter);
        getLoaderManager().initLoader(ID_FOLLOWED_LOADER, null, new FollowedCursorLoader(getContext(), mFollowedAdapter));
        mFollowedAdapter.notifyDataSetChanged();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    if (selectedImage != null) {
                        Picasso.with(getContext())
                                .load(selectedImage)
                                .noPlaceholder()
                                .centerCrop()
                                .fit()
                                .into(mProfileImage);
                    }
                    mUri = selectedImage;
                }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFollowedArtists();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadFollowedArtists();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(getContext())
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}