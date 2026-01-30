# LucidDreaming Android App

一个用于控制 Minecraft 1.12.2 Forge Mod "Lucid Dreaming" 的 Android 应用程序。

## 功能特性

- **连接管理**: 首次启动时输入 Mod 服务器的 IP 地址和端口
- **监测功能**: 实时显示玩家信息、服务器信息、游戏信息、系统信息
- **截图功能**: 查看游戏实时截图
- **计分板**: 显示游戏内计分板内容
- **模块管理**: 启用/禁用游戏模块，查看模块状态
- **自动化**: 占位符（即将推出）
- **主题支持**: 亮色/深色模式自动切换
- **现代化 UI**: 使用 Jetpack Compose 构建，Material 3 设计

## 技术栈

- **框架**: Jetpack Compose (Kotlin)
- **架构**: MVVM (Model-View-ViewModel)
- **网络**: Retrofit + OkHttp
- **图片加载**: Coil
- **本地存储**: DataStore
- **主题**: Material 3 Design System
- **最低版本**: Android 7.0 (API 24)
- **目标版本**: Android 14 (API 34)

## 构建说明

### 环境要求

- JDK 17 或更高版本
- Android Studio Hedgehog 或更高版本
- Android SDK 34

### 构建步骤

1. 克隆仓库并切换到 Android 分支:
```bash
git clone https://github.com/herbrine8403/LucidDreaming.git
cd LucidDreaming
git checkout feature/android-app
```

2. 进入 Android 项目目录:
```bash
cd android
```

3. 构建 Debug APK:
```bash
./gradlew assembleDebug
```

4. 构建 Release APK:
```bash
./gradlew assembleRelease
```

5. 生成的 APK 文件位于:
   - Debug: `app/build/outputs/apk/debug/app-debug.apk`
   - Release: `app/build/outputs/apk/release/app-release-unsigned.apk`

### 使用 GitHub Actions

项目配置了 GitHub Actions CI，可以自动构建 APK：

1. 访问 GitHub Actions 页面
2. 选择 "Android CI" 工作流
3. 点击 "Run workflow" 手动触发构建
4. 构建完成后下载生成的 APK

## 使用说明

### 首次连接

1. 安装 APK 到 Android 设备
2. 打开应用
3. 在连接页面输入 LucidDreaming Mod 服务器的 IP 地址
4. 输入端口号（默认: 1122）
5. 点击"连接"按钮

### 主界面功能

#### 监测区
- **玩家信息**: 名称、生命值、饥饿度、经验、位置、维度、游戏模式
- **服务器信息**: 类型、地址、名称
- **游戏信息**: Minecraft 版本、Forge 版本、Mod 版本、运行时间、FPS
- **系统信息**: 操作系统、Java 版本、当前时间
- **截图**: 实时查看游戏画面
- **计分板**: 显示游戏内计分板

#### 模块区
- 查看所有已注册的模块
- 启用/禁用模块
- 查看模块描述和类别
- 查看模块快捷键

#### 自动化区
- 占位符，功能即将推出

## 项目结构

```
android/
├── app/
│   ├── src/main/
│   │   ├── java/com/luciddreaming/android/
│   │   │   ├── data/
│   │   │   │   ├── api/          # API 接口
│   │   │   │   ├── model/        # 数据模型
│   │   │   │   ├── preferences/  # 本地存储
│   │   │   │   └── repository/   # 数据仓库
│   │   │   ├── ui/
│   │   │   │   ├── screens/      # 页面
│   │   │   │   ├── components/   # 组件
│   │   │   │   └── theme/        # 主题
│   │   │   ├── viewmodel/        # ViewModel
│   │   │   └── MainActivity.kt   # 主 Activity
│   │   └── res/                  # 资源文件
│   └── build.gradle.kts          # 应用构建配置
├── build.gradle.kts              # 项目构建配置
└── settings.gradle.kts           # Gradle 设置
```

## 设计规范

### 颜色方案

- **主色调**:
  - 亮色模式: 白色 (#FFFFFF)
  - 深色模式: 黑色 (#000000)
- **强调色**: 深绿色 (#00D014)
- **状态色**: 成功、错误、警告

### 字体

- 使用系统默认字体
- 支持可缩放的排版

### 图标

- 使用 Material Icons
- SVG 格式，不使用 emoji

## API 端点

应用通过 HTTP API 与 LucidDreaming Mod 通信：

- `GET /api/json` - 获取游戏信息（JSON 格式）
- `GET /api/modules` - 获取模块列表
- `POST /api/modules/{name}` - 切换模块状态
- `GET /api/screenshot` - 获取游戏截图

## 开发说明

### 添加新页面

1. 在 `ui/screens/` 目录下创建新的 Composable 函数
2. 在 `MainActivity.kt` 中添加导航路由
3. 在 `BottomNav.kt` 中添加导航项

### 添加新 API

1. 在 `data/api/` 中定义接口
2. 在 `RetrofitClient` 中添加 API 实例
3. 在 `GameInfoRepository` 中添加方法

### 修改主题

编辑 `ui/theme/Color.kt` 中的颜色定义。

## 许可证

与主项目保持一致，采用 MIT 许可证。

## 贡献

欢迎提交 Issue 和 Pull Request！

## 联系方式

- 作者: Drwei
- GitHub: [项目仓库](https://github.com/herbrine8403/LucidDreaming)