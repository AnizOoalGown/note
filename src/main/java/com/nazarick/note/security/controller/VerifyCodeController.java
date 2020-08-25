package com.nazarick.note.security.controller;

import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.domain.dto.VerifyCodeDTO;
import com.nazarick.note.security.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verifyCode")
public class VerifyCodeController {

    @Autowired
    VerifyCodeService verifyCodeService;

    @GetMapping
    public RespDTO<VerifyCodeDTO> getVerifyCode() {
        return RespDTO.success(verifyCodeService.getVerifyCode());
    }
}
