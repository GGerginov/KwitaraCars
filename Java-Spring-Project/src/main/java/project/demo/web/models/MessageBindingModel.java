package project.demo.web.models;

import project.demo.service.models.UserServiceModel;

public class MessageBindingModel {

    private String messageUser;

    private String messageEmail;

    private String messageNumber;

    private String messageContent;

    private UserServiceModel userServiceModel;

    public MessageBindingModel() {
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageEmail() {
        return messageEmail;
    }

    public void setMessageEmail(String messageEmail) {
        this.messageEmail = messageEmail;
    }

    public String getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public UserServiceModel getUserServiceModel() {
        return userServiceModel;
    }

    public void setUserServiceModel(UserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }
}
