package com.example.tripy.domain.material;

import com.example.tripy.domain.bagmaterials.BagMaterials;
import com.example.tripy.domain.countrymaterial.CountryMaterial;
import com.example.tripy.domain.material.dto.MaterialRequestDto.CreateMaterialRequest;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Material extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    public static Material toEntity(CreateMaterialRequest createMaterialRequest) {
        return Material.builder()
            .name(createMaterialRequest.getMaterialName())
            .build();
    }
}