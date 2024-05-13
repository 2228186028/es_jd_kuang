package com.kuang.esjd.utils;

import com.kuang.esjd.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {

    public List<Content> parseJD() {
       String data = txtParse(new String("F:\\workspace\\es-jd\\src\\main\\java\\com\\kuang\\esjd\\utils\\Search.txt"));


        // 解析文档
        Document document = null;

        document = Jsoup.parse(data);

        // 获取 "J_goodsList" 节点
        Element element =document.getElementById("J_goodsList");
        // 获取 "所有子节点"
        Elements elements = element.getElementsByTag("li");

        ArrayList<Content> goodslist = new ArrayList<Content>();

        //
        for(Element el: elements) {
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            goodslist.add(new Content(title, img, price));
        }
        return goodslist;
    }


    public static String txtParse(String filePath){

        BufferedReader br = null;
        StringBuffer sb = null;
        try {

            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"utf-8")); //这里可以控制编码
            sb = new StringBuffer();

            String line = null;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String data = new String(sb);
        return data;
    }
}
