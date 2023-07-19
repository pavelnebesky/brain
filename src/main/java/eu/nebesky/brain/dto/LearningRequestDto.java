package eu.nebesky.brain.dto;

import eu.nebesky.brain.model.TrainingData;
import lombok.Data;

import java.util.List;

@Data
public class LearningRequestDto {
    private Integer epochs;
    private Double learnFactor;
    private List<TrainingData> trainingDataList;
}
