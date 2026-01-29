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
                "            /* 主题色 */\n" +
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
                "            --font-sans: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Noto Sans', Helvetica, Arial, sans-serif;\n" +
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
                "        /* 任务网格 */\n" +
                "        .tasks-grid {\n" +
                "            display: grid;\n" +
                "            grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));\n" +
                "            gap: var(--spacing-lg);\n" +
                "        }\n" +
                "        \n" +
                "        /* 任务卡片 */\n" +
                "        .task-card {\n" +
                "            background-color: var(--bg-card);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-lg);\n" +
                "            padding: var(--spacing-lg);\n" +
                "            box-shadow: var(--shadow-sm);\n" +
                "            transition: all var(--transition-base);\n" +
                "            position: relative;\n" +
                "        }\n" +
                "        \n" +
                "        .task-card:hover {\n" +
                "            box-shadow: var(--shadow-md);\n" +
                "        }\n" +
                "        \n" +
                "        .task-card.enabled {\n" +
                "            border-color: var(--success);\n" +
                "            background-color: var(--success-bg);\n" +
                "        }\n" +
                "        \n" +
                "        .task-card.running {\n" +
                "            border-color: var(--accent-primary);\n" +
                "            box-shadow: 0 0 0 2px var(--accent-primary-light);\n" +
                "        }\n" +
                "        \n" +
                "        .task-header {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "            align-items: flex-start;\n" +
                "            margin-bottom: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        .task-title {\n" +
                "            font-size: 1.125rem;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "            margin-bottom: var(--spacing-xs);\n" +
                "        }\n" +
                "        \n" +
                "        .task-description {\n" +
                "            font-size: 0.875rem;\n" +
                "            color: var(--text-secondary);\n" +
                "            line-height: 1.5;\n" +
                "        }\n" +
                "        \n" +
                "        .task-status {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: var(--spacing-sm);\n" +
                "            margin-bottom: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        .status-badge {\n" +
                "            display: inline-flex;\n" +
                "            align-items: center;\n" +
                "            font-size: 0.75rem;\n" +
                "            padding: 2px 8px;\n" +
                "            border-radius: var(--radius-sm);\n" +
                "            font-weight: 500;\n" +
                "        }\n" +
                "        \n" +
                "        .status-badge.enabled {\n" +
                "            background-color: var(--success-bg);\n" +
                "            color: var(--success);\n" +
                "        }\n" +
                "        \n" +
                "        .status-badge.disabled {\n" +
                "            background-color: var(--bg-secondary);\n" +
                "            color: var(--text-secondary);\n" +
                "        }\n" +
                "        \n" +
                "        .status-badge.running {\n" +
                "            background-color: var(--accent-blue-light);\n" +
                "            color: var(--accent-blue);\n" +
                "        }\n" +
                "        \n" +
                "        .task-actions {\n" +
                "            display: flex;\n" +
                "            gap: var(--spacing-sm);\n" +
                "            flex-wrap: wrap;\n" +
                "        }\n" +
                "        \n" +
                "        .task-meta {\n" +
                "            margin-top: var(--spacing-md);\n" +
                "            padding-top: var(--spacing-md);\n" +
                "            border-top: 1px solid var(--border-color);\n" +
                "            font-size: 0.75rem;\n" +
                "            color: var(--text-muted);\n" +
                "        }\n" +
                "        \n" +
                "        /* 空状态 */\n" +
                "        .empty-state {\n" +
                "            text-align: center;\n" +
                "            padding: var(--spacing-xl);\n" +
                "            color: var(--text-secondary);\n" +
                "        }\n" +
                "        \n" +
                "        .empty-state svg {\n" +
                "            width: 64px;\n" +
                "            height: 64px;\n" +
                "            color: var(--text-muted);\n" +
                "            margin-bottom: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        .empty-state h3 {\n" +
                "            font-size: 1.25rem;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "            margin-bottom: var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .empty-state p {\n" +
                "            margin-bottom: var(--spacing-lg);\n" +
                "        }\n" +
                "        \n" +
                "        /* 模态框 */\n" +
                "        .modal-overlay {\n" +
                "            position: fixed;\n" +
                "            top: 0;\n" +
                "            left: 0;\n" +
                "            right: 0;\n" +
                "            bottom: 0;\n" +
                "            background-color: rgba(0, 0, 0, 0.5);\n" +
                "            display: none;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            z-index: 1000;\n" +
                "        }\n" +
                "        \n" +
                "        .modal-overlay.show {\n" +
                "            display: flex;\n" +
                "        }\n" +
                "        \n" +
                "        .modal {\n" +
                "            background-color: var(--bg-card);\n" +
                "            border-radius: var(--radius-lg);\n" +
                "            padding: var(--spacing-xl);\n" +
                "            max-width: 500px;\n" +
                "            width: 90%;\n" +
                "            box-shadow: var(--shadow-lg);\n" +
                "        }\n" +
                "        \n" +
                "        .modal-header {\n" +
                "            margin-bottom: var(--spacing-lg);\n" +
                "        }\n" +
                "        \n" +
                "        .modal-title {\n" +
                "            font-size: 1.5rem;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "        }\n" +
                "        \n" +
                "        .form-group {\n" +
                "            margin-bottom: var(--spacing-md);\n" +
                "        }\n" +
                "        \n" +
                "        .form-label {\n" +
                "            display: block;\n" +
                "            font-weight: 500;\n" +
                "            color: var(--text-primary);\n" +
                "            margin-bottom: var(--spacing-sm);\n" +
                "        }\n" +
                "        \n" +
                "        .form-input {\n" +
                "            width: 100%;\n" +
                "            padding: var(--spacing-sm) var(--spacing-md);\n" +
                "            font-size: 0.9375rem;\n" +
                "            color: var(--text-primary);\n" +
                "            background-color: var(--bg-secondary);\n" +
                "            border: 1px solid var(--border-color);\n" +
                "            border-radius: var(--radius-md);\n" +
                "            transition: all var(--transition-fast);\n" +
                "        }\n" +
                "        \n" +
                "        .form-input:focus {\n" +
                "            outline: none;\n" +
                "            border-color: var(--accent-primary);\n" +
                "            box-shadow: 0 0 0 3px rgba(14, 165, 233, 0.1);\n" +
                "        }\n" +
                "        \n" +
                "        .form-textarea {\n" +
                "            min-height: 80px;\n" +
                "            resize: vertical;\n" +
                "        }\n" +
                "        \n" +
                "        .modal-actions {\n" +
                "            display: flex;\n" +
                "            justify-content: flex-end;\n" +
                "            gap: var(--spacing-sm);\n" +
                "            margin-top: var(--spacing-lg);\n" +
                "        }\n" +
                "        \n" +
                "        /* 加载状态 */\n" +
                "        .loading {\n" +
                "            text-align: center;\n" +
                "            padding: var(--spacing-xl);\n" +
                "            color: var(--text-secondary);\n" +
                "        }\n" +
                "        \n" +
                "        /* 响应式 */\n" +
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
                "            }\n" +
                "            \n" +
                "            .tasks-grid {\n" +
                "                grid-template-columns: 1fr;\n" +
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
                "                    <span>返回控制面板</span>\n" +
                "                </a>\n" +
                "                <h1 class=\"header-title\">自动化管理</h1>\n" +
                "            </div>\n" +
                "            <div class=\"header-controls\">\n" +
                "                <button class=\"btn btn-primary\" onclick=\"showCreateModal()\">\n" +
                "                    <span class=\"btn-icon\">\n" +
                "                        <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                            <path d=\"M12 5v14M5 12h14\"/>\n" +
                "                        </svg>\n" +
                "                    </span>\n" +
                "                    <span>新建自动化</span>\n" +
                "                </button>\n" +
                "            </div>\n" +
                "        </header>\n" +
                "        \n" +
                "        <!-- 任务列表 -->\n" +
                "        <div id=\"tasksContainer\" class=\"tasks-grid\">\n" +
                "            <div class=\"loading\">加载中...</div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    \n" +
                "    <!-- 新建任务模态框 -->\n" +
                "    <div id=\"createModal\" class=\"modal-overlay\">\n" +
                "        <div class=\"modal\">\n" +
                "            <div class=\"modal-header\">\n" +
                "                <h2 class=\"modal-title\">新建自动化</h2>\n" +
                "            </div>\n" +
                "            <div class=\"form-group\">\n" +
                "                <label class=\"form-label\">名称 *</label>\n" +
                "                <input type=\"text\" id=\"taskName\" class=\"form-input\" placeholder=\"输入自动化名称\">\n" +
                "            </div>\n" +
                "            <div class=\"form-group\">\n" +
                "                <label class=\"form-label\">描述</label>\n" +
                "                <textarea id=\"taskDescription\" class=\"form-input form-textarea\" placeholder=\"输入自动化描述（可选）\"></textarea>\n" +
                "            </div>\n" +
                "            <div class=\"modal-actions\">\n" +
                "                <button class=\"btn\" onclick=\"hideCreateModal()\">取消</button>\n" +
                "                <button class=\"btn btn-primary\" onclick=\"createTask()\">创建</button>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    \n" +
                "    <script>\n" +
                "        // 加载任务列表\n" +
                "        async function loadTasks() {\n" +
                "            const container = document.getElementById('tasksContainer');\n" +
                "            \n" +
                "            try {\n" +
                "                const response = await fetch('/api/automation');\n" +
                "                const data = await response.json();\n" +
                "                \n" +
                "                if (data.tasks.length === 0) {\n" +
                "                    container.innerHTML = `\n" +
                "                        <div class=\"empty-state\">\n" +
                "                            <svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\">\n" +
                "                                <rect x=\"3\" y=\"3\" width=\"18\" height=\"18\" rx=\"2\" ry=\"2\"/>\n" +
                "                                <path d=\"M12 8v8M8 12h8\"/>\n" +
                "                            </svg>\n" +
                "                            <h3>暂无自动化</h3>\n" +
                "                            <p>创建您的第一个自动化任务吧</p>\n" +
                "                            <button class=\"btn btn-primary\" onclick=\"showCreateModal()\">创建自动化</button>\n" +
                "                        </div>\n" +
                "                    `;\n" +
                "                    return;\n" +
                "                }\n" +
                "                \n" +
                "                container.innerHTML = data.tasks.map(task => `\n" +
                "                    <div class=\"task-card ${task.enabled ? 'enabled' : ''} ${task.running ? 'running' : ''}\" id=\"task-${task.id}\">\n" +
                "                        <div class=\"task-header\">\n" +
                "                            <div>\n" +
                "                                <h3 class=\"task-title\">${escapeHtml(task.name)}</h3>\n" +
                "                                <p class=\"task-description\">${escapeHtml(task.description || '无描述')}</p>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"task-status\">\n" +
                "                            <span class=\"status-badge ${task.enabled ? 'enabled' : 'disabled'}\">\n" +
                "                                ${task.enabled ? '已启用' : '已禁用'}\n" +
                "                            </span>\n" +
                "                            ${task.running ? '<span class=\"status-badge running\">运行中</span>' : ''}\n" +
                "                        </div>\n" +
                "                        <div class=\"task-actions\">\n" +
                "                            <button class=\"btn btn-primary\" onclick=\"editTask('${task.id}')\">编辑</button>\n" +
                "                            <button class=\"btn\" onclick=\"toggleTask('${task.id}')\">\n" +
                "                                ${task.enabled ? '禁用' : '启用'}\n" +
                "                            </button>\n" +
                "                            ${task.running ? `<button class=\"btn btn-danger\" onclick=\"stopTask('${task.id}')\">停止</button>` : `<button class=\"btn\" onclick=\"runTask('${task.id}')\">试运行</button>`}\n" +
                "                            <button class=\"btn btn-danger\" onclick=\"deleteTask('${task.id}')\">删除</button>\n" +
                "                        </div>\n" +
                "                        <div class=\"task-meta\">\n" +
                "                            创建于: ${new Date(task.createdAt).toLocaleString('zh-CN')}\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                `).join('');\n" +
                "            } catch (error) {\n" +
                "                container.innerHTML = `<div class=\"empty-state\"><p>加载失败: ${escapeHtml(error.message)}</p></div>`;\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        // 显示新建模态框\n" +
                "        function showCreateModal() {\n" +
                "            document.getElementById('createModal').classList.add('show');\n" +
                "            document.getElementById('taskName').focus();\n" +
                "        }\n" +
                "        \n" +
                "        // 隐藏新建模态框\n" +
                "        function hideCreateModal() {\n" +
                "            document.getElementById('createModal').classList.remove('show');\n" +
                "            document.getElementById('taskName').value = '';\n" +
                "            document.getElementById('taskDescription').value = '';\n" +
                "        }\n" +
                "        \n" +
                "        // 创建任务\n" +
                "        async function createTask() {\n" +
                "            const name = document.getElementById('taskName').value.trim();\n" +
                "            const description = document.getElementById('taskDescription').value.trim();\n" +
                "            \n" +
                "            if (!name) {\n" +
                "                alert('请输入任务名称');\n" +
                "                return;\n" +
                "            }\n" +
                "            \n" +
                "            try {\n" +
                "                const response = await fetch('/api/automation', {\n" +
                "                    method: 'POST',\n" +
                "                    headers: {\n" +
                "                        'Content-Type': 'application/json'\n" +
                "                    },\n" +
                "                    body: JSON.stringify({ name, description, blocks: [] })\n" +
                "                });\n" +
                "                \n" +
                "                if (response.ok) {\n" +
                "                    hideCreateModal();\n" +
                "                    loadTasks();\n" +
                "                } else {\n" +
                "                    alert('创建失败');\n" +
                "                }\n" +
                "            } catch (error) {\n" +
                "                alert('创建失败: ' + error.message);\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        // 编辑任务\n" +
                "        function editTask(id) {\n" +
                "            window.location.href = `/automation/editor?id=${id}`;\n" +
                "        }\n" +
                "        \n" +
                "        // 切换任务状态\n" +
                "        async function toggleTask(id) {\n" +
                "            try {\n" +
                "                await fetch(`/api/automation/${id}/toggle`, { method: 'POST' });\n" +
                "                loadTasks();\n" +
                "            } catch (error) {\n" +
                "                alert('操作失败: ' + error.message);\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        // 运行任务\n" +
                "        async function runTask(id) {\n" +
                "            try {\n" +
                "                const response = await fetch(`/api/automation/${id}/run`, { method: 'POST' });\n" +
                "                if (response.ok) {\n" +
                "                    loadTasks();\n" +
                "                } else {\n" +
                "                    alert('运行失败');\n" +
                "                }\n" +
                "            } catch (error) {\n" +
                "                alert('运行失败: ' + error.message);\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        // 停止任务\n" +
                "        async function stopTask(id) {\n" +
                "            try {\n" +
                "                await fetch(`/api/automation/${id}/stop`, { method: 'POST' });\n" +
                "                loadTasks();\n" +
                "            } catch (error) {\n" +
                "                alert('停止失败: ' + error.message);\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        // 删除任务\n" +
                "        async function deleteTask(id) {\n" +
                "            if (!confirm('确定要删除这个自动化吗？')) {\n" +
                "                return;\n" +
                "            }\n" +
                "            \n" +
                "            try {\n" +
                "                const response = await fetch(`/api/automation/${id}`, { method: 'DELETE' });\n" +
                "                if (response.ok) {\n" +
                "                    loadTasks();\n" +
                "                } else {\n" +
                "                    alert('删除失败');\n" +
                "                }\n" +
                "            } catch (error) {\n" +
                "                alert('删除失败: ' + error.message);\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        // HTML 转义\n" +
                "        function escapeHtml(text) {\n" +
                "            const div = document.createElement('div');\n" +
                "            div.textContent = text;\n" +
                "            return div.innerHTML;\n" +
                "        }\n" +
                "        \n" +
                "        // 页面加载时加载任务\n" +
                "        loadTasks();\n" +
                "        \n" +
                "        // 定时刷新\n" +
                "        setInterval(loadTasks, 3000);\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }
}
