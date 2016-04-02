package tbd.gateway.model.domain;

public class User {

    private String email;
    private String password;
    private String name;
    private String id;

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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User [email=" + this.email + ", password=" + this.password + ", name=" + this.name + ", id=" + this.id + "]";
    }

}
