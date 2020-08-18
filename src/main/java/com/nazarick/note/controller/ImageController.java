package com.nazarick.note.controller;

import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.domain.entity.Image;
import com.nazarick.note.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    RespDTO<Image> create(@RequestParam Integer noteId, @RequestBody byte[] data) {
        return RespDTO.successIfNotNull(imageService.create(noteId, data));
    }

    @DeleteMapping
    RespDTO<Boolean> delete(@RequestParam Integer noteId, @RequestParam Integer no) {
        return RespDTO.successIf(imageService.delete(noteId, no));
    }
}
