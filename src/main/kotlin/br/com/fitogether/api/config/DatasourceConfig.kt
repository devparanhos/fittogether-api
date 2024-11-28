package br.com.fitogether.api.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.datasource")
data class DatasourceConfig(
    val url: String,
    val username: String,
    val password: String
)