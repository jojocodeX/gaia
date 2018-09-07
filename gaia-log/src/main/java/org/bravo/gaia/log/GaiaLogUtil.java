package org.bravo.gaia.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * gaia提供的基础日志工具
 *
 * @author lijian
 * @version $Id: GaiaLogUtil.java, v 0.1 2018年04月13日 11:56 lijian Exp $
 */
public class GaiaLogUtil {

    public final static String GLOBAL_ERROR_LOGGER = "ERROR";

    public final static String TRACE_MSG_SUB       = "TRACE-MESSAGE-SUB";

    public final static String TRACE_MSG_PUB       = "TRACE-MESSAGE-PUB";

    public final static String TRACE_RPC_SEND      = "TRACE-RPC-SEND";

    public final static String TRACE_RPC_RECEPT    = "TRACE-RPC-RECEPT";

    public final static String TRACE_DATABASE      = "TRACE-DATABASE";

    public final static String BIZ_MSG_SUB         = "BIZ-MESSAGE-SUB";

    public final static String BIZ_MSG_PUB         = "BIZ-MESSAGE-PUB";

    public final static String BIZ_RPC_SEND        = "BIZ-RPC-SEND";

    public final static String BIZ_RPC_RECEPT      = "BIZ-RPC-RECEPT";

    public final static String BIZ_DATABASE        = "BIZ-DATABASE";

    public static Logger getGlobalErrorLogger() {
        return LoggerFactory.getLogger(GLOBAL_ERROR_LOGGER);
    }

    public static Logger getTraceMsgSubLogger() {
        return LoggerFactory.getLogger(TRACE_MSG_SUB);
    }

    public static Logger getTraceMsgPubLogger() {
        return LoggerFactory.getLogger(TRACE_MSG_PUB);
    }

    public static Logger getTraceRpcReceptLogger() {
        return LoggerFactory.getLogger(TRACE_RPC_RECEPT);
    }

    public static Logger getTraceRpcSendLogger() {
        return LoggerFactory.getLogger(TRACE_RPC_SEND);
    }

    public static Logger getTraceDatabaseLogger() {
        return LoggerFactory.getLogger(TRACE_DATABASE);
    }

    public static Logger getBizMsgSubLogger() {
        return LoggerFactory.getLogger(BIZ_MSG_SUB);
    }

    public static Logger getBizMsgPubLogger() {
        return LoggerFactory.getLogger(BIZ_MSG_PUB);
    }

    public static Logger getBizRpcReceptLogger() {
        return LoggerFactory.getLogger(BIZ_RPC_RECEPT);
    }

    public static Logger getBizRpcSendLogger() {
        return LoggerFactory.getLogger(BIZ_RPC_SEND);
    }

    public static Logger getBizDatabaseLogger() {
        return LoggerFactory.getLogger(BIZ_DATABASE);
    }

}