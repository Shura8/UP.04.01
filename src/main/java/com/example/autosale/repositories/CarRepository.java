package com.example.autosale.repositories;
import com.example.autosale.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarModel, Long> {

}
