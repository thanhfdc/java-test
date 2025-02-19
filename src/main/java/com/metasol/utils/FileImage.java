package com.metasol.utils;

import com.metasol.constant.ErrorCode;
import com.metasol.constant.MessageCode;
import com.metasol.exception.EOException;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Component
public class FileImage {

    public String storeFile2(MultipartFile file) throws IOException {
        if (!isImageFile2(file) || file.getOriginalFilename() == null) {
            throw new IOException("Không đúng định dạng");
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        // thêm UUID vào trực tiếp tên file để đảm bảo tên file là duy nhaats
        String uniqueFilename = UUID.randomUUID().toString() + "_" + fileName;
        // Đường dẫn đến thư mục m bạn muốn lưu
        Path uploatDir = Paths.get("uploads");
        // kiểm tra và tạo thư mục nếu nó không tồn tại
        if (!Files.exists(uploatDir)) {
            Files.createDirectories(uploatDir);
        }
        // đường dẫn đầy đủ đến file
        Path destination = Paths.get(uploatDir.toString(), uniqueFilename);
        // sao chép file vào thư mục -- StandardCopyOption.REPLACE_EXISTING: nếu có th thay thé
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }

    public boolean isImageFile2(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }


    public UrlResource viewImage(String imageName) {
        try {
            java.nio.file.Path imagePath = Paths.get("uploads/" + imageName);
            UrlResource resource = new UrlResource(imagePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                return new UrlResource(Paths.get("uploads/notfound.jpg").toUri());

                //return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw new EOException(ErrorCode.NO_SOURCE_IMAGE, MessageCode.NOT_NULL);
        }
    }

}
