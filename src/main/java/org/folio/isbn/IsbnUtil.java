package org.folio.isbn;

/**
 * Class with utility methods for working with ISBN number: convert and validate
 */
public class IsbnUtil {

  private IsbnUtil() {
  }

  /**
   * Validate ten-digit ISBN
   *
   * @param number - ten-digit ISBN
   * @return - is input number is valid for ten-digit ISBN format
   */
  public static boolean isValidTenDigitNumber(String number) {
    return false;
  }

  /**
   * Validate thirteen-digit ISBN
   *
   * @param number - thirteen-digit ISBN
   * @return - is input number is valid for thirteen-digit ISBN format
   */
  public static boolean isValidThirteenDigitNumber(String number) {
    return false;
  }

  /**
   * Convert from ten-digit ISBN to the thirteen-digit ISBN
   *
   * @param number - valid ten-digit ISBN
   * @return - thirteen-digit ISBN
   */
  public static String convertToThirteenDigitNumber(String number) {
    return "";
  }

  /**
   * Convert from thirteen-digit ISBN to the ten-digit ISBN
   *
   * @param number - valid thirteen-digit ISBN
   * @return - ten-digit ISBN
   */
  public static String convertToTenDigitNumber(String number) {
    return "";
  }
}
