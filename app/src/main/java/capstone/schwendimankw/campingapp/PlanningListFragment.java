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

/**
 * Created by Kyle Schwendiman on 4/10/2018.
 */

public class PlanningListFragment extends ListFragment {
    private ArrayList<Checklist> mChecks;

    private static final String TAG = "PlanningListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.id.planning_button);
        mChecks = ListPlanner.get(getActivity()).getChecks();

        CheckAdapter adapter = new CheckAdapter(mChecks);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Checklist c = ((CheckAdapter)getListAdapter()).getItem(position);

        Intent i = new Intent(getActivity(), ChecklistActivity.class);
        i.putExtra(ChecklistFragment.EXTRA_CHECK_ID, c.getId());
        startActivity(i);
    }

    private class CheckAdapter extends ArrayAdapter<Checklist> {

        public CheckAdapter(ArrayList<Checklist> checks) {
            super(getActivity(), 0, checks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_checklist, null);
            }

            Checklist c = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.checklist_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.checklist_item_dateTextView);
            dateTextView.setText(c.getDate().toString());

            return convertView;
        }
    }
}
