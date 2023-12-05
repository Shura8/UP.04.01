package com.example.autosale.repositories;
import com.example.autosale.model.MaintenanceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<MaintenanceModel, Long> {

}
