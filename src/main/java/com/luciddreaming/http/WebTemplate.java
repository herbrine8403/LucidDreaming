package com.luciddreaming.http;

public class WebTemplate {
    public static String generateHTML() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"zh-CN\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>控制面板 - Lucid Dreaming</title>\n" +
                "    <style>\n" +
                "        :root {\n" +
                "            /* 浅色主题 */\n" +
                "            --bg-primary: #ffffff;\n" +
                "            --bg-secondary: #f8fafc;\n" +
                "            --bg-card: #ffffff;\n" +
                "            --bg-hover: #f1f5f9;\n" +
                "            --text-primary: #1e293b;\n" +
                "            --text-secondary: #64748b;\n" +
                "            --text-muted: #94a3b8;\n" +
                "            --border-color: #e2e8f0;\n" +
                "            --border-hover: #cbd5e1;\n" +
                "            \n" +
                "            /* 主题色 - 浅绿和浅蓝 */\n" +
                "            --accent-green: #10b981;\n" +
                "            --accent-green-light: #d1fae5;\n" +
                "            --accent-blue: #0ea5e9;\n" +
                "            --accent-blue-light: #e0f2fe;\n" +
                "            --accent-primary: #0ea5e9;\n" +
                "            --accent-primary-hover: #0284c7;\n" +
                "            \n" +
                "            /* 状态色 */\n" +
                "            --success: #10b981;\n" +
                "            --success-bg: #d1fae5;\n" +
                "            --error: #ef4444;\n" +
                "            --error-bg: #fee2e2;\n" +
                "            --warning: #f59e0b;\n" +
                "            --warning-bg: #fef3c7;\n" +
                "            \n" +
                "            /* 阴影 */\n" +
                "            --shadow-sm: 0 1px 2px 0 rgb(0 0 0 / 0.05);\n" +
                "            --shadow-md: 0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1);\n" +
                "            --shadow-lg: 0 10px 15px -3px rgb(0 0 0 / 0.1), 0 4px 6px -4px rgb(0 0 0 / 0.1);\n" +
                "            \n" +
                "            /* 圆角 */\n" +
                "            --radius-sm: 6px;\n" +
                "            --radius-md: 8px;\n" +
                "            --radius-lg: 12px;\n" +
                "            --radius-xl: 16px;\n" +
                "            \n" +
                "            /* 间距 */\n" +
                "            --spacing-xs: 0.25rem;\n" +
                "            --spacing-sm: 0.5rem;\n" +
                "            --spacing-md: 1rem;\n" +
                "            --spacing-lg: 1.5rem;\n" +
                "            --spacing-xl: 2rem;\n" +
                "            \n" +
                "            /* 字体 */\n" +
                "            --font-sans: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Noto Sans', Helvetica, Arial, sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji';\n" +
                "            \n" +
                "            /* 过渡 */\n" +
                "            --transition-fast: 150ms ease;\n" +
                "            --transition-base: 200ms ease;\n" +
                "            --transition-slow: 300ms ease;\n" +
                "        }\n" +
                "        \n" +
                "        [data-theme=\"dark\"] {\n" +
                "            /* 深色主题 */\n" +
                "            --bg-primary: #0f172a;\n" +
                "            --bg-secondary: #1e293b;\n" +
                "            --bg-card: #1e293b;\n" +
                "            --bg-hover: #334155;\n" +
                "            --text-primary: #f1f5f9;\n" +
                "            --text-secondary: #cbd5e1;\n" +
                "            --text-muted: #64748b;\n" +
                "            --border-color: #334155;\n" +
                "            --border-hover: #475569;\n" +
                "            \n" +
                "            --shadow-sm: 0 1px 2px 0 rgb(0 0 0 / 0.3);\n" +
                "            --shadow-md: 0 4px 6px -1px rgb(0 0 0 / 0.4), 0 2px 4px -2px rgb(0 0 0 / 0.3);\n" +
                "            --shadow-lg: 0 10px 15px -3px rgb(0 0 0 / 0.4), 0 4px 6px -4px rgb(0 0 0 / 0.3);\n" +
                "        }\n" +
                "        \n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "        \n" +
                "        html {\n" +
                "            font-size: 16px;\n" +
                "            -webkit-font-smoothing: antialiased;\n" +
                "            -moz-osx-font-smoothing: grayscale;\n" +
                "        }\n" +
                "        \n" +
                "        body {\n" +
                "            font-family: var(--font-sans);\n" +
                "            background-color: var(--bg-primary);\n" +
                "            color: var(--text-primary);\n" +
                "            line-height: 1.6;\n" +
                "            min-height: 100vh;\n" +
                "            transition: background-color var(--transition-base), color var(--transition-base);\n" +
                "        }\n" +
                "        \n" +
                "        .container {\n" +
                "            max-width: 1400px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: var(--spacing-lg);\n" +
                "        }\n" +
                "        \n" +
                "        /* 头部样式 */\n" +
                "        .header {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "            align-items: center;\n" +
                "            margin-bottom: var(--spacing-xl);\n" +
                "            padding-bottom: var(--spacing-lg);\n" +
                "            border-bottom: 1px solid var(--border-color);\n" +
                "            flex-wrap: wrap;\n" +
                "            gap: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        .header-left {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        .header-title {\n" +
                "            font-size: 1.75rem;\n" +
                "            font-weight: 700;\n" +
                "            color: var(--text-primary);\n" +
                "            letter-spacing: -0.5px;\n" +
                "        }\n" +
                "        \n" +
                "        .header-subtitle {\n" +
                "            font-size: 0.875rem;\n" +
                "            color: var(--text-secondary);\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .status-dot {\n" +
                "            width: 8px;\n" +
                "            height: 8px;\n" +
                "            background-color: var(--success);\n" +
                "            border-radius: 50%;\n" +
                "            animation: pulse 2s ease-in-out infinite;\n" +
                "        }\n" +
                "        \n" +
                "        @keyframes pulse {\n" +
                "            0%, 100% { opacity: 1; }\n" +
                "            50% { opacity: 0.5; }\n" +
                "        }\n" +
                "        \n" +
                "        .header-controls {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .btn {\n" +
                "            display: inline-flex;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "            gap: var(--spacing-sm);\n" +
                "            padding: var(--spacing-sm) var(--spacing-md);\n" +
                "            font-size: 0.875rem;\n" +
                "            font-weight: 500;\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            background-color: var(--bg-card);\n" +
                "            color: var(--text-primary);\n" +
                "            cursor: pointer;\n" +
                "            transition: all var(--transition-fast);\n" +
                "            white-space: nowrap;\n" +
                "        }\n" +
                "        \n" +
                "        .btn:hover {\n" +
                "            background-color: var(--bg-hover);\n" +
                "            border-color: var(--border-hover);\n" +
                "        }\n" +
                "        \n" +
                "        .btn:active {\n" +
                "            transform: translateY(1px);\n" +
                "        }\n" +
                "        \n" +
                "        .btn.active {\n" +
                "            background-color: var(--accent-primary);\n" +
                "            border-color: var(--accent-primary);\n" +
                "            color: white;\n" +
                "        }\n" +
                "        \n" +
                "        .btn-icon {\n" +
                "            width: 18px;\n" +
                "            height: 18px;\n" +
                "            flex-shrink: 0;\n" +
                "        }\n" +
                "        \n" +
                "        .btn svg {\n" +
                "            width: 100%;\n" +
                "            height: 100%;\n" +
                "        }\n" +
                "        \n" +
                "        .btn-divider {\n" +
                "            width: 1px;\n" +
                "            height: 20px;\n" +
                "            background-color: var(--border-color);\n" +
                "            margin: 0 var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        /* 网格布局 */\n" +
                "        .grid {\n" +
                "            display: grid;\n" +
                "            gap: var(--spacing-lg);\n" +
                "        }\n" +
                "        \n" +
                "        .grid-2 {\n" +
                "            grid-template-columns: repeat(2, 1fr);\n" +
                "        }\n" +
                "        \n" +
                "        .grid-3 {\n" +
                "            grid-template-columns: repeat(3, 1fr);\n" +
                "        }\n" +
                "        \n" +
                "        .grid-4 {\n" +
                "            grid-template-columns: repeat(4, 1fr);\n" +
                "        }\n" +
                "        \n" +
                "        .grid-auto {\n" +
                "            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));\n" +
                "        }\n" +
                "        \n" +
                "        /* 卡片样式 */\n" +
                "        .card {\n" +
                "            background-color: var(--bg-card);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-lg);\n" +
                "            padding: var(--spacing-lg);\n" +
                "            box-shadow: var(--shadow-sm);\n" +
                "            transition: all var(--transition-base);\n" +
                "        }\n" +
                "        \n" +
                "        .card:hover {\n" +
                "            box-shadow: var(--shadow-md);\n" +
                "        }\n" +
                "        \n" +
                "        .card-header {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: var(--spacing-sm);\n" +
                "            margin-bottom: var(--spacing-lg);\n" +
                "            padding-bottom: var(--spacing-md);\n" +
                "            border-bottom: 1px solid var(--border-color);\n" +
                "        }\n" +
                "        \n" +
                "        .card-icon {\n" +
                "            width: 20px;\n" +
                "            height: 20px;\n" +
                "            flex-shrink: 0;\n" +
                "            color: var(--accent-primary);\n" +
                "        }\n" +
                "        \n" +
                "        .card-title {\n" +
                "            font-size: 1.125rem;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "        }\n" +
                "        \n" +
                "        .card-link {\n" +
                "            margin-left: auto;\n" +
                "            font-size: 0.75rem;\n" +
                "            color: var(--accent-primary);\n" +
                "            text-decoration: none;\n" +
                "            font-weight: 500;\n" +
                "        }\n" +
                "        \n" +
                "        .card-link:hover {\n" +
                "            text-decoration: underline;\n" +
                "        }\n" +
                "        \n" +
                "        /* 信息行样式 */\n" +
                "        .info-row {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "            align-items: center;\n" +
                "            padding: var(--spacing-sm) 0;\n" +
                "            border-bottom: 1px solid var(--border-color);\n" +
                "        }\n" +
                "        \n" +
                "        .info-row:last-child {\n" +
                "            border-bottom: none;\n" +
                "        }\n" +
                "        \n" +
                "        .info-label {\n" +
                "            font-size: 0.875rem;\n" +
                "            color: var(--text-secondary);\n" +
                "            font-weight: 500;\n" +
                "        }\n" +
                "        \n" +
                "        .info-value {\n" +
                "            font-size: 0.875rem;\n" +
                "            color: var(--text-primary);\n" +
                "            font-weight: 600;\n" +
                "            text-align: right;\n" +
                "        }\n" +
                "        \n" +
                "        .info-value.accent {\n" +
                "            color: var(--accent-primary);\n" +
                "        }\n" +
                "        \n" +
                "        /* 模块卡片样式 */\n" +
                "        .module-card {\n" +
                "            background-color: var(--bg-card);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            padding: var(--spacing-md);\n" +
                "            transition: all var(--transition-fast);\n" +
                "        }\n" +
                "        \n" +
                "        .module-card:hover {\n" +
                "            border-color: var(--border-hover);\n" +
                "            box-shadow: var(--shadow-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .module-card.enabled {\n" +
                "            border-color: var(--success);\n" +
                "            background-color: var(--success-bg);\n" +
                "        }\n" +
                "        \n" +
                "        .module-card.enabled .module-name {\n" +
                "            color: var(--success);\n" +
                "        }\n" +
                "        \n" +
                "        .module-header {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "            align-items: center;\n" +
                "            margin-bottom: var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .module-name {\n" +
                "            font-size: 0.9375rem;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "        }\n" +
                "        \n" +
                "        .module-toggle {\n" +
                "            width: 40px;\n" +
                "            height: 20px;\n" +
                "            background-color: var(--border-color);\n" +
                "            border-radius: 10px;\n" +
                "            cursor: pointer;\n" +
                "            position: relative;\n" +
                "            transition: background-color var(--transition-fast);\n" +
                "        }\n" +
                "        \n" +
                "        .module-toggle::after {\n" +
                "            content: '';\n" +
                "            position: absolute;\n" +
                "            top: 2px;\n" +
                "            left: 2px;\n" +
                "            width: 16px;\n" +
                "            height: 16px;\n" +
                "            background-color: white;\n" +
                "            border-radius: 50%;\n" +
                "            transition: transform var(--transition-fast);\n" +
                "            box-shadow: 0 1px 2px rgb(0 0 0 / 0.2);\n" +
                "        }\n" +
                "        \n" +
                "        .module-toggle.enabled {\n" +
                "            background-color: var(--success);\n" +
                "        }\n" +
                "        \n" +
                "        .module-toggle.enabled::after {\n" +
                "            transform: translateX(20px);\n" +
                "        }\n" +
                "        \n" +
                "        .module-description {\n" +
                "            font-size: 0.8125rem;\n" +
                "            color: var(--text-secondary);\n" +
                "            margin-bottom: var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .module-meta {\n" +
                "            display: flex;\n" +
                "            gap: var(--spacing-sm);\n" +
                "            flex-wrap: wrap;\n" +
                "        }\n" +
                "        \n" +
                "        .module-tag {\n" +
                "            display: inline-flex;\n" +
                "            align-items: center;\n" +
                "            font-size: 0.75rem;\n" +
                "            padding: 2px 8px;\n" +
                "            border-radius: var(--radius-sm);\n" +
                "            font-weight: 500;\n" +
                "        }\n" +
                "        \n" +
                "        .module-tag.category {\n" +
                "            background-color: var(--accent-blue-light);\n" +
                "            color: var(--accent-blue);\n" +
                "        }\n" +
                "        \n" +
                "        [data-theme=\"dark\"] .module-tag.category {\n" +
                "            background-color: rgba(14, 165, 233, 0.2);\n" +
                "            color: var(--accent-blue);\n" +
                "        }\n" +
                "        \n" +
                "        .module-tag.keybind {\n" +
                "            background-color: var(--bg-secondary);\n" +
                "            color: var(--text-secondary);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "        }\n" +
                "        \n" +
                "        /* 输入框样式 */\n" +
                "        .input-group {\n" +
                "            display: flex;\n" +
                "            gap: var(--spacing-sm);\n" +
                "            margin-bottom: var(--spacing-md);\n" +
                "            flex-wrap: wrap;\n" +
                "        }\n" +
                "        \n" +
                "        .input-wrapper {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .input-label {\n" +
                "            font-size: 0.875rem;\n" +
                "            font-weight: 500;\n" +
                "            color: var(--text-secondary);\n" +
                "            min-width: 20px;\n" +
                "        }\n" +
                "        \n" +
                "        .input {\n" +
                "            width: 80px;\n" +
                "            padding: var(--spacing-sm) var(--spacing-sm);\n" +
                "            font-size: 0.875rem;\n" +
                "            color: var(--text-primary);\n" +
                "            background-color: var(--bg-secondary);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            transition: all var(--transition-fast);\n" +
                "        }\n" +
                "        \n" +
                "        .input:focus {\n" +
                "            outline: none;\n" +
                "            border-color: var(--accent-primary);\n" +
                "            box-shadow: 0 0 0 3px rgba(14, 165, 233, 0.1);\n" +
                "        }\n" +
                "        \n" +
                "        .btn-primary {\n" +
                "            background-color: var(--accent-primary);\n" +
                "            border-color: var(--accent-primary);\n" +
                "            color: white;\n" +
                "        }\n" +
                "        \n" +
                "        .btn-primary:hover {\n" +
                "            background-color: var(--accent-primary-hover);\n" +
                "            border-color: var(--accent-primary-hover);\n" +
                "        }\n" +
                "        \n" +
                "        /* 状态显示 */\n" +
                "        .status-box {\n" +
                "            padding: var(--spacing-md);\n" +
                "            background-color: var(--bg-secondary);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            border-left: 3px solid var(--accent-primary);\n" +
                "        }\n" +
                "        \n" +
                "        .status-box.active {\n" +
                "            border-left-color: var(--success);\n" +
                "        }\n" +
                "        \n" +
                "        .status-box.failed {\n" +
                "            border-left-color: var(--error);\n" +
                "        }\n" +
                "        \n" +
                "        .status-box.calculating {\n" +
                "            border-left-color: var(--warning);\n" +
                "        }\n" +
                "        \n" +
                "        .status-text {\n" +
                "            font-size: 0.875rem;\n" +
                "            color: var(--text-secondary);\n" +
                "            margin: var(--spacing-xs) 0;\n" +
                "        }\n" +
                "        \n" +
                "        .status-text strong {\n" +
                "            color: var(--text-primary);\n" +
                "        }\n" +
                "        \n" +
                "        /* 计分板样式 */\n" +
                "        .scoreboard-list {\n" +
                "            list-style: none;\n" +
                "            background-color: #808080;\n" +
                "            border-radius: var(--radius-md);\n" +
                "            padding: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        .scoreboard-list li {\n" +
                "            padding: var(--spacing-sm) var(--spacing-sm);\n" +
                "            border-bottom: 1px solid rgba(255, 255, 255, 0.15);\n" +
                "            font-family: 'Courier New', 'Consolas', monospace;\n" +
                "            font-size: 0.875rem;\n" +
                "            line-height: 1.5;\n" +
                "            color: white;\n" +
                "            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);\n" +
                "        }\n" +
                "        \n" +
                "        .scoreboard-list li:last-child {\n" +
                "            border-bottom: none;\n" +
                "        }\n" +
                "        \n" +
                "        /* 截图样式 */\n" +
                "        .screenshot-preview {\n" +
                "            margin-top: var(--spacing-md);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            overflow: hidden;\n" +
                "            box-shadow: var(--shadow-md);\n" +
                "            display: none;\n" +
                "        }\n" +
                "        \n" +
                "        .screenshot-preview img {\n" +
                "            width: 100%;\n" +
                "            height: auto;\n" +
                "            display: block;\n" +
                "        }\n" +
                "        \n" +
                "        /* Minecraft 颜色代码 */\n" +
                "        .mc-0 { color: #000000; }\n" +
                "        .mc-1 { color: #0000AA; }\n" +
                "        .mc-2 { color: #00AA00; }\n" +
                "        .mc-3 { color: #00AAAA; }\n" +
                "        .mc-4 { color: #AA0000; }\n" +
                "        .mc-5 { color: #AA00AA; }\n" +
                "        .mc-6 { color: #FFAA00; }\n" +
                "        .mc-7 { color: #AAAAAA; }\n" +
                "        .mc-8 { color: #555555; }\n" +
                "        .mc-9 { color: #5555FF; }\n" +
                "        .mc-a { color: #55FF55; }\n" +
                "        .mc-b { color: #55FFFF; }\n" +
                "        .mc-c { color: #FF5555; }\n" +
                "        .mc-d { color: #FF55FF; }\n" +
                "        .mc-e { color: #FFFF55; }\n" +
                "        .mc-f { color: #FFFFFF; }\n" +
                "        .mc-l { font-weight: bold; }\n" +
                "        .mc-m { text-decoration: line-through; }\n" +
                "        .mc-n { text-decoration: underline; }\n" +
                "        .mc-o { font-style: italic; }\n" +
                "        .mc-r { color: inherit; text-decoration: none; font-style: normal; font-weight: normal; }\n" +
                "        \n" +
                "        /* 页脚 */\n" +
                "        .footer {\n" +
                "            margin-top: var(--spacing-xl);\n" +
                "            padding-top: var(--spacing-lg);\n" +
                "            border-top: 1px solid var(--border-color);\n" +
                "            text-align: center;\n" +
                "            font-size: 0.8125rem;\n" +
                "            color: var(--text-muted);\n" +
                "        }\n" +
                "        \n" +
                "        .footer a {\n" +
                "            color: var(--accent-primary);\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "        \n" +
                "        .footer a:hover {\n" +
                "            text-decoration: underline;\n" +
                "        }\n" +
                "        \n" +
                "        /* 响应式设计 */\n" +
                "        @media (max-width: 1024px) {\n" +
                "            .grid-4 {\n" +
                "                grid-template-columns: repeat(2, 1fr);\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        @media (max-width: 768px) {\n" +
                "            .container {\n" +
                "                padding: var(--spacing-md);\n" +
                "            }\n" +
                "            \n" +
                "            .header {\n" +
                "                flex-direction: column;\n" +
                "                align-items: flex-start;\n" +
                "            }\n" +
                "            \n" +
                "            .header-controls {\n" +
                "                width: 100%;\n" +
                "                overflow-x: auto;\n" +
                "                padding-bottom: var(--spacing-sm);\n" +
                "            }\n" +
                "            \n" +
                "            .grid-2,\n" +
                "            .grid-3,\n" +
                "            .grid-4 {\n" +
                "                grid-template-columns: 1fr;\n" +
                "            }\n" +
                "            \n" +
                "            .header-title {\n" +
                "                font-size: 1.5rem;\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        @media (max-width: 480px) {\n" +
                "            .input-group {\n" +
                "                flex-direction: column;\n" +
                "            }\n" +
                "            \n" +
                "            .input-wrapper {\n" +
                "                width: 100%;\n" +
                "            }\n" +
                "            \n" +
                "            .input {\n" +
                "                width: 100%;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <!-- 头部 -->\n" +
                "        <header class=\"header\">\n" +
                "            <div class=\"header-left\">\n" +
                "                <div>\n" +
                "                    <h1 class=\"header-title\" id=\"pageTitle\">控制面板</h1>\n" +
                "                    <p class=\"header-subtitle\">\n" +
                "                        <span class=\"status-dot\"></span>\n" +
                "                        <span id=\"autoRefreshStatus\">自动刷新中</span>\n" +
                "                    </p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"header-controls\">\n" +
                "                <button class=\"btn\" onclick=\"setLanguage('zh')\" id=\"langZhBtn\">\n" +
                "                    <span class=\"btn-icon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <path d=\"M4 9h16M4 15h16M10 3L8 21M16 3l-2 18\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    中文\n" +
                "                </button>\n" +
                "                <button class=\"btn\" onclick=\"setLanguage('en')\" id=\"langEnBtn\">\n" +
                "                    <span class=\"btn-icon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <path d=\"M4 9h16M4 15h16M10 3L8 21M16 3l-2 18\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    English\n" +
                "                </button>\n" +
                "                <div class=\"btn-divider\"></div>\n" +
                "                <button class=\"btn\" onclick=\"toggleTheme()\" id=\"themeBtn\">\n" +
                "                    <span class=\"btn-icon\" id=\"themeIcon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <circle cx=\"12\" cy=\"12\" r=\"5\"/>\n" +
                "                            <path d=\"M12 1v2M12 21v2M4.22 4.22l1.42 1.42M18.36 18.36l1.42 1.42M1 12h2M21 12h2M4.22 19.78l1.42-1.42M18.36 5.64l1.42-1.42\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    <span id=\"themeText\">浅色</span>\n" +
                "                </button>\n" +
                "                <button class=\"btn\" onclick=\"takeScreenshot()\" id=\"screenshotBtn\">\n" +
                "                    <span class=\"btn-icon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <rect x=\"3\" y=\"3\" width=\"18\" height=\"18\" rx=\"2\" ry=\"2\"/>\n" +
                "                            <circle cx=\"8.5\" cy=\"8.5\" r=\"1.5\"/>\n" +
                "                            <path d=\"M21 15l-5-5L5 21\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    <span id=\"screenshotText\">截图</span>\n" +
                "                </button>\n" +
                "            </div>\n" +
                "        </header>\n" +
                "        \n" +
                "        <!-- 主要内容 -->\n" +
                "        <div class=\"grid grid-2\">\n" +
                "            <!-- 玩家信息 -->\n" +
                "            <div class=\"card\">\n" +
                "                <div class=\"card-header\">\n" +
                "                    <svg class=\"card-icon\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                        <path d=\"M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2\"/>\n" +
                "                        <circle cx=\"12\" cy=\"7\" r=\"4\"/>\n" +
                "                    </svg>\n" +
                "                    <h2 class=\"card-title\" id=\"playerInfoTitle\">玩家信息</h2>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelName\">名称</span>\n" +
                "                    <span class=\"info-value\" id=\"playerName\">加载中...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelHealth\">生命值</span>\n" +
                "                    <span class=\"info-value accent\" id=\"playerHealth\">加载中...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelHunger\">饥饿度</span>\n" +
                "                    <span class=\"info-value accent\" id=\"playerHunger\">加载中...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelPosition\">位置</span>\n" +
                "                    <span class=\"info-value\" id=\"playerPosition\">加载中...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelDimension\">维度</span>\n" +
                "                    <span class=\"info-value\" id=\"playerDimension\">加载中...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelExperience\">经验等级</span>\n" +
                "                    <span class=\"info-value accent\" id=\"playerExperience\">加载中...</span>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            \n" +
                "            <!-- 游戏信息 -->\n" +
                "            <div class=\"card\">\n" +
                "                <div class=\"card-header\">\n" +
                "                    <svg class=\"card-icon\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                        <rect x=\"2\" y=\"3\" width=\"20\" height=\"14\" rx=\"2\" ry=\"2\"/>\n" +
                "                        <line x1=\"8\" y1=\"21\" x2=\"16\" y2=\"21\"/>\n" +
                "                        <line x1=\"12\" y1=\"17\" x2=\"12\" y2=\"21\"/>\n" +
                "                    </svg>\n" +
                "                    <h2 class=\"card-title\" id=\"gameInfoTitle\">游戏信息</h2>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelMcVersion\">Minecraft 版本</span>\n" +
                "                    <span class=\"info-value\" id=\"mcVersion\">加载中...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelForgeVersion\">Forge 版本</span>\n" +
                "                    <span class=\"info-value\" id=\"forgeVersion\">加载中...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelFps\">帧率</span>\n" +
                "                    <span class=\"info-value accent\" id=\"fps\">加载中...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelUptime\">运行时间</span>\n" +
                "                    <span class=\"info-value\" id=\"uptime\">加载中...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelCurrentTime\">当前时间</span>\n" +
                "                    <span class=\"info-value\" id=\"currentTime\">加载中...</span>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"grid grid-2\">\n" +
                "            <!-- 服务器信息 -->\n" +
                "            <div class=\"card\">\n" +
                "                <div class=\"card-header\">\n" +
                "                    <svg class=\"card-icon\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                        <rect x=\"2\" y=\"2\" width=\"20\" height=\"8\" rx=\"2\" ry=\"2\"/>\n" +
                "                        <rect x=\"2\" y=\"14\" width=\"20\" height=\"8\" rx=\"2\" ry=\"2\"/>\n" +
                "                        <line x1=\"6\" y1=\"6\" x2=\"6.01\" y2=\"6\"/>\n" +
                "                        <line x1=\"6\" y1=\"18\" x2=\"6.01\" y2=\"18\"/>\n" +
                "                    </svg>\n" +
                "                    <h2 class=\"card-title\" id=\"serverInfoTitle\">服务器信息</h2>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelType\">类型</span>\n" +
                "                    <span class=\"info-value\" id=\"serverType\">加载中...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelAddress\">地址</span>\n" +
                "                    <span class=\"info-value\" id=\"serverAddress\">加载中...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelServerName\">名称</span>\n" +
                "                    <span class=\"info-value\" id=\"serverName\">加载中...</span>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            \n" +
                "            <!-- 系统信息 -->\n" +
                "            <div class=\"card\">\n" +
                "                <div class=\"card-header\">\n" +
                "                    <svg class=\"card-icon\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                        <rect x=\"2\" y=\"3\" width=\"20\" height=\"14\" rx=\"2\" ry=\"2\"/>\n" +
                "                        <line x1=\"8\" y1=\"21\" x2=\"16\" y2=\"21\"/>\n" +
                "                        <line x1=\"12\" y1=\"17\" x2=\"12\" y2=\"21\"/>\n" +
                "                    </svg>\n" +
                "                    <h2 class=\"card-title\" id=\"systemInfoTitle\">系统信息</h2>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelOs\">操作系统</span>\n" +
                "                    <span class=\"info-value\" id=\"osName\">加载中...</span>\n" +
                "                </div>\n" +
                "                <div class=\"info-row\">\n" +
                "                    <span class=\"info-label\" id=\"labelJavaVersion\">Java 版本</span>\n" +
                "                    <span class=\"info-value\" id=\"javaVersion\">加载中...</span>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        \n" +
                "        <!-- 模块控制 -->\n" +
                "        <div class=\"card\">\n" +
                "            <div class=\"card-header\">\n" +
                "                <svg class=\"card-icon\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                    <circle cx=\"12\" cy=\"12\" r=\"3\"/>\n" +
                "                    <path d=\"M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z\"/>\n" +
                "                </svg>\n" +
                "                <h2 class=\"card-title\" id=\"modulesTitle\">模块控制</h2>\n" +
                "                <a href=\"/config\" class=\"card-link\" id=\"editConfigLink\">[编辑配置]</a>\n" +
                "            </div>\n" +
                "            <div class=\"grid grid-auto\" id=\"moduleGrid\">\n" +
                "                <div id=\"loadingModules\">加载模块中...</div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"grid grid-2\">\n" +
                "            <!-- 自动行走 -->\n" +
                "            <div class=\"card\">\n" +
                "                <div class=\"card-header\">\n" +
                "                    <svg class=\"card-icon\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                        <path d=\"M4 15s1-1 4-1 5 2 8 2 4-1 4-1V3s-1 1-4 1-5-2-8-2-4 1-4 1z\"/>\n" +
                "                        <line x1=\"4\" y1=\"22\" x2=\"4\" y2=\"15\"/>\n" +
                "                    </svg>\n" +
                "                    <h2 class=\"card-title\" id=\"autowalkTitle\">自动行走</h2>\n" +
                "                </div>\n" +
                "                <div class=\"input-group\">\n" +
                "                    <div class=\"input-wrapper\">\n" +
                "                        <span class=\"input-label\">X:</span>\n" +
                "                        <input type=\"number\" class=\"input\" id=\"targetX\" placeholder=\"X\" />\n" +
                "                    </div>\n" +
                "                    <div class=\"input-wrapper\">\n" +
                "                        <span class=\"input-label\">Y:</span>\n" +
                "                        <input type=\"number\" class=\"input\" id=\"targetY\" placeholder=\"Y\" />\n" +
                "                    </div>\n" +
                "                    <div class=\"input-wrapper\">\n" +
                "                        <span class=\"input-label\">Z:</span>\n" +
                "                        <input type=\"number\" class=\"input\" id=\"targetZ\" placeholder=\"Z\" />\n" +
                "                    </div>\n" +
                "                    <button class=\"btn btn-primary\" onclick=\"setAutoWalkTarget()\" id=\"setTargetBtn\">设置目标</button>\n" +
                "                    <button class=\"btn\" onclick=\"clearAutoWalkTarget()\" id=\"clearTargetBtn\">清除</button>\n" +
                "                </div>\n" +
                "                <div class=\"status-box\" id=\"autowalkStatus\">\n" +
                "                    <p class=\"status-text\">状态: <strong id=\"autowalkStatusText\">未激活</strong></p>\n" +
                "                    <p class=\"status-text\" id=\"autowalkTargetInfo\"></p>\n" +
                "                    <p class=\"status-text\" id=\"pathfindingStatus\"></p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            \n" +
                "            <!-- 截图 -->\n" +
                "            <div class=\"card\">\n" +
                "                <div class=\"card-header\">\n" +
                "                    <svg class=\"card-icon\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                        <rect x=\"3\" y=\"3\" width=\"18\" height=\"18\" rx=\"2\" ry=\"2\"/>\n" +
                "                        <circle cx=\"8.5\" cy=\"8.5\" r=\"1.5\"/>\n" +
                "                        <path d=\"M21 15l-5-5L5 21\"/>\n" +
                "                    </svg>\n" +
                "                    <h2 class=\"card-title\" id=\"screenshotTitle\">截图</h2>\n" +
                "                </div>\n" +
                "                <button class=\"btn btn-primary\" onclick=\"takeScreenshot()\" id=\"screenshotBtn2\">\n" +
                "                    <span class=\"btn-icon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <rect x=\"3\" y=\"3\" width=\"18\" height=\"18\" rx=\"2\" ry=\"2\"/>\n" +
                "                            <circle cx=\"8.5\" cy=\"8.5\" r=\"1.5\"/>\n" +
                "                            <path d=\"M21 15l-5-5L5 21\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    <span id=\"takeScreenshotText\">拍摄截图</span>\n" +
                "                </button>\n" +
                "                <div class=\"screenshot-preview\" id=\"screenshotPreview\">\n" +
                "                    <img id=\"screenshotImage\" alt=\"游戏截图\">\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        \n" +
                "        <!-- 计分板 -->\n" +
                "        <div class=\"card\">\n" +
                "            <div class=\"card-header\">\n" +
                "                <svg class=\"card-icon\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                    <line x1=\"18\" y1=\"20\" x2=\"18\" y2=\"10\"/>\n" +
                "                    <line x1=\"12\" y1=\"20\" x2=\"12\" y2=\"4\"/>\n" +
                "                    <line x1=\"6\" y1=\"20\" x2=\"6\" y2=\"14\"/>\n" +
                "                </svg>\n" +
                "                <h2 class=\"card-title\" id=\"scoreboardTitle\">计分板</h2>\n" +
                "            </div>\n" +
                "            <ul class=\"scoreboard-list\" id=\"scoreboardList\">\n" +
                "                <li id=\"loadingScoreboard\">加载计分板中...</li>\n" +
                "            </ul>\n" +
                "        </div>\n" +
                "        \n" +
                "        <!-- 页脚 -->\n" +
                "        <footer class=\"footer\">\n" +
                "            <p id=\"footerText\">Lucid Dreaming Mod v1.0.0 | 每秒自动刷新</p>\n" +
                "        </footer>\n" +
                "    </div>\n" +
                "    \n" +
                "    <script>\n" +
                "        // 主题管理\n" +
                "        let currentTheme = localStorage.getItem('theme') || 'light';\n" +
                "        \n" +
                "        // 检测系统主题\n" +
                "        function getSystemTheme() {\n" +
                "            if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {\n" +
                "                return 'dark';\n" +
                "            }\n" +
                "            return 'light';\n" +
                "        }\n" +
                "        \n" +
                "        // 初始化主题\n" +
                "        function initTheme() {\n" +
                "            if (currentTheme === 'auto') {\n" +
                "                currentTheme = getSystemTheme();\n" +
                "            }\n" +
                "            applyTheme();\n" +
                "        }\n" +
                "        \n" +
                "        function toggleTheme() {\n" +
                "            const themes = ['light', 'dark', 'auto'];\n" +
                "            const currentIndex = themes.indexOf(localStorage.getItem('theme') || 'light');\n" +
                "            const nextIndex = (currentIndex + 1) % themes.length;\n" +
                "            currentTheme = themes[nextIndex];\n" +
                "            localStorage.setItem('theme', currentTheme);\n" +
                "            \n" +
                "            if (currentTheme === 'auto') {\n" +
                "                currentTheme = getSystemTheme();\n" +
                "            }\n" +
                "            \n" +
                "            applyTheme();\n" +
                "        }\n" +
                "        \n" +
                "        function applyTheme() {\n" +
                "            document.documentElement.setAttribute('data-theme', currentTheme);\n" +
                "            \n" +
                "            const themeBtn = document.getElementById('themeBtn');\n" +
                "            const themeText = document.getElementById('themeText');\n" +
                "            const themeIcon = document.getElementById('themeIcon');\n" +
                "            \n" +
                "            if (themeBtn && themeText && themeIcon) {\n" +
                "                const savedTheme = localStorage.getItem('theme') || 'light';\n" +
                "                \n" +
                "                if (savedTheme === 'auto') {\n" +
                "                    themeText.textContent = '跟随系统';\n" +
                "                    themeIcon.innerHTML = '<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\"><circle cx=\"12\" cy=\"12\" r=\"5\"/><path d=\"M12 1v2M12 21v2M4.22 4.22l1.42 1.42M18.36 18.36l1.42 1.42M1 12h2M21 12h2M4.22 19.78l1.42-1.42M18.36 5.64l1.42-1.42\"/></svg>';\n" +
                "                } else if (savedTheme === 'dark') {\n" +
                "                    themeText.textContent = '深色';\n" +
                "                    themeIcon.innerHTML = '<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\"><path d=\"M21 12.79A9 9 0 1 1 11.21 3 7 7 0 0 0 21 12.79z\"/></svg>';\n" +
                "                } else {\n" +
                "                    themeText.textContent = '浅色';\n" +
                "                    themeIcon.innerHTML = '<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\"><circle cx=\"12\" cy=\"12\" r=\"5\"/><path d=\"M12 1v2M12 21v2M4.22 4.22l1.42 1.42M18.36 18.36l1.42 1.42M1 12h2M21 12h2M4.22 19.78l1.42-1.42M18.36 5.64l1.42-1.42\"/></svg>';\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        // 监听系统主题变化\n" +
                "        if (window.matchMedia) {\n" +
                "            window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', (e) => {\n" +
                "                if (localStorage.getItem('theme') === 'auto') {\n" +
                "                    currentTheme = e.matches ? 'dark' : 'light';\n" +
                "                    applyTheme();\n" +
                "                }\n" +
                "            });\n" +
                "        }\n" +
                "        \n" +
                "        // 截图功能\n" +
                "        function takeScreenshot() {\n" +
                "            const btn1 = document.getElementById('screenshotBtn');\n" +
                "            const btn2 = document.getElementById('screenshotBtn2');\n" +
                "            const preview = document.getElementById('screenshotPreview');\n" +
                "            const img = document.getElementById('screenshotImage');\n" +
                "            \n" +
                "            if (btn1) btn1.disabled = true;\n" +
                "            if (btn2) btn2.disabled = true;\n" +
                "            \n" +
                "            fetch('/api/screenshot')\n" +
                "                .then(response => {\n" +
                "                    if (!response.ok) throw new Error('截图失败');\n" +
                "                    return response.blob();\n" +
                "                })\n" +
                "                .then(blob => {\n" +
                "                    const url = URL.createObjectURL(blob);\n" +
                "                    img.src = url;\n" +
                "                    preview.style.display = 'block';\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('截图错误:', error);\n" +
                "                    alert('截图失败: ' + error.message);\n" +
                "                })\n" +
                "                .finally(() => {\n" +
                "                    if (btn1) btn1.disabled = false;\n" +
                "                    if (btn2) btn2.disabled = false;\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        // 模块控制\n" +
                "        function loadModules() {\n" +
                "            fetch('/api/modules')\n" +
                "                .then(response => response.json())\n" +
                "                .then(data => {\n" +
                "                    renderModules(data.modules);\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('加载模块错误:', error);\n" +
                "                    const grid = document.getElementById('moduleGrid');\n" +
                "                    if (grid) grid.innerHTML = '<div style=\"color: var(--error); padding: var(--spacing-md);\">加载模块失败</div>';\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        function renderModules(modules) {\n" +
                "            const grid = document.getElementById('moduleGrid');\n" +
                "            if (!grid) return;\n" +
                "            \n" +
                "            grid.innerHTML = '';\n" +
                "            \n" +
                "            modules.forEach(module => {\n" +
                "                const card = document.createElement('div');\n" +
                "                card.className = 'module-card' + (module.enabled ? ' enabled' : '');\n" +
                "                const localizedName = module.localizedName || module.name;\n" +
                "                const localizedDescription = module.localizedDescription || module.description;\n" +
                "                const localizedCategory = module.localizedCategory || module.category;\n" +
                "                \n" +
                "                card.innerHTML = `\n" +
                "                    <div class=\"module-header\">\n" +
                "                        <span class=\"module-name\">${escapeHtml(localizedName)}</span>\n" +
                "                        <div class=\"module-toggle ${module.enabled ? 'enabled' : ''}\" onclick=\"toggleModule('${escapeHtml(module.name)}')\"></div>\n" +
                "                    </div>\n" +
                "                    <div class=\"module-description\">${escapeHtml(localizedDescription)}</div>\n" +
                "                    <div class=\"module-meta\">\n" +
                "                        <span class=\"module-tag category\">${escapeHtml(localizedCategory)}</span>\n" +
                "                        <span class=\"module-tag keybind\">${escapeHtml(module.keybind)}</span>\n" +
                "                    </div>\n" +
                "                `;\n" +
                "                grid.appendChild(card);\n" +
                "            });\n" +
                "        }\n" +
                "        \n" +
                "        function toggleModule(moduleName) {\n" +
                "            fetch('/api/modules/' + encodeURIComponent(moduleName), {\n" +
                "                method: 'POST',\n" +
                "                headers: { 'Content-Type': 'application/json' },\n" +
                "                body: JSON.stringify({ action: 'toggle' })\n" +
                "            })\n" +
                "            .then(response => response.json())\n" +
                "            .then(data => {\n" +
                "                loadModules();\n" +
                "            })\n" +
                "            .catch(error => {\n" +
                "                console.error('切换模块错误:', error);\n" +
                "                alert('切换模块失败: ' + error.message);\n" +
                "            });\n" +
                "        }\n" +
                "        \n" +
                "        function escapeHtml(text) {\n" +
                "            const div = document.createElement('div');\n" +
                "            div.textContent = text;\n" +
                "            return div.innerHTML;\n" +
                "        }\n" +
                "        \n" +
                "        // 语言翻译\n" +
                "        const translations = {\n" +
                "            zh: {\n" +
                "                pageTitle: '控制面板',\n" +
                "                autoRefreshStatus: '自动刷新中',\n" +
                "                themeText: { light: '浅色', dark: '深色', auto: '跟随系统' },\n" +
                "                screenshotText: '截图',\n" +
                "                takeScreenshotText: '拍摄截图',\n" +
                "                playerInfoTitle: '玩家信息',\n" +
                "                gameInfoTitle: '游戏信息',\n" +
                "                serverInfoTitle: '服务器信息',\n" +
                "                systemInfoTitle: '系统信息',\n" +
                "                modulesTitle: '模块控制',\n" +
                "                editConfigLink: '[编辑配置]',\n" +
                "                autowalkTitle: '自动行走',\n" +
                "                setTargetBtn: '设置目标',\n" +
                "                clearTargetBtn: '清除',\n" +
                "                screenshotTitle: '截图',\n" +
                "                scoreboardTitle: '计分板',\n" +
                "                loadingModules: '加载模块中...',\n" +
                "                loadingScoreboard: '加载计分板中...',\n" +
                "                footerText: 'Lucid Dreaming Mod v1.0.0 | 每秒自动刷新',\n" +
                "                labels: {\n" +
                "                    name: '名称',\n" +
                "                    health: '生命值',\n" +
                "                    hunger: '饥饿度',\n" +
                "                    position: '位置',\n" +
                "                    dimension: '维度',\n" +
                "                    experience: '经验等级',\n" +
                "                    mcVersion: 'Minecraft 版本',\n" +
                "                    forgeVersion: 'Forge 版本',\n" +
                "                    fps: '帧率',\n" +
                "                    uptime: '运行时间',\n" +
                "                    currentTime: '当前时间',\n" +
                "                    type: '类型',\n" +
                "                    address: '地址',\n" +
                "                    serverName: '名称',\n" +
                "                    os: '操作系统',\n" +
                "                    javaVersion: 'Java 版本'\n" +
                "                },\n" +
                "                dimensionNames: {\n" +
                "                    'Overworld': '主世界',\n" +
                "                    'Nether': '下界',\n" +
                "                    'End': '末地'\n" +
                "                },\n" +
                "                autowalkStatus: {\n" +
                "                    notActive: '未激活',\n" +
                "                    calculating: '计算路径中...',\n" +
                "                    moving: '移动中',\n" +
                "                    target: '目标',\n" +
                "                    pathFound: '路径已找到',\n" +
                "                    pathNotFound: '路径未找到',\n" +
                "                    nodes: '节点'\n" +
                "                }\n" +
                "            },\n" +
                "            en: {\n" +
                "                pageTitle: 'Control Panel',\n" +
                "                autoRefreshStatus: 'Auto-refreshing',\n" +
                "                themeText: { light: 'Light', dark: 'Dark', auto: 'Auto' },\n" +
                "                screenshotText: 'Screenshot',\n" +
                "                takeScreenshotText: 'Take Screenshot',\n" +
                "                playerInfoTitle: 'Player Information',\n" +
                "                gameInfoTitle: 'Game Information',\n" +
                "                serverInfoTitle: 'Server Information',\n" +
                "                systemInfoTitle: 'System Information',\n" +
                "                modulesTitle: 'Module Control',\n" +
                "                editConfigLink: '[Edit Config]',\n" +
                "                autowalkTitle: 'Auto Walk',\n" +
                "                setTargetBtn: 'Set Target',\n" +
                "                clearTargetBtn: 'Clear',\n" +
                "                screenshotTitle: 'Screenshot',\n" +
                "                scoreboardTitle: 'Scoreboard',\n" +
                "                loadingModules: 'Loading modules...',\n" +
                "                loadingScoreboard: 'Loading scoreboard...',\n" +
                "                footerText: 'Lucid Dreaming Mod v1.0.0 | Auto-refreshing every 1 second',\n" +
                "                labels: {\n" +
                "                    name: 'Name',\n" +
                "                    health: 'Health',\n" +
                "                    hunger: 'Hunger',\n" +
                "                    position: 'Position',\n" +
                "                    dimension: 'Dimension',\n" +
                "                    experience: 'Experience',\n" +
                "                    mcVersion: 'Minecraft Version',\n" +
                "                    forgeVersion: 'Forge Version',\n" +
                "                    fps: 'FPS',\n" +
                "                    uptime: 'Uptime',\n" +
                "                    currentTime: 'Current Time',\n" +
                "                    type: 'Type',\n" +
                "                    address: 'Address',\n" +
                "                    serverName: 'Name',\n" +
                "                    os: 'OS',\n" +
                "                    javaVersion: 'Java Version'\n" +
                "                },\n" +
                "                dimensionNames: {\n" +
                "                    'Overworld': 'Overworld',\n" +
                "                    'Nether': 'Nether',\n" +
                "                    'End': 'The End'\n" +
                "                },\n" +
                "                autowalkStatus: {\n" +
                "                    notActive: 'Not active',\n" +
                "                    calculating: 'Calculating path...',\n" +
                "                    moving: 'Moving',\n" +
                "                    target: 'Target',\n" +
                "                    pathFound: 'Path found',\n" +
                "                    pathNotFound: 'Path not found',\n" +
                "                    nodes: 'nodes'\n" +
                "                }\n" +
                "            }\n" +
                "        };\n" +
                "        \n" +
                "        let currentLang = localStorage.getItem('lang') || 'zh';\n" +
                "        \n" +
                "        function setLanguage(lang) {\n" +
                "            currentLang = lang;\n" +
                "            localStorage.setItem('lang', lang);\n" +
                "            \n" +
                "            // 更新语言按钮状态\n" +
                "            const langZhBtn = document.getElementById('langZhBtn');\n" +
                "            const langEnBtn = document.getElementById('langEnBtn');\n" +
                "            \n" +
                "            if (langZhBtn) langZhBtn.classList.remove('active');\n" +
                "            if (langEnBtn) langEnBtn.classList.remove('active');\n" +
                "            \n" +
                "            const activeBtn = document.getElementById('lang' + lang.charAt(0).toUpperCase() + lang.slice(1) + 'Btn');\n" +
                "            if (activeBtn) activeBtn.classList.add('active');\n" +
                "            \n" +
                "            updateTranslations();\n" +
                "            updateInfo();\n" +
                "        }\n" +
                "        \n" +
                "        function updateTranslations() {\n" +
                "            const t = translations[currentLang];\n" +
                "            if (!t) return;\n" +
                "            \n" +
                "            const elements = {\n" +
                "                pageTitle: 'pageTitle',\n" +
                "                autoRefreshStatus: 'autoRefreshStatus',\n" +
                "                screenshotText: 'screenshotText',\n" +
                "                takeScreenshotText: 'takeScreenshotText',\n" +
                "                playerInfoTitle: 'playerInfoTitle',\n" +
                "                gameInfoTitle: 'gameInfoTitle',\n" +
                "                serverInfoTitle: 'serverInfoTitle',\n" +
                "                systemInfoTitle: 'systemInfoTitle',\n" +
                "                modulesTitle: 'modulesTitle',\n" +
                "                editConfigLink: 'editConfigLink',\n" +
                "                autowalkTitle: 'autowalkTitle',\n" +
                "                setTargetBtn: 'setTargetBtn',\n" +
                "                clearTargetBtn: 'clearTargetBtn',\n" +
                "                screenshotTitle: 'screenshotTitle',\n" +
                "                scoreboardTitle: 'scoreboardTitle',\n" +
                "                loadingModules: 'loadingModules',\n" +
                "                loadingScoreboard: 'loadingScoreboard',\n" +
                "                footerText: 'footerText'\n" +
                "            };\n" +
                "            \n" +
                "            for (const [key, elementId] of Object.entries(elements)) {\n" +
                "                const element = document.getElementById(elementId);\n" +
                "                if (element && t[key]) {\n" +
                "                    element.textContent = t[key];\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            // 更新标签\n" +
                "            if (t.labels) {\n" +
                "                for (const [key, value] of Object.entries(t.labels)) {\n" +
                "                    const elementId = 'label' + key.charAt(0).toUpperCase() + key.slice(1);\n" +
                "                    const element = document.getElementById(elementId);\n" +
                "                    if (element) {\n" +
                "                        element.textContent = value;\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            // 更新主题文本\n" +
                "            applyTheme();\n" +
                "        }\n" +
                "        \n" +
                "        // 解析 Minecraft 颜色代码\n" +
                "        function parseColorCodes(text) {\n" +
                "            if (!text) return '';\n" +
                "            let result = text;\n" +
                "            const colorMap = {\n" +
                "                '0': 'mc-0', '1': 'mc-1', '2': 'mc-2', '3': 'mc-3',\n" +
                "                '4': 'mc-4', '5': 'mc-5', '6': 'mc-6', '7': 'mc-7',\n" +
                "                '8': 'mc-8', '9': 'mc-9', 'a': 'mc-a', 'b': 'mc-b',\n" +
                "                'c': 'mc-c', 'd': 'mc-d', 'e': 'mc-e', 'f': 'mc-f',\n" +
                "                'l': 'mc-l', 'm': 'mc-m', 'n': 'mc-n', 'o': 'mc-o',\n" +
                "                'r': 'mc-r'\n" +
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
                "                            currentClasses = currentClasses.filter(c => !c.startsWith('mc-'));\n" +
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
                "                    if (!t) return;\n" +
                "                    \n" +
                "                    // 玩家信息\n" +
                "                    const playerName = document.getElementById('playerName');\n" +
                "                    if (playerName && data.player) playerName.textContent = data.player.name;\n" +
                "                    \n" +
                "                    const playerHealth = document.getElementById('playerHealth');\n" +
                "                    if (playerHealth && data.player) playerHealth.textContent = data.player.health + '/' + data.player.maxHealth;\n" +
                "                    \n" +
                "                    const playerHunger = document.getElementById('playerHunger');\n" +
                "                    if (playerHunger && data.player) playerHunger.textContent = data.player.hunger + '/20';\n" +
                "                    \n" +
                "                    const playerPosition = document.getElementById('playerPosition');\n" +
                "                    if (playerPosition && data.player) playerPosition.textContent = data.player.position;\n" +
                "                    \n" +
                "                    const playerDimension = document.getElementById('playerDimension');\n" +
                "                    if (playerDimension && data.player && t.dimensionNames) {\n" +
                "                        const dimensionName = t.dimensionNames[data.player.dimension] || data.player.dimension;\n" +
                "                        playerDimension.textContent = dimensionName;\n" +
                "                    }\n" +
                "                    \n" +
                "                    const playerExperience = document.getElementById('playerExperience');\n" +
                "                    if (playerExperience && data.player) {\n" +
                "                        const expText = currentLang === 'zh' ? '等级 ' : 'Level ';\n" +
                "                        playerExperience.textContent = expText + data.player.experienceLevel;\n" +
                "                    }\n" +
                "                    \n" +
                "                    // 游戏信息\n" +
                "                    const mcVersion = document.getElementById('mcVersion');\n" +
                "                    if (mcVersion && data.game) mcVersion.textContent = data.game.minecraftVersion;\n" +
                "                    \n" +
                "                    const forgeVersion = document.getElementById('forgeVersion');\n" +
                "                    if (forgeVersion && data.game) forgeVersion.textContent = data.game.forgeVersion;\n" +
                "                    \n" +
                "                    const fps = document.getElementById('fps');\n" +
                "                    if (fps && data.game) fps.textContent = data.game.fps;\n" +
                "                    \n" +
                "                    const uptime = document.getElementById('uptime');\n" +
                "                    if (uptime && data.game) uptime.textContent = data.game.uptime;\n" +
                "                    \n" +
                "                    const currentTime = document.getElementById('currentTime');\n" +
                "                    if (currentTime && data.game) currentTime.textContent = data.game.currentTime;\n" +
                "                    \n" +
                "                    // 服务器信息\n" +
                "                    const serverType = document.getElementById('serverType');\n" +
                "                    if (serverType && data.server) serverType.textContent = data.server.type;\n" +
                "                    \n" +
                "                    const serverAddress = document.getElementById('serverAddress');\n" +
                "                    if (serverAddress && data.server) serverAddress.textContent = data.server.address;\n" +
                "                    \n" +
                "                    const serverName = document.getElementById('serverName');\n" +
                "                    if (serverName && data.server) serverName.textContent = data.server.name;\n" +
                "                    \n" +
                "                    // 系统信息\n" +
                "                    const osName = document.getElementById('osName');\n" +
                "                    if (osName && data.system) osName.textContent = data.system.osName + ' ' + data.system.osVersion;\n" +
                "                    \n" +
                "                    const javaVersion = document.getElementById('javaVersion');\n" +
                "                    if (javaVersion && data.system) javaVersion.textContent = data.system.javaVersion;\n" +
                "                    \n" +
                "                    // 计分板\n" +
                "                    const scoreboardList = document.getElementById('scoreboardList');\n" +
                "                    if (scoreboardList && data.scoreboard) {\n" +
                "                        scoreboardList.innerHTML = '';\n" +
                "                        data.scoreboard.forEach(line => {\n" +
                "                            const li = document.createElement('li');\n" +
                "                            li.innerHTML = parseColorCodes(line);\n" +
                "                            scoreboardList.appendChild(li);\n" +
                "                        });\n" +
                "                    }\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('获取数据错误:', error);\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        // 自动行走功能\n" +
                "        function setAutoWalkTarget() {\n" +
                "            const x = document.getElementById('targetX').value;\n" +
                "            const y = document.getElementById('targetY').value;\n" +
                "            const z = document.getElementById('targetZ').value;\n" +
                "            \n" +
                "            if (!x || !y || !z) {\n" +
                "                alert(currentLang === 'zh' ? '请输入 X、Y、Z 坐标' : 'Please enter X, Y, and Z coordinates');\n" +
                "                return;\n" +
                "            }\n" +
                "            \n" +
                "            fetch('/api/autowalk?x=' + x + '&y=' + y + '&z=' + z)\n" +
                "                .then(response => response.json())\n" +
                "                .then(data => {\n" +
                "                    if (data.success) {\n" +
                "                        alert(data.message);\n" +
                "                        updateAutoWalkStatus();\n" +
                "                    } else {\n" +
                "                        alert('Error: ' + data.error);\n" +
                "                    }\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    alert('Error: ' + error);\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        function clearAutoWalkTarget() {\n" +
                "            fetch('/api/autowalk?action=clear')\n" +
                "                .then(response => response.json())\n" +
                "                .then(data => {\n" +
                "                    if (data.success) {\n" +
                "                        alert(data.message);\n" +
                "                        updateAutoWalkStatus();\n" +
                "                    } else {\n" +
                "                        alert('Error: ' + data.error);\n" +
                "                    }\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    alert('Error: ' + error);\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        function updateAutoWalkStatus() {\n" +
                "            fetch('/api/autowalk')\n" +
                "                .then(response => response.json())\n" +
                "                .then(data => {\n" +
                "                    const statusText = document.getElementById('autowalkStatusText');\n" +
                "                    const targetInfo = document.getElementById('autowalkTargetInfo');\n" +
                "                    const pathfindingStatus = document.getElementById('pathfindingStatus');\n" +
                "                    const statusBox = document.getElementById('autowalkStatus');\n" +
                "                    const t = translations[currentLang];\n" +
                "                    \n" +
                "                    if (data.enabled && data.hasTarget) {\n" +
                "                        if (statusText && t.autowalkStatus) {\n" +
                "                            statusText.textContent = data.pathfinding ? t.autowalkStatus.calculating : t.autowalkStatus.moving;\n" +
                "                        }\n" +
                "                        \n" +
                "                        if (data.target && targetInfo && t.autowalkStatus) {\n" +
                "                            targetInfo.textContent = t.autowalkStatus.target + ': X=' + data.target.x + ', Y=' + data.target.y + ', Z=' + data.target.z;\n" +
                "                        }\n" +
                "                        \n" +
                "                        // 更新路径查找状态\n" +
                "                        if (pathfindingStatus && t.autowalkStatus) {\n" +
                "                            if (data.pathfinding) {\n" +
                "                                if (statusBox) {\n" +
                "                                    statusBox.className = 'status-box calculating';\n" +
                "                                }\n" +
                "                                pathfindingStatus.textContent = t.autowalkStatus.calculating;\n" +
                "                            } else if (data.hasPath) {\n" +
                "                                if (statusBox) {\n" +
                "                                    statusBox.className = 'status-box active';\n" +
                "                                }\n" +
                "                                pathfindingStatus.textContent = t.autowalkStatus.pathFound + ' (' + data.pathLength + ' ' + t.autowalkStatus.nodes + ')';\n" +
                "                            } else {\n" +
                "                                if (statusBox) {\n" +
                "                                    statusBox.className = 'status-box failed';\n" +
                "                                }\n" +
                "                                pathfindingStatus.textContent = t.autowalkStatus.pathNotFound;\n" +
                "                            }\n" +
                "                        }\n" +
                "                    } else {\n" +
                "                        if (statusText && t.autowalkStatus) {\n" +
                "                            statusText.textContent = t.autowalkStatus.notActive;\n" +
                "                        }\n" +
                "                        if (targetInfo) {\n" +
                "                            targetInfo.textContent = '';\n" +
                "                        }\n" +
                "                        if (pathfindingStatus) {\n" +
                "                            pathfindingStatus.textContent = '';\n" +
                "                        }\n" +
                "                        if (statusBox) {\n" +
                "                            statusBox.className = 'status-box';\n" +
                "                        }\n" +
                "                    }\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('获取自动行走状态错误:', error);\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        // 初始化\n" +
                "        document.addEventListener('DOMContentLoaded', function() {\n" +
                "            initTheme();\n" +
                "            \n" +
                "            // 初始化语言按钮\n" +
                "            const langBtn = document.getElementById('lang' + currentLang.charAt(0).toUpperCase() + currentLang.slice(1) + 'Btn');\n" +
                "            if (langBtn) {\n" +
                "                langBtn.classList.add('active');\n" +
                "            }\n" +
                "            \n" +
                "            updateTranslations();\n" +
                "            updateInfo();\n" +
                "            loadModules();\n" +
                "            updateAutoWalkStatus();\n" +
                "            \n" +
                "            // 自动刷新\n" +
                "            setInterval(updateInfo, 1000);\n" +
                "            setInterval(loadModules, 2000);\n" +
                "            setInterval(updateAutoWalkStatus, 1000);\n" +
                "        });\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }
}
