package hardlearner.springStudy.user.transaction;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TransactionHandler implements InvocationHandler {
    private Object target; // 부가기능을 제공할 타깃 오브제그
    private PlatformTransactionManager transactionManager;
    private String pattern; // 트랜잭션을 적용할 메소드 이름 패턴

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {

        this.transactionManager = transactionManager;
    }

    public void setTarget(Object target) {

        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith(pattern)) {
            return invokeTransaction(method, args);
        }
        return method.invoke(target, args);
    }

    private Object invokeTransaction(Method method, Object[] args) throws Throwable {
        TransactionStatus status =
                this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            Object ret = method.invoke(target, args);
            this.transactionManager.commit(status);
            return ret;
        } catch (InvocationTargetException e) {
            this.transactionManager.rollback(status);
            throw e.getTargetException();
        } catch (IllegalAccessException e) {
            throw new IllegalStateException();
        }
    }
}
