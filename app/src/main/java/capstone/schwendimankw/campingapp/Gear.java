package capstone.schwendimankw.campingapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class Gear {
    private ArrayList<Items> mItems;

    private static Gear sGear;
    private Context mAppContext;

    private Gear(Context appContext) {
        mAppContext = appContext;
        mItems = new ArrayList<Items>();
        for (int i = 0; i < 100; i++) {
            Items it = new Items();
            it.setName("Item #" + i);
            mItems.add(it);
        }
    }

    public static Gear get(Context c) {
        if (sGear == null) {
            sGear = new Gear(c.getApplicationContext());
        }
        return sGear;
    }

    public ArrayList<Items> getItems() {
        return mItems;
    }

    public Items getItem(UUID id) {
        for (Items i : mItems) {
            if (i.getId().equals(id))
                return i;
        }
        return null;
    }
}
