package hw3q1.model.domain;

public class User {

    private String email;
    private String password;
    private String name;
    private String streetAddress;
    private String state;
    private String zipcode;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "User [email=" + this.email + ", password=" + this.password + ", name=" + this.name + ", streetAddress=" + this.streetAddress + ", state="
                + this.state + ", zipcode=" + this.zipcode + "]";
    }

}
