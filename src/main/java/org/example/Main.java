package org.example;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
// import com.mongodb.client.model.Filters;
// import com.mongodb.client.model.Updates;

public class Main {
    private static final String DB_URI = "mongodb://localhost";
    private static final String DB_NAME = "jdbc-test";
    private static final String COLLECTION_NAME = "people";

    public static void main(String[] args) {

        // Creating a Mongo client
        MongoClient mongo = MongoClients.create(DB_URI);

        MongoDatabase database = mongo.getDatabase(DB_NAME);
        System.out.println("Database " + DB_NAME + " database created successfully");

        // Get all db's
        System.out.println("\nAll databases:");
        MongoIterable<String> dbList = mongo.listDatabaseNames();
        for (String dbName : dbList) {
            System.out.println(dbName);
        }

        // Creating a collection
        database.createCollection(COLLECTION_NAME);
        System.out.println("Collection " + COLLECTION_NAME + " created successfully");

        // get all collections
        System.out.println("\nAll collections:");
        MongoIterable<String> collections = database.listCollectionNames();
        for (String col : collections) {
            System.out.println(col);
        }

        // get a collection
        MongoCollection<org.bson.Document> collection = database.getCollection(COLLECTION_NAME);

        // Insert one document
        Document document = new Document("name", "Shrutanten")
                .append("age", 22)
                .append("canDrive", true);
        System.out.println("New document created:\n" + document);

        collection.insertOne(document);
        System.out.println("Inserted document successfully!");

        // Get all documents
        System.out.println("All documents:");
        FindIterable<Document> docs = collection.find();

        // Getting the iterator
        MongoCursor<Document> itr = docs.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        // Insert many documents
        Document doc1 = new Document("name", "Ramesh")
                .append("age", 17)
                .append("canDrive", false);
        Document doc2 = new Document("name", "Suresh")
                .append("age", 25)
                .append("canDrive", true);
        Document doc3 = new Document("name", "Rashmi")
                .append("age", 13)
                .append("canDrive", false);

        List<Document> docList = new ArrayList<>();
        docList.add(doc1);
        docList.add(doc2);
        docList.add(doc3);

        // Insert many records
        collection.insertMany(docList);

        // Get all documents
        FindIterable<Document> docs2 = collection.find();
        // Getting the iterator
        MongoCursor<Document> itr2 = docs2.iterator();
        while (itr2.hasNext()) {
            System.out.println(itr2.next());
        }

        // Update
        // collection.updateOne(Filters.eq("name", "Sam"), Updates.set("age", 13));

        // Delete
        // collection.deleteOne(Filters.eq("name", "Sam"));

    }
}