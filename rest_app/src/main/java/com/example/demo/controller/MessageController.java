package com.example.demo.controller;

import com.example.demo.domain.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/controller/message")
public class MessageController {

    private final List<Message> messageList = new ArrayList<>(
            List.of(
                    new Message(1L, "Hello, reactive!"),
                    new Message(2L, "More then one"),
                    new Message(3L, "Third post"),
                    new Message(4L, "Fourth post"),
                    new Message(5L, "Fifth post")
            )
    );

    @GetMapping("get")
    public ResponseEntity<?> getMessages() {
        return ResponseEntity.ok(messageList);
    }

    @PostMapping("add")
    public ResponseEntity<?> addMessages(@RequestBody Message message) {
        messageList.add(message);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateMessages(@PathVariable int id, @RequestBody Message message) {
        messageList.remove(id-1);
        messageList.add(message);

        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteMessages(@PathVariable int id) {
        messageList.remove(id-1);

        return ResponseEntity.ok("Success");
    }

}
