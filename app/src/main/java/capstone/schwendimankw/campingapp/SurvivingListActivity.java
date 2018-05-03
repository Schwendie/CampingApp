package capstone.schwendimankw.campingapp;

import android.support.v4.app.Fragment;

public class SurvivingListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SurvivingListFragment();
    }
}
