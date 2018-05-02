package capstone.schwendimankw.campingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Kyle Schwendiman on 4/7/2018.
 */

public class ReferenceFragment extends Fragment {
    public static final String EXTRA_REF_TITLE =
            "capstone.schwendimankw.campingapp.reference_title";
    public static final String EXTRA_REF_URL =
            "capstone.schwendimankw.campingapp.reference_url";
    public static final String EXTRA_REF_ICON =
            "capstone.schwendimankw.campingapp.reference_icon";

    private Reference mReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = (String)getActivity().getIntent()
                .getSerializableExtra(EXTRA_REF_TITLE);
        String url = (String)getActivity().getIntent()
                .getSerializableExtra(EXTRA_REF_URL);
        int iconId = (int)getActivity().getIntent()
                .getSerializableExtra(EXTRA_REF_ICON);

        mReference = new Reference(title, url, iconId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reference, parent, false);

        //WebView webView = (WebView)v.findViewById(R.id.webView);
        return v;
    }
}
