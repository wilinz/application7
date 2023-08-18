package com.example.application7;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

public class NetworkNews implements Serializable {
  private String msg;

  private Result result;

  private Integer code;

  public String getMsg() {
    return this.msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Result getResult() {
    return this.result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public Integer getCode() {
    return this.code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public static class Result implements Serializable {
    private Integer curpage;

    private Integer allnum;

    private List<Newslist> newslist;

    public Integer getCurpage() {
      return this.curpage;
    }

    public void setCurpage(Integer curpage) {
      this.curpage = curpage;
    }

    public Integer getAllnum() {
      return this.allnum;
    }

    public void setAllnum(Integer allnum) {
      this.allnum = allnum;
    }

    public List<Newslist> getNewslist() {
      return this.newslist;
    }

    public void setNewslist(List<Newslist> newslist) {
      this.newslist = newslist;
    }

    public static class Newslist implements Serializable {
      private String picUrl;

      private String ctime;

      private String description;

      private String id;

      private String source;

      private String title;

      private String url;

      public String getPicUrl() {
        return this.picUrl;
      }

      public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
      }

      public String getCtime() {
        return this.ctime;
      }

      public void setCtime(String ctime) {
        this.ctime = ctime;
      }

      public String getDescription() {
        return this.description;
      }

      public void setDescription(String description) {
        this.description = description;
      }

      public String getId() {
        return this.id;
      }

      public void setId(String id) {
        this.id = id;
      }

      public String getSource() {
        return this.source;
      }

      public void setSource(String source) {
        this.source = source;
      }

      public String getTitle() {
        return this.title;
      }

      public void setTitle(String title) {
        this.title = title;
      }

      public String getUrl() {
        return this.url;
      }

      public void setUrl(String url) {
        this.url = url;
      }
    }
  }
}
