package br.com.fitogether.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ApiApplication {

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<ApiApplication>(*args)
		}
	}
}
 