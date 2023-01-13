package simbirsoft.eshevtsova.filestests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadFilesTest {

    @Test
    void readTxtFile() throws Exception {
        String txtFile;
        InputStream txtStream = getClass().getClassLoader().getResourceAsStream("Книга.txt");
        txtFile = new String(txtStream.readAllBytes(), StandardCharsets.UTF_8);
        assertThat(txtFile).contains("А что вы хотели от Бабы-яги");
    }

    @Test
    void readPdfFile() throws Exception {
        PDF pdfFile = new PDF(getClass().getClassLoader().getResourceAsStream("pdf-sample.pdf"));
        assertThat(pdfFile.text).contains("always print correctly");
    }

    @Test
    void readXlsFile() throws Exception {
        InputStream xlsStream = getClass().getClassLoader().getResourceAsStream("SampleXLSFile.xls");
        XLS xlsFile = new XLS(xlsStream);
        assertEquals("Clay Rozendal", xlsFile.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue());
    }

    @Test
    void readDocxFile() throws Exception {
        InputStream docStream = getClass().getClassLoader().getResourceAsStream("SampleDOCFile.docx");
        XWPFDocument docDocument = new XWPFDocument(docStream);
        XWPFWordExtractor docExtractor = new XWPFWordExtractor(docDocument);
        assertThat(docExtractor.getText()).contains("This is Heading1 Text");
    }

    @Test
    public void readZipFile() throws Exception {
        assertThat(ZipClass.readZip("test_document.zip", "test_document.txt", "123456")).contains("Аннотация");
    }
}
