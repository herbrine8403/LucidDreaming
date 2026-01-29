package com.luciddreaming.http;

public class ConfigTemplate {
    public static String generateHTML() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"zh-CN\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>模块配置 - Lucid Dreaming</title>\n" +
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
                "            max-width: 1200px;\n" +
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
                "            text-decoration: none;\n" +
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
                "        /* 配置容器 */\n" +
                "        .config-container {\n" +
                "            background-color: var(--bg-card);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-lg);\n" +
                "            padding: var(--spacing-lg);\n" +
                "            box-shadow: var(--shadow-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .config-selector {\n" +
                "            margin-bottom: var(--spacing-lg);\n" +
                "        }\n" +
                "        \n" +
                "        .config-selector label {\n" +
                "            display: block;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "            margin-bottom: var(--spacing-sm);\n" +
                "            font-size: 0.9375rem;\n" +
                "        }\n" +
                "        \n" +
                "        .config-select {\n" +
                "            width: 100%;\n" +
                "            max-width: 300px;\n" +
                "            padding: var(--spacing-sm) var(--spacing-md);\n" +
                "            font-size: 0.9375rem;\n" +
                "            color: var(--text-primary);\n" +
                "            background-color: var(--bg-secondary);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            transition: all var(--transition-fast);\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "        \n" +
                "        .config-select:focus {\n" +
                "            outline: none;\n" +
                "            border-color: var(--accent-primary);\n" +
                "            box-shadow: 0 0 0 3px rgba(14, 165, 233, 0.1);\n" +
                "        }\n" +
                "        \n" +
                "        .config-form {\n" +
                "            display: grid;\n" +
                "            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));\n" +
                "            gap: var(--spacing-lg);\n" +
                "        }\n" +
                "        \n" +
                "        .config-item {\n" +
                "            background-color: var(--bg-secondary);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            padding: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        .config-item label {\n" +
                "            display: block;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "            margin-bottom: var(--spacing-sm);\n" +
                "            font-size: 0.875rem;\n" +
                "        }\n" +
                "        \n" +
                "        .config-input {\n" +
                "            width: 100%;\n" +
                "            padding: var(--spacing-sm) var(--spacing-sm);\n" +
                "            font-size: 0.875rem;\n" +
                "            color: var(--text-primary);\n" +
                "            background-color: var(--bg-card);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            transition: all var(--transition-fast);\n" +
                "        }\n" +
                "        \n" +
                "        .config-input:focus {\n" +
                "            outline: none;\n" +
                "            border-color: var(--accent-primary);\n" +
                "            box-shadow: 0 0 0 3px rgba(14, 165, 233, 0.1);\n" +
                "        }\n" +
                "        \n" +
                "        .checkbox-wrapper {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .checkbox-input {\n" +
                "            width: 20px;\n" +
                "            height: 20px;\n" +
                "            cursor: pointer;\n" +
                "            accent-color: var(--accent-primary);\n" +
                "        }\n" +
                "        \n" +
                "        .checkbox-text {\n" +
                "            font-size: 0.875rem;\n" +
                "            color: var(--text-secondary);\n" +
                "        }\n" +
                "        \n" +
                "        .range-wrapper {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .range-input {\n" +
                "            flex: 1;\n" +
                "            cursor: pointer;\n" +
                "            accent-color: var(--accent-primary);\n" +
                "        }\n" +
                "        \n" +
                "        .range-value {\n" +
                "            min-width: 60px;\n" +
                "            text-align: right;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--accent-primary);\n" +
                "            font-size: 0.875rem;\n" +
                "        }\n" +
                "        \n" +
                "        .config-actions {\n" +
                "            margin-top: var(--spacing-lg);\n" +
                "            display: flex;\n" +
                "            gap: var(--spacing-sm);\n" +
                "            justify-content: flex-end;\n" +
                "            flex-wrap: wrap;\n" +
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
                "        .btn-secondary {\n" +
                "            background-color: var(--bg-secondary);\n" +
                "            border-color: var(--border-color);\n" +
                "            color: var(--text-primary);\n" +
                "        }\n" +
                "        \n" +
                "        .btn-secondary:hover {\n" +
                "            background-color: var(--bg-hover);\n" +
                "            border-color: var(--border-hover);\n" +
                "        }\n" +
                "        \n" +
                "        .empty-state {\n" +
                "            text-align: center;\n" +
                "            padding: var(--spacing-xl);\n" +
                "            color: var(--text-secondary);\n" +
                "        }\n" +
                "        \n" +
                "        .empty-state svg {\n" +
                "            width: 48px;\n" +
                "            height: 48px;\n" +
                "            color: var(--text-muted);\n" +
                "            margin-bottom: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        /* 响应式设计 */\n" +
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
                "            .config-form {\n" +
                "                grid-template-columns: 1fr;\n" +
                "            }\n" +
                "            \n" +
                "            .config-select {\n" +
                "                max-width: 100%;\n" +
                "            }\n" +
                "            \n" +
                "            .header-title {\n" +
                "                font-size: 1.5rem;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <!-- 头部 -->\n" +
                "        <header class=\"header\">\n" +
                "            <div class=\"header-left\">\n" +
                "                <a href=\"/\" class=\"btn\">\n" +
                "                    <span class=\"btn-icon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <path d=\"M19 12H5M12 19l-7-7 7-7\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    <span id=\"backLink\">返回控制面板</span>\n" +
                "                </a>\n" +
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
                "                <a href=\"/automation\" class=\"btn\">\n" +
                "                    <span class=\"btn-icon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <path d=\"M12 6V4M12 20V18M6 12H4M20 12H18M7.05 7.05L5.64 5.64M18.36 18.36L16.95 16.95M7.05 16.95L5.64 18.36M18.36 5.64L16.95 7.05\"/>\n" +
                "                            <circle cx=\"12\" cy=\"12\" r=\"3\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    <span>自动化</span>\n" +
                "                </a>\n" +
                "            </div>\n" +
                "        </header>\n" +
                "        \n" +
                "        <h1 class=\"header-title\" style=\"margin-bottom: var(--spacing-lg);\" id=\"pageTitle\">模块配置编辑</h1>\n" +
                "        \n" +
                "        <!-- 配置容器 -->\n" +
                "        <div class=\"config-container\">\n" +
                "            <div class=\"config-selector\">\n" +
                "                <label for=\"moduleSelect\" id=\"moduleSelectLabel\">选择模块</label>\n" +
                "                <select id=\"moduleSelect\" class=\"config-select\" onchange=\"loadModuleConfig()\">\n" +
                "                    <option value=\"\">-- 请选择模块 --</option>\n" +
                "                    <option value=\"AutoFish\">AutoFish</option>\n" +
                "                    <option value=\"AutoClicker\">AutoClicker</option>\n" +
                "                    <option value=\"AutoKill\">AutoKill</option>\n" +
                "                    <option value=\"AntiKick\">AntiKick</option>\n" +
                "                    <option value=\"AutoWalk\">AutoWalk</option>\n" +
                "                    <option value=\"NoRender\">NoRender</option>\n" +
                "                    <option value=\"FakeBlackScreen\">FakeBlackScreen</option>\n" +
                "                </select>\n" +
                "            </div>\n" +
                "            \n" +
                "            <div class=\"config-form\" id=\"configForm\">\n" +
                "                <div class=\"empty-state\">\n" +
                "                    <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                        <circle cx=\"12\" cy=\"12\" r=\"3\"/>\n" +
                "                        <path d=\"M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z\"/>\n" +
                "                    </svg>\n" +
                "                    <p id=\"noModuleSelected\">请选择一个模块来查看和编辑其配置</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
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
                "        // 语言翻译\n" +
                "        const translations = {\n" +
                "            zh: {\n" +
                "                backLink: '返回控制面板',\n" +
                "                pageTitle: '模块配置编辑',\n" +
                "                themeText: { light: '浅色', dark: '深色', auto: '跟随系统' },\n" +
                "                moduleSelectLabel: '选择模块',\n" +
                "                noModuleSelected: '请选择一个模块来查看和编辑其配置',\n" +
                "                placeholderSelect: '-- 请选择模块 --',\n" +
                "                enabled: '已启用',\n" +
                "                disabled: '已禁用',\n" +
                "                saveConfig: '保存配置',\n" +
                "                resetConfig: '重置',\n" +
                "                configSaved: '配置保存成功！',\n" +
                "                configError: '保存配置失败：',\n" +
                "                modules: {\n" +
                "                    AutoFish: '自动钓鱼',\n" +
                "                    AutoClicker: '自动点击',\n" +
                "                    AutoKill: '自动击杀',\n" +
                "                    AntiKick: '防踢',\n" +
                "                    AutoWalk: '自动行走',\n" +
                "                    NoRender: '不渲染',\n" +
                "                    FakeBlackScreen: '假黑屏'\n" +
                "                },\n" +
                "                configKeys: {\n" +
                "                    enabled: '已启用',\n" +
                "                    keybind: '按键绑定',\n" +
                "                    delay: '延迟',\n" +
                "                    chance: '概率',\n" +
                "                    range: '范围',\n" +
                "                    speed: '速度',\n" +
                "                    cooldown: '冷却',\n" +
                "                    opacity: '透明度',\n" +
                "                    distance: '距离',\n" +
                "                    interval: '间隔',\n" +
                "                    time: '时间',\n" +
                "                    amount: '数量',\n" +
                "                    cost: '消耗',\n" +
                "                    mode: '模式',\n" +
                "                    message: '消息',\n" +
                "                    radius: '半径',\n" +
                "                    strength: '强度',\n" +
                "                    duration: '持续时间',\n" +
                "                    min: '最小值',\n" +
                "                    max: '最大值',\n" +
                "                    threshold: '阈值',\n" +
                "                    multiplier: '倍数',\n" +
                "                    factor: '因子',\n" +
                "                    percentage: '百分比',\n" +
                "                    level: '等级',\n" +
                "                    count: '计数',\n" +
                "                    limit: '限制',\n" +
                "                    period: '周期',\n" +
                "                    rate: '速率',\n" +
                "                    frequency: '频率'\n" +
                "                }\n" +
                "            },\n" +
                "            en: {\n" +
                "                backLink: 'Back to Control Panel',\n" +
                "                pageTitle: 'Module Configuration',\n" +
                "                themeText: { light: 'Light', dark: 'Dark', auto: 'Auto' },\n" +
                "                moduleSelectLabel: 'Select Module',\n" +
                "                noModuleSelected: 'Select a module to view and edit its configuration',\n" +
                "                placeholderSelect: '-- Select a module --',\n" +
                "                enabled: 'Enabled',\n" +
                "                disabled: 'Disabled',\n" +
                "                saveConfig: 'Save Configuration',\n" +
                "                resetConfig: 'Reset',\n" +
                "                configSaved: 'Configuration saved successfully!',\n" +
                "                configError: 'Error saving configuration: ',\n" +
                "                modules: {\n" +
                "                    AutoFish: 'AutoFish',\n" +
                "                    AutoClicker: 'AutoClicker',\n" +
                "                    AutoKill: 'AutoKill',\n" +
                "                    AntiKick: 'AntiKick',\n" +
                "                    AutoWalk: 'AutoWalk',\n" +
                "                    NoRender: 'NoRender',\n" +
                "                    FakeBlackScreen: 'Fake Black Screen'\n" +
                "                },\n" +
                "                configKeys: {\n" +
                "                    enabled: 'Enabled',\n" +
                "                    keybind: 'Keybind',\n" +
                "                    delay: 'Delay',\n" +
                "                    chance: 'Chance',\n" +
                "                    range: 'Range',\n" +
                "                    speed: 'Speed',\n" +
                "                    cooldown: 'Cooldown',\n" +
                "                    opacity: 'Opacity',\n" +
                "                    distance: 'Distance',\n" +
                "                    interval: 'Interval',\n" +
                "                    time: 'Time',\n" +
                "                    amount: 'Amount',\n" +
                "                    cost: 'Cost',\n" +
                "                    mode: 'Mode',\n" +
                "                    message: 'Message',\n" +
                "                    radius: 'Radius',\n" +
                "                    strength: 'Strength',\n" +
                "                    duration: 'Duration',\n" +
                "                    min: 'Min',\n" +
                "                    max: 'Max',\n" +
                "                    threshold: 'Threshold',\n" +
                "                    multiplier: 'Multiplier',\n" +
                "                    factor: 'Factor',\n" +
                "                    percentage: 'Percentage',\n" +
                "                    level: 'Level',\n" +
                "                    count: 'Count',\n" +
                "                    limit: 'Limit',\n" +
                "                    period: 'Period',\n" +
                "                    rate: 'Rate',\n" +
                "                    frequency: 'Frequency'\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
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
                "        }\n" +
                "        \n" +
                "        function updateTranslations() {\n" +
                "            const t = translations[currentLang];\n" +
                "            if (!t) return;\n" +
                "            \n" +
                "            const elements = {\n" +
                "                backLink: 'backLink',\n" +
                "                pageTitle: 'pageTitle',\n" +
                "                moduleSelectLabel: 'moduleSelectLabel',\n" +
                "                noModuleSelected: 'noModuleSelected'\n" +
                "            };\n" +
                "            \n" +
                "            for (const [key, elementId] of Object.entries(elements)) {\n" +
                "                const element = document.getElementById(elementId);\n" +
                "                if (element && t[key]) {\n" +
                "                    element.textContent = t[key];\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            // 更新模块选择器\n" +
                "            const moduleSelect = document.getElementById('moduleSelect');\n" +
                "            if (moduleSelect && t.modules) {\n" +
                "                // 更新占位符\n" +
                "                moduleSelect.options[0].textContent = t.placeholderSelect;\n" +
                "                \n" +
                "                // 更新模块名称\n" +
                "                for (let i = 1; i < moduleSelect.options.length; i++) {\n" +
                "                    const option = moduleSelect.options[i];\n" +
                "                    const moduleKey = option.value;\n" +
                "                    option.textContent = t.modules[moduleKey] || moduleKey;\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            // 如果有选中的模块，重新加载配置表单\n" +
                "            if (moduleSelect && moduleSelect.value) {\n" +
                "                loadModuleConfig();\n" +
                "            }\n" +
                "            \n" +
                "            // 更新主题文本\n" +
                "            applyTheme();\n" +
                "        }\n" +
                "        \n" +
                "        function loadModuleConfig() {\n" +
                "            const moduleSelect = document.getElementById('moduleSelect');\n" +
                "            const moduleName = moduleSelect.value;\n" +
                "            const configForm = document.getElementById('configForm');\n" +
                "            const t = translations[currentLang];\n" +
                "            \n" +
                "            if (!moduleName) {\n" +
                "                configForm.innerHTML = '<div class=\"empty-state\"><svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\"><circle cx=\"12\" cy=\"12\" r=\"3\"/><path d=\"M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z\"/></svg><p>' + t.noModuleSelected + '</p></div>';\n" +
                "                return;\n" +
                "            }\n" +
                "            \n" +
                "            fetch('/api/config/' + moduleName)\n" +
                "                .then(response => response.json())\n" +
                "                .then(data => {\n" +
                "                    renderConfigForm(moduleName, data);\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('Error loading module config:', error);\n" +
                "                    configForm.innerHTML = '<div class=\"empty-state\"><p style=\"color: var(--error);\">' + t.configError + error.message + '</p></div>';\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        function renderConfigForm(moduleName, config) {\n" +
                "            const configForm = document.getElementById('configForm');\n" +
                "            const t = translations[currentLang];\n" +
                "            let html = '';\n" +
                "            \n" +
                "            for (const key in config) {\n" +
                "                const value = config[key];\n" +
                "                const label = formatConfigKey(key);\n" +
                "                \n" +
                "                html += '<div class=\"config-item\">';\n" +
                "                html += '<label for=\"config_' + key + '\">' + label + '</label>';\n" +
                "                \n" +
                "                if (typeof value === 'boolean') {\n" +
                "                    html += '<div class=\"checkbox-wrapper\">';\n" +
                "                    html += '<input type=\"checkbox\" id=\"config_' + key + '\" class=\"checkbox-input\" ' + (value ? 'checked' : '') + ' onchange=\"updateCheckboxText(this)\">';\n" +
                "                    html += '<span class=\"checkbox-text\" id=\"checkbox_text_' + key + '\">' + (value ? t.enabled : t.disabled) + '</span>';\n" +
                "                    html += '</div>';\n" +
                "                } else if (typeof value === 'number') {\n" +
                "                    if (key.includes('Range') || key.includes('Speed') || key.includes('Amount') || key.includes('Chance') || key.includes('Opacity') || key.includes('Cost') || key.includes('Distance') || key.includes('Time') || key.includes('Interval')) {\n" +
                "                        html += '<div class=\"range-wrapper\">';\n" +
                "                        html += '<input type=\"range\" id=\"config_' + key + '\" class=\"range-input\" value=\"' + value + '\" min=\"0\" max=\"100\" step=\"0.1\" oninput=\"updateRangeValue(this)\">';\n" +
                "                        html += '<span class=\"range-value\" id=\"range_' + key + '\">' + value + '</span>';\n" +
                "                        html += '</div>';\n" +
                "                    } else {\n" +
                "                        html += '<input type=\"number\" id=\"config_' + key + '\" class=\"config-input\" value=\"' + value + '\" step=\"0.1\">';\n" +
                "                    }\n" +
                "                } else if (typeof value === 'string') {\n" +
                "                    html += '<input type=\"text\" id=\"config_' + key + '\" class=\"config-input\" value=\"' + value + '\">';\n" +
                "                }\n" +
                "                \n" +
                "                html += '</div>';\n" +
                "            }\n" +
                "            \n" +
                "            html += '<div class=\"config-actions\">';\n" +
                "            html += '<button class=\"btn btn-primary\" onclick=\"saveModuleConfig(\'' + moduleName + '\')\">' + t.saveConfig + '</button>';\n" +
                "            html += '<button class=\"btn btn-secondary\" onclick=\"loadModuleConfig()\">' + t.resetConfig + '</button>';\n" +
                "            html += '</div>';\n" +
                "            \n" +
                "            configForm.innerHTML = html;\n" +
                "        }\n" +
                "        \n" +
                "        function updateRangeValue(input) {\n" +
                "            const valueSpan = document.getElementById('range_' + input.id.replace('config_', ''));\n" +
                "            if (valueSpan) {\n" +
                "                valueSpan.textContent = input.value;\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        function updateCheckboxText(checkbox) {\n" +
                "            const textSpan = document.getElementById('checkbox_text_' + checkbox.id.replace('config_', ''));\n" +
                "            const t = translations[currentLang];\n" +
                "            if (textSpan) {\n" +
                "                textSpan.textContent = checkbox.checked ? t.enabled : t.disabled;\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        function formatConfigKey(key) {\n" +
                "            const t = translations[currentLang];\n" +
                "            // 检查是否有翻译\n" +
                "            if (t.configKeys && t.configKeys[key]) {\n" +
                "                return t.configKeys[key];\n" +
                "            }\n" +
                "            // 回退到格式化键名\n" +
                "            return key.replace(/([A-Z])/g, ' $1').replace(/^./, str => str.toUpperCase()).trim();\n" +
                "        }\n" +
                "        \n" +
                "        function saveModuleConfig(moduleName) {\n" +
                "            const configForm = document.getElementById('configForm');\n" +
                "            const inputs = configForm.querySelectorAll('input, select');\n" +
                "            const configData = {};\n" +
                "            const t = translations[currentLang];\n" +
                "            \n" +
                "            inputs.forEach(input => {\n" +
                "                const key = input.id.replace('config_', '');\n" +
                "                if (input.type === 'checkbox') {\n" +
                "                    configData[key] = input.checked;\n" +
                "                } else if (input.type === 'number' || input.type === 'range') {\n" +
                "                    configData[key] = parseFloat(input.value);\n" +
                "                } else {\n" +
                "                    configData[key] = input.value;\n" +
                "                }\n" +
                "            });\n" +
                "            \n" +
                "            fetch('/api/config/' + moduleName, {\n" +
                "                method: 'POST',\n" +
                "                headers: {\n" +
                "                    'Content-Type': 'application/json'\n" +
                "                },\n" +
                "                body: JSON.stringify(configData)\n" +
                "            })\n" +
                "            .then(response => response.json())\n" +
                "            .then(data => {\n" +
                "                if (data.success) {\n" +
                "                    alert(t.configSaved);\n" +
                "                } else {\n" +
                "                    alert(t.configError + data.error);\n" +
                "                }\n" +
                "            })\n" +
                "            .catch(error => {\n" +
                "                console.error('Error saving module config:', error);\n" +
                "                alert(t.configError + error.message);\n" +
                "            });\n" +
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
                "        });\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }
}