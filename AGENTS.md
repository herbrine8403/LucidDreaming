# Lucid Dreaming 项目指南

## 项目概述

**Lucid Dreaming（清醒梦）** 是一个 Minecraft 1.12.2 Forge Mod 生态系统，包含：
1. **Minecraft Mod** - 通过内置 HTTP 服务器实时展示游戏信息并提供模块化游戏辅助功能
2. **跨平台移动应用** - 使用 Kotlin Multiplatform 开发的 Android 和 iOS 应用，用于远程控制和管理 Minecraft Mod

玩家可以在浏览器或移动应用中查看游戏状态并控制各种游戏模块。项目支持现代化的 Web 界面设计和原生移动应用体验。

- **项目名称**：Lucid Dreaming
- **版本**：1.0.0
- **作者**：Drwei
- **Minecraft 版本**：1.12.2
- **Forge 版本**：14.23.5.2847
- **编程语言**：Java 8 (Mod)、Kotlin 1.9.20 (移动应用)、Swift (iOS UI)
- **构建工具**：Gradle (ForgeGradle 2.3-SNAPSHOT)、Gradle (Kotlin DSL)
- **最新更新**：2026-02-06

## 项目架构

### 整体架构

```
LucidDreaming/
├── mod/                          # Minecraft Mod (Java 8)
│   ├── src/main/java/com/luciddreaming/
│   │   ├── LucidDreaming.java    # Mod 主类
│   │   ├── config/               # 配置管理
│   │   ├── http/                 # HTTP 服务器和 API
│   │   ├── info/                 # 游戏信息收集
│   │   ├── modules/              # 游戏模块
│   │   ├── pathfinding/          # A* 寻路算法
│   │   ├── utils/                # 工具类
│   │   └── proxy/                # 代理类
│   └── build.gradle              # Mod 构建配置
│
├── shared/                       # KMP 共享代码 (Kotlin)
│   └── src/commonMain/kotlin/com/luciddreaming/shared/
│       └── data/
│           ├── api/              # HTTP API 客户端
│           │   ├── NetworkClient.kt
│           │   ├── GameInfoApi.kt
│           │   ├── ModuleApi.kt
│           │   └── ScreenshotApi.kt
│           ├── model/            # 数据模型
│           │   ├── GameInfo.kt
│           │   └── Module.kt
│           ├── repository/       # 数据仓库
│           │   └── GameInfoRepository.kt
│           └── preferences/      # 共享偏好设置
│               └── Preferences.kt
│
├── androidApp/                   # Android 应用 (Kotlin + Jetpack Compose)
│   ├── src/main/java/com/luciddreaming/android/
│   │   ├── MainActivity.kt       # 主 Activity
│   │   ├── ui/
│   │   │   ├── screens/          # UI 屏幕
│   │   │   │   ├── ConnectScreen.kt
│   │   │   │   ├── MonitorScreen.kt
│   │   │   │   ├── ModulesScreen.kt
│   │   │   │   ├── AutomationScreen.kt
│   │   │   │   ├── SettingsScreen.kt
│   │   │   │   └── AboutScreen.kt
│   │   │   └── theme/            # Material3 主题
│   │   └── viewmodel/            # ViewModel
│   └── build.gradle.kts          # Android 构建配置
│
└── iosApp/                       # iOS 应用 (Swift + SwiftUI)
    └── LucidDreaming/
        ├── AppDelegate.swift
        ├── SceneDelegate.swift
        ├── ContentView.swift
        ├── UI/                   # SwiftUI 屏幕
        │   ├── MainApp.swift
        │   ├── ConnectScreen.swift
        │   ├── MonitorScreen.swift
        │   ├── ModulesScreen.swift
        │   ├── AutomationScreen.swift
        │   ├── SettingsScreen.swift
        │   └── AboutScreen.swift
        └── ViewModels/           # ViewModel
            ├── ConnectionViewModel.swift
            ├── ModulesViewModel.swift
            ├── MonitorViewModel.swift
            └── SettingsViewModel.swift
```

