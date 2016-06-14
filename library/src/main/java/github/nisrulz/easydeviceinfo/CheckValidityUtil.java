package github.nisrulz.easydeviceinfo;

/**
 * The type Check validity util.
 */
class CheckValidityUtil {

  /**
   * Handle illegal character in result string.
   *
   * @param result the result
   * @return the string
   */
  static String handleIllegalCharacterInResult(String result) {
    String tempResult = result;
    if (result.indexOf(" ") > 0) {
      tempResult = result.replaceAll(" ", "_");
    }
    return tempResult;
  }

  /**
   * Check valid data string.
   *
   * @param data the data
   * @return the string
   */
  static String checkValidData(String data) {
    String tempData = data;
    if (data == null || data.length() == 0) {
      tempData = "NA";
    }
    return tempData;
  }

  /**
   * Check valid data string [ ].
   *
   * @param data the data
   * @return the string [ ]
   */
  static String[] checkValidData(String[] data) {
    String[] tempData = data;
    if (data == null || data.length == 0) {
      tempData = new String[] { "-" };
    }
    return tempData;
  }
}
