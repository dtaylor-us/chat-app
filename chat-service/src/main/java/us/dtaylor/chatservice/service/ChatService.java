package us.dtaylor.chatservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dtaylor.chatservice.model.ChatMessage;
import us.dtaylor.chatservice.repository.ChatMessageRepository;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage saveMessage(ChatMessage message) {
        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getMessages(String chatRoomId) {
        return chatMessageRepository.findByChatRoomId(chatRoomId);
    }

    public Iterable<ChatMessage> getMessagesByRoomId(String roomId) {
        return chatMessageRepository.findByChatRoomId(roomId);
    }
}
