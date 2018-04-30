package capstone.schwendimankw.campingapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class Items {

    private static final String JSON_ID = "id";
    private static final String JSON_NAME = "name";

    private String mName;
    private UUID mId;

    public Items() {
        mId = UUID.randomUUID();
        mName = "";
    }

    public Items(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        if (json.has(JSON_NAME)) {
            mName = json.getString(JSON_NAME);
        }
    }

    public Items(String name) {
        mId = UUID.randomUUID();
        mName = name;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_NAME, mName);
        return json;
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
