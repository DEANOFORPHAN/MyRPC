package edu.hit.version3.server;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName ServiceProvider
 * @Description 这个类用于存储所有的 Service实现类， 提供使用
 * 1 暂时全部使用static， 避免每次都new, 同时共享一个map
 * @Date 2025/4/28 16:19
 **/
public class ServiceProvider {

    private static Map<String, Object> name2ServiceMap = new HashMap<String, Object>();

    public static void addService(Object service) {
        // 1 拿到了类对象
        Class<?> clazz = service.getClass();

        // 2 拿到实现的接口list
        Class<?>[] interfaceNames = clazz.getInterfaces();

        // 3 最后依次把这些name对应绑定到这个service
        for (Class<?> interfaceName : interfaceNames) {
            name2ServiceMap.put(interfaceName.getName(), service);
        }
        // name2ServiceMap.put(service.getClass().ge, service);
    }

    public static Object getService(String name) {
        return name2ServiceMap.get(name);
    }

}
