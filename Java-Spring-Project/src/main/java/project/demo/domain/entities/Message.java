package project.demo.domain.entities;

import javax.persistence.*;

@Table
@Entity
public class Message extends BaseEntity{

    private String messageUser;

    private String messageEmail;

    private String messageNumber;

    private String messageContent;

    private User user;

    public Message() {
    }

    @Column(nullable = false)
    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    @Column(nullable = false)
    public String getMessageEmail() {
        return messageEmail;
    }

    public void setMessageEmail(String messageEmail) {
        this.messageEmail = messageEmail;
    }

    @Column(nullable = false)
    public String getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    @Column(nullable = false)
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
