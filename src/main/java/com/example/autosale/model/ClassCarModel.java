package com.example.autosale.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "class_car")
public class ClassCarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long _id;
    @NotBlank(message = "Name class is required")
    private String _name_class;
    @Min(message = "Cost should be more than 0", value = 0)
    private int _day_cost;

    public ClassCarModel() {}
    public ClassCarModel(long id, String name_class, int day_cost){
        _id = id;
        _name_class = name_class;
        _day_cost = day_cost;
    }
    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public String getNameClass() {
        return _name_class;
    }

    public void setNameClass(String _name_class) {
        this._name_class = _name_class;
    }

    public int getDayCost() {
        return _day_cost;
    }

    public void setDayCost(int _day_cost) {
        this._day_cost = _day_cost;
    }
}
