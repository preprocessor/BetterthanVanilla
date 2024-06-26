package lusiiplugin.mixin;

import net.minecraft.core.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface IEntityMixin {
	@Invoker("resetPos")
	void invokeResetPos();
}
