import Extractors.PDFExtractor;

public class Main {

    public static void main(String[] args){

        try {
            PDFExtractor.importPDF("/home/brc/Downloads/ibm-machine-learning-for-dummies-ibm-limited-edition_IMM14209USEN.pdf");
        } catch (Exception e){
            System.out.println(("Failed: " + e.getMessage()));
        }


    }
}
