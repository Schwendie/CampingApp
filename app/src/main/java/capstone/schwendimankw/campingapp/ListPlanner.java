package capstone.schwendimankw.campingapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class ListPlanner {
    private ArrayList<Checklist> mChecks;

    private static ListPlanner sListPlanner;
    private Context mAppContext;

    private ListPlanner(Context appContext) {
        mAppContext = appContext;
        mChecks = new ArrayList<Checklist>();
        for (int i = 0; i < 100; i++) {
            Checklist c = new Checklist();
            c.setTitle("List #" + i);
            mChecks.add(c);
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
}
