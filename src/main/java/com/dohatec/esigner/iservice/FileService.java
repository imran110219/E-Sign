package com.dohatec.esigner.iservice;

import com.dohatec.esigner.model.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    File saveFile(MultipartFile file);
    File getFile(String fileId);
}
