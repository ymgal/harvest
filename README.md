# harvest
Obtain galgame archive data.

### v2.0.0 / Jakarta 适配

- 需要 Java 17 或更高版本；模型改用 Jakarta Validation 3 注解，字段、级联规则和原错误消息保持不变。
- 库只传递 `jakarta.validation-api`，校验器由宿主提供；Spring Boot 3 宿主可使用 `spring-boot-starter-validation`。Hibernate Validator 8 仅用于本库测试。
- 删除未使用的旧 Persistence API；抓取逻辑、JSON 字段和公开方法未调整。
- 这是与旧 javax Validation 消费方不兼容的大版本，不覆盖原 v1.0.0 制品。

在 Java 17 环境下，先从本目录执行 `mvn clean install -Dmaven.test.skip=true`，宿主再引用：

```xml
<dependency>
    <groupId>com.ymgal</groupId>
    <artifactId>harvest</artifactId>
    <version>v2.0.0</version>
</dependency>
```

独立模型回归命令：`mvn test -DskipTests=false -Dtest=JakartaValidationCompatibilityTest`。用例不发起网络抓取，覆盖嵌套校验、数值范围、错误消息及可空/空集合语义。

已完成有效 POM、依赖树解析和源码差异检查；没有编译、安装或执行测试，以上命令是后续操作说明。其他开发机和 CI 需要先构建安装本版本，或从团队制品库获取。

<br>

### 注意
* 此项目使用openjdk运行可能出现证书问题（the trustAnchors parameter must be non-empty.）这是由于程序内访问SSL接口导致的。
