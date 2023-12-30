package com.example.tripy.domain.city;

import com.example.tripy.domain.cityplan.CityPlan;
import com.example.tripy.domain.country.Country;
import com.example.tripy.domain.landmark.Landmark;
import com.example.tripy.domain.post.Post;
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
public class City extends BaseTimeEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<CityPlan> cityplans = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Landmark> landmarks = new ArrayList<>();

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

}