# 🌙 Lucid Dreaming (清醒梦) - Minecraft 1.12.2 Forge Mod

一个功能丰富的 Minecraft 1.12.2 Forge Mod，通过内置的 HTTP 服务器实时展示游戏信息，并提供模块化游戏辅助功能。让您可以在浏览器中查看游戏状态并控制各种游戏模块。

## ✨ 功能特性

### 📊 游戏信息展示
- **玩家信息**：实时显示玩家名称、生命值、饥饿度、经验、位置、维度等
- **游戏信息**：显示 Minecraft 版本、Forge 版本、运行时长、FPS 等
- **服务器信息**：显示服务器类型、地址、名称（如果在服务器中）
- **计分板**：实时显示侧边栏计分板内容，支持 Minecraft 颜色代码
- **系统信息**：显示操作系统和 Java 版本
- **实时更新**：网页自动刷新，无需手动刷新
- **截图功能**：支持在网页中查看游戏截图

### 🎮 模块系统
内置多个游戏辅助模块，可通过网页界面或按键控制：

#### 🎣 AutoFish（自动钓鱼）
- 自动检测鱼钩状态
- 自动收杆和重抛
- 支持多竿模式
- 支持防止鱼竿损坏
- 可配置重抛延迟

#### 🖱️ AutoClicker（自动点击器）
- 支持左右键自动点击
- 可配置 CPS（每秒点击次数）
- 支持 CPS 波动以避免检测

#### ⚔️ KillAura（自动攻击光环）
- 自动攻击范围内的敌对生物
- 支持攻击速度配置
- 支持攻击范围配置
- 支持 CPS 波动和未命中概率（反作弊）

#### 🛡️ AntiKick（防踢出）
- 防止因挂机被服务器踢出
- 支持多种模式：跳跃、旋转、移动
- 可配置动作间隔

#### 👁️ NoRender（渲染控制）
- 隐藏实体、粒子、天气、天空、雾气
- 提升性能或视觉效果

#### 🖤 FakeBlackScreen（假黑屏）
- 模拟黑屏效果
- 可配置不透明度

### 🌐 Web 界面
- **美观界面**：现代化的响应式网页设计，支持移动设备
- **主题切换**：支持亮色/暗色主题，自动检测系统主题
- **模块控制面板**：在网页中直接控制所有模块
- **实时状态**：显示所有模块的启用状态和按键绑定
- **Android 支持**：支持 PojavLauncher 等安卓环境，可通过局域网访问

## 📋 系统要求

- **Minecraft 版本**：1.12.2
- **Forge 版本**：14.23.5.2847 或更高
- **Java 版本**：Java 8（必需）
- **构建环境**：需要 Java 8 来编译

## 🚀 安装

### 方法 1：直接安装 JAR 文件

1. 下载编译好的 `luciddreaming-1.0.0.jar` 文件
2. 将 JAR 文件放入 Minecraft 的 `mods` 文件夹
3. 启动 Minecraft

### 方法 2：从源码构建

#### 环境准备

1. 安装 **Java 8 JDK**（必需）
   - 下载地址：https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html
   - 或使用 OpenJDK 8：https://adoptium.net/temurin/releases/?version=8

2. 设置 JAVA_HOME 环境变量
   ```bash
   export JAVA_HOME=/path/to/java8
   export PATH=$JAVA_HOME/bin:$PATH
   ```

#### 构建步骤

1. 克隆或下载项目
2. 在项目根目录运行：
   ```bash
   chmod +x gradlew
   ./gradlew build
   ```

3. 构建完成后，JAR 文件位于 `build/libs/luciddreaming-1.0.0.jar`

## 📖 使用方法

### 启动服务器

Mod 安装后，游戏启动时会自动启动 HTTP 服务器和模块系统。

### 访问网页

在浏览器中访问以下地址：

- **本地访问**：`http://localhost:1122`
- **局域网访问**：`http://[设备IP]:1122`（例如：`http://192.168.1.100:1122`）

### Android 设备访问

1. 确保设备和运行 Minecraft 的设备在同一网络
2. 查找运行 Minecraft 的设备的 IP 地址
3. 在 Android 设备浏览器中访问 `http://[IP地址]:1122`

### 使用模块

#### 方法 1：通过网页控制
1. 访问网页界面
2. 滚动到"模块控制面板"部分
3. 点击模块开关来启用或禁用模块

#### 方法 2：通过按键控制
1. 在游戏中按下模块绑定的按键（默认未绑定）
2. 模块会在启用和禁用之间切换

