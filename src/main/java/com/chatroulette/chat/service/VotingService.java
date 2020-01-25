package com.chatroulette.chat.service;


import org.springframework.stereotype.Service;

@Service
public class VotingService {
    public void addVoteToPlayer(String roomId, String sender, String vote, String answer) {
        if (vote.equalsIgnoreCase(answer)) {

        }
    }
}
