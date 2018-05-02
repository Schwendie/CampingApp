package capstone.schwendimankw.campingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.UUID;

public class ChecklistPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ArrayList<Checklist> mChecks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mChecks = ListPlanner.get(this).getChecks();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Checklist checklist = mChecks.get(position);
                return ChecklistFragment.newInstance(checklist.getId());
            }

            @Override
            public int getCount() {
                return mChecks.size();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                Checklist checklist = mChecks.get(position);
                if (checklist.getTitle() != null) {
                    setTitle(checklist.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        UUID checklistId = (UUID)getIntent()
                .getSerializableExtra(ChecklistFragment.EXTRA_CHECK_ID);
        for (int i = 0; i < mChecks.size(); i++) {
            if (mChecks.get(i).getId().equals(checklistId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

}
