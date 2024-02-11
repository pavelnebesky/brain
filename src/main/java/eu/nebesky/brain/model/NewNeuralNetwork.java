package eu.nebesky.brain.model;

import eu.nebesky.brain.Utils.MachineUtils;

import java.util.ArrayList;
import java.util.List;


public class NewNeuralNetwork {

    List<NeuralLayer> neuralLayers = new ArrayList<>();
    int numberOfLayers;

    public NewNeuralNetwork(int numberOfLayers) {
        for (int i = 0 ; i < numberOfLayers ; i++) {
            neuralLayers.add(new NeuralLayer(i + 1));
        }
        this.numberOfLayers = numberOfLayers;
    }

    public Double predictNeuron(double input1, double input2, int layerNumber, int neuronNumber) {
        if (layerNumber == numberOfLayers - 1) {
            return neuralLayers.get(layerNumber).getNeuron(neuronNumber).compute(input1, input2);
        } else {
            return neuralLayers.get(layerNumber).getNeuron(neuronNumber).compute(
                    predictNeuron(input1, input2, layerNumber + 1, neuronNumber),
                    predictNeuron(input1, input2, layerNumber + 1, neuronNumber + 1));
        }
    }

    public Double predict(double input1, double input2) {
        return predictNeuron(input1, input2, 0, 0);
    }


    public void train(List<TrainingData> trainingDataList, int epochs, Double learnFactor) {
        Double bestEpochLoss = null;
        for (int epoch = 0; epoch < epochs; epoch++) {
            // adapt neuron
            Neuron epochNeuron = neuralLayers.get(epoch % numberOfLayers).getNeuron(epoch % neuralLayers.get(epoch % numberOfLayers).numberOfNeurons);
            epochNeuron.mutate(learnFactor);

            List<Double> predictions = new ArrayList<>();
            for (int i = 0; i < trainingDataList.size(); i++) {
                predictions.add(i, this.predict(trainingDataList.get(i).getInput1(), trainingDataList.get(i).getInput2()));
            }
            Double thisEpochLoss = MachineUtils.meanSquareLoss(trainingDataList.stream().map(TrainingData::getResult).toList(), predictions);

            if (epoch % 100000 == 0)
                System.out.println(String.format("Epoch: %s | bestEpochLoss: %.15f", epoch, bestEpochLoss));

            if (bestEpochLoss == null) {
                bestEpochLoss = thisEpochLoss;
                epochNeuron.remember();
            } else {
                if (thisEpochLoss < bestEpochLoss) {
                    bestEpochLoss = thisEpochLoss;
                    epochNeuron.remember();
                } else {
                    epochNeuron.forget();
                }
            }
        }
    }
}
