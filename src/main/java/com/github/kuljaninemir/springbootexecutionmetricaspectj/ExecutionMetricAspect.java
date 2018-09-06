package com.github.kuljaninemir.springbootexecutionmetricaspectj;

import io.micrometer.core.instrument.Metrics;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
public class ExecutionMetricAspect {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* *(..)) && @annotation(com.github.kuljaninemir.springbootexecutionmetricaspectj.ExecutionMetric)")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        ExecutionMetric executionMetric = getExecutionMetric(joinPoint);
        String metricName = executionMetric.value();
        logger.trace("Trying to capture metrics for "+metricName);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        long duration = stopWatch.getLastTaskTimeMillis();

        Metrics.counter("counter."+metricName).increment();
        Metrics.gauge("gauge."+metricName, duration);
        Metrics.timer("timer."+metricName).record(duration, TimeUnit.MILLISECONDS);

        logger.trace("Time elapsed for "+metricName+": "+duration + " ms");

        return proceed;
    }

    private ExecutionMetric getExecutionMetric(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ExecutionMetric executionMetric = method.getAnnotation(ExecutionMetric.class);
        if (executionMetric == null && method.getDeclaringClass().isInterface()) {
            final String methodName = signature.getName();
            final Class<?> implementationClass = joinPoint.getTarget().getClass();
            final Method implementationMethod = implementationClass.getDeclaredMethod(methodName, method.getParameterTypes());
            executionMetric = implementationMethod.getAnnotation(ExecutionMetric.class);
        }
        return executionMetric;
    }
}