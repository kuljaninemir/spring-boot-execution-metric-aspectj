[![Build Status](https://travis-ci.org/kuljaninemir/spring-boot-execution-metric-aspectj.svg?branch=master)](https://travis-ci.org/kuljaninemir/spring-boot-execution-metric-aspectj) [![Coverage Status](https://codecov.io/gh/kuljaninemir/spring-boot-execution-metric-aspectj/branch/master/graph/badge.svg)](https://codecov.io/gh/kuljaninemir/spring-boot-execution-metric-aspectj) [![HitCount](http://hits.dwyl.com/kuljaninemir/spring-boot-execution-metric-aspectj.svg)](http://hits.dwyl.com/kuljaninemir/spring-boot-execution-metric-aspectj) [![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/dwyl/esta/issues)

# Spring boot execution metric aspectj

This repository is based on [spring-boot-execution-metric](https://github.com/lukashinsch/spring-boot-execution-metric) by lukashinschs. Gradle is swapped for Maven. Minor modifications are made to be able to use this with AspectJ instead of Spring AOP.

## Usage

Add the following dependencies:

```
<dependency>
  <groupId>com.github.kuljaninemir</groupId>
  <artifactId>spring-boot-execution-metric-aspectj</artifactId>
  <version>0.0.1</version>
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

* **Emir Kuljanin** - *Initial work* - [lukashinsch](https://github.com/lukashinsch)

See also the list of [contributors](https://github.com/kuljaninemir/spring-boot-execution-metric-aspectj/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
