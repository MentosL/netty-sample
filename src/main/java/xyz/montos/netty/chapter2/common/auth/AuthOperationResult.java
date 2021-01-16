package xyz.montos.netty.chapter2.common.auth;

import lombok.Data;
import xyz.montos.netty.chapter2.common.OperationResult;

/**
 * @author Montos
 * @create 2021/1/10 9:17 下午
 */
@Data
public class AuthOperationResult extends OperationResult {

    private final Boolean passAuth;

    public AuthOperationResult(Boolean passAuth) {
        this.passAuth = passAuth;
    }
}
