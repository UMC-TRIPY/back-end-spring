package com.example.tripy.global.s3;


import com.example.tripy.global.response.code.status.ErrorStatus;
import com.example.tripy.global.response.exception.GeneralException;
import com.example.tripy.global.s3.dto.S3Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping("api/file")
    public ResponseEntity<S3Result> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        try {
            S3Result result = s3Service.uploadFile(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new GeneralException(ErrorStatus._FILE_UPLOAD_ERROR);
        }
    }
}