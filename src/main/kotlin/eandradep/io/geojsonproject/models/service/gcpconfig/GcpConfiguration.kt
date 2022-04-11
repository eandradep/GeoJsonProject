package eandradep.io.geojsonproject.models.service.gcpconfig

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import com.google.cloud.storage.Storage.PredefinedAcl
import com.google.cloud.storage.StorageOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


class GcpConfiguration {

    @Autowired
    val storage: Storage? = StorageOptions.newBuilder().setCredentials(GoogleCredentials
        .fromStream(FileInputStream("src/main/resources/static/mibuseta3-ea9209e7f0bf.json"))).build().service

    fun testUploadFile(){
        val blobId: BlobId = BlobId.of("mibuseta3-bucket-01","9c9a6167-f11c-4599-8817-3db870fbe36e.json")
        val blobInfo: BlobInfo = BlobInfo.newBuilder(blobId).build()
        val file: File = File("/home/edisonandrade/Escritorio/", "9c9a6167-f11c-4599-8817-3db870fbe36e.json")
        val data = Files.readAllBytes(Paths.get(file.toURI()))
        val blob = this.storage!!.create(blobInfo, data)
        blob.mediaLink
    }


}