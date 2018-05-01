package capstone.schwendimankw.campingapp;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class ListPlanner {
    private static final String TAG = "ListPlanner";
    private static final String FILENAME = "checklist.json";

    private ArrayList<Checklist> mChecks;
    private CampingAppJSONSerializer mSerializer;

    private static ListPlanner sListPlanner;
    private Context mAppContext;

    private ListPlanner(Context appContext) {
        mAppContext = appContext;
        mSerializer = new CampingAppJSONSerializer(mAppContext, FILENAME);

        try {
            mChecks = mSerializer.loadChecklist();
        } catch (Exception e) {
            mChecks = new ArrayList<Checklist>();
            Log.e(TAG, "Error loading checklist: ", e);
        }
    }

    public static ListPlanner get(Context c) {
        if (sListPlanner == null) {
            sListPlanner = new ListPlanner(c.getApplicationContext());
        }
        return sListPlanner;
    }

    public ArrayList<Checklist> getChecks() {
        return mChecks;
    }

    public Checklist getCheck(UUID id) {
        for (Checklist c : mChecks) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

    public void addCheck(Checklist check) {
        mChecks.add(check);
    }

    public void deleteCheck(Checklist check) {
        mChecks.remove(check);
        saveChecks();
    }

    public boolean saveChecks() {
        try {
            mSerializer.saveChecklist(mChecks);
            Log.d(TAG, "checklists saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving checklists: ", e);
            return false;
        }
    }
}
