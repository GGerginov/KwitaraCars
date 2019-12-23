package project.demo.service;

import project.demo.service.impl.MessageServiceImpl;
import project.demo.service.models.MessageServiceModel;

public interface MessageService {

    void addMessage(MessageServiceModel messageServiceModel);
}
