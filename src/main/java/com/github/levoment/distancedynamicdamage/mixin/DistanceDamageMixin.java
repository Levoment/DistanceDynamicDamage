package com.github.levoment.distancedynamicdamage.mixin;

import com.github.levoment.distancedynamicdamage.DistanceDynamicDamageMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class DistanceDamageMixin extends Entity {

	public DistanceDamageMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Redirect(method = "attack(Lnet/minecraft/entity/Entity;)V", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
	public boolean damageModify(Entity instance, DamageSource source, float amount) {
		if (!source.getSource().world.isClient) {
			// Get the current player and target positions
			Vec3d currentPos = getPos();
			Vec3d targetBlockPos = instance.getPos();
			// Get the distance between the player and the target
			double distance = targetBlockPos.distanceTo(currentPos);
			// Calculate the damage
			float result = (float) ((amount/Math.max(1, distance))*DistanceDynamicDamageMod.DYNAMIC_DAMAGE);
			// Use the new damage amount
			return instance.damage(source, result);
		}
		return this.damage(source, amount);
	}
}
