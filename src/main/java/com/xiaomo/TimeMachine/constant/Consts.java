package com.xiaomo.TimeMachine.constant;

import com.jfinal.kit.PathKit;

/**
 * 系统常量
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 * @date 2013-7-1 上午9:28:16
 */
public class Consts {

	// 缓存时间 10分钟
	public static final int CACHE_TIME_MINI = 60 * 10;
	// 缓存时间 20分钟
	public static final int CACHE_TIME_MAX = 60 * 20;
	// Http缓存时间 一个月
	public static final long HTTP_CACHE_TIME = 2592000L;
	// 用户session key
	public static final String USER_SESSION = "user";
	// 分页大小
	public static final int BLOG_PAGE_SIZE = 8;
	// 百度云 ak，sk
//	public static String AK = ConfigKit.getStr("db.user");
//	public static String SK = ConfigKit.getStr("db.password");
//
//	// domain
//	public static String DOMAIN_COOKIE = ConfigKit.getStr("domain");
//	public static String DOMAIN_NAME   = ConfigKit.getStr("domain.name");
//	public static String DOMAIN_URL	= "http://".concat(DOMAIN_NAME);
	// gravatar url, 七牛的gravatar镜像
//	public static String PHOTO_URL	= "http://".concat(ConfigKit.getStr("gravatar.url")).concat("/avatar/");
	// 安装状态
	public static boolean IS_INSTALL = false;
	
	// tmpfsPath
	public static final String TMP_FS_PATH = PathKit.getWebRootPath() + "/temp";
	
	// bucket
//	public static final String QINIU_BUCKET = ConfigKit.getStr("qiniu.bucket");
	
//	static {
//		Config.ACCESS_KEY = ConfigKit.getStr("qiniu.access.key");
//		Config.SECRET_KEY = ConfigKit.getStr("qiniu.secret.key");
//	}
	
	// ajax 状态
	public static final String AJAX_STATUS = "status";
	public static final int AJAX_Y = 0; //OK
	public static final int AJAX_N = 1; //no
	public static final int AJAX_S = 2; //权限
	public static final int AJAX_O = 3; //other 其他错误
}
