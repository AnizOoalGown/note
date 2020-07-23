package com.nazarick.note.controller;

import com.nazarick.note.domain.dto.RespDTO;
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
    RespDTO create(@RequestParam Integer noteId, @RequestBody byte[] data) throws IOException {
        return RespDTO.successIfNotNull(imageService.create(noteId, data));
    }

    @DeleteMapping
    RespDTO delete(@RequestParam Integer noteId, @RequestParam Integer no) {
        return RespDTO.successIf(imageService.delete(noteId, no));
    }
}
