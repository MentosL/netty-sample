package xyz.montos.netty.chapter2.common.auth;

import lombok.Data;
import lombok.extern.java.Log;
import xyz.montos.netty.chapter2.common.Operation;
import xyz.montos.netty.chapter2.common.OperationResult;

/**
 * @author Montos
 * @create 2021/1/10 9:17 下午
 */
@Data
@Log
public class AuthOperation extends Operation {

    private final String userName;
    private final String password;

    @Override
    public OperationResult execute() {
        if ("admin".equalsIgnoreCase(this.userName)) {
            AuthOperationResult authOperationResult = new AuthOperationResult(true);
            return authOperationResult;
        }
        return new AuthOperationResult(false);
    }
}
