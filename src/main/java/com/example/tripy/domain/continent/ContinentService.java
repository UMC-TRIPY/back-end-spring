package com.example.tripy.domain.continent;

import com.example.tripy.domain.continent.dto.ContinentResponseDto.ContinentFilterResult;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContinentService {


    private final ContinentRepository continentRepository;

    public List<ContinentFilterResult> getContinents(String continentName) {
        return continentRepository.findAll().stream()
            .filter(continent -> continentName == null || continent.getName().equals(continentName))
            .map(ContinentFilterResult::toDTO)
            .collect(Collectors.toList());
    }

}
