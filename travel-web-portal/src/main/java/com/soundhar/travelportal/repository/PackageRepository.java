package com.soundhar.travelportal.repository;

import com.soundhar.travelportal.model.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PackageRepository extends JpaRepository<TravelPackage, Long> {
    List<TravelPackage> findByDestinationContainingIgnoreCase(String destination);
}
