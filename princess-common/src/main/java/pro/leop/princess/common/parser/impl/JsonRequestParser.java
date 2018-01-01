package pro.leop.princess.common.parser.impl;

import com.alibaba.fastjson.JSON;
import pro.leop.princess.common.entity.Request;
import pro.leop.princess.common.parser.IRequestParser;

/**
 * 基于Json的请求解析器
 */
public class JsonRequestParser implements IRequestParser {
    @Override
    public Request parse(byte[] data) {
        Request request = JSON.parseObject(data, Request.class);
        return request;
    }

    @Override
    public byte[] unparse(Request object) {
        return JSON.toJSONBytes(object);
    }
}
