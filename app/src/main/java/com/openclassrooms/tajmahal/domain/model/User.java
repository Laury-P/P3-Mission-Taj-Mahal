package com.openclassrooms.tajmahal.domain.model;

/**
 * This class represents a user in the application.
 * This class encapsulates all the details of a user,
 * including the name, the profil picture and in the their id/author.
 */
public class User {

    /** The name of the user. */
    private String name;

    /** The profil picture of the user. */
    private String profilPicture;

    /** The id of the user. */
    private String id;

    /**
     * Constructs a new User instance.
     *
     * @param name the name of the user
     * @param profilPicture the profil picture of the user
     */
    public User(String name, String profilPicture, String id) {
        this.name = name;
        this.profilPicture = profilPicture;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilPicture() {
        return profilPicture;
    }

    public void setProfilPicture(String profilPicture) {
        this.profilPicture = profilPicture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
