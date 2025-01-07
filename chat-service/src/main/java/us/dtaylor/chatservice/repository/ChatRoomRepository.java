package us.dtaylor.chatservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.dtaylor.chatservice.model.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {


}
