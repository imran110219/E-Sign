package com.dohatec.esigner.payload;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
