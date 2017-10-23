package it.petitogennaro.jjexe1.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;

public class ModBlocks {
	
	public static ArrayList<Block> blocks;
	public static BlockReplicatore block_replicatore;
	public static BlockScaletta block_scaletta;


	public static void init(){
		blocks = new ArrayList<Block>();
		block_replicatore = new BlockReplicatore("block_replicatore", "block_replicatore");
		block_scaletta = new BlockScaletta("block_scaletta", "block_scaletta");
		blocks.add(block_replicatore);
		blocks.add(block_scaletta);
	}
}
