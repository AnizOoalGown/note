package com.nazarick.note.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APILog {
    Timestamp createTime;
    String ip;
    Integer userId;
    String method;
    String uri;
    String args;
    Integer code;
    String msg;
    String data;
}
