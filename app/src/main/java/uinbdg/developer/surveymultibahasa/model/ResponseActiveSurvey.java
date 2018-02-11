package uinbdg.developer.surveymultibahasa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nikko Eka Saputra on 11/02/2018.
 */

public class ResponseActiveSurvey {

    @SerializedName("data")
    private List<ActiveSurvey> surveyList;

    @SerializedName("status")
    private String status;

    public void setSurveyList(List<ActiveSurvey> surveyList){
        this.surveyList = surveyList;
    }

    public List<ActiveSurvey> getSurveyList(){
        return surveyList;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

}
