package com.example.mypassword;

public class PasswordEntry {

    private int id;
    private String displayName;
    private String username;
    private String password;
    private String infoText;

    public PasswordEntry()
    {

    }

    public PasswordEntry( int id, String displayName, String username, String password, String infoText )
    {
        this.id = id;
        this.displayName = displayName;
        this.username = username;
        this.password = password;
        this.infoText = infoText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
