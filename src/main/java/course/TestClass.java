/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author zeus
 */
public class TestClass {

    final static MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost"));
    final static MongoDatabase studentsDatabase = mongoClient.getDatabase("students");

    public static void main(String[] args) {

        
         MongoCollection<Document> usersCollection = studentsDatabase.getCollection("grades");
         
         
         
         
         List<Document> documents = usersCollection.find(new Document("type","homework")).sort(new Document("student_id",1).append("score", 1)).into(new ArrayList<Document>());
         
         
         Map<Integer,Document> studentsTodelete = new HashMap<>();
         
        for (Document document : documents ){
            
            
            if (studentsTodelete.containsKey(  document.getInteger("student_id")) ==false){
                usersCollection.deleteOne(document);
                //studentsTodelete.put(document.getInteger("student_id"), document);
            }
            
        }
         
         
         
         documents.forEach(s -> {System.out.println(s);} );
         
         
         System.out.println("Total: "+studentsTodelete.size());
        
    }

}
