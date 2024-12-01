package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;
import net.voxla.hybridvx.init.HybridVxModParticleTypes;
import net.voxla.hybridvx.entity.VoidGatewayHallucinationEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import java.util.Comparator;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class VoidGatewayHallucinationOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		com.google.gson.JsonObject amaljsonobj = new com.google.gson.JsonObject();
		File hybridconfig = new File("");
		if (entity.isPassenger()) {
			entity.stopRiding();
		}
		if ((entity instanceof VoidGatewayHallucinationEntity _datEntI ? _datEntI.getEntityData().get(VoidGatewayHallucinationEntity.DATA_PlayerActivationGateway) : 0) >= 1) {
			if (entity instanceof VoidGatewayHallucinationEntity) {
				((VoidGatewayHallucinationEntity) entity).setAnimation("gatewayclose");
			}
		}
		if ((entity instanceof VoidGatewayHallucinationEntity _datEntI ? _datEntI.getEntityData().get(VoidGatewayHallucinationEntity.DATA_CloseTime) : 0) >= 9) {
			if (!entity.level.isClientSide())
				entity.discard();
		}
		if ((entity instanceof VoidGatewayHallucinationEntity _datEntI ? _datEntI.getEntityData().get(VoidGatewayHallucinationEntity.DATA_PlayerActivationGateway) : 0) <= 0
				&& !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 10, 10, 10), e -> true).isEmpty()) {
			if (entity instanceof VoidGatewayHallucinationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(VoidGatewayHallucinationEntity.DATA_PlayerActivationGateway,
						(int) ((entity instanceof VoidGatewayHallucinationEntity _datEntI ? _datEntI.getEntityData().get(VoidGatewayHallucinationEntity.DATA_PlayerActivationGateway) : 0) + 1));
		}
		if ((entity instanceof VoidGatewayHallucinationEntity _datEntI ? _datEntI.getEntityData().get(VoidGatewayHallucinationEntity.DATA_PlayerActivationGateway) : 0) >= 1
				&& (entity instanceof VoidGatewayHallucinationEntity _datEntI ? _datEntI.getEntityData().get(VoidGatewayHallucinationEntity.DATA_CloseTime) : 0) < 9) {
			if (entity instanceof VoidGatewayHallucinationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(VoidGatewayHallucinationEntity.DATA_CloseTime, (int) ((entity instanceof VoidGatewayHallucinationEntity _datEntI ? _datEntI.getEntityData().get(VoidGatewayHallucinationEntity.DATA_CloseTime) : 0) + 1));
		}
		if ((entity instanceof VoidGatewayHallucinationEntity _datEntI ? _datEntI.getEntityData().get(VoidGatewayHallucinationEntity.DATA_CloseTime) : 0) == 4) {
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 1, 1, false);
					}
				}
			}
		}
		hybridconfig = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "hybridvxconfig.json");
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(hybridconfig));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				amaljsonobj = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (amaljsonobj.get("hallucination_jumpscare").getAsBoolean() == true) {
					if ((entity instanceof VoidGatewayHallucinationEntity _datEntI ? _datEntI.getEntityData().get(VoidGatewayHallucinationEntity.DATA_PlayerActivationGateway) : 0) >= 1
							&& (entity instanceof VoidGatewayHallucinationEntity _datEntI ? _datEntI.getEntityData().get(VoidGatewayHallucinationEntity.DATA_CloseTime) : 0) == 8) {
						if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 100, 100, 100), e -> true).isEmpty()) {
							if (Math.random() > 0.6) {
								if ((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 100, 100, 100), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf((entity.getX()), (entity.getY()), (entity.getZ()))).findFirst().orElse(null)).getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new HybridVxModVariables.PlayerVariables())).VoidGatewayJumpscareTimer < 1) {
									{
										double _setval = 19;
										((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 100, 100, 100), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf((entity.getX()), (entity.getY()), (entity.getZ()))).findFirst().orElse(null)).getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.VoidGatewayJumpscareTimer = _setval;
											capability.syncPlayerVariables(
													((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 100, 100, 100), e -> true).stream().sorted(new Object() {
														Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
															return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
														}
													}.compareDistOf((entity.getX()), (entity.getY()), (entity.getZ()))).findFirst().orElse(null)));
										});
									}
								}
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (Math.random() > 0.9) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), x, y, z, 1, 0.18, 0.2, 0.18, 0.1);
		}
		if (Math.random() > 0.9) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FEAR_SMOKE.get()), x, y, z, 1, 0.18, 0.2, 0.18, 0.01);
		}
		if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 60, 255, false, false));
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.WATER || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.WATER
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.WATER || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.WATER) {
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 1, 1, false);
					}
				}
			}
			if (!entity.level.isClientSide())
				entity.discard();
		}
	}
}
