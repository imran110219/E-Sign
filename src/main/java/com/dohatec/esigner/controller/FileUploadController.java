package com.dohatec.esigner.controller;

import com.dohatec.esigner.iservice.FileService;
import com.dohatec.esigner.model.File;
import com.dohatec.esigner.payload.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class FileUploadController {

    private final FileService fileService;

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        File fileName = fileService.saveFile(file);

//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName.getFileName())
//                .toUriString();

        return new Response(fileName.getFileName(), null,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List< Response > uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
}