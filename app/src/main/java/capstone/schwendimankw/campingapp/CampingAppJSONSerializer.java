package capstone.schwendimankw.campingapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class CampingAppJSONSerializer {

    private Context mContext;
    private String mFileName;

    public CampingAppJSONSerializer(Context c, String f) {
        mContext = c;
        mFileName = f;
    }

    public ArrayList<Items> loadItems() throws IOException, JSONException {
        ArrayList<Items> items = new ArrayList<Items>();
        BufferedReader reader = null;
        try {
            // Open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // Parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
                    .nextValue();
            // Build the array of items from JSONObjects
            for (int i = 0; i < array.length(); i++) {
                items.add(new Items(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // Ignore this one; it happens when starting fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return items;
    }

    public void saveItems(ArrayList<Items> items) throws JSONException, IOException {
        JSONArray array = new JSONArray();
        for (Items i : items)
            array.put(i.toJSON());

        Writer writer = null;
        try {
            OutputStream out = mContext
                    .openFileOutput(mFileName, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public ArrayList<Checklist> loadChecklist() throws IOException, JSONException {
        ArrayList<Checklist> checklists = new ArrayList<Checklist>();
        BufferedReader reader = null;
        try {
            // Open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // Parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
                    .nextValue();
            // Build the array of checklists from JSONObjects
            for (int i = 0; i < array.length(); i++) {
                checklists.add(new Checklist(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // Ignore this one; it happens when starting fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return checklists;
    }

    public void saveChecklist(ArrayList<Checklist> checklists) throws JSONException, IOException {
        JSONArray array = new JSONArray();
        for (Checklist c : checklists)
            array.put(c.toJSON());

        Writer writer = null;
        try {
            OutputStream out = mContext
                    .openFileOutput(mFileName, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
