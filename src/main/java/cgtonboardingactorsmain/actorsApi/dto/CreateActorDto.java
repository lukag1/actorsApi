package cgtonboardingactorsmain.actorsApi.dto;

import java.time.LocalDate;
import java.util.Date;

public class CreateActorDto {
    private String gender;
    private String fullName;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String spouse;
    private String parents;
    private String biography;
    private String actorImage;
    private String children;

    private String jobTitle;

    public CreateActorDto() {

    }

    public CreateActorDto(String gender, String fullName, LocalDate dateOfBirth, String placeOfBirth,
                          String spouse, String parents, String biography, String actorImage, String children, String jobTitle) {
        this.gender = gender;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.spouse = spouse;
        this.parents = parents;
        this.biography = biography;
        this.actorImage = actorImage;
        this.children = children;
        this.jobTitle = jobTitle;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getActorImage() {
        return actorImage;
    }

    public void setActorImage(String actorImage) {
        this.actorImage = actorImage;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
