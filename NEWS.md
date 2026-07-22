## 1.10.0 In Progress

### Breaking changes
* Description ([ISSUE](https://folio-org.atlassian.net/browse/ISSUE))

### Features
* Description ([ISSUE](https://folio-org.atlassian.net/browse/ISSUE))

### Bug fixes
* Description ([ISSUE](https://folio-org.atlassian.net/browse/ISSUE))

### Tech Dept
* Migrate unit tests from JUnit 4 to JUnit 5
* Add `maven-checkstyle-plugin` and enforce `folio-java-checkstyle` rules
* Add `maven-source-plugin` and `maven-javadoc-plugin` to attach sources/javadocs on deploy

### Dependencies
* Bump `commons-validator` from `1.9.0` to `1.10.1`
* Bump `com.github.ladutsko:isbn-core` from `1.5.3` to `2.1.0`
* Bump `org.glassfish.jaxb:jaxb-runtime` from `2.3.9` to `4.0.9`
* Bump `org.apache.logging.log4j:log4j-core` from `2.25.3` to `2.26.1`
* Bump `junit` from `4.13.2` to `org.junit.jupiter:junit-jupiter 6.1.2`
* Bump `maven-enforcer-plugin` from `3.5.0` to `3.6.3`
* Bump `maven-compiler-plugin` from `3.13.0` to `3.15.0`
* Bump `maven-surefire-plugin` from `3.5.2` to `3.5.6`
* Bump `maven-release-plugin` from `3.1.1` to `3.3.1`
* Add `maven-checkstyle-plugin 3.6.0`
* Add `checkstyle 13.7.0`
* Add `folio-java-checkstyle 1.2.0`
* Add `maven-source-plugin 3.4.0`
* Add `maven-javadoc-plugin 3.12.0`
* Remove `maven-shade-plugin`

## 1.9.0 2026-04-10
* Update dependencies

## 1.8.0 2025-03-07
* [ISBNUTIL-27](https://folio-org.atlassian.net/browse/ISBNUTIL-27) Update to folio-isbn-util Java 21   

## 1.7.0 2024-11-21
* [ISBNUTIL-23](https://folio-org.atlassian.net/browse/ISBNUTIL-23) Upgrade dependencies for Ramsons; fix jackson-core DoS

## 1.6.0 2023-10-11
* [ISBNUTIL-19](https://issues.folio.org/browse/ISBNUTIL-19) Upgrade folio-isbn-util to Java 17

## 1.5.0 2023-02-06
* [MODDATAIMP-750](https://issues.folio.org/browse/MODDATAIMP-750) Update dependencies
* [MODDATAIMP-736](https://issues.folio.org/browse/MODDATAIMP-736) Adjust logging configuration to display datetime in a proper format
* [ISBNUTIL-14](https://issues.folio.org/browse/ISBNUTIL-14) Logging improvement
* [ISBNUTIL-17](https://issues.folio.org/browse/ISBNUTIL-17) Logging improvement - Configuration

## 1.4.0 2022-05-23
* [ISBNUTIL-15](https://issues.folio.org/browse/ISBNUTIL-15) Upgrade commons-validator from 1.6 to 1.7

## 1.3.0 2021-01-11
* [ISBNUTIL-11](https://issues.folio.org/browse/ISBNUTIL-11) Upgrade folio-isbn-util to Java 11.
* [ISBNUTIL-10](https://issues.folio.org/browse/ISBNUTIL-10) Add personal data disclosure form.

## 1.2.0 2019-10-09
 * Added functionality to remove hyphens

## 1.1.0 2019-03-20
 * Implemented formatting of ISBN numbers with hyphens

## 1.0.0 2019-01-17
 * Implemented methods for validation and conversion of ISBN-10 and ISBN-13 numbers

## 0.0.1 2018-12-16
 * Initial module setup
 * Util's public interface added
