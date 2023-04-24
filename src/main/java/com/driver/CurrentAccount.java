package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
      super(name,balance,5000);
      if(balance<5000){
          throw new Exception("Insufficient Balance");
      }
      this.tradeLicenseId=tradeLicenseId;
      validateLicenseId();
    }
    private boolean isValidLicenseId(String id) {
        for (int i = 0; i < id.length() - 1; i++) {
            if (id.charAt(i) == id.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private String rearrangeLicenseId(String id) {
        StringBuilder sb = new StringBuilder(id);
        for (int i = 1; i < id.length(); i += 2) {
            if (i == id.length() - 1 || id.charAt(i) == id.charAt(i + 1)) {
                sb.setCharAt(i, (char) ((id.charAt(i) + 1 - 'A') % 26 + 'A'));
            }
        }
        return sb.toString();
    }
    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if (isValidLicenseId(tradeLicenseId)) {
            return;
        }
        String rearrangedId = rearrangeLicenseId(tradeLicenseId);
        if (!isValidLicenseId(rearrangedId)) {
            throw new Exception("Valid License can not be generated");
        }
        tradeLicenseId = rearrangedId;
    }


}

