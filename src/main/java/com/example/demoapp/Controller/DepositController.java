package com.example.demoapp.Controller;

import com.example.demoapp.DTO.Request.DepositRequest;
import com.example.demoapp.DTO.Request.RefundRequest;
import com.example.demoapp.DTO.Response.DepositResponse;
import com.example.demoapp.DTO.Response.RefundResponse;
import com.example.demoapp.Model.TokenInit;
import com.example.demoapp.Model.User;
import com.example.demoapp.Service.DepositService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@AllArgsConstructor
public class DepositController {

    @Autowired
    private DepositService depositService;
    @Autowired
    private TokenInit tokenInit;

    @PostMapping("/deposit")
    public ResponseEntity<?> depositAmount(
            @RequestBody DepositRequest depositRequest,
            @RequestHeader("token") String token
    ) {
        DepositResponse depositResponse = new DepositResponse();
        if (!tokenInit.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        User updatedPerson = depositService.updateBalance(depositRequest);
        if (updatedPerson != null) {
            depositResponse.setDepositId(updatedPerson.getDepositId());
            depositResponse.setBalance(updatedPerson.getBalance());
            return ResponseEntity.ok(depositResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/rollback")
    public ResponseEntity<?> refundAmount(@RequestBody RefundRequest refundRequest,
                                          @RequestHeader("token") String token
    ) {
        RefundResponse response = new RefundResponse();
        if (!tokenInit.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        User user = depositService.refundBalance(refundRequest);
        if (user != null) {
            response.setBalance(user.getBalance());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
}
