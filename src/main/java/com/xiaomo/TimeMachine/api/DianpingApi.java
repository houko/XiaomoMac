package com.xiaomo.TimeMachine.api;

import com.alibaba.fastjson.JSON;
import net.dreamlu.api.util.HttpKit;
import net.dreamlu.api.util.OathConfig;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 大众点评api
 * 调用大众点评系列周边信息api，没有用百度的
 * 大众点评真在测试佣金，有钱便是爷
 *
 * @author L.cm
 *         email: 596392912@qq.com
 *         site:  http://www.dreamlu.net
 * @date Jul 18, 2013 11:38:30 PM
 * <url>http://developer.dianping.com/app/documentation/api</url>
 */
public class DianpingApi {

    // http://api.dianping.com/v1/business/find_businesses

    private String appKey;
    private String secret;

    public DianpingApi() {
        this.appKey = OathConfig.getValue("dp_key");
        this.secret = OathConfig.getValue("dp_secret");
    }

    public static void main(String[] args) throws IOException {
        String url = "http://api.dianping.com/v1/business/find_businesses";
        Map<String, String> paramMap = new HashMap<String, String>();
        DianpingApi dianpin = new DianpingApi();
        paramMap.put("city", "北京");
        Map<String, Object> dpjson = dianpin.doApi(url, paramMap);
        System.out.println(JSON.toJSONString(dpjson));
    }

    private String sign(Map<String, String> paramMap) {
        // 对参数名进行字典排序
        Object[] keys = paramMap.keySet().toArray();
        Arrays.sort(keys);
        // 拼接有序的参数名-值串
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(appKey);
        for (Object key : keys) {
            stringBuilder.append(key).append(paramMap.get(key));
        }
        stringBuilder.append(secret);
        String codes = stringBuilder.toString();
        System.out.println(codes);
        return DigestUtils.shaHex(codes).toUpperCase();
    }

    // http://api.dianping.com/v1/business/find_businesses_by_coordinate
//	public static final String findBusinessesByCoordinate() {
//		
//	}

    /**
     * 执行api
     *
     * @param @param  paramMap
     * @param @return 设定文件
     * @return JSONObject    返回类型
     * @throws
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> doApi(String url, Map<String, String> paramMap) throws IOException {
        paramMap.put("sign", sign(paramMap));
        paramMap.put("appkey", getAppKey());
        return JSON.parseObject(HttpKit.get(url, paramMap), Map.class);
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
