package core.pojo;

import java.io.Serializable;

public class ResultVo implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    public ResultVo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", code, msg, data.toString());
    }
}