#### 配置模块按键
1. 打开 Minecraft 游戏设置
2. 进入"控制"设置
3. 找到"Lucid Dreaming"分类
4. 为每个模块设置按键

## ⚙️ 配置

### 主配置文件

配置文件位于 `config/luciddreaming.cfg`，可修改以下选项：

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
B:Enable Screenshot=true          # 启用截图功能
D:Screenshot Quality=0.8          # 截图质量（0.1-1.0）
I:Theme Mode=0                    # 主题模式（0=自动，1=亮色，2=暗色）
I:Auto Refresh Interval=1000      # 自动刷新间隔（毫秒，0=禁用）
B:Enable Module Control=true      # 启用模块控制面板
```

### 模块配置文件

配置文件位于 `config/Lucid Dreaming_modules.cfg`，每个模块都有独立的配置：

#### AutoFish 配置
```ini
AutoFish {
  B:Enabled=false          # 是否启用
  I:Keybind=0              # 按键代码（0=未绑定）
  I:Recast Delay=1500      # 重抛延迟（毫秒）
  B:Multi Rod=false        # 多竿模式
  B:No Break=false         # 防止鱼竿损坏
}
```

#### AutoClicker 配置
```ini
AutoClicker {
  B:Enabled=false          # 是否启用
  I:Keybind=0              # 按键代码
  I:Left CPS=8             # 左键 CPS
  I:Right CPS=4            # 右键 CPS
  B:CPS Fluctuation=true   # CPS 波动
  D:Fluctuation Amount=1.0 # 波动幅度
}
```

#### KillAura 配置
```ini
KillAura {
  B:Enabled=false          # 是否启用
  I:Keybind=0              # 按键代码
  D:Attack Speed=8.0       # 攻击速度（次/秒）
  D:Range=4.5              # 攻击范围（方块）
  B:CPS Fluctuation=true   # CPS 波动
  D:Miss Chance=0.05       # 未命中概率
}
```

#### AntiKick 配置
```ini
AntiKick {
  B:Enabled=false          # 是否启用
  I:Keybind=0              # 按键代码
  I:Mode=0                 # 模式（0=跳跃，1=旋转，2=移动）
  I:Interval=300           # 动作间隔（tick）
}
```

#### NoRender 配置
```ini
NoRender {
  B:Enabled=false          # 是否启用
  I:Keybind=0              # 按键代码
  B:Hide Entities=false    # 隐藏实体
  B:Hide Particles=false   # 隐藏粒子
  B:Hide Weather=false     # 隐藏天气
  B:Hide Sky=false         # 隐藏天空
  B:Hide Fog=false         # 隐藏雾气
}
```

#### FakeBlackScreen 配置
```ini
FakeBlackScreen {
  B:Enabled=false          # 是否启用
  I:Keybind=0              # 按键代码
  D:Opacity=1.0            # 黑屏不透明度（0.0-1.0）
}
```

### 配置说明

- **HTTP Server Port**：修改 HTTP 服务器的端口号
- **Bind Address**：
  - `0.0.0.0`：绑定到所有网络接口（推荐，支持局域网访问）
  - `127.0.0.1`：仅本地访问
- **Enable LAN Access**：是否允许局域网设备访问
- **Theme Mode**：
  - `0`：自动检测系统主题
  - `1`：强制亮色主题
  - `2`：强制暗色主题

## 🔧 API 端点

Mod 提供以下 API 端点供开发者使用：

### 1. 主页（HTML 界面）
```
GET /
```
返回美观的 HTML 界面，包含游戏信息展示、模块控制面板、截图功能等。

### 2. 纯文本信息
```
GET /api/info
```
返回纯文本格式的游戏信息。

### 3. JSON 数据
```
GET /api/json
```
返回 JSON 格式的游戏信息，可用于自定义应用。

#### JSON 数据结构示例

```json
{
  "player": {
    "name": "PlayerName",
    "uuid": "550e8400-e29b-41d4-a716-446655440000",
    "health": 20.0,
    "maxHealth": 20.0,
    "hunger": 20,
    "saturation": 5.0,
    "experienceLevel": 10,
    "experienceProgress": 0.5,
    "position": "X: 100.00, Y: 64.00, Z: 200.00",
    "dimension": "Overworld",
    "gameMode": "Survival"
  },
  "game": {
    "minecraftVersion": "1.12.2",
    "forgeVersion": "14.23.5.2847",
    "modVersion": "1.0.0",
    "uptime": "1h 30m 45s",
    "uptimeSeconds": 5445,
    "fps": 60,
    "currentTime": "2026-01-22 14:30:00"
  },
  "server": {
    "type": "Multiplayer",
    "address": "mc.hypixel.net",
    "name": "Hypixel Network"
  },
  "scoreboard": [
    "§6§lHypixel Network",
    "§aPlayer: §fPlayerName",
    "§aRank: §fVIP",
    "§aCoins: §f1000"
  ],
  "system": {
    "osName": "Linux",
    "osVersion": "5.4.0",
    "javaVersion": "1.8.0_301"
  }
}
```

### 4. 截图
```
GET /api/screenshot
```
返回当前游戏画面的截图（PNG/JPEG 格式）。

### 5. 模块列表
```
GET /api/modules
```
返回所有模块的列表和状态（JSON 格式）。

#### 响应示例
```json
{
  "modules": [
    {
      "name": "AutoFish",
      "description": "Automatically catch fish",
      "category": "Player",
      "enabled": false,
      "keybind": "NONE"
    },
    {
      "name": "AutoClicker",
      "description": "Automatically click",
      "category": "Player",
      "enabled": false,
      "keybind": "NONE"
    },
    {
      "name": "KillAura",
      "description": "Automatically attack nearby entities",
      "category": "Combat",
      "enabled": false,
      "keybind": "NONE"
    }
  ],
  "categories": ["Server", "Combat", "Movement", "Player", "Render", "Misc"]
}
```

### 6. 控制模块
```
POST /api/modules/{name}
```
切换指定模块的状态。

#### 请求体示例
```json
{
  "action": "toggle"
}
```

#### 支持的操作
- `toggle` - 切换状态
- `enable` - 启用模块
- `disable` - 禁用模块

#### 响应示例
```json
{
  "name": "AutoFish",
  "enabled": true,
  "description": "Automatically catch fish",
  "category": "Player"
}
```

## 🎯 使用场景

1. **多屏监控**：在第二个屏幕上实时监控游戏状态
2. **手机查看**：用手机在局域网内查看游戏信息
3. **模块控制**：通过网页界面控制各种游戏辅助功能
4. **录制辅助**：录制时可以方便地查看游戏状态
5. **服务器管理**：服务器管理员可以远程查看玩家状态
6. **信息展示**：在直播间展示游戏信息

## 🔒 安全注意

- **仅在受信任的网络中使用**：HTTP 服务器未加密，建议仅在受信任的局域网中使用
- **端口配置**：如果需要在公网访问，建议使用反向代理（如 Nginx）并启用 HTTPS
- **信息敏感**：服务器地址和玩家信息可能会被同网络内的其他设备看到
- **模块使用**：请遵守服务器规则，合理使用模块功能

## 📱 Android 兼容性

本 Mod 经过优化，完全支持 Android 设备：

- ✅ PojavLauncher 兼容
- ✅ 支持热点的局域网访问
- ✅ 响应式网页设计，适配各种屏幕尺寸
- ✅ 自动检测系统信息
- ✅ 支持触摸操作

## 🛠️ 开发

### 构建项目

```bash
# 赋予 gradlew 执行权限
chmod +x gradlew

