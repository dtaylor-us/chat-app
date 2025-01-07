package us.dtaylor.chatservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dtaylor.chatservice.model.ChatRoom;
import us.dtaylor.chatservice.repository.ChatRoomRepository;

import java.util.Optional;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom(String id, String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setId(id);
        chatRoom.setName(name);
        return chatRoomRepository.save(chatRoom);
    }

    public Optional<ChatRoom> getChatRoom(String id) {
        return chatRoomRepository.findById(id);
    }

    public Iterable<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }
}
