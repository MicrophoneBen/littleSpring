package com.spring.poi;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.TextRenderData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:独角兽zhuojl
 * @date :2017/11/30
 * @saysth:
 */
public class PoiTlTest {
    public static void main(String[] args) {
        testPicture();
    }
    public static void testPicture(){
        final String tt="asdfasdfa";
        Map<String, Object> datas = new HashMap<>(16);
        datas.put("name","刘一刀");
        datas.put("thinkTable",new TableRenderData(new ArrayList<RenderData>() {
            {
                add(new TextRenderData("FFFFFF",tt));
                add(new TextRenderData("FFFFFF", "活动时间或次数"));
            }
        }, new ArrayList<Object>() {
            {
                add("在成都唱歌在成都唱歌在成都唱歌在成都唱歌在成都唱歌;2");
                add("在上海唱歌在上海唱歌在上海唱歌在上海唱歌在上海唱歌在上海唱歌在上海唱歌;3");
            }
        }, "no datas", 8600));

        List<Object> list = new ArrayList<>(16);
        list.add("在2;23");
        TableRenderData tableRenderData = new TableRenderData(null,list,"",18000);
//        tableRenderData.setWidth(8000);
        datas.put("table2",tableRenderData);

        List<Object> list2 = new ArrayList<>();
        list2.addAll(list);
        list2.add("在上海唱歌在上海唱歌在上海唱歌在上海唱歌在上海唱歌在上海唱歌在上海唱歌;3;asdfadf");
        datas.put("table3",new TableRenderData(list2,""));


        XWPFTemplate template = XWPFTemplate.compile("C:\\Users\\wubo\\Desktop\\aaaa.docx")
                .render(datas);
        FileOutputStream out;
        try {
            out = new FileOutputStream("C:\\Users\\wubo\\Desktop\\out_picture1.docx");
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
