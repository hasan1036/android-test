package com.example.ecg_monitoring_system1.media;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
