package com.luciddreaming.http;

public class AutomationTemplate {
    public static String generateHTML() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"zh-CN\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>自动化管理 - Lucid Dreaming</title>\n" +
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
                "        /* 网格布局 */\n" +
                "        .grid {\n" +
                "            display: grid;\n" +
                "            gap: var(--spacing-lg);\n" +
                "        }\n" +
                "        .grid-2 {\n" +
                "            grid-template-columns: repeat(2, 1fr);\n" +
                "        }\n" +
                "        .grid-3 {\n" +
                "            grid-template-columns: repeat(3, 1fr);\n" +
                "        }\n" +
                "        .grid-4 {\n" +
                "            grid-template-columns: repeat(4, 1fr);\n" +
                "        }\n" +
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
                "        /* 自动化任务卡片 */\n" +
                "        .automation-card {\n" +
                "            background-color: var(--bg-card);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            padding: var(--spacing-md);\n" +
                "            transition: all var(--transition-fast);\n" +
                "            position: relative;\n" +
                "        }\n" +
                "        \n" +
                "        .automation-card:hover {\n" +
                "            border-color: var(--border-hover);\n" +
                "            box-shadow: var(--shadow-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .automation-card.enabled {\n" +
                "            border-left: 3px solid var(--success);\n" +
                "        }\n" +
                "        \n" +
                "        .automation-header {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "            align-items: center;\n" +
                "            margin-bottom: var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .automation-name {\n" +
                "            font-size: 0.9375rem;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "        }\n" +
                "        \n" +
                "        .automation-actions {\n" +
                "            display: flex;\n" +
                "            gap: var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .automation-description {\n" +
                "            font-size: 0.8125rem;\n" +
                "            color: var(--text-secondary);\n" +
                "            margin-bottom: var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .automation-meta {\n" +
                "            display: flex;\n" +
                "            gap: var(--spacing-sm);\n" +
                "            flex-wrap: wrap;\n" +
                "        }\n" +
                "        \n" +
                "        .automation-tag {\n" +
                "            display: inline-flex;\n" +
                "            align-items: center;\n" +
                "            font-size: 0.75rem;\n" +
                "            padding: 2px 8px;\n" +
                "            border-radius: var(--radius-sm);\n" +
                "            font-weight: 500;\n" +
                "        }\n" +
                "        \n" +
                "        .automation-tag.status {\n" +
                "            background-color: var(--success-bg);\n" +
                "            color: var(--success);\n" +
                "        }\n" +
                "        \n" +
                "        .automation-tag.type {\n" +
                "            background-color: var(--accent-blue-light);\n" +
                "            color: var(--accent-blue);\n" +
                "        }\n" +
                "        \n" +
                "        [data-theme=\"dark\"] .automation-tag.type {\n" +
                "            background-color: rgba(14, 165, 233, 0.2);\n" +
                "            color: var(--accent-blue);\n" +
                "        }\n" +
                "        \n" +
                "        /* 按钮样式 */\n" +
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
                "        .btn-danger {\n" +
                "            background-color: var(--error);\n" +
                "            border-color: var(--error);\n" +
                "            color: white;\n" +
                "        }\n" +
                "        \n" +
                "        .btn-danger:hover {\n" +
                "            background-color: #dc2626;\n" +
                "            border-color: #dc2626;\n" +
                "        }\n" +
                "        \n" +
                "        /* 搜索框样式 */\n" +
                "        .search-container {\n" +
                "            display: flex;\n" +
                "            gap: var(--spacing-sm);\n" +
                "            margin-bottom: var(--spacing-lg);\n" +
                "        }\n" +
                "        \n" +
                "        .search-input {\n" +
                "            flex: 1;\n" +
                "            padding: var(--spacing-sm) var(--spacing-md);\n" +
                "            font-size: 0.9375rem;\n" +
                "            color: var(--text-primary);\n" +
                "            background-color: var(--bg-secondary);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            transition: all var(--transition-fast);\n" +
                "        }\n" +
                "        \n" +
                "        .search-input:focus {\n" +
                "            outline: none;\n" +
                "            border-color: var(--accent-primary);\n" +
                "            box-shadow: 0 0 0 3px rgba(14, 165, 233, 0.1);\n" +
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
                "                <div>\n" +
                "                    <h1 class=\"header-title\" id=\"pageTitle\">自动化管理</h1>\n" +
                "                    <p class=\"header-subtitle\" id=\"pageSubtitle\">创建和管理您的自动化任务</p>\n" +
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
                "                <button class=\"btn btn-primary\" onclick=\"createNewAutomation()\" id=\"newAutomationBtn\">\n" +
                "                    <span class=\"btn-icon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <circle cx=\"12\" cy=\"12\" r=\"10\"/>\n" +
                "                            <path d=\"M12 8v8M8 12h8\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    <span id=\"newAutomationText\">新建自动化</span>\n" +
                "                </button>\n" +
                "            </div>\n" +
                "        </header>\n" +
                "        \n" +
                "        <!-- 搜索栏 -->\n" +
                "        <div class=\"search-container\">\n" +
                "            <input type=\"text\" class=\"search-input\" id=\"automationSearch\" placeholder=\"搜索自动化任务...\">\n" +
                "            <select class=\"config-select\" id=\"automationTypeFilter\">\n" +
                "                <option value=\"\">所有类型</option>\n" +
                "                <option value=\"movement\">运动</option>\n" +
                "                <option value=\"detection\">侦测</option>\n" +
                "                <option value=\"event\">事件</option>\n" +
                "                <option value=\"control\">控制</option>\n" +
                "                <option value=\"module\">模块</option>\n" +
                "            </select>\n" +
                "            <select class=\"config-select\" id=\"automationStatusFilter\">\n" +
                "                <option value=\"\">所有状态</option>\n" +
                "                <option value=\"enabled\">已启用</option>\n" +
                "                <option value=\"disabled\">已禁用</option>\n" +
                "            </select>\n" +
                "        </div>\n" +
                "        \n" +
                "        <!-- 动作按钮 -->\n" +
                "        <div class=\"header-controls\" style=\"margin-bottom: var(--spacing-lg);\">\n" +
                "            <div style=\"display: flex; align-items: center; gap: var(--spacing-sm); margin-right: var(--spacing-md);\">\n" +
                "                <input type=\"checkbox\" id=\"selectAllCheckbox\" style=\"width: 16px; height: 16px;\" onchange=\"toggleSelectAll()\">\n" +
                "                <label for=\"selectAllCheckbox\" style=\"font-size: 0.875rem; color: var(--text-secondary);\">全选</label>\n" +
                "            </div>\n" +
                "            <button class=\"btn\" onclick=\"batchEnable()\">\n" +
                "                <span class=\"btn-icon\">\n" +
                "                    <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                        <polyline points=\"9,14 4,9 9,4\"/>\n" +
                "                        <path d=\"M20 20v-7a4 4 0 0 0-4-4H4\"/>\n" +
                "                    </svg>\n" +
                "                </span>\n" +
                "                <span>批量启用</span>\n" +
                "            </button>\n" +
                "            <button class=\"btn\" onclick=\"batchDisable()\">\n" +
                "                <span class=\"btn-icon\">\n" +
                "                    <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                        <path d=\"M17 2 2 17\"/>\n" +
                "                        <path d=\"M2 2 l15 15\"/>\n" +
                "                    </svg>\n" +
                "                </span>\n" +
                "                <span>批量禁用</span>\n" +
                "            </button>\n" +
                "            <button class=\"btn\" onclick=\"batchDelete()\">\n" +
                "                <span class=\"btn-icon\">\n" +
                "                    <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                        <path d=\"M3 6h18M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2\"/>\n" +
                "                    </svg>\n" +
                "                </span>\n" +
                "                <span>批量删除</span>\n" +
                "            </button>\n" +
                "        </div>\n" +
                "        \n" +
                "        <!-- 自动化任务列表 -->\n" +
                "        <div class=\"grid grid-auto\" id=\"automationGrid\">\n" +
                "            <div id=\"loadingAutomations\">加载自动化任务中...</div>\n" +
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
                "                pageTitle: '自动化管理',\n" +
                "                pageSubtitle: '创建和管理您的自动化任务',\n" +
                "                themeText: { light: '浅色', dark: '深色', auto: '跟随系统' },\n" +
                "                newAutomationText: '新建自动化',\n" +
                "                newAutomationBtn: '新建自动化',\n" +
                "                searchPlaceholder: '搜索自动化任务...',\n" +
                "                allTypes: '所有类型',\n" +
                "                allStatus: '所有状态',\n" +
                "                enabled: '已启用',\n" +
                "                disabled: '已禁用',\n" +
                "                movement: '运动',\n" +
                "                detection: '侦测',\n" +
                "                event: '事件',\n" +
                "                control: '控制',\n" +
                "                module: '模块',\n" +
                "                edit: '编辑',\n" +
                "                delete: '删除',\n" +
                "                run: '运行',\n" +
                "                stop: '停止',\n" +
                "                loadingAutomations: '加载自动化任务中...',\n" +
                "                batchEnable: '批量启用',\n" +
                "                batchDisable: '批量禁用',\n" +
                "                batchDelete: '批量删除',\n" +
                "                confirmDelete: '确定要删除这个自动化任务吗？',\n" +
                "                automationDeleted: '自动化任务已删除',\n" +
                "                automationEnabled: '自动化任务已启用',\n" +
                "                automationDisabled: '自动化任务已禁用',\n" +
                "                automationCreated: '自动化任务已创建',\n" +
                "                automationUpdated: '自动化任务已更新'\n" +
                "            },\n" +
                "            en: {\n" +
                "                backLink: 'Back to Control Panel',\n" +
                "                pageTitle: 'Automation Management',\n" +
                "                pageSubtitle: 'Create and manage your automation tasks',\n" +
                "                themeText: { light: 'Light', dark: 'Dark', auto: 'Auto' },\n" +
                "                newAutomationText: 'New Automation',\n" +
                "                newAutomationBtn: 'New Automation',\n" +
                "                searchPlaceholder: 'Search automation tasks...',\n" +
                "                allTypes: 'All Types',\n" +
                "                allStatus: 'All Status',\n" +
                "                enabled: 'Enabled',\n" +
                "                disabled: 'Disabled',\n" +
                "                movement: 'Movement',\n" +
                "                detection: 'Detection',\n" +
                "                event: 'Event',\n" +
                "                control: 'Control',\n" +
                "                module: 'Module',\n" +
                "                edit: 'Edit',\n" +
                "                delete: 'Delete',\n" +
                "                run: 'Run',\n" +
                "                stop: 'Stop',\n" +
                "                loadingAutomations: 'Loading automation tasks...',\n" +
                "                batchEnable: 'Batch Enable',\n" +
                "                batchDisable: 'Batch Disable',\n" +
                "                batchDelete: 'Batch Delete',\n" +
                "                confirmDelete: 'Are you sure you want to delete this automation task?',\n" +
                "                automationDeleted: 'Automation task deleted',\n" +
                "                automationEnabled: 'Automation task enabled',\n" +
                "                automationDisabled: 'Automation task disabled',\n" +
                "                automationCreated: 'Automation task created',\n" +
                "                automationUpdated: 'Automation task updated'\n" +
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
                "            loadAutomations();\n" +
                "        }\n" +
                "        \n" +
                "        function updateTranslations() {\n" +
                "            const t = translations[currentLang];\n" +
                "            if (!t) return;\n" +
                "            \n" +
                "            const elements = {\n" +
                "                backLink: 'backLink',\n" +
                "                pageTitle: 'pageTitle',\n" +
                "                pageSubtitle: 'pageSubtitle',\n" +
                "                newAutomationText: 'newAutomationText',\n" +
                "                loadingAutomations: 'loadingAutomations'\n" +
                "            };\n" +
                "            \n" +
                "            for (const [key, elementId] of Object.entries(elements)) {\n" +
                "                const element = document.getElementById(elementId);\n" +
                "                if (element && t[key]) {\n" +
                "                    element.textContent = t[key];\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            // 更新筛选器选项\n" +
                "            const typeFilter = document.getElementById('automationTypeFilter');\n" +
                "            if (typeFilter && t.allTypes) {\n" +
                "                typeFilter.options[0].textContent = t.allTypes;\n" +
                "                if (t.movement) typeFilter.options[1].textContent = t.movement;\n" +
                "                if (t.detection) typeFilter.options[2].textContent = t.detection;\n" +
                "                if (t.event) typeFilter.options[3].textContent = t.event;\n" +
                "                if (t.control) typeFilter.options[4].textContent = t.control;\n" +
                "                if (t.module) typeFilter.options[5].textContent = t.module;\n" +
                "            }\n" +
                "            \n" +
                "            const statusFilter = document.getElementById('automationStatusFilter');\n" +
                "            if (statusFilter && t.allStatus) {\n" +
                "                statusFilter.options[0].textContent = t.allStatus;\n" +
                "                if (t.enabled) statusFilter.options[1].textContent = t.enabled;\n" +
                "                if (t.disabled) statusFilter.options[2].textContent = t.disabled;\n" +
                "            }\n" +
                "            \n" +
                "            // 更新搜索框占位符\n" +
                "            const searchInput = document.getElementById('automationSearch');\n" +
                "            if (searchInput && t.searchPlaceholder) {\n" +
                "                searchInput.placeholder = t.searchPlaceholder;\n" +
                "            }\n" +
                "            \n" +
                "            // 更新动作按钮\n" +
                "            const batchButtons = document.querySelectorAll('.header-controls button');\n" +
                "            if (batchButtons.length >= 3 && t.batchEnable && t.batchDisable && t.batchDelete) {\n" +
                "                batchButtons[0].querySelector('span:last-child').textContent = t.batchEnable;\n" +
                "                batchButtons[1].querySelector('span:last-child').textContent = t.batchDisable;\n" +
                "                batchButtons[2].querySelector('span:last-child').textContent = t.batchDelete;\n" +
                "            }\n" +
                "            \n" +
                "            // 更新主题文本\n" +
                "            applyTheme();\n" +
                "        }\n" +
                "        \n" +
                "        // 加载自动化任务\n" +
                "        function loadAutomations() {\n" +
                "            fetch('/api/automation')\n" +
                "                .then(response => response.json())\n" +
                "                .then(data => {\n" +
                "                    renderAutomationList(data.automations);\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('加载自动化任务错误:', error);\n" +
                "                    const grid = document.getElementById('automationGrid');\n" +
                "                    if (grid) grid.innerHTML = '<div style=\"color: var(--error); padding: var(--spacing-md);\">加载自动化任务失败</div>';\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        function renderAutomationList(automations) {\n" +
                "            const grid = document.getElementById('automationGrid');\n" +
                "            if (!grid) return;\n" +
                "            \n" +
                "            if (!automations || automations.length === 0) {\n" +
                "                grid.innerHTML = '<div class=\"empty-state\" style=\"text-align: center; padding: var(--spacing-xl); color: var(--text-secondary);\"><svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" style=\"width: 48px; height: 48px; color: var(--text-muted); margin-bottom: var(--spacing-md);\"><path d=\"M14.7 6.3a1 1 0 0 0 0 1.4l1.6 1.6a1 1 0 0 0 1.4 0l3.77-3.77a6 6 0 0 1-7.94 7.94l-6.91 6.91a6 6 0 0 1-1.56 1.56l-1.56 1.56-3.77 3.77a1 1 0 0 1-1.4-1.4l3.77-3.77a6 6 0 0 1 1.56-1.56l1.56-1.56 6.91-6.91a6 6 0 0 1 7.94-7.94l-3.77 3.77a1 1 0 0 0-1.4 0l-1.6-1.6a1 1 0 0 0-1.4 0z\"/></svg><p>没有找到自动化任务，点击\"新建自动化\"按钮创建一个</p></div>';\n" +
                "                return;\n" +
                "            }\n" +
                "            \n" +
                "            grid.innerHTML = '';\n" +
                "            \n" +
                "            automations.forEach(automation => {\n" +
                "                const card = document.createElement('div');\n" +
                "                card.className = 'automation-card' + (automation.enabled ? ' enabled' : '');\n" +
                "                card.dataset.id = automation.id;\n" +
                "                \n" +
                "                const t = translations[currentLang];\n" +
                "                \n" +
                "                card.innerHTML = `\n" +
                "                    <div class=\"automation-header\">\n" +
                "                        <div style=\"display: flex; align-items: center; gap: var(--spacing-sm);\">\n" +
                "                            <input type=\"checkbox\" class=\"automation-checkbox\" style=\"width: 16px; height: 16px;\" onchange=\"updateSelectAll()\">\n" +
                "                            <span class=\"automation-name\">${escapeHtml(automation.name)}</span>\n" +
                "                        </div>\n" +
                "                        <div class=\"automation-actions\">\n" +
                "                            <button class=\"btn\" onclick=\"toggleAutomation('${escapeHtml(automation.id)}')\" style=\"padding: var(--spacing-xs) var(--spacing-sm);\">\n" +
                "                                <span class=\"btn-icon\">\n" +
                "                                    <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" style=\"width: 16px; height: 16px;\">\n" +
                "                                        <polygon points=\"5,3 19,12 5,21\"/>\n" +
                "                                    </svg>\n" +
                "                                </span>\n" +
                "                                <span>${automation.enabled ? t.stop : t.run}</span>\n" +
                "                            </button>\n" +
                "                            <button class=\"btn\" onclick=\"editAutomation('${escapeHtml(automation.id)}')\" style=\"padding: var(--spacing-xs) var(--spacing-sm);\">\n" +
                "                                <span class=\"btn-icon\">\n" +
                "                                    <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" style=\"width: 16px; height: 16px;\">\n" +
                "                                        <path d=\"M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7\"/>\n" +
                "                                        <path d=\"M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z\"/>\n" +
                "                                    </svg>\n" +
                "                                </span>\n" +
                "                                <span>${t.edit}</span>\n" +
                "                            </button>\n" +
                "                            <button class=\"btn btn-danger\" onclick=\"deleteAutomation('${escapeHtml(automation.id)}')\" style=\"padding: var(--spacing-xs) var(--spacing-sm);\">\n" +
                "                                <span class=\"btn-icon\">\n" +
                "                                    <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" style=\"width: 16px; height: 16px;\">\n" +
                "                                        <path d=\"M3 6h18M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2\"/>\n" +
                "                                    </svg>\n" +
                "                                </span>\n" +
                "                                <span>${t.delete}</span>\n" +
                "                            </button>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"automation-description\">${escapeHtml(automation.description)}</div>\n" +
                "                    <div class=\"automation-meta\">\n" +
                "                        <span class=\"automation-tag status\">${automation.enabled ? t.enabled : t.disabled}</span>\n" +
                "                        <span class=\"automation-tag type\">${getAutomationTypeText(automation.type)}</span>\n" +
                "                        <span class=\"automation-tag\">触发: ${escapeHtml(automation.trigger || '手动')}</span>\n" +
                "                    </div>\n" +
                "                `;\n" +
                "                grid.appendChild(card);\n" +
                "            });\n" +
                "        }\n" +
                "        \n" +
                "        function getAutomationTypeText(type) {\n" +
                "            const t = translations[currentLang];\n" +
                "            switch (type) {\n" +
                "                case 'movement': return t.movement;\n" +
                "                case 'detection': return t.detection;\n" +
                "                case 'event': return t.event;\n" +
                "                case 'control': return t.control;\n" +
                "                case 'module': return t.module;\n" +
                "                default: return type;\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        function escapeHtml(text) {\n" +
                "            const div = document.createElement('div');\n" +
                "            div.textContent = text;\n" +
                "            return div.innerHTML;\n" +
                "        }\n" +
                "        \n" +
                "        function createNewAutomation() {\n" +
                "            window.location.href = '/automation/editor';\n" +
                "        }\n" +
                "        \n" +
                "        function editAutomation(id) {\n" +
                "            window.location.href = '/automation/editor?id=' + encodeURIComponent(id);\n" +
                "        }\n" +
                "        \n" +
                "        function toggleAutomation(id) {\n" +
                "            fetch('/api/automation/' + encodeURIComponent(id), {\n" +
                "                method: 'POST',\n" +
                "                headers: { 'Content-Type': 'application/json' },\n" +
                "                body: JSON.stringify({ action: 'toggle' })\n" +
                "            })\n" +
                "            .then(response => response.json())\n" +
                "            .then(data => {\n" +
                "                loadAutomations();\n" +
                "            })\n" +
                "            .catch(error => {\n" +
                "                console.error('切换自动化错误:', error);\n" +
                "                alert('切换自动化失败: ' + error.message);\n" +
                "            });\n" +
                "        }\n" +
                "        \n" +
                "        function deleteAutomation(id) {\n" +
                "            const t = translations[currentLang];\n" +
                "            if (!confirm(t.confirmDelete)) return;\n" +
                "            \n" +
                "            fetch('/api/automation/' + encodeURIComponent(id), {\n" +
                "                method: 'DELETE'\n" +
                "            })\n" +
                "            .then(response => response.json())\n" +
                "            .then(data => {\n" +
                "                loadAutomations();\n" +
                "                alert(t.automationDeleted);\n" +
                "            })\n" +
                "            .catch(error => {\n" +
                "                console.error('删除自动化错误:', error);\n" +
                "                alert('删除自动化失败: ' + error.message);\n" +
                "            });\n" +
                "        }\n" +
                "        \n" +
                "        function batchEnable() {\n" +
                "            const selectedAutomations = getSelectedAutomations();\n" +
                "            if (selectedAutomations.length === 0) {\n" +
                "                alert('请先选择要启用的自动化任务');\n" +
                "                return;\n" +
                "            }\n" +
                "            \n" +
                "            if (confirm('确定要启用选中的 ' + selectedAutomations.length + ' 个自动化任务吗？')) {\n" +
                "                selectedAutomations.forEach(id => {\n" +
                "                    fetch('/api/automation/' + encodeURIComponent(id), {\n" +
                "                        method: 'POST',\n" +
                "                        headers: { 'Content-Type': 'application/json' },\n" +
                "                        body: JSON.stringify({ action: 'update', enabled: true })\n" +
                "                    })\n" +
                "                    .then(response => response.json())\n" +
                "                    .then(data => {\n" +
                "                        if (!data.success) {\n" +
                "                            console.error('启用自动化任务失败:', id, data.error);\n" +
                "                        }\n" +
                "                    })\n" +
                "                    .catch(error => {\n" +
                "                        console.error('启用自动化任务错误:', error);\n" +
                "                    });\n" +
                "                });\n" +
                "                \n" +
                "                // 重新加载自动化任务列表\n" +
                "                loadAutomations();\n" +
                "                alert('批量启用完成');\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        function batchDisable() {\n" +
                "            const selectedAutomations = getSelectedAutomations();\n" +
                "            if (selectedAutomations.length === 0) {\n" +
                "                alert('请先选择要禁用的自动化任务');\n" +
                "                return;\n" +
                "            }\n" +
                "            \n" +
                "            if (confirm('确定要禁用选中的 ' + selectedAutomations.length + ' 个自动化任务吗？')) {\n" +
                "                selectedAutomations.forEach(id => {\n" +
                "                    fetch('/api/automation/' + encodeURIComponent(id), {\n" +
                "                        method: 'POST',\n" +
                "                        headers: { 'Content-Type': 'application/json' },\n" +
                "                        body: JSON.stringify({ action: 'update', enabled: false })\n" +
                "                    })\n" +
                "                    .then(response => response.json())\n" +
                "                    .then(data => {\n" +
                "                        if (!data.success) {\n" +
                "                            console.error('禁用自动化任务失败:', id, data.error);\n" +
                "                        }\n" +
                "                    })\n" +
                "                    .catch(error => {\n" +
                "                        console.error('禁用自动化任务错误:', error);\n" +
                "                    });\n" +
                "                });\n" +
                "                \n" +
                "                // 重新加载自动化任务列表\n" +
                "                loadAutomations();\n" +
                "                alert('批量禁用完成');\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        function batchDelete() {\n" +
                "            const selectedAutomations = getSelectedAutomations();\n" +
                "            if (selectedAutomations.length === 0) {\n" +
                "                alert('请先选择要删除的自动化任务');\n" +
                "                return;\n" +
                "            }\n" +
                "            \n" +
                "            if (confirm('确定要删除选中的 ' + selectedAutomations.length + ' 个自动化任务吗？此操作不可撤销！')) {\n" +
                "                selectedAutomations.forEach(id => {\n" +
                "                    fetch('/api/automation/' + encodeURIComponent(id), {\n" +
                "                        method: 'DELETE'\n" +
                "                    })\n" +
                "                    .then(response => response.json())\n" +
                "                    .then(data => {\n" +
                "                        if (!data.success) {\n" +
                "                            console.error('删除自动化任务失败:', id, data.error);\n" +
                "                        }\n" +
                "                    })\n" +
                "                    .catch(error => {\n" +
                "                        console.error('删除自动化任务错误:', error);\n" +
                "                    });\n" +
                "                });\n" +
                "                \n" +
                "                // 重新加载自动化任务列表\n" +
                "                loadAutomations();\n" +
                "                alert('批量删除完成');\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        // 获取选中的自动化任务ID\n" +
                "        function getSelectedAutomations() {\n" +
                "            const selected = [];\n" +
                "            document.querySelectorAll('.automation-card input[type=\"checkbox\"]:checked').forEach(checkbox => {\n" +
                "                const card = checkbox.closest('.automation-card');\n" +
                "                if (card && card.dataset.id) {\n" +
                "                    selected.push(card.dataset.id);\n" +
                "                }\n" +
                "            });\n" +
                "            return selected;\n" +
                "        }\n" +
                "        \n" +
                "        // 全选/取消全选功能\n" +
                "        function toggleSelectAll() {\n" +
                "            const selectAllCheckbox = document.getElementById('selectAllCheckbox');\n" +
                "            const checkboxes = document.querySelectorAll('.automation-card input[type=\"checkbox\"]');\n" +
                "            \n" +
                "            checkboxes.forEach(checkbox => {\n" +
                "                checkbox.checked = selectAllCheckbox.checked;\n" +
                "            });\n" +
                "        }\n" +
                "        \n" +
                "        // 更新全选复选框状态\n" +
                "        function updateSelectAll() {\n" +
                "            const checkboxes = document.querySelectorAll('.automation-card input[type=\"checkbox\"]');\n" +
                "            const selectAllCheckbox = document.getElementById('selectAllCheckbox');\n" +
                "            \n" +
                "            const allChecked = Array.from(checkboxes).every(checkbox => checkbox.checked);\n" +
                "            selectAllCheckbox.checked = allChecked;\n" +
                "            \n" +
                "            // 如果不是所有都被选中，取消 全选 复选框的选中状态\n" +
                "            if (!allChecked) {\n" +
                "                selectAllCheckbox.checked = false;\n" +
                "            }\n" +
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
                "            loadAutomations();\n" +
                "            \n" +
                "            // 添加搜索和筛选功能\n" +
                "            document.getElementById('automationSearch').addEventListener('input', loadAutomations);\n" +
                "            document.getElementById('automationTypeFilter').addEventListener('change', loadAutomations);\n" +
                "            document.getElementById('automationStatusFilter').addEventListener('change', loadAutomations);\n" +
                "        });\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }
}
