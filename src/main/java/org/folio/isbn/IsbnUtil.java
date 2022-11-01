package org.folio.isbn;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.validator.routines.ISBNValidator;
import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.ISBN10CheckDigit;

import com.github.ladutsko.isbn.ISBNException;
import com.github.ladutsko.isbn.ISBNFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class with utility methods for working with ISBN number
 */
public final class IsbnUtil {

  private static final String PREFIX_OF_ISBN13_ABLE_TO_CONVERT_TO_ISBN10 = "978";
  private static final String HYPHEN_GROUP_SEPARATOR = "-";
  private static Logger logger = LogManager.getLogger(IsbnUtil.class);
  private static ISBNValidator validator = ISBNValidator.getInstance();
  private static ISBNFormat isbnFormatter = new ISBNFormat();

  private IsbnUtil() {
  }

  /**
   * Check the code is a valid ISBN-10 code.
   *
   * @param isbn10 The code to validate.
   * @return <code>true</code> if a valid ISBN-10
   * code, otherwise <code>false</code>.
   */
  public static boolean isValid10DigitNumber(String isbn10) {
    logger.debug("isValid10DigitNumber:: parameter isbn10: {}", isbn10);
    return validator.isValidISBN10(isbn10);
  }

  /**
   * Check the code is a valid ISBN-13 code.
   *
   * @param isbn13 The code to validate.
   * @return <code>true</code> if a valid ISBN-13
   * code, otherwise <code>false</code>.
   */
  public static boolean isValid13DigitNumber(String isbn13) {
    logger.debug("isValid13DigitNumber:: parameter isbn13: {}", isbn13);
    return validator.isValidISBN13(isbn13);
  }

  /**
   * Convert an ISBN-10 code to an ISBN-13 code.
   * <p>
   * This method accepts ISBN-10 with or without formatting
   * characters.
   *
   * @param isbn10 The ISBN-10 code to convert
   * @return A converted ISBN-13 code or <code>null</code>
   * if the ISBN-10 code is not valid
   */
  public static String convertTo13DigitNumber(String isbn10) {
    logger.debug("convertTo13DigitNumber:: parameter isbn10: {}", isbn10);
    return validator.convertToISBN13(validator.validateISBN10(isbn10));
  }

  /**
   * Convert an ISBN-13 code to an ISBN-10 code if possible.
   * Only 978-prefixed ISBNs can be converted to ISBN-10.
   * <p>
   * This method accepts ISBN-13 with or without formatting
   * characters.
   *
   * @param isbn13 The ISBN-13 code to convert
   * @return A converted ISBN-10 code or <code>null</code>
   * if the ISBN-13 code is not valid or does not have an ISBN-10 code.
   * @see org.apache.commons.validator.routines.ISBNValidator#convertToISBN13(String)
   *
   * @author Julian Ladisch
   *
   */
  public static String convertTo10DigitNumber(String isbn13) {
    logger.debug("convertTo10DigitNumber:: parameter isbn13: {}", isbn13);
    String input = validator.validateISBN13(isbn13);
    logger.info("Matched substring: {}", input);
    if (input == null || !input.startsWith(PREFIX_OF_ISBN13_ABLE_TO_CONVERT_TO_ISBN10)) {
      logger.warn("No match found for ISBN13 in {}", isbn13);
      return null;
    }
    // drop "978" and the original check digit
    String isbn10 = input.substring(3, 12);
    try {
      return isbn10 + ISBN10CheckDigit.ISBN10_CHECK_DIGIT.calculate(isbn10);
    } catch (CheckDigitException e) {
      logger.warn("Check digit error for '{}' - {}", isbn13,  e.getMessage());
      throw new IllegalArgumentException("Check digit error for '" + isbn13 + "' - " + e.getMessage());
    }
  }

  /**
   * Format ISBN-10 or ISBN-13 code with hyphens.
   *
   * @param isbn The code to format.
   * @return formatted ISBN
   * @throws IllegalArgumentException if specified invalid ISBN code
   * @see com.github.ladutsko.isbn.ISBNFormat#format(CharSequence, String)
   */
  public static String insertHyphens(String isbn) {
    logger.debug("insertHyphens:: parameter isbn: {}", isbn);
    if (!(isValid10DigitNumber(isbn) || isValid13DigitNumber(isbn))) {
      logger.warn("ISBN value is invalid: {}", isbn);
      throw new IllegalArgumentException("ISBN value is invalid: " + isbn);
    }
    try {
      return isbnFormatter.format(isbn, HYPHEN_GROUP_SEPARATOR);
    } catch (ISBNException e) {
      logger.warn("Error during call isbnFormatter.format with parameters input '{}', groupSeparator '{}' - {}", isbn, HYPHEN_GROUP_SEPARATOR, e.getMessage());
      throw new IllegalArgumentException(e.getMessage(), e);
    }
  }

  public static String removeHyphens(String isbn) {
    logger.debug("removeHyphens:: parameter isbn: {}", isbn);
    if (!(isValid10DigitNumber(isbn) || isValid13DigitNumber(isbn))) {
      logger.warn("ISBN value is invalid: {}", isbn);
      throw new IllegalArgumentException("ISBN value is invalid: " + isbn);
    }
    return Stream.of(isbn.split("-")).collect(Collectors.joining());
  }
}
