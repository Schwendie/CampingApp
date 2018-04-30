package capstone.schwendimankw.campingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.UUID;

public class ItemsPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ArrayList<Items> mItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mItems = Gear.get(this).getItems();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Items items = mItems.get(position);
                return ItemsFragment.newInstance(items.getId());
            }

            @Override
            public int getCount() {
                return mItems.size();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                Items items = mItems.get(position);
                if (items.getName() != null) {
                    setTitle(items.getName());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        UUID itemId = (UUID)getIntent()
                .getSerializableExtra(ItemsFragment.EXTRA_ITEMS_ID);
        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i).getId().equals(itemId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
