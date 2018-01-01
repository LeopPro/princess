package pro.leop.princess.common.parser.impl;

import com.alibaba.fastjson.JSON;
import pro.leop.princess.common.entity.Response;
import pro.leop.princess.common.parser.IResponseParser;

/**
 * 基于Json的响应解析器
 */
public class JsonResponseParser implements IResponseParser {
    @Override
    public Response parse(byte[] data) {
        return JSON.parseObject(data, Response.class);
    }

    @Override
    public byte[] unparse(Response object) {
        return JSON.toJSONBytes(object);
    }
}
