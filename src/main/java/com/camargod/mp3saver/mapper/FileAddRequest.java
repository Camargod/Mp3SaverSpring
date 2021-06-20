package com.camargod.mp3saver.mapper;

import com.camargod.mp3saver.entity.UserUploader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileAddRequest {
    public String name;
    public Base64 file;
    public UserUploader uploadedBy;
}
