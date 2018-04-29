package capstone.schwendimankw.campingapp;

import java.util.UUID;

public class Items {

    private String mName;
    private UUID mId;

    public Items() {
        mId = UUID.randomUUID();
        mName = "";
    }

    public Items(String name) {
        mId = UUID.randomUUID();
        mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
