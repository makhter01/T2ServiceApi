package resources.databases;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;

public class EmployeeDatabaseServices {
    public ConnectDB connectDB = new ConnectDB();
    public MongoClient mongoClient = new MongoClient();
    public static EmployeeFields employeeFields = new EmployeeFields();
    public EmployeeProfile employeeProfile = null;
    public List<EmployeeProfile> employeeProfileList = null;
    public boolean insertVCProfileNReturn(EmployeeProfile profile)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        try {
            String st = profile.getEmpName() + " " + "is Inserted";
            MongoClient mongoClient = connectDB.connectToRecommendedSSLAtlasMongoClient();
            MongoDatabase mongoDatabase = mongoClient.getDatabase("dev");
            MongoCollection mongoCollection = mongoDatabase.getCollection("profile");
            Document vcInfoDocument = documentEmployeeInfoData(profile);
            Document preparedDocument = new Document("empInfo",vcInfoDocument);
            mongoCollection.insertOne(preparedDocument);
            mongoClient.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if (mongoClient != null) {
                mongoClient = null;
            }
        }
        return true;
    }
    public static Document documentEmployeeInfoData(EmployeeProfile profile){
        Document document = new Document().append(employeeFields.empEmail, profile.getEmpEmail())
                .append(employeeFields.empName, profile.getEmpName())
                .append(employeeFields.salary,profile.getSalary())
                .append(employeeFields.department, profile.getDepartment());
        return document;
    }
    public List<EmployeeProfile> queryListOfEmployeeByID(String vcID)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        List<EmployeeProfile> vcList = new ArrayList<EmployeeProfile>();
        vcList = readData(vcID);
        return vcList;
    }
    public List<EmployeeProfile> readData(String empID)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException{
        final List<EmployeeProfile> empList = new ArrayList<EmployeeProfile>();
        try{
            mongoClient = connectDB.connectToRecommendedSSLAtlasMongoClient();
            MongoDatabase database = mongoClient.getDatabase("dev");
            MongoCollection<Document> collection = database.getCollection("profile");
            Document myDoc = collection.find(eq("_id", empID)).first();
            employeeProfile = new EmployeeProfile(myDoc);
            empList.add(employeeProfile);
            mongoClient.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if (mongoClient != null) {
                mongoClient = null;
            }
        }
        return empList;
    }
    public List<EmployeeProfile> queryListOfEmployee()throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        List<EmployeeProfile> vcList = new ArrayList<EmployeeProfile>();
        vcList = readData();
        return vcList;
    }
    public List<EmployeeProfile> readData()throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException{
        final List<EmployeeProfile> empList = new ArrayList<EmployeeProfile>();
        try{
            mongoClient = connectDB.connectToRecommendedSSLAtlasMongoClient();
            MongoDatabase database = mongoClient.getDatabase("dev");
            MongoCollection<Document> collection = database.getCollection("profile");
            MongoCursor<Document> cursor = collection.find().iterator();
            try {
                while (cursor.hasNext()) {
                    employeeProfile = new EmployeeProfile(cursor.next());
                    empList.add(employeeProfile);
                }
            } finally {
                cursor.close();
            }
            mongoClient.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if (mongoClient != null) {
                mongoClient = null;
            }
        }
        return empList;
    }
    public boolean updateEmpProfileByIDNReturn(EmployeeProfile profile)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        try{
            mongoClient = connectDB.connectToRecommendedSSLAtlasMongoClient();
            MongoDatabase mongoDatabase = mongoClient.getDatabase("dev");
            MongoCollection mongoCollection = mongoDatabase.getCollection("profile");
            Document empInfoDocument = documentEmployeeInfoData(profile);
            Document filter = new Document("_id", profile.getId());
            String id = filter.values().toString().replace("[","").replace("]","");
            Document preparedDocument = new Document("empInfo", empInfoDocument);
            mongoCollection.updateOne(new BasicDBObject("_id",new ObjectId(id)),new BasicDBObject("$set",new BasicDBObject(preparedDocument)));
            mongoClient.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if (mongoClient != null) {
                mongoClient = null;
            }
        }
        return true;
    }
    public void deleteEmpProfileByIDNReturn(String vcId)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        try{
            mongoClient = connectDB.connectToRecommendedSSLAtlasMongoClient();
            MongoDatabase mongoDatabase = mongoClient.getDatabase("dev");
            MongoCollection mongoCollection = mongoDatabase.getCollection("profile");
            BasicDBObject basicDBObject = new BasicDBObject("_id", new ObjectId(vcId));
            mongoCollection.deleteOne(basicDBObject);
            mongoClient.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if (mongoClient != null) {
                mongoClient = null;
            }
        }
    }
}
