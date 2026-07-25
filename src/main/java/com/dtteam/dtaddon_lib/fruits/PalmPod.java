package com.dtteam.dtaddon_lib.fruits;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.pod.Pod;
import net.minecraft.resources.Identifier;

// From DTPHC2
public class PalmPod extends Pod {

    public static final TypedRegistry.EntryType<Pod> TYPE = TypedRegistry.newType(PalmPod::new);

    private int minRadius = 2;

    public PalmPod(Identifier registryName) {
        super(registryName);
    }

    @Override
    public void setMinRadius(int minRadius) {
        super.setMinRadius(minRadius);
        this.minRadius = minRadius;
    }

    /**
     * Palm pods hang off the crown, where the trunk is at its thickest, so any branch at or above the
     * minimum radius supports them — unlike the base implementation there is no upper bound.
     *
     * <p>Up to Dynamic Trees 1.7 this was a {@code PodBlock.isSupported} override installed through
     * {@code Pod.createBlock}. That factory hook is gone in 1.8.0, but the radius test now delegates
     * to the pod, so it hooks in here instead.</p>
     */
    @Override
    public boolean isValidRadius(int radius) {
        return radius >= this.minRadius;
    }

}
