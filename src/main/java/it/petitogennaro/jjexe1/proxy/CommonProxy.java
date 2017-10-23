package it.petitogennaro.jjexe1.proxy;

import it.petitogennaro.jjexe1.blocks.ModBlocks;
import it.petitogennaro.jjexe1.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

	public void preinit(FMLPreInitializationEvent event) {
		ModBlocks.init();
        ModItems.init();
        for(Block block: ModBlocks.blocks){
        	GameRegistry.register(block);
		}
        for (Block block : ModBlocks.blocks){
        	GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		}
		
		for(Item item: ModItems.items){ 
			GameRegistry.register(item);
		}	
    }

    public void init(FMLInitializationEvent event) {
    	
    }

    public void postinit(FMLPostInitializationEvent event) {
    	
    }   
	
}