### 技术栈

#### Minecraft Mod
- **语言**：Java 8
- **框架**：Minecraft Forge 14.23.5.2847
- **HTTP 服务器**：com.sun.net.httpserver（内置）
- **JSON 处理**：Google Gson
- **寻路算法**：A*（自定义实现）

#### KMP 共享模块
- **语言**：Kotlin 1.9.20
- **网络**：Ktor Client 2.3.7
- **异步**：Kotlinx Coroutines 1.7.3
- **序列化**：Kotlinx Serialization 1.6.0
- **平台**：Android (JVM)、iOS (Native)

#### Android 应用
- **语言**：Kotlin 1.9.20
- **UI 框架**：Jetpack Compose (Material3)
- **架构**：MVVM + Repository
- **导航**：Navigation Compose 2.7.6
- **图片加载**：Coil 2.5.0
- **最低 SDK**：24 (Android 7.0)
- **目标 SDK**：34 (Android 14)

#### iOS 应用
- **语言**：Swift
- **UI 框架**：SwiftUI
- **架构**：MVVM
- **最低版本**：iOS 15.0
- **依赖管理**：CocoaPods

## Minecraft Mod 模块

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
- 每个模块都有独立的配置类和配置项

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

#### 5. modules.ModuleManager
- 模块管理器，负责：
  - 注册所有模块
  - 管理模块生命周期
  - 在客户端 tick 中调用已启用模块的 onTick 方法

#### 6. modules.Module (抽象基类)
- 模块抽象基类，定义了所有模块的通用功能：
  - 模块名称、描述、类别
  - 启用/禁用状态管理
  - 按键绑定
  - onEnable/onDisable 生命周期钩子
  - onTick 抽象方法（子类必须实现）

#### 7. pathfinding 包
完整的 A* 寻路系统实现：
- **AStarPathFinder**：A* 寻路算法核心实现，支持动态代价计算
- **MovementHelper**：移动辅助工具，判断方块可通过性、可破坏性、可放置性
- **Path**：路径数据结构，存储完整路径信息
- **PathNode**：路径节点，包含位置、代价等信息
- **IGoal**：目标接口
- **GoalBlock**：方块目标（精确坐标）
- **GoalXZ**：XZ 平面目标（仅水平坐标）

#### 8. 具体模块实现

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

##### AutoWalk (自动行走)
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

## KMP 共享模块

### 数据模型

#### GameInfo
游戏信息数据模型：
```kotlin
@Serializable
data class GameInfo(
    val serverName: String,
    val version: String,
    val playerCount: Int,
    val uptime: Long,
    val mapName: String,
    val gameMode: String
)
```

#### Module
模块数据模型：
```kotlin
@Serializable
data class Module(
    val name: String,
    val displayName: String,
    val description: String,
    val enabled: Boolean,
    val category: String,
    val version: String
)
```

### API 客户端

#### NetworkClient
HTTP 客户端配置：
- 使用 Ktor Client
- 支持 JSON 序列化
- 配置超时和日志
- 支持动态更新基础 URL

#### GameInfoApi
游戏信息 API：
- 获取游戏信息（`/api/json`）

#### ModuleApi
模块管理 API：
- 获取模块列表（`/api/modules`）
- 切换模块状态（`POST /api/modules/{name}`）

#### ScreenshotApi
截图 API：
- 获取游戏截图（`/api/screenshot`）

### Repository

#### GameInfoRepository
游戏信息数据仓库：
- 封装 API 调用
- 提供数据缓存
- 错误处理

### Preferences

#### Preferences
共享偏好设置：
- 连接设置（服务器地址、端口）
- 应用设置（主题、语言）
- 使用平台特定的 DataStore 实现

## 移动应用架构

### Android 应用

#### UI 架构
- **Jetpack Compose** - 声明式 UI
- **Material3** - 现代化设计系统
- **Navigation Compose** - 屏幕导航
- **MVVM** - Model-View-ViewModel 架构

