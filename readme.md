[![Build Status](https://travis-ci.org/kuljaninemir/spring-boot-execution-metric-aspectj.svg?branch=master)](https://travis-ci.org/kuljaninemir/spring-boot-execution-metric-aspectj) [![HitCount](http://hits.dwyl.com/kuljaninemir/spring-boot-execution-metric-aspectj.svg)](http://hits.dwyl.com/kuljaninemir/spring-boot-execution-metric-aspectj) [![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/dwyl/esta/issues)

# Spring boot execution metric aspectj for graphite

This dependency lets you:
- Use Spring Boot 2.x metrics with AspectJ.
- Use one annotation to measure counter, gauge and timed data.
- Annotate any objects, not just spring components.
- Minimal config to export data to Graphite.
- Configure environment and app-name on the data.

## Usage

Add the following dependencies:

```
<dependency>
  <groupId>com.github.kuljaninemir</groupId>
  <artifactId>spring-boot-execution-metric-aspectj</artifactId>
  <version>1.0.12</version>
</dependency>
<dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjweaver</artifactId>
  <version>1.8.13</version>
</dependency>
```

Configure aspectj-maven-plugin and set it to weave this jar:

```
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>aspectj-maven-plugin</artifactId>
  <version>1.11</version>
  <configuration>
    <complianceLevel>1.8</complianceLevel>
    <source>1.8</source>
    <target>1.8</target>
    <showWeaveInfo>true</showWeaveInfo>
    <verbose>true</verbose>
    <Xlint>ignore</Xlint>
    <encoding>UTF-8</encoding>
    <XnoInline>true</XnoInline>
    <proc>none</proc>
    <weaveDependencies>
      <weaveDependency>
        <groupId>com.github.kuljaninemir</groupId>
        <artifactId>spring-boot-execution-metric-aspectj</artifactId>
      </weaveDependency>
    </weaveDependencies>
  </configuration>
  <executions>
    <execution>
      <goals>
        <!-- use this goal to weave all your main classes -->
        <goal>compile</goal>
        <!-- use this goal to weave all your test classes -->
        <goal>test-compile</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

Configure the properties as you like, for example:

```
management.metrics.export.graphite.host=localhost
management.metrics.export.graphite.port=8097
management.metrics.export.graphite.step=10s
management.metrics.export.graphite.tags-as-prefix=graphite-prefix-env,graphite-prefix-app
management.metrics.export.graphite.prefix-tag-env=dev
management.metrics.export.graphite.prefix-tag-app=myapp1
management.metrics.export.graphite.enabled=true
management.metrics.export.graphite.protocol=plaintext
management.metrics.web.server.auto-time-requests=true
```

Make sure you have a Configuration that scans this package:

```
@Configuration
@ComponentScan("com.github.kuljaninemir.springbootexecutionmetricaspectj")
public class MyConfig {
}
```



Compile with maven

```
mvn clean
mvn compile
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

I use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/kuljaninemir/spring-boot-execution-metric-aspectj/tags). 

## Authors

* **Emir Kuljanin** - [kuljaninemir](https://github.com/kuljaninemir)

See also the list of [contributors](https://github.com/kuljaninemir/spring-boot-execution-metric-aspectj/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
