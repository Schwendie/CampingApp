package capstone.schwendimankw.campingapp;

import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SurvivingListFragment extends ListFragment {
    private ArrayList<Reference> mReferences;
    private static final String TAG = "SurvivingListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.camping_list);
        mReferences = ReferenceManual.get(getActivity()).getReferences();

        ReferenceAdapter adapter = new ReferenceAdapter(mReferences);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Reference r = ((ReferenceAdapter)getListAdapter()).getItem(position);

        String title = r.getTitle();
        String url = r.getURL();
        int listIconId = r.getListIconId();

        Intent i;

        switch (listIconId) {
            case 1:
                // Orienteering Activity
                i = new Intent(getActivity(), FireActivity.class);
                startActivity(i);
                break;
            default:
                break;

        }
    }

    private class ReferenceAdapter extends ArrayAdapter<Reference> {

        public ReferenceAdapter(ArrayList<Reference> refs) {
            super(getActivity(), 0, refs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we aren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_reference, null);
            }

            // Configure the view for this Reference
            Reference r = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.camping_list_item_titleTextView);
            titleTextView.setText(r.getTitle());
            ImageView referenceIcon =
                    (ImageView)convertView.findViewById(R.id.camping_list_item_referenceIcon);
            referenceIcon.setImageResource(R.drawable.netero_character);

            return convertView;
        }
    }
}
