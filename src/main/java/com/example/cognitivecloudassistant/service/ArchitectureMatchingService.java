package com.example.cognitivecloudassistant.service;

import com.example.cognitivecloudassistant.dto.ArchitectureDTO;
import com.example.cognitivecloudassistant.dto.ServiceItemDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArchitectureMatchingService {

    public List<ArchitectureDTO> matchArchitecture(List<String> servicesCategory) {
        // 1. Gather ArchitectureDTOs from JSON sources
        List<ArchitectureDTO> awsArchitectures = loadArchitecturesFromJsonFiles("data/architectures/awsArchitectures");
        List<ArchitectureDTO> azureArchitectures = loadArchitecturesFromJsonFiles("data/architectures/azureArchitectures");

        // 2. Pre-process services list for case-insensitive matching and duplicates
        Set<String> normalizedServicesCategory = new HashSet<>(servicesCategory.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList()));


        // 3. Calculate compliance scores for each architecture
        Map<ArchitectureDTO, Integer> azureArchitectureScores = new HashMap<>();
        for (ArchitectureDTO architecture : azureArchitectures) {
            int score = calculateComplianceScore(architecture, normalizedServicesCategory);
            azureArchitectureScores.put(architecture, score);
        }
        List<ArchitectureDTO> bestAzureArchitectures = getBestSuitableArchitectures(3, azureArchitectureScores);


        Map<ArchitectureDTO, Integer> awsArchitectureScores = new HashMap<>();
        for (ArchitectureDTO architecture : awsArchitectures) {
            int score = calculateComplianceScore(architecture, normalizedServicesCategory);
            awsArchitectureScores.put(architecture, score);
        }
        List<ArchitectureDTO> bestAwsArchitectures = getBestSuitableArchitectures(1, awsArchitectureScores);


        List<ArchitectureDTO> allBestArchitectures = new ArrayList<>();
        allBestArchitectures.addAll(bestAwsArchitectures);
        allBestArchitectures.addAll(bestAzureArchitectures);

        return allBestArchitectures;
    }

    private int calculateComplianceScore(ArchitectureDTO architecture, Set<String> servicesCategory) {
        int count = 0;
        for (ServiceItemDTO resource : architecture.getResources()) {

            // not valid cloud service (user, computer etc...)
            if (resource.getName() == null) {
                continue;
            }
            if (servicesCategory.contains(resource.getType().toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    private List<ArchitectureDTO> loadArchitecturesFromJsonFiles(String architecturesDirectory) {
        List<ArchitectureDTO> architectures = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        File directory = new File(architecturesDirectory);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                try {
                    ArchitectureDTO architecture = mapper.readValue(file, ArchitectureDTO.class);
                    architectures.add(architecture);
                } catch (IOException e) {
                    System.err.println("Error parsing JSON file: " + file.getName());
                }
            }
        }

        return architectures;
    }

    private List<ArchitectureDTO> getBestSuitableArchitectures(int numberOfArchitectures, Map<ArchitectureDTO, Integer> scoredArchitectures) {
        // sort architectures by score
        List<Map.Entry<ArchitectureDTO, Integer>> sortedArchitectures =
                scoredArchitectures.entrySet().stream()
                        .sorted(Map.Entry.<ArchitectureDTO, Integer>comparingByValue().reversed())
                        .collect(Collectors.toList());

        // check if number of desired architecture is greater than number of existing architectures
        if (numberOfArchitectures > sortedArchitectures.size()) {
            numberOfArchitectures = sortedArchitectures.size();
        }

        // get best x [param numberOfArchitectures] suitable architectures
        List<ArchitectureDTO> resultListWithArchitectures = new ArrayList<>();
        for (int i = 0; i < numberOfArchitectures; i++) {
            resultListWithArchitectures.add(sortedArchitectures.get(i).getKey());
            System.out.println(sortedArchitectures.get(i).getKey().getName() + " with score " + sortedArchitectures.get(i).getValue());
        }

        return resultListWithArchitectures;
    }
}
