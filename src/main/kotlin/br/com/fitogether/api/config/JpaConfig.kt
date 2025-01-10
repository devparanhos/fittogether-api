package br.com.fitogether.api.config

import br.com.fitogether.api.data.repository.SoftDeleteRepositoryImpl
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@Configuration
@EnableJpaRepositories(
    basePackages = ["br.com.fitogether.api.data.repository"], // Substitua pelo pacote dos seus repositórios
    repositoryBaseClass = SoftDeleteRepositoryImpl::class // Classe base customizada
)
class JpaConfig