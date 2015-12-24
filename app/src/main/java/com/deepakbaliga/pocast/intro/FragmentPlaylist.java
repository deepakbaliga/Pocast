package com.deepakbaliga.pocast.intro;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.deepakbaliga.pocast.ActivityMain;
import com.deepakbaliga.pocast.PocastApp;
import com.deepakbaliga.pocast.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPlaylist extends Fragment {

    private Button startButton;
    private TextView title, subtitle;


    public FragmentPlaylist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_playlist_intro, container, false);
        startButton = (Button) view.findViewById(R.id.button_start);



        title = (TextView) view.findViewById(R.id.textview_intro_title);
        subtitle = (TextView) view.findViewById(R.id.textview_intro_subtitle);

        title.setTypeface(PocastApp.robotoBlack);
        subtitle.setTypeface(PocastApp.robotoLight);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityMain.class));
                getActivity().finish();
            }
        });

        return view;
    }

}
