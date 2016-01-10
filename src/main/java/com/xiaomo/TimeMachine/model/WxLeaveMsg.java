package com.xiaomo.TimeMachine.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * 微信留言
 *
 * @author L.cm
 *         email: 596392912@qq.com
 *         site:  http://www.dreamlu.net
 * @date 2013-09-19 01:35:57
 */
public class WxLeaveMsg extends Model<WxLeaveMsg> {

    public static final String TABLE_NAME = "wx_leave_msg";
    public static final String ID = "id";
    public static final String WX_USER = "wx_user";
    public static final String MSG = "msg";
    public static final WxLeaveMsg dao = new WxLeaveMsg();
    private static final long serialVersionUID = 6876954336686053233L;

//    public Map<String, Object> pageDataTables(int pageNum, int pageSize, String sEcho,
//                                              String search) {
//        final List<Object> parameters = new ArrayList<Object>();
//        String select = "SELECT m.*";
//        StringBuilder sqlExceptSelect = new StringBuilder(" FROM wx_leave_msg m");
//        if (StrKit.notBlank(search)) {
//            sqlExceptSelect.append(" AND m.msg like ?");
//            parameters.add("%" + search + "%");
//        }
//        sqlExceptSelect.append(" ORDER BY m.id DESC");
//        return dao.paginateDataTables(pageNum, pageSize, select, sqlExceptSelect.toString(), sEcho, parameters.toArray());
//    }
}