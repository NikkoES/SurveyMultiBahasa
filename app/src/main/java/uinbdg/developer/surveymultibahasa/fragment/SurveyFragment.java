package uinbdg.developer.surveymultibahasa.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uinbdg.developer.surveymultibahasa.R;
import uinbdg.developer.surveymultibahasa.activity.EditSurveyActivity;
import uinbdg.developer.surveymultibahasa.adapter.SurveyAdapter;
import uinbdg.developer.surveymultibahasa.api.BaseApiService;
import uinbdg.developer.surveymultibahasa.api.UtilsApi;
import uinbdg.developer.surveymultibahasa.model.ResponseSurvey;
import uinbdg.developer.surveymultibahasa.model.Survey;

public class SurveyFragment extends Fragment {

    private RecyclerView rvSurvey;
    private SurveyAdapter adapter;
    List<Survey> listSurvey = new ArrayList<>();

    private FloatingActionButton fab;

    private TextView tvJudulSurvey;
    private EditText etNamaSurvey;

    ProgressDialog loading;

    BaseApiService apiService;

    public SurveyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_survey, container, false);

        rvSurvey = (RecyclerView) view.findViewById(R.id.rv_survey);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        apiService = UtilsApi.getAPIService();

        adapter = new SurveyAdapter(getContext(), listSurvey);

        rvSurvey.setHasFixedSize(true);
        rvSurvey.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSurvey.setAdapter(adapter);

        refresh();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNamaSurvey = new EditText(getContext());
                etNamaSurvey.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);

                tvJudulSurvey = new TextView(getContext());
                tvJudulSurvey.setText("Survey Title");
                tvJudulSurvey.setGravity(0);

                LinearLayout ll = new LinearLayout(getContext());
                ll.setPadding(48,16,48,16);
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.addView(tvJudulSurvey);
                ll.addView(etNamaSurvey);
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("New Survey")
                        .setView(ll)
                        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                createNewSurvey(etNamaSurvey.getText().toString());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });

        return view;
    }

    private void createNewSurvey(String namaSurvey){
        loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);

        apiService.createSurvey(namaSurvey)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            Toast.makeText(getContext(), "Success to adding new Survey", Toast.LENGTH_SHORT).show();
                            refresh();
                        } else {
                            loading.dismiss();
                            Toast.makeText(getContext(), "Gagal menambahkan data matkul", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(getContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void refresh() {
        loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);

        apiService.getListSurvey().enqueue(new Callback<ResponseSurvey>() {
            @Override
            public void onResponse(Call<ResponseSurvey> call, Response<ResponseSurvey> response) {
                if (response.isSuccessful()){
                    loading.dismiss();

                    listSurvey = response.body().getSurveyList();

                    rvSurvey.setAdapter(new SurveyAdapter(getContext(), listSurvey));
                    adapter.notifyDataSetChanged();
                } else {
                    loading.dismiss();
                    Toast.makeText(getContext(), "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSurvey> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getContext(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
