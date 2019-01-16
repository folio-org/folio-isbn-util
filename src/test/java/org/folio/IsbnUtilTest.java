package org.folio;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.folio.isbn.IsbnUtil;

/**
 * Unit test for App.
 */
public class IsbnUtilTest extends TestCase {

  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public IsbnUtilTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(IsbnUtilTest.class);
  }

  public void testValidTenDigitsIsbn() {
    assertTrue(IsbnUtil.isValidTenDigitNumber("1930110995"));
    assertTrue(IsbnUtil.isValidTenDigitNumber("1-930110-99-5"));
    assertTrue(IsbnUtil.isValidTenDigitNumber("1 930110 99 5"));
    assertTrue(IsbnUtil.isValidTenDigitNumber("020163385X"));
    assertTrue(IsbnUtil.isValidTenDigitNumber("0-201-63385-X"));
    assertTrue(IsbnUtil.isValidTenDigitNumber("0 201 63385 X"));
    assertTrue(IsbnUtil.isValidTenDigitNumber("0060250143"));
    assertTrue(IsbnUtil.isValidTenDigitNumber("0122746058"));
  }

  public void testValidThirteenDigitsIsbn() {
    assertTrue(IsbnUtil.isValidThirteenDigitNumber("9781930110991"));
    assertTrue(IsbnUtil.isValidThirteenDigitNumber("9781474418416"));
    assertTrue(IsbnUtil.isValidThirteenDigitNumber("9781474418430"));
    assertTrue(IsbnUtil.isValidThirteenDigitNumber("978-1-930110-99-1"));
    assertTrue(IsbnUtil.isValidThirteenDigitNumber("978 1 930110 99 1"));
    assertTrue(IsbnUtil.isValidThirteenDigitNumber("9780201633856"));
    assertTrue(IsbnUtil.isValidThirteenDigitNumber("978-0-201-63385-6"));
    assertTrue(IsbnUtil.isValidThirteenDigitNumber("978 0 201 63385 6"));
    assertTrue(IsbnUtil.isValidThirteenDigitNumber("979-10-90636-07-1"));
  }

  public void testInvalidTenDigitsIsbn() {
    assertFalse(IsbnUtil.isValidTenDigitNumber(null));
    assertFalse(IsbnUtil.isValidTenDigitNumber(""));
    assertFalse(IsbnUtil.isValidTenDigitNumber("012 27460 58"));
    // no alphas allowed in ISBN-10 except check digit of X
    assertFalse(IsbnUtil.isValidTenDigitNumber("BR18694353"));
    assertFalse(IsbnUtil.isValidTenDigitNumber("0317Y63929"));
    assertFalse(IsbnUtil.isValidTenDigitNumber("0318Y29948"));
    assertFalse(IsbnUtil.isValidTenDigitNumber("0318Y40638"));
    assertFalse(IsbnUtil.isValidTenDigitNumber("0318Y40648"));
    // invalid check digit
    String baseCode = "193011099";
    assertFalse(IsbnUtil.isValidTenDigitNumber(baseCode + "0"));
    assertFalse(IsbnUtil.isValidTenDigitNumber(baseCode + "1"));
    assertFalse(IsbnUtil.isValidTenDigitNumber(baseCode + "2"));
    assertFalse(IsbnUtil.isValidTenDigitNumber(baseCode + "3"));
    assertFalse(IsbnUtil.isValidTenDigitNumber(baseCode + "4"));
    assertFalse(IsbnUtil.isValidTenDigitNumber(baseCode + "6"));
    assertFalse(IsbnUtil.isValidTenDigitNumber(baseCode + "7"));
    assertFalse(IsbnUtil.isValidTenDigitNumber(baseCode + "8"));
    assertFalse(IsbnUtil.isValidTenDigitNumber(baseCode + "9"));
    assertFalse(IsbnUtil.isValidTenDigitNumber(baseCode + "X"));
  }

  public void testInvalidThirteenDigitsIsbn() {
    assertFalse(IsbnUtil.isValidThirteenDigitNumber(null));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber(""));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("97819 301109 91"));
    // can never be an X check digit on a 13-digit ISBN
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("978033521257X"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("978041536907X"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("978047089024X"));
    // 13-digit can only start with 978 or 979
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9760987652805"));
    // incorrect check digit for an ISBN-13
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9783859512551"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9788849120731"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9788860815955"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9788861343380"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9788874700118"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9788880636536"));
    // may have just prepended 978 to ISBN-10
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9780521343283"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9782711848329"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9782869582218"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9782913667325"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9788849212224"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9788851601038"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("9788871403541"));
    // ISBN too short
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("97888868156"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("978039184243"));
    assertFalse(IsbnUtil.isValidThirteenDigitNumber("978179491128"));
  }

  public void testConversion10To13Digits() {
    assertNull(IsbnUtil.convertToThirteenDigitNumber(null));
    assertNull(IsbnUtil.convertToThirteenDigitNumber("978"));
    assertNull(IsbnUtil.convertToThirteenDigitNumber("8992a83426"));

    assertEquals("9788992783422", IsbnUtil.convertToThirteenDigitNumber("8992783426"));
    assertEquals("9780845144022", IsbnUtil.convertToThirteenDigitNumber("0845144022"));
    assertEquals("9780916984779", IsbnUtil.convertToThirteenDigitNumber("091698477X"));
    assertEquals("9780916984809", IsbnUtil.convertToThirteenDigitNumber("091698480X"));
    assertEquals("9780122746017", IsbnUtil.convertToThirteenDigitNumber("0122746015"));
    assertEquals("9780122746024", IsbnUtil.convertToThirteenDigitNumber("0122746023"));
    assertEquals("9780122746031", IsbnUtil.convertToThirteenDigitNumber("0122746031"));
    assertEquals("9780122746048", IsbnUtil.convertToThirteenDigitNumber("012274604X"));

    assertEquals("9781930110991", IsbnUtil.convertToThirteenDigitNumber("1930110995"));
    assertEquals("9781930110991", IsbnUtil.convertToThirteenDigitNumber("1-930110-99-5"));
    assertEquals("9781930110991", IsbnUtil.convertToThirteenDigitNumber("1 930110 99 5"));

    assertEquals("9780201633856", IsbnUtil.convertToThirteenDigitNumber(" 020163385X "));
    assertEquals("9780201633856", IsbnUtil.convertToThirteenDigitNumber(" 0-201-63385-X "));
    assertEquals("9780201633856", IsbnUtil.convertToThirteenDigitNumber(" 0 201 63385 X "));
  }

  public void testConversion13To10Digits() {
    assertNull(IsbnUtil.convertToTenDigitNumber(null));
    assertNull(IsbnUtil.convertToTenDigitNumber("9790201633856"));
    assertNull(IsbnUtil.convertToTenDigitNumber("978"));
    assertNull(IsbnUtil.convertToTenDigitNumber("978193a110991"));

    assertEquals("8992783426", IsbnUtil.convertToTenDigitNumber("9788992783422"));
    assertEquals("0845144022", IsbnUtil.convertToTenDigitNumber("9780845144022"));
    assertEquals("091698477X", IsbnUtil.convertToTenDigitNumber("9780916984779"));
    assertEquals("091698480X", IsbnUtil.convertToTenDigitNumber("9780916984809"));
    assertEquals("0122746015", IsbnUtil.convertToTenDigitNumber("9780122746017"));
    assertEquals("0122746023", IsbnUtil.convertToTenDigitNumber("9780122746024"));
    assertEquals("0122746031", IsbnUtil.convertToTenDigitNumber("9780122746031"));
    assertEquals("012274604X", IsbnUtil.convertToTenDigitNumber("9780122746048"));

    assertEquals("1930110995", IsbnUtil.convertToTenDigitNumber("9781930110991"));
    assertEquals("1930110995", IsbnUtil.convertToTenDigitNumber("978-1-930110-99-1"));
    assertEquals("1930110995", IsbnUtil.convertToTenDigitNumber("978 1 930110 99 1"));

    assertEquals("020163385X", IsbnUtil.convertToTenDigitNumber(" 9780201633856 "));
    assertEquals("020163385X", IsbnUtil.convertToTenDigitNumber(" 978-0-201-63385-6 "));
    assertEquals("020163385X", IsbnUtil.convertToTenDigitNumber(" 978 0 201 63385 6 "));
  }

}
