package com.luciddreaming.automation;

import java.util.List;
import java.util.Map;

public class AutomationTask {
    private String id;
    private String name;
    private String description;
    private String type;
    private boolean enabled;
    private String trigger;
    private List<AutomationInstruction> instructions;
    
    public AutomationTask() {
    }
    
    public AutomationTask(String id, String name, String description, String type, 
                         boolean enabled, String trigger, List<AutomationInstruction> instructions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.enabled = enabled;
        this.trigger = trigger;
        this.instructions = instructions;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public String getTrigger() {
        return trigger;
    }
    
    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }
    
    public List<AutomationInstruction> getInstructions() {
        return instructions;
    }
    
    public void setInstructions(List<AutomationInstruction> instructions) {
        this.instructions = instructions;
    }
}