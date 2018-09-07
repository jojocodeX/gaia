/**
 * bravo.org
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package org.bravo.gaia.commons.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.bravo.gaia.commons.commoninterface.ErrorCodeLevel;
import org.bravo.gaia.commons.commoninterface.ErrorCodeType;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统综合码，抽象类，子类包含结果码和异常码。
 * <p>综合码码位总共14位，不多不少，必须满足；
 * <p>系统公用码的格式如下：
 *      <table border="1">
 *          <tr>
 *           <td><b>位置</b></td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td><td>12</td><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td>
 *          </tr>
 *          <tr>
 *           <td><b>示例</b></td><td>S</td><td>Y</td><td>S</td><td>M</td><td>_</td><td>M</td><td>1</td><td>_</td><td>1</td><td>0</td><td>0</td><td>0</td><td>1</td><td>0</td><td>0</td><td>0</td><td>1</td>
 *          </tr>
 *          <tr>
 *           <td><b>说明</b></td><td colspan=4>固定标识1</td><td>分<br>隔<br>符<br></td><td colspan=2>固定<br>标识2</td><td>分<br>隔<br>符<br></td><td>返<br>回<br>码<br>级<br>别</td><td>返<br>回<br>码<br>类<br>型</td><td colspan=3>&nbsp;&nbsp;系统编号</td><td colspan=4>序&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</td>
 *          </tr>
 *      </table>
 *   
 * 
 * @author lijian
 * @version $Id: CommonCode.java, v 0.1 2018年1月6日 下午12:34:15 lijian Exp $
 */
public class ErrorCode implements Serializable {

    private static final long serialVersionUID = 2377988816412754062L;

    @Getter
    @Setter
    /** 综合码固定前缀 ，可以进行变更，通常固定码指代的是一个大的应用或者一个大的平台*/
    protected String          prefix           = "SYSM_M1_";

    @Setter
    @Getter
    /** 综合码级别[第9位]，参见{@link ErrorCodeLevel} */
    protected String          codeLevel;

    @Setter
    @Getter
    /** 综合码类型[第10位]，参见{@link ErrorCodeType} */
    protected String          codeType;

    @Setter
    @Getter
    /** 系统编号[第11-13位]，通常指代的是一个微服务(类似用户中心、订单中心)编号，参见{@link org.bravo.gaia.commons.system.SystemNameEnum} */
    protected String          systemCode;

    @Setter
    @Getter
    /** 错误码编号 */
    protected String          errorCodeNum;

    @Setter
    @Getter
    protected String          errorDesc;

    // ~~~ 构造方法

    /**
     * 根据单一字符串构造错误码
     * 构造方法。
     * 
     * @param codeString    综合码字符串-17位
     */
    public ErrorCode(String codeString) {

        //综合码长度检查
        checkStringLength(codeString, 17);

        //拆分综合码
        spliteResultCode(codeString);
    }

    /**
     * 构造函数
     * @param prefix 平台前缀
     * @param codeLevel 码等级
     * @param codeType 码类型
     * @param systemCode 系统编号
     * @param errorCodeNum 错误编号
     */
    public ErrorCode(String prefix, String codeLevel, String codeType, String systemCode,
                     String errorCodeNum, String errorDesc) {
        this.prefix = prefix;
        this.codeLevel = codeLevel;
        this.codeType = codeType;
        this.systemCode = systemCode;
        this.errorCodeNum = errorCodeNum;
        this.errorDesc = errorDesc;
    }

    /**
     * 构造函数
     * @param codeLevel 码等级
     * @param codeType 码类型
     * @param systemCode 系统编号
     * @param errorCodeNum 错误编号
     */
    public ErrorCode(String codeLevel, String codeType, String systemCode, String errorCodeNum, String errorDesc) {
        this.codeLevel = codeLevel;
        this.codeType = codeType;
        this.systemCode = systemCode;
        this.errorCodeNum = errorCodeNum;
        this.errorDesc = errorDesc;
    }

    /**
     * 组装返回码字符串。
     * 
     * @return  返回码字符串
     */
    public String fetchResultCode() {
        StringBuffer sb = new StringBuffer();

        sb.append(prefix);
        sb.append(codeLevel);
        sb.append(codeType);
        sb.append(systemCode);
        sb.append(errorCodeNum);

        return sb.toString();
    }

    @Override
    public String toString() {

        //错误完整性检查
        checkStringLength(this.codeLevel, 1);
        checkStringLength(this.codeType, 1);
        checkStringLength(this.systemCode, 3);
        checkStringLength(this.errorCodeNum, 4);

        //组装综合码字符串
        String resultCode = fetchResultCode();

        return resultCode;
    }

    /** 
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.fetchResultCode().hashCode();
    }

    /** 
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        return StringUtils.equals(this.fetchResultCode(), (((ErrorCode) obj).fetchResultCode()));
    }

    // ~~~ 内部方法

    /**
     * 解析和拆分综合码。
     * 
     * @param resultCode    综合码字符串
     */
    private void spliteResultCode(String resultCode) {
        if (!resultCode.startsWith(prefix)) {
            throw new IllegalArgumentException();
        }

        char[] chars = resultCode.toCharArray();

        this.codeLevel = "" + chars[8];
        this.codeType = "" + chars[9];
        this.systemCode = "" + chars[10] + chars[11] + chars[12];
        this.errorCodeNum = "" + chars[13] + chars[14] + chars[15] + chars[16];
    }

    /**
     * 字符串长度检查。
     * 
     * @param codeString    综合码字符串
     * @param length        长度
     */
    private void checkStringLength(String codeString, int length) {
        if (codeString == null || codeString.length() != length) {
            throw new IllegalArgumentException();
        }
    }

}
