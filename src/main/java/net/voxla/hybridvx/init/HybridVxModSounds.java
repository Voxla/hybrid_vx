
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.voxla.hybridvx.init;

import net.voxla.hybridvx.HybridVxMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

public class HybridVxModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HybridVxMod.MODID);
	public static final RegistryObject<SoundEvent> NONE = REGISTRY.register("none", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "none")));
	public static final RegistryObject<SoundEvent> CRIMSON_FIRE_AMBIENT = REGISTRY.register("crimson_fire.ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "crimson_fire.ambient")));
	public static final RegistryObject<SoundEvent> CRIMSON_FIRE_EXTINGUISH = REGISTRY.register("crimson_fire.extinguish", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "crimson_fire.extinguish")));
	public static final RegistryObject<SoundEvent> CRIMSON_FIRE_LIGHT = REGISTRY.register("crimson_fire.light", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "crimson_fire.light")));
	public static final RegistryObject<SoundEvent> MUSIC_HYBRID_CHASE = REGISTRY.register("music.hybrid.chase", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "music.hybrid.chase")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_FOOTSTEP = REGISTRY.register("entity.hybrid.footstep", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.footstep")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_AMBIENT = REGISTRY.register("entity.hybrid.ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.ambient")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_CHASE_SCREAM = REGISTRY.register("entity.hybrid.chase_scream", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.chase_scream")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_HURT = REGISTRY.register("entity.hybrid.hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.hurt")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_BACKING_AMBIENT = REGISTRY.register("entity.hybrid.backing_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.backing_ambient")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_CHASE_START = REGISTRY.register("entity.hybrid.chase_start", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.chase_start")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_TELEPORT = REGISTRY.register("entity.hybrid.teleport", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.teleport")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_CHASE_END = REGISTRY.register("entity.hybrid.chase_end", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.chase_end")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_DEATH = REGISTRY.register("entity.hybrid.death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.death")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_SPAWN_MFTF_AI = REGISTRY.register("entity.hybrid.spawn_mftf_ai", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.spawn_mftf_ai")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_JUMPSCARE = REGISTRY.register("entity.hybrid.jumpscare", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.jumpscare")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_GRAB_JUMPSCARE = REGISTRY.register("entity.hybrid.grab_jumpscare", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.grab_jumpscare")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_GRAB_KILL = REGISTRY.register("entity.hybrid.grab_kill", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.grab_kill")));
	public static final RegistryObject<SoundEvent> ENTITY_HYBRID_SCREAM = REGISTRY.register("entity.hybrid.scream", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "entity.hybrid.scream")));
	public static final RegistryObject<SoundEvent> AMBIENT_FEAR_HAL = REGISTRY.register("ambient.fear.hal", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "ambient.fear.hal")));
	public static final RegistryObject<SoundEvent> AMBIENT_FEAR_13 = REGISTRY.register("ambient.fear.13", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "ambient.fear.13")));
	public static final RegistryObject<SoundEvent> MUSIC_PLAYER_PANIC_ATTACK = REGISTRY.register("music.player.panic_attack", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "music.player.panic_attack")));
	public static final RegistryObject<SoundEvent> AMBIENT_DISTANT_GROUND = REGISTRY.register("ambient.distant.ground", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "ambient.distant.ground")));
	public static final RegistryObject<SoundEvent> AMBIENT_DISTANT_MUSIC = REGISTRY.register("ambient.distant.music", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "ambient.distant.music")));
	public static final RegistryObject<SoundEvent> AMBIENT_DISTANT_RUMBLE = REGISTRY.register("ambient.distant.rumble", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "ambient.distant.rumble")));
	public static final RegistryObject<SoundEvent> AMBIENT_DISTANT_WIND = REGISTRY.register("ambient.distant.wind", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("hybrid_vx", "ambient.distant.wind")));
}
