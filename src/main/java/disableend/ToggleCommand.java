package disableend;

import com.mojang.brigadier.Command;
import disableend.config.ConfigManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.Permissions;

public class ToggleCommand
{
    public void register()
    {
        CommandRegistrationCallback.EVENT.register((dispatcher, _, _) ->
            dispatcher.register(net.minecraft.commands.Commands.literal("disableend")
                .requires(s -> s.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER))
                .then(net.minecraft.commands.Commands.literal("reload")
                    .executes(ctx ->
                    {
                        ConfigManager.load();
                        ctx.getSource().sendSuccess(() -> Component.literal("DisableEnd config reloaded."), true);
                        return Command.SINGLE_SUCCESS;
                    }))
                .then(net.minecraft.commands.Commands.literal("enable")
                    .executes(ctx ->
                    {
                        ConfigManager.get().disableLightingEndPortal = false;
                        ConfigManager.save();
                        ctx.getSource().sendSuccess(() -> Component.literal("Enabled lighting the end portal."), true);
                        return Command.SINGLE_SUCCESS;
                    }))
                .then(net.minecraft.commands.Commands.literal("disable")
                    .executes(ctx ->
                    {
                        ConfigManager.get().disableLightingEndPortal = true;
                        ConfigManager.save();
                        ctx.getSource().sendSuccess(() -> Component.literal("Disabled lighting the end portal."), true);
                        return Command.SINGLE_SUCCESS;
                    }))));
    }
}
