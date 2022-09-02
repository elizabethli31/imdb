package imdb;

import java.io.*;
import opennlp.tools.doccat.*;
import opennlp.tools.util.*;
import opennlp.tools.util.model.ModelUtil;
import opennlp.tools.tokenize.WhitespaceTokenizer;

// import opennlp.tools.doccat.BagOfWordsFeatureGenerator;
// import opennlp.tools.doccat.DoccatFactory;
// import opennlp.tools.doccat.DoccatModel;
// import opennlp.tools.doccat.DocumentCategorizerME;
// import opennlp.tools.doccat.DocumentSample;
// import opennlp.tools.doccat.DocumentSampleStream;
// import opennlp.tools.doccat.FeatureGenerator;
// import opennlp.tools.tokenize.WhitespaceTokenizer;
// import opennlp.tools.util.InputStreamFactory;
// import opennlp.tools.util.MarkableFileInputStreamFactory;
// import opennlp.tools.util.ObjectStream;
// import opennlp.tools.util.PlainTextByLineStream;
// import opennlp.tools.util.TrainingParameters;
// import opennlp.tools.util.model.ModelUtil;

public class sentiment 
{
    DoccatModel model;

    public void train_model()
    {
        try {
            File trainingFile = new File("/Users/elizabethli/cmsc/personal/imdb/imdb/inputs/data/train.txt");
            InputStreamFactory trainingData = new MarkableFileInputStreamFactory(trainingFile);

            ObjectStream<String> lineStream = new PlainTextByLineStream(trainingData, "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

            TrainingParameters params = ModelUtil.createDefaultTrainingParameters();
            params.put(TrainingParameters.CUTOFF_PARAM, 0);
            params.put(TrainingParameters.ITERATIONS_PARAM, 150);
            FeatureGenerator[] featureGenerators = { new BagOfWordsFeatureGenerator()};
            DoccatFactory factory = new DoccatFactory(featureGenerators);

            model = DocumentCategorizerME.train("en", sampleStream, params, factory);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void classifyReview(String line)
    {
        DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model);
        String[] tokens = WhitespaceTokenizer.INSTANCE.tokenize(line);
        double[] outcomes = myCategorizer.categorize(tokens);

        Integer category = Integer.parseInt(myCategorizer.getBestCategory(outcomes));
        if (category == 1) {
            System.out.println("\nThis review is positive");
        } else{
            System.out.println("\nThis review is negative");
        }
    }

    public void classifyLoop(String path)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);
                classifyReview(line);

                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args )
    {
        sentiment reviewsSentiment = new sentiment();
        reviewsSentiment.train_model();
        reviewsSentiment.classifyLoop("/Users/elizabethli/cmsc/personal/imdb/imdb/inputs/data/test.txt");
    }
}
