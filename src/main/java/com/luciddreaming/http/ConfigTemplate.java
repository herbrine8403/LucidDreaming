package com.luciddreaming.http;

public class ConfigTemplate {
    public static String generateHTML() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Lucid Dreaming - Module Configuration</title>\n" +
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
                "            --danger-color: #f56565;\n" +
                "            --border-color: #e2e8f0;\n" +
                "            --shadow-sm: 0 2px 4px rgba(0, 0, 0, 0.1);\n" +
                "            --shadow-md: 0 4px 6px rgba(0, 0, 0, 0.1);\n" +
                "            --shadow-lg: 0 10px 25px rgba(0, 0, 0, 0.15);\n" +
                "        }\n" +
                "        \n" +
                "        [data-theme=\"dark\"] {\n" +
                "            --primary-gradient: linear-gradient(135deg, #1a1a2e 0%, #16213e 100%);\n" +
                "            --card-bg: rgba(30, 30, 50, 0.95);\n" +
                "            --text-primary: #e2e8f0;\n" +
                "            --text-secondary: #a0aec0;\n" +
                "            --accent-color: #667eea;\n" +
                "            --success-color: #48bb78;\n" +
                "            --danger-color: #f56565;\n" +
                "            --border-color: #4a5568;\n" +
                "            --shadow-sm: 0 2px 4px rgba(0, 0, 0, 0.3);\n" +
                "            --shadow-md: 0 4px 6px rgba(0, 0, 0, 0.3);\n" +
                "            --shadow-lg: 0 10px 25px rgba(0, 0, 0, 0.4);\n" +
                "        }\n" +
                "        \n" +
                "        body {\n" +
                "            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif;\n" +
                "            background: var(--primary-gradient);\n" +
                "            min-height: 100vh;\n" +
                "            padding: 20px;\n" +
                "            color: var(--text-primary);\n" +
                "            line-height: 1.6;\n" +
                "        }\n" +
                "        \n" +
                "        .container {\n" +
                "            max-width: 1200px;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "        \n" +
                "        .header {\n" +
                "            text-align: center;\n" +
                "            color: white;\n" +
                "            margin-bottom: 30px;\n" +
                "        }\n" +
                "        \n" +
                "        .header h1 {\n" +
                "            font-size: 2.5em;\n" +
                "            margin-bottom: 8px;\n" +
                "            text-shadow: 2px 2px 8px rgba(0,0,0,0.3);\n" +
                "        }\n" +
                "        \n" +
                "        .back-link {\n" +
                "            display: inline-block;\n" +
                "            margin-bottom: 20px;\n" +
                "            color: white;\n" +
                "            text-decoration: none;\n" +
                "            padding: 8px 16px;\n" +
                "            background: rgba(255, 255, 255, 0.2);\n" +
                "            border-radius: 8px;\n" +
                "            transition: all 0.3s ease;\n" +
                "        }\n" +
                "        \n" +
                "        .back-link:hover {\n" +
                "            background: rgba(255, 255, 255, 0.3);\n" +
                "        }\n" +
                "        \n" +
                "        .config-container {\n" +
                "            background: var(--card-bg);\n" +
                "            border-radius: 16px;\n" +
                "            padding: 24px;\n" +
                "            box-shadow: var(--shadow-lg);\n" +
                "            margin-top: 20px;\n" +
                "        }\n" +
                "        \n" +
                "        .config-selector {\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        \n" +
                "        .config-selector label {\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "            margin-right: 10px;\n" +
                "        }\n" +
                "        \n" +
                "        .config-selector select {\n" +
                "            padding: 10px 14px;\n" +
                "            border: 2px solid var(--border-color);\n" +
                "            border-radius: 8px;\n" +
                "            background: var(--card-bg);\n" +
                "            color: var(--text-primary);\n" +
                "            font-size: 14px;\n" +
                "            min-width: 200px;\n" +
                "        }\n" +
                "        \n" +
                "        .config-form {\n" +
                "            display: grid;\n" +
                "            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));\n" +
                "            gap: 16px;\n" +
                "        }\n" +
                "        \n" +
                "        .config-item {\n" +
                "            background: rgba(102, 126, 234, 0.05);\n" +
                "            border: 2px solid var(--border-color);\n" +
                "            border-radius: 12px;\n" +
                "            padding: 16px;\n" +
                "        }\n" +
                "        \n" +
                "        .config-item label {\n" +
                "            display: block;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-primary);\n" +
                "            margin-bottom: 8px;\n" +
                "        }\n" +
                "        \n" +
                "        .config-item input[type=\"number\"],\n" +
                "        .config-item input[type=\"text\"] {\n" +
                "            width: 100%;\n" +
                "            padding: 8px 12px;\n" +
                "            border: 2px solid var(--border-color);\n" +
                "            border-radius: 6px;\n" +
                "            background: var(--card-bg);\n" +
                "            color: var(--text-primary);\n" +
                "            font-size: 14px;\n" +
                "        }\n" +
                "        \n" +
                "        .config-item .checkbox-wrapper {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: 8px;\n" +
                "        }\n" +
                "        \n" +
                "        .config-item input[type=\"checkbox\"] {\n" +
                "            width: 20px;\n" +
                "            height: 20px;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "        \n" +
                "        .config-item .range-wrapper {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: 8px;\n" +
                "        }\n" +
                "        \n" +
                "        .config-item input[type=\"range\"] {\n" +
                "            flex: 1;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "        \n" +
                "        .config-item .range-value {\n" +
                "            min-width: 60px;\n" +
                "            text-align: right;\n" +
                "            font-weight: 600;\n" +
                "            color: var(--accent-color);\n" +
                "        }\n" +
                "        \n" +
                "        .config-actions {\n" +
                "            margin-top: 20px;\n" +
                "            display: flex;\n" +
                "            gap: 10px;\n" +
                "            justify-content: flex-end;\n" +
                "        }\n" +
                "        \n" +
                "        .config-actions button {\n" +
                "            padding: 10px 24px;\n" +
                "            border: none;\n" +
                "            border-radius: 8px;\n" +
                "            font-size: 14px;\n" +
                "            font-weight: 600;\n" +
                "            cursor: pointer;\n" +
                "            transition: all 0.3s ease;\n" +
                "        }\n" +
                "        \n" +
                "        .config-actions button.save {\n" +
                "            background: var(--success-color);\n" +
                "            color: white;\n" +
                "        }\n" +
                "        \n" +
                "        .config-actions button.reset {\n" +
                "            background: var(--text-secondary);\n" +
                "            color: white;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <a href=\"/\" class=\"back-link\">← Back to Dashboard</a>\n" +
                "            <h1>⚙️ Module Configuration</h1>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"config-container\">\n" +
                "            <div class=\"config-selector\">\n" +
                "                <label for=\"moduleSelect\">Select Module:</label>\n" +
                "                <select id=\"moduleSelect\" onchange=\"loadModuleConfig()\">\n" +
                "                    <option value=\"\">-- Select a module --</option>\n" +
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
                "                <p>Select a module to view and edit its configuration.</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    \n" +
                "    <script>\n" +
                "        function loadModuleConfig() {\n" +
                "            const moduleSelect = document.getElementById('moduleSelect');\n" +
                "            const moduleName = moduleSelect.value;\n" +
                "            const configForm = document.getElementById('configForm');\n" +
                "            \n" +
                "            if (!moduleName) {\n" +
                "                configForm.innerHTML = '<p>Select a module to view and edit its configuration.</p>';\n" +
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
                "                    configForm.innerHTML = '<p>Error loading configuration: ' + error.message + '</p>';\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        function renderConfigForm(moduleName, config) {\n" +
                "            const configForm = document.getElementById('configForm');\n" +
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
                "                    html += '<input type=\"checkbox\" id=\"config_' + key + '\" ' + (value ? 'checked' : '') + '>';\n" +
                "                    html += '<span>' + (value ? 'Enabled' : 'Disabled') + '</span>';\n" +
                "                    html += '</div>';\n" +
                "                } else if (typeof value === 'number') {\n" +
                "                    if (key.includes('Range') || key.includes('Speed') || key.includes('Amount') || key.includes('Chance') || key.includes('Opacity') || key.includes('Cost') || key.includes('Distance') || key.includes('Time') || key.includes('Interval')) {\n" +
                "                        html += '<div class=\"range-wrapper\">';\n" +
                "                        html += '<input type=\"range\" id=\"config_' + key + '\" value=\"' + value + '\" min=\"0\" max=\"100\" step=\"0.1\" oninput=\"updateRangeValue(this)\">';\n" +
                "                        html += '<span class=\"range-value\" id=\"range_' + key + '\">' + value + '</span>';\n" +
                "                        html += '</div>';\n" +
                "                    } else {\n" +
                "                        html += '<input type=\"number\" id=\"config_' + key + '\" value=\"' + value + '\" step=\"0.1\">';\n" +
                "                    }\n" +
                "                } else if (typeof value === 'string') {\n" +
                "                    html += '<input type=\"text\" id=\"config_' + key + '\" value=\"' + value + '\">';\n" +
                "                }\n" +
                "                \n" +
                "                html += '</div>';\n" +
                "            }\n" +
                "            \n" +
                "            html += '<div class=\"config-actions\">';\n" +
                "            html += '<button class=\"save\" onclick=\"saveModuleConfig(\\'' + moduleName + '\\')\">Save Configuration</button>';\n" +
                "            html += '<button class=\"reset\" onclick=\"loadModuleConfig()\">Reset</button>';\n" +
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
                "        function formatConfigKey(key) {\n" +
                "            return key.replace(/([A-Z])/g, ' $1').replace(/^./, str => str.toUpperCase()).trim();\n" +
                "        }\n" +
                "        \n" +
                "        function saveModuleConfig(moduleName) {\n" +
                "            const configForm = document.getElementById('configForm');\n" +
                "            const inputs = configForm.querySelectorAll('input, select');\n" +
                "            const configData = {};\n" +
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
                "                    alert('Configuration saved successfully!');\n" +
                "                } else {\n" +
                "                    alert('Error saving configuration: ' + data.error);\n" +
                "                }\n" +
                "            })\n" +
                "            .catch(error => {\n" +
                "                console.error('Error saving module config:', error);\n" +
                "                alert('Error saving configuration: ' + error.message);\n" +
                "            });\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }
}
