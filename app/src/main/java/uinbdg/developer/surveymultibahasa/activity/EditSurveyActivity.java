package uinbdg.developer.surveymultibahasa.activity;

import android.app.AlertDialog;
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

import uinbdg.developer.surveymultibahasa.R;
import uinbdg.developer.surveymultibahasa.adapter.QuestionAdapter;
import uinbdg.developer.surveymultibahasa.model.Question;

public class EditSurveyActivity extends AppCompatActivity {

    private RecyclerView rvQuestion;
    private QuestionAdapter adapter;
    private List<Question> listQuetion;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_survey);

        String namaSurvey = getIntent().getStringExtra("namaSurvey");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Survey "+namaSurvey);

        rvQuestion = (RecyclerView) findViewById(R.id.rv_question);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddQuestionActivity.class);
                startActivity(i);
            }
        });

        listQuetion = new ArrayList<>();
        listQuetion.add(new Question("text","Apakah yang dimaksud dengan ini ?","","","",""));
        listQuetion.add(new Question("multiple","Manakah yang dimaksud dengan ini ?","Ini","Itu","Sana","Situ"));
        listQuetion.add(new Question("text","Apakah yang dimaksud dengan itu ?","","","",""));
        listQuetion.add(new Question("multiple","Manakah yang dimaksud dengan itu ?","Ini","Itu","Sana","Situ"));

        adapter = new QuestionAdapter(getApplicationContext(), listQuetion);

        rvQuestion.setHasFixedSize(true);
        rvQuestion.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvQuestion.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
