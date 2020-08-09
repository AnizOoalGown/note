package com.nazarick.note.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes == null ? null : attributes.getRequest();
    }
}
