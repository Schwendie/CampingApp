package capstone.schwendimankw.campingapp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Kyle Schwendiman on 4/10/2018.
 */

public class PlanningListFragment extends ListFragment {

    private static final String TAG = "PlanningListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Checklists");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }
}
