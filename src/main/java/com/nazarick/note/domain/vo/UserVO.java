package com.nazarick.note.domain.vo;

import com.nazarick.note.domain.entity.User;
import lombok.Data;

@Data
public class UserVO {
    Integer id;
    String username;

    public UserVO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
