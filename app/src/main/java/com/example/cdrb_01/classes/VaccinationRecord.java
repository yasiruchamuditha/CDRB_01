package com.example.cdrb_01.classes;

public class VaccinationRecord {
    private String babyId;
    private String babyMonths;
    private String babyName;
    private String gender;
    private String vaccinationDate;
    private String vaccineName;
    private String immediateHealth;

    public VaccinationRecord() {
        // Default constructor required for Firebase deserialization
    }

    public VaccinationRecord(String babyId, String babyMonths, String babyName, String gender, String vaccinationDate, String vaccineName, String immediateHealth) {
        this.babyId = babyId;
        this.babyMonths = babyMonths;
        this.babyName = babyName;
        this.gender = gender;
        this.vaccinationDate = vaccinationDate;
        this.vaccineName = vaccineName;
        this.immediateHealth = immediateHealth;
    }

    public String getBabyId() {
        return babyId;
    }

    public void setBabyId(String babyId) {
        this.babyId = babyId;
    }

    public String getBabyMonths() {
        return babyMonths;
    }

    public void setBabyMonths(String babyMonths) {
        this.babyMonths = babyMonths;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getImmediateHealth() {
        return immediateHealth;
    }

    public void setImmediateHealth(String immediateHealth) {
        this.immediateHealth = immediateHealth;
    }
}
