package eandradep.io.geojsonproject.models.service.gcpconfig

interface IGcpUploadService {

    fun uploadFileGcpStorage(filePath: String, fileName: String, geJsonData: String): String?
}