#### 屏幕说明

##### ConnectScreen (连接屏幕)
- 服务器地址输入
- 端口输入
- 连接状态显示
- 保存连接设置

##### MonitorScreen (监控屏幕)
- 实时游戏信息显示
- 玩家状态
- 服务器信息
- 游戏截图
- 自动刷新

##### ModulesScreen (模块屏幕)
- 模块列表（按类别分组）
- 模块开关
- 模块状态显示
- 搜索和过滤

##### AutomationScreen (自动化屏幕)
- 自动化任务管理
- 任务创建和编辑
- 任务执行状态

##### SettingsScreen (设置屏幕)
- 应用设置
- 连接设置
- 主题切换
- 断开连接

##### AboutScreen (关于屏幕)
- 应用信息
- 版本信息
- 许可证信息

#### ViewModel
- **ConnectionViewModel** - 连接状态管理
- **MonitorViewModel** - 游戏信息监控
- **ModulesViewModel** - 模块管理
- **SettingsViewModel** - 设置管理

### iOS 应用

#### UI 架构
- **SwiftUI** - 声明式 UI
- **MVVM** - Model-View-ViewModel 架构
- **响应式设计** - 支持 iPhone 和 iPad

#### 屏幕说明
iOS 应用提供与 Android 应用相同的功能：
- ConnectScreen - 连接屏幕
- MonitorScreen - 监控屏幕
- ModulesScreen - 模块屏幕
- AutomationScreen - 自动化屏幕
- SettingsScreen - 设置屏幕
- AboutScreen - 关于屏幕

#### 布局适配
- **iPhone** - 底部标签栏导航
- **iPad** - 侧边栏导航 + 主内容区域

#### ViewModel
- **ConnectionViewModel** - 连接状态管理
- **MonitorViewModel** - 游戏信息监控
- **ModulesViewModel** - 模块管理
- **SettingsViewModel** - 设置管理

## 开发环境配置

### 系统要求

#### Minecraft Mod 开发
- **Java 版本**：Java 8（必需）
- **Minecraft 版本**：1.12.2
- **Forge 版本**：14.23.5.2847
- **操作系统**：Windows/Linux/macOS

#### KMP 移动应用开发
- **JDK 版本**：JDK 8 或更高
- **Kotlin 版本**：1.9.20
- **Android Studio**：最新稳定版（用于 Android 开发）
- **Xcode**：15.0 或更高（用于 iOS 开发，仅 macOS）
- **Gradle**：8.2+
- **Kotlin 编译器**：1.9.20

### 环境变量设置

```bash
# Minecraft Mod - Java 8 环境变量
export JAVA_HOME=/path/to/java8
export PATH=$JAVA_HOME/bin:$PATH

# KMP - kotlinc（可选，用于命令行编译）
export PATH=$PATH:~/kotlin/kotlinc/bin
```

### 依赖项

#### Minecraft Mod
- ForgeGradle 2.3-SNAPSHOT
- Google Gson（JSON 处理）

#### KMP 共享模块
- Kotlin Multiplatform Plugin 1.9.20
- Kotlin Serialization Plugin 1.9.20
- Ktor Client 2.3.7
- Kotlinx Coroutines 1.7.3
- Kotlinx Serialization 1.6.0
- Android Gradle Plugin 8.2.0

#### Android 应用
- Jetpack Compose BOM 2024.02.00
- Compose Material3
- Navigation Compose 2.7.6
- Coil 2.5.0
- AndroidX Core KTX 1.12.0

#### iOS 应用
- CocoaPods
- Swift 5.9+
- SwiftUI

## 构建命令

### Minecraft Mod

```bash
# 进入 Mod 目录
cd mod

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

### Mod 构建产物
```
mod/build/libs/luciddreaming-1.0.0.jar
mod/build/libs/luciddreaming-1.0.0-sources.jar
```

### KMP 项目

```bash
# 构建所有平台
./gradlew build

