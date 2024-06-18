package model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * This is a MappedSuperclass that represents a User in the system.
 * Both Customers and Owners are Users, thus sharing several fields such as an id, email, username, etc.
 * This class provides getters for these fields, making the Customer and Owner classes less condensed.
 */
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String name;
    private String address;

    /**
     * Constructor for User class.
     * @param email User's email.
     * @param phoneNumber User's phone number.
     * @param username User's username.
     * @param password User's password.
     * @param name User's name.
     * @param address User's address.
     */
    public User(String email, String phoneNumber, String username, String password, String name, String address){
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    /**
     * Default constructor for User class.
     */
    public User(){};

    /**
     * @return User's email.
     */
    public String getEmail(){
        return email;
    }

    /**
     * @return User's phone number.
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /**
     * @return User's username.
     */
    public String getUsername(){
        return username;
    }

    /**
     * @return User's password.
     */
    public String getPassword(){
        return password;
    }

    /**
     * @return User's ID.
     */
    public long getId(){
        return id;
    }

    /**
     * Sets the User's ID.
     * @param id User's ID.
     */
    public void setId(long id){
        this.id = id;
    }

    /**
     * @return User's name.
     */
    public String getName(){
        return name;
    }

    /**
     * @return User's address.
     */
    public String getAddress(){
        return address;
    }
}