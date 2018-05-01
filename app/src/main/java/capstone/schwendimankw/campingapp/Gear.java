package capstone.schwendimankw.campingapp;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class Gear {
    private static final String TAG = "Gear";
    private static final String FILENAME = "items.json";

    private ArrayList<Items> mItems;
    private CampingAppJSONSerializer mSerializer;

    private static Gear sGear;
    private Context mAppContext;

    private Gear(Context appContext) {
        mAppContext = appContext;
        mSerializer = new CampingAppJSONSerializer(mAppContext, FILENAME);

        try {
            mItems = mSerializer.loadItems();
        } catch (Exception e) {
            mItems = new ArrayList<Items>();
            Log.e(TAG, "Error loading items: ", e);
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

    public void addItem(Items i) {
        mItems.add(i);
    }

    public void deleteItems(Items i) {
        mItems.remove(i);
        saveItems();
    }

    public boolean saveItems() {
        try {
            mSerializer.saveItems(mItems);
            Log.d(TAG, "items saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving items: ", e);
            return false;
        }
    }
}
