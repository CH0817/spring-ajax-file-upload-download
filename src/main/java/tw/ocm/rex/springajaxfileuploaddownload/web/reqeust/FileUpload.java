package tw.ocm.rex.springajaxfileuploaddownload.web.reqeust;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class FileUpload implements Serializable {

    private String username;
    private MultipartFile file;

}
