package com.lhu.payloadsecure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payload")
public class Controller {

    @Autowired
    private PayLoadAuthService payLoadAuthService;

    @GetMapping("/validate")
    public void payloadValidateTest(
            @RequestHeader(name = "payLoadEncodedData") final String payLoadEncodedData,
            @RequestBody PayLoadDto payload) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String payloadString = objectMapper.writeValueAsString(payload);
        payloadString = payloadString.replaceAll(" ", "");
        payLoadAuthService.authPayload(payLoadEncodedData,payloadString);
    }
}
