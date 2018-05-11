package com.ytd.mds.exception;


public class CustomIllegalArgumentException extends CustomRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CustomIllegalArgumentException() {
        super();
    }

    public CustomIllegalArgumentException(String msg) {
        super(msg);
    }

}
