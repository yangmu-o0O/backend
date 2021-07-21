package com.tian.backend.api.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * <h2>返回消息基础实体类</h2>
 * @author muyang.tian
 * @date 2021/4/28 10:15
 */
@Data
@JacksonXmlRootElement(localName = "xml")
public class ReplyBaseMessage {

    /**
     * 开发者微信号
     */
    @JacksonXmlProperty(localName = "ToUserName")
    protected String toUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    @JacksonXmlProperty(localName = "FromUserName")
    protected String fromUserName;
    /**
     * 消息创建时间 （整型）
     */
    @JacksonXmlProperty(localName = "CreateTime")
    protected Long createTime;
    /**
     * 消息类型，文本为text
     */
    @JacksonXmlProperty(localName = "MsgType")
    protected String msgType;

}
