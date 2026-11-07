package com.barnacle.foregone_shack.entity.utils;

import com.barnacle.foregone_shack.entity.animation.BodyChain;

public interface BodyChainMob {

    BodyChain getBodyChain();

    float getRenderYaw(float partialTicks);

    float getSegmentYawOffset(int index, float partialTicks);

    float getSegmentPitchOffset(int index, float partialTicks);
}