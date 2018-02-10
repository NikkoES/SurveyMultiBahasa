package uinbdg.developer.surveymultibahasa.model;

/**
 * Created by Nikko Eka Saputra on 10/02/2018.
 */

public class Question {
    private String tipeQuestion;
    private String question;
    private String optionSatu;
    private String optionDua;
    private String optionTiga;
    private String optionEmpat;

    public Question(String tipeQuestion, String question, String optionSatu, String optionDua, String optionTiga, String optionEmpat) {
        this.tipeQuestion = tipeQuestion;
        this.question = question;
        this.optionSatu = optionSatu;
        this.optionDua = optionDua;
        this.optionTiga = optionTiga;
        this.optionEmpat = optionEmpat;
    }

    public String getTipeQuestion() {
        return tipeQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionSatu() {
        return optionSatu;
    }

    public String getOptionDua() {
        return optionDua;
    }

    public String getOptionTiga() {
        return optionTiga;
    }

    public String getOptionEmpat() {
        return optionEmpat;
    }
}