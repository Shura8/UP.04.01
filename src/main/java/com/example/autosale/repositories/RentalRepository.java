package com.example.autosale.repositories;
import com.example.autosale.model.RentalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalModel, Long> {

}
