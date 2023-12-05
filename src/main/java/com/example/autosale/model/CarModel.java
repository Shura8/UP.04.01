package com.example.autosale.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "car")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long _id;
    @NotBlank(message = "State number is required")
    @Size(max = 9)
    private String _state_number;
    @NotBlank(message = "Brand is required")
    @Size(max = 20)
    private String _brand;
    @NotBlank(message = "Color is required")
    @Size(max = 20)
    private String _color;
    @ManyToOne
    private ClassCarModel _class_car;
    @ManyToOne
    private StatusCarModel _status_car;
    @Min(message = "Year should be more than 0", value = 0)
    private int _issue_year;
    @Min(message = "Deposit should be more than 0", value = 0)
    private int _deposit;
    @NotBlank(message = "Passport is required")
    @Size(max = 10)
    private String _passport;

    public CarModel() {}
    public CarModel(long _id, String _state_number, String _brand, String _color,
                    ClassCarModel _class_car, StatusCarModel statusCarModel, int _issue_year, int _deposit, String _passport) {
        this._id = _id;
        this._state_number = _state_number;
        this._brand = _brand;
        this._color = _color;
        this._status_car = statusCarModel;
        this._class_car = _class_car;
        this._issue_year = _issue_year;
        this._deposit = _deposit;
        this._passport = _passport;
    }
    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public String getStateNumber() {
        return _state_number;
    }

    public void setStateNumber(String _state_number) {
        this._state_number = _state_number;
    }

    public String getBrand() {
        return _brand;
    }

    public void setBrand(String _brand) {
        this._brand = _brand;
    }

    public ClassCarModel getClassCar() {
        return _class_car;
    }

    public void setClassCar(ClassCarModel _class_car) {
        this._class_car = _class_car;
    }

    public StatusCarModel getStatusCar() {
        return _status_car;
    }

    public void setStatusCar(StatusCarModel _status_car) {
        this._status_car = _status_car;
    }

    public int getIssueYear() {
        return _issue_year;
    }

    public void setIssueYear(int _issue_year) {
        this._issue_year = _issue_year;
    }

    public int getDeposit() {
        return _deposit;
    }

    public void setDeposit(int _deposit) {
        this._deposit = _deposit;
    }

    public String getPassport() {
        return _passport;
    }

    public void setPassport(String _passport) {
        this._passport = _passport;
    }
    public String getColor() {
        return _color;
    }

    public void setColor(String _color) {
        this._color = _color;
    }
}
