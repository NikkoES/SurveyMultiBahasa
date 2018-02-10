package uinbdg.developer.surveymultibahasa.activity;

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

import uinbdg.developer.surveymultibahasa.R;

public class AddQuestionActivity extends AppCompatActivity {

    private RadioButton rbText, rbMultiple;
    private LinearLayout llAnswerChoice;

    private EditText etQuestion, etOptionSatu, etOptionDua, etOptionTiga, etOptionEmpat;
    private TextView btnCancel, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

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
            }
        });

        rbMultiple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                llAnswerChoice.setVisibility(View.GONE);
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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(AddQuestionActivity.this, ""+etQuestion.getText().toString(), Toast.LENGTH_SHORT).show();
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
