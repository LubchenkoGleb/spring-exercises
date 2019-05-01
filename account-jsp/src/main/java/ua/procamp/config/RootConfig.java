package ua.procamp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.procamp.TestDataGenerator;

/**
 * This class provides application root (non-web) configuration.
 * <p>
 * todo: 1. Mark this class as config
 * todo: 2. Enable component scanning for all packages in "ua.procamp" using annotation property "basePackages"
 * todo: 3. Exclude web related config and beans (ignore @{@link Controller}, ignore {@link EnableWebMvc})
 * todo: 4. Configure {@link TestDataGenerator} bean with name "dataGenerator" (don't specify name explicitly)
 */
@Configuration
@ComponentScan(
        basePackages = "ua.procamp",
        excludeFilters = {
                @ComponentScan.Filter(value = {Controller.class}),
                @ComponentScan.Filter(value = {EnableWebMvc.class})
        })
public class RootConfig {

    @Bean
    public TestDataGenerator dataGenerator() {
        return new TestDataGenerator();
    }
}
