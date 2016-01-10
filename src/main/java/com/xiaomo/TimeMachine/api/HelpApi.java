package com.xiaomo.TimeMachine.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.dreamlu.api.util.HttpKit;
import net.dreamlu.api.util.OathConfig;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 生活帮助类api
 *
 * @author L.cm
 *         email: 596392912@qq.com
 *         site:  http://www.dreamlu.net
 * @date Jul 10, 2013 10:26:26 PM
 */
public class HelpApi {

    public static final String DEFAULT_CITY = "杭州"; // 默认城市
    private static final String WEATHER_URL = "http://api.map.baidu.com/telematics/v2/weather";
    private static final String TRANSLATE_URL = "http://openapi.baidu.com/public/2.0/bmt/translate";
    private static final String TAOBAO_IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    /**
     * 百度天气api
     *
     * @param @param  params
     * @param @return 设定文件
     * @return JSONObject    返回类型
     * @throws <url>http://developer.baidu.com/map/carapi-7.htm</url>
     */
    public static JSONArray weather(String data) throws IOException {
        // location=北京&output=json&ak=6c19c46381dd73b0b5f6a5c0c8acde42
        Map<String, String> params = new HashMap<String, String>();
        params.put("location", data);
        params.put("ak", OathConfig.getValue("lbs_ak_baidu"));
        params.put("output", "json");
        JSONObject object = JSONObject.parseObject(HttpKit.get(WEATHER_URL, params));
        if (null != object && object.containsKey("results")) {
            return object.getJSONArray("results");
        }
        return null;
    }

    /**
     * 百度翻译api
     *
     * @param @param  data
     * @param @return 设定文件
     * @return JSONObject    返回类型
     * @throws <url>http://developer.baidu.com/wiki/index.php?title=%E5%B8%AE%E5%8A%A9%E6%96%87%E6%A1%A3%E9%A6%96%E9%A1%B5/%E7%99%BE%E5%BA%A6%E7%BF%BB%E8%AF%91/%E7%BF%BB%E8%AF%91API</url>
     */
    public static JSONArray translate(String data) throws IOException {
        // client_id=xwKOgtKjbbrn9dOb7ZkGrAo5&q=today&from=auto&to=auto
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", data);
        params.put("client_id", OathConfig.getValue("openid_baidu"));
        params.put("from", "auto");
        params.put("to", "auto");
        JSONObject object = JSONObject.parseObject(HttpKit.get(TRANSLATE_URL, params));
        if (null != object && object.containsKey("trans_result")) {
            return object.getJSONArray("trans_result");
        }
        return null;
    }

    /**
     * 淘宝ip库
     *
     * @param @param  ip
     * @param @return 设定文件
     * @return Map<String,String>    返回类型
     * @throws
     */
    public static JSONObject ipData(String ip) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ip", ip);
        JSONObject object = JSONObject.parseObject(HttpKit.get(TAOBAO_IP_URL, params));
        if (null != object && object.containsKey("data")) {
            return object.getJSONObject("data");
        }
        return null;
    }

    /**
     * 翻译博文title
     *
     * @param title
     * @throws IOException
     */
    public static String transBlogTitle(String title) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", title);
        params.put("client_id", OathConfig.getValue("openid_baidu"));
        params.put("from", "zh");
        params.put("to", "en");
        JSONObject object = JSONObject.parseObject(HttpKit.get(TRANSLATE_URL, params));
        if (null != object && object.containsKey("trans_result")) {
            JSONObject json = object.getJSONArray("trans_result").getJSONObject(0);
            String dst = json.getString("dst");
            String[] words = StringUtils.split(dst, " ");
            return StringUtils.join(words, "-");
        }
        return null;
    }
}
