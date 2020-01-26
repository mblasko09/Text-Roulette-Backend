package com.chatroulette.chat.service;

import com.chatroulette.chat.entities.PlayerData;
import org.springframework.integration.store.SimpleMessageStore;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageStoreService {

    private SimpleMessageStore messageStore;

    public MessageStoreService() {
        messageStore = new SimpleMessageStore();
    }

    public void addVoteToPlayer(String roomId, com.chatroulette.chat.entities.Message mes) {
        Message message = new GenericMessage<>(mes.getContent());
        Message player = new GenericMessage<>(mes.getSender());
        String playerId = roomId + mes.getSender();
        if (mes.getContent().equalsIgnoreCase(mes.getContent())) { //TODO Need to change to work with mes.getAnswer()
            messageStore.addMessageToGroup(playerId, message);
        }
    }

    public void setGroup(String roomId, String playerId) {
        Message player = new GenericMessage<>(playerId);
        messageStore.addMessageToGroup(roomId, player);
    }

    public List<PlayerData> getPlayerScores(String roomId) {
        List<PlayerData> playerData = new ArrayList<>();
        for (Message message : messageStore.getMessageGroup(roomId).getMessages()) {
            String curPlayer = message.getPayload().toString();
            int curPlayerScore = messageStore.getMessageGroup(roomId+curPlayer).size();
            System.out.println(curPlayer + "'s score: " + curPlayerScore);
            PlayerData playerData1 = new PlayerData(curPlayer, curPlayerScore);
            playerData.add(playerData1);
        }
        return playerData;
    }
}
