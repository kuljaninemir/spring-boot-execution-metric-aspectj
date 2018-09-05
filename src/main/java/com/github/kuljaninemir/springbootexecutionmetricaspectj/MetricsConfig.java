package com.github.kuljaninemir.springbootexecutionmetricaspectj;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.util.HierarchicalNameMapper;
import io.micrometer.graphite.GraphiteConfig;
import io.micrometer.graphite.GraphiteMeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class MetricsConfig {
    @Value("${management.metrics.export.graphite.host}")
    private String hostName;

    @Value("${management.metrics.export.graphite.tags-as-prefix}")
    private String[] tagAsPrefix;

    @Value("${management.metrics.export.graphite.prefix-tag-env}")
    private String prefixTagEnv;

    @Value("${management.metrics.export.graphite.prefix-tag-app}")
    private String prefixTagApp;

    @Bean(name="graphiteBean")
    public MeterRegistry graphiteLoggingRegistry() {
        GraphiteConfig graphiteConfig = new GraphiteConfig() {
            @Override
            public String host() {
                return hostName;
            }

            @Override
            public String get(String k) {
                //Accept the rest of the defaults.
                return null;
            }
        };
        MeterRegistry registry = new GraphiteMeterRegistry(graphiteConfig, Clock.SYSTEM, HierarchicalNameMapper.DEFAULT);
        return registry;
    }

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config()
                .commonTags(tagAsPrefix[0], prefixTagEnv, tagAsPrefix[1], prefixTagApp);
    }
}