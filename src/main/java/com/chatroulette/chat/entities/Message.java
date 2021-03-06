package com.chatroulette.chat.entities;

public class Message {

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    // Content = vote
    private MessageType messageType;
    private String content;
    private String sender;
    private String answer;
    private String handle;

    public MessageType getType() {
        return messageType;
    }

    public void setType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) { this.answer = answer; }

    public String getHandle() { return this.handle; }

    public void setHandle(String handle) { this.handle = handle; }

}