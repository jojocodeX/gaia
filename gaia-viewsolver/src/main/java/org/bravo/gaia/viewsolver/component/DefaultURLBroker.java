/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package org.bravo.gaia.viewsolver.component;

import lombok.Getter;
import lombok.Setter;

/**
 * 默认的uri broker实现
 * @author lijian
 * @version @Id: DefaultURLBroker.java, v 0.1 2018年11月19日 22:49 lijian Exp $
 */
@Getter
@Setter
public class DefaultURLBroker implements URLBroker {

    /**
     * url名称，用于匹配一个uribroker对象
     */
    private String name;

    /**
     * uribroker的访问路径
     */
    private String path;

    public DefaultURLBroker() {
    }

    public DefaultURLBroker(String name, String path) {
        this.name = name;
        this.path = path;
    }

    @Override
    public String render() {
        return this.path;
    }
}
