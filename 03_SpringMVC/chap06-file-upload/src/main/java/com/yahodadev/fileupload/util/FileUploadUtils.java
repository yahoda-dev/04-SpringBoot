package com.yahodadev.fileupload.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Locale;

@Component
public class FileUploadUtils {

    @Value("${file.upload.windows.path}")
    private String windowsPath;
    @Value("${file.upload.mac.path}")
    private String macPath;

    public String getUploadPath(String subDir) {
        String os = System.getProperty("os.name").toLowerCase();
        String basePath;
        System.out.println("os = " + os);


        basePath = os.contains("win") ? windowsPath : macPath;
        System.out.println("basePath = " + basePath);

        String uploadPath = basePath + "/" + subDir;
        File dir = new File(uploadPath);

        if(!dir.exists()) dir.mkdirs();
        // 원래는 예외 처리, 로그 출력, mkdirs 반환값 확인 추가해야 함.

        return uploadPath; // 조합한 경로 반환
    }

}
