package com.nazarick.note.security.service.impl;

import com.nazarick.note.mapper.InviteCodeMapper;
import com.nazarick.note.security.service.InviteCodeService;
import com.nazarick.note.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteCodeServiceImpl implements InviteCodeService {

    @Autowired
    InviteCodeMapper inviteCodeMapper;

    @Override
    public boolean delete(String code) {
        if (StringUtil.isEmpty(code)) return false;
        return inviteCodeMapper.delete(code) == 1;
    }
}
