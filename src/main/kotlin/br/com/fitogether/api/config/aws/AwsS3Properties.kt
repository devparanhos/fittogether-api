package br.com.fitogether.api.config.aws

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "aws.s3")
class AwsS3Properties {
    lateinit var accessKey: String
    lateinit var secretKey: String
    lateinit var region: String
    lateinit var bucketName: String
}