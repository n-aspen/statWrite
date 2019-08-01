package net.mov51.statWrite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class StatWrite implements ModInitializer {
	private File Conf = new File (FabricLoader.getInstance().getConfigDirectory() + "\\positionWrite");
	@Override
	public void onInitialize() {
		if (Files.notExists(Conf.toPath())) {
			if(Conf.mkdirs()){
				System.out.println("Folder made!");
			}
		}

		ClientTickCallback.EVENT.register((mc) -> {
			PlayerEntity clientPlayer = MinecraftClient.getInstance().player;
			if (clientPlayer != null ){
				Vec3d position = clientPlayer.getPos();
				write(position.x,"\\Xpos.txt");
				write(position.y,"\\Ypos.txt");
				write(position.z,"\\Zpos.txt");
			}
		});
	}
	public void write(Double string, String filePath) {
		try (BufferedWriter out = new BufferedWriter (new FileWriter(Conf + filePath))) {
			out.write(String.valueOf(string));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
