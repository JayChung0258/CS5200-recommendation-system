package user.model;

public class UserIdentity {
    protected int userID;
    protected int age;
    protected String gender;
    protected String country;

    // Constructor with all fields
    public UserIdentity(int userID, int age, String gender, String country) {
        this.userID = userID;
        this.age = age;
        this.gender = gender;
        this.country = country;
    }

    // Constructor without userID (for insertion cases)
    public UserIdentity(int age, String gender, String country) {
        this.age = age;
        this.gender = gender;
        this.country = country;
    }

    // Constructor for lookup by userID
    public UserIdentity(int userID) {
        this.userID = userID;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
