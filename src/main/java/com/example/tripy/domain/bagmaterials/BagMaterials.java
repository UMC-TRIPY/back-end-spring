package com.example.tripy.domain.bagmaterials;

import com.example.tripy.domain.bag.Bag;
import com.example.tripy.domain.material.Material;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BagMaterials extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isChecked;

    @ManyToOne(fetch = FetchType.LAZY)
    private Bag bag;

    @ManyToOne(fetch = FetchType.LAZY)
    private Material material;
}