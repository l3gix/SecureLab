package dev.securelab.controller;

import dev.securelab.dto.LabTargetResponse;
import dev.securelab.model.LabTarget;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/targets")
@RequiredArgsConstructor
public class LabTargetController
{

    @GetMapping
    public List<LabTargetResponse> getAllTargets()
    {

        return Arrays.stream(LabTarget.values()) // diventa un array
                .map(target -> new LabTargetResponse(target.getKey())) // conversione in labTarghetResponse
                .toList(); // tutto in una lista
    }

    /* codice equivalente
    @GetMapping
    public List<LabTargetResponse> getAllTargets()
    {

    List<LabTargetResponse> responses = new ArrayList<>();

    for (LabTarget target : LabTarget.values()) {

        LabTargetResponse response =
                new LabTargetResponse(target.getKey());

        responses.add(response);
    }

    return responses;
    }
     */
}
