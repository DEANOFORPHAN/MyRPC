package edu.hit.version3.common.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName BLog
 * @Description TODO
 * @Date 2025/4/28 14:24
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Blog implements Serializable {
    private Integer id;
    private Integer useId;
    private String title;
}
