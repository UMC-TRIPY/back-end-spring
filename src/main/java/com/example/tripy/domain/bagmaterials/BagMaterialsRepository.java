package com.example.tripy.domain.bagmaterials;

import com.example.tripy.domain.bag.Bag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagMaterialsRepository extends JpaRepository<BagMaterials, Long> {

	List<BagMaterials> findBagMaterialsByBag(Bag bag);

	BagMaterials findBagMaterialsByBagAndMaterialId(Bag bag, Long materialId);

}
