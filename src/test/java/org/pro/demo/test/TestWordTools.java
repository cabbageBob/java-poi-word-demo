package org.pro.demo.test;

import com.deepoove.poi.data.*;
import com.spire.doc.FileFormat;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.pro.demo.AddrModel;
import org.pro.demo.WeekreportBusinessOpportunity;
import org.pro.demo.WeekreportReportionProject;
import org.pro.demo.WordTools;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TestWordTools {

//    private static final String TEMPLATE = "template.docx";
//    private static final String OUT_WORD_PATH ="D:\\demos\\java-poi-demo\\src\\main\\resources\\sss.docx";
//    private static final String OUT_WORD_PATH = "/tmp/out_template.docx";
//    private static final String OUT_PDF_PATH = "/tmp/out_template.pdf";
    private static final String SUB = "sub.docx";
private static final String TEMPLATE = "reportTemp.docx";
    private static final String OUT_WORD_PATH = "D:\\demos\\java-poi-demo\\src\\main\\resources\\out.docx";
    private static final String  REPORT_PROJECT_NOMAL= "reportProjectNomal.docx";
    private static final String  REPORT_PROJECT_CLOSED= "reportProjectShutdown.docx";
    private static final String  OPPORTINUTY_NOMAL= "opportinutyNomal.docx";
    private static final String  OPPORTINUTY_CLOSED= "opportinutyShutdown.docx";


    @Test
    void testWriteWord() throws IOException {
        // create table
        RowRenderData header = Rows.of("Word处理方案", "是否跨平台", "易用性").textColor("FFFFFF").bgColor("ff9800").center()
                .rowHeight(2.5f).create();
        RowRenderData row0 = Rows.create("Poi-tl", "纯Java组件，跨平台", "简单：模板引擎功能，并对POI进行了一些封装");
        RowRenderData row1 = Rows.create("Apache Poi", "纯Java组件，跨平台", "简单，缺少一些功能的封装");
        RowRenderData row2 = Rows.create("Freemarker", "XML操作，跨平台", "复杂，需要理解XML结构");
        RowRenderData row3 = Rows.create("OpenOffice", "需要安装OpenOffice软件", "复杂，需要了解OpenOffice的API");
        RowRenderData row4 = Rows.create("Jacob、winlib", "Windows平台", "复杂，不推荐使用");
        TableRenderData table = Tables.create(header, row0, row1, row2, row3, row4);

        Map<String, Object> datas = new HashMap<>();
        // text
        datas.put("header", "Deeply love what you love.");
        datas.put("name", "Poi-tl");
        datas.put("word", "模板引擎");
        datas.put("time", "2020-08-31");
        datas.put("what",
                "Java Word模板引擎： Minimal Microsoft word(docx) templating with {{template}} in Java. It works by expanding tags in a template using values provided in a JavaMap or JavaObject.");
        datas.put("author", Texts.of("Sayi卅一").color("000000").create());

        // hyperlink
        datas.put("introduce", Texts.of("http://www.deepoove.com").link("http://www.deepoove.com").create());
        // picture
//        datas.put("portrait", Pictures.ofStream((new ClassPathResource("company2.jpeg")).getInputStream()).size(60, 60).create());
        // table
        datas.put("solution_compare", table);
        // numbering
        datas.put("feature",
                Numberings.create("Plug-in grammar, add new grammar by yourself",
                        "Supports word text, local pictures, web pictures, table, list, header, footer...",
                        "Templates, not just templates, but also style templates"));

        //嵌套

        List<AddrModel> subData = new ArrayList<>();
        subData.add(new AddrModel("Hangzhou,China", Numberings.create("Plug-in grammar, add new grammar by yourself",
                "Supports word text, local pictures, web pictures, table, list, header, footer...",
                "Templates, not just templates, but also style templates")));
        subData.add(new AddrModel("Shanghai,China", Numberings.create("Plug-in grammar, add new grammar by yourself",
                "Supports word text, local pictures, web pictures, table, list, header, footer...",
                "Templates, not just templates, but also style templates")));
        datas.put("nested", Includes.ofStream((new ClassPathResource(SUB).getInputStream())).setRenderModel(subData).create());


        WordTools.writeWordByTemplate((new ClassPathResource(TEMPLATE)).getInputStream(), datas, OUT_WORD_PATH);
    }

    @Test
    void testReport() throws IOException {

        Map<String, Object> datas = new HashMap<>();
        // text
        datas.put("reportName", "周报20220501");
        datas.put("time", "2022-05-01 12:00:22");
        datas.put("otherProgress", "其他工作进展");
        datas.put("needCompanySupport", "需要公司支持的事项");
        datas.put("nextWeekPlan", "下周计划");

        List<WeekreportReportionProject> normalProjectList = new ArrayList<>();
        normalProjectList.add(new WeekreportReportionProject("闪电发货类似东航飞机喀什双方就卡","说法",
                1,"1234.13","1234.1214","2022-06-17","fsdfasdf"));
        normalProjectList.add(new WeekreportReportionProject("闪电发货类似东航飞机喀什双方就卡2","说法",
                1,"1234.13","1234.1214","2022-06-17","fsdfasdf"));
        datas.put("reportProjectNomal",Includes.ofStream((new ClassPathResource(REPORT_PROJECT_NOMAL).getInputStream())).setRenderModel(normalProjectList).create());

        List<WeekreportReportionProject> closedProjectList = new ArrayList<>();
        closedProjectList.add(new WeekreportReportionProject("项目名称1","说法",
                1,"1234.13","1234.1214","2022-06-17","fsdfasdf"));
        closedProjectList.add(new WeekreportReportionProject("项目名称2","说法",
                1,"1234.13","1234.1214","2022-06-17","fsdfasdf"));
        datas.put("reportProjectShutdown",Includes.ofStream((new ClassPathResource(REPORT_PROJECT_CLOSED).getInputStream())).setRenderModel(closedProjectList).create());


        List<WeekreportBusinessOpportunity> normalOpportunityList = new ArrayList<>();
        normalOpportunityList.add(new WeekreportBusinessOpportunity("商机线索名称",1,1,
                "34234.45","1345.66667","的示范法发送到发双方都\na.fsdf\nb:dfasf"));
        normalOpportunityList.add(new WeekreportBusinessOpportunity("商机线索名称2",1,1,
                "34234.45","1345.66667","的示范法发送到发双方都\na.fsdf\nb:dfasf"));
        datas.put("opportunityNomal",Includes.ofStream((new ClassPathResource(OPPORTINUTY_NOMAL).getInputStream())).setRenderModel(normalOpportunityList).create());

        List<WeekreportBusinessOpportunity> closedOpportunity = new ArrayList<>();
        closedOpportunity.add(new WeekreportBusinessOpportunity("商机线索名称",1,1,
                "34234.45","1345.66667","的示范法发送到发双方都\na.fsdf\nb:dfasf"));
        datas.put("opportunityShutdown",Includes.ofStream((new ClassPathResource(OPPORTINUTY_CLOSED).getInputStream())).setRenderModel(closedOpportunity).create());
        WordTools.writeWordByTemplate((new ClassPathResource(TEMPLATE)).getInputStream(), datas, OUT_WORD_PATH);
    }


//    @Test
//    void testWord2PDF() throws IOException {
//        WordTools.word2PDF(Files.newInputStream(Paths.get(OUT_WORD_PATH)), OUT_PDF_PATH, FileFormat.PDF);
//    }


    /***
     * crack
     * https://blog.csdn.net/xxw19950701/article/details/115724571
     */
    @SneakyThrows
    @Test
    void crack() {
        //这一步是完整的jar包路径
        ClassPool.getDefault().insertClassPath("D:\\tmp\\aspose-words-21.9-jdk17.jar");
        CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.words.zzVXK");
        CtMethod zzW9h = zzZJJClass.getDeclaredMethod("zzW9h");
        CtMethod zzZ4g = zzZJJClass.getDeclaredMethod("zzZ4g");
        zzW9h.setBody("{return 1;}");
        zzZ4g.setBody("{return 1;}");
//这一步就是将破译完的代码放在桌面上
        zzZJJClass.writeFile("D:\\tmp\\crack\\");
    }

    @SneakyThrows
    @Test
    void crack1() {

        Class<?> aClass = Class.forName("com.aspose.words.zzXyu");
        java.lang.reflect.Field zzYAC = aClass.getDeclaredField("zzZXG");
        zzYAC.setAccessible(true);

        java.lang.reflect.Field modifiersField = zzYAC.getClass().getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(zzYAC, zzYAC.getModifiers() & ~Modifier.FINAL);
        zzYAC.set(null, new byte[]{76, 73, 67, 69, 78, 83, 69, 68});


    }


}
