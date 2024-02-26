package com.example.tripy.domain.countrymaterial;

import com.example.tripy.domain.material.Material;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CountryMaterialService {

	private final CountryMaterialRepository countryMaterialRepository;

	public List<Material> getCountryMaterials(String countryName) {
		List<Material> materials = countryMaterialRepository.findMaterialsByCountry(countryName);
		return materials;
	}
}
