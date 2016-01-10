package com.xiaomo.TimeMachine.helper;

import com.jfinal.kit.StrKit;
import com.xiaomo.TimeMachine.utils.DateUtil;
import com.xiaomo.TimeMachine.utils.HtmlFilter;
import com.xiaomo.TimeMachine.utils.StringUtil;

import java.sql.Timestamp;

/**
 * jetbrick模版函数
 *
 * @author l.cm
 * @site:www.dreamlu.net 2014年3月22日 下午5:46:41
 */
public class JetFuns {
    // use ${baseFormat(XXXX)}
    public static String baseFormat(Object tamp) {
        return DateUtil.format((Timestamp) tamp);
    }

    // use ${format(XXXX)}
    public static String format(Object tamp) {
        return DateUtil.formatCn((Timestamp) tamp);
    }

    // use ${formatRss(XXXX)}
    public static String formatRss(Object tamp) {
        return DateUtil.formartRss((Timestamp) tamp);
    }

    // 清除字符串中的html标签
    public static String filterText(String string) {
        return HtmlFilter.getText(string);
    }

    // 清除rss字符串中的html标签
    public static String filterRss(String string) {
        string = HtmlFilter.getText(string);
        return string.replaceAll("\\s*|\t|\r|\n", "").replaceAll("&nbsp;", "");
    }

    // 清除字符串中的html标签并截取
    public static String filterSubText(String string, int length) {
        //string = string.replaceAll("&nbsp;", "");
        return StringUtil.subCn(HtmlFilter.getText(string), length, "...");
    }

    // 页面description，seo
    public static String description(String string) {
        string = string.replaceAll("\\s*|\t|\r|\n", "").replaceAll("&nbsp;", "");
        return StringUtil.subCn(HtmlFilter.getText(string), 90, "...");
    }

    // 页面keyWords，seo，分词
//    public static String keyWords(Integer blogid) {
//        List<BlogTag> list = BlogTag.dao.findCacheListByBlogId(blogid);
//        Set<String> tags = new HashSet<String>();
//        for (BlogTag blogTag : list) {
//            tags.add(blogTag.getStr(Tags.TAG_NAME));
//        }
//        return StringUtils.join(tags, ',');
//    }

    // 标记关键字
    public static String markKeywords(String string, int length, String keywords) {
        if (StrKit.notBlank(keywords)) {
            return HtmlFilter.markKeywods(keywords, filterSubText(string, length));
        } else {
            return filterSubText(string, length);
        }
    }

//    // 标签帮助，根据博文查找该博文的标签集合
//    public static String bTags(Integer blogid) {
//        StringBuilder html = new StringBuilder();
//        List<BlogTag> list = BlogTag.dao.findCacheListByBlogId(blogid);
//        if(null != list && list.size() > 0) {
//            html.append("<span class=\"mt_icon\"></span>");
//        }
//        String temp = "<a title=\"{}\" href=\"/tags/{}\">{}</a>";
//        for (int i = 0; i < list.size(); i++) {
//            BlogTag blogTag = list.get(i);
//            html.append(temp.replace("{}", blogTag.getStr(Tags.TAG_NAME)));
//            if (i != list.size() - 1 ) {
//                html.append(',');
//            }
//        }
//        return html.toString();
//    }
//
//    // 热门标签 默认最多22个
//    public static List<Tags> hostTags() {
//        return Tags.dao.findHostTags(14);
//    }
//
//    // 热门话题 5条
//    public static List<Blog> topBlogs() {
//        return Blog.dao.findHotList(5);
//    }
//
//    // 最新博文 5条
//    public static List<Blog> lateBlogs() {
//        return Blog.dao.findLateList(5);
//    }
//
//    // combo 压缩css，js
//    public static String combo(String fileName) {
//        try {
//            return AssetsKit.combo(fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // friendLinks 友情链接
//    public static List<Links> friendLinks() {
//        return Links.dao.findListByType(0, Links.DEL_N);
//    }
//
//    /**
//     * rss 订阅最新20条
//     */
//    public static List<Blog> rssList() {
//        return Blog.dao.rssList(20);
//    }
//
//    /**
//     * sitemap查找所有的blog估计会有耗时...
//     * 最好是改成定时器去生成
//     */
//    public static List<Blog> findAll() {
//        return Blog.dao.findAll();
//    }

    /**
     * 从博文中获取一张图片
     *
     * @param content
     * @return url
     */
    public static String getBlogImg(String content) {
        return HtmlFilter.getImgSrc(content);
    }
}
