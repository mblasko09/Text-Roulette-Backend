package com.chatroulette.chat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.store.MessageGroup;
import org.springframework.integration.store.SimpleMessageStore;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VotingService {

    private SimpleMessageStore messageStore;

    public VotingService() {
        messageStore = new SimpleMessageStore();
    }

    public void addVoteToPlayer(String roomId, com.chatroulette.chat.Message mes) {
        Message message = new GenericMessage<>(mes.getContent());
        System.out.println("Message: " + mes.getContent());
        String playerId = roomId + mes.getSender();
        if (mes.getContent().equalsIgnoreCase(mes.getContent())) { //TODO Need to change to work with mes.getAnswer()
            messageStore.addMessageToGroup(playerId, message);
            System.out.println(playerId + " " + message.getPayload());
            System.out.println("votes: " + messageStore.getGroupMetadata(playerId).size());
        }
    }
}
