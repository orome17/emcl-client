package com.safeway.emclclient.emcl.utils;

public class EMCLProxyConstants {
    public static short MSG_TYPE = 5;
    public static short MSG_VERSION = 15;
    public static int DEFAULT_STORE_NUMBER = 970;
    public static int DEFAULT_TERMINAL_NUMBER = 6;
    public static short DEFAULT_TRANSACTION_NUMBER = 1;
    public static short DEFAULT_SEQUENCE_NUMBER = 1;
    public static String DEFAULT_ENTRY_CODE = "P";
    public static String DEFAULT_RESP_CODE = "0000";
    public static String S = "53";
    public static String E = "45";
    public static String ZERO = "0";
    public static String DOUBLE_ZERO = "00";
    public static String IGNORE_STR = "ign";
    public static String IGNORE_INT = "99";
    public static String SPACE = " ";

    // Indicators keys
    public static String PUBLISH_PROFILE_IND = "PublishProfile";
    public static String NAME_PRINTED_RECEIPT_IND = "NamePrintedReceipt";
    public static String CUSTOMER_TYPE_IND = "CustomerType";
    public static String DIGITAL_RECEIPT_IND = "DigitalReceipt";
    public static String DIGITAL_REG_IND = "DigitalReg";

    // Attribute Constants
    public static String CLUBCARD = "CLUBCARD";

    // EventTypes
    public static String HOUSEHOLD_EVENT = "HouseholdAssociateEvent";
    public static String CARDLESS_REGISTRATION_SMS_EVENT = "CardlessRegistrationSMSSentEvent";
    public static String REGISTRATION_REMINDER_SMS_EVENT = "CustomerRegistrationReminderSMSSentEvent";
}
