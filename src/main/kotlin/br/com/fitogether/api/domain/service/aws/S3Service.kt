package br.com.fitogether.api.domain.service.aws

import br.com.fitogether.api.config.aws.AwsS3Properties
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.*

@Service
class S3Service(
    private val s3Client: S3Client,
    private val awsS3Properties: AwsS3Properties
) {
    fun uploadFile(file: MultipartFile, userId: Long): ResponseEntity<String> {

        val prefix = "users/$userId"
        val fileName = "$prefix/${System.currentTimeMillis()}-${file.originalFilename}"
        clearObjectsByPrefix(prefix)

        try {
            // monta o request para fazer o upload da foto
            val request = PutObjectRequest.builder()
                .bucket(awsS3Properties.bucketName)
                .key(fileName)
                .contentType(file.contentType)
                .build()

            // executa o upload e retorna a url da foto
            s3Client.putObject(
                request,
                RequestBody.fromBytes(file.bytes)
            )

            val fileUrl = "https://${awsS3Properties.bucketName}.s3.${awsS3Properties.region}.amazonaws.com/$fileName"
            return ResponseEntity.ok("Imagem enviada com sucesso: $fileUrl")
        } catch (e: Exception) {
            throw RuntimeException("Erro ao fazer upload para o S3: ${e.message}")
        }
    }

    fun clearObjectsByPrefix(prefix: String) {
        try {
            // lista os objetos dentro da pasta para apagar posteriormente
            val listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(awsS3Properties.bucketName)
                .prefix(prefix)
                .build()

            val listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest)

            // Verifica se há objetos para excluir e percorre os objetos excluindo-os
            if (listObjectsResponse.hasContents() && listObjectsResponse.contents().isNotEmpty()) {
                val deleteObjectsRequest = DeleteObjectsRequest.builder()
                    .bucket(awsS3Properties.bucketName)
                    .delete(
                        Delete.builder()
                            .objects(
                                listObjectsResponse.contents().map {
                                    ObjectIdentifier.builder().key(it.key()).build()
                                }
                            )
                            .build()
                    )
                    .build()

                s3Client.deleteObjects(deleteObjectsRequest)
            }
        } catch (e: Exception) {
            throw RuntimeException("Erro ao deletar objetos no prefixo $prefix: ${e.message}", e)
        }
    }
}