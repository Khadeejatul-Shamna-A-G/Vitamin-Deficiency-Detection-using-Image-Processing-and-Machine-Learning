package com.example.vitaApp.models;

public class EyeModel {

    private String cId, title, description, image, diagnosisHint, symptomHint, symptoms, treatmentHint, treatments, cause;


    public EyeModel() {
    }

    public EyeModel(String cId, String title, String description, String image, String diagnosisHint, String symptomHint, String symptoms, String treatmentHint, String treatments, String cause) {
        this.cId = cId;
        this.title = title;
        this.description = description;
        this.image = image;
        this.diagnosisHint = diagnosisHint;
        this.symptomHint = symptomHint;
        this.symptoms = symptoms;
        this.treatmentHint = treatmentHint;
        this.treatments = treatments;
        this.cause = cause;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiagnosisHint() {
        return diagnosisHint;
    }

    public void setDiagnosisHint(String diagnosisHint) {
        this.diagnosisHint = diagnosisHint;
    }

    public String getSymptomHint() {
        return symptomHint;
    }

    public void setSymptomHint(String symptomHint) {
        this.symptomHint = symptomHint;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTreatmentHint() {
        return treatmentHint;
    }

    public void setTreatmentHint(String treatmentHint) {
        this.treatmentHint = treatmentHint;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
