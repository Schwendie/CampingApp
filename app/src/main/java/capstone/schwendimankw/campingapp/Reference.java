package capstone.schwendimankw.campingapp;

import java.util.UUID;

/**
 * Created by Kyle Schwendiman on 4/6/2018.
 */

public class Reference extends Object {

    private UUID mId;
    private String mTitle;
    private int mListIconId;

    public Reference() {
        mId = UUID.randomUUID();
    }

    public Reference(int ListIconId) {
        mId = UUID.randomUUID();
        mListIconId = ListIconId;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
