package com.example.mirimswshow2;

/**
 * Created by 수현 on 2017-11-05.
 */
public class Memo {

    private String txt,title,key,editTxt;
    private String createDate;
    private long updateDate;

    public String getEditTxt() {
        return editTxt;
    }

    public void setEditTxt(String editTxt) {
        this.editTxt = editTxt;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTxt() {

        return txt;
    }

    public void setTxt(String txt) {
        if(this.txt==null) {
            this.txt = (txt.charAt(0)=='#')?txt:"#"+(txt);
        }
        else {
            this.txt = this.txt+"  #"+txt;
        }
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    /*
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }*/
/*
    public String getTitle() {
        if(txt!=null){
            if(txt.indexOf("\n")>-1){
                return txt.substring(0,txt.indexOf("\n"));
            } else {
                return txt;
            }
        }
        return title;
    }*/
}