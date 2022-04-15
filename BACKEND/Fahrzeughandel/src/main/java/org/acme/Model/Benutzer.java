package org.acme.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Benutzer {

    @Id
    @GeneratedValue
    private int UserID;
    private String Benutzername;
    private String Kennwort;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Car> boughtCars;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Car> reservedCars;



    public Benutzer() {
    }

    public Benutzer(String benutzername, String kennwort) {
        setUsername(benutzername);
        setPassword(kennwort);
    }

    public Benutzer(String username, String password, List<Car> boughtCars, List<Car> reservedCars) {
        setUsername(username);
        setPassword(password);
        setBoughtCars(boughtCars);
        setReservedCars(reservedCars);
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Kennwort;
    }

    public void setUsername(String benutzername) {
        Benutzername = benutzername;
    }

    public String getPassword() {
        return Kennwort;
    }

    public void setPassword(String kennwort) {
        Kennwort = kennwort;
    }

    public List<Car> getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(List<Car> boughtCars) {
        this.boughtCars = boughtCars;
    }

    public List<Car> getReservedCars() {
        return reservedCars;
    }

    public void setReservedCars(List<Car> reservedCars) {
        this.reservedCars = reservedCars;
    }
}
