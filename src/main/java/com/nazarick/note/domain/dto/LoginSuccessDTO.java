package com.nazarick.note.domain.dto;

import com.nazarick.note.domain.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginSuccessDTO {
    UserVO user;
    String token;
}
