package com.nazarick.note.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtil {
    public static void write(HttpServletResponse response, Object object) {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(object));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
