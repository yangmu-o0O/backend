package com.tian.backend.wxApi.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author muyang.tian
 * @date 2021/4/28 14:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ReplyTextMessage extends ReplyBaseMessage{

    @JacksonXmlProperty(localName = "Content")
    private String Content;
}
