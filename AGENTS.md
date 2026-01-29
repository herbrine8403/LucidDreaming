# Lucid Dreaming 项目指南

## 项目概述

**Lucid Dreaming（清醒梦）** 是一个 Minecraft 1.12.2 Forge Mod，通过内置的 HTTP 服务器实时展示游戏信息，并提供模块化游戏辅助功能。玩家可以在浏览器中查看游戏状态并控制各种游戏模块。项目支持现代化的 Web 界面设计，包含配置编辑器和模块控制面板。

- **项目名称**：Lucid Dreaming
- **版本**：1.0.0
- **作者**：Drwei
- **Minecraft 版本**：1.12.2
- **Forge 版本**：14.23.5.2847
- **编程语言**：Java 8
- **构建工具**：Gradle (ForgeGradle 2.3-SNAPSHOT)
- **最新更新**：2026-01-28

## 项目架构

### 源代码结构

```
src/main/java/com/luciddreaming/
├── LucidDreaming.java          # 主类，Mod 入口点
├── config/
│   ├── ModConfig.java          # Mod 配置管理（端口、绑定地址、网页设置等）
│   └── ModuleConfigs.java      # 模块配置管理（各模块的详细配置）
├── http/
│   ├── HTTPServer.java         # HTTP 服务器实现
│   ├── ModuleAPIHandler.java   # 模块 API 处理器
│   ├── ModuleConfigAPIHandler.java  # 模块配置 API 处理器
│   ├── ConfigTemplate.java     # 配置编辑器 HTML 模板
│   └── WebTemplate.java        # 主界面 HTML 模板
├── info/
│   └── GameInfoCollector.java  # 游戏信息收集器
├── modules/
│   ├── Module.java             # 模块抽象基类
│   ├── ModuleManager.java      # 模块管理器
│   ├── ModuleCategory.java     # 模块类别枚举
│   ├── AutoFish.java           # 自动钓鱼模块
│   ├── AutoClicker.java        # 自动点击器模块
│   ├── AutoKill.java           # 自动攻击模块
│   ├── AutoWalk.java           # 自动行走模块（带 A* 寻路）
│   ├── AntiKick.java           # 防踢出模块
│   ├── NoRender.java           # 渲染控制模块
│   └── FakeBlackScreen.java    # 假黑屏模块
├── pathfinding/
│   ├── AStarPathFinder.java    # A* 寻路算法实现
│   ├── MovementHelper.java     # 移动辅助工具
│   ├── Path.java               # 路径数据结构
│   ├── PathNode.java           # 路径节点
│   ├── IGoal.java              # 目标接口
│   ├── GoalBlock.java          # 方块目标
│   └── GoalXZ.java             # XZ 平面目标
├── utils/
│   ├── Keybind.java            # 按键绑定工具类
│   ├── ScreenshotUtils.java    # 截图工具类
│   ├── DirectMainThreadExecutor.java  # 主线程执行器
│   └── MainThreadTaskQueue.java       # 主线程任务队列
└── proxy/
    ├── CommonProxy.java        # 通用代理
    └── ClientProxy.java        # 客户端代理
```

### 核心模块

#### 1. LucidDreaming (主类)
- Mod 生命周期管理
- 初始化 HTTP 服务器和模块管理器
- 加载配置文件
- 日志记录

#### 2. config.ModConfig
- 配置文件管理（`config/luciddreaming.cfg`）
- 支持配置项：
  - HTTP 服务器端口（默认：1122）
  - 绑定地址（默认：0.0.0.0）
  - 局域网访问开关（默认：true）
  - 网页界面设置（截图、主题、自动刷新间隔等）

#### 3. config.ModuleConfigs
- 模块配置文件管理（`config/Lucid Dreaming_modules.cfg`）
- 每个模块都有独立的配置类和配置项：
  - AutoFish：重抛延迟、多竿、不损坏
  - AutoClicker：点击模式、CPS、CPS 波动
  - AutoKill：目标设置、攻击范围、攻击速度、CPS 波动、未命中概率、旋转设置等
  - AntiKick：跳跃、挥手、潜行、旋转、移动等动作及间隔设置
  - AutoWalk：速度、允许破坏/放置方块、最大寻路距离、最大寻路时间
  - NoRender：隐藏实体、粒子、天气、天空、雾气
  - FakeBlackScreen：不透明度

