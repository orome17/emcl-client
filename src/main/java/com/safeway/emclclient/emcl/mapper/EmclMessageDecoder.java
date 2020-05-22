package com.safeway.emclclient.emcl.mapper;

import com.safeway.emclclient.emcl.dto.EMCLLookUpResponse;
import com.safeway.emclclient.emcl.dto.Error;
import com.safeway.emclclient.emcl.utils.HexUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class EmclMessageDecoder {

    private static final Log logger = LogFactory.getLog(EmclMessageDecoder.class);

    public EMCLLookUpResponse decodeT2Message(String[] bytes){

        EMCLLookUpResponse emclLookUpResponse = new EMCLLookUpResponse();
        try {
            // Read the first 6 bytes to extract the length of the message
            StringBuilder lengthBuilder = new StringBuilder("");
            for (int ctr = 0; ctr < 6; ctr += 1) {
                lengthBuilder.append(bytes[ctr]);
            }
            String msgLenAscii = HexUtil.convertHexToASCII(lengthBuilder.toString());
            int length = 0;
            if (msgLenAscii.startsWith("S") && msgLenAscii.endsWith("E")) {
                String numericValue = msgLenAscii.substring(msgLenAscii.indexOf('S') + 1, msgLenAscii.indexOf('E'));
                length = Integer.parseInt(numericValue);
            }
            List<String> messageTypeBuffer = new ArrayList<>();
            List<String> messageVersionBuffer = new ArrayList<>();
            List<String> storeNumberBuffer = new ArrayList<>();
            List<String> termNumberBuffer = new ArrayList<>();
            List<String> transactionNumberBuffer = new ArrayList<>();
            List<String> termSequenceBuffer = new ArrayList<>();
            StringBuilder customerNumberBuffer = new StringBuilder("");
            StringBuilder entryCodeBuffer = new StringBuilder("");
            List<String> responseCodeBuffer = new ArrayList<>();
            List<String> customerStatusBuffer = new ArrayList<>();
            List<String> redemptionFlagBuffer = new ArrayList<>();
            StringBuilder secondaryIdBuffer = new StringBuilder("");
            List<String> totalSavingsBuffer = new ArrayList<>();
            StringBuilder customerNameBuffer = new StringBuilder("");
            List<String> numOfOffersBuffer = new ArrayList<>();
            List<String> nuOfSegmentsBuffer = new ArrayList<>();
            List<String> textSizeBuffer = new ArrayList<>();
            List<String> resvSizeBuffer = new ArrayList<>();
            List<String> segmentIdBuffer = new ArrayList<>();
            List<String> shopOfferBuffer = new ArrayList<>();
            StringBuilder householdIdBuffer = new StringBuilder("");
            List<String> textAreaBuffer = new ArrayList<>();
            StringBuilder footerBuffer = new StringBuilder("");
            String numOffsB = "00";
            String numOfSegmentsB = "00";
            String textSizeB = "00";
            String resvSizeB = "00";

            for (int ctr = 0; ctr < bytes.length; ctr += 1) {
                if (ctr == 6 || ctr == 7) {
                    messageTypeBuffer.add(bytes[ctr]);
                } else if (ctr == 8 || ctr == 9) {
                    messageVersionBuffer.add(bytes[ctr]);
                } else if (ctr >= 10 && ctr <= 13) {
                    storeNumberBuffer.add(bytes[ctr]);
                } else if (ctr >= 14 && ctr <= 17) {
                    termNumberBuffer.add(bytes[ctr]);
                } else if (ctr == 18 || ctr == 19) {
                    transactionNumberBuffer.add(bytes[ctr]);
                } else if (ctr == 20 || ctr == 21) {
                    termSequenceBuffer.add(bytes[ctr]);
                } else if (ctr >= 22 && ctr <= 37) {
                    customerNumberBuffer.append(bytes[ctr]);
                } else if (ctr == 38) {
                    entryCodeBuffer.append(bytes[ctr]);
                } else if (ctr >= 40 && ctr <= 41) {
                    responseCodeBuffer.add(bytes[ctr]);
                } else if (ctr == 42 || ctr == 43) {
                    customerStatusBuffer.add(bytes[ctr]);
                } else if (ctr == 44 || ctr == 45) {
                    redemptionFlagBuffer.add(bytes[ctr]);
                } else if (ctr >= 46 && ctr <= 61) {
                    secondaryIdBuffer.append(bytes[ctr]);
                } else if (ctr >= 62 && ctr <= 65) {
                    totalSavingsBuffer.add(bytes[ctr]);
                } else if (ctr >= 66 && ctr <= 95) {
                    customerNameBuffer.append(bytes[ctr]);
                } else if (ctr == 96 || ctr == 97) {
                    numOfOffersBuffer.add(bytes[ctr]);
                    if (ctr == 96) {
                        numOffsB = bytes[ctr];
                    }
                } else if (ctr == 98 || ctr == 99) {
                    nuOfSegmentsBuffer.add(bytes[ctr]);
                    if (ctr == 98) {
                        numOfSegmentsB = bytes[ctr];
                    }
                } else if (ctr == 100 || ctr == 101) {
                    textSizeBuffer.add(bytes[ctr]);
                } else if (ctr == 102 || ctr == 103) {
                    resvSizeBuffer.add(bytes[ctr]);
                    if (ctr == 102) {
                        resvSizeB = bytes[ctr];
                    }

                } else if (ctr >= 104 && ctr < (104 + (2 * HexUtil.convertHexToDecimal(numOfSegmentsB)))) {
                    segmentIdBuffer.add(bytes[ctr]);
                } else if (ctr >= 104 + (2 * HexUtil.convertHexToDecimal(numOfSegmentsB))
                        && ctr < 104 + (2 * HexUtil.convertHexToDecimal(numOfSegmentsB)) +
                        (6 * HexUtil.convertHexToDecimal(numOffsB))) {
                    shopOfferBuffer.add(bytes[ctr]);
                } else if (ctr >= 104 + (2 * HexUtil.convertHexToDecimal(numOfSegmentsB)) +
                        (6 * HexUtil.convertHexToDecimal(numOffsB))
                        && ctr < 104 + (2 * HexUtil.convertHexToDecimal(numOfSegmentsB)) +
                        (6 * HexUtil.convertHexToDecimal(numOffsB)) + HexUtil.convertHexToDecimal(resvSizeB)) {
                    householdIdBuffer.append(bytes[ctr]);
                } else if (ctr >= 104 + (2 * HexUtil.convertHexToDecimal(numOfSegmentsB)) +
                        (6 * HexUtil.convertHexToDecimal(numOffsB)) + HexUtil.convertHexToDecimal(resvSizeB)
                        && ctr < 104 + (2 * HexUtil.convertHexToDecimal(numOfSegmentsB)) +
                        (6 * HexUtil.convertHexToDecimal(numOffsB)) + HexUtil.convertHexToDecimal(resvSizeB) +
                        HexUtil.convertHexToDecimal(textSizeB)) {
                    textAreaBuffer.add(bytes[ctr]);

                } else if (ctr >= 104 + (2 * HexUtil.convertHexToDecimal(numOfSegmentsB)) +
                        (6 * HexUtil.convertHexToDecimal(numOffsB)) + HexUtil.convertHexToDecimal(resvSizeB) +
                        HexUtil.convertHexToDecimal(textSizeB)) {
                    footerBuffer.append(bytes[ctr]);
                }

            }
            logger.info("Response:: T2 message : "+length + "|" + HexUtil.convertHexToDecimal(messageTypeBuffer) +
                                       "|" + HexUtil.convertHexToDecimal(messageVersionBuffer) +
                                       "|" + HexUtil.convertHexToDecimal(storeNumberBuffer) +
                                       "|" + HexUtil.convertHexToDecimal(termNumberBuffer) +
                                       "|" + HexUtil.convertHexToDecimal(transactionNumberBuffer) +
                                       "|" + HexUtil.convertHexToDecimal(termSequenceBuffer) +
                                       "|" + HexUtil.convertHexToASCII(customerNumberBuffer.toString()) +
                                       "|" + HexUtil.convertHexToASCII(entryCodeBuffer.toString()) +
                                       "|" + HexUtil.convertHexToDecimal(responseCodeBuffer) +
                                       "|" + HexUtil.convertHexToDecimal(customerStatusBuffer) +
                                       "|" + HexUtil.convertHexToDecimal(redemptionFlagBuffer) +
                                       "|" + HexUtil.convertHexToASCII(secondaryIdBuffer.toString()) +
                                       "|" + HexUtil.convertHexToDecimal(totalSavingsBuffer) +
                                       "|" + HexUtil.convertHexToASCII(customerNameBuffer.toString()) +
                                       "|" + HexUtil.convertHexToDecimal(numOfOffersBuffer) +
                                       "|" + HexUtil.convertHexToDecimal(nuOfSegmentsBuffer) +
                                       "|" + HexUtil.convertHexToDecimal(textSizeBuffer) +
                                       "|" + HexUtil.convertHexToDecimal(resvSizeBuffer) +
                                       "|" + HexUtil.convertHexToDecimal(segmentIdBuffer) +
                                       "|" + HexUtil.convertHexToDecimal(shopOfferBuffer) +
                                       "|" + HexUtil.convertHexToASCII(householdIdBuffer.toString()) +
                                       "|" + HexUtil.convertHexToDecimal(textAreaBuffer) +
                                       "|" + HexUtil.convertHexToASCII(footerBuffer.toString()) +
                                       "|");

            if (HexUtil.convertHexToDecimal(responseCodeBuffer).equals("0")){
                emclLookUpResponse.setPhoneNumber(HexUtil.convertHexToASCII(secondaryIdBuffer.toString()));
                emclLookUpResponse.setClubCardNumber(HexUtil.convertHexToASCII(customerNumberBuffer.toString()));
                emclLookUpResponse.setHouseholdId(HexUtil.convertHexToASCII(householdIdBuffer.toString()));
                emclLookUpResponse.setCustomerName(HexUtil.convertHexToASCII(customerNameBuffer.toString()));
            } else{
                Error error = new Error();
                error.setCode("RSS0116E");
                error.setMessage("Invalid phone/clubcard number");
                emclLookUpResponse.setErrors(Collections.singletonList(error));
            }

        } catch (Exception e) {
            logger.error("Error occurred while reading response", e);
            //TODO :: Throw exception
        }

        return emclLookUpResponse;
    }
}
