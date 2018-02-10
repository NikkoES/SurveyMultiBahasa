package uinbdg.developer.surveymultibahasa.fragment;

import android.app.AlertDialog;
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

import uinbdg.developer.surveymultibahasa.R;
import uinbdg.developer.surveymultibahasa.activity.EditSurveyActivity;
import uinbdg.developer.surveymultibahasa.adapter.SurveyAdapter;
import uinbdg.developer.surveymultibahasa.model.Survey;

public class SurveyFragment extends Fragment {

    private RecyclerView rvSurvey;
    private SurveyAdapter adapter;
    private List<Survey> listSurvey;

    private FloatingActionButton fab;

    private TextView tvJudulSurvey;
    private EditText etNamaSurvey;

    public SurveyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_survey, container, false);

        rvSurvey = (RecyclerView) view.findViewById(R.id.rv_survey);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

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
                                Intent i = new Intent(getActivity(), EditSurveyActivity.class);
                                i.putExtra("namaSurvey", etNamaSurvey.getText().toString());
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });

        listSurvey = new ArrayList<>();
        listSurvey.add(new Survey("Nama Survey Pertama","05/02/2018"));
        listSurvey.add(new Survey("Nama Survey Kedua", "05/02/2018"));
        listSurvey.add(new Survey("Nama Survey Ketiga", "05/02/2018"));
        listSurvey.add(new Survey("Nama Survey Keempat", "05/02/2018"));

        adapter = new SurveyAdapter(getContext(), listSurvey);

        rvSurvey.setHasFixedSize(true);
        rvSurvey.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSurvey.setAdapter(adapter);

        return view;
    }

}
