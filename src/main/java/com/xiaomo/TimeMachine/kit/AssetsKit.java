package com.xiaomo.TimeMachine.kit;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * YUICompressor压缩帮助类
 *
 * @author L.cm
 * @date 2013-7-1 下午1:00:28
 */
public class AssetsKit {

    public static final String CHARSET = "UTF-8";
    public static final String JS_EXT = ".js", CSS_EXT = ".css";
    private static final Logger log = LoggerFactory.getLogger(AssetsKit.class);

    /**
     * 压缩css,js帮助
     *
     * @param isCss
     * @param out
     * @return
     */
    private static boolean compressorHelper(List<String> fileList, boolean isCss, Writer out) {
        Reader in = null;
        try {
            if (isCss) {
                for (String path : fileList) {
                    String filePath = PathKit.getWebRootPath() + path;
                    in = new InputStreamReader(new FileInputStream(filePath), CHARSET);
                    if (path.indexOf(".min.") > 0) {// 对.min.css的css放弃压缩
                        out.append(repairCss(IOUtils.toString(in), path));
                    } else {
                        CssCompressor css = new CssCompressor(new StringReader(repairCss(IOUtils.toString(in), path)));
                        in.close();
                        in = null;
                        css.compress(out, -1);
                    }
                }
            } else {
                // nomunge: 混淆,verbose：显示信息消息和警告,preserveAllSemiColons：保留所有的分号 ,disableOptimizations 禁止优化
                boolean munge = true, verbose = false, preserveAllSemiColons = false, disableOptimizations = false;
                for (String path : fileList) {
                    String filePath = PathKit.getWebRootPath() + path;
                    in = new InputStreamReader(new FileInputStream(filePath), CHARSET);
                    if (path.indexOf(".min.") > 0) { // 对.min.js的js放弃压缩
                        out.append(IOUtils.toString(in));
                    } else {
                        JavaScriptCompressor compressor = new JavaScriptCompressor(in, new ErrorReporter() {
                            public void warning(String message, String sourceName,
                                                int line, String lineSource, int lineOffset) {
                                if (line < 0) {
                                    log.error("\n[WARNING] " + message);
                                } else {
                                    log.error("\n[WARNING] " + line + ':' + lineOffset + ':' + message);
                                }
                            }

                            public void error(String message, String sourceName,
                                              int line, String lineSource, int lineOffset) {
                                if (line < 0) {
                                    log.error("\n[ERROR] " + message);
                                } else {
                                    log.error("\n[ERROR] " + line + ':' + lineOffset + ':' + message);
                                }
                            }

                            public EvaluatorException runtimeError(String message, String sourceName,
                                                                   int line, String lineSource, int lineOffset) {
                                error(message, sourceName, line, lineSource, lineOffset);
                                return new EvaluatorException(message);
                            }
                        });
                        in.close();
                        in = null;
                        compressor.compress(out, -1, munge, verbose, preserveAllSemiColons, disableOptimizations);
                    }
                }
            }
            out.flush();
            return true;
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 将css文件里的图片相对路径修改为绝对路径
     *
     * @return
     */
    private static String repairCss(String content, String path) {
        Pattern p = Pattern.compile("url\\([\\s]*['\"]?((?!['\"]?https?://|['\"]?data:|['\"]?/).*?)['\"]?[\\s]*\\)"); // 感谢Code Life(程式人生)的正则
        Matcher m = p.matcher(content);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String url = m.group(1).trim();
            StringBuffer cssPath = new StringBuffer("url(").append(FilenameUtils.getFullPath(path)).append(url).append(")");
            m.appendReplacement(sb, cssPath.toString());
        }
        m.appendTail(sb);
        content = sb.toString();
        return content;
    }

    /**
     * 压缩工具
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String combo(String fileName) throws IOException {
        String rootPath = PathKit.getWebRootPath();
        String comboName = fileName.substring(0, fileName.indexOf('.')); // /assets/assets-web
        // 读取文件中的js或者css路径
        List<String> list = FileUtils.readLines(new File(rootPath + fileName), CHARSET);
        StringBuilder sb = new StringBuilder(); // 文件更改时间拼接
        for (String string : list) {
            if (StrKit.isBlank(string)) {
                continue;
            }
            String filePath = PathKit.getWebRootPath() + string;
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(file.getName() + " not found...");
            }
            sb.append(file.lastModified());
        }
        // 文件更改时间集合hex
        String hex = DigestUtils.md5Hex(sb.toString());

        boolean isCss = true;
        if (fileName.endsWith(".jjs")) {
            isCss = false;
        }
        String newFileName = comboName + '-' + hex + (isCss ? CSS_EXT : JS_EXT);

        String newPath = rootPath + newFileName;
        File file = new File(newPath);
        // 判断文件是否已存在，已存在直接返回
        if (file.exists()) {
            return newFileName;
        }
        // 将合并的结果写入文件
        Writer out = new OutputStreamWriter(new FileOutputStream(newPath), CHARSET);
        compressorHelper(list, isCss, out);
        return newFileName;
    }
}