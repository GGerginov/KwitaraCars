package project.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.domain.entities.Message;
import project.demo.repositories.MessageRepository;
import project.demo.service.MessageService;
import project.demo.service.models.MessageServiceModel;

@Service
public class MessageServiceImpl implements MessageService {

    private ModelMapper modelMapper;

    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(ModelMapper modelMapper, MessageRepository messageRepository) {
        this.modelMapper = modelMapper;
        this.messageRepository = messageRepository;
    }

    @Override
    public void addMessage(MessageServiceModel messageServiceModel) {

        this.messageRepository.saveAndFlush(this.modelMapper.map(messageServiceModel, Message.class));
    }
}
