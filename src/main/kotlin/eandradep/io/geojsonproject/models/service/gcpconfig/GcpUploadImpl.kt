package eandradep.io.geojsonproject.models.service.gcpconfig

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import org.springframework.stereotype.Service
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Paths


@Service
class GcpUploadImpl : IGcpUploadService {

    private val storage: Storage? = StorageOptions.newBuilder()
        .setCredentials(
            GoogleCredentials
                .fromStream(
                    FileInputStream("src/main/resources/static/gcp_key.json")
                )
        )
        .build()
        .service
    private val bucketName: String = "mibuseta3-bucket-01"

    override fun uploadFileGcpStorage(filePath: String, fileName: String, geJsonData: String): String? {
        val file = File(filePath)
        try {
            if (!file.exists()) {
                file.createNewFile()
            }
            val fw = FileWriter(file)
            val bw = BufferedWriter(fw)
            bw.write(geJsonData)
            bw.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return this.testUploadFile(file = file, fileName = fileName)
    }

    private fun testUploadFile(file: File, fileName: String): String {
        val blobId: BlobId = BlobId.of(bucketName, fileName)

        val blobInfo: BlobInfo = BlobInfo.newBuilder(blobId).setContentType("application/json").build()
        val data = Files.readAllBytes(Paths.get(file.toURI()))
        val blob = storage!!.create(
            blobInfo,
            data
        )
        return blob.mediaLink.toString()
    }


}