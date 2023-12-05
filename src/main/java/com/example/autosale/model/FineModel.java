package com.example.autosale.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "fine")
public class FineModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long _id;
    private String _name_fine;
    @Min(message = "Cost should be more than 0", value = 0)
    private int _cost_fine;
    @ManyToMany
    @JoinTable(name="fine_rental",
            joinColumns=@JoinColumn(name="rental_id"),
            inverseJoinColumns=@JoinColumn(name="fine_id"))
    private Set<RentalModel> _rental;

    public FineModel() {}
    public FineModel(Long id, String name_fine, int cost_fine, Set<RentalModel> rental){
        _id = id;
        _name_fine = name_fine;
        _cost_fine = cost_fine;
        _rental = rental;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long _id) {
        this._id = _id;
    }

    public String getNameFine() {
        return _name_fine;
    }

    public void setNameFine(String _name_fine) {
        this._name_fine = _name_fine;
    }

    public int getCostFine() {
        return _cost_fine;
    }

    public void setCostFine(int _cost_fine) {
        this._cost_fine = _cost_fine;
    }

    public Set<RentalModel> getRental() {
        return _rental;
    }

    public void setRental(Set<RentalModel> _rental) {
        this._rental = _rental;
    }
}
