package eu.nebesky.brain.services;

import eu.nebesky.brain.model.NeuralNetwork;
import eu.nebesky.brain.model.TrainingData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MachineLearningService {

    private NeuralNetwork neuralNetwork;

    @Async
    public void trainAndPredict(Integer epochs, Double learnFactor, List<TrainingData> trainingDataList) {

        this.neuralNetwork = new NeuralNetwork();
        neuralNetwork.train(trainingDataList, epochs, learnFactor);

        System.out.println("Training done.");

    }

    public Double compute(double input1, double input2) {
        Double predicted = neuralNetwork.predict(input1, input2)*100;
        return predicted;
    }
}
