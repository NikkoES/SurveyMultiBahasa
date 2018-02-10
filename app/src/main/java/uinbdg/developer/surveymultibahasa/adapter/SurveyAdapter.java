package uinbdg.developer.surveymultibahasa.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uinbdg.developer.surveymultibahasa.R;
import uinbdg.developer.surveymultibahasa.model.ActiveSurvey;
import uinbdg.developer.surveymultibahasa.model.Survey;

/**
 * Created by Nikko Eka Saputra on 10/02/2018.
 */

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.ViewHolder> {

    private Context context;
    private List<Survey> listSurvey;

    public SurveyAdapter(Context context, List<Survey> listSurvey){
        this.context = context;
        this.listSurvey = listSurvey;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_survey, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layoutParams);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Survey survey = listSurvey.get(position);
        holder.lblNamaSurvey.setText(survey.getNamaSurvey());
        holder.lblDateModified.setText(survey.getModifiedAt());
        holder.lblSurvey.setText("survey");
        holder.lblModified.setText("Modified At");
        holder.imgSurvey.setImageResource(R.drawable.ic_list_black_24px);

        holder.cvSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, survey.getNamaSurvey(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSurvey.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvSurvey;
        private ImageView imgSurvey;
        private TextView lblSurvey, lblModified;
        private TextView lblNamaSurvey, lblDateModified;

        public ViewHolder(View itemView) {
            super(itemView);

            cvSurvey = (CardView) itemView.findViewById(R.id.cv_survey);
            lblNamaSurvey = (TextView) itemView.findViewById(R.id.tv_nama_survey);
            lblDateModified = (TextView) itemView.findViewById(R.id.tv_date);
            lblSurvey = (TextView) itemView.findViewById(R.id.tv_survey);
            lblModified = (TextView) itemView.findViewById(R.id.tv_modified_at);
            imgSurvey = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
