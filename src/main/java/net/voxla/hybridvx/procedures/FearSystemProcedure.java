package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;
import net.voxla.hybridvx.init.HybridVxModParticleTypes;
import net.voxla.hybridvx.init.HybridVxModItems;
import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.client.player.LocalPlayer;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

@Mod.EventBusSubscriber
public class FearSystemProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level, event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		com.google.gson.JsonObject amaljsonobj = new com.google.gson.JsonObject();
		File hybridconfig = new File("");
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).VoidGatewayJumpscareTimer > 0) {
			if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).VoidGatewayJumpscareTimer == 12) {
				if (world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.jumpscare")), SoundSource.HOSTILE, 1, (float) 0.9);
						} else {
							_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.jumpscare")), SoundSource.HOSTILE, 1, (float) 0.9, false);
						}
					}
				}
				if (Math.random() > 0.7) {
					{
						double _setval = 301;
						entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.PanicAttackTimer = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
			{
				double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).VoidGatewayJumpscareTimer - 1;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.VoidGatewayJumpscareTimer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PanicAttackTimer > 0) {
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
					if (amaljsonobj.get("can_have_panic_attacks").getAsBoolean() == true) {
						if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter >= 2400) {
							if (Math.random() > 0.7) {
								world.addParticle((SimpleParticleType) (HybridVxModParticleTypes.LURKER_EYE_PARTICLE.get()), (x + Mth.nextDouble(RandomSource.create(), -6, 6)), (y + Mth.nextDouble(RandomSource.create(), 0, 6)),
										(z + Mth.nextDouble(RandomSource.create(), -6, 6)), 0, 0, 0);
							}
							if (Math.random() > 0.95) {
								if (world.isClientSide()) {
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x + Mth.nextDouble(RandomSource.create(), -6, 6), y + Mth.nextDouble(RandomSource.create(), 0, 6), z + Mth.nextDouble(RandomSource.create(), -6, 6)),
													ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.hal")), SoundSource.RECORDS, 1, 1);
										} else {
											_level.playLocalSound((x + Mth.nextDouble(RandomSource.create(), -6, 6)), (y + Mth.nextDouble(RandomSource.create(), 0, 6)), (z + Mth.nextDouble(RandomSource.create(), -6, 6)),
													ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.hal")), SoundSource.RECORDS, 1, 1, false);
										}
									}
								}
							}
							if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PanicAttackTimer == 300) {
								if (world.isClientSide()) {
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:music.player.panic_attack")), SoundSource.RECORDS, 1, 1);
										} else {
											_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:music.player.panic_attack")), SoundSource.RECORDS, 1, 1, false);
										}
									}
								}
							}
						}
						if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter >= 4800) {
							if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 3, 0, false, false));
							if (Math.random() > 0.7) {
								world.addParticle((SimpleParticleType) (HybridVxModParticleTypes.FEAR_SMOKE.get()), (x + Mth.nextDouble(RandomSource.create(), -6, 6)), (y + Mth.nextDouble(RandomSource.create(), 0, 6)),
										(z + Mth.nextDouble(RandomSource.create(), -6, 6)), 0, 0, 0);
							}
							if (Math.random() > 0.7) {
								world.addParticle((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (x + Mth.nextDouble(RandomSource.create(), -6, 6)), (y + Mth.nextDouble(RandomSource.create(), 0, 6)),
										(z + Mth.nextDouble(RandomSource.create(), -6, 6)), 0, 0, 0);
							}
							if (Math.random() > 0.8) {
								if (world.isClientSide()) {
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x + Mth.nextDouble(RandomSource.create(), -20, 20), y + Mth.nextDouble(RandomSource.create(), 0, 2), z + Mth.nextDouble(RandomSource.create(), -20, 20)),
													ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.wind")), SoundSource.RECORDS, 1, 1);
										} else {
											_level.playLocalSound((x + Mth.nextDouble(RandomSource.create(), -20, 20)), (y + Mth.nextDouble(RandomSource.create(), 0, 2)), (z + Mth.nextDouble(RandomSource.create(), -20, 20)),
													ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.wind")), SoundSource.RECORDS, 1, 1, false);
										}
									}
								}
							}
						}
						if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter >= 7200) {
							if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) || y > 50) {
								if (Math.random() > 0.8) {
									if (world.isClientSide()) {
										if (world instanceof Level _level) {
											if (!_level.isClientSide()) {
												_level.playSound(null, BlockPos.containing(x + Mth.nextDouble(RandomSource.create(), -20, 20), y + Mth.nextDouble(RandomSource.create(), 0, 2), z + Mth.nextDouble(RandomSource.create(), -20, 20)),
														ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.ground")), SoundSource.RECORDS, 1, 1);
											} else {
												_level.playLocalSound((x + Mth.nextDouble(RandomSource.create(), -20, 20)), (y + Mth.nextDouble(RandomSource.create(), 0, 2)), (z + Mth.nextDouble(RandomSource.create(), -20, 20)),
														ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.ground")), SoundSource.RECORDS, 1, 1, false);
											}
										}
									}
								}
							}
							if (Math.random() > 0.8) {
								if (world.isClientSide()) {
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x + Mth.nextDouble(RandomSource.create(), -20, 20), y + Mth.nextDouble(RandomSource.create(), 0, 2), z + Mth.nextDouble(RandomSource.create(), -20, 20)),
													ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.rumble")), SoundSource.RECORDS, 1, 1);
										} else {
											_level.playLocalSound((x + Mth.nextDouble(RandomSource.create(), -20, 20)), (y + Mth.nextDouble(RandomSource.create(), 0, 2)), (z + Mth.nextDouble(RandomSource.create(), -20, 20)),
													ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.rumble")), SoundSource.RECORDS, 1, 1, false);
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
			{
				double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PanicAttackTimer - 1;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.PanicAttackTimer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PanicAttackTimer < 0) {
			{
				double _setval = 0;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.PanicAttackTimer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PanicAttackTimer != 0
				&& !world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3(x, y, z), 30, 30, 30), e -> true).isEmpty()) {
			{
				double _setval = 0;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.PanicAttackTimer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter < 12000
				&& !world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3(x, y, z), 30, 30, 30), e -> true).isEmpty()) {
			{
				double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter + 1;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FearMeter = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter > 12000) {
			{
				double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter - 1;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FearMeter = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter < 0) {
			{
				double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter + 1;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FearMeter = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter < 12000
				&& (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeterNaturalTimer < 160) {
			{
				double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeterNaturalTimer + 1;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FearMeterNaturalTimer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter < 12000
				&& (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeterNaturalTimer >= 160) {
			{
				double _setval = 0;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FearMeterNaturalTimer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter + 1;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FearMeter = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PlayerWakesUpTimer > 1) {
			{
				double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PlayerWakesUpTimer - 1;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.PlayerWakesUpTimer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PlayerWakesUpTimer == 1) {
			{
				double _setval = 0;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.PlayerWakesUpTimer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (world instanceof ServerLevel _origLevel) {
				LevelAccessor _worldorig = world;
				world = _origLevel.getServer().getLevel(Level.OVERWORLD);
				if (world != null) {
					if (world instanceof Level _lvl44 && _lvl44.isDay() && (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter > 1000) {
						{
							double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter - 1000;
							entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.FearMeter = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				}
				world = _worldorig;
			}
		}
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if ((world instanceof Level _lvl46 && _lvl46.isDay()) == true && world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) || world.getMaxLocalRawBrightness(BlockPos.containing(x, y, z)) >= 15) {
					if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeterReduceDaytimeCooldown < 50) {
						{
							double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeterReduceDaytimeCooldown + 1;
							entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.FearMeterReduceDaytimeCooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
					if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter > 0
							&& (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeterReduceDaytimeCooldown >= 50) {
						{
							double _setval = 0;
							entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.FearMeterReduceDaytimeCooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter - 1;
							entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.FearMeter = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				} else if ((world instanceof Level _lvl49 && _lvl49.isDay()) == false && world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) || world.getMaxLocalRawBrightness(BlockPos.containing(x, y, z)) == 0
						&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("hybrid_vx:lightitems"))))
						&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("hybrid_vx:lightitems"))))) {
					if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeterPlusNighttimeCooldown < 5) {
						{
							double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeterPlusNighttimeCooldown + 1;
							entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.FearMeterPlusNighttimeCooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
					if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter < 12000
							&& (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeterPlusNighttimeCooldown >= 5) {
						{
							double _setval = 0;
							entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.FearMeterPlusNighttimeCooldown = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter + 1;
							entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.FearMeter = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				}
			}
			world = _worldorig;
		}
		if (((entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(HybridVxModItems.VILE_FLESH.get())) : false)
				|| (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(HybridVxModItems.VILE_BONE.get())) : false)
				|| (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(HybridVxModItems.VILE_BLADE.get())) : false)
				|| (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(HybridVxModItems.VILE_MIRROR.get())) : false)) && (!(new Object() {
					public boolean hasRecipe(Entity _ent, ResourceLocation recipe) {
						if (_ent instanceof ServerPlayer _player)
							return _player.getRecipeBook().contains(recipe);
						else if (_ent.level.isClientSide() && _ent instanceof LocalPlayer _player)
							return _player.getRecipeBook().contains(recipe);
						return false;
					}
				}.hasRecipe(entity, new ResourceLocation("hybrid_vx:vile_mirror_recipe"))) || !(new Object() {
					public boolean hasRecipe(Entity _ent, ResourceLocation recipe) {
						if (_ent instanceof ServerPlayer _player)
							return _player.getRecipeBook().contains(recipe);
						else if (_ent.level.isClientSide() && _ent instanceof LocalPlayer _player)
							return _player.getRecipeBook().contains(recipe);
						return false;
					}
				}.hasRecipe(entity, new ResourceLocation("hybrid_vx:vile_blade_recipe"))))) {
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("hybrid_vx:vile_mirror_recipe")});
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("hybrid_vx:vile_blade_recipe")});
		}
	}
}
