package com.example.autosale.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Table(name = "maintenance")
public class MaintenanceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long _id;
    @Min(message = "Year should be more than 0", value = 0)
    private int _service_cost;
    @ManyToOne
    private CarModel _car;
    private Date _date;
    public MaintenanceModel() {}
    public MaintenanceModel(Long _id, int _service_cost, CarModel _car, Date _date) {
        this._id = _id;
        this._service_cost = _service_cost;
        this._car = _car;
        this._date = _date;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long _id) {
        this._id = _id;
    }

    public int getServiceCost() {
        return _service_cost;
    }

    public void setServiceCost(int _service_cost) {
        this._service_cost = _service_cost;
    }

    public CarModel getCar() {
        return _car;
    }

    public void setCar(CarModel _car) {
        this._car = _car;
    }

    public Date getDate() {
        return _date;
    }

    public void setDate(Date _date) {
        this._date = _date;
    }
}
