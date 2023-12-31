package com.example.tripy.domain.country;

import com.example.tripy.domain.city.City;
import com.example.tripy.domain.continent.Continent;
import com.example.tripy.domain.conversation.Conversation;
import com.example.tripy.domain.countrymaterial.CountryMaterial;
import com.example.tripy.domain.currency.Currency;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<City> cities = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Conversation> conversations = new ArrayList<>();

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<CountryMaterial> countryMaterials = new ArrayList<>();
    

}