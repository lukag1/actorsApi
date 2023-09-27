package cgtonboardingactorsmain.actorsApi.dto;

import java.time.LocalDate;
import java.util.Date;

public class ActorDto {

    private Integer id;
    private String gender;
    private String fullName;
    private String href;
    private int age;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String spouse;
    private String parents;
    private String biography;
    private String actorImage;
    private String imageName;

    private String children;

    public ActorDto() {

    }

    public ActorDto(Integer id, String gender, String fullName, String href, int age, LocalDate dateOfBirth, String children,
                    String placeOfBirth, String spouse, String parents, String biography, String actorImage, String imageName) {
        this.id = id;
        this.children = children;
        this.gender = gender;
        this.fullName = fullName;
        this.href = href;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.spouse = spouse;
        this.parents = parents;
        this.biography = biography;
        this.actorImage = actorImage;
        this.imageName = imageName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getAge() {
        return age;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
