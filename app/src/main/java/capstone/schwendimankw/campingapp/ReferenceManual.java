package capstone.schwendimankw.campingapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Kyle Schwendiman on 4/6/2018.
 */

public class ReferenceManual {
    private ArrayList<Reference> mReferences;

    private static ReferenceManual sReferenceManual;
    private Context mAppContext;

    private ReferenceManual(Context appContext) {
        mAppContext = appContext;
        mReferences = new ArrayList<Reference>();
        mReferences.add(new Reference("Orienteering", "file:///android_asset/orienteering.html", 0));
        mReferences.add(new Reference("Fire", "file:///android_asset/fire.html", 1));
    }

    public static ReferenceManual get(Context c) {
        if (sReferenceManual == null) {
            sReferenceManual = new ReferenceManual(c.getApplicationContext());
        }
        return sReferenceManual;
    }

    public ArrayList<Reference> getReferences() {
        return mReferences;
    }

    public Reference getReference(UUID id) {
        for (Reference r : mReferences) {
            if (r.getId().equals(id))
                return r;
        }
        return null;
    }
}
