package com.chatroulette.chat;

import com.chatroulette.chat.entities.Message;
import com.chatroulette.chat.entities.PlayerData;
import com.chatroulette.chat.service.VotingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Controller
public class ChatRoomController {

    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private VotingService votingService;

    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId, @Payload Message chatMessage) {
        logger.info(roomId + "Vote added for " + chatMessage.getContent() + " by " + chatMessage.getSender());
        votingService.addVoteToPlayer(roomId, chatMessage);
        chatMessage.setContent("Vote received from " + chatMessage.getSender());
        messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), chatMessage);
    }

    @MessageMapping("/chat/{roomId}/addVote")
    public void addVote(@DestinationVariable String roomId, @Payload Message chatMessage) {
        logger.info(roomId + "Vote added for " + chatMessage.getContent() + " by " + chatMessage.getSender());
        chatMessage.setContent("Vote received from " + chatMessage.getSender());
        messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), chatMessage);
    }

    @MessageMapping("/chat/{roomId}/addUser")
    public void addUser(@DestinationVariable String roomId, @Payload Message chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {
        String currentRoomId = (String) headerAccessor.getSessionAttributes().put("room_id", roomId);
        if (currentRoomId != null) {
            Message leaveMessage = new Message();
            leaveMessage.setType(Message.MessageType.LEAVE);
            leaveMessage.setSender(chatMessage.getSender());
            messagingTemplate.convertAndSend(format("/chat-room/%s", currentRoomId), leaveMessage);
        }
        votingService.setGroup(roomId, chatMessage.getSender());
        votingService.getPlayerScores(roomId);
        headerAccessor.getSessionAttributes().put("name", chatMessage.getSender());
        messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), chatMessage);
    }

    @RequestMapping(value = "/chat/getAllPlayerScores/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerData>> getPlayerScores(@RequestParam String roomId) {
        List<PlayerData> playerScores = new ArrayList<>();
        try {
            playerScores = votingService.getPlayerScores(roomId);
            return new ResponseEntity<>(playerScores, HttpStatus.OK);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return  new ResponseEntity<>(playerScores, HttpStatus.BAD_REQUEST);
        }
    }
}