#### 4. http.HTTPServer
- 内置 HTTP 服务器实现（使用 `com.sun.net.httpserver`）
- 提供八个端点：
  - `/` - HTML 主界面（包含模块控制面板）
  - `/config` - 配置编辑器界面
  - `/api/info` - 纯文本信息
  - `/api/json` - JSON 数据
  - `/api/screenshot` - 截图功能
  - `/api/modules` - 模块管理 API
  - `/api/config` - 模块配置 API
  - `/api/autowalk` - AutoWalk 控制 API

#### 5. http.ModuleAPIHandler
- 模块 API 处理器
- `GET /api/modules` - 获取所有模块列表和状态
- `POST /api/modules/{name}` - 切换模块状态（支持 enable/disable/toggle）

#### 6. http.ModuleConfigAPIHandler
- 模块配置 API 处理器
- `GET /api/config` - 获取所有模块配置
- `GET /api/config/{moduleName}` - 获取指定模块配置
- `POST /api/config/{moduleName}` - 更新指定模块配置

#### 7. modules.ModuleManager
- 模块管理器，负责：
  - 注册所有模块
  - 管理模块生命周期
  - 在客户端 tick 中调用已启用模块的 onTick 方法

#### 8. modules.Module (抽象基类)
- 模块抽象基类，定义了所有模块的通用功能：
  - 模块名称、描述、类别
  - 启用/禁用状态管理
  - 按键绑定
  - onEnable/onDisable 生命周期钩子
  - onTick 抽象方法（子类必须实现）

#### 9. modules.ModuleCategory
- 模块类别枚举：
  - Server - 服务器相关
  - Combat - 战斗相关
  - Movement - 移动相关
  - Player - 玩家相关
  - Render - 渲染相关
  - Misc - 杂项

#### 10. pathfinding 包（新增）
完整的 A* 寻路系统实现：
- **AStarPathFinder**：A* 寻路算法核心实现，支持动态代价计算
- **MovementHelper**：移动辅助工具，判断方块可通过性、可破坏性、可放置性
- **Path**：路径数据结构，存储完整路径信息
- **PathNode**：路径节点，包含位置、代价等信息
- **IGoal**：目标接口
- **GoalBlock**：方块目标（精确坐标）
- **GoalXZ**：XZ 平面目标（仅水平坐标）

#### 11. 具体模块实现

##### AutoFish (自动钓鱼)
- 自动检测鱼钩状态
- 自动收杆和重抛
- 支持多竿和不损坏配置
- 可配置重抛延迟

##### AutoClicker (自动点击器)
- 支持左右键自动点击
- 可配置点击模式（左键/右键/两者都）
- 可配置 CPS（每秒点击次数）
- 支持 CPS 波动以避免检测

##### AutoKill (自动攻击)
- 自动攻击范围内的目标
- 支持多种目标类型：玩家、敌对生物、被动生物
- 支持攻击范围和穿墙范围配置
- 支持攻击速度配置
- 支持 CPS 波动和未命中概率（反作弊）
- 支持旋转模式（始终/击中时/无）
- 支持目标优先级设置（最近/最低生命值/最近角度）
- 支持过滤命名生物和驯服生物

##### AutoWalk (自动行走) - 新增
- 使用 A* 寻路算法自动走到目标位置
- 支持破坏和放置方块（可配置）
- 可配置行走速度
- 可配置最大寻路距离和时间
- 支持动态寻路（在后台线程运行）
- 支持方块破坏和放置的成本计算

##### AntiKick (防踢出)
- 防止因挂机被服务器踢出
- 支持多种动作：跳跃、挥手、潜行、旋转、移动
- 可配置动作间隔和随机变化
- 可配置潜行时间和旋转速度

##### NoRender (渲染控制)
- 隐藏实体、粒子、天气、天空、雾气
- 提升性能或视觉效果

##### FakeBlackScreen (假黑屏)
- 模拟黑屏效果
- 可配置不透明度

#### 12. utils.DirectMainThreadExecutor (新增)
- 主线程执行器，使用 Minecraft 的调度机制
- 提供可靠的主线程任务执行
- 支持同步等待任务完成

#### 13. utils.MainThreadTaskQueue (新增)
- 主线程任务队列
- 支持无返回值和有返回值的任务
- 线程安全的任务提交和处理

#### 14. info.GameInfoCollector
- 收集游戏信息：
  - 玩家信息（名称、生命值、饥饿度、经验、位置、维度、游戏模式）
  - 游戏信息（版本、运行时长、FPS）
  - 服务器信息（类型、地址、名称）
  - 计分板内容
  - 系统信息（操作系统、Java 版本）

