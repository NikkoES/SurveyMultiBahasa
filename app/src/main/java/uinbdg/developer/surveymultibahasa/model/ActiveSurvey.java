package uinbdg.developer.surveymultibahasa.model;

/**
 * Created by Nikko Eka Saputra on 10/02/2018.
 */

public class ActiveSurvey {
    private String namaSurvey;
    private String totalResponse;
    private String createdAt;

    public ActiveSurvey(String namaSurvey, String totalResponse, String createdAt) {
        this.namaSurvey = namaSurvey;
        this.totalResponse = totalResponse;
        this.createdAt = createdAt;
    }

    public String getNamaSurvey() {
        return namaSurvey;
    }

    public String getTotalResponse() {
        return totalResponse;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
