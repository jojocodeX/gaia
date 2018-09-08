/**
 * bravo.org
 * Copyright (c) 2018-2019 All Rights Reserved
 */
package org.bravo.gaia.utils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.bravo.gaia.commons.commoninterface.IErrorCode;
import org.bravo.gaia.commons.exception.PlatformException;

/**
 * 断言工具类
 *
 * @author alex.lj
 * @version @Id: AssertUtil.java, v 0.1 2018年09月08日 20:45 alex.lj Exp $
 */
public abstract class AssertUtil {

    public static void state(boolean expression, IErrorCode errorCodeEnum, String errorMsg) {
        checkArgument(errorCodeEnum);
        checkArgument(errorMsg);

        if (!expression) {
            throw new PlatformException(errorMsg, errorCodeEnum.getCode());
        }
    }

    public static void state(boolean expression, IErrorCode errorCodeEnum) {
        checkArgument(errorCodeEnum);

        if (!expression) {
            throw new PlatformException(errorCodeEnum.getCode());
        }
    }

    public static void state(boolean expression, String errorMsg) {
        checkArgument(errorMsg);

        if (!expression) {
            throw new PlatformException(errorMsg);
        }
    }

    public static void state(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new PlatformException(nullSafeGet(messageSupplier));
        }
    }

    public static void isTrue(boolean expression, IErrorCode errorCodeEnum, String errorMsg) {
        checkArgument(errorCodeEnum);
        checkArgument(errorMsg);

        if (!expression) {
            throw new PlatformException(errorMsg, errorCodeEnum.getCode());
        }
    }

    public static void isTrue(boolean expression, IErrorCode errorCodeEnum) {
        checkArgument(errorCodeEnum);

        if (!expression) {
            throw new PlatformException(errorCodeEnum.getCode());
        }
    }

    public static void isTrue(boolean expression, String errorMsg) {
        checkArgument(errorMsg);

        if (!expression) {
            throw new PlatformException(errorMsg);
        }
    }

    public static void isTrue(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new PlatformException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNull(Object object, IErrorCode errorCodeEnum, String errorMsg) {
        checkArgument(errorCodeEnum);
        checkArgument(errorMsg);

        if (object != null) {
            throw new PlatformException(errorMsg, errorCodeEnum.getCode());
        }
    }

    public static void isNull(Object object, IErrorCode errorCodeEnum) {
        checkArgument(errorCodeEnum);

        if (object != null) {
            throw new PlatformException(errorCodeEnum.getCode());
        }
    }

    public static void isNull(Object object, String errorMsg) {
        checkArgument(errorMsg);

        if (object != null) {
            throw new PlatformException(errorMsg);
        }
    }

    public static void isNull(Object object, Supplier<String> messageSupplier) {
        if (object != null) {
            throw new PlatformException(nullSafeGet(messageSupplier));
        }
    }

    public static void notNull(Object object, IErrorCode errorCodeEnum, String errorMsg) {
        checkArgument(errorCodeEnum);
        checkArgument(errorMsg);

        if (object == null) {
            throw new PlatformException(errorMsg, errorCodeEnum.getCode());
        }
    }

    public static void notNull(Object object, IErrorCode errorCodeEnum) {
        checkArgument(errorCodeEnum);

        if (object == null) {
            throw new PlatformException(errorCodeEnum.getCode());
        }
    }

    public static void notNull(Object object, String errorMsg) {
        checkArgument(errorMsg);

        if (object == null) {
            throw new PlatformException(errorMsg);
        }
    }

    public static void notNull(Object object, Supplier<String> messageSupplier) {
        if (object == null) {
            throw new PlatformException(nullSafeGet(messageSupplier));
        }
    }

    public static void isNotBlank(String text, IErrorCode errorCodeEnum, String errorMsg) {
        checkArgument(errorCodeEnum);
        checkArgument(errorMsg);

        if (StringUtils.isBlank(text)) {
            throw new PlatformException(errorMsg, errorCodeEnum.getCode());
        }
    }

    public static void isNotBlank(String text, IErrorCode errorCodeEnum) {
        checkArgument(errorCodeEnum);

        if (StringUtils.isBlank(text)) {
            throw new PlatformException(errorCodeEnum.getCode());
        }
    }

    public static void isNotBlank(String text, String errorMsg) {
        checkArgument(errorMsg);

        if (StringUtils.isBlank(text)) {
            throw new PlatformException(errorMsg);
        }
    }

    public static void isNotBlank(String text, Supplier<String> messageSupplier) {
        if (StringUtils.isBlank(text)) {
            throw new PlatformException(nullSafeGet(messageSupplier));
        }
    }

    public static void isBlank(String text, IErrorCode errorCodeEnum, String errorMsg) {
        checkArgument(errorCodeEnum);
        checkArgument(errorMsg);

        if (StringUtils.isNoneBlank(text)) {
            throw new PlatformException(errorMsg, errorCodeEnum.getCode());
        }
    }

    public static void isBlank(String text, IErrorCode errorCodeEnum) {
        checkArgument(errorCodeEnum);

        if (StringUtils.isNoneBlank(text)) {
            throw new PlatformException(errorCodeEnum.getCode());
        }
    }

    public static void isBlank(String text, String errorMsg) {
        checkArgument(errorMsg);

        if (StringUtils.isNoneBlank(text)) {
            throw new PlatformException(errorMsg);
        }
    }

    public static void isBlank(String text, Supplier<String> messageSupplier) {
        if (StringUtils.isNoneBlank(text)) {
            throw new PlatformException(nullSafeGet(messageSupplier));
        }
    }

    public static void notEmpty(Collection<?> collection, IErrorCode errorCodeEnum,
                                String errorMsg) {
        checkArgument(errorCodeEnum);
        checkArgument(errorMsg);

        if (CollectionUtils.isEmpty(collection)) {
            throw new PlatformException(errorMsg, errorCodeEnum.getCode());
        }
    }

    public static void notEmpty(Collection<?> collection, IErrorCode errorCodeEnum) {
        checkArgument(errorCodeEnum);

        if (CollectionUtils.isEmpty(collection)) {
            throw new PlatformException(errorCodeEnum.getCode());
        }
    }

    public static void notEmpty(Collection<?> collection, String errorMsg) {
        checkArgument(errorMsg);

        if (CollectionUtils.isEmpty(collection)) {
            throw new PlatformException(errorMsg);
        }
    }

    public static void notEmpty(Collection<?> collection, Supplier<String> messageSupplier) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new PlatformException(nullSafeGet(messageSupplier));
        }
    }

    public static void isEmpty(Collection<?> collection, IErrorCode errorCodeEnum,
                               String errorMsg) {
        checkArgument(errorCodeEnum);
        checkArgument(errorMsg);

        if (!CollectionUtils.isEmpty(collection)) {
            throw new PlatformException(errorMsg, errorCodeEnum.getCode());
        }
    }

    public static void isEmpty(Collection<?> collection, IErrorCode errorCodeEnum) {
        checkArgument(errorCodeEnum);

        if (!CollectionUtils.isEmpty(collection)) {
            throw new PlatformException(errorCodeEnum.getCode());
        }
    }

    public static void isEmpty(Collection<?> collection, String errorMsg) {
        checkArgument(errorMsg);

        if (!CollectionUtils.isEmpty(collection)) {
            throw new PlatformException(errorMsg);
        }
    }

    public static void isEmpty(Collection<?> collection, Supplier<String> messageSupplier) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new PlatformException(nullSafeGet(messageSupplier));
        }
    }

    public static void noNullElements(Object[] array, IErrorCode errorCodeEnum) {
        checkArgument(errorCodeEnum);

        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new PlatformException(errorCodeEnum.getCode());
                }
            }
        }
    }

    public static void noNullElements(Object[] array, IErrorCode errorCodeEnum, String errorMsg) {
        checkArgument(errorMsg);
        checkArgument(errorMsg);

        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new PlatformException(errorMsg, errorCodeEnum.getCode());
                }
            }
        }
    }

    public static void noNullElements(Object[] array, String errorMsg) {
        checkArgument(errorMsg);

        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new PlatformException(errorMsg);
                }
            }
        }
    }

    public static void noNullElements(Object[] array, Supplier<String> messageSupplier) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new PlatformException(nullSafeGet(messageSupplier));
                }
            }
        }
    }

    public static void notEmpty(Map<?, ?> map, IErrorCode errorCodeEnum, String errorMsg) {
        checkArgument(errorCodeEnum);
        checkArgument(errorMsg);

        if (MapUtils.isEmpty(map)) {
            throw new PlatformException(errorMsg, errorCodeEnum.getCode());
        }
    }

    public static void notEmpty(Map<?, ?> map, IErrorCode errorCodeEnum) {
        checkArgument(errorCodeEnum);

        if (MapUtils.isEmpty(map)) {
            throw new PlatformException(errorCodeEnum.getCode());
        }
    }

    public static void notEmpty(Map<?, ?> map, String errorMsg) {
        checkArgument(errorMsg);

        if (MapUtils.isEmpty(map)) {
            throw new PlatformException(errorMsg);
        }
    }

    public static void notEmpty(Map<?, ?> map, Supplier<String> messageSupplier) {
        if (MapUtils.isEmpty(map)) {
            throw new PlatformException(nullSafeGet(messageSupplier));
        }
    }

    public static void isEmpty(Map<?, ?> map, IErrorCode errorCodeEnum, String errorMsg) {
        checkArgument(errorCodeEnum);
        checkArgument(errorMsg);

        if (MapUtils.isNotEmpty(map)) {
            throw new PlatformException(errorMsg, errorCodeEnum.getCode());
        }
    }

    public static void isEmpty(Map<?, ?> map, IErrorCode errorCodeEnum) {
        checkArgument(errorCodeEnum);

        if (MapUtils.isNotEmpty(map)) {
            throw new PlatformException(errorCodeEnum.getCode());
        }
    }

    public static void isEmpty(Map<?, ?> map, String errorMsg) {
        checkArgument(errorMsg);

        if (MapUtils.isNotEmpty(map)) {
            throw new PlatformException(errorMsg);
        }
    }

    public static void isEmpty(Map<?, ?> map, Supplier<String> messageSupplier) {
        if (MapUtils.isNotEmpty(map)) {
            throw new PlatformException(nullSafeGet(messageSupplier));
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, IErrorCode errorCodeEnum,
                                    String errorMsg) {
        checkArgument(errorCodeEnum);
        checkArgument(errorMsg);

        if (!type.isInstance(obj)) {
            throw new PlatformException(errorMsg, errorCodeEnum.getCode());
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, IErrorCode errorCodeEnum) {
        checkArgument(errorCodeEnum);

        if (!type.isInstance(obj)) {
            throw new PlatformException(errorCodeEnum.getCode());
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, String errorMsg) {
        checkArgument(errorMsg);

        if (!type.isInstance(obj)) {
            throw new PlatformException(errorMsg);
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, Supplier<String> messageSupplier) {
        if (!type.isInstance(obj)) {
            throw new PlatformException(nullSafeGet(messageSupplier));
        }
    }

    private static String nullSafeGet(Supplier<String> messageSupplier) {
        if (messageSupplier == null) {
            throw new IllegalArgumentException("messageSupplier is null");
        }

        return messageSupplier.get();
    }

    private static void checkArgument(IErrorCode errorCodeEnum) {
        if (errorCodeEnum == null && errorCodeEnum.getCode() == null) {
            throw new IllegalArgumentException("error is null");
        }
    }

    private static void checkArgument(String errorMsg) {
        if (StringUtils.isBlank(errorMsg)) {
            throw new IllegalArgumentException("errorMsg is blank");
        }
    }

}
