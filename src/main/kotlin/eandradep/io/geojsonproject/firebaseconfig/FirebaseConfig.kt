package eandradep.io.geojsonproject.firebaseconfig

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
class FirebaseConfig {

    @Bean
    fun getDB(): Firestore {
        try {
            val serviceAccount = FileInputStream("src/main/resources/static/firebase.json")

            val options: FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://mibuseta3.firebaseio.com")
                .build()

            val firebaseApp: FirebaseApp = FirebaseApp.initializeApp(options)
            return FirestoreClient.getFirestore(firebaseApp)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return FirestoreClient.getFirestore()
    }

}