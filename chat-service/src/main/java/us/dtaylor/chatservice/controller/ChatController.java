package us.dtaylor.chatservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import us.dtaylor.chatservice.model.ChatMessage;
import us.dtaylor.chatservice.model.ChatRoom;
import us.dtaylor.chatservice.model.CreateRoomDto;
import us.dtaylor.chatservice.service.ChatRoomService;
import us.dtaylor.chatservice.service.ChatService;
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/chat.sendMessage/{roomId}")
    public void sendMessage(@DestinationVariable String roomId, ChatMessage chatMessage) {
        chatMessage.setChatRoomId(roomId);
        chatService.saveMessage(chatMessage);
        messagingTemplate.convertAndSend("/topic/" + roomId, chatMessage);
    }

    @MessageMapping("/chat.addUser") // Handles user joins
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage) {
        chatMessage.setType(ChatMessage.MessageType.JOIN);
        return chatMessage;
    }

    @PostMapping("/rooms")
    public ChatRoom createRoom(@RequestBody CreateRoomDto createRoomDto) {
        String name = createRoomDto.getName();
        return chatRoomService.createChatRoom(name.toLowerCase(), name);
    }

    @GetMapping("/rooms")
    public Iterable<ChatRoom> getAllRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @GetMapping("messages/{roomId}")
    public Iterable<ChatMessage> getMessages(@PathVariable String roomId) {
        return chatService.getMessagesByRoomId(roomId);
    }

}
