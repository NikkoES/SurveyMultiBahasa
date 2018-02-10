package uinbdg.developer.surveymultibahasa.model;

/**
 * Created by Nikko Eka Saputra on 10/02/2018.
 */

public class Survey {
    private String namaSurvey;
    private String modifiedAt;

    public Survey(String namaSurvey, String modifiedAt) {
        this.namaSurvey = namaSurvey;
        this.modifiedAt = modifiedAt;
    }

    public String getNamaSurvey() {
        return namaSurvey;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }
}
