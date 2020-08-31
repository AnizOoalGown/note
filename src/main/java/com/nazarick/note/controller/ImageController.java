package com.nazarick.note.controller;

import com.nazarick.note.aop.annotation.LogAPI;
import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.domain.entity.Image;
import com.nazarick.note.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @LogAPI
    @PostMapping
    RespDTO<Image> create(@RequestParam Integer noteId, @RequestBody byte[] data) {
        return RespDTO.successIfNotNull(imageService.create(noteId, data));
    }

    @LogAPI
    @DeleteMapping
    RespDTO<Boolean> delete(@RequestParam Integer noteId, @RequestParam Integer no) {
        return RespDTO.successIf(imageService.delete(noteId, no));
    }
}
