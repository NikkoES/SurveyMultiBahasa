package uinbdg.developer.surveymultibahasa.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uinbdg.developer.surveymultibahasa.R;
import uinbdg.developer.surveymultibahasa.api.BaseApiService;
import uinbdg.developer.surveymultibahasa.api.UtilsApi;

public class AddQuestionActivity extends AppCompatActivity {

    private RadioButton rbText, rbMultiple;
    private LinearLayout llAnswerChoice;

    private EditText etQuestion, etOptionSatu, etOptionDua, etOptionTiga, etOptionEmpat;
    private TextView btnCancel, btnSave;

    String idSurvey, tipeSurvey = "text";

    ProgressDialog loading;

    BaseApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        idSurvey = getIntent().getStringExtra("idSurvey");

        rbText = (RadioButton) findViewById(R.id.rb_text);
        rbMultiple = (RadioButton) findViewById(R.id.rb_multiple);
        llAnswerChoice = (LinearLayout) findViewById(R.id.layout_answer_choice);

        etQuestion = (EditText) findViewById(R.id.et_question);
        etOptionSatu = (EditText) findViewById(R.id.et_option_1);
        etOptionDua = (EditText) findViewById(R.id.et_option_2);
        etOptionTiga = (EditText) findViewById(R.id.et_option_3);
        etOptionEmpat = (EditText) findViewById(R.id.et_option_4);

        btnCancel = (TextView) findViewById(R.id.btn_cancel);
        btnSave = (TextView) findViewById(R.id.btn_save);

        rbText.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                llAnswerChoice.setVisibility(View.VISIBLE);
                tipeSurvey = "multiple";
            }
        });

        rbMultiple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                llAnswerChoice.setVisibility(View.GONE);
                tipeSurvey = "text";
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24px);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Question");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        apiService = UtilsApi.getAPIService();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveQuestion(tipeSurvey, etQuestion.getText().toString(),
                        etOptionSatu.getText().toString(), etOptionDua.getText().toString(), etOptionTiga.getText().toString(),
                        etOptionEmpat.getText().toString(), idSurvey);
                finish();
                startActivity(new Intent(AddQuestionActivity.this, EditSurveyActivity.class).putExtra("idSurvey",idSurvey));
            }
        });
    }

    private void saveQuestion(String tipeQuestion, String question, String optionSatu, String optionDua, String optionTiga, String optionEmpat, String idSurvey){
        loading = ProgressDialog.show(AddQuestionActivity.this, null, "Harap Tunggu...", true, false);

        apiService.addQuestion(tipeQuestion, question, optionSatu, optionDua, optionTiga, optionEmpat, idSurvey)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Success to adding Question", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Gagal menambahkan data matkul", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
