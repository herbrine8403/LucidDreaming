package com.luciddreaming.http;

public class WebTemplate {
    public static String generateHTML() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Lucid Dreaming - Game Information</title>\n" +
                "    <style>\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "        \n" +
                "        :root {\n" +
                "            --primary-gradient: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
                "            --card-bg: rgba(255, 255, 255, 0.95);\n" +
                "            --text-primary: #2d3748;\n" +
                "            --text-secondary: #718096;\n" +
                "            --accent-color: #667eea;\n" +
                "            --success-color: #48bb78;\n" +
                "            --border-color: #e2e8f0;\n" +
                "            --shadow-sm: 0 2px 4px rgba(0, 0, 0, 0.1);\n" +
                "            --shadow-md: 0 4px 6px rgba(0, 0, 0, 0.1);\n" +
                "            --shadow-lg: 0 10px 25px rgba(0, 0, 0, 0.15);\n" +
                "        }\n" +
                "        \n" +
                "        body {\n" +
                "            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';\n" +
                "            background: var(--primary-gradient);\n" +
                "            min-height: 100vh;\n" +
                "            padding: 20px;\n" +
                "            color: var(--text-primary);\n" +
                "            line-height: 1.6;\n" +
                "        }\n" +
                "        \n" +
                "        .container {\n" +
                "            max-width: 1400px;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "        \n" +
                "        .header {\n" +
                "            text-align: center;\n" +
                "            color: white;\n" +
                "            margin-bottom: 30px;\n" +
                "            animation: fadeInDown 0.6s ease-out;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "        \n" +
                "        .header h1 {\n" +
                "            font-size: 2.8em;\n" +
                "            margin-bottom: 8px;\n" +
                "            text-shadow: 2px 2px 8px rgba(0,0,0,0.3);\n" +
                "            font-weight: 700;\n" +
                "            letter-spacing: -0.5px;\n" +
                "        }\n" +
                "        \n" +
                "        .header p {\n" +
                "            font-size: 1.15em;\n" +
                "            opacity: 0.95;\n" +
                "            font-weight: 300;\n" +
                "        }\n" +
                "        \n" +
                "        .header .subtitle {\n" +
                "            font-size: 0.9em;\n" +
                "            margin-top: 12px;\n" +
                "            opacity: 0.85;\n" +
                "        }\n" +
                "        \n" +
                "        .language-selector {\n" +
                "            position: absolute;\n" +
                "            top: 0;\n" +
                "            right: 0;\n" +
                "            display: flex;\n" +
                "            gap: 8px;\n" +
                "        }\n" +
                "        \n" +
                "        .lang-btn {\n" +
                "            background: rgba(255, 255, 255, 0.2);\n" +
                "            border: 2px solid rgba(255, 255, 255, 0.3);\n" +
                "            color: white;\n" +
                "            padding: 8px 16px;\n" +
                "            border-radius: 20px;\n" +
                "            cursor: pointer;\n" +
                "            font-size: 0.9em;\n" +
                "            font-weight: 500;\n" +
                "            transition: all 0.3s ease;\n" +
                "            backdrop-filter: blur(10px);\n" +
                "        }\n" +
                "        \n" +
                "        .lang-btn:hover {\n" +
                "            background: rgba(255, 255, 255, 0.3);\n" +
                "            transform: translateY(-2px);\n" +
                "        }\n" +
                "        \n" +
                "        .lang-btn.active {\n" +
                "            background: white;\n" +
                "            color: var(--accent-color);\n" +
                "            border-color: white;\n" +
                "        }\n" +
                "        \n" +
                "        .info-grid {\n" +
                "            display: grid;\n" +
                "            grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));\n" +
                "            gap: 20px;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        \n" +
                "        .card {\n" +
                "            background: var(--card-bg);\n" +
                "            border-radius: 16px;\n" +
                "            padding: 24px;\n" +
                "            box-shadow: var(--shadow-lg);\n" +
                "            transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);\n" +
                "            animation: fadeInUp 0.6s ease-out;\n" +
                "            backdrop-filter: blur(10px);\n" +
                "        }\n" +
                "        \n" +
                "        .card:hover {\n" +
                "            transform: translateY(-4px);\n" +
                "            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);\n" +
                "        }\n" +
                "        \n" +
                "        .card h2 {\n" +
                "            color: var(--accent-color);\n" +
                "            font-size: 1.35em;\n" +
                "            margin-bottom: 18px;\n" +
                "            border-bottom: 2px solid var(--accent-color);\n" +
                "            padding-bottom: 10px;\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: 10px;\n" +
                "            font-weight: 600;\n" +
                "        }\n" +
                "        \n" +
                "        .card h2 .icon {\n" +
                "            font-size: 1.3em;\n" +
                "        }\n" +
                "        \n" +
                "        .info-row {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "            align-items: center;\n" +
                "            padding: 12px 0;\n" +
                "            border-bottom: 1px solid var(--border-color);\n" +
                "            transition: background-color 0.2s ease;\n" +
                "        }\n" +
                "        \n" +
                "        .info-row:last-child {\n" +
                "            border-bottom: none;\n" +
                "        }\n" +
                "        \n" +
                "        .info-row:hover {\n" +
                "            background-color: rgba(102, 126, 234, 0.05);\n" +
                "            border-radius: 8px;\n" +
                "            padding-left: 8px;\n" +
                "            padding-right: 8px;\n" +
                "        }\n" +
                "        \n" +
                "        .info-label {\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "            font-size: 0.95em;\n" +
                "        }\n" +
                "        \n" +
                "        .info-value {\n" +
                "            color: var(--accent-color);\n" +
                "            font-weight: 600;\n" +
                "            text-align: right;\n" +
                "            font-size: 0.95em;\n" +
                "        }\n" +
                "        \n" +
                "        .scoreboard-container {\n" +
                "            background: var(--card-bg);\n" +
                "            border-radius: 16px;\n" +
                "            padding: 24px;\n" +
                "            box-shadow: var(--shadow-lg);\n" +
                "            margin-top: 20px;\n" +
                "            animation: fadeInUp 0.6s ease-out;\n" +
                "            backdrop-filter: blur(10px);\n" +
                "        }\n" +
                "        \n" +
                "        .scoreboard-container h2 {\n" +
                "            color: var(--accent-color);\n" +
                "            margin-bottom: 18px;\n" +
                "            border-bottom: 2px solid var(--accent-color);\n" +
                "            padding-bottom: 10px;\n" +
                "            font-size: 1.35em;\n" +
                "            font-weight: 600;\n" +
                "        }\n" +
                "        \n" +
                "        .scoreboard-list {\n" +
                "            list-style: none;\n" +
                "            background: rgba(0, 0, 0, 0.03);\n" +
                "            border-radius: 12px;\n" +
                "            padding: 16px;\n" +
                "        }\n" +
                "        \n" +
                "        .scoreboard-list li {\n" +
                "            padding: 10px 12px;\n" +
                "            border-bottom: 1px solid var(--border-color);\n" +
                "            font-family: 'Courier New', 'Consolas', monospace;\n" +
                "            font-size: 0.9em;\n" +
                "            line-height: 1.5;\n" +
                "            transition: background-color 0.2s ease;\n" +
                "        }\n" +
                "        \n" +
                "        .scoreboard-list li:last-child {\n" +
                "            border-bottom: none;\n" +
                "        }\n" +
                "        \n" +
                "        .scoreboard-list li:hover {\n" +
                "            background-color: rgba(102, 126, 234, 0.08);\n" +
                "            border-radius: 6px;\n" +
                "        }\n" +
                "        \n" +
                "        /* Minecraft color codes */\n" +
                "        .color-0 { color: #000000; }\n" +
                "        .color-1 { color: #0000AA; }\n" +
                "        .color-2 { color: #00AA00; }\n" +
                "        .color-3 { color: #00AAAA; }\n" +
                "        .color-4 { color: #AA0000; }\n" +
                "        .color-5 { color: #AA00AA; }\n" +
                "        .color-6 { color: #FFAA00; }\n" +
                "        .color-7 { color: #AAAAAA; }\n" +
                "        .color-8 { color: #555555; }\n" +
                "        .color-9 { color: #5555FF; }\n" +
                "        .color-a { color: #55FF55; }\n" +
                "        .color-b { color: #55FFFF; }\n" +
                "        .color-c { color: #FF5555; }\n" +
                "        .color-d { color: #FF55FF; }\n" +
                "        .color-e { color: #FFFF55; }\n" +
                "        .color-f { color: #FFFFFF; }\n" +
                "        .color-l { font-weight: bold; }\n" +
                "        .color-m { text-decoration: line-through; }\n" +
                "        .color-n { text-decoration: underline; }\n" +
                "        .color-o { font-style: italic; }\n" +
                "        .color-r { color: inherit; text-decoration: none; font-style: normal; font-weight: normal; }\n" +
                "        \n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            color: white;\n" +
                "            margin-top: 30px;\n" +
                "            opacity: 0.9;\n" +
                "            font-size: 0.9em;\n" +
                "        }\n" +
                "        \n" +
                "        .refresh-indicator {\n" +
                "            display: inline-block;\n" +
                "            width: 8px;\n" +
                "            height: 8px;\n" +
                "            background-color: var(--success-color);\n" +
                "            border-radius: 50%;\n" +
                "            margin-left: 8px;\n" +
                "            animation: pulse 2s infinite;\n" +
                "            box-shadow: 0 0 10px var(--success-color);\n" +
                "        }\n" +
                "        \n" +
                "        @keyframes fadeInDown {\n" +
                "            from {\n" +
                "                opacity: 0;\n" +
                "                transform: translateY(-20px);\n" +
                "            }\n" +
                "            to {\n" +
                "                opacity: 1;\n" +
                "                transform: translateY(0);\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        @keyframes fadeInUp {\n" +
                "            from {\n" +
                "                opacity: 0;\n" +
                "                transform: translateY(20px);\n" +
                "            }\n" +
                "            to {\n" +
                "                opacity: 1;\n" +
                "                transform: translateY(0);\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        @keyframes pulse {\n" +
                "            0%, 100% {\n" +
                "                opacity: 1;\n" +
                "                transform: scale(1);\n" +
                "            }\n" +
                "            50% {\n" +
                "                opacity: 0.6;\n" +
                "                transform: scale(1.1);\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        @media (max-width: 768px) {\n" +
                "            .header h1 {\n" +
                "                font-size: 2em;\n" +
                "            }\n" +
                "            \n" +
                "            .language-selector {\n" +
                "                position: static;\n" +
                "                justify-content: center;\n" +
                "                margin-top: 15px;\n" +
                "            }\n" +
                "            \n" +
                "            .info-grid {\n" +
                "                grid-template-columns: 1fr;\n" +
                "            }\n" +
                "            \n" +
                "            .card {\n" +
                "                padding: 20px;\n" +
                "            }\n" +
                "            \n" +
                "            .info-row {\n" +
                "                flex-direction: column;\n" +
                "                align-items: flex-start;\n" +
                "                gap: 4px;\n" +
                "            }\n" +
                "            \n" +
                "            .info-value {\n" +
                "                text-align: left;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <div class=\"language-selector\">\n" +
                "                <button class=\"lang-btn active\" onclick=\"setLanguage('en')\">English</button>\n" +
                "                <button class=\"lang-btn\" onclick=\"setLanguage('zh')\">中文</button>\n" +
                "            </div>\n" +
                "            <h1 id=\"title\">🌙 Lucid Dreaming</h1>\n" +
                "            <p id=\"subtitle\">Real-time Minecraft Game Information</p>\n" +
                "            <p class=\"subtitle\" id=\"auto-refresh\">Auto-refreshing <span class=\"refresh-indicator\"></span></p>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"info-grid\">\n" +
                "            <div class=\"card\">\n" +
                "                <h2><span class=\"icon\">👤</span> <span id=\"player-info-title\">Player Information</span></h2>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-name\">Name:</span>\n" +
                "                    <span class=\"info-value\" id=\"playerName\">Loading...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-health\">Health:</span>\n" +
                "                    <span class=\"info-value\" id=\"playerHealth\">Loading...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-hunger\">Hunger:</span>\n" +
                "                    <span class=\"info-value\" id=\"playerHunger\">Loading...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-position\">Position:</span>\n" +
                "                    <span class=\"info-value\" id=\"playerPosition\">Loading...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-dimension\">Dimension:</span>\n" +
                "                    <span class=\"info-value\" id=\"playerDimension\">Loading...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-experience\">Experience:</span>\n" +
                "                    <span class=\"info-value\" id=\"playerExperience\">Loading...</span>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            \n" +
                "            <div class=\"card\">\n" +
                "                <h2><span class=\"icon\">🎮</span> <span id=\"game-info-title\">Game Information</span></h2>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-mc-version\">Minecraft Version:</span>\n" +
                "                    <span class=\"info-value\" id=\"mcVersion\">Loading...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-forge-version\">Forge Version:</span>\n" +
                "                    <span class=\"info-value\" id=\"forgeVersion\">Loading...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-fps\">FPS:</span>\n" +
                "                    <span class=\"info-value\" id=\"fps\">Loading...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-uptime\">Uptime:</span>\n" +
                "                    <span class=\"info-value\" id=\"uptime\">Loading...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-current-time\">Current Time:</span>\n" +
                "                    <span class=\"info-value\" id=\"currentTime\">Loading...</span>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            \n" +
                "            <div class=\"card\">\n" +
                "                <h2><span class=\"icon\">🌐</span> <span id=\"server-info-title\">Server Information</span></h2>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-type\">Type:</span>\n" +
                "                    <span class=\"info-value\" id=\"serverType\">Loading...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-address\">Address:</span>\n" +
                "                    <span class=\"info-value\" id=\"serverAddress\">Loading...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-server-name\">Name:</span>\n" +
                "                    <span class=\"info-value\" id=\"serverName\">Loading...</span>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            \n" +
                "            <div class=\"card\">\n" +
                "                <h2><span class=\"icon\">💻</span> <span id=\"system-info-title\">System Information</span></h2>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-os\">OS:</span>\n" +
                "                    <span class=\"info-value\" id=\"osName\">Loading...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"label-java-version\">Java Version:</span>\n" +
                "                    <span class=\"info-value\" id=\"javaVersion\">Loading...</span>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"scoreboard-container\">\n" +
                "            <h2>📊 <span id=\"scoreboard-title\">Scoreboard</span></h2>\n" +
                "            <ul class=\"scoreboard-list\" id=\"scoreboardList\">\n" +
                "                <li id=\"loading-scoreboard\">Loading scoreboard...</li>\n" +
                "            </ul>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"footer\">\n" +
                "            <p id=\"footer\">Lucid Dreaming Mod v1.0.0 | Auto-refreshing every 1 second</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    \n" +
                "    <script>\n" +
                "        // Language translations\n" +
                "        const translations = {\n" +
                "            en: {\n" +
                "                title: '🌙 Lucid Dreaming',\n" +
                "                subtitle: 'Real-time Minecraft Game Information',\n" +
                "                autoRefresh: 'Auto-refreshing ',\n" +
                "                playerInfo: 'Player Information',\n" +
                "                gameInfo: 'Game Information',\n" +
                "                serverInfo: 'Server Information',\n" +
                "                systemInfo: 'System Information',\n" +
                "                scoreboard: 'Scoreboard',\n" +
                "                loadingScoreboard: 'Loading scoreboard...',\n" +
                "                footer: 'Lucid Dreaming Mod v1.0.0 | Auto-refreshing every 1 second',\n" +
                "                labels: {\n" +
                "                    name: 'Name:',\n" +
                "                    health: 'Health:',\n" +
                "                    hunger: 'Hunger:',\n" +
                "                    position: 'Position:',\n" +
                "                    dimension: 'Dimension:',\n" +
                "                    experience: 'Experience:',\n" +
                "                    mcVersion: 'Minecraft Version:',\n" +
                "                    forgeVersion: 'Forge Version:',\n" +
                "                    fps: 'FPS:',\n" +
                "                    uptime: 'Uptime:',\n" +
                "                    currentTime: 'Current Time:',\n" +
                "                    type: 'Type:',\n" +
                "                    address: 'Address:',\n" +
                "                    serverName: 'Name:',\n" +
                "                    os: 'OS:',\n" +
                "                    javaVersion: 'Java Version:'\n" +
                "                },\n" +
                "                dimensionNames: {\n" +
                "                    'Overworld': 'Overworld',\n" +
                "                    'Nether': 'Nether',\n" +
                "                    'End': 'The End'\n" +
                "                }\n" +
                "            },\n" +
                "            zh: {\n" +
                "                title: '🌙 清醒梦',\n" +
                "                subtitle: 'Minecraft 实时游戏信息',\n" +
                "                autoRefresh: '自动刷新中 ',\n" +
                "                playerInfo: '玩家信息',\n" +
                "                gameInfo: '游戏信息',\n" +
                "                serverInfo: '服务器信息',\n" +
                "                systemInfo: '系统信息',\n" +
                "                scoreboard: '计分板',\n" +
                "                loadingScoreboard: '加载计分板中...',\n" +
                "                footer: '清醒梦 Mod v1.0.0 | 每秒自动刷新',\n" +
                "                labels: {\n" +
                "                    name: '名称:',\n" +
                "                    health: '生命值:',\n" +
                "                    hunger: '饥饿度:',\n" +
                "                    position: '位置:',\n" +
                "                    dimension: '维度:',\n" +
                "                    experience: '经验等级:',\n" +
                "                    mcVersion: 'Minecraft 版本:',\n" +
                "                    forgeVersion: 'Forge 版本:',\n" +
                "                    fps: '帧率:',\n" +
                "                    uptime: '运行时间:',\n" +
                "                    currentTime: '当前时间:',\n" +
                "                    type: '类型:',\n" +
                "                    address: '地址:',\n" +
                "                    serverName: '名称:',\n" +
                "                    os: '操作系统:',\n" +
                "                    javaVersion: 'Java 版本:'\n" +
                "                },\n" +
                "                dimensionNames: {\n" +
                "                    'Overworld': '主世界',\n" +
                "                    'Nether': '下界',\n" +
                "                    'End': '末地'\n" +
                "                }\n" +
                "            }\n" +
                "        };\n" +
                "        \n" +
                "        let currentLang = localStorage.getItem('lang') || 'en';\n" +
                "        \n" +
                "        function setLanguage(lang) {\n" +
                "            currentLang = lang;\n" +
                "            localStorage.setItem('lang', lang);\n" +
                "            \n" +
                "            // Update active button\n" +
                "            document.querySelectorAll('.lang-btn').forEach(btn => btn.classList.remove('active'));\n" +
                "            event.target.classList.add('active');\n" +
                "            \n" +
                "            // Update translations\n" +
                "            const t = translations[lang];\n" +
                "            document.getElementById('title').textContent = t.title;\n" +
                "            document.getElementById('subtitle').textContent = t.subtitle;\n" +
                "            document.getElementById('auto-refresh').innerHTML = t.autoRefresh + '<span class=\"refresh-indicator\"></span>';\n" +
                "            document.getElementById('player-info-title').textContent = t.playerInfo;\n" +
                "            document.getElementById('game-info-title').textContent = t.gameInfo;\n" +
                "            document.getElementById('server-info-title').textContent = t.serverInfo;\n" +
                "            document.getElementById('system-info-title').textContent = t.systemInfo;\n" +
                "            document.getElementById('scoreboard-title').textContent = t.scoreboard;\n" +
                "            document.getElementById('loading-scoreboard').textContent = t.loadingScoreboard;\n" +
                "            document.getElementById('footer').textContent = t.footer;\n" +
                "            \n" +
                "            // Update labels\n" +
                "            for (const [key, value] of Object.entries(t.labels)) {\n" +
                "                const elementId = 'label-' + key.replace(/[A-Z]/g, m => '-' + m.toLowerCase());\n" +
                "                const element = document.getElementById(elementId);\n" +
                "                if (element) {\n" +
                "                    element.textContent = value;\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            // Refresh data\n" +
                "            updateInfo();\n" +
                "        }\n" +
                "        \n" +
                "        // Parse Minecraft color codes\n" +
                "        function parseColorCodes(text) {\n" +
                "            if (!text) return '';\n" +
                "            let result = text;\n" +
                "            const colorMap = {\n" +
                "                '0': 'color-0', '1': 'color-1', '2': 'color-2', '3': 'color-3',\n" +
                "                '4': 'color-4', '5': 'color-5', '6': 'color-6', '7': 'color-7',\n" +
                "                '8': 'color-8', '9': 'color-9', 'a': 'color-a', 'b': 'color-b',\n" +
                "                'c': 'color-c', 'd': 'color-d', 'e': 'color-e', 'f': 'color-f',\n" +
                "                'l': 'color-l', 'm': 'color-m', 'n': 'color-n', 'o': 'color-o',\n" +
                "                'r': 'color-r'\n" +
                "            };\n" +
                "            \n" +
                "            let parts = [];\n" +
                "            let currentClasses = [];\n" +
                "            let i = 0;\n" +
                "            \n" +
                "            while (i < result.length) {\n" +
                "                if (result[i] === '§' && i + 1 < result.length) {\n" +
                "                    const code = result[i + 1].toLowerCase();\n" +
                "                    if (colorMap[code]) {\n" +
                "                        if (code === 'r') {\n" +
                "                            currentClasses = [];\n" +
                "                        } else if (code >= '0' && code <= 'f') {\n" +
                "                            currentClasses = currentClasses.filter(c => !c.startsWith('color-'));\n" +
                "                            currentClasses.push(colorMap[code]);\n" +
                "                        } else {\n" +
                "                            currentClasses.push(colorMap[code]);\n" +
                "                        }\n" +
                "                        i += 2;\n" +
                "                        continue;\n" +
                "                    }\n" +
                "                }\n" +
                "                \n" +
                "                if (currentClasses.length > 0) {\n" +
                "                    parts.push(`<span class=\"${currentClasses.join(' ')}\">${result[i]}</span>`);\n" +
                "                } else {\n" +
                "                    parts.push(result[i]);\n" +
                "                }\n" +
                "                i++;\n" +
                "            }\n" +
                "            \n" +
                "            return parts.join('');\n" +
                "        }\n" +
                "        \n" +
                "        function updateInfo() {\n" +
                "            fetch('/api/json')\n" +
                "                .then(response => response.json())\n" +
                "                .then(data => {\n" +
                "                    const t = translations[currentLang];\n" +
                "                    \n" +
                "                    // Player info\n" +
                "                    document.getElementById('playerName').textContent = data.player.name;\n" +
                "                    document.getElementById('playerHealth').textContent = data.player.health + '/' + data.player.maxHealth;\n" +
                "                    document.getElementById('playerHunger').textContent = data.player.hunger + '/20';\n" +
                "                    document.getElementById('playerPosition').textContent = data.player.position;\n" +
                "                    \n" +
                "                    // Translate dimension name\n" +
                "                    const dimensionName = t.dimensionNames[data.player.dimension] || data.player.dimension;\n" +
                "                    document.getElementById('playerDimension').textContent = dimensionName;\n" +
                "                    \n" +
                "                    document.getElementById('playerExperience').textContent = (currentLang === 'zh' ? '等级 ' : 'Level ') + data.player.experienceLevel;\n" +
                "                    \n" +
                "                    // Game info\n" +
                "                    document.getElementById('mcVersion').textContent = data.game.minecraftVersion;\n" +
                "                    document.getElementById('forgeVersion').textContent = data.game.forgeVersion;\n" +
                "                    document.getElementById('fps').textContent = data.game.fps;\n" +
                "                    document.getElementById('uptime').textContent = data.game.uptime;\n" +
                "                    document.getElementById('currentTime').textContent = data.game.currentTime;\n" +
                "                    \n" +
                "                    // Server info\n" +
                "                    document.getElementById('serverType').textContent = data.server.type;\n" +
                "                    document.getElementById('serverAddress').textContent = data.server.address;\n" +
                "                    document.getElementById('serverName').textContent = data.server.name;\n" +
                "                    \n" +
                "                    // System info\n" +
                "                    document.getElementById('osName').textContent = data.system.osName + ' ' + data.system.osVersion;\n" +
                "                    document.getElementById('javaVersion').textContent = data.system.javaVersion;\n" +
                "                    \n" +
                "                    // Scoreboard with color codes\n" +
                "                    const scoreboardList = document.getElementById('scoreboardList');\n" +
                "                    scoreboardList.innerHTML = '';\n" +
                "                    data.scoreboard.forEach(line => {\n" +
                "                        const li = document.createElement('li');\n" +
                "                        li.innerHTML = parseColorCodes(line);\n" +
                "                        scoreboardList.appendChild(li);\n" +
                "                    });\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('Error fetching data:', error);\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        // Initialize language\n" +
                "        document.addEventListener('DOMContentLoaded', function() {\n" +
                "            const langButtons = document.querySelectorAll('.lang-btn');\n" +
                "            langButtons.forEach(btn => {\n" +
                "                if (btn.textContent.includes(currentLang === 'en' ? 'English' : '中文')) {\n" +
                "                    btn.classList.add('active');\n" +
                "                } else {\n" +
                "                    btn.classList.remove('active');\n" +
                "                }\n" +
                "            });\n" +
                "            \n" +
                "            // Apply initial translations\n" +
                "            const t = translations[currentLang];\n" +
                "            document.getElementById('title').textContent = t.title;\n" +
                "            document.getElementById('subtitle').textContent = t.subtitle;\n" +
                "            document.getElementById('auto-refresh').innerHTML = t.autoRefresh + '<span class=\"refresh-indicator\"></span>';\n" +
                "            document.getElementById('player-info-title').textContent = t.playerInfo;\n" +
                "            document.getElementById('game-info-title').textContent = t.gameInfo;\n" +
                "            document.getElementById('server-info-title').textContent = t.serverInfo;\n" +
                "            document.getElementById('system-info-title').textContent = t.systemInfo;\n" +
                "            document.getElementById('scoreboard-title').textContent = t.scoreboard;\n" +
                "            document.getElementById('loading-scoreboard').textContent = t.loadingScoreboard;\n" +
                "            document.getElementById('footer').textContent = t.footer;\n" +
                "            \n" +
                "            for (const [key, value] of Object.entries(t.labels)) {\n" +
                "                const elementId = 'label-' + key.replace(/[A-Z]/g, m => '-' + m.toLowerCase());\n" +
                "                const element = document.getElementById(elementId);\n" +
                "                if (element) {\n" +
                "                    element.textContent = value;\n" +
                "                }\n" +
                "            }\n" +
                "        });\n" +
                "        \n" +
                "        // Initial load\n" +
                "        updateInfo();\n" +
                "        \n" +
                "        // Update every 1 second\n" +
                "        setInterval(updateInfo, 1000);\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }
}