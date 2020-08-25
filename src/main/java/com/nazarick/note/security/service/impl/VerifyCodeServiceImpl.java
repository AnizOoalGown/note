package com.nazarick.note.security.service.impl;

import com.nazarick.note.constants.Constants;
import com.nazarick.note.domain.dto.VerifyCodeDTO;
import com.nazarick.note.exception.CustomException;
import com.nazarick.note.security.service.VerifyCodeService;
import com.nazarick.note.util.IdUtil;
import com.nazarick.note.util.RedisUtil;
import com.nazarick.note.util.StringUtil;
import com.nazarick.note.util.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public VerifyCodeDTO getVerifyCode() {
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        String uuid = IdUtil.genUUID();
        String key = Constants.VERIFY_NAMESPACE + ":" + uuid;
        redisUtil.set(key, verifyCode, 2, TimeUnit.MINUTES);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            VerifyCodeUtil.outputImage(111, 36, stream, verifyCode);
        } catch (IOException e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
        String image = "data:image/gif;base64," + Base64Utils.encodeToString(stream.toByteArray());
        return new VerifyCodeDTO(uuid, image);
    }

    @Override
    public Boolean validateVerifyCode(String uuid, String code) {
        if (StringUtil.isEmpty(uuid)) return false;
        String value = redisUtil.get(Constants.VERIFY_NAMESPACE + ":" + uuid, String.class);
        return StringUtil.isNotEmpty(value) && value.equalsIgnoreCase(code);
    }
}
