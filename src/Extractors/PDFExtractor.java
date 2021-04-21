package Extractors;

// File Handling
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// Data Type for returning metadata
import java.util.HashMap;
import java.util.Map;

// Tika Components
import Repository.DocumentRecord;
import Repository.DocumentRepository;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

// Exception Handling
import org.xml.sax.SAXException;

public class PDFExtractor {

    // Invoke initial handler and contexts  * Node, the limit for BodyContentHandler - Set -1 (unlimited) - Probably better to specify
    private static BodyContentHandler handler = new BodyContentHandler(-1);
    private static Metadata metadata = new Metadata();
    private static ParseContext pcontext = new ParseContext();
    private static FileInputStream inputstream;
    private static PDFParser pdfparser = new PDFParser();


    // Takes filepath as input and tries to open and extract the file
    public static void importPDF(String filepath) throws FileNotFoundException {
        inputstream = new FileInputStream(new File(filepath));
        try {
            pdfparser.parse(inputstream, handler, metadata,pcontext);
            DocumentRecord record = new DocumentRecord(getMetadata(),getDocumentText());
            DocumentRepository.storeRecord(record);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
    }

    // Returns the document text
    private static String getDocumentText(){
          return handler.toString();
    }

    // Returns document metadata
    private static Map<String,String> getMetadata(){
          String[] metadatanames = metadata.names();
          Map<String,String> metamap = new HashMap<>();
          for(String name: metadatanames){
              metamap.put(name,metadata.get(name));
          }
          return metamap;
    }

}
