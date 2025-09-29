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

    /**
     * Returns the name of the user.
     *
     * @return a string for the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets or updates the name of the user.
     *
     * @param name the new name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the profil picture of the user.
     *
     * @return a string for the url of the profil picture of the user
     */
    public String getProfilPicture() {
        return profilPicture;
    }

    /**
     * Sets or updates the profil picture of the user.
     *
     * @param profilPicture the new profil picture to be set
     */
    public void setProfilPicture(String profilPicture) {
        this.profilPicture = profilPicture;
    }

    /**
     * Returns the id of the user.
     *
     * @return a string for the id of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Sets or updates the id of the user.
     *
     * @param id the new id to be set
     */
    public void setId(String id) {
        this.id = id;
    }
}
