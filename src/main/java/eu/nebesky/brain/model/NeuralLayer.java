package eu.nebesky.brain.model;

public class NeuralLayer {

    Neuron[] neurons;
    int numberOfNeurons;

    public NeuralLayer(int numberOfNeurons) {
        neurons = new Neuron[numberOfNeurons];
        for (int i = 0 ; i < numberOfNeurons ; i++) {
            neurons[i] = new Neuron();
        }
        this.numberOfNeurons = numberOfNeurons;
    }

    public Neuron[] getNeurons() {
        return neurons;
    }

    public int getNeuralLayerSize() {
        return neurons.length;
    }

    public Neuron getNeuron(int neuronNumber) {
        return neurons[neuronNumber];
    }
}
