package kr.dataportal.application.persistence.config.jpa

import kr.dataportal.application.persistence.Persistence
import org.hibernate.cfg.AvailableSettings
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

/**
 * @author Heli
 * Created on 2022. 09. 06
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
    entityManagerFactoryRef = Jpa.DEFAULT_ENTITY_MANAGER,
    transactionManagerRef = Jpa.DEFAULT_TRANSACTION_MANAGER,
    basePackageClasses = [Persistence::class]
)
class JpaConfiguration(
    private val dataSource: DataSource
) {

    @Bean(Jpa.DEFAULT_ENTITY_MANAGER)
    fun entityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {

        val properties = mapOf(
            AvailableSettings.USE_SECOND_LEVEL_CACHE to false.toString(),
            AvailableSettings.USE_QUERY_CACHE to false.toString()
        )

        return builder
            .dataSource(dataSource)
            .packages(Persistence::class.java)
            .properties(properties)
            .persistenceUnit(Jpa.DEFAULT_PERSISTENCE_UNIT)
            .build()
    }

    @Bean(Jpa.DEFAULT_TRANSACTION_MANAGER)
    @Primary
    fun transactionManager(factory: EntityManagerFactory): JpaTransactionManager = JpaTransactionManager(factory)
}
