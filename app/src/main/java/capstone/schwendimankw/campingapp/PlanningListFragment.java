package capstone.schwendimankw.campingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Kyle Schwendiman on 4/10/2018.
 */

public class PlanningListFragment extends ListFragment {
    private ArrayList<Checklist> mChecks;
    private Checklist mChecklist;

    private static final String TAG = "PlanningListFragment";

    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        getActivity().setTitle(R.id.planning_button);
        mChecks = ListPlanner.get(getActivity()).getChecks();

        CheckAdapter adapter = new CheckAdapter(mChecks);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, parent, savedInstanceState);

        ListView listView = (ListView)view.findViewById(android.R.id.list);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            // Use floating context menus on Froyo and Gingerbread
            registerForContextMenu(view);
        } else {
            // Use contextual action bar on Honeycomb and higher
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                    // Required, but not used in this implementation
                }

                @Override
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    MenuInflater inflater = actionMode.getMenuInflater();
                    inflater.inflate(R.menu.planning_list_item_context, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                    // Required, but not used in this implementation
                }

                @Override
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.menu_item_delete_checklist:
                            CheckAdapter adapter = (CheckAdapter)getListAdapter();
                            ListPlanner lp = ListPlanner.get(getActivity());
                            for (int i = adapter.getCount() - 1; i >= 0; i--) {
                                if (getListView().isItemChecked(i)) {
                                    lp.deleteCheck(adapter.getItem(i));
                                }
                            }
                            actionMode.finish();
                            adapter.notifyDataSetChanged();
                            return true;
                        default:
                            return false;
                    }
                }

                @Override
                public void onDestroyActionMode(ActionMode actionMode) {
                    // Required, but not used in this implementation
                }
            });
        }

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Checklist c = ((CheckAdapter)getListAdapter()).getItem(position);

        Intent i = new Intent(getActivity(), ChecklistActivity.class);
        i.putExtra(ChecklistFragment.EXTRA_CHECK_ID, c.getId());
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CheckAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        ListPlanner.get(getActivity()).saveChecks();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.fragment_planning_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_check:
                /*Checklist checklist = new Checklist();
                ListPlanner.get(getActivity()).addCheck(checklist);
                Intent i = new Intent(getActivity(), ChecklistActivity.class);
                i.putExtra(ChecklistFragment.EXTRA_CHECK_ID, checklist.getId());
                startActivityForResult(i, 0);
                return true;*/

                Checklist checklist = new Checklist();
                ListPlanner.get(getActivity()).addCheck(checklist);

                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(checklist.getDate(), checklist.getTitle(), checklist.getId());
                dialog.setTargetFragment(PlanningListFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.checklist_item_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position = info.position;
        CheckAdapter adapter = (CheckAdapter)getListAdapter();
        Checklist checklist = adapter.getItem(position);

        switch (item.getItemId()) {
            case R.id.menu_item_delete_checklist:
                ListPlanner.get(getActivity()).deleteCheck(checklist);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date)data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            String title = (String)data
                    .getSerializableExtra(DatePickerFragment.EXTRA_TITLE);
            UUID id = (UUID)data
                    .getSerializableExtra(DatePickerFragment.EXTRA_ID);

            mChecklist = ListPlanner.get(getActivity()).getCheck(id);

            mChecklist.setDate(date);
            mChecklist.setTitle(title);

            CheckAdapter adapter = (CheckAdapter)getListAdapter();
            adapter.notifyDataSetChanged();
        }
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