# 构建共享模块
./gradlew :shared:build

# 构建 Android 应用
./gradlew :androidApp:assembleDebug
./gradlew :androidApp:assembleRelease

# 构建 iOS 框架
./gradlew :shared:linkReleaseFrameworkIosArm64
./gradlew :shared:linkReleaseFrameworkIosX64
./gradlew :shared:linkReleaseFrameworkIosSimulatorArm64

# 运行 Android 应用
./gradlew :androidApp:installDebug
./gradlew :androidApp:installRelease

# 清理构建产物
./gradlew clean
```

### Android 构建产物
```
androidApp/build/outputs/apk/debug/androidApp-debug.apk
androidApp/build/outputs/apk/release/androidApp-release.apk
```

### iOS 构建步骤

```bash
# 进入 iOS 目录
cd iosApp

# 安装 CocoaPods 依赖
pod install

# 打开 Xcode 项目
open LucidDreaming.xcworkspace

# 在 Xcode 中构建和运行
# 或使用命令行：
xcodebuild -workspace LucidDreaming.xcworkspace \
  -scheme LucidDreaming \
  -configuration Release \
  -destination 'platform=iOS Simulator,name=iPhone 15' \
  build
```

## 开发规范

### 代码风格

#### Java (Minecraft Mod)
- **缩进**：4 空格
- **命名约定**：
  - 类名：PascalCase（如 `ModConfig`）
  - 方法名：camelCase（如 `loadConfig`）
  - 常量：UPPER_SNAKE_CASE（如 `MODID`）
  - 变量：camelCase
- **包命名**：全小写（如 `com.luciddreaming.http`）

#### Kotlin (KMP 和 Android)
- **缩进**：4 空格
- **命名约定**：
  - 类名：PascalCase（如 `GameInfoRepository`）
  - 方法名：camelCase（如 `fetchGameInfo`）
  - 常量：UPPER_SNAKE_CASE（如 `BASE_URL`）
  - 变量：camelCase
- **包命名**：全小写（如 `com.luciddreaming.shared.data`）
- **使用 Kotlin 惯用法**：数据类、扩展函数、协程

#### Swift (iOS)
- **缩进**：4 空格
- **命名约定**：
  - 类型：PascalCase（如 `ConnectionViewModel`）
  - 属性和方法：camelCase（如 `fetchGameInfo`）
  - 常量：camelCase（如 `baseUrl`）
- **使用 Swift 惯用法**：可选类型、闭包、属性观察器

### 注释规范

#### Java
- 类和方法应有清晰的 Javadoc 注释
- 复杂逻辑应有行内注释说明

#### Kotlin
- 使用 KDoc 格式的文档注释
- 公共 API 必须有文档

#### Swift
- 使用 Swift 文档注释格式
- 公共接口必须说明参数和返回值

### 日志规范

#### Minecraft Mod (Log4j2)
```java
LucidDreaming.LOGGER.info("Info message");
LucidDreaming.LOGGER.warn("Warning message");
LucidDreaming.LOGGER.error("Error message", exception);
```

#### Android (Logcat)
```kotlin
Log.d("TAG", "Debug message")
Log.i("TAG", "Info message")
Log.w("TAG", "Warning message")
Log.e("TAG", "Error message", exception)
```

#### iOS (os_log)
```swift
import os
let logger = Logger(subsystem: "com.luciddreaming", category: "Network")
logger.debug("Debug message")
logger.info("Info message")
logger.error("Error message")
```

### 架构规范

#### Minecraft Mod
- 使用 `@Mod` 注解标记主类
- 使用 `@Mod.EventHandler` 标记事件处理方法
- 使用 `@SidedProxy` 区分客户端和服务端代码
- 使用 `@SideOnly` 标记客户端专用方法

#### KMP
- 共享代码放在 `commonMain`
- 平台特定代码放在 `androidMain` 或 `iosMain`
- 使用 expect/actual 机制实现平台特定接口

#### Android
- 遵循 MVVM 架构
- 使用 Jetpack Compose 构建 UI
- 使用 Repository 模式管理数据
- 使用协程处理异步操作

#### iOS
- 遵循 MVVM 架构
- 使用 SwiftUI 构建 UI
- 使用 Combine 处理数据流
- 使用 Combine 处理异步操作

## HTTP API 端点

Mod 启动时自动启动 HTTP 服务器，监听配置的端口（默认 1122）。

### 访问地址
- 主界面：`http://localhost:1122`
- 配置编辑器：`http://localhost:1122/config`
- 局域网：`http://[设备IP]:1122`