#### 15. utils.Keybind
- 按键绑定工具类
- 封装 Minecraft 的 KeyBinding
- 提供按键检测和名称获取

#### 16. utils.ScreenshotUtils
- 截图工具类
- 支持可配置的截图质量
- 支持自动保存到文件
- 返回 PNG 或 JPEG 格式

#### 17. proxy 包
- CommonProxy：服务器端和客户端通用逻辑
- ClientProxy：客户端特定逻辑，负责注册模块

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

项目使用 ForgeGradle 插件，使用 Google Gson 进行 JSON 处理。

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

### 模块开发规范

- 所有模块必须继承 `Module` 抽象类
- 在构造函数中指定模块名称、描述和类别
- 实现 `onTick()` 方法（每帧调用）
- 可选实现 `onEnable()` 和 `onDisable()` 生命周期方法
- 在 `ClientProxy` 中注册模块

### 线程安全规范

- 使用 `DirectMainThreadExecutor` 在主线程执行任务
- 使用 `MainThreadTaskQueue` 提交主线程任务
- 避免在非主线程直接操作 Minecraft 对象

## 核心功能

### 1. HTTP 服务器

Mod 启动时自动启动 HTTP 服务器，监听配置的端口（默认 1122）。

**访问地址**：
- 主界面：`http://localhost:1122`
- 配置编辑器：`http://localhost:1122/config`
- 局域网：`http://[设备IP]:1122`

### 2. API 端点

#### GET `/`
返回美观的 HTML 主界面，包含：
- 游戏信息展示（玩家、游戏、服务器、计分板）
- 模块控制面板
- 截图功能
- 主题切换（浅色/深色/跟随系统）
- 语言切换（中文/English）
- 自动刷新

#### GET `/config`
返回配置编辑器界面，包含：
- 模块选择器
- 配置表单（支持多种输入类型）
- 实时配置更新
- 现代化极简 UI 设计

#### GET `/api/info`
返回纯文本格式的游戏信息。

#### GET `/api/json`
返回 JSON 格式的游戏信息，可用于自定义应用。

#### GET `/api/screenshot`
返回当前游戏画面的截图（PNG/JPEG 格式）。

#### GET `/api/modules`
返回所有模块的列表和状态（JSON 格式）：
```json
{
  "modules": [
    {
      "name": "AutoFish",
      "description": "自动钓鱼",
      "category": "Player",
      "enabled": false,
      "keybind": "NONE"
    }
  ],
  "categories": ["Server", "Combat", "Movement", "Player", "Render", "Misc"]
}
```

#### POST `/api/modules/{name}`
切换模块状态，支持三种操作：
- `toggle` - 切换状态
- `enable` - 启用模块
- `disable` - 禁用模块

请求体示例：
```json
{
  "action": "toggle"
}
```

#### GET `/api/config`
返回所有模块配置（JSON 格式）。

#### GET `/api/config/{moduleName}`
返回指定模块的配置（JSON 格式）。

#### POST `/api/config/{moduleName}`
更新指定模块的配置。请求体包含要更新的字段和值。

#### GET `/api/autowalk`
获取 AutoWalk 模块的状态（JSON 格式）：
```json
{
  "enabled": false,
  "hasTarget": true,
  "pathfinding": false,
  "target": {
    "x": 100,
    "y": 64,
    "z": 200
  },
  "hasPath": true,
  "pathLength": 42
}
```

#### POST `/api/autowalk`
设置 AutoWalk 目标或清除目标。支持的参数：
- `x`, `y`, `z` - 目标坐标
- `action` - 操作类型（可选，"clear" 表示清除目标）

### 3. 配置系统

#### 主配置文件
配置文件位置：`config/luciddreaming.cfg`

```ini
# HTTP 服务器端口（默认：1122）
I:HTTP Server Port=1122

# 绑定地址（默认：0.0.0.0，表示所有网络接口）
S:Bind Address=0.0.0.0

# 是否允许局域网访问（默认：true）
B:Enable LAN Access=true

# 是否启用 Web 服务器
B:Enable Web Server=true

# 网页界面设置
B:Enable Screenshot=true
D:Screenshot Quality=0.8
I:Theme Mode=0
I:Auto Refresh Interval=1000
B:Enable Module Control=true
```

#### 模块配置文件
配置文件位置：`config/Lucid Dreaming_modules.cfg`

