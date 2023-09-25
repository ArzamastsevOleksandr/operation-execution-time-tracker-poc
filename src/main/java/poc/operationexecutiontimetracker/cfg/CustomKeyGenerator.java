package poc.operationexecutiontimetracker.cfg;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

class CustomKeyGenerator implements KeyGenerator {

    public Object generate(Object target, Method method, Object... params) {
        return method.getName() + "_"
                + StringUtils.arrayToDelimitedString(params, "_");
    }

}
