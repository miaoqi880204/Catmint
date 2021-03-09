package org.catmint.proxy.packet.mysql.command;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.ToString;
import org.catmint.proxy.packet.mysql.MySQLCommandPacketType;
import org.catmint.proxy.packet.mysql.MySQLPacketPayload;
import org.catmint.proxy.packet.constant.MySQLColumnType;
import org.catmint.proxy.packet.constant.MySQLNewParametersBoundFlag;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * COM_STMT_EXECUTE command packet for MySQL.
 *
 * @see <a href="https://dev.mysql.com/doc/internals/en/com-stmt-execute.html">COM_STMT_EXECUTE</a>
 *
 * @author Shuo Xiang
 */
@ToString(of = {"sql", "parameters"})
public final class MySQLComStmtExecutePacket extends MySQLCommandPacket {

    private static final int ITERATION_COUNT = 1;

    private static final int NULL_BITMAP_OFFSET = 0;

    private final int statementId;

    private final MySQLBinaryStatement binaryStatement;

    private final int flags;

    private final MySQLNullBitmap nullBitmap;

    private final MySQLNewParametersBoundFlag newParametersBoundFlag;

    @Getter
    private final String sql;

    @Getter
    private final List<Object> parameters;

    public MySQLComStmtExecutePacket(final MySQLPacketPayload payload) throws SQLException {
        super(MySQLCommandPacketType.COM_STMT_EXECUTE);
        statementId = payload.readInt4();
//        binaryStatement = MySQLBinaryStatementRegistry.getInstance().getBinaryStatement(statementId);
        binaryStatement = null;
        flags = payload.readInt1();
        Preconditions.checkArgument(ITERATION_COUNT == payload.readInt4());
        int parametersCount = binaryStatement.getParametersCount();
        nullBitmap = new MySQLNullBitmap(parametersCount, NULL_BITMAP_OFFSET);
        for (int i = 0; i < nullBitmap.getNullBitmap().length; i++) {
            nullBitmap.getNullBitmap()[i] = payload.readInt1();
        }
        newParametersBoundFlag = MySQLNewParametersBoundFlag.valueOf(payload.readInt1());
        if (MySQLNewParametersBoundFlag.PARAMETER_TYPE_EXIST == newParametersBoundFlag) {
            binaryStatement.setParameterTypes(getParameterTypes(payload, parametersCount));
        }
        sql = binaryStatement.getSql();
        parameters = getParameters(payload, parametersCount);
    }

    private List<MySQLBinaryStatementParameterType> getParameterTypes(final MySQLPacketPayload payload, final int parametersCount) {
        List<MySQLBinaryStatementParameterType> result = new ArrayList<>(parametersCount);
        for (int parameterIndex = 0; parameterIndex < parametersCount; parameterIndex++) {
            MySQLColumnType columnType = MySQLColumnType.valueOf(payload.readInt1());
            int unsignedFlag = payload.readInt1();
            result.add(new MySQLBinaryStatementParameterType(columnType, unsignedFlag));
        }
        return result;
    }

    private List<Object> getParameters(final MySQLPacketPayload payload, final int parametersCount) throws SQLException {
        List<Object> result = new ArrayList<>(parametersCount);
        for (int parameterIndex = 0; parameterIndex < parametersCount; parameterIndex++) {
//            MySQLBinaryProtocolValue binaryProtocolValue = MySQLBinaryProtocolValueFactory.getBinaryProtocolValue(binaryStatement.getParameterTypes().get(parameterIndex).getColumnType());
//            result.add(nullBitmap.isNullParameter(parameterIndex) ? null : binaryProtocolValue.read(payload));
        }
        return result;
    }

    @Override
    public void doWrite(final MySQLPacketPayload payload) {
        payload.writeInt4(statementId);
        payload.writeInt1(flags);
        payload.writeInt4(ITERATION_COUNT);
        for (int each : nullBitmap.getNullBitmap()) {
            payload.writeInt1(each);
        }
        payload.writeInt1(newParametersBoundFlag.getValue());
        int count = 0;
        for (Object each : parameters) {
            MySQLBinaryStatementParameterType parameterType = binaryStatement.getParameterTypes().get(count);
            payload.writeInt1(parameterType.getColumnType().getValue());
            payload.writeInt1(parameterType.getUnsignedFlag());
            payload.writeStringLenenc(null == each ? "" : each.toString());
            count++;
        }
    }
}
