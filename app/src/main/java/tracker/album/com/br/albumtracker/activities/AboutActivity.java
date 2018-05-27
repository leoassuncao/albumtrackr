package tracker.album.com.br.albumtracker.activities;

import android.os.Bundle;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.aboutlibraries.ui.LibsActivity;

import tracker.album.com.br.albumtracker.R;

/**
 * Created by Leonardo Assunção on 12/05/2016.
 */
public class AboutActivity extends LibsActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setIntent(new LibsBuilder()
                .withActivityTitle(getResources().getString(R.string.about_act))
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                .withFields(R.string.class.getFields())
                .withAboutIconShown(true)
                .withAboutVersionShown(true)
                .withAboutDescription(getResources().getString(R.string.about_description))
                .withLicenseShown(true)
                .intent(this));

        super.onCreate(savedInstanceState);
    }
}