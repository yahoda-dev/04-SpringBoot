package com.yahodadev.fileupload.controller;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class InternalFileUploadController {
    private final ResourceLoader resourceLoader;

    @GetMapping("/result")
    public String result() {
        return "result";
    }

    public InternalFileUploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostMapping("/single-file-upload")
    public String singleFileUpload(
            @RequestParam MultipartFile singleFile,
            @RequestParam String singleFileDescription,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        String filePath = getInternalUploadPath("single");
        String originFileName = singleFile.getOriginalFilename();

        String savedName = createSavedName(originFileName);

        System.out.println("savedName = " + savedName);

        singleFile.transferTo(new File(filePath, savedName).toPath());

        redirectAttributes.addFlashAttribute("message", "단일 파일 업로드 성공 !!");
        redirectAttributes.addFlashAttribute("img", "/static/uploadedFiles/img/single/" + savedName); // 이미지 경로를 전달하여 브라우저에 이미지를 보여주기 위함
        redirectAttributes.addFlashAttribute("singleFileDescription", singleFileDescription);

        return "redirect:/result";
    }

    private String createSavedName(String originFileName) {
        String extension = originFileName.substring(originFileName.lastIndexOf(".")); // 확장자 추출을 위한 코드
        return UUID.randomUUID().toString().replace("-", "") + extension;
    }

    // Upload할 경로를 반환해주는 메서드
    private String getInternalUploadPath(String directoryName) throws IOException {
        String classLocation = "classpath:static/uploadedFiles/img/" + directoryName;
        Resource resource = resourceLoader.getResource(classLocation);

        if(resource.exists())  {
            return resource.getFile().getAbsolutePath();
        }

        File directory = new File("src/main/resources/static/uploadedFiles/img/" + directoryName);
        directory.mkdirs();
        return directory.getAbsolutePath();
    }
}
