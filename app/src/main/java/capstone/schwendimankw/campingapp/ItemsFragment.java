package capstone.schwendimankw.campingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

public class ItemsFragment extends Fragment {
    public static final String EXTRA_ITEMS_ID =
            "capstone.schwendimankw.campingapp.items_id";

    private Items mItems;
    private EditText mNameField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID itemId = (UUID)getActivity().getIntent()
                .getSerializableExtra(EXTRA_ITEMS_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item, parent, false);

        mNameField = (EditText)v.findViewById(R.id.item_name);
        mNameField.setText(mItems.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mItems.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return v;
    }
}
