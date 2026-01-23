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
                "            --danger-color: #f56565;\n" +
                "            --border-color: #e2e8f0;\n" +
                "            --shadow-sm: 0 2px 4px rgba(0, 0, 0, 0.1);\n" +
                "            --shadow-md: 0 4px 6px rgba(0, 0, 0, 0.1);\n" +
                "            --shadow-lg: 0 10px 25px rgba(0, 0, 0, 0.15);\n" +
                "            --scoreboard-bg: #808080;\n" +
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
                "            --scoreboard-bg: #808080;\n" +
                "        }\n" +
                "        \n" +
                "        body {\n" +
                "            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';\n" +
                "            background: var(--primary-gradient);\n" +
                "            min-height: 100vh;\n" +
                "            padding: 20px;\n" +
                "            color: var(--text-primary);\n" +
                "            line-height: 1.6;\n" +
                "            transition: background 0.3s ease, color 0.3s ease;\n" +
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
                "        .header-controls {\n" +
                "            position: absolute;\n" +
                "            top: 0;\n" +
                "            right: 0;\n" +
                "            display: flex;\n" +
                "            gap: 10px;\n" +
                "            align-items: center;\n" +
                "        }\n" +
                "        \n" +
                "        .control-btn {\n" +
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
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: 6px;\n" +
                "        }\n" +
                "        \n" +
                "        .control-btn:hover {\n" +
                "            background: rgba(255, 255, 255, 0.3);\n" +
                "            transform: translateY(-2px);\n" +
                "        }\n" +
                "        \n" +
                "        .control-btn.active {\n" +
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
                "            background: var(--scoreboard-bg);\n" +
                "            border-radius: 12px;\n" +
                "            padding: 16px;\n" +
                "        }\n" +
                "        \n" +
                "        .scoreboard-list li {\n" +
                "            padding: 10px 12px;\n" +
                "            border-bottom: 1px solid rgba(255, 255, 255, 0.2);\n" +
                "            font-family: 'Courier New', 'Consolas', monospace;\n" +
                "            font-size: 0.9em;\n" +
                "            line-height: 1.5;\n" +
                "            transition: background-color 0.2s ease;\n" +
                "            color: white;\n" +
                "            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);\n" +
                "        }\n" +
                "        \n" +
                "        .scoreboard-list li:last-child {\n" +
                "            border-bottom: none;\n" +
                "        }\n" +
                "        \n" +
                "        .scoreboard-list li:hover {\n" +
                "            background-color: rgba(102, 126, 234, 0.3);\n" +
                "            border-radius: 6px;\n" +
                "        }\n" +
                "        \n" +
                "        .autowalk-container {\n" +
                "            background: var(--container-bg);\n" +
                "            border-radius: 16px;\n" +
                "            padding: 24px;\n" +
                "            box-shadow: var(--shadow-lg);\n" +
                "            margin-top: 20px;\n" +
                "            animation: fadeInUp 0.6s ease-out;\n" +
                "            backdrop-filter: blur(10px);\n" +
                "        }\n" +
                "        \n" +
                "        .autowalk-container h2 {\n" +
                "            color: var(--accent-color);\n" +
                "            margin-bottom: 18px;\n" +
                "            border-bottom: 2px solid var(--accent-color);\n" +
                "            padding-bottom: 10px;\n" +
                "            font-size: 1.35em;\n" +
                "            font-weight: 600;\n" +
                "        }\n" +
                "        \n" +
                "        .autowalk-controls {\n" +
                "            display: flex;\n" +
                "            flex-direction: column;\n" +
                "            gap: 16px;\n" +
                "        }\n" +
                "        \n" +
                "        .autowalk-input-group {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: 10px;\n" +
                "            flex-wrap: wrap;\n" +
                "        }\n" +
                "        \n" +
                "        .autowalk-input-group label {\n" +
                "            font-weight: 600;\n" +
                "            color: var(--text-color);\n" +
                "            min-width: 20px;\n" +
                "        }\n" +
                "        \n" +
                "        .autowalk-input-group input {\n" +
                "            padding: 10px 14px;\n" +
                "            border: 2px solid var(--border-color);\n" +
                "            border-radius: 8px;\n" +
                "            background: var(--input-bg);\n" +
                "            color: var(--text-color);\n" +
                "            font-size: 14px;\n" +
                "            width: 80px;\n" +
                "            transition: all 0.3s ease;\n" +
                "        }\n" +
                "        \n" +
                "        .autowalk-input-group input:focus {\n" +
                "            outline: none;\n" +
                "            border-color: var(--accent-color);\n" +
                "            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.2);\n" +
                "        }\n" +
                "        \n" +
                "        .autowalk-input-group button {\n" +
                "            padding: 10px 20px;\n" +
                "            border: none;\n" +
                "            border-radius: 8px;\n" +
                "            background: var(--accent-color);\n" +
                "            color: white;\n" +
                "            font-size: 14px;\n" +
                "            font-weight: 600;\n" +
                "            cursor: pointer;\n" +
                "            transition: all 0.3s ease;\n" +
                "            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);\n" +
                "        }\n" +
                "        \n" +
                "        .autowalk-input-group button:hover {\n" +
                "            background: #5b7cfa;\n" +
                "            transform: translateY(-2px);\n" +
                "            box-shadow: 0 6px 8px rgba(0, 0, 0, 0.3);\n" +
                "        }\n" +
                "        \n" +
                "        .autowalk-input-group button:active {\n" +
                "            transform: translateY(0);\n" +
                "        }\n" +
                "        \n" +
                "        .autowalk-status {\n" +
                "            background: var(--info-bg);\n" +
                "            border-radius: 8px;\n" +
                "            padding: 14px;\n" +
                "            border-left: 4px solid var(--accent-color);\n" +
                "        }\n" +
                "        \n" +
                "                .autowalk-status p {\n" +
                                "            margin: 6px 0;\n" +
                                "            color: var(--text-color);\n" +
                                "            font-size: 14px;\n" +
                                "        }\n" +
                                "        \n" +
                                "        .autowalk-status span {\n" +
                                "            font-weight: 600;\n" +
                                "        }\n" +
                                "        \n" +
                                "        .pathfinding-status {\n" +
                                "            margin-top: 10px;\n" +
                                "            padding: 10px;\n" +
                                "            border-radius: 6px;\n" +
                                "            font-size: 13px;\n" +
                                "        }\n" +
                                "        \n" +
                                "        .pathfinding-status.calculating {\n" +
                                "            background: rgba(255, 193, 7, 0.2);\n" +
                                "            border-left: 4px solid #FFC107;\n" +
                                "        }\n" +
                                "        \n" +
                                "        .pathfinding-status.active {\n" +
                                "            background: rgba(76, 175, 80, 0.2);\n" +
                                "            border-left: 4px solid #4CAF50;\n" +
                                "        }\n" +
                                "        \n" +
                                "        .pathfinding-status.failed {\n" +
                                "            background: rgba(244, 67, 54, 0.2);\n" +
                                "            border-left: 4px solid #F44336;\n" +
                                "        }\n" +
                                "        \n" +
                                "        /* Minecraft color codes */\n" +
                "        .color-0 { color: #000000; text-shadow: none; }\n" +
                "        .color-1 { color: #0000AA; text-shadow: none; }\n" +
                "        .color-2 { color: #00AA00; text-shadow: none; }\n" +
                "        .color-3 { color: #00AAAA; text-shadow: none; }\n" +
                "        .color-4 { color: #AA0000; text-shadow: none; }\n" +
                "        .color-5 { color: #AA00AA; text-shadow: none; }\n" +
                "        .color-6 { color: #FFAA00; text-shadow: none; }\n" +
                "        .color-7 { color: #AAAAAA; text-shadow: none; }\n" +
                "        .color-8 { color: #555555; text-shadow: none; }\n" +
                "        .color-9 { color: #5555FF; text-shadow: none; }\n" +
                "        .color-a { color: #55FF55; text-shadow: none; }\n" +
                "        .color-b { color: #55FFFF; text-shadow: none; }\n" +
                "        .color-c { color: #FF5555; text-shadow: none; }\n" +
                "        .color-d { color: #FF55FF; text-shadow: none; }\n" +
                "        .color-e { color: #FFFF55; text-shadow: none; }\n" +
                "        .color-f { color: #FFFFFF; text-shadow: none; }\n" +
                "        .color-l { font-weight: bold; }\n" +
                "        .color-m { text-decoration: line-through; }\n" +
                "        .color-n { text-decoration: underline; }\n" +
                "        .color-o { font-style: italic; }\n" +
                "        .color-r { color: inherit; text-decoration: none; font-style: normal; font-weight: normal; text-shadow: none; }\n" +
                "        \n" +
                "        .screenshot-container {\n" +
                "            background: var(--card-bg);\n" +
                "            border-radius: 16px;\n" +
                "            padding: 24px;\n" +
                "            box-shadow: var(--shadow-lg);\n" +
                "            margin-top: 20px;\n" +
                "            animation: fadeInUp 0.6s ease-out;\n" +
                "            backdrop-filter: blur(10px);\n" +
                "        }\n" +
                "        \n" +
                "        .screenshot-container h2 {\n" +
                "            color: var(--accent-color);\n" +
                "            margin-bottom: 18px;\n" +
                "            border-bottom: 2px solid var(--accent-color);\n" +
                "            padding-bottom: 10px;\n" +
                "            font-size: 1.35em;\n" +
                "            font-weight: 600;\n" +
                "        }\n" +
                "        \n" +
                "        .screenshot-btn {\n" +
                "            background: var(--accent-color);\n" +
                "            color: white;\n" +
                "            border: none;\n" +
                "            padding: 12px 24px;\n" +
                "            border-radius: 8px;\n" +
                "            cursor: pointer;\n" +
                "            font-size: 1em;\n" +
                "            font-weight: 600;\n" +
                "            transition: all 0.3s ease;\n" +
                "            display: inline-flex;\n" +
                "            align-items: center;\n" +
                "            gap: 8px;\n" +
                "        }\n" +
                "        \n" +
                "        .screenshot-btn:hover {\n" +
                "            transform: translateY(-2px);\n" +
                "            box-shadow: 0 4px 12px rgba(102, 126, 234, 0.4);\n" +
                "        }\n" +
                "        \n" +
                "        .screenshot-btn:active {\n" +
                "            transform: translateY(0);\n" +
                "        }\n" +
                "        \n" +
                "        .screenshot-btn:disabled {\n" +
                "            background: var(--text-secondary);\n" +
                "            cursor: not-allowed;\n" +
                "            transform: none;\n" +
                "        }\n" +
                "        \n" +
                "        .screenshot-preview {\n" +
                "            margin-top: 20px;\n" +
                "            border-radius: 12px;\n" +
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
                "        /* Module Control Panel */\n" +
                "        .modules-container {\n" +
                "            background: var(--card-bg);\n" +
                "            border-radius: 16px;\n" +
                "            padding: 24px;\n" +
                "            box-shadow: var(--shadow-lg);\n" +
                "            margin-top: 20px;\n" +
                "            animation: fadeInUp 0.6s ease-out;\n" +
                "            backdrop-filter: blur(10px);\n" +
                "        }\n" +
                "        \n" +
                "        .modules-container h2 {\n" +
                "            color: var(--accent-color);\n" +
                "            margin-bottom: 18px;\n" +
                "            border-bottom: 2px solid var(--accent-color);\n" +
                "            padding-bottom: 10px;\n" +
                "            font-size: 1.35em;\n" +
                "            font-weight: 600;\n" +
                "        }\n" +
                "        \n" +
                "        .module-grid {\n" +
                "            display: grid;\n" +
                "            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));\n" +
                "            gap: 16px;\n" +
                "        }\n" +
                "        \n" +
                "        .module-card {\n" +
                "            background: rgba(102, 126, 234, 0.05);\n" +
                "            border: 2px solid var(--border-color);\n" +
                "            border-radius: 12px;\n" +
                "            padding: 16px;\n" +
                "            transition: all 0.3s ease;\n" +
                "        }\n" +
                "        \n" +
                "        .module-card:hover {\n" +
                "            border-color: var(--accent-color);\n" +
                "            transform: translateY(-2px);\n" +
                "        }\n" +
                "        \n" +
                "        .module-card.enabled {\n" +
                "            border-color: var(--success-color);\n" +
                "            background: rgba(72, 187, 120, 0.1);\n" +
                "        }\n" +
                "        \n" +
                "        .module-header {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "            align-items: center;\n" +
                "            margin-bottom: 12px;\n" +
                "        }\n" +
                "        \n" +
                "        .module-name {\n" +
                "            font-weight: 600;\n" +
                "            font-size: 1.1em;\n" +
                "            color: var(--text-primary);\n" +
                "        }\n" +
                "        \n" +
                "        .module-toggle {\n" +
                "            position: relative;\n" +
                "            width: 50px;\n" +
                "            height: 26px;\n" +
                "            background: var(--text-secondary);\n" +
                "            border-radius: 13px;\n" +
                "            cursor: pointer;\n" +
                "            transition: background 0.3s ease;\n" +
                "        }\n" +
                "        \n" +
                "        .module-toggle.enabled {\n" +
                "            background: var(--success-color);\n" +
                "        }\n" +
                "        \n" +
                "        .module-toggle::after {\n" +
                "            content: '';\n" +
                "            position: absolute;\n" +
                "            top: 3px;\n" +
                "            left: 3px;\n" +
                "            width: 20px;\n" +
                "            height: 20px;\n" +
                "            background: white;\n" +
                "            border-radius: 50%;\n" +
                "            transition: transform 0.3s ease;\n" +
                "        }\n" +
                "        \n" +
                "        .module-toggle.enabled::after {\n" +
                "            transform: translateX(24px);\n" +
                "        }\n" +
                "        \n" +
                "        .module-description {\n" +
                "            font-size: 0.9em;\n" +
                "            color: var(--text-secondary);\n" +
                "            margin-bottom: 8px;\n" +
                "        }\n" +
                "        \n" +
                "        .module-keybind {\n" +
                "            font-size: 0.85em;\n" +
                "            color: var(--accent-color);\n" +
                "            font-weight: 500;\n" +
                "        }\n" +
                "        \n" +
                "        .module-category {\n" +
                "            display: inline-block;\n" +
                "            padding: 4px 8px;\n" +
                "            background: var(--accent-color);\n" +
                "            color: white;\n" +
                "            border-radius: 4px;\n" +
                "            font-size: 0.8em;\n" +
                "            font-weight: 500;\n" +
                "            margin-top: 8px;\n" +
                "        }\n" +
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
                "            .header-controls {\n" +
                "                position: static;\n" +
                "                justify-content: center;\n" +
                "                margin-top: 15px;\n" +
                "                flex-wrap: wrap;\n" +
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
                "            .module-grid {\n" +
                "                grid-template-columns: 1fr;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <div class=\"header-controls\">\n" +
                "                <button class=\"control-btn\" onclick=\"setLanguage('en')\" id=\"langEnBtn\">English</button>\n" +
                "                <button class=\"control-btn\" onclick=\"setLanguage('zh')\" id=\"langZhBtn\">中文</button>\n" +
                "                <button class=\"control-btn\" onclick=\"toggleTheme()\" id=\"themeBtn\">🌙 Dark</button>\n" +
                "                <button class=\"control-btn\" onclick=\"takeScreenshot()\" id=\"screenshotBtn\">📸 Screenshot</button>\n" +
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
                "        <div class=\"screenshot-container\">\n" +
                "            <h2>📸 <span id=\"screenshot-title\">Screenshot</span></h2>\n" +
                "            <button class=\"screenshot-btn\" onclick=\"takeScreenshot()\" id=\"screenshotBtn2\">\n" +
                "                <span>📸</span> Take Screenshot\n" +
                "            </button>\n" +
                "            <div class=\"screenshot-preview\" id=\"screenshotPreview\">\n" +
                "                <img id=\"screenshotImage\" alt=\"Game Screenshot\">\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"modules-container\">\n" +
                "            <h2>⚙️ <span id=\"modules-title\">Module Control</span> <a href=\"/config\" style=\"font-size: 0.6em; margin-left: 10px; color: var(--accent-color);\">[Edit Config]</a></h2>\n" +
                "            <div class=\"module-grid\" id=\"moduleGrid\">\n" +
                "                <div id=\"loading-modules\">Loading modules...</div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"autowalk-container\">\n" +
                "            <h2>🚶 <span id=\"autowalk-title\">Auto Walk</span></h2>\n" +
                "            <div class=\"autowalk-controls\">\n" +
                "                <div class=\"autowalk-input-group\">\n" +
                "                    <label for=\"targetX\">X:</label>\n" +
                "                    <input type=\"number\" id=\"targetX\" placeholder=\"X\" />\n" +
                "                    <label for=\"targetY\">Y:</label>\n" +
                "                    <input type=\"number\" id=\"targetY\" placeholder=\"Y\" />\n" +
                "                    <label for=\"targetZ\">Z:</label>\n" +
                "                    <input type=\"number\" id=\"targetZ\" placeholder=\"Z\" />\n" +
                "                    <button onclick=\"setAutoWalkTarget()\">Set Target</button>\n" +
                "                    <button onclick=\"clearAutoWalkTarget()\">Clear</button>\n" +
                "                </div>\n" +
                "                <div class=\"autowalk-status\" id=\"autowalkStatus\">\n" +
                "                    <p>Status: <span id=\"autowalkStatusText\">Not active</span></p>\n" +
                "                    <p id=\"autowalkTargetInfo\"></p>\n" +
                "                    <div id=\"pathfindingStatus\" class=\"pathfinding-status\"></div>\n" +
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
                "        // Theme management\n" +
                "        let currentTheme = localStorage.getItem('theme') || 'light';\n" +
                "        \n" +
                "        function toggleTheme() {\n" +
                "            currentTheme = currentTheme === 'light' ? 'dark' : 'light';\n" +
                "            localStorage.setItem('theme', currentTheme);\n" +
                "            applyTheme();\n" +
                "        }\n" +
                "        \n" +
                "        function applyTheme() {\n" +
                "            document.documentElement.setAttribute('data-theme', currentTheme);\n" +
                "            const themeBtn = document.getElementById('themeBtn');\n" +
                "            if (themeBtn) {\n" +
                "                themeBtn.textContent = currentTheme === 'light' ? '🌙 Dark' : '☀️ Light';\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        // Screenshot functionality\n" +
                "        function takeScreenshot() {\n" +
                "            const btn = document.getElementById('screenshotBtn');\n" +
                "            const btn2 = document.getElementById('screenshotBtn2');\n" +
                "            const preview = document.getElementById('screenshotPreview');\n" +
                "            const img = document.getElementById('screenshotImage');\n" +
                "            \n" +
                "            if (btn) btn.disabled = true;\n" +
                "            if (btn2) btn2.disabled = true;\n" +
                "            \n" +
                "            fetch('/api/screenshot')\n" +
                "                .then(response => {\n" +
                "                    if (!response.ok) {\n" +
                "                        throw new Error('Screenshot failed');\n" +
                "                    }\n" +
                "                    return response.blob();\n" +
                "                })\n" +
                "                .then(blob => {\n" +
                "                    const url = URL.createObjectURL(blob);\n" +
                "                    img.src = url;\n" +
                "                    preview.style.display = 'block';\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('Screenshot error:', error);\n" +
                "                    alert('Failed to take screenshot: ' + error.message);\n" +
                "                })\n" +
                "                .finally(() => {\n" +
                "                    if (btn) btn.disabled = false;\n" +
                "                    if (btn2) btn2.disabled = false;\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        // Module control\n" +
                "        function loadModules() {\n" +
                "            fetch('/api/modules')\n" +
                "                .then(response => response.json())\n" +
                "                .then(data => {\n" +
                "                    renderModules(data.modules);\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('Error loading modules:', error);\n" +
                "                    document.getElementById('moduleGrid').innerHTML = '<div style=\"color: var(--danger-color)\">Failed to load modules</div>';\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        function renderModules(modules) {\n" +
                "            const grid = document.getElementById('moduleGrid');\n" +
                "            grid.innerHTML = '';\n" +
                "            \n" +
                "            modules.forEach(module => {\n" +
                "                const card = document.createElement('div');\n" +
                "                card.className = 'module-card' + (module.enabled ? ' enabled' : '');\n" +
                "                const localizedName = module.localizedName || module.name;\n" +
                "                const localizedDescription = module.localizedDescription || module.description;\n" +
                "                const localizedCategory = module.localizedCategory || module.category;\n" +
                "                card.innerHTML = `\n" +
                "                    <div class=\"module-header\">\n" +
                "                        <span class=\"module-name\">${escapeHtml(localizedName)}</span>\n" +
                "                        <div class=\"module-toggle ${module.enabled ? 'enabled' : ''}\" onclick=\"toggleModule('${escapeHtml(module.name)}')\"></div>\n" +
                "                    </div>\n" +
                "                    <div class=\"module-description\">${escapeHtml(localizedDescription)}</div>\n" +
                "                    <div class=\"module-keybind\">Keybind: ${escapeHtml(module.keybind)}</div>\n" +
                "                    <div class=\"module-category\">${escapeHtml(localizedCategory)}</div>\n" +
                "                `;\n" +
                "                grid.appendChild(card);\n" +
                "            });\n" +
                "        }\n" +
                "        \n" +
                "        function toggleModule(moduleName) {\n" +
                "            fetch('/api/modules/' + encodeURIComponent(moduleName), {\n" +
                "                method: 'POST',\n" +
                "                headers: {\n" +
                "                    'Content-Type': 'application/json'\n" +
                "                },\n" +
                "                body: JSON.stringify({ action: 'toggle' })\n" +
                "            })\n" +
                "            .then(response => response.json())\n" +
                "            .then(data => {\n" +
                "                loadModules(); // Refresh module list\n" +
                "            })\n" +
                "            .catch(error => {\n" +
                "                console.error('Error toggling module:', error);\n" +
                "                alert('Failed to toggle module: ' + error.message);\n" +
                "            });\n" +
                "        }\n" +
                "        \n" +
                "        function escapeHtml(text) {\n" +
                "            const div = document.createElement('div');\n" +
                "            div.textContent = text;\n" +
                "            return div.innerHTML;\n" +
                "        }\n" +
                "        \n" +
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
                "                screenshot: 'Screenshot',\n" +
                "                modules: 'Module Control',\n" +
                "                scoreboard: 'Scoreboard',\n" +
                "                loadingScoreboard: 'Loading scoreboard...',\n" +
                "                loadingModules: 'Loading modules...',\n" +
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
                "                screenshot: '截图',\n" +
                "                modules: '模块控制',\n" +
                "                scoreboard: '计分板',\n" +
                "                loadingScoreboard: '加载计分板中...',\n" +
                "                loadingModules: '加载模块中...',\n" +
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
                "            // Update language button states\n" +
                "            document.getElementById('langEnBtn').classList.remove('active');\n" +
                "            document.getElementById('langZhBtn').classList.remove('active');\n" +
                "            document.getElementById('lang' + lang.charAt(0).toUpperCase() + lang.slice(1) + 'Btn').classList.add('active');\n" +
                "            \n" +
                "            updateTranslations();\n" +
                "            updateInfo();\n" +
                "        }\n" +
                "        \n" +
                "        function updateTranslations() {\n" +
                "            const t = translations[currentLang];\n" +
                "            document.getElementById('title').textContent = t.title;\n" +
                "            document.getElementById('subtitle').textContent = t.subtitle;\n" +
                "            document.getElementById('auto-refresh').innerHTML = t.autoRefresh + '<span class=\"refresh-indicator\"></span>';\n" +
                "            document.getElementById('player-info-title').textContent = t.playerInfo;\n" +
                "            document.getElementById('game-info-title').textContent = t.gameInfo;\n" +
                "            document.getElementById('server-info-title').textContent = t.serverInfo;\n" +
                "            document.getElementById('system-info-title').textContent = t.systemInfo;\n" +
                "            document.getElementById('screenshot-title').textContent = t.screenshot;\n" +
                "            document.getElementById('modules-title').textContent = t.modules;\n" +
                "            document.getElementById('scoreboard-title').textContent = t.scoreboard;\n" +
                "            document.getElementById('loading-scoreboard').textContent = t.loadingScoreboard;\n" +
                "            document.getElementById('loading-modules').textContent = t.loadingModules;\n" +
                "            document.getElementById('footer').textContent = t.footer;\n" +
                "            \n" +
                "            for (const [key, value] of Object.entries(t.labels)) {\n" +
                "                const elementId = 'label-' + key.replace(/[A-Z]/g, m => '-' + m.toLowerCase());\n" +
                "                const element = document.getElementById(elementId);\n" +
                "                if (element) {\n" +
                "                    element.textContent = value;\n" +
                "                }\n" +
                "            }\n" +
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
                "                    document.getElementById('playerName').textContent = data.player.name;\n" +
                "                    document.getElementById('playerHealth').textContent = data.player.health + '/' + data.player.maxHealth;\n" +
                "                    document.getElementById('playerHunger').textContent = data.player.hunger + '/20';\n" +
                "                    document.getElementById('playerPosition').textContent = data.player.position;\n" +
                "                    \n" +
                "                    const dimensionName = t.dimensionNames[data.player.dimension] || data.player.dimension;\n" +
                "                    document.getElementById('playerDimension').textContent = dimensionName;\n" +
                "                    \n" +
                "                    document.getElementById('playerExperience').textContent = (currentLang === 'zh' ? '等级 ' : 'Level ') + data.player.experienceLevel;\n" +
                "                    \n" +
                "                    document.getElementById('mcVersion').textContent = data.game.minecraftVersion;\n" +
                "                    document.getElementById('forgeVersion').textContent = data.game.forgeVersion;\n" +
                "                    document.getElementById('fps').textContent = data.game.fps;\n" +
                "                    document.getElementById('uptime').textContent = data.game.uptime;\n" +
                "                    document.getElementById('currentTime').textContent = data.game.currentTime;\n" +
                "                    \n" +
                "                    document.getElementById('serverType').textContent = data.server.type;\n" +
                "                    document.getElementById('serverAddress').textContent = data.server.address;\n" +
                "                    document.getElementById('serverName').textContent = data.server.name;\n" +
                "                    \n" +
                "                    document.getElementById('osName').textContent = data.system.osName + ' ' + data.system.osVersion;\n" +
                "                    document.getElementById('javaVersion').textContent = data.system.javaVersion;\n" +
                "                    \n" +
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
                "        // AutoWalk functions\n" +
                "        function setAutoWalkTarget() {\n" +
                "            const x = document.getElementById('targetX').value;\n" +
                "            const y = document.getElementById('targetY').value;\n" +
                "            const z = document.getElementById('targetZ').value;\n" +
                "            \n" +
                "            if (!x || !y || !z) {\n" +
                "                alert('Please enter X, Y, and Z coordinates');\n" +
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
                "                    \n" +
                "                    if (data.enabled && data.hasTarget) {\n" +
                "                        statusText.textContent = data.pathfinding ? 'Calculating path...' : 'Moving';\n" +
                "                        statusText.style.color = data.pathfinding ? '#FFC107' : '#4CAF50';\n" +
                "                        if (data.target) {\n" +
                "                            targetInfo.textContent = 'Target: X=' + data.target.x + ', Y=' + data.target.y + ', Z=' + data.target.z;\n" +
                "                        }\n" +
                "                        \n" +
                "                        // Update pathfinding status\n" +
                "                        if (pathfindingStatus) {\n" +
                "                            if (data.pathfinding) {\n" +
                "                                pathfindingStatus.className = 'pathfinding-status calculating';\n" +
                "                                pathfindingStatus.textContent = '⏳ Calculating optimal path...';\n" +
                "                            } else if (data.hasPath) {\n" +
                "                                pathfindingStatus.className = 'pathfinding-status active';\n" +
                "                                pathfindingStatus.textContent = '✅ Path found (' + data.pathLength + ' nodes)';\n" +
                "                            } else {\n" +
                "                                pathfindingStatus.className = 'pathfinding-status failed';\n" +
                "                                pathfindingStatus.textContent = '❌ Path not found';\n" +
                "                            }\n" +
                "                        }\n" +
                "                    } else {\n" +
                "                        statusText.textContent = 'Not active';\n" +
                "                        statusText.style.color = '#f44336';\n" +
                "                        targetInfo.textContent = '';\n" +
                "                        if (pathfindingStatus) {\n" +
                "                            pathfindingStatus.className = 'pathfinding-status';\n" +
                "                            pathfindingStatus.textContent = '';\n" +
                "                        }\n" +
                "                    }\n" +
                "                })\n" +
                "                .catch(error => {\n" +
                "                    console.error('Error fetching AutoWalk status:', error);\n" +
                "                });\n" +
                "        }\n" +
                "        \n" +
                "        // Initialize\n" +
                "        document.addEventListener('DOMContentLoaded', function() {\n" +
                "            applyTheme();\n" +
                "            \n" +
                "            // Initialize language buttons\n" +
                "            const langBtn = document.getElementById('lang' + currentLang.charAt(0).toUpperCase() + currentLang.slice(1) + 'Btn');\n" +
                "            if (langBtn) {\n" +
                "                langBtn.classList.add('active');\n" +
                "            }\n" +
                "            \n" +
                "            updateTranslations();\n" +
                "            updateInfo();\n" +
                "            loadModules();\n" +
                "            updateAutoWalkStatus();\n" +
                "            setInterval(updateInfo, 1000);\n" +
                "            setInterval(loadModules, 2000); // Refresh modules every 2 seconds\n" +
                "            setInterval(updateAutoWalkStatus, 1000); // Refresh AutoWalk status every 1 second\n" +
                "        });\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }
}