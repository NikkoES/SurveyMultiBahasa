package uinbdg.developer.surveymultibahasa.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import uinbdg.developer.surveymultibahasa.R;
import uinbdg.developer.surveymultibahasa.adapter.HomeAdapter;
import uinbdg.developer.surveymultibahasa.model.ActiveSurvey;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView rvHome;
    private HomeAdapter adapter;
    private List<ActiveSurvey> listActiveSurvey;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);

        listActiveSurvey = new ArrayList<>();
        listActiveSurvey.add(new ActiveSurvey("Nama Survey Pertama","8", "05/02/2018"));
        listActiveSurvey.add(new ActiveSurvey("Nama Survey Kedua","0", "05/02/2018"));
        listActiveSurvey.add(new ActiveSurvey("Nama Survey Ketiga","3", "05/02/2018"));
        listActiveSurvey.add(new ActiveSurvey("Nama Survey Keempat","12", "05/02/2018"));

        adapter = new HomeAdapter(getContext(), listActiveSurvey);

        rvHome.setHasFixedSize(true);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHome.setAdapter(adapter);

        return view;
    }

}
