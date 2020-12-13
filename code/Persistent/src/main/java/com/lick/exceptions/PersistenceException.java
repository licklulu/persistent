package com.lick.exceptions;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/12 17:06
 */
public class PersistenceException extends RuntimeException{

    private static final long serialVersionUID = -7537395265357977271L;

    public PersistenceException() {
        super();
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }
}
