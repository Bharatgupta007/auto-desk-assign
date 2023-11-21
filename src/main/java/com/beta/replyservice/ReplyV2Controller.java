package com.beta.replyservice;

import com.beta.replyservice.DTO.Response;
import com.beta.replyservice.service.ReplyV2Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v2")
public class ReplyV2Controller {

    private final ReplyV2Service replyV2Service;

    public ReplyV2Controller(ReplyV2Service replyV2Service) {
        this.replyV2Service = replyV2Service;
    }

    @GetMapping("/reply")
    public ReplyMessage replying() {
        return new ReplyMessage("Message is empty");
    }

    @GetMapping("/reply/{message}")
    public ResponseEntity<Response> replying(@PathVariable String message) {
        String[] parts = message.split("-");

        if (parts.length == 2 && parts[0].length() == 2) {
            String body = replyV2Service.manipulate(parts[0], parts[1]);

            if ("-1".equals(body)) {
                return ResponseEntity.badRequest().body(new Response("Invalid Input",null));
            }

            return ResponseEntity.ok(new Response(null,body));
        }

        return ResponseEntity.badRequest().body(new Response("Invalid Input",null));
    }

//    @GetMapping("/reply/{message}")
//    public String replying(@PathVariable String message) {
//        String msg = message.substring(3);
//        System.out.println(msg);
//        if (message.startsWith("11")) {
//            return reverseString(msg);
//        } else if (message.startsWith("12")) {
//            return encode(reverseString(msg));
//        } else if (message.startsWith("22")) {
//            String res = encode(msg);
//            return encode(res);
//        } else {
//            return "Wrong Input";
//        }
//    }
    }

