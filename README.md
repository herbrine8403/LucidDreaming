# 🌙 Lucid Dreaming (清醒梦) - Minecraft 1.12.2 Forge Mod

一个轻量级的 Minecraft 1.12.2 Forge Mod，通过内置的 HTTP 服务器实时展示游戏信息，让您可以在浏览器中查看游戏状态。

## ✨ 功能特性

- **玩家信息**：实时显示玩家名称、生命值、饥饿度、经验、位置、维度等
- **游戏信息**：显示 Minecraft 版本、Forge 版本、运行时长、FPS 等
- **服务器信息**：显示服务器类型、地址、名称（如果在服务器中）
- **计分板**：实时显示侧边栏计分板内容
- **系统信息**：显示操作系统和 Java 版本
- **实时更新**：网页每秒自动刷新，无需手动刷新
- **Android 支持**：支持 PojavLauncher 等安卓环境，可通过局域网访问
- **美观界面**：现代化的响应式网页设计，支持移动设备

## 📋 系统要求

- **Minecraft 版本**：1.12.2
- **Forge 版本**：14.23.5.2859 或更高
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

Mod 安装后，游戏启动时会自动启动 HTTP 服务器。

### 访问网页

在浏览器中访问以下地址：

- **本地访问**：`http://localhost:1122`
- **局域网访问**：`http://[设备IP]:1122`（例如：`http://192.168.1.100:1122`）

### Android 设备访问

1. 确保设备和运行 Minecraft 的设备在同一网络
2. 查找运行 Minecraft 的设备的 IP 地址
3. 在 Android 设备浏览器中访问 `http://[IP地址]:1122`

## ⚙️ 配置

配置文件位于 `config/luciddreaming.cfg`，可修改以下选项：

```ini
# HTTP 服务器端口（默认：1122）
I:HTTP Server Port=1122

# 绑定地址（默认：0.0.0.0，表示所有网络接口）
S:Bind Address=0.0.0.0

# 是否允许局域网访问（默认：true）
B:Enable LAN Access=true
```

### 配置说明

- **HTTP Server Port**：修改 HTTP 服务器的端口号
- **Bind Address**：
  - `0.0.0.0`：绑定到所有网络接口（推荐，支持局域网访问）
  - `127.0.0.1`：仅本地访问
- **Enable LAN Access**：是否允许局域网设备访问

## 🔧 API 端点

Mod 提供以下 API 端点：

### 1. 主页（HTML 界面）
```
GET /
```
返回美观的 HTML 界面，自动刷新显示游戏信息。

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
    "forgeVersion": "14.23.5.2859",
    "modVersion": "1.0.0",
    "uptime": "1h 30m 45s",
    "uptimeSeconds": 5445,
    "fps": 60,
    "currentTime": "2026-01-21 14:30:00"
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

## 🎯 使用场景

1. **多屏监控**：在第二个屏幕上实时监控游戏状态
2. **手机查看**：用手机在局域网内查看游戏信息
3. **录制辅助**：录制时可以方便地查看游戏状态
4. **服务器管理**：服务器管理员可以远程查看玩家状态
5. **信息展示**：在直播间展示游戏信息

## 🔒 安全注意

- **仅在受信任的网络中使用**：HTTP 服务器未加密，建议仅在受信任的局域网中使用
- **端口配置**：如果需要在公网访问，建议使用反向代理（如 Nginx）并启用 HTTPS
- **信息敏感**：服务器地址和玩家信息可能会被同网络内的其他设备看到

## 📱 Android 兼容性

本 Mod 经过优化，完全支持 Android 设备：

- ✅ PojavLauncher 兼容
- ✅ 支持热点的局域网访问
- ✅ 响应式网页设计，适配各种屏幕尺寸
- ✅ 自动检测系统信息

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## 📝 许可证

本项目采用 MIT 许可证。

## 🙏 致谢

- Minecraft Forge 团队
- Minecraft 社区

---

**注意**：本 Mod 仅用于教育和娱乐目的。请遵守 Minecraft 的使用条款和服务器规则。