**AutoFish 模块配置：**
```ini
AutoFish {
  B:Enabled=false
  I:Keybind=0
  I:Recast Delay=1500
  B:Multi Rod=false
  B:No Break=false
}
```

**AutoClicker 模块配置：**
```ini
AutoClicker {
  B:Enabled=false
  I:Keybind=0
  I:Click Mode=0
  I:Left CPS=8
  I:Right CPS=4
  B:CPS Fluctuation=true
  D:Fluctuation Amount=1.0
}
```

**AutoKill 模块配置：**
```ini
AutoKill {
  B:Enabled=false
  I:Keybind=0
  B:Target Players=true
  B:Target Hostile Mobs=true
  B:Target Passive Mobs=false
  D:Range=4.5
  D:Walls Range=3.5
  I:Max Targets=1
  I:Priority=0
  B:Ignore Named=false
  B:Ignore Tamed=false
  I:Mob Age Filter=2
  I:Rotation Mode=0
  D:Rotation Speed=0.5
  D:Attack Speed=8.0
  B:CPS Fluctuation=true
  D:Fluctuation Amount=1.0
  D:Miss Chance=0.05
  B:Only On Click=false
  B:Only On Look=false
  B:Require Weapon=false
}
```

**AntiKick 模块配置：**
```ini
AntiKick {
  B:Enabled=false
  I:Keybind=0
  B:Jump=true
  B:Swing=false
  B:Sneak=false
  I:Sneak Time=5
  B:Rotate=true
  I:Rotate Speed=7
  B:Move=false
  D:Move Distance=0.1
  I:Interval=300
  B:Random Interval=true
  I:Max Random Variation=50
}
```

**AutoWalk 模块配置：**
```ini
AutoWalk {
  B:Enabled=false
  I:Keybind=0
  D:Speed=1.0
  B:Allow Break=false
  B:Allow Place=false
  D:Break Block Cost=10.0
  D:Place Block Cost=10.0
  I:Max Pathfinding Distance=200
  I:Max Pathfinding Time=10000
}
```

**NoRender 模块配置：**
```ini
NoRender {
  B:Enabled=false
  I:Keybind=0
  B:Hide Entities=false
  B:Hide Particles=false
  B:Hide Weather=false
  B:Hide Sky=false
  B:Hide Fog=false
}
```

**FakeBlackScreen 模块配置：**
```ini
FakeBlackScreen {
  B:Enabled=false
  I:Keybind=0
  D:Opacity=1.0
}
```

### 4. 模块系统

#### 模块生命周期
1. **注册**：在 `ClientProxy.registerModules()` 中注册模块
2. **初始化**：模块管理器初始化所有模块
3. **启用**：通过按键绑定或 API 调用启用模块
4. **运行**：每帧调用 `onTick()` 方法
5. **禁用**：通过按键绑定或 API 调用禁用模块

#### 按键绑定
- 每个模块都有独立的按键绑定
- 可以在配置文件中修改按键代码
- 按下按键时自动切换模块状态

#### 模块分类
- **Server**：服务器相关功能
- **Combat**：战斗辅助功能
- **Movement**：移动辅助功能
- **Player**：玩家辅助功能
- **Render**：渲染相关功能
- **Misc**：其他功能

### 5. Web 界面特性

#### 现代化设计
- 采用极简主义设计风格
- 响应式布局，支持移动设备
- 流畅的动画和过渡效果

#### 主题支持
- 浅色主题
- 深色主题
- 跟随系统主题

#### 国际化
- 中文界面
- English interface
- 实时语言切换

#### 配置编辑器
- 可视化配置编辑
- 支持多种输入类型（文本、数字、复选框、滑块）
- 实时配置同步
- 配置验证和错误提示

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
   - 测试主题切换（浅色/深色/跟随系统）
   - 测试语言切换（中文/English）
   - 测试截图功能

5. **测试模块控制面板**：
   - 检查所有模块是否正确显示
   - 测试模块开关功能
   - 测试模块状态更新

6. **测试配置编辑器**：
   - 访问 `http://localhost:1122/config`
   - 测试模块选择器
   - 测试配置表单
   - 测试配置更新功能
   - 验证配置是否正确保存

7. **测试 API 端点**：
   - 访问 `http://localhost:1122/api/info` 查看纯文本输出
   - 访问 `http://localhost:1122/api/json` 查看 JSON 输出
   - 访问 `http://localhost:1122/api/modules` 查看模块列表
   - 测试 POST `/api/modules/{name}` 切换模块状态
   - 测试 GET `/api/config` 和 GET `/api/config/{moduleName}`
   - 测试 POST `/api/config/{moduleName}` 更新配置
   - 测试 GET `/api/autowalk` 和 POST `/api/autowalk`

