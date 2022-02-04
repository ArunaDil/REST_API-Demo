package com.example.demo.models;

import org.springframework.data.annotation.Id;

public class UserRole {
    @Id
    private String id;
    private String email;
    private int userRoleRank;
    private int status;

    public UserRole(String email, int userRoleRank) {
        this.email = email;
        this.userRoleRank = userRoleRank;
        this.status = 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserRoleRank() {
        return userRoleRank;
    }

    public void setUserRoleRank(int userRoleRank) {
        this.userRoleRank = userRoleRank;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", userRoleRank=" + userRoleRank +
                ", status=" + status +
                '}';
    }
}
