package com.example.androidqunyinhui.retrofit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JacksonTranslation {

    @JsonProperty("status")
    public int status;
    @JsonProperty("content")
    public content content;
    private static class content {
        @JsonProperty("from")
        public String from;
        @JsonProperty("to")
        public String to;
        @JsonProperty("vendor")
        public String vendor;
        @JsonProperty("out")
        public String out;
        @JsonProperty("errNo")
        public int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        System.out.println(status);

        System.out.println(content.from);
        System.out.println(content.to);
        System.out.println(content.vendor);
        System.out.println(content.out);
        System.out.println(content.errNo);
    }

}
