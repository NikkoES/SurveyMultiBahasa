package uinbdg.developer.surveymultibahasa.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uinbdg.developer.surveymultibahasa.R;
import uinbdg.developer.surveymultibahasa.adapter.HomeAdapter;
import uinbdg.developer.surveymultibahasa.adapter.SurveyAdapter;
import uinbdg.developer.surveymultibahasa.api.BaseApiService;
import uinbdg.developer.surveymultibahasa.api.UtilsApi;
import uinbdg.developer.surveymultibahasa.model.ActiveSurvey;
import uinbdg.developer.surveymultibahasa.model.ResponseActiveSurvey;
import uinbdg.developer.surveymultibahasa.model.ResponseSurvey;
import uinbdg.developer.surveymultibahasa.model.Survey;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView rvHome;
    private HomeAdapter adapter;
    List<ActiveSurvey> listActiveSurvey = new ArrayList<>();

    ProgressDialog loading;

    BaseApiService apiService;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);

        adapter = new HomeAdapter(getContext(), listActiveSurvey);

        apiService = UtilsApi.getAPIService();

        rvHome.setHasFixedSize(true);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHome.setAdapter(adapter);

        refresh();

        return view;
    }

    public void refresh() {
        loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);

        apiService.getListActiveSurvey().enqueue(new Callback<ResponseActiveSurvey>() {
            @Override
            public void onResponse(Call<ResponseActiveSurvey> call, Response<ResponseActiveSurvey> response) {
                if (response.isSuccessful()){
                    loading.dismiss();

                    listActiveSurvey = response.body().getSurveyList();

                    rvHome.setAdapter(new HomeAdapter(getContext(), listActiveSurvey));
                    adapter.notifyDataSetChanged();
                } else {
                    loading.dismiss();
                    Toast.makeText(getContext(), "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseActiveSurvey> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getContext(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