### 端点列表

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
返回 JSON 格式的游戏信息，可用于自定义应用：
```json
{
  "serverName": "My Server",
  "version": "1.12.2",
  "playerCount": 5,
  "uptime": 3600000,
  "mapName": "World",
  "gameMode": "Survival"
}
```

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

## 配置系统

### Minecraft Mod 配置

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

### 移动应用配置

#### Android
- 配置文件：`androidApp/src/main/res/values/strings.xml`
- 主题配置：`androidApp/src/main/res/values/themes.xml`

#### iOS
- 配置文件：`iosApp/LucidDreaming/Info.plist`
- 主题使用 SwiftUI Appearance API

#### KMP 共享配置
- 使用 DataStore Preferences 存储
- 平台特定实现在 `androidMain` 和 `iosMain`

## 测试指南

### Minecraft Mod 测试

#### 手动测试

1. **构建 Mod**：
   ```bash
   cd mod
   ./gradlew build
   ```

2. **安装 Mod**：
   - 将 `mod/build/libs/luciddreaming-1.0.0.jar` 复制到 Minecraft 的 `mods` 文件夹

3. **启动游戏**：
   - 启动 Minecraft 1.12.2 Forge

4. **测试 HTTP 服务器**：
   - 在浏览器中访问 `http://localhost:1122`
   - 检查页面是否正常显示游戏信息
   - 测试自动刷新功能
   - 测试主题切换（浅色/深色/跟随系统）
   - 测试语言切换（中文/English）
   - 测试截图功能

5. **测试模块功能**：
   - 为每个模块绑定按键
   - 在游戏中测试每个模块的功能
   - 检查模块配置是否生效

6. **测试 API 端点**：
   - 访问 `http://localhost:1122/api/info` 查看纯文本输出
   - 访问 `http://localhost:1122/api/json` 查看 JSON 输出
   - 访问 `http://localhost:1122/api/modules` 查看模块列表
   - 测试 POST `/api/modules/{name}` 切换模块状态
   - 测试 GET `/api/config` 和 GET `/api/config/{moduleName}`
   - 测试 POST `/api/config/{moduleName}` 更新配置
   - 测试 GET `/api/autowalk` 和 POST `/api/autowalk`

### 移动应用测试

#### Android 测试

1. **构建应用**：
   ```bash
   ./gradlew :androidApp:assembleDebug
   ```

2. **安装应用**：
   ```bash
   ./gradlew :androidApp:installDebug
   ```

3. **测试功能**：
   - 测试连接到 Mod HTTP 服务器
   - 测试游戏信息监控
   - 测试模块开关
   - 测试配置编辑
   - 测试设置功能
   - 测试主题切换

4. **调试**：
   - 使用 Android Studio Logcat 查看日志
   - 使用 Android Studio Debugger 进行调试

#### iOS 测试

1. **安装依赖**：
   ```bash
   cd iosApp
   pod install
   ```

2. **构建应用**：
   - 在 Xcode 中选择目标设备
   - 点击 Run 按钮

3. **测试功能**：
   - 测试连接到 Mod HTTP 服务器
   - 测试游戏信息监控
   - 测试模块开关
   - 测试配置编辑
   - 测试设置功能
   - 测试主题切换
   - 测试 iPad 和 iPhone 布局

4. **调试**：
   - 使用 Xcode Console 查看日志
   - 使用 Xcode Debugger 进行调试

