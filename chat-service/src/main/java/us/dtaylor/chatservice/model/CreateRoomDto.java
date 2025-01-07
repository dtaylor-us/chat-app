package us.dtaylor.chatservice.model;

public class CreateRoomDto {
    private String name;

    public CreateRoomDto() {
    }

    public CreateRoomDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
