package site.dadangsinhhoc.services.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.MessageModel;
import site.dadangsinhhoc.repositories.MessageRepository;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MessageService implements IMessageService {
    private final MessageRepository messageRepository;

    @Override
    public boolean existById(Long id) {
        return messageRepository.existsById(id);
    }

    @Override
    public ResponseObject findMessageById(Long id) {
        return messageRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public ResponseObject getAllMessage() {
        List<MessageModel> messageModels = messageRepository.findAll();
        return ResponseObject.success(messageModels);
    }

    @Override
    public ResponseObject countAllMessage() {
        long quantity = messageRepository.count();
        return ResponseObject.success(quantity);
    }

    @Override
    public ResponseObject saveMessage(MessageModel messageModels) {
        if (messageRepository.existsById(messageModels.getId())) {
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        messageModels.setId(null);
        MessageModel savedMessage = messageRepository.save(messageModels);
        return ResponseObject.success(savedMessage);
    }

    @Override
    public ResponseObject updateMessage(Long id, MessageModel messageModel) {
        if (!messageRepository.existsById(messageModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Message with id: " + messageModel.getId());
        }
        return messageRepository.findById(id)
                .map(existingNganh -> {
                    existingNganh.setName(messageModel.getName());
                    existingNganh.setEmail(messageModel.getEmail());
                    existingNganh.setPhone(messageModel.getPhone());
                    existingNganh.setRPH(messageModel.getRPH());
                    existingNganh.setContent(messageModel.getContent());
                    existingNganh.setStatus(messageModel.getStatus());
                    existingNganh.setNote(messageModel.getNote());
                    return ResponseObject.success(messageRepository.save(existingNganh));
                })
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Message with id: " + id));
    }

    @Override
    public ResponseObject deleteByIdMessage(Long id) {
        if (!messageRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            messageRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }

}
