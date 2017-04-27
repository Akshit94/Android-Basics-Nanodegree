package com.example.jainsaab.tourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class AttractionsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public AttractionsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AttractionsFragment newInstance(int sectionNumber) {
        AttractionsFragment fragment = new AttractionsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_attractions, container, false);

        ArrayList<Location> locationsArray = new ArrayList<>();
        ListView listView = (ListView) rootView.findViewById(R.id.attractions_list_view);
        LocationAdapter adapter = new LocationAdapter(getActivity(), locationsArray);
        listView.setAdapter(adapter);

        if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
            Location location1 = new Location(getString(R.string.qutub_minar), getString(R.string.qutub_addr), R.drawable.qutub_minar);
            Location location2 = new Location(getString(R.string.india_gate), getString(R.string.india_gate_addr), R.drawable.india_gate);
            Location location3 = new Location(getString(R.string.tv_tower), getString(R.string.tv_tower_addr), R.drawable.tv_tower);
            Location location4 = new Location(getString(R.string.humayu_tomb), getString(R.string.humayu_addr), R.drawable.humayu_tomb);

            adapter.add(location1);
            adapter.add(location2);
            adapter.add(location3);
            adapter.add(location4);
        } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {

            Location location1 = new Location(getString(R.string.akshardham_temple), getString(R.string.akshardhaam_addr), R.drawable.akshardham_temple);
            Location location2 = new Location(getString(R.string.lotus_temple), getString(R.string.lotus_addr), R.drawable.india_gate);
            Location location3 = new Location(getString(R.string.bangla_sahib), getString(R.string.bangla_sahib_addr), R.drawable.bangla_sahib);
            Location location4 = new Location(getString(R.string.jama_masjid), getString(R.string.jama_addr), R.drawable.jama_masjid);

            adapter.add(location1);
            adapter.add(location2);
            adapter.add(location3);
            adapter.add(location4);

        } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {

            Location location1 = new Location(getString(R.string.pacific_mall), getString(R.string.pacific_addr), 0);
            Location location2 = new Location(getString(R.string.connaught_place), getString(R.string.cp_addr), 0);
            Location location3 = new Location(getString(R.string.ambience_mall), getString(R.string.ambience_addr), 0);
            Location location4 = new Location(getString(R.string.chawri_bazaar), getString(R.string.chawri_addr), 0);

            adapter.add(location1);
            adapter.add(location2);
            adapter.add(location3);
            adapter.add(location4);

        } else {

            Location location1 = new Location(getString(R.string.dilli_haat), getString(R.string.dilli_haat_addr), 0);
            Location location2 = new Location(getString(R.string.indira_gandhi), getString(R.string.indra_addr), 0);
            Location location3 = new Location(getString(R.string.feroz_shah), getString(R.string.feroz_shah_addr), 0);
            Location location4 = new Location(getString(R.string.jln_stadium), getString(R.string.jln_addr), 0);

            adapter.add(location1);
            adapter.add(location2);
            adapter.add(location3);
            adapter.add(location4);

        }

        return rootView;
    }
}
