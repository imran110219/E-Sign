package com.dohatec.esigner.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Basic
    @Column(name = "file_name")
    private String fileName;

    @Basic
    @Column(name = "file_type")
    private String fileType;

    @Basic
    @Column(name = "data")
    private byte[] data;

    public File(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
}
