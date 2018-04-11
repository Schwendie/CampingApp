package capstone.schwendimankw.campingapp;

import android.support.v4.app.Fragment;

/**
 * Created by Kyle Schwendiman on 4/10/2018.
 */

public class PlanningListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PlanningListFragment();
    }
}