## 常见问题

### Minecraft Mod

#### 编译错误

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

#### 端口被占用

**问题**：HTTP 服务器无法启动，端口已被占用

**解决方案**：
1. 修改配置文件 `config/luciddreaming.cfg` 中的端口号
2. 或停止占用该端口的进程

### 移动应用

#### KMP 构建错误

**问题**：Kotlin 编译器未找到

**解决方案**：
```bash
# 安装 Kotlin 编译器
export PATH=$PATH:~/kotlin/kotlinc/bin
```

#### Android 构建错误

**问题**：Android SDK 未配置

**解决方案**：
- 安装 Android Studio
- 配置 ANDROID_HOME 环境变量
- 安装所需的 SDK 版本

#### iOS 构建错误

**问题**：CocoaPods 依赖安装失败

**解决方案**：
```bash
# 更新 CocoaPods
sudo gem install cocoapods

# 清理并重新安装
cd iosApp
rm -rf Pods Podfile.lock
pod install
```

### 连接问题

#### 移动应用无法连接到 Mod

**问题**：无法连接到 Mod HTTP 服务器

**解决方案**：
1. 确认 Mod 正在运行且 HTTP 服务器已启动
2. 检查配置文件中的端口和绑定地址
3. 确认设备在同一网络
4. 检查防火墙设置
5. 使用 `ipconfig` 或 `ifconfig` 查看设备 IP

## 扩展开发

### 添加新的 Minecraft 模块

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

### 扩展 KMP 共享模块

#### 添加新的数据模型

在 `shared/src/commonMain/kotlin/com/luciddreaming/shared/data/model/` 中添加：

```kotlin
package com.luciddreaming.shared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MyData(
    val id: String,
    val name: String,
    val value: Int
)
```

#### 添加新的 API 端点

1. 在 `shared/src/commonMain/kotlin/com/luciddreaming/shared/data/api/` 中添加 API 接口：
   ```kotlin
   package com.luciddreaming.shared.data.api

   import io.ktor.client.*
   import io.ktor.client.request.*
   import com.luciddreaming.shared.data.model.MyData

   class MyApi(private val client: HttpClient) {
       suspend fun fetchMyData(): MyData {
           return client.get("/api/mydata")
       }
   }
   ```

2. 在 Repository 中集成 API：
   ```kotlin
   class GameInfoRepository(
       private val gameInfoApi: GameInfoApi,
       private val myApi: MyApi
   ) {
       suspend fun fetchMyData(): MyData {
           return myApi.fetchMyData()
       }
   }
   ```

#### 添加平台特定实现

使用 expect/actual 机制：

```kotlin
// commonMain
expect fun getPlatformName(): String

// androidMain
actual fun getPlatformName(): String = "Android"

// iosMain
actual fun getPlatformName(): String = "iOS"
```

### 扩展 Android 应用

#### 添加新的屏幕

1. 在 `androidApp/src/main/java/com/luciddreaming/android/ui/screens/` 中创建新的 Compose 屏幕：
   ```kotlin
   @Composable
   fun MyScreen(
       viewModel: MyViewModel = viewModel()
   ) {
       // UI 实现
   }
   ```

2. 创建 ViewModel：
   ```kotlin
   class MyViewModel(
       private val repository: GameInfoRepository
   ) : ViewModel() {
       // ViewModel 实现
   }
   ```

3. 在 Navigation 中添加路由：
   ```kotlin
   NavHost(
       navController = navController,
       startDestination = "my_screen"
   ) {
       composable("my_screen") {
           MyScreen()
       }
   }
   ```

### 扩展 iOS 应用

#### 添加新的屏幕

1. 在 `iosApp/LucidDreaming/UI/` 中创建新的 SwiftUI 屏幕：
   ```swift
   import SwiftUI

   struct MyScreen: View {
       @StateObject private var viewModel = MyViewModel()

       var body: some View {
           // UI 实现
       }
   }
   ```

