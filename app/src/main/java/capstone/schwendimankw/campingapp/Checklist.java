package capstone.schwendimankw.campingapp;

import java.util.Date;
import java.util.UUID;

public class Checklist {

    private UUID mId;
    private String mTitle;
    private Date mDate;

    public Checklist() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
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
