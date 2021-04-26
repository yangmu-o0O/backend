package com.tian.backend.user.common;

/**
 * @author muyang.tian
 * @date 2021/4/26 09:49
 */
public interface ResultCode {

    enum ErrorCode{
        ;

        ErrorCode(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        private Integer code;
        private String name;
    }
    enum SuccessCode{
        success(200,"success");

        SuccessCode(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        private Integer code;
        private String name;
    }
}
