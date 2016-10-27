package edu.dev.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tengzhao on 9/18/16.
 */
@Entity
@Table(name = "user")
public class User {
    public static String TAG = User.class.getCanonicalName();

    public interface Role {
        int INSTRUCTOR = 0;
        int STUDENT = 1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String name;
    private String password;
    private int role;

//    @OneToMany(mappedBy = "user")
//    private List<UserProposalRelationship> proposals = new ArrayList<>();

    public User() {
    }

    public User(int id, String email, String name, String password, int role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

//    public List<UserProposalRelationship> getProposals() {
//        return proposals;
//    }
//
//    public void setProposals(List<UserProposalRelationship> proposals) {
//        this.proposals = proposals;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
