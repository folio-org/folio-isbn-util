# folio-isbn-util

Copyright (C) 2018-2024 The Open Library Foundation

This software is distributed under the terms of the Apache License,
Version 2.0. See the file "[LICENSE](LICENSE)" for more information.

## Introduction

ISBN number converter utilities.

Provides implementation of methods for validation and conversion of ISBN-10 and ISBN-13 numbers using [Apache Commons Validator](https://commons.apache.org/proper/commons-validator/) library

 | METHOD                                | DESCRIPTION                                                                                                                                                                                                                                                                                 |
 |---------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
 | isValid10DigitNumber(String isbn10)   | Checks the code is a valid ISBN-10 code. Returns <code>true</code> if a valid ISBN-10 code, otherwise <code>false</code>                                                                                                                                                                    |
 | isValid13DigitNumber(String isbn13)   | Checks the code is a valid ISBN-13 code. Returns <code>true</code> if a valid ISBN-10 code, otherwise <code>false</code>                                                                                                                                                                    |
 | convertTo13DigitNumber(String isbn10) | Converts an ISBN-10 code to an ISBN-13 code. Accepts ISBN-10 with or without formatting characters. Returns a converted ISBN-13 code or <code>null</code> if the ISBN-10 code is not valid                                                                                                  |
 | convertTo10DigitNumber(String isbn13) | Converts an ISBN-13 code to an ISBN-10 code if possible. Accepts ISBN-13 with or without formatting characters. Returns a converted ISBN-10 code or <code>null</code> if the ISBN-13 code is not valid or does not have an ISBN-10 code. Only 978-prefixed ISBNs can be converted to ISBN-10|

 In order to use the library add the dependency to the pom.xml file:

 ```
    <dependency>
      <groupId>org.folio</groupId>
      <artifactId>folio-isbn-util</artifactId>
      <version>x.y.z*</version>
      <type>jar</type>
    </dependency>
 ```
*x.y.z - latest release version

## Additional information

* See project [ISBNUTIL](https://issues.folio.org/browse/ISBNUTIL)
at the [FOLIO issue tracker](https://dev.folio.org/guidelines/issue-tracker).

* Other FOLIO Developer documentation is at [dev.folio.org](https://dev.folio.org/)
