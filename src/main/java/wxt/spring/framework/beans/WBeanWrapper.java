package wxt.spring.framework.beans;

import lombok.Getter;

/**
 * @author wangxt
 * @date 2024/11/24 18:05
 * @desc
 */
public class WBeanWrapper {
    @Getter
    private Object wrappedInstance;
    private Class<?> wrappedClass;

    public WBeanWrapper(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    public Class<?> getWrappedClass() {
        return this.wrappedInstance.getClass();
    }
}
