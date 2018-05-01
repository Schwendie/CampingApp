package capstone.schwendimankw.campingapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChecklistFragment extends ListFragment {
    public static final String EXTRA_CHECK_ID =
            "capstone.schwendimankw.campingapp.checklist_id";

    private ArrayList<Items> mItems;
    private Checklist mChecklist;
    private RelativeLayout mEmptyView;
    private Button mEmptyViewAddButton;
    private RecyclerView mItemRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        UUID checklistId = (UUID)getActivity().getIntent()
                .getSerializableExtra(EXTRA_CHECK_ID);

        mChecklist = ListPlanner.get(getActivity()).getCheck(checklistId);

        mItems = Gear.get(getActivity()).getItems();

        ItemsAdapter adapter = new ItemsAdapter(mItems);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, parent, savedInstanceState);

        ListView listView = (ListView)view.findViewById(android.R.id.list);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            // Use floating context menus on Froyo and Gingerbread
            registerForContextMenu(listView);
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
                    inflater.inflate(R.menu.checklist_item_context, menu);
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
                        case R.id.menu_item_delete_items:
                            ItemsAdapter adapter = (ItemsAdapter)getListAdapter();
                            Gear gear = Gear.get(getActivity());
                            for (int i = adapter.getCount() - 1; i >= 0; i--) {
                                if (getListView().isItemChecked(i)) {
                                    gear.deleteItems(adapter.getItem(i));
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
        Items it = ((ItemsAdapter)getListAdapter()).getItem(position);

        Intent i = new Intent(getActivity(), ItemsPagerActivity.class);
        i.putExtra(ItemsFragment.EXTRA_ITEMS_ID, it.getId());
        startActivity(i);
    }

    /*public void updateUI() {
        Gear gear = Gear.get(getActivity());
        List<Items> items =
    }*/

    @Override
    public void onResume() {
        super.onResume();
        ((ItemsAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        Gear.get(getActivity()).saveItems();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.fragment_checklist, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_item:
                Items it = new Items();
                Gear.get(getActivity()).addItem(it);
                Intent i = new Intent(getActivity(), ItemsPagerActivity.class);
                i.putExtra(ItemsFragment.EXTRA_ITEMS_ID, it.getId());
                startActivityForResult(i, 0);
                return true;
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
        ItemsAdapter adapter = (ItemsAdapter)getListAdapter();
        Items it = adapter.getItem(position);

        switch (item.getItemId()) {
            case R.id.menu_item_delete_items:
                Gear.get(getActivity()).deleteItems(it);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
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
