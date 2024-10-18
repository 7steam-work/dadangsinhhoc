package site.dadangsinhhoc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.MessageModel;
import site.dadangsinhhoc.services.message.IMessageService;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@CrossOrigin
public class MessageController {
    private final IMessageService messageService;

    @GetMapping("/getAllMessage")
    public ResponseObject getAllMessage() {
        return messageService.getAllMessage();
    }

    @GetMapping("/countAllMessage")
    public ResponseObject countAllMessage() {
        return messageService.countAllMessage();
    }

    @PostMapping("/addNewMessage")
    public ResponseObject addNewMessage(@RequestBody MessageModel model) {
        return messageService.saveMessage(model);
    }

    @PutMapping("/updateMessage/{id}")
    public ResponseObject updateMessage(@PathVariable Long id, @RequestBody MessageModel model) {
        model.setId(id);
        return messageService.updateMessage(id, model);
    }

    @DeleteMapping("/deleteMessage/{id}")
    public ResponseObject deleteMessage(@PathVariable Long id) {
        return messageService.deleteByIdMessage(id);
    }

}
