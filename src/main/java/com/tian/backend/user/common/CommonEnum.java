package com.tian.backend.user.common;

/**
 * @author muyang.tian
 * @date 2021/4/16 18:08
 */
public interface CommonEnum {

    /**
     * 员工状态
     */
    enum StaffState{
        WORK("work","在职"),
        REGION("region","离职");

        private String value;
        private String name;

        StaffState(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
}
