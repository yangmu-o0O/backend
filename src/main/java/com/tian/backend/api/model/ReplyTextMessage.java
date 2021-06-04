package com.tian.backend.api.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <h2>文本消息实体类</h2>
 *
 * @author muyang.tian
 * @date 2021/4/28 14:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ReplyTextMessage extends ReplyBaseMessage {

    @JacksonXmlProperty(localName = "Content")
    private String Content;
}
