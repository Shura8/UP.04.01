package com.example.autosale.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "rental")
public class RentalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long _id;
    @ManyToOne
    private UserModel _user;
    @ManyToOne
    private CarModel _car;
    private Date _date_rental;
    @Min(message = "Cost should be more than 0", value = 0)
    private int _cost_rental;
    @ManyToMany
    @JoinTable(name="fine_rental",
            joinColumns=@JoinColumn(name="fine_id"),
            inverseJoinColumns=@JoinColumn(name="rental_id"))
    private Set<FineModel> _fine;

    public RentalModel(){}
    public RentalModel(Long _id, UserModel _user, CarModel _car, Date _date_rental,
                       int _cost_rental, Set<FineModel> _fine) {
        this._id = _id;
        this._user = _user;
        this._car = _car;
        this._date_rental = _date_rental;
        this._cost_rental = _cost_rental;
        this._fine = _fine;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long _id) {
        this._id = _id;
    }

    public UserModel getUser() {
        return _user;
    }

    public void setUser(UserModel _user) {
        this._user = _user;
    }

    public CarModel getCar() {
        return _car;
    }

    public void setCar(CarModel _car) {
        this._car = _car;
    }

    public Date getDateRental() {
        return _date_rental;
    }

    public void setDateRental(Date _date_rental) {
        this._date_rental = _date_rental;
    }

    public int getCostRental() {
        return _cost_rental;
    }

    public void setCostRental(int _cost_rental) {
        this._cost_rental = _cost_rental;
    }

    public Set<FineModel> getFine() {
        return _fine;
    }

    public void setFine(Set<FineModel> _fine) {
        this._fine = _fine;
    }
}
