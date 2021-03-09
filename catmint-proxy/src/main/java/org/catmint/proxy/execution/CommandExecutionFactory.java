package org.catmint.proxy.execution;

import org.catmint.core.config.define.DialectEnum;
import org.catmint.proxy.execution.mysql.MySQLCommandExecutionEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public final class CommandExecutionFactory {
    private static final Map<String,CommandExecutionEngine> COMMAND_EXECUTION_ENGINE_MAP = new HashMap<>();

    static {
        COMMAND_EXECUTION_ENGINE_MAP.put( DialectEnum.MYSQL.getName(),new MySQLCommandExecutionEngine() );
    }

    public static CommandExecutionEngine getCommandExecutionEngine(final String dialect){
        return COMMAND_EXECUTION_ENGINE_MAP.get( dialect );
    }
}
