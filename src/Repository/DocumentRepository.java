package Repository;

public class DocumentRepository {

    public static void storeRecord(DocumentRecord record){
        System.out.println("add database details to store");
    }

    public static DocumentRecord getRecord(String id){
        System.out.println("add criteria to retrieve record where ID = "+id);
        return new DocumentRecord();
    }
}
