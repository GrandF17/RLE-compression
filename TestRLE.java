import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TestRLE {
    public static void main(String[] args) {
        String inputFile, outputFile;
        try {
            if (args[0].toLowerCase().equals("c") || args[0].toLowerCase().equals("compress")) {
                inputFile = args[1];
                outputFile = "comp_" + inputFile;
                FileInputStream fin = new FileInputStream(inputFile);
                FileOutputStream fout = new FileOutputStream(outputFile);
                RLE rle = new RLE();
                System.out.print("File compress: ");
                if (rle.compress(fin, fout).equals("Complete!")) System.out.println("Successful");
                else System.out.println("Unsuccessful");
            } else if (args[0].toLowerCase().equals("d") || args[0].toLowerCase().equals("decompress")) {
                inputFile = args[1];
                outputFile = "de_" + inputFile;
                FileInputStream fin = new FileInputStream(inputFile);
                FileOutputStream fout = new FileOutputStream(outputFile);
                RLE rle = new RLE();
                System.out.print("File decompress: ");
                if (rle.decompress(fin, fout).equals("Complete!")) System.out.println("Successful");
                else System.out.println("Unsuccessful");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
