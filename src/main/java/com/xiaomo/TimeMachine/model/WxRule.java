package com.xiaomo.TimeMachine.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * 微信回复rule
 *
 * @author L.cm
 *         email: 596392912@qq.com
 *         site:  http://www.dreamlu.net
 * @date 2013-06-23 23:39:58
 * jfinal-rapid自动生成：<url>http://git.oschina.net/596392912/jfinal-rapid</url>
 */
public class WxRule extends Model<WxRule> {

    public static final String TABLE_NAME = "wx_rule";
    public static final String ID = "id";
    public static final String RULE = "rule";                // 规则
    public static final String P_ID = "pid";                // 父级规则
    public static final String REPLY = "reply";                // 规则对应的回复
    public static final String DIRECTIONS = "directions";   // 规则说明
    public static final String D = "default";    //默认规则
    public static final WxRule dao = new WxRule();
    private static final long serialVersionUID = -5235609179278435648L;

//	// datatable
//	public Map<String, Object> pageDataTables(int pageNum, int pageSize, String sEcho,
//			String search) {
//		final List<Object> parameters = new ArrayList<Object>();
//		String select = "SELECT r.*";
//		StringBuilder sqlExceptSelect = new StringBuilder(" FROM wx_rule r");
//		if (StrKit.notBlank(search)) {
//			sqlExceptSelect.append(" AND l.rule like ?");
//			parameters.add("%" + search + "%");
//		}
//		sqlExceptSelect.append(" ORDER BY r.id DESC");
//		return dao.paginateDataTables(pageNum, pageSize, select, sqlExceptSelect.toString(), sEcho, parameters.toArray());
//	}
//
//	// 根据rule查找信息
//	public WxRule findByRule(String rule) {
//		String sql = "SELECT r.reply FROM wx_rule r WHERE r.rule = ? limit 1";
//		return dao.findFirstByCache(Consts.CACHE_TIME_MINI, sql, rule);
//	}
}