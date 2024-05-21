package com.example.tripy.domain.countrymaterial;

import com.example.tripy.domain.material.Material;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryMaterialRepository extends JpaRepository<CountryMaterial, Long> {

	@Query("SELECT cm.material FROM CountryMaterial cm WHERE cm.country.id = :countryId")
	List<Material> findMaterialsByCountry(@Param("countryId") Long countryId);

}
