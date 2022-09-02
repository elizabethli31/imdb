package imdb;

import java.io.*;

public class Dset {

    public static void writePositive(FileWriter finalFile, String folderPath) {
        try {
            File folder = new File(folderPath);

            File[] files = folder.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isFile())
                {
                    FileReader file = new FileReader(files[i]);
                    int j = 0;
                    String str = "1\t";

                    while((j = file.read()) != -1)
                    {
                        str += (char)j;
                    }
                    
                    str += "\n";

                    finalFile.write(str);
                    file.close();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();;
        }
    }

    public static void writeNegative(FileWriter finalFile, String folderPath) {
        try {
            File folder = new File(folderPath);

            File[] files = folder.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isFile())
                {
                    FileReader file = new FileReader(files[i]);
                    int j = 0;
                    String str = "0\t";

                    while((j = file.read()) != -1)
                    {
                        str += (char)j;
                    }

                    str += "\n";

                    finalFile.write(str);
                    file.close();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();;
        }
    }

    public static void writeUnlabeled(FileWriter finalFile, String folderPath) {
        try {
            File folder = new File(folderPath);

            File[] files = folder.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isFile())
                {
                    FileReader file = new FileReader(files[i]);
                    int j = 0;
                    String str = "";

                    while((j = file.read()) != -1)
                    {
                        str += (char)j;
                    }

                    str += "\n";

                    finalFile.write(str);
                    file.close();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();;
        }
    }

    public static void main(String[] args) {
        try {
            // File trainPath = new File("/Users/elizabethli/cmsc/personal/imdb/imdb/inputs/data/train.txt");
            // FileWriter trainFile = new FileWriter(trainPath, true);


            // String trainPosPath = "/Users/elizabethli/cmsc/personal/imdb/imdb/inputs/train/pos";
            // String trainNegPath = "/Users/elizabethli/cmsc/personal/imdb/imdb/inputs/train/neg";

            // writePositive(trainFile, trainPosPath);
            // writeNegative(trainFile, trainNegPath);

            // trainFile.close();

            File testPath = new File("/Users/elizabethli/cmsc/personal/imdb/imdb/inputs/data/test.txt");
            FileWriter testFile = new FileWriter(testPath, true);

            String testPosPath = "/Users/elizabethli/cmsc/personal/imdb/imdb/inputs/test/pos";
            String testNegPath = "/Users/elizabethli/cmsc/personal/imdb/imdb/inputs/test/neg";

            writeUnlabeled(testFile, testPosPath);
            writeUnlabeled(testFile, testNegPath);

            testFile.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}