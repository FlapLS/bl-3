package com.example.bl_lab1.utils;

import java.util.Map;

public interface VersionJmsProducer {
    void sendVersion(Map<String,Object> message);
}
