package com.yahodadev.fileupload.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class BasicFileUploadController {

    @GetMapping("/basic-file-upload")
    public String basicFileUplaodPage() {
        return "redirect:/";
    }

    @PostMapping("/basic-file-upload")
    public String uploadFile(
            @RequestParam(value = "file") MultipartFile file,  // Spring에서 업로드 파일을 다루는 인터페이스(파일명, 크기, 타입, 내용을 확인할 수 있음)
            Model model
    ) throws IOException {
        /*
        Path uploadDirectory1 = Paths.get("uploads"); // src의 루트 경로
        Path uploadDirectory2 = Paths.get("uploads").toAbsolutePath(); // 절대 경로
        Path uploadDirectory3 = Paths.get("uploads").toAbsolutePath().normalize(); // 정리된 경로
        */
        Path uploadDirectory = Paths.get("uploads");

        if (!Files.exists(uploadDirectory)) {
            Files.createDirectories(uploadDirectory);
        }

        String originalFile = file.getOriginalFilename();
        System.out.println("originalFile = " + originalFile);

        Path savePath = uploadDirectory.resolve(originalFile);
        System.out.println("savePath = " + savePath);

        file.transferTo(savePath);
        model.addAttribute("message", "파일 업로드 성공!");
        model.addAttribute("originalFileName", originalFile);
        model.addAttribute("savedPath", savePath);

        return "main";
    }

}
