package com.safeway.emclclient.emcl.model;

import java.util.List;

public class CustomerInformation {

    private String aggregateId;
    private Short messageType;
    private Short messageVersion;
    private Integer storeNumber;
    private Integer terminalNumber;
    private Short transactionNumber;
    private Short sequenceNumber;
    private String entryCode;
    private String responseCode;

    private String customerNumber;
    private String eventType;
    private String requestId;
    private List<String> clubCard;
    private String phone;
    private String houseHold;
    private String firstName;
    private String lastName;
    private String receiptPrintInd;
    private String customerTypeInd;
    private String customerCardRedeemInd;
    private String email;
    private String digitalRegInd;
    private String guid;
    private String emailVerifyInd;
    private String digitalReceiptInd;
    private String HHIDDigitalRegInd;
    private String profilePublishInd;
    private String UUID;
    private String clientId;
    private String reservedArea;

    /** Header fields **/
    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public Short getMessageType() {
        return messageType;
    }

    public void setMessageType(Short messageType) {
        this.messageType = messageType;
    }

    public Short getMessageVersion() {
        return messageVersion;
    }

    public void setMessageVersion(Short messageVersion) {
        this.messageVersion = messageVersion;
    }

    public Integer getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(Integer storeNumber) {
        this.storeNumber = storeNumber;
    }

    public Integer getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(Integer terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public Short getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Short transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Short getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Short sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /** Body fields **/

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHHIDDigitalRegInd() {
        return HHIDDigitalRegInd;
    }

    public void setHHIDDigitalRegInd(String HHIDDigitalRegInd) {
        this.HHIDDigitalRegInd = HHIDDigitalRegInd;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<String> getClubCard() {
        return clubCard;
    }

    public void setClubCard(List<String> clubCard) {
        this.clubCard = clubCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHouseHold() {
        return houseHold;
    }

    public void setHouseHold(String houseHold) {
        this.houseHold = houseHold;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getReceiptPrintInd() {
        return receiptPrintInd;
    }

    public void setReceiptPrintInd(String receiptPrintInd) {
        this.receiptPrintInd = receiptPrintInd;
    }

    public String getCustomerTypeInd() {
        return customerTypeInd;
    }

    public void setCustomerTypeInd(String customerTypeInd) {
        this.customerTypeInd = customerTypeInd;
    }

    public String getCustomerCardRedeemInd() {
        return customerCardRedeemInd;
    }

    public void setCustomerCardRedeemInd(String customerCardRedeemInd) {
        this.customerCardRedeemInd = customerCardRedeemInd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDigitalRegInd() {
        return digitalRegInd;
    }

    public void setDigitalRegInd(String digitalRegInd) {
        this.digitalRegInd = digitalRegInd;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getEmailVerifyInd() {
        return emailVerifyInd;
    }

    public void setEmailVerifyInd(String emailVerifyInd) {
        this.emailVerifyInd = emailVerifyInd;
    }

    public String getDigitalReceiptInd() {
        return digitalReceiptInd;
    }

    public void setDigitalReceiptInd(String digitalReceiptInd) {
        this.digitalReceiptInd = digitalReceiptInd;
    }

    public String getProfilePublishInd() {
        return profilePublishInd;
    }

    public void setProfilePublishInd(String profilePublishInd) {
        this.profilePublishInd = profilePublishInd;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getReservedArea() {
        return reservedArea;
    }

    public void setReservedArea(String reservedArea) {
        this.reservedArea = reservedArea;
    }
}
