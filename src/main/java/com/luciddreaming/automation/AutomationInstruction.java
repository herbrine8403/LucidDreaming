package com.luciddreaming.automation;

import java.util.Map;

public class AutomationInstruction {
    private String type;
    private Map<String, Object> params;
    private int delay; // 延迟执行时间（毫秒）
    
    public AutomationInstruction() {
    }
    
    public AutomationInstruction(String type, Map<String, Object> params, int delay) {
        this.type = type;
        this.params = params;
        this.delay = delay;
    }
    
    // Getters and Setters
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Map<String, Object> getParams() {
        return params;
    }
    
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    
    public int getDelay() {
        return delay;
    }
    
    public void setDelay(int delay) {
        this.delay = delay;
    }
}