package com.example.squadroncinema;

public class feedbackmodel {

    String femail;
    String description;

    public feedbackmodel(String femail, String description){
        this.femail = femail;
        this.description = description;
    }

    public String getFemail() {
        return femail;
    }

    public void setFemail(String femail) {
        this.femail = femail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
