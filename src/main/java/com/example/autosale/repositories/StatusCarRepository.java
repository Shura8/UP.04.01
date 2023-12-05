package com.example.autosale.repositories;
import com.example.autosale.model.RoleModel;
import com.example.autosale.model.StatusCarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusCarRepository extends JpaRepository<StatusCarModel, Long> {

}
