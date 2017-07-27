package com.smarttab.google_map.Custom_Navigation_Drawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smarttab.google_map.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fixtures extends Fragment {


    public fixtures() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_fixtures, container, false);

        Toast.makeText(getActivity(), "Hello Ia ", Toast.LENGTH_SHORT).show();
        return rootview;
    }

}
