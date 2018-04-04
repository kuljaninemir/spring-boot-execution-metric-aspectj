package com.github.kuljaninemir.springbootexecutionmetricaspectj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lh on 28/04/15.
 */
@Configuration
public class ExecutionMetricAutoConfiguration {
    public ExecutionMetricAutoConfiguration(GaugeService gaugeService, CounterService counterService){
        ExecutionMetricAspect.factory = new ExecutionMetricFactory(counterService, gaugeService);
    }
}
