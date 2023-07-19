package eu.nebesky.brain.controllers;

import eu.nebesky.brain.dto.ComputeRequestDto;
import eu.nebesky.brain.dto.LearningRequestDto;
import eu.nebesky.brain.services.MachineLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MachineLearningController {

    @Autowired
    MachineLearningService machineLearningService;

    @PostMapping("/learn")
    void learn(@RequestBody LearningRequestDto learningRequestDto) {
        machineLearningService.trainAndPredict(learningRequestDto.getEpochs(), learningRequestDto.getLearnFactor(), learningRequestDto.getTrainingDataList());
    }

    @PostMapping("/compute")
    Double learn(@RequestBody ComputeRequestDto computeRequestDto) {
        return machineLearningService.compute(computeRequestDto.getInput1(), computeRequestDto.getInput2());
    }

    //todo getNeuralNetwork

}