2. 创建 ViewModel：
   ```swift
   import SwiftUI
   import Combine

   class MyViewModel: ObservableObject {
       @Published var data: MyData?
       private var cancellables = Set<AnyCancellable>()

       init() {
           // 初始化
       }
   }
   ```

3. 在 MainApp 中添加导航：
   ```swift
   case myScreen:
       return AnyView(MyScreen())
   ```

## 部署

### Minecraft Mod

#### 发布到 CurseForge

1. 构建 JAR 文件
2. 创建 CurseForge 项目页面
3. 上传 JAR 文件
4. 填写版本信息和更新日志

#### 发布到 Modrinth

1. 构建 JAR 文件
2. 创建 Modrinth 项目页面
3. 上传 JAR 文件
4. 填写版本信息和更新日志

### Android 应用

#### 发布到 Google Play

1. 构建 Release APK 或 AAB：
   ```bash
   ./gradlew :androidApp:bundleRelease
   ```

2. 签名应用
3. 创建 Google Play 开发者账号
4. 上传应用
5. 填写应用信息和截图
6. 提交审核

### iOS 应用

#### 发布到 App Store

1. 配置签名和证书
2. 创建 App Store Connect 记录
3. 上传应用：
   ```bash
   xcodebuild -workspace LucidDreaming.xcworkspace \
     -scheme LucidDreaming \
     -configuration Release \
     -archivePath build/LucidDreaming.xcarchive \
     archive

   xcodebuild -exportArchive \
     -archivePath build/LucidDreaming.xcarchive \
     -exportPath build/Export \
     -exportOptionsPlist ExportOptions.plist
   ```

4. 填写应用信息和截图
5. 提交审核

## 维护

### 版本更新

1. 更新 `mod/build.gradle` 中的版本号
2. 更新 `androidApp/build.gradle.kts` 中的版本号
3. 更新 `iosApp/LucidDreaming/Info.plist` 中的版本号
4. 更新 AGENTS.md 中的版本信息
5. 提交代码并创建新的 Git 标签

### 依赖更新

#### Minecraft Mod
更新 Forge 版本：
```gradle
minecraft {
    version = "1.12.2-14.23.5.2847"  // 更新此版本号
}
```

#### KMP
更新 Kotlin 版本：
```kotlin
kotlin("multiplatform") version "1.9.20"
```

更新 Ktor 版本：
```kotlin
implementation("io.ktor:ktor-client-core:2.3.7")
```

#### Android
更新 Compose BOM：
```kotlin
implementation(platform("androidx.compose:compose-bom:2024.02.00"))
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

### Git 分支策略

- `main` - 主分支，稳定版本
- `kmp-refactor` - KMP 重构分支
- `feature/*` - 功能分支
- `bugfix/*` - 修复分支

## 参考资料

### Minecraft Mod 开发
- [Minecraft Forge Documentation](https://docs.minecraftforge.net/)
- [MCP mappings](https://mcp.thiakil.com/)
- [ForgeGradle Documentation](https://github.com/MinecraftForge/ForgeGradle)
- [Java 8 Documentation](https://docs.oracle.com/javase/8/docs/)
- [A* Pathfinding Algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm)

### KMP 开发
- [Kotlin Multiplatform Documentation](https://kotlinlang.org/docs/multiplatform.html)
- [Ktor Client Documentation](https://ktor.io/docs/client.html)
- [Kotlinx Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)

### Android 开发
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Android Developers](https://developer.android.com/)
- [Material Design 3](https://m3.material.io/)

### iOS 开发
- [SwiftUI Documentation](https://developer.apple.com/documentation/swiftui)
- [Apple Developer](https://developer.apple.com/)
- [Human Interface Guidelines](https://developer.apple.com/design/human-interface-guidelines/)

## 许可证

本项目采用 MIT 许可证。

## 联系方式

- 作者：Drwei
- GitHub：[项目仓库地址](https://github.com/herbrine8403/LucidDreaming)

---

**最后更新**：2026-02-06