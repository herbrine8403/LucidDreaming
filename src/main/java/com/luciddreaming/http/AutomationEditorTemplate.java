package com.luciddreaming.http;

public class AutomationEditorTemplate {
    public static String generateHTML() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"zh-CN\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>自动化编辑器 - Lucid Dreaming</title>\n" +
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
                "            max-width: 1600px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: var(--spacing-lg);\n" +
                "            height: calc(100vh - 2 * var(--spacing-lg));\n" +
                "        }\n" +
                "        \n" +
                "        /* 头部样式 */\n" +
                "        .header {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "            align-items: center;\n" +
                "            margin-bottom: var(--spacing-lg);\n" +
                "            padding-bottom: var(--spacing-md);\n" +
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
                "            font-size: 1.5rem;\n" +
                "            font-weight: 700;\n" +
                "            color: var(--text-primary);\n" +
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
                "        /* 主体布局 - 使用 Flexbox 实现三栏布局 */\n" +
                "        .editor-container {\n" +
                "            display: flex;\n" +
                "            height: calc(100% - 100px);\n" +
                "            gap: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        .blocks-panel {\n" +
                "            width: 250px;\n" +
                "            background-color: var(--bg-secondary);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            padding: var(--spacing-md);\n" +
                "            overflow-y: auto;\n" +
                "        }\n" +
                "        \n" +
                "        .blocks-category {\n" +
                "            margin-bottom: var(--spacing-lg);\n" +
                "        }\n" +
                "        \n" +
                "        .blocks-category-title {\n" +
                "            font-size: 0.875rem;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "            margin-bottom: var(--spacing-sm);\n" +
                "            padding-bottom: var(--spacing-xs);\n" +
                "            border-bottom: 1px solid var(--border-color);\n" +
                "        }\n" +
                "        \n" +
                "        .block-item {\n" +
                "            display: block;\n" +
                "            padding: var(--spacing-sm);\n" +
                "            margin-bottom: var(--spacing-xs);\n" +
                "            background-color: var(--bg-card);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-sm);\n" +
                "            cursor: grab;\n" +
                "            transition: all var(--transition-fast);\n" +
                "            font-size: 0.8125rem;\n" +
                "        }\n" +
                "        \n" +
                "        .block-item:hover {\n" +
                "            border-color: var(--accent-primary);\n" +
                "            background-color: var(--bg-hover);\n" +
                "            transform: translateY(-1px);\n" +
                "        }\n" +
                "        \n" +
                "        .workspace {\n" +
                "            flex: 1;\n" +
                "            background-color: var(--bg-secondary);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            padding: var(--spacing-lg);\n" +
                "            overflow-y: auto;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "        \n" +
                "        .workspace-header {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "            align-items: center;\n" +
                "            margin-bottom: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        .workspace-title {\n" +
                "            font-size: 1rem;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "        }\n" +
                "        \n" +
                "        .properties-panel {\n" +
                "            width: 300px;\n" +
                "            background-color: var(--bg-secondary);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            padding: var(--spacing-md);\n" +
                "            overflow-y: auto;\n" +
                "        }\n" +
                "        \n" +
                "        .properties-title {\n" +
                "            font-size: 0.875rem;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "            margin-bottom: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        .property-group {\n" +
                "            margin-bottom: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        .property-label {\n" +
                "            display: block;\n" +
                "            font-size: 0.8125rem;\n" +
                "            font-weight: 500;\n" +
                "            color: var(--text-secondary);\n" +
                "            margin-bottom: var(--spacing-xs);\n" +
                "        }\n" +
                "        \n" +
                "        .property-input {\n" +
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
                "        .property-input:focus {\n" +
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
                "        /* 响应式设计 */\n" +
                "        @media (max-width: 1200px) {\n" +
                "            .editor-container {\n" +
                "                flex-direction: column;\n" +
                "                height: auto;\n" +
                "            }\n" +
                "            \n" +
                "            .blocks-panel, .properties-panel {\n" +
                "                width: 100%;\n" +
                "                max-height: 300px;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <!-- 头部 -->\n" +
                "        <header class=\"header\">\n" +
                "            <div class=\"header-left\">\n" +
                "                <a href=\"/automation\" class=\"btn\">\n" +
                "                    <span class=\"btn-icon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <path d=\"M19 12H5M12 19l-7-7 7-7\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    <span id=\"backLink\">返回管理</span>\n" +
                "                </a>\n" +
                "                <div>\n" +
                "                    <h1 class=\"header-title\" id=\"pageTitle\">自动化编辑器</h1>\n" +
                "                    <p class=\"header-subtitle\" id=\"pageSubtitle\">使用图形化编程创建自动化任务</p>\n" +
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
                "                <button class=\"btn btn-primary\" onclick=\"runAutomation()\" id=\"runBtn\">\n" +
                "                    <span class=\"btn-icon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <polygon points=\"5,3 19,12 5,21\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    <span id=\"runText\">试运行</span>\n" +
                "                </button>\n" +
                "                <button class=\"btn btn-danger\" onclick=\"stopAutomation()\" id=\"stopBtn\">\n" +
                "                    <span class=\"btn-icon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <rect x=\"6\" y=\"6\" width=\"12\" height=\"12\" rx=\"1\" ry=\"1\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    <span id=\"stopText\">急停</span>\n" +
                "                </button>\n" +
                "                <button class=\"btn btn-primary\" onclick=\"saveAutomation()\" id=\"saveBtn\">\n" +
                "                    <span class=\"btn-icon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <path d=\"M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11l5 5v11a2 2 0 0 1-2 2z\"/>\n" +
                "                            <polyline points=\"17,21 17,13 7,13 7,21\"/>\n" +
                "                            <polyline points=\"7,3 7,8 15,8\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    <span id=\"saveText\">保存</span>\n" +
                "                </button>\n" +
                "            </div>\n" +
                "        </header>\n" +
                "        \n" +
                "        <!-- 编辑器容器 -->\n" +
                "        <div class=\"editor-container\">\n" +
                "            <!-- 代码积木面板 -->\n" +
                "            <div class=\"blocks-panel\">\n" +
                "                <div class=\"blocks-category\">\n" +
                "                    <div class=\"blocks-category-title\" id=\"eventCategory\">事件</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"event\" data-block=\"when_start\">当开始时</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"event\" data-block=\"when_scoreboard_change\">当计分板变化时</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"event\" data-block=\"when_health_change\">当血量变化时</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"event\" data-block=\"when_entity_nearby\">当实体在附近时</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"event\" data-block=\"when_hunger_change\">当饥饿值变化时</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"event\" data-block=\"when_position_change\">当位置变化时</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"event\" data-block=\"when_dimension_change\">当维度变化时</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"event\" data-block=\"when_time_passes\">当时间经过时</div>\n" +
                "                </div>\n" +
                "                \n" +
                "                <div class=\"blocks-category\">\n" +
                "                    <div class=\"blocks-category-title\" id=\"controlCategory\">控制</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"control\" data-block=\"wait\">等待 x 秒</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"control\" data-block=\"if_else\">如果 x 那么 y 否则 z</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"control\" data-block=\"if\">如果 x 那么 y</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"control\" data-block=\"repeat\">重复 x 次</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"control\" data-block=\"repeat_until\">重复直到 x</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"control\" data-block=\"forever\">永远</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"control\" data-block=\"wait_until\">等待直到 x</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"control\" data-block=\"stop_all\">停止全部</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"control\" data-block=\"stop_this\">停止这个</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"control\" data-block=\"stop_other\">停止其他脚本</div>\n" +
                "                </div>\n" +
                "                \n" +
                "                <div class=\"blocks-category\">\n" +
                "                    <div class=\"blocks-category-title\" id=\"movementCategory\">运动</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"movement\" data-block=\"move_steps\">移动 x 步</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"movement\" data-block=\"turn_right\">右转 x 度</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"movement\" data-block=\"turn_left\">左转 x 度</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"movement\" data-block=\"face_direction\">面向 x 方向</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"movement\" data-block=\"move_to_xyz\">移动到 x,y,z</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"movement\" data-block=\"interact_entity\">与实体互动</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"movement\" data-block=\"jump\">跳跃</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"movement\" data-block=\"sneak\">潜行</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"movement\" data-block=\"sprint\">疾跑</div>\n" +
                "                </div>\n" +
                "                \n" +
                "                <div class=\"blocks-category\">\n" +
                "                    <div class=\"blocks-category-title\" id=\"detectionCategory\">侦测</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"detection\" data-block=\"scoreboard_change\">计分板变化？</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"detection\" data-block=\"health_change\">血量变化？</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"detection\" data-block=\"entity_nearby\">实体在附近？</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"detection\" data-block=\"player_distance\">玩家距离？</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"detection\" data-block=\"hunger_change\">饥饿值变化？</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"detection\" data-block=\"position_change\">位置变化？</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"detection\" data-block=\"item_change\">物品变化？</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"detection\" data-block=\"dimension_change\">维度变化？</div>\n" +
                "                </div>\n" +
                "                \n" +
                "                <div class=\"blocks-category\">\n" +
                "                    <div class=\"blocks-category-title\" id=\"moduleCategory\">模块</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"module\" data-block=\"enable_module\">启用模块</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"module\" data-block=\"disable_module\">禁用模块</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"module\" data-block=\"toggle_module\">切换模块</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"module\" data-block=\"module_status\">模块状态</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"module\" data-block=\"enable_autofish\">启用自动钓鱼</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"module\" data-block=\"enable_autoclicker\">启用自动点击</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"module\" data-block=\"enable_autokill\">启用自动击杀</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"module\" data-block=\"enable_antikick\">启用防踢</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"module\" data-block=\"enable_autowalk\">启用自动行走</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"module\" data-block=\"enable_norender\">启用不渲染</div>\n" +
                "                    <div class=\"block-item\" draggable=\"true\" data-type=\"module\" data-block=\"enable_fakeblack\">启用假黑屏</div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            \n" +
                "            <!-- 工作区 -->\n" +
                "            <div class=\"workspace\">\n" +
                "                <div class=\"workspace-header\">\n" +
                "                    <div class=\"workspace-title\" id=\"workspaceTitle\">代码工作区</div>\n" +
                "                    <div class=\"workspace-actions\">\n" +
                "                        <button class=\"btn\" onclick=\"clearWorkspace()\" id=\"clearWorkspaceBtn\">\n" +
                "                            <span class=\"btn-icon\">\n" +
                "                                <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                                    <path d=\"M3 6h18M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2\"/>\n" +
                "                                </svg>\n" +
                "                            </span>\n" +
                "                            <span id=\"clearText\">清空工作区</span>\n" +
                "                        </button>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"workspace-content\" id=\"workspace\">\n" +
                "                    <div style=\"color: var(--text-muted); text-align: center; padding: var(--spacing-xl);\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" style=\"width: 48px; height: 48px; margin-bottom: var(--spacing-md);\">\n" +
                "                            <path d=\"M14.7 6.3a1 1 0 0 0 0 1.4l1.6 1.6a1 1 0 0 0 1.4 0l3.77-3.77a6 6 0 0 1-7.94 7.94l-6.91 6.91a6 6 0 0 1-1.56 1.56l-1.56 1.56-3.77 3.77a1 1 0 0 1-1.4-1.4l3.77-3.77a6 6 0 0 1 1.56-1.56l1.56-1.56 6.91-6.91a6 6 0 0 1 7.94-7.94l-3.77 3.77a1 1 0 0 0-1.4 0l-1.6-1.6a1 1 0 0 0-1.4 0z\"/>\n" +
                "                        </svg>\n" +
                "                        <p id=\"workspaceEmptyText\">将代码积木拖拽到这里开始编程</p>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            \n" +
                "            <!-- 属性面板 -->\n" +
                "            <div class=\"properties-panel\">\n" +
                "                <div class=\"properties-title\" id=\"propertiesTitle\">属性</div>\n" +
                "                <div id=\"propertiesContent\">\n" +
                "                    <div style=\"color: var(--text-muted); text-align: center; padding: var(--spacing-xl);\">\n" +
                "                        <p id=\"selectBlockText\">选择一个代码积木来编辑其属性</p>\n" +
                "                    </div>\n" +
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
                "                backLink: '返回管理',\n" +
                "                pageTitle: '自动化编辑器',\n" +
                "                pageSubtitle: '使用图形化编程创建自动化任务',\n" +
                "                themeText: { light: '浅色', dark: '深色', auto: '跟随系统' },\n" +
                "                runText: '试运行',\n" +
                "                stopText: '急停',\n" +
                "                saveText: '保存',\n" +
                "                clearText: '清空工作区',\n" +
                "                workspaceTitle: '代码工作区',\n" +
                "                workspaceEmptyText: '将代码积木拖拽到这里开始编程',\n" +
                "                propertiesTitle: '属性',\n" +
                "                selectBlockText: '选择一个代码积木来编辑其属性',\n" +
                "                eventCategory: '事件',\n" +
                "                controlCategory: '控制',\n" +
                "                movementCategory: '运动',\n" +
                "                detectionCategory: '侦测',\n" +
                "                moduleCategory: '模块',\n" +
                "                automationSaved: '自动化已保存',\n" +
                "                automationUpdated: '自动化已更新',\n" +
                "                automationCreated: '自动化已创建',\n" +
                "                confirmSave: '是否保存当前的自动化？',\n" +
                "                automationRunning: '自动化正在运行',\n" +
                "                automationStopped: '自动化已停止'\n" +
                "            },\n" +
                "            en: {\n" +
                "                backLink: 'Back to Management',\n" +
                "                pageTitle: 'Automation Editor',\n" +
                "                pageSubtitle: 'Create automation tasks with visual programming',\n" +
                "                themeText: { light: 'Light', dark: 'Dark', auto: 'Auto' },\n" +
                "                runText: 'Run Test',\n" +
                "                stopText: 'Emergency Stop',\n" +
                "                saveText: 'Save',\n" +
                "                clearText: 'Clear Workspace',\n" +
                "                workspaceTitle: 'Code Workspace',\n" +
                "                workspaceEmptyText: 'Drag code blocks here to start programming',\n" +
                "                propertiesTitle: 'Properties',\n" +
                "                selectBlockText: 'Select a code block to edit its properties',\n" +
                "                eventCategory: 'Events',\n" +
                "                controlCategory: 'Control',\n" +
                "                movementCategory: 'Movement',\n" +
                "                detectionCategory: 'Detection',\n" +
                "                moduleCategory: 'Modules',\n" +
                "                automationSaved: 'Automation saved',\n" +
                "                automationUpdated: 'Automation updated',\n" +
                "                automationCreated: 'Automation created',\n" +
                "                confirmSave: 'Do you want to save the current automation?',\n" +
                "                automationRunning: 'Automation is running',\n" +
                "                automationStopped: 'Automation stopped'\n" +
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
                "        }\n" +
                "        \n" +
                "        function updateTranslations() {\n" +
                "            const t = translations[currentLang];\n" +
                "            if (!t) return;\n" +
                "            \n" +
                "            const elements = {\n" +
                "                backLink: 'Back to Management',\n" +
                "                pageTitle: 'pageTitle',\n" +
                "                pageSubtitle: 'pageSubtitle',\n" +
                "                runText: 'runText',\n" +
                "                stopText: 'stopText',\n" +
                "                saveText: 'saveText',\n" +
                "                clearText: 'clearText',\n" +
                "                workspaceTitle: 'workspaceTitle',\n" +
                "                workspaceEmptyText: 'workspaceEmptyText',\n" +
                "                propertiesTitle: 'propertiesTitle',\n" +
                "                selectBlockText: 'selectBlockText'\n" +
                "            };\n" +
                "            \n" +
                "            for (const [key, elementId] of Object.entries(elements)) {\n" +
                "                const element = document.getElementById(elementId);\n" +
                "                if (element && t[key]) {\n" +
                "                    element.textContent = t[key];\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            // 更新类别标题\n" +
                "            const eventCat = document.getElementById('eventCategory');\n" +
                "            const controlCat = document.getElementById('controlCategory');\n" +
                "            const movementCat = document.getElementById('movementCategory');\n" +
                "            const detectionCat = document.getElementById('detectionCategory');\n" +
                "            const moduleCat = document.getElementById('moduleCategory');\n" +
                "            \n" +
                "            if (eventCat && t.eventCategory) eventCat.textContent = t.eventCategory;\n" +
                "            if (controlCat && t.controlCategory) controlCat.textContent = t.controlCategory;\n" +
                "            if (movementCat && t.movementCategory) movementCat.textContent = t.movementCategory;\n" +
                "            if (detectionCat && t.detectionCategory) detectionCat.textContent = t.detectionCategory;\n" +
                "            if (moduleCat && t.moduleCategory) moduleCat.textContent = t.moduleCategory;\n" +
                "            \n" +
                "            // 更新主题文本\n" +
                "            applyTheme();\n" +
                "        }\n" +
                "        \n" +
                "        // 拖拽功能\n" +
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
                "            \n" +
                "            // 为所有代码积木添加拖拽事件\n" +
                "            const blocks = document.querySelectorAll('.block-item');\n" +
                "            blocks.forEach(block => {\n" +
                "                block.addEventListener('dragstart', dragStart);\n" +
                "            });\n" +
                "            \n" +
                "            // 工作区添加拖放事件\n" +
                "            const workspace = document.getElementById('workspace');\n" +
                "            workspace.addEventListener('dragover', dragOver);\n" +
                "            workspace.addEventListener('drop', drop);\n" +
                "        });\n" +
                "        \n" +
                "        let draggedBlock = null;\n" +
                "        \n" +
                "        function dragStart(e) {\n" +
                "            draggedBlock = e.target;\n" +
                "            e.dataTransfer.setData('text/plain', e.target.dataset.block);\n" +
                "            e.dataTransfer.effectAllowed = 'copy';\n" +
                "        }\n" +
                "        \n" +
                "        function dragOver(e) {\n" +
                "            e.preventDefault();\n" +
                "            e.dataTransfer.dropEffect = 'copy';\n" +
                "        }\n" +
                "        \n" +
                "        function drop(e) {\n" +
                "            e.preventDefault();\n" +
                "            \n" +
                "            const blockType = e.dataTransfer.getData('text/plain');\n" +
                "            if (!blockType) return;\n" +
                "            \n" +
                "            const block = document.createElement('div');\n" +
                "            block.className = 'block-item';\n" +
                "            block.draggable = true;\n" +
                "            block.dataset.type = draggedBlock.dataset.type;\n" +
                "            block.dataset.block = draggedBlock.dataset.block;\n" +
                "            block.textContent = draggedBlock.textContent;\n" +
                "            block.style.position = 'absolute';\n" +
                "            block.style.left = (e.clientX - 250) + 'px';\n" +
                "            block.style.top = (e.clientY - 150) + 'px';\n" +
                "            block.style.cursor = 'move';\n" +
                "            \n" +
                "            // 添加点击选择事件\n" +
                "            block.addEventListener('click', function() {\n" +
                "                selectBlock(block);\n" +
                "            });\n" +
                "            \n" +
                "            // 添加拖拽移动事件\n" +
                "            block.addEventListener('mousedown', function(e) {\n" +
                "                if (e.button !== 0) return; // 只处理左键\n" +
                "                \n" +
                "                const startX = e.clientX;\n" +
                "                const startY = e.clientY;\n" +
                "                const startLeft = parseInt(block.style.left);\n" +
                "                const startTop = parseInt(block.style.top);\n" +
                "                \n" +
                "                function onMouseMove(moveEvent) {\n" +
                "                    const dx = moveEvent.clientX - startX;\n" +
                "                    const dy = moveEvent.clientY - startY;\n" +
                "                    \n" +
                "                    block.style.left = (startLeft + dx) + 'px';\n" +
                "                    block.style.top = (startTop + dy) + 'px';\n" +
                "                }\n" +
                "                \n" +
                "                function onMouseUp() {\n" +
                "                    document.removeEventListener('mousemove', onMouseMove);\n" +
                "                    document.removeEventListener('mouseup', onMouseUp);\n" +
                "                }\n" +
                "                \n" +
                "                document.addEventListener('mousemove', onMouseMove);\n" +
                "                document.addEventListener('mouseup', onMouseUp);\n" +
                "                \n" +
                "                e.preventDefault();\n" +
                "            });\n" +
                "            \n" +
                "            // 添加右键删除事件\n" +
                "            block.addEventListener('contextmenu', function(e) {\n" +
                "                e.preventDefault();\n" +
                "                if (confirm('确定要删除这个代码积木吗？')) {\n" +
                "                    block.remove();\n" +
                "                    document.getElementById('propertiesContent').innerHTML = '<div style=\"color: var(--text-muted); text-align: center; padding: var(--spacing-xl);\"><p id=\"selectBlockText\">选择一个代码积木来编辑其属性</p></div>';\n" +
                "                    updateTranslations(); // 更新新添加元素的翻译\n" +
                "                }\n" +
                "            });\n" +
                "            \n" +
                "            // 移除空状态提示\n" +
                "            const emptyIndicator = workspace.querySelector('[style*=\"text-align: center\"]');\n" +
                "            if (emptyIndicator) {\n" +
                "                emptyIndicator.remove();\n" +
                "            }\n" +
                "            \n" +
                "            workspace.appendChild(block);\n" +
                "        }\n" +
                "        \n" +
                "        function selectBlock(block) {\n" +
                "            // 移除之前的选择样式\n" +
                "            document.querySelectorAll('.block-item').forEach(b => {\n" +
                "                b.style.border = '1px solid var(--border-color)';\n" +
                "            });\n" +
                "            \n" +
                "            // 添加选择样式\n" +
                "            block.style.border = '2px solid var(--accent-primary)';\n" +
                "            \n" +
                "            // 显示属性面板\n" +
                "            const propertiesContent = document.getElementById('propertiesContent');\n" +
                "            const blockType = block.dataset.type;\n" +
                "            const blockName = block.dataset.block;\n" +
                "            \n" +
                "            propertiesContent.innerHTML = `\n" +
                "                <div class=\"property-group\">\n" +
                "                    <label class=\"property-label\">类型</label>\n" +
                "                    <input type=\"text\" class=\"property-input\" value=\"${blockType}\" readonly>\n" +
                "                </div>\n" +
                "                <div class=\"property-group\">\n" +
                "                    <label class=\"property-label\">名称</label>\n" +
                "                    <input type=\"text\" class=\"property-input\" value=\"${blockName}\" readonly>\n" +
                "                </div>\n" +
                "                <div class=\"property-group\">\n" +
                "                    <label class=\"property-label\">描述</label>\n" +
                "                    <input type=\"text\" class=\"property-input\" value=\"${block.textContent}\" readonly>\n" +
                "                </div>\n" +
                "            `;\n" +
                "        }\n" +
                "        \n" +
                "        function runAutomation() {\n" +
                "            const t = translations[currentLang];\n" +
                "            // 获取当前页面的自动化任务ID（如果有的话）\n" +
                "            const urlParams = new URLSearchParams(window.location.search);\n" +
                "            const automationId = urlParams.get('id');\n" +
                "            \n" +
                "            if (automationId) {\n" +
                "                // 如果有ID，执行指定的自动化任务\n" +
                "                fetch('/api/automation/' + encodeURIComponent(automationId), {\n" +
                "                    method: 'POST',\n" +
                "                    headers: { 'Content-Type': 'application/json' },\n" +
                "                    body: JSON.stringify({ action: 'execute' })\n" +
                "                })\n" +
                "                .then(response => response.json())\n" +
                "                .then(data => {\n" +
                "                    if (data.success) {\n" +
                "                        alert(t.automationRunning);\n" +
                "                    } else {\n" +
                "                        alert('执行失败: ' + data.error);\n" +
                "                    }\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('执行自动化任务错误:', error);\n" +
                "                    alert('执行自动化任务失败: ' + error.message);\n" +
                "                });\n" +
                "            } else {\n" +
                "                alert('请先保存自动化任务，然后才能执行');\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        function stopAutomation() {\n" +
                "            const t = translations[currentLang];\n" +
                "            // 获取当前页面的自动化任务ID（如果有的话）\n" +
                "            const urlParams = new URLSearchParams(window.location.search);\n" +
                "            const automationId = urlParams.get('id');\n" +
                "            \n" +
                "            if (automationId) {\n" +
                "                // 发送停止命令 - 通过禁用任务实现停止\n" +
                "                fetch('/api/automation/' + encodeURIComponent(automationId), {\n" +
                "                    method: 'POST',\n" +
                "                    headers: { 'Content-Type': 'application/json' },\n" +
                "                    body: JSON.stringify({ action: 'update', enabled: false })\n" +
                "                })\n" +
                "                .then(response => response.json())\n" +
                "                .then(data => {\n" +
                "                    if (data.success) {\n" +
                "                        alert(t.automationStopped);\n" +
                "                    } else {\n" +
                "                        alert('停止失败: ' + data.error);\n" +
                "                    }\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('停止自动化任务错误:', error);\n" +
                "                    alert('停止自动化任务失败: ' + error.message);\n" +
                "                });\n" +
                "            } else {\n" +
                "                alert('没有正在运行的自动化任务');\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        function saveAutomation() {\n" +
                "            const t = translations[currentLang];\n" +
                "            const urlParams = new URLSearchParams(window.location.search);\n" +
                "            const automationId = urlParams.get('id');\n" +
                "            \n" +
                "            // 获取当前工作区中的所有积木\n" +
                "            const blocks = document.querySelectorAll('#workspace .block-item');\n" +
                "            const instructions = [];\n" +
                "            \n" +
                "            blocks.forEach(block => {\n" +
                "                const instruction = {\n" +
                "                    type: block.dataset.block,\n" +
                "                    params: {},\n" +
                "                    delay: 0\n" +
                "                };\n" +
                "                \n" +
                "                // 根据积木类型收集参数\n" +
                "                switch(block.dataset.block) {\n" +
                "                    case 'move_steps':\n" +
                "                        instruction.params.steps = block.dataset.steps || '1';\n" +
                "                        break;\n" +
                "                    case 'turn_right':\n" +
                "                        instruction.params.degrees = block.dataset.degrees || '90';\n" +
                "                        break;\n" +
                "                    case 'turn_left':\n" +
                "                        instruction.params.degrees = block.dataset.degrees || '90';\n" +
                "                        break;\n" +
                "                    case 'face_direction':\n" +
                "                        instruction.params.direction = block.dataset.direction || '0';\n" +
                "                        break;\n" +
                "                    case 'move_to_xyz':\n" +
                "                        instruction.params.x = block.dataset.x || '0';\n" +
                "                        instruction.params.y = block.dataset.y || '0';\n" +
                "                        instruction.params.z = block.dataset.z || '0';\n" +
                "                        break;\n" +
                "                    case 'interact_entity':\n" +
                "                        instruction.params.entityType = block.dataset.entityType || 'nearest';\n" +
                "                        instruction.params.interactionType = block.dataset.interactionType || 'right_click';\n" +
                "                        instruction.params.maxDistance = block.dataset.maxDistance || '3';\n" +
                "                        break;\n" +
                "                    case 'jump':\n" +
                "                        instruction.params.count = block.dataset.count || '1';\n" +
                "                        break;\n" +
                "                    case 'sneak':\n" +
                "                        instruction.params.duration = block.dataset.duration || '1';\n" +
                "                        break;\n" +
                "                    case 'sprint':\n" +
                "                        instruction.params.duration = block.dataset.duration || '1';\n" +
                "                        break;\n" +
                "                }\n" +
                "                \n" +
                "                instructions.push(instruction);\n" +
                "            });\n" +
                "            \n" +
                "            // 构建要保存的数据\n" +
                "            const automationData = {\n" +
                "                name: document.querySelector('.header-title').textContent || '未命名自动化',\n" +
                "                description: '通过图形化编程创建的自动化任务',\n" +
                "                type: 'custom',\n" +
                "                enabled: false, // 默认不启用\n" +
                "                trigger: 'manual',\n" +
                "                instructions: instructions\n" +
                "            };\n" +
                "            \n" +
                "            if (automationId) {\n" +
                "                // 更新现有自动化任务\n" +
                "                fetch('/api/automation/' + encodeURIComponent(automationId), {\n" +
                "                    method: 'POST',\n" +
                "                    headers: { 'Content-Type': 'application/json' },\n" +
                "                    body: JSON.stringify({ \n" +
                "                        action: 'update',\n" +
                "                        ...automationData\n" +
                "                    })\n" +
                "                })\n" +
                "                .then(response => response.json())\n" +
                "                .then(data => {\n" +
                "                    if (data.success) {\n" +
                "                        alert(t.automationUpdated);\n" +
                "                    } else {\n" +
                "                        alert('保存失败: ' + data.error);\n" +
                "                    }\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('保存自动化任务错误:', error);\n" +
                "                    alert('保存自动化任务失败: ' + error.message);\n" +
                "                });\n" +
                "            } else {\n" +
                "                // 创建新自动化任务\n" +
                "                fetch('/api/automation', {\n" +
                "                    method: 'POST',\n" +
                "                    headers: { 'Content-Type': 'application/json' },\n" +
                "                    body: JSON.stringify({ \n" +
                "                        action: 'create',\n" +
                "                        ...automationData\n" +
                "                    })\n" +
                "                })\n" +
                "                .then(response => response.json())\n" +
                "                .then(data => {\n" +
                "                    if (data.success) {\n" +
                "                        alert(t.automationCreated);\n" +
                "                        // 重定向到编辑已保存的任务\n" +
                "                        window.location.href = '/automation/editor?id=' + data.id;\n" +
                "                    } else {\n" +
                "                        alert('创建失败: ' + data.error);\n" +
                "                    }\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('创建自动化任务错误:', error);\n" +
                "                    alert('创建自动化任务失败: ' + error.message);\n" +
                "                });\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        function clearWorkspace() {\n" +
                "            if (confirm('确定要清空整个工作区吗？此操作不可撤销。')) {\n" +
                "                const workspace = document.getElementById('workspace');\n" +
                "                workspace.innerHTML = '<div style=\"color: var(--text-muted); text-align: center; padding: var(--spacing-xl);\"><svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" style=\"width: 48px; height: 48px; margin-bottom: var(--spacing-md);\"><path d=\"M14.7 6.3a1 1 0 0 0 0 1.4l1.6 1.6a1 1 0 0 0 1.4 0l3.77-3.77a6 6 0 0 1-7.94 7.94l-6.91 6.91a6 6 0 0 1-1.56 1.56l-1.56 1.56-3.77 3.77a1 1 0 0 1-1.4-1.4l3.77-3.77a6 6 0 0 1 1.56-1.56l1.56-1.56 6.91-6.91a6 6 0 0 1 7.94-7.94l-3.77 3.77a1 1 0 0 0-1.4 0l-1.6-1.6a1 1 0 0 0-1.4 0z\"/></svg><p id=\"workspaceEmptyText\">将代码积木拖拽到这里开始编程</p></div>';\n" +
                "                updateTranslations(); // 重新翻译新添加的元素\n" +
                "            }\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }
}
