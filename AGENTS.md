# Lucid Dreaming 项目指南

## 项目概述

**Lucid Dreaming（清醒梦）** 是一个 Minecraft 1.12.2 Forge Mod，通过内置的 HTTP 服务器实时展示游戏信息，让玩家可以在浏览器中查看游戏状态。

- **项目名称**：Lucid Dreaming
- **版本**：1.0.0
- **作者**：Drwei
- **Minecraft 版本**：1.12.2
- **Forge 版本**：14.23.5.2847
- **编程语言**：Java 8
- **构建工具**：Gradle (ForgeGradle 2.3-SNAPSHOT)

## 项目架构

### 源代码结构

```
src/main/java/com/luciddreaming/
├── LucidDreaming.java          # 主类，Mod 入口点
├── config/
│   └── ModConfig.java          # 配置管理（端口、绑定地址等）
├── http/
│   ├── HTTPServer.java         # HTTP 服务器实现
│   └── WebTemplate.java        # HTML 网页模板生成
├── info/
│   └── GameInfoCollector.java  # 游戏信息收集器
└── proxy/
    ├── CommonProxy.java        # 通用代理
    └── ClientProxy.java        # 客户端代理
```

### 核心模块

#### 1. LucidDreaming (主类)
- Mod 生命周期管理
- 初始化 HTTP 服务器
- 加载配置文件
- 日志记录

#### 2. config.ModConfig
- 配置文件管理（`config/luciddreaming.cfg`）
- 支持配置项：
  - HTTP 服务器端口（默认：1122）
  - 绑定地址（默认：0.0.0.0）
  - 局域网访问开关（默认：true）

#### 3. http.HTTPServer
- 内置 HTTP 服务器实现（使用 `com.sun.net.httpserver`）
- 提供三个端点：
  - `/` - HTML 界面
  - `/api/info` - 纯文本信息
  - `/api/json` - JSON 数据

#### 4. info.GameInfoCollector
- 收集游戏信息：
  - 玩家信息（名称、生命值、饥饿度、经验、位置、维度、游戏模式）
  - 游戏信息（版本、运行时长、FPS）
  - 服务器信息（类型、地址、名称）
  - 计分板内容
  - 系统信息（操作系统、Java 版本）

#### 5. proxy 包
- CommonProxy：服务器端和客户端通用逻辑
- ClientProxy：客户端特定逻辑

## 开发环境配置

### 系统要求

- **Java 版本**：Java 8（必需，用于编译）
- **Minecraft 版本**：1.12.2
- **Forge 版本**：14.23.5.2847
- **操作系统**：Windows/Linux/macOS/Android（通过 PojavLauncher）

### 环境变量设置

```bash
# 设置 Java 8 环境变量
export JAVA_HOME=/path/to/java8
export PATH=$JAVA_HOME/bin:$PATH
```

### 依赖项

项目使用 ForgeGradle 插件，无需额外依赖项。

## 构建命令

### 基本命令

```bash
# 赋予 gradlew 执行权限
chmod +x gradlew

# 构建项目
./gradlew build

# 清理构建产物
./gradlew clean

# 清理并重新构建
./gradlew clean build

# 生成开发环境
./gradlew setupDecompWorkspace

# 编译 Java 源码
./gradlew compileJava

# 运行游戏客户端（开发模式）
./gradlew runClient

# 运行游戏服务器（开发模式）
./gradlew runServer
```

### 构建产物

构建成功后，JAR 文件位于：
```
build/libs/luciddreaming-1.0.0.jar
build/libs/luciddreaming-1.0.0-sources.jar
```

## 开发规范

### 代码风格

- **缩进**：4 空格
- **命名约定**：
  - 类名：PascalCase（如 `ModConfig`）
  - 方法名：camelCase（如 `loadConfig`）
  - 常量：UPPER_SNAKE_CASE（如 `MODID`）
  - 变量：camelCase
- **包命名**：全小写（如 `com.luciddreaming.http`）

### 注释规范

- 类和方法应有清晰的 Javadoc 注释
- 复杂逻辑应有行内注释说明
- 使用 `//` 进行单行注释，`/* */` 进行多行注释

### 日志规范

使用 Log4j2 记录日志：
```java
LucidDreaming.LOGGER.info("Info message");
LucidDreaming.LOGGER.warn("Warning message");
LucidDreaming.LOGGER.error("Error message", exception);
```

### Forge Mod 开发规范

- 使用 `@Mod` 注解标记主类
- 使用 `@Mod.EventHandler` 标记事件处理方法
- 使用 `@SidedProxy` 区分客户端和服务端代码
- 使用 `@SideOnly` 标记客户端专用方法

## 核心功能

### 1. HTTP 服务器

Mod 启动时自动启动 HTTP 服务器，监听配置的端口（默认 1122）。

