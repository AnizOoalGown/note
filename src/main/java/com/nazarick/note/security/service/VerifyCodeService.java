package com.nazarick.note.security.service;

import com.nazarick.note.domain.dto.VerifyCodeDTO;

public interface VerifyCodeService {
    VerifyCodeDTO getVerifyCode();
    Boolean validateVerifyCode(String uuid, String code);
}
