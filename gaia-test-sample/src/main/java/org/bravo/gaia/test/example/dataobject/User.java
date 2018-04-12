package org.bravo.gaia.test.example.dataobject;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author lijian
 * @version $Id: User.java, v 0.1 2018年04月12日 18:28 lijian Exp $
 */
@Entity
@Table(name = "t_user")
@Setter
@Getter
public class User {

    //@Id
    private String id;

    private String name;




}