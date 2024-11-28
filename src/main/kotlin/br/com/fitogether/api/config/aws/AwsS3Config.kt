package br.com.fitogether.api.config.aws

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

@Configuration
class AwsS3Config(private val awsS3Properties: AwsS3Properties) {
    @Bean
    fun s3Client(): S3Client {
        val credentials = AwsBasicCredentials.create(
            awsS3Properties.accessKey,
            awsS3Properties.secretKey
        )

        return S3Client.builder()
            .region(Region.of(awsS3Properties.region))
            .credentialsProvider { credentials }
            .build()
    }
}