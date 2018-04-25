package capstone.schwendimankw.campingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

public class ChecklistFragment extends ListFragment {
    public static final String EXTRA_CHECK_ID =
            "capstone.schwendimankw.campingapp.checklist_id";

    private ArrayList<Items> mItems;
    private Checklist mChecklist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID checklistId = (UUID)getActivity().getIntent()
                .getSerializableExtra(EXTRA_CHECK_ID);

        mChecklist = ListPlanner.get(getActivity()).getCheck(checklistId);

        // ********************************************************************
        // Take a look at later -
        // This may make the checklist blank each time it's loaded?
        mItems = new ArrayList<Items>();

        ItemsAdapter adapter = new ItemsAdapter(mItems);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Items it = ((ItemsAdapter)getListAdapter()).getItem(position);

        Intent i = new Intent(getActivity(), ItemsActivity.class);
        i.putExtra(ItemsFragment.EXTRA_ITEMS_ID, it.getId());
        startActivity(i);
    }

    private class ItemsAdapter extends ArrayAdapter<Items> {

        public ItemsAdapter(ArrayList<Items> items) {
            super(getActivity(), 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_item, null);
            }

            Items it = getItem(position);

            TextView nameTextView =
                    (TextView)convertView.findViewById(R.id.item_nameTextView);
            nameTextView.setText(it.getName());

            return convertView;
        }
    }
}
