package capstone.schwendimankw.campingapp;

import android.support.v4.app.Fragment;

import java.util.UUID;

public class ChecklistActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {

        UUID checklistId = (UUID)getIntent()
                .getSerializableExtra(ChecklistFragment.EXTRA_CHECK_ID);

        return ChecklistFragment.newInstance(checklistId);
    }
}
