package capstone.schwendimankw.campingapp;

import android.support.v4.app.Fragment;

public class ItemsActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new ItemsFragment();
    }
}
