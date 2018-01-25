package resources.databases;

import com.mongodb.*;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

public class ConnectDB {
    private static SocketFactory _sf = null;
    public MongoClient connectToRecommendedSSLAtlasMongoClient() {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, trustAllCerts, null);
            _sf = context.getSocketFactory();
        } catch (GeneralSecurityException e) {
            System.out.println(e.getStackTrace());
        }
        MongoClientURI uri = new MongoClientURI(
                "mongodb://testAdmin:abc123!@cluster0-shard-00-00-esgno.mongodb.net:27017,cluster0-shard-00-01-esgno.mongodb.net:27017,cluster0-shard-00-02-esgno.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        return mongoClient;
    }
    private static TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        public void checkClientTrusted(X509Certificate[] certs, String authType) {
        }
        public void checkServerTrusted(X509Certificate[] certs, String authType) {
        }
    }};
}
