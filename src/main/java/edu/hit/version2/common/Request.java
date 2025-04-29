package edu.hit.version2.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName Request
 * @Description TODO
 * @Date 2025/4/28 11:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request implements Serializable {
    private String interfaceName;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] parameters;

}
