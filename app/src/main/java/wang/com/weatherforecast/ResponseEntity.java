package wang.com.weatherforecast;

import java.util.ArrayList;

/**
 * Created by 1 on 2018/5/21.
 */

public class ResponseEntity {
    String code;
    String message;
    String cnt;
    ArrayList<Object> list;

    public ResponseEntity(String code, String message, String cnt, ArrayList<Object> list) {
        this.code = code;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public ArrayList<Object> getList() {
        return list;
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
    }
}
