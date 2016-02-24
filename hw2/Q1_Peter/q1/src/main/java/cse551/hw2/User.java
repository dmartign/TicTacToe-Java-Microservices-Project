package cse551.hw2;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String profession;

    public User() {
    }

    public User(int id, String name, String profession) {
        this.id = id;
        this.name = name;
        this.profession = profession;
    }

    public int getId() {
        return this.id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return this.profession;
    }

    @XmlElement
    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (!(object instanceof User)) {
            return false;
        } else {
            User user = (User) object;
            if (this.id == user.getId() && this.name.equals(user.getName()) && this.profession.equals(user.getProfession())) {
                return true;
            }
        }
        return false;
    }
}