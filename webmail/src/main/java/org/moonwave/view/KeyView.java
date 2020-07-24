package org.moonwave.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.moonwave.util.Md5Util;
import org.moonwave.util.SHAUtil;

@ManagedBean
@SessionScoped
public class KeyView {

    private String key1;
    private String result1;
    private String key2;
    private String result2;
    private String key3;
    private String result3;

    public String getKey1() {
        return key1;
    }
    public void setKey1(String key) {
        this.key1 = key;
        // calculate generated key result
        result1 = Md5Util.encryptPassword(key);
    }
    public String getResult1() {
        return result1;
    }
    public void setResult1(String result) {
        this.result1 = result;
    }
    public String getKey2() {
        return key2;
    }
    public void setKey2(String key2) {
        this.key2 = key2;
        result2 = SHAUtil.sha1(key2);
    }
    public String getResult2() {
        return result2;
    }
    public void setResult2(String result2) {
        this.result2 = result2;
    }
    public String getKey3() {
        return key3;
    }
    public void setKey3(String key3) {
        this.key3 = key3;
        result3 = SHAUtil.sha2(key3);
    }
    public String getResult3() {
        return result3;
    }
    public void setResult3(String result3) {
        this.result3 = result3;
    }
  
}