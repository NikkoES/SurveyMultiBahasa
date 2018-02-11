package uinbdg.developer.surveymultibahasa.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uinbdg.developer.surveymultibahasa.R;
import uinbdg.developer.surveymultibahasa.activity.EditSurveyActivity;
import uinbdg.developer.surveymultibahasa.model.ActiveSurvey;
import uinbdg.developer.surveymultibahasa.model.Survey;

/**
 * Created by Nikko Eka Saputra on 10/02/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<ActiveSurvey> listActiveSurvey;

    public HomeAdapter(Context context, List<ActiveSurvey> listActiveSurvey){
        this.context = context;
        this.listActiveSurvey = listActiveSurvey;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layoutParams);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ActiveSurvey activeSurvey = listActiveSurvey.get(position);
        holder.lblNamaSurvey.setText(activeSurvey.getNamaSurvey());
        holder.lblDateCreated.setText(activeSurvey.getCreatedAt());
        holder.lblActiveSurvey.setText("active survey");
        holder.lblCreated.setText("Created At");

        holder.cvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Coming Soon !", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(context, EditSurveyActivity.class);
//                i.putExtra("idSurvey", activeSurvey.getIdSurvey().toString());
//                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listActiveSurvey.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvHome;
        private TextView lblActiveSurvey, lblCreated;
        private TextView lblNamaSurvey, lblDateCreated;

        public ViewHolder(View itemView) {
            super(itemView);

            cvHome = (CardView) itemView.findViewById(R.id.cv_home);
            lblNamaSurvey = (TextView) itemView.findViewById(R.id.tv_nama_survey);
            lblDateCreated = (TextView) itemView.findViewById(R.id.tv_date);
            lblActiveSurvey = (TextView) itemView.findViewById(R.id.tv_active_survey);
            lblCreated = (TextView) itemView.findViewById(R.id.tv_created_at);
        }
    }
}
