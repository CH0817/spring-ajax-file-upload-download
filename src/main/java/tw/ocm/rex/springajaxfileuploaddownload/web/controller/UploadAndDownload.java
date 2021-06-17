package tw.ocm.rex.springajaxfileuploaddownload.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.ocm.rex.springajaxfileuploaddownload.web.reqeust.FileDownload;
import tw.ocm.rex.springajaxfileuploaddownload.web.reqeust.FileUpload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/file")
public class UploadAndDownload {

    @PostMapping(path = {"/uploadAndDownload"})
    public HttpEntity<FileDownload> uploadAndDownload(FileUpload fileUpload) throws IOException {
        showUploadFile(fileUpload);
        return ResponseEntity.ok(getFileDownload(fileUpload.getFile().getOriginalFilename()));
    }

    private void showUploadFile(FileUpload fileUpload) throws IOException {
        log.info("username: {}", fileUpload.getUsername());
        Path upload = Files.createTempFile("upload", ".txt");
        fileUpload.getFile().transferTo(upload);
        List<String> contents = Files.readAllLines(upload);
        contents.forEach(c -> log.info("content: {}", c));
    }

    private FileDownload getFileDownload(String originFilename) throws IOException {
        Path csvFile = Files.createTempFile("test", ".txt");
        Files.write(csvFile, "測試內容".getBytes());
        return FileDownload.builder()
                           .status(true)
                           .successCount(10)
                           .errorCount(0)
                           .filename(originFilename)
                           .file(Files.readAllBytes(csvFile))
                           .build();
    }

}
