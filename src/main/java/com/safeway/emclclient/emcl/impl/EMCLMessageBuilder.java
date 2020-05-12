package com.safeway.emclclient.emcl.impl;

import com.safeway.emclclient.emcl.model.CustomerInformation;
import com.safeway.emclclient.emcl.utils.HexUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.safeway.emclclient.emcl.utils.EMCLProxyConstants.*;

@Service
public class EMCLMessageBuilder {
    private static final Log logger = LogFactory.getLog(EMCLMessageBuilder.class);

    public List<String> buildT5Message(CustomerInformation customerInformation) {
        List<String> messages = new ArrayList<>();

        String metadata = buildMetadata(customerInformation);
        List<String> bodies = buildBody(customerInformation);
        for (String body : bodies) {
            StringBuilder sb = new StringBuilder(metadata);
            sb.append(body);
            sb.insert(0, buildHeader(sb.toString()));
            messages.add(sb.toString());
        }

        return messages;
    }
    private String buildHeader(String msg) {
        StringBuffer header = new StringBuffer(S);
        String leftPadded = StringUtils.leftPad(String.valueOf(msg.length()/2), 4, ZERO);
        for(char c: leftPadded.toCharArray()) {
            header.append(Integer.toHexString(c));
        }
        header.append(E);
        return header.toString();
    }
    private String buildMetadata(CustomerInformation customerInformation) {
        short msgType = customerInformation.getMessageType() != null? customerInformation.getMessageType() : MSG_TYPE;
        short msgVersion = customerInformation.getMessageVersion() != null? customerInformation.getMessageVersion() : MSG_VERSION;
        int storeNum = customerInformation.getStoreNumber() != null? customerInformation.getStoreNumber() : DEFAULT_STORE_NUMBER;
        int terminalNum = customerInformation.getTerminalNumber() != null? customerInformation.getTerminalNumber() : DEFAULT_TERMINAL_NUMBER;
        short transNum = customerInformation.getTransactionNumber() != null? customerInformation.getTransactionNumber() : DEFAULT_TRANSACTION_NUMBER;
        short seqNum = customerInformation.getSequenceNumber() != null? customerInformation.getSequenceNumber() : DEFAULT_SEQUENCE_NUMBER;
        String customerNumber = StringUtils.rightPad(
                customerInformation.getCustomerNumber() != null? customerInformation.getCustomerNumber() : IGNORE_STR, 16, SPACE);
        String entryCode = customerInformation.getEntryCode() != null? customerInformation.getEntryCode() : DEFAULT_ENTRY_CODE;
        String respCode = DEFAULT_RESP_CODE;

        StringBuilder sb = new StringBuilder();
        sb.append(HexUtil.shortToHex(msgType));
        sb.append(HexUtil.shortToHex(msgVersion));
        sb.append(HexUtil.intToHex(storeNum));
        sb.append(HexUtil.intToHex(terminalNum));
        sb.append(HexUtil.shortToHex(transNum));
        sb.append(HexUtil.shortToHex(seqNum));
        sb.append(HexUtil.toHexString(customerNumber.getBytes()));
        sb.append(HexUtil.toHexString(entryCode.getBytes()));
        sb.append(DOUBLE_ZERO);
        sb.append(respCode);

        return sb.toString();
    }
    private static List<String> buildBody(CustomerInformation customerInformation) {
        String eventType = StringUtils.rightPad(
                customerInformation.getEventType() != null? customerInformation.getEventType() : IGNORE_STR, 50, SPACE);
        String requestId = StringUtils.rightPad(
                customerInformation.getRequestId() != null? customerInformation.getRequestId() : IGNORE_STR,36, SPACE);
        String phoneNumber = StringUtils.rightPad(
                customerInformation.getPhone() != null? customerInformation.getPhone() : IGNORE_STR, 10, SPACE);
        String householdId = StringUtils.rightPad(
                customerInformation.getHouseHold() != null? customerInformation.getHouseHold() : IGNORE_STR, 19, SPACE);
        String firstName = StringUtils.rightPad(
                customerInformation.getFirstName() != null? customerInformation.getFirstName() : IGNORE_STR, 30, SPACE);
        String lastName = StringUtils.rightPad(
                customerInformation.getLastName() != null? customerInformation.getLastName() : IGNORE_STR, 30, SPACE);
        String receiptPrintInd = StringUtils.rightPad(
                customerInformation.getReceiptPrintInd() != null? customerInformation.getReceiptPrintInd() : IGNORE_INT, 2, SPACE);
        String customerTypeInd = StringUtils.rightPad(
                customerInformation.getCustomerTypeInd() != null? customerInformation.getCustomerTypeInd() : IGNORE_INT, 2, SPACE);
        String customerCardRedeemInd = StringUtils.rightPad(
                customerInformation.getCustomerCardRedeemInd() != null? customerInformation.getCustomerCardRedeemInd() : IGNORE_STR, 6, SPACE);
        String emailAddress =  StringUtils.rightPad(
                customerInformation.getEmail() != null? customerInformation.getEmail() : IGNORE_STR, 50, SPACE);
        String digitalRegInd = StringUtils.rightPad(
                customerInformation.getDigitalRegInd() != null? customerInformation.getDigitalRegInd() : IGNORE_INT, 2, SPACE);
        String guid = StringUtils.rightPad(
                customerInformation.getGuid() != null? customerInformation.getGuid() : IGNORE_STR, 50, SPACE);
        String emailVerifyInd = StringUtils.rightPad(
                customerInformation.getEmailVerifyInd() != null? customerInformation.getEmailVerifyInd() : IGNORE_INT, 3, SPACE);
        String digitalReceiptInd = StringUtils.rightPad(
                customerInformation.getDigitalReceiptInd() != null? customerInformation.getDigitalReceiptInd() : IGNORE_INT, 2, SPACE);
        String hhidDigitalRegInd = StringUtils.rightPad(
                customerInformation.getHHIDDigitalRegInd() != null? customerInformation.getHHIDDigitalRegInd() : IGNORE_STR, 5, SPACE);
        String profilePublishInd = StringUtils.rightPad(
                customerInformation.getProfilePublishInd() != null? customerInformation.getProfilePublishInd() : IGNORE_STR, 5, SPACE);
        String uuid = StringUtils.rightPad(
                customerInformation.getUUID() != null? customerInformation.getUUID() : IGNORE_STR, 36, SPACE);
        String clientId = StringUtils.rightPad(
                customerInformation.getClientId() != null? customerInformation.getClientId() : IGNORE_STR, 18, SPACE);
        String reservedArea = StringUtils.rightPad(
                customerInformation.getReservedArea() != null? customerInformation.getReservedArea() : IGNORE_STR, 30, SPACE);

        List<String> bodies = new ArrayList<>();
        for (String cc : customerInformation.getClubCard()) {
            String clubCard = StringUtils.rightPad(cc, 18, SPACE);

            StringBuilder sb = new StringBuilder();
            sb.append(HexUtil.toHexString(clubCard.getBytes()));
            sb.append(HexUtil.toHexString(phoneNumber.getBytes()));
            sb.append(HexUtil.toHexString(householdId.getBytes()));
            sb.append(HexUtil.toHexString(firstName.getBytes()));
            sb.append(HexUtil.toHexString(lastName.getBytes()));
            sb.append(HexUtil.toHexString(receiptPrintInd.getBytes()));
            sb.append(HexUtil.toHexString(customerTypeInd.getBytes()));
            sb.append(HexUtil.toHexString(customerCardRedeemInd.getBytes()));
            sb.append(HexUtil.toHexString(emailAddress.getBytes()));
            sb.append(HexUtil.toHexString(digitalRegInd.getBytes()));
            sb.append(HexUtil.toHexString(guid.getBytes()));
            sb.append(HexUtil.toHexString(emailVerifyInd.getBytes()));
            sb.append(HexUtil.toHexString(digitalReceiptInd.getBytes()));
            sb.append(HexUtil.toHexString(hhidDigitalRegInd.getBytes()));
            sb.append(HexUtil.toHexString(profilePublishInd.getBytes()));
            sb.append(HexUtil.toHexString(uuid.getBytes()));
            sb.append(HexUtil.toHexString(clientId.getBytes()));
            sb.append(HexUtil.toHexString(reservedArea.getBytes()));
            sb.append(HexUtil.toHexString(requestId.getBytes()));
            sb.append(HexUtil.toHexString(eventType.getBytes()));
            bodies.add(sb.toString());
        }
        return bodies;
    }
}