# 构建项目
./gradlew build

# 清理并重新构建
./gradlew clean build

# 运行游戏客户端（开发模式）
./gradlew runClient
```

### 添加新模块

1. 创建新的模块类，继承 `Module` 抽象类
2. 实现 `onTick()` 方法
3. 可选实现 `onEnable()` 和 `onDisable()` 方法
4. 在 `ClientProxy` 中注册模块
5. 在 `ModuleConfigs` 中添加配置

详细的开发指南请参考 [AGENTS.md](AGENTS.md)。

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

### 贡献指南

1. Fork 项目仓库
2. 创建功能分支：`git checkout -b feature/your-feature`
3. 提交更改：`git commit -m 'Add some feature'`
4. 推送到分支：`git push origin feature/your-feature`
5. 创建 Pull Request

## 📝 许可证

本项目采用 MIT 许可证。

## 🙏 致谢

- Minecraft Forge 团队
- Minecraft 社区
- 所有贡献者

## 📞 联系方式

- **作者**：Drwei
- **GitHub**：[https://github.com/herbrine8403/LucidDreaming](https://github.com/herbrine8403/LucidDreaming)

---

**注意**：本 Mod 仅用于教育和娱乐目的。请遵守 Minecraft 的使用条款和服务器规则。使用模块功能时请确保不违反服务器规则。