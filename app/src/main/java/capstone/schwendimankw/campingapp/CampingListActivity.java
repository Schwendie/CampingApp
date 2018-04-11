package capstone.schwendimankw.campingapp;

import android.support.v4.app.Fragment;

/**
 * Created by Kyle Schwendiman on 4/6/2018.
 */

public class CampingListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CampingListFragment();
    }
}
