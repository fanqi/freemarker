package xyz.fanqi.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanqi on 16/2/21.
 */
public class ProductDemo {
    public static void main(String[] args) throws Exception {

        /* ------------------------------------------------------------------------ */
        /* 创建和调整单例配置
         * Configuration 实例是存储 FreeMarker 应用级设置的核心部分。
         * 同时，它也处理创建和 缓存 预解析模板(比如 Template 对象)的工作。
         * 在应用生命周期中应该只执行一次
         * */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File("/Users/fanqi/maven/freemarker/src/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        /* ------------------------------------------------------------------------ */

        /* 创建一个数据模型 */
        Map root = new HashMap();
        root.put("user", "Big Joe");
        Map latest = new HashMap();
        root.put("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");

        /* 获取模板(使用内部缓存) */
        Template temp = cfg.getTemplate("product.ftl");

        /* 合并数据模型和模板,并输出 */
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);
        out.close();
    }
}
