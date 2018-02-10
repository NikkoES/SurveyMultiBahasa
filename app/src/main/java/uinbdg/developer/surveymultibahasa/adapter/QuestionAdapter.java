package uinbdg.developer.surveymultibahasa.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uinbdg.developer.surveymultibahasa.R;
import uinbdg.developer.surveymultibahasa.model.Question;

/**
 * Created by Nikko Eka Saputra on 10/02/2018.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private Context context;
    private List<Question> listQueston;

    public QuestionAdapter(Context context, List<Question> listQueston){
        this.context = context;
        this.listQueston = listQueston;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_question, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layoutParams);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Question question = listQueston.get(position);
        holder.lblNomor.setText("no. "+(position+1));
        holder.lblQuestion.setText(question.getQuestion());
        if(question.getTipeQuestion().equalsIgnoreCase("multiple")){
            holder.llOpsi.setVisibility(View.VISIBLE);
            holder.lblOpsiSatu.setText("Opsi 1 : "+question.getOptionSatu());
            holder.lblOpsiDua.setText("Opsi 2 : "+question.getOptionDua());
            holder.lblOpsiTiga.setText("Opsi 3 : "+question.getOptionTiga());
            holder.lblOpsiEmpat.setText("Opsi 4 : "+question.getOptionEmpat());
        }
        else{
            holder.llOpsi.setVisibility(View.GONE);
        }
        holder.lblTipe.setText(question.getTipeQuestion());

        holder.cvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, question.getQuestion(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listQueston.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvQuestion;
        private TextView lblNomor, lblQuestion, lblTipe;

        private LinearLayout llOpsi;
        private TextView lblOpsiSatu, lblOpsiDua, lblOpsiTiga, lblOpsiEmpat;

        public ViewHolder(View itemView) {
            super(itemView);

            cvQuestion = (CardView) itemView.findViewById(R.id.cv_question);
            lblNomor = (TextView) itemView.findViewById(R.id.tv_nomor);
            lblQuestion = (TextView) itemView.findViewById(R.id.tv_question);
            lblTipe = (TextView) itemView.findViewById(R.id.tv_tipe_question);

            llOpsi = (LinearLayout) itemView.findViewById(R.id.ll_option);
            lblOpsiSatu = (TextView) itemView.findViewById(R.id.tv_opsi_1);
            lblOpsiDua = (TextView) itemView.findViewById(R.id.tv_opsi_2);
            lblOpsiTiga = (TextView) itemView.findViewById(R.id.tv_opsi_3);
            lblOpsiEmpat = (TextView) itemView.findViewById(R.id.tv_opsi_4);
        }
    }
}
