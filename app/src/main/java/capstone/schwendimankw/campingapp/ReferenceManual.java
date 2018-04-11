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
        for (int i = 0; i < 100; i++) {
            Reference r = new Reference();
            r.setTitle("Reference #" + i);
            mReferences.add(r);
        }
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
