package hello;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class MeterRegistryCustomizerConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String serverPort;

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(){
        return registry -> registry.config().commonTags("application", applicationName);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsApplicationTags() throws UnknownHostException {
        final String hostName = InetAddress.getLocalHost().getHostName();
        return registry -> registry.config().commonTags(
                "service.name", applicationName,
                "service.host", hostName,
                "service.port", serverPort);
    }
}