8. **测试局域网访问**：
   - 在同一网络的其他设备上访问 `http://[IP]:1122`

9. **测试模块功能**：
   - 为每个模块绑定按键
   - 在游戏中测试每个模块的功能
   - 检查模块配置是否生效

10. **测试 AutoWalk 模块**：
    - 启用 AutoWalk 模块
    - 通过 API 设置目标坐标
    - 观察玩家是否自动走到目标位置
    - 测试寻路功能（包括障碍物处理）
    - 测试破坏和放置方块功能（如果启用）

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

### 模块无法启用

**问题**：模块开关无反应

**解决方案**：
1. 检查按键绑定是否正确
2. 检查日志中是否有错误信息
3. 确认模块是否已正确注册

### AutoWalk 寻路失败

**问题**：AutoWalk 无法找到路径

**解决方案**：
1. 检查目标距离是否超过最大寻路距离配置
2. 检查是否启用了破坏/放置方块功能
3. 检查路径中是否有不可破坏的障碍物
4. 增加最大寻路时间配置

### 配置更改未生效

**问题**：通过 Web 界面修改配置后未生效

**解决方案**：
1. 检查配置文件权限
2. 查看日志中是否有配置同步错误
3. 重启游戏以重新加载配置

## 扩展开发

### 添加新模块

1. **创建模块类**：
   ```java
   package com.luciddreaming.modules;

   import com.luciddreaming.LucidDreaming;
   import net.minecraft.client.Minecraft;
   import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
   import net.minecraftforge.fml.common.gameevent.TickEvent;

   public class MyModule extends Module {
       private final Minecraft mc = Minecraft.getMinecraft();

       public MyModule() {
           super("MyModule", "My custom module", ModuleCategory.MISC, 0);
       }

       @Override
       protected void onEnable() {
           LucidDreaming.LOGGER.info("MyModule enabled");
       }

       @Override
       protected void onDisable() {
           LucidDreaming.LOGGER.info("MyModule disabled");
       }

       @Override
       public void onTick() {
           // 模块逻辑
           if (mc.world == null || mc.player == null) {
               return;
           }
           // 实现模块功能
       }
   }
   ```

2. **注册模块**：
   在 `ClientProxy.java` 中添加：
   ```java
   @Override
   public void registerModules(ModuleManager moduleManager) {
       super.registerModules(moduleManager);
       moduleManager.registerModule(new MyModule());
   }
   ```

3. **添加模块配置**（可选）：
   在 `ModuleConfigs.java` 中添加：
   ```java
   @Config.Name("我的模块")
   @Config.Comment("我的模块设置")
   public static MyModule myModule = new MyModule();

   public static class MyModule {
       @Config.Name("启用")
       @Config.Comment("启用或禁用我的模块")
       public boolean enabled = false;

       @Config.Name("快捷键")
       @Config.Comment("切换我的模块的快捷键")
       public int keybind = 0;

       // 添加其他配置项
   }
   ```

4. **更新 ModuleConfigAPIHandler**（如果需要 Web 配置）：
   在 `ModuleConfigAPIHandler.java` 的 `getModuleConfig` 方法中添加：
   ```java
   case "mymodule":
       return ModuleConfigs.myModule;
   ```

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

- 修改 `WebTemplate.java` 中的 HTML 模板以更改主界面
- 修改 `ConfigTemplate.java` 中的 HTML 模板以更改配置编辑器界面

### 实现自定义寻路目标

1. 实现 `IGoal` 接口：
   ```java
   public class MyCustomGoal implements IGoal {
       @Override
       public BlockPos getGoalPos() {
           // 返回目标位置
       }

       @Override
       public boolean isInGoal(int x, int y, int z) {
           // 判断坐标是否在目标范围内
       }
   }
   ```

2. 在 AutoWalk 模块中使用：
   ```java
   public void setCustomGoal(MyCustomGoal goal) {
       this.goal = goal;
       this.hasTarget = true;
   }
   ```

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
4. 更新 AGENTS.md 中的版本信息
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
- [A* Pathfinding Algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm)

## 许可证

本项目采用 MIT 许可证。

## 联系方式

- 作者：Drwei
- GitHub：[项目仓库地址](https://github.com/herbrine8403/LucidDreaming)

---

**最后更新**：2026-01-28