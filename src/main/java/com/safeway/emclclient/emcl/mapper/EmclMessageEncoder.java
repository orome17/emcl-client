package com.safeway.emclclient.emcl.mapper;

import com.safeway.emclclient.emcl.dto.EMCLLookUpRequest;
import com.safeway.emclclient.emcl.utils.HexUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.safeway.emclclient.emcl.utils.EMCLProxyConstants.*;

@Component
public class EmclMessageEncoder {

    private static final Log logger = LogFactory.getLog(EmclMessageEncoder.class);

    public String encodeT1Message(EMCLLookUpRequest emclLookUpRequest) throws Exception {
        String customerNumber;
        String entryCode;
        if (StringUtils.isNotBlank(emclLookUpRequest.getPhoneNumber())){
            customerNumber = StringUtils.rightPad(emclLookUpRequest.getPhoneNumber(), 16, SPACE);
            entryCode = DEFAULT_SECONDARY_ENTRY_CODE;
        } else if (StringUtils.isNotBlank(emclLookUpRequest.getClubCardNumber())){
            customerNumber = StringUtils.rightPad(emclLookUpRequest.getClubCardNumber(), 16, SPACE);
            entryCode = DEFAULT_ENTRY_CODE;
        }else{
            throw new Exception();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(HexUtil.shortToHex(T1_MSG_TYPE));
        sb.append(HexUtil.shortToHex(MSG_VERSION));
        int storeId = StringUtils.isNotBlank(emclLookUpRequest.getStoreId()) ?
                Integer.parseInt(emclLookUpRequest.getStoreId()) : DEFAULT_STORE_NUMBER;
        sb.append(HexUtil.intToHex(storeId));
        sb.append(HexUtil.intToHex(DEFAULT_TERMINAL_NUMBER));
        sb.append(HexUtil.shortToHex(DEFAULT_TRANSACTION_NUMBER));
        sb.append(HexUtil.shortToHex(DEFAULT_SEQUENCE_NUMBER));
        sb.append(HexUtil.toHexString(customerNumber.getBytes()));
        sb.append(HexUtil.toHexString(entryCode.getBytes()));
        sb.append(DOUBLE_ZERO);
        sb.append(DEFAULT_RESP_CODE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
        String time = timeFormat.format(new Date());
        sb.append(HexUtil.intToHex(Integer.parseInt(date)));
        sb.append(HexUtil.intToHex(Integer.parseInt(time)));
        String length = buildHeader(sb.toString());
        sb.insert(0, length);
        logger.info("T1 request : "+length+"|"+T1_MSG_TYPE+"|"
                            +MSG_VERSION+"|"
                            +storeId+"|"
                            +DEFAULT_TERMINAL_NUMBER+"|"
                            +DEFAULT_TRANSACTION_NUMBER+"|"
                            +DEFAULT_SEQUENCE_NUMBER+"|"
                            +customerNumber+"|"
                            +entryCode+"|"
                            +0+"|"
                            +0+"|"
                            +date+"|"
                            +time);
        return sb.toString();
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

}
