
#JFinal 2.1 来袭,用JFinal开发，就这么定了！

1：添加BaseModelGenerator，极速合体Model与传统java bean
2：添加MappingKitGenerator，极速映射table与model，极速配置主键/复合主键
3：添加ModelGenerator，极速生成Model
4：添加DataDictionaryGenerator，极速生成数据字典
5：添加json模块，极速json与object互转。提供jfinal、jackson、fastjson三种json实现，按需选用
6：增强Clear注解，极速支持类级别拦截器清除
7：添加Controller.renderCaptcha()，极速验证码渲染
8：添加Validator.validateCaptcha()、Controller.validateCaptcha()极速校验验证码
9：添加Db.batchSave()、batchUpdate()，极速批量插入/更新数据
10：增强Controller.getModel()、keepModel()，支持传入""作为 modelName，极速友好支持纯API项目
11：添加ActiveRecordPlugin.useAsDataTransfer()极速支持Model在分布式场景下承载/传输数据，极速支持在无数据源场景下使用Model
12：添加Json、IJsonFactory抽象，极速扩展自己喜爱的json实现
13：改进所有paginate()方法，合并select与sqlExceptSelect参数，并兼容老版本用法。改进正则对order by、group by支持更完备
14：改进Controller.setCookie()系列方法，添加isHttpOnly参数，方便保护敏感cookie值不被js读取
15：增强文件上传功能，支持设置baseUploadPath在项目根路径之外，便于支持单机多实例共享上传目录
16：增强文件下载功能，支持设置baseDownloadPath在项目根路径之外，便于支持单机多实例共享上传目录
17：改进ActiveRecord，默认事务隔离级别提升为Connection.TRANSACTION_REPEATABLE_READ
18：增强TypeConverter，对Timestamp类型转换时根据字符串长度选择转换pattern，添加对boolean、float、double三种primitive类型的转换支持以便更适合传统java bean应用场景
19：添加StrKit.toCamelCase()、支持下划线命名转化为驼峰命名
20：增强I18nInterceptor，支持404、500页面国际化
21：重构log模块，所有Logger更名为Log,所有getLogger()更名为getLog()
22：优化、重构HashKit，提升性能，缩减代码量
23：改进Json转换规则，避免对"/"字符的转义，与jackson、fastjson转换行为一致
24：改进TxByRegex，将其拆分成TxByActionKeyRegex与TxByMethodRegex，语义更加明确
25：改进Prop.getBoolean()添加对value值前后空格的处理，避免开发者粗心带来难以发现的错误
26：添加Controller.setHttpServletRequest()、setHttpServletResponse()便于拦截注入做更深扩展
27：改进Db.delete(String, String, Record)支持复合主键参数
28：添加Constants.setReportAfterInvocation()设置Action Report时机，默认值为true，有利于纯API项目
29：添加Controller.getBean()、keepBean()系列方法，支持传统java bean以及合体后的Model，有利于无数据源使用Model的场景
30：JFinalConfig.loadPropertyFile()直接使用Prop，避免影响PropKit，删除unloadAllPropertyFiles() 方法
31：改进OracleDialect.fillStatement()添加对Timestamp的判断
32：改进Validator，其内部属性全部改为protected可见性，便于继承扩展
33：添加Handler.next属性代替nextHandler，原属性被Deprecated
34：添加CaptchaRender，极速生成更加美观安全的验证码
35：改进Controller.getModel()系列方法，添加skipConvertError参数，便于在获取model时跳过无法转换的字段
36：添加LogKit支持在任意地方快捷使用日志
37：改进Model，getAttrNames、getAttrsEntrySet、getAttrValues、setAttrs方法名添加 "_"前缀，便于和getter、setter区分开来
38：添加FreeMarkerRender.setTemplateLoadingPath()，支持指定freemarker模板基础路径
39：改进HttpKit，支持非Oracle JDK，readIncommingRequestData()更名为 readData()、添加 setCharSet()方法
40：添加IBean接口，标记识别Model继承自BaseModel带有getter、setter
41：改进Redis Cache，添加对hash、set数据结构的field字段转换，支持field字段使用任意类型。改进支持通过继承Cache实现自定义Cache。
42：添加Constants.setJsonFactory()方法，用于切换json实现
43：改进Constants，setUploadedFileSaveDirectory()更名为setBaseUploadPath()，setFileRenderPath()更名为setBaseDownloadPath()
44：添加Constants.setXmlRenderFactory()用于切换XmlRender工厂实现类
45：改进ActionReporter，可设置JFinal Action Report时机
46：添加IXmlRenderFactory接口，支持Xml Render实现类的切换
47：添加InterceptorManager，支持缓存类级别拦截器栈
48：添加FreeMarkerRender.getContentType()，有利于重用freemarker，并利用其重构XmlRender，缩减代码量
49：改进SqlServerDialect，避免将sql转成小写字母，更好支持model字段取值
50：改进CacheInterceptor，支持未调用render时的场景
51：改进Redirect301Render，支持对已有queryString进行追加
52：改进异常处理，对所有catch块中忽略的异常添加适当处理，例如添加日志
53：添加ActiveRecordPlugin.setPrimaryKey()用于强制指定主键名或复合主键名及其次序
54：优化com.jfinal.aop.Callback，提升性能
55：重构合并InterceptorBuilder、ActionInterceptorBuilder，去除冗余代码
56：改进Controller.renderJson(Object)，识别JsonRender类型对象，避免对其进行toJson转换
57：改进Controller中cookie操作，默认不设置domain与path值
58：改进Model.set()使Model可在未启动ActiveRecordPlugin场景下充当java bean使用
59：添加RedisInterceptor.getCache()，便于继承扩展切换Cache
60：改进FileRender，支持baseDownloadPath为应用以外的路径
61：添加Constants.setJsonDatePattern()，支持配置json转换Date类型时的pattern
62：I18nInterceptor.getLocalPara()更名为getLocalParaName()
63：c3p0升至0.9.5.1版本
64：删除 StringKit、NullLoggerFactory

