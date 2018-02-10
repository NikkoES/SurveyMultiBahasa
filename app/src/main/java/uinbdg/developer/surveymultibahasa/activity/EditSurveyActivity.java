package uinbdg.developer.surveymultibahasa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import uinbdg.developer.surveymultibahasa.R;

public class EditSurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_survey);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Profil");

        String namaSurvey = getIntent().getStringExtra("namaSurvey");
        Toast.makeText(this, ""+namaSurvey, Toast.LENGTH_SHORT).show();
    }
}
