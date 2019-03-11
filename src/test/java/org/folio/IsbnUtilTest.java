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

  public void testValid10DigitsIsbn() {
    assertTrue(IsbnUtil.isValid10DigitNumber("1930110995"));
    assertTrue(IsbnUtil.isValid10DigitNumber("1-930110-99-5"));
    assertTrue(IsbnUtil.isValid10DigitNumber("1 930110 99 5"));
    assertTrue(IsbnUtil.isValid10DigitNumber("020163385X"));
    assertTrue(IsbnUtil.isValid10DigitNumber("0-201-63385-X"));
    assertTrue(IsbnUtil.isValid10DigitNumber("0 201 63385 X"));
    assertTrue(IsbnUtil.isValid10DigitNumber("0060250143"));
    assertTrue(IsbnUtil.isValid10DigitNumber("0122746058"));
  }

  public void testValid13DigitsIsbn() {
    assertTrue(IsbnUtil.isValid13DigitNumber("9781930110991"));
    assertTrue(IsbnUtil.isValid13DigitNumber("9781474418416"));
    assertTrue(IsbnUtil.isValid13DigitNumber("9781474418430"));
    assertTrue(IsbnUtil.isValid13DigitNumber("978-1-930110-99-1"));
    assertTrue(IsbnUtil.isValid13DigitNumber("978 1 930110 99 1"));
    assertTrue(IsbnUtil.isValid13DigitNumber("9780201633856"));
    assertTrue(IsbnUtil.isValid13DigitNumber("978-0-201-63385-6"));
    assertTrue(IsbnUtil.isValid13DigitNumber("978 0 201 63385 6"));
    assertTrue(IsbnUtil.isValid13DigitNumber("979-10-90636-07-1"));
    assertTrue(IsbnUtil.isValid13DigitNumber("9791843123391"));
  }

  public void testInvalid10DigitsIsbn() {
    assertFalse(IsbnUtil.isValid10DigitNumber(null));
    assertFalse(IsbnUtil.isValid10DigitNumber(""));
    assertFalse(IsbnUtil.isValid10DigitNumber("012 27460 58"));
    // no alphas allowed in ISBN-10 except check digit of X
    assertFalse(IsbnUtil.isValid10DigitNumber("BR18694353"));
    assertFalse(IsbnUtil.isValid10DigitNumber("0317Y63929"));
    assertFalse(IsbnUtil.isValid10DigitNumber("0318Y29948"));
    assertFalse(IsbnUtil.isValid10DigitNumber("0318Y40638"));
    assertFalse(IsbnUtil.isValid10DigitNumber("0318Y40648"));
    // invalid check digit
    String baseCode = "193011099";
    assertFalse(IsbnUtil.isValid10DigitNumber(baseCode + "0"));
    assertFalse(IsbnUtil.isValid10DigitNumber(baseCode + "1"));
    assertFalse(IsbnUtil.isValid10DigitNumber(baseCode + "2"));
    assertFalse(IsbnUtil.isValid10DigitNumber(baseCode + "3"));
    assertFalse(IsbnUtil.isValid10DigitNumber(baseCode + "4"));
    assertFalse(IsbnUtil.isValid10DigitNumber(baseCode + "6"));
    assertFalse(IsbnUtil.isValid10DigitNumber(baseCode + "7"));
    assertFalse(IsbnUtil.isValid10DigitNumber(baseCode + "8"));
    assertFalse(IsbnUtil.isValid10DigitNumber(baseCode + "9"));
    assertFalse(IsbnUtil.isValid10DigitNumber(baseCode + "X"));
  }

  public void testInvalid13DigitsIsbn() {
    assertFalse(IsbnUtil.isValid13DigitNumber(null));
    assertFalse(IsbnUtil.isValid13DigitNumber(""));
    assertFalse(IsbnUtil.isValid13DigitNumber("97819 301109 91"));
    // can never be an X check digit on a 13-digit ISBN
    assertFalse(IsbnUtil.isValid13DigitNumber("978033521257X"));
    assertFalse(IsbnUtil.isValid13DigitNumber("978041536907X"));
    assertFalse(IsbnUtil.isValid13DigitNumber("978047089024X"));
    // 13-digit can only start with 978 or 979
    assertFalse(IsbnUtil.isValid13DigitNumber("9760987652805"));
    // incorrect check digit for an ISBN-13
    assertFalse(IsbnUtil.isValid13DigitNumber("9783859512551"));
    assertFalse(IsbnUtil.isValid13DigitNumber("9788849120731"));
    assertFalse(IsbnUtil.isValid13DigitNumber("9788860815955"));
    assertFalse(IsbnUtil.isValid13DigitNumber("9788861343380"));
    assertFalse(IsbnUtil.isValid13DigitNumber("9788874700118"));
    assertFalse(IsbnUtil.isValid13DigitNumber("9788880636536"));
    // may have just prepended 978 to ISBN-10
    assertFalse(IsbnUtil.isValid13DigitNumber("9780521343283"));
    assertFalse(IsbnUtil.isValid13DigitNumber("9782711848329"));
    assertFalse(IsbnUtil.isValid13DigitNumber("9782869582218"));
    assertFalse(IsbnUtil.isValid13DigitNumber("9782913667325"));
    assertFalse(IsbnUtil.isValid13DigitNumber("9788849212224"));
    assertFalse(IsbnUtil.isValid13DigitNumber("9788851601038"));
    assertFalse(IsbnUtil.isValid13DigitNumber("9788871403541"));
    // ISBN too short
    assertFalse(IsbnUtil.isValid13DigitNumber("97888868156"));
    assertFalse(IsbnUtil.isValid13DigitNumber("978039184243"));
    assertFalse(IsbnUtil.isValid13DigitNumber("978179491128"));
  }

  public void testConversion10To13Digits() {
    assertNull(IsbnUtil.convertTo13DigitNumber(null));
    assertNull(IsbnUtil.convertTo13DigitNumber("978"));
    assertNull(IsbnUtil.convertTo13DigitNumber("8992a83426"));

    assertEquals("9788992783422", IsbnUtil.convertTo13DigitNumber("8992783426"));
    assertEquals("9780845144022", IsbnUtil.convertTo13DigitNumber("0845144022"));
    assertEquals("9780916984779", IsbnUtil.convertTo13DigitNumber("091698477X"));
    assertEquals("9780916984809", IsbnUtil.convertTo13DigitNumber("091698480X"));
    assertEquals("9780122746017", IsbnUtil.convertTo13DigitNumber("0122746015"));
    assertEquals("9780122746024", IsbnUtil.convertTo13DigitNumber("0122746023"));
    assertEquals("9780122746031", IsbnUtil.convertTo13DigitNumber("0122746031"));
    assertEquals("9780122746048", IsbnUtil.convertTo13DigitNumber("012274604X"));

    assertEquals("9781930110991", IsbnUtil.convertTo13DigitNumber("1930110995"));
    assertEquals("9781930110991", IsbnUtil.convertTo13DigitNumber("1-930110-99-5"));
    assertEquals("9781930110991", IsbnUtil.convertTo13DigitNumber("1 930110 99 5"));

    assertEquals("9780201633856", IsbnUtil.convertTo13DigitNumber(" 020163385X "));
    assertEquals("9780201633856", IsbnUtil.convertTo13DigitNumber(" 0-201-63385-X "));
    assertEquals("9780201633856", IsbnUtil.convertTo13DigitNumber(" 0 201 63385 X "));
  }

  public void testConversion13To10Digits() {
    assertNull(IsbnUtil.convertTo10DigitNumber(null));
    assertNull(IsbnUtil.convertTo10DigitNumber("9790201633856"));
    assertNull(IsbnUtil.convertTo10DigitNumber("9791843123391"));
    assertNull(IsbnUtil.convertTo10DigitNumber("978"));
    assertNull(IsbnUtil.convertTo10DigitNumber("978193a110991"));

    assertEquals("8992783426", IsbnUtil.convertTo10DigitNumber("9788992783422"));
    assertEquals("0845144022", IsbnUtil.convertTo10DigitNumber("9780845144022"));
    assertEquals("091698477X", IsbnUtil.convertTo10DigitNumber("9780916984779"));
    assertEquals("091698480X", IsbnUtil.convertTo10DigitNumber("9780916984809"));
    assertEquals("0122746015", IsbnUtil.convertTo10DigitNumber("9780122746017"));
    assertEquals("0122746023", IsbnUtil.convertTo10DigitNumber("9780122746024"));
    assertEquals("0122746031", IsbnUtil.convertTo10DigitNumber("9780122746031"));
    assertEquals("012274604X", IsbnUtil.convertTo10DigitNumber("9780122746048"));

    assertEquals("1930110995", IsbnUtil.convertTo10DigitNumber("9781930110991"));
    assertEquals("1930110995", IsbnUtil.convertTo10DigitNumber("978-1-930110-99-1"));
    assertEquals("1930110995", IsbnUtil.convertTo10DigitNumber("978 1 930110 99 1"));

    assertEquals("020163385X", IsbnUtil.convertTo10DigitNumber(" 9780201633856 "));
    assertEquals("020163385X", IsbnUtil.convertTo10DigitNumber(" 978-0-201-63385-6 "));
    assertEquals("020163385X", IsbnUtil.convertTo10DigitNumber(" 978 0 201 63385 6 "));
  }

  public void testFormatIsbnWithHyphens() {
    assertEquals("978-0-321-13002-0", IsbnUtil.formatWithHyphens("9780321130020"));
    assertEquals("0-321-13002-2", IsbnUtil.formatWithHyphens("0321130022"));
    assertEquals("979-10-90636-07-1", IsbnUtil.formatWithHyphens("9791090636071"));
    assertEquals("978-0-12-274602-4", IsbnUtil.formatWithHyphens("9780122746024"));
    assertEquals("999999999-9", IsbnUtil.formatWithHyphens("9999999999"));
  }
}
