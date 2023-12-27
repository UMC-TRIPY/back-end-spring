package com.example.tripy.domain.bag;

import com.example.tripy.domain.bagmaterials.BagMaterials;
import com.example.tripy.domain.travelplan.TravelPlan;
import com.example.tripy.domain.user.User;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bag extends BaseTimeEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String bagName;

    @NotNull
    private String content;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "travelplan_id")
    private TravelPlan travelPlan;

    @OneToMany(mappedBy = "bag")
    private List<BagMaterials> bagMaterials = new ArrayList<>();


}