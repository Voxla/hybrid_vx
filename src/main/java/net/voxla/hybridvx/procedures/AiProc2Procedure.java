package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.init.HybridVxModParticleTypes;
import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import java.util.Comparator;

public class AiProc2Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty() && !(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 80, 80, 80), e -> true).isEmpty())) {
			if (entity instanceof Mob _entity)
				_entity.getNavigation().moveTo((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getX()), (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getY()), (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getZ()), 1);
		}
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 80, 80, 80), e -> true).isEmpty()) {
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3, 200, false, false));
		}
		if ((entity instanceof AmalgamationEntity _datEntL11 && _datEntL11.getEntityData().get(AmalgamationEntity.DATA_IsBeingLookedAt)) == true
				|| !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 8, 8, 8), e -> true).isEmpty()) {
			if (Math.random() > 0.4) {
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 2,
									(float) Mth.nextDouble(RandomSource.create(), 0.8, 1));
						} else {
							_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 2, (float) Mth.nextDouble(RandomSource.create(), 0.8, 1), false);
						}
					}
				}
				if (!entity.level.isClientSide())
					entity.discard();
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
			} else {
				if ((entity instanceof AmalgamationEntity _datEntL25 && _datEntL25.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == false) {
					if (entity instanceof AmalgamationEntity _datEntSetL)
						_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_Chasing, true);
				}
			}
		}
	}
}
