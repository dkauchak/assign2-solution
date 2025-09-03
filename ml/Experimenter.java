package ml;

import ml.classifiers.Classifier;
import ml.classifiers.DecisionTreeClassifier;
import ml.classifiers.RandomClassifier;
import ml.data.DataSet;
import ml.data.DataSetSplit;
import ml.data.Example;

public class Experimenter{
	public static double getAccuracy(Classifier classifier, DataSet test){
		int correct = 0;
		
		for( Example d: test.getData() ){
			double predicted = classifier.classify(d);
			
			if( predicted == d.getLabel() ){
				correct ++;
			}
		}
		
		return (double)correct/test.getData().size();
	}
	
	public static void analyzeTreeSize(DataSet dataset){
		
		for( int depthLimit = 1; depthLimit < 20; depthLimit++ ){
			double dtAve = 0.0;
			double trainAve = 0.0;
			double height = 0.0;
			int numRuns = 100;

			for( int i = 0; i < numRuns; i++ ){
				DataSetSplit temp = dataset.split(0.8);			
				DataSet train = temp.getTrain();
				DataSet test = temp.getTest();

				DecisionTreeClassifier dt = new DecisionTreeClassifier();
				dt.setDepthLimit(depthLimit);
				dt.train(train);

				//height += dt.
				
				dtAve += getAccuracy(dt, test);
				trainAve += getAccuracy(dt, train);
			}
			
			System.out.println((dtAve/numRuns) + "\t" + trainAve/numRuns + "\t" + (height/numRuns));
		}
	}
	
	public static void analyzeTrainingSize(DataSet dataset){
		
		for( double splitProp = 0.05; splitProp <= 0.9; splitProp += 0.1 ){
			double dtAve = 0.0;
			double trainAve = 0.0;
			int numRuns = 100;

			for( int i = 0; i < numRuns; i++ ){
				DataSetSplit temp = dataset.split(splitProp);			
				DataSet train = temp.getTrain();
				DataSet test = temp.getTest();

				DecisionTreeClassifier dt = new DecisionTreeClassifier();
				dt.setDepthLimit(1);
				dt.train(train);

				dtAve += getAccuracy(dt, test);
				trainAve += getAccuracy(dt, train);
			}
			
			System.out.println(dtAve/numRuns + "\t" + trainAve/numRuns);
		}
	}
	
	public static void test(){
		String csvFile = "/Users/drk04747/classes/cs158/assignments/assign1/starter/titanic-train.csv";
		DataSet dataset = new DataSet(csvFile);
				
		double randomAve = 0.0;
		double dtAve = 0.0;
		
		int numRuns = 100;
		
		for( int i = 0; i < numRuns; i++ ){
			DataSetSplit temp = dataset.split(0.8);			
			DataSet train = temp.getTrain();
			DataSet test = temp.getTest();

			RandomClassifier random = new RandomClassifier();
			DecisionTreeClassifier dt = new DecisionTreeClassifier();
			dt.setDepthLimit(5);
			dt.train(train);
			
			randomAve += getAccuracy(random, test);
			dtAve += getAccuracy(dt, test);
		}
		
		System.out.println("Random: " + randomAve/numRuns);
		System.out.println("DTrees: " + dtAve/numRuns);
	}
	
	public static void main(String[] args){
		String csvFile = "/Users/drk04747/classes/cs158/assignments/assign1/starter/titanic-train.csv";
		DataSet dataset = new DataSet(csvFile, 6);
		
		//analyzeTreeSize(dataset);
		test();
	}
}
