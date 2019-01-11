package org.folio.isbn;

import org.apache.commons.validator.routines.ISBNValidator;
import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.ISBN10CheckDigit;

/**
 * Class with utility methods for working with ISBN number
 */
public final class IsbnUtil {

  private static ISBNValidator validator = ISBNValidator.getInstance();

  private IsbnUtil() {
  }

  /**
   * Check the code is a valid ISBN-10 code.
   *
   * @param number The code to validate.
   * @return <code>true</code> if a valid ISBN-10
   * code, otherwise <code>false</code>.
   */
  public static boolean isValidTenDigitNumber(String number) {
    return validator.isValidISBN10(number);
  }

  /**
   * Check the code is a valid ISBN-13 code.
   *
   * @param number The code to validate.
   * @return <code>true</code> if a valid ISBN-13
   * code, otherwise <code>false</code>.
   */
  public static boolean isValidThirteenDigitNumber(String number) {
    return validator.isValidISBN13(number);
  }

  /**
   * Convert an ISBN-10 code to an ISBN-13 code.
   * <p>
   * This method requires a valid ISBN-10 with NO formatting
   * characters.
   *
   * @param isbn10 The ISBN-10 code to convert
   * @return A converted ISBN-13 code or <code>null</code>
   * if the ISBN-10 code is not valid
   */
  public static String convertToThirteenDigitNumber(String isbn10) {
    return validator.convertToISBN13(isbn10);
  }

  /**
   * Convert an ISBN-13 code to an ISBN-10 code if possible.
   * <p>
   * This method requires a valid ISBN-13 with NO formatting
   * characters.
   *
   * @param isbn13 The ISBN-13 code to convert
   * @return A converted ISBN-10 code or <code>null</code>
   * if the ISBN-13 code is not valid or does not have an ISBN-10 code.
   * @throws IllegalArgumentException if the input is not 0 or 13 characters long
   * or contains a character that is not a digit.
   * @see org.apache.commons.validator.routines.ISBNValidator#convertToISBN13(String)
   *
   * @author Julian Ladisch
   *
   */
  public static String convertToTenDigitNumber(String isbn13) {
    if (isbn13 == null) {
      return null;
    }

    String input = isbn13.trim();
    if (input.length() != 13) {
      throw new IllegalArgumentException("Invalid length " + input.length() + " for '" + input + "'");
    }

    if (! input.startsWith("978")) {
      return null;
    }

    // drop "978" and the original check digit
    String isbn10 = input.substring(3, 12);
    try {
      return isbn10 + ISBN10CheckDigit.ISBN10_CHECK_DIGIT.calculate(isbn10);
    } catch (CheckDigitException e) {
      throw new IllegalArgumentException("Check digit error for '" + input + "' - " + e.getMessage());
    }
  }
}
