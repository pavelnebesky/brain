package eu.nebesky.brain.model;

import eu.nebesky.brain.Utils.MachineUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NeuralNetwork {

    //todo change logic to layers, use layers count in constructor
    List<Neuron> neurons = Arrays.asList(
            new Neuron(), new Neuron(), new Neuron(), new Neuron(),
            new Neuron(), new Neuron(), new Neuron(),
            new Neuron(), new Neuron(),
            new Neuron());

    public NeuralNetwork() {
    }

    public Double predict(double input1, double input2) {
        return neurons.get(9).compute(
                neurons.get(8).compute(
                        neurons.get(6).compute(
                                neurons.get(3).compute(input1, input2),
                                neurons.get(2).compute(input1, input2)),
                        neurons.get(5).compute(
                                neurons.get(2).compute(input1, input2),
                                neurons.get(1).compute(input1, input2))
                ),
                neurons.get(7).compute(
                        neurons.get(5).compute(
                                neurons.get(2).compute(input1, input2),
                                neurons.get(1).compute(input1, input2)),
                        neurons.get(4).compute(
                                neurons.get(1).compute(input1, input2),
                                neurons.get(0).compute(input1, input2))
                )
        );
    }


    public void train(List<TrainingData> trainingDataList, int epochs, Double learnFactor) {
        Double bestEpochLoss = null;
        for (int epoch = 0; epoch < epochs; epoch++) {
            // adapt neuron
            Neuron epochNeuron = neurons.get(epoch % neurons.size());
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