**访问地址**：
- 本地：`http://localhost:1122`
- 局域网：`http://[设备IP]:1122`

### 2. API 端点

#### GET `/`
返回美观的 HTML 界面，自动刷新显示游戏信息。

#### GET `/api/info`
返回纯文本格式的游戏信息。

#### GET `/api/json`
返回 JSON 格式的游戏信息，可用于自定义应用。

### 3. 配置系统

配置文件位置：`config/luciddreaming.cfg`

```ini
# HTTP 服务器端口（默认：1122）
I:HTTP Server Port=1122

# 绑定地址（默认：0.0.0.0，表示所有网络接口）
S:Bind Address=0.0.0.0

# 是否允许局域网访问（默认：true）
B:Enable LAN Access=true
```

## 测试指南

### 手动测试

1. **构建 Mod**：
   ```bash
   ./gradlew build
   ```

2. **安装 Mod**：
   - 将 `build/libs/luciddreaming-1.0.0.jar` 复制到 Minecraft 的 `mods` 文件夹

3. **启动游戏**：
   - 启动 Minecraft 1.12.2 Forge

4. **测试 HTTP 服务器**：
   - 在浏览器中访问 `http://localhost:1122`
   - 检查页面是否正常显示游戏信息
   - 测试自动刷新功能

5. **测试 API 端点**：
   - 访问 `http://localhost:1122/api/info` 查看纯文本输出
   - 访问 `http://localhost:1122/api/json` 查看 JSON 输出

6. **测试局域网访问**：
   - 在同一网络的其他设备上访问 `http://[IP]:1122`

### 调试

查看日志文件：
- 客户端：`.minecraft/logs/latest.log`
- 服务器：`logs/latest.log`

## 常见问题

### 编译错误

**问题**：Java 版本不匹配
```
* What went wrong:
Execution failed for task ':compileJava'.
> Compilation failed; see the compiler error output for details.
```

**解决方案**：
```bash
# 检查 Java 版本
java -version

# 切换到 Java 8
export JAVA_HOME=/path/to/java8
export PATH=$JAVA_HOME/bin:$PATH
```

### 端口被占用

**问题**：HTTP 服务器无法启动，端口已被占用

**解决方案**：
1. 修改配置文件 `config/luciddreaming.cfg` 中的端口号
2. 或停止占用该端口的进程

### 局域网无法访问

**问题**：其他设备无法访问 HTTP 服务器

**解决方案**：
1. 检查配置文件中的 `Bind Address` 是否为 `0.0.0.0`
2. 检查防火墙设置
3. 确保设备在同一网络

## 扩展开发

### 添加新的 API 端点

在 `HTTPServer.java` 中添加新的处理器：

```java
server.createContext("/api/custom", new CustomHandler());

static class CustomHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // 处理请求
    }
}
```

### 添加新的信息收集

在 `GameInfoCollector.java` 中添加新的字段：

```java
public static class GameInfo {
    // 添加新字段
    public String customField = "N/A";
}
```

### 自定义网页模板

修改 `WebTemplate.java` 中的 HTML 模板。

## 部署

### 发布到 CurseForge

1. 构建 JAR 文件
2. 创建 CurseForge 项目页面
3. 上传 JAR 文件
4. 填写版本信息和更新日志

### 发布到 Modrinth

1. 构建 JAR 文件
2. 创建 Modrinth 项目页面
3. 上传 JAR 文件
4. 填写版本信息和更新日志

## 维护

### 版本更新

1. 更新 `build.gradle` 中的版本号
2. 更新 `LucidDreaming.java` 中的 `VERSION` 常量
3. 更新 `mcmod.info` 中的版本信息
4. 更新 README.md 中的版本信息
5. 提交代码并创建新的 Git 标签

### 依赖更新

更新 Forge 版本：

```gradle
minecraft {
    version = "1.12.2-14.23.5.2847"  // 更新此版本号
}
```

## 贡献指南

### 提交代码

1. Fork 项目仓库
2. 创建功能分支：`git checkout -b feature/your-feature`
3. 提交更改：`git commit -m 'Add some feature'`
4. 推送到分支：`git push origin feature/your-feature`
5. 创建 Pull Request

### 代码审查

- 确保代码符合项目规范
- 添加必要的测试
- 更新相关文档
- 确保 CI/CD 通过

## 参考资料

- [Minecraft Forge Documentation](https://docs.minecraftforge.net/)
- [MCP mappings](https://mcp.thiakil.com/)
- [ForgeGradle Documentation](https://github.com/MinecraftForge/ForgeGradle)
- [Java 8 Documentation](https://docs.oracle.com/javase/8/docs/)

## 许可证

本项目采用 MIT 许可证。

## 联系方式

- 作者：Drwei
- GitHub：[项目仓库地址]

---

**最后更新**：2026-01-21