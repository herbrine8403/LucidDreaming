package com.luciddreaming.automation;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Block 类的 JSON 反序列化器
 */
public class BlockDeserializer implements JsonDeserializer<Block> {
    @Override
    public Block deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) context.deserialize(jsonObject, Map.class);

        return Block.fromMap(map);
    }
}