package tw.ocm.rex.springajaxfileuploaddownload.web.reqeust;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class FileDownload implements Serializable {

    private boolean status;
    private int successCount;
    private int errorCount;
    private String filename;
    private byte[] file;

}
