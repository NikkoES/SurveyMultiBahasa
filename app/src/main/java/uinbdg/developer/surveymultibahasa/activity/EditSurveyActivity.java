package uinbdg.developer.surveymultibahasa.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uinbdg.developer.surveymultibahasa.R;
import uinbdg.developer.surveymultibahasa.adapter.QuestionAdapter;
import uinbdg.developer.surveymultibahasa.adapter.SurveyAdapter;
import uinbdg.developer.surveymultibahasa.api.BaseApiService;
import uinbdg.developer.surveymultibahasa.api.UtilsApi;
import uinbdg.developer.surveymultibahasa.model.Question;
import uinbdg.developer.surveymultibahasa.model.ResponseQuestion;
import uinbdg.developer.surveymultibahasa.model.ResponseSurvey;

public class EditSurveyActivity extends AppCompatActivity {

    private RecyclerView rvQuestion;
    private QuestionAdapter adapter;
    List<Question> listQuetion = new ArrayList<>();

    private FloatingActionButton fab;

    ProgressDialog loading;

    BaseApiService apiService;

    String idSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_survey);

        idSurvey = getIntent().getStringExtra("idSurvey");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Survey ");

        rvQuestion = (RecyclerView) findViewById(R.id.rv_question);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(getApplicationContext(), AddQuestionActivity.class);
                i.putExtra("idSurvey", idSurvey);
                startActivity(i);
            }
        });

        apiService = UtilsApi.getAPIService();

        adapter = new QuestionAdapter(getApplicationContext(), listQuetion);

        rvQuestion.setHasFixedSize(true);
        rvQuestion.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvQuestion.setAdapter(adapter);

        refresh();
    }

    public void refresh() {
        loading = ProgressDialog.show(EditSurveyActivity.this, null, "Harap Tunggu...", true, false);

        apiService.getListQuestion(idSurvey).enqueue(new Callback<ResponseQuestion>() {
            @Override
            public void onResponse(Call<ResponseQuestion> call, Response<ResponseQuestion> response) {
                if (response.isSuccessful()){
                    loading.dismiss();

                    listQuetion = response.body().getQuestionList();

                    rvQuestion.setAdapter(new QuestionAdapter(getApplicationContext(), listQuetion));
                    adapter.notifyDataSetChanged();
                } else {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseQuestion> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
