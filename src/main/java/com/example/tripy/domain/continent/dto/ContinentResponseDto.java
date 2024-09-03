package com.example.tripy.domain.continent.dto;

import com.example.tripy.domain.continent.Continent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ContinentResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContinentFilterResult {

        private Long continentId;
        private String continentName;

        public static ContinentFilterResult toDTO(Continent continent) {
            return ContinentFilterResult.builder()
                .continentId(continent.getId())
                .continentName(continent.getName())
                .build();
        }
    }

}
