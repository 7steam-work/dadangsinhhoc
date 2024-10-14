package site.dadangsinhhoc.services.message;

import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.MessageModel;

public interface IMessageService {
     boolean existById(Long id);

     ResponseObject findMessageById(Long id);

     ResponseObject getAllMessage();

     ResponseObject countAllMessage();

     ResponseObject saveMessage(MessageModel messageModels);

     ResponseObject updateMessage(Long id, MessageModel messageModel);

     ResponseObject deleteByIdMessage(Long id);

}
