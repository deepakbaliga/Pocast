package com.deepakbaliga.pocast.intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deepakbaliga.pocast.PocastApp;
import com.deepakbaliga.pocast.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOffline extends Fragment {

    private TextView title, subtitle;
    public FragmentOffline() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_minimal_intro, container, false);

        title = (TextView) view.findViewById(R.id.textview_intro_title);
        subtitle = (TextView) view.findViewById(R.id.textview_intro_subtitle);

        title.setTypeface(PocastApp.robotoBlack);
        subtitle.setTypeface(PocastApp.robotoLight);
        return view;
    }

}
