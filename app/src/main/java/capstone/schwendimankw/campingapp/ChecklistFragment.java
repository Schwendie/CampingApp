package capstone.schwendimankw.campingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist, container, false);

        mEmptyView = (RelativeLayout)view.findViewById(R.id.empty_view);
        mEmptyViewAddButton = (Button)mEmptyView.findViewById(R.id.empty_view_add_button);

        mEmptyViewAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAnItem();
            }
        });

        mItemRecyclerView = (RecyclerView)view.findViewById(R.id.item_recycler_view);
        mItemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }*/

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

    private void addAnItem() {
        Items items = new Items();
        Gear.get(getActivity()).addItem(items);

    }
}
