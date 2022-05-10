package org.pro.demo;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Includes;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.springframework.util.Assert;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Allen
 * @ClassName:
 * @Description: Word工具类
 * @date 2022/3/11 13:46
 * @see org.pro.demo.test.TestWordTools
 */

public class WordTools {

    private WordTools() {
        throw new IllegalStateException("Utility class");
    }

    /***
     * 根据模板生成word,注: 模板中不同类型的数据,替换符不同 例如:普通文字{{what}},序列{{*feature}},图片{{@portrait}},表格{{#solution_compare}}
     * @param templateFile
     * @param data
     * @param outPath
     * @throws IOException
     * @see com.deepoove.poi.data.Texts,
     * @see com.deepoove.poi.data.Pictures
     * @see com.deepoove.poi.data.TableRenderData,com.deepoove.poi.data.RowRenderData
     */
    public static void writeWordByTemplate(InputStream templateFile, Map<String, Object> data, String outPath) throws IOException {
        Assert.notEmpty(data, "参数不能为空");
        XWPFTemplate.compile(templateFile).render(data)
                .writeToFile(outPath);
    }

    public static void main(String[] args) {
        List<AddrModel> subData = new ArrayList<>();
        subData.add(new AddrModel("Hangzhou,China"));
        subData.add(new AddrModel("Shanghai,China"));
        Map<String,Object> map = new HashMap<>();
        map.put("name","测试标题");
        map.put("nested", Includes.ofLocal("sub.docx").setRenderModel(subData).create());
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("template.docx");
        ZipSecureFile.setMinInflateRatio(-1.0d);
        try {
            writeWordByTemplate(resourceAsStream,map,"D:\\demos\\java-poi-demo\\src\\main\\resources\\sss.docx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class AddrModel {
        private String addr;
        public AddrModel(String addr) {
            this.addr = addr;
        }
        // Getter/Setter
    }

    /****
     * word转pdf
     * @param wordInputStream
     * @param outPath
     * @param fileFormat FileFormat.Docx_2013
     * @see com.spire.doc.FileFormat,
     */
    public static void word2PDF(InputStream wordInputStream, String outPath, FileFormat fileFormat) {
        //读取doc文件
        Document dc = new Document(wordInputStream);
        //转成docx
        dc.saveToFile(outPath, fileFormat);
        dc.close();
    }


}
