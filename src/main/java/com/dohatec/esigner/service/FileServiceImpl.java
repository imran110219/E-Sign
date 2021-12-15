package com.dohatec.esigner.service;

import com.dohatec.esigner.exception.FileNotFoundException;
import com.dohatec.esigner.exception.FileStorageException;
import com.dohatec.esigner.iservice.FileService;
import com.dohatec.esigner.model.File;
import com.dohatec.esigner.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public File saveFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            File newFile = new File(fileName, file.getContentType(), file.getBytes());

            return fileRepository.save(newFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public File getFile(String fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}
