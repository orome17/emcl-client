package com.safeway.emclclient.controller;

import com.safeway.emclclient.emcl.EMCLService;

import com.safeway.emclclient.emcl.dto.ErrorDTO;
import com.safeway.emclclient.emcl.dto.UpdateCustomerProfileDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("emcl")
public class EMCLProxyController {
    private static final Log logger = LogFactory.getLog(EMCLProxyController.class);

    @Autowired
    private EMCLService emclService;

    @RequestMapping(value = "/customer-update", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Object>
    customerUpdate(@RequestBody UpdateCustomerProfileDTO customerInformation, HttpServletRequest request) {
        logger.debug("customerUpdate message received!");
        try {
            emclService.customerUpdate(customerInformation);
        } catch(Exception ex) {
            logger.error(ex);
            return new ResponseEntity<>(
                    buildError(ex, request),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(customerInformation);
    }

    private ErrorDTO buildError(Exception ex, HttpServletRequest request) {
        ErrorDTO error = new ErrorDTO();
        error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setPath(request.getRequestURI());
        error.setTimestamp(System.currentTimeMillis());
        return error;
    }
}