package edu.hit.version2.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName Response
 * @Description TODO
 * @Date 2025/4/28 11:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    public static Boolean isSuccess(Response response) {
        return response.getCode() == 200;
    }


    public static Response success(Object data) {
        return Response.builder()
                .code(200)
                .msg("success")
                .data(data)
                .build();
    }

    public static Response fail(String msg) {
        return Response.builder()
                .code(500)
                .msg(msg)
                .build();
    }

}
