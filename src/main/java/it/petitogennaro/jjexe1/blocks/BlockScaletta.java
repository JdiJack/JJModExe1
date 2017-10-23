package it.petitogennaro.jjexe1.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.petitogennaro.jjexe1.JJModExe1;
import it.petitogennaro.jjexe1.util.MyLogger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlockScaletta extends BlockLadder{

	public BlockScaletta(String unlocalizedName, String registryName){
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.setCreativeTab(JJModExe1.tab1);
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		if(placer instanceof EntityPlayerMP){
			if(!worldIn.isRemote){
				//lato server
				EntityPlayerMP player = (EntityPlayerMP)placer;
				ItemStack itemstack_da_cercare = new ItemStack(Item.getItemFromBlock(this));
				int pos_inferiore = 1;
				IBlockState state = worldIn.getBlockState(pos);
				BlockPos block_pos_inferiore = new BlockPos(new Vec3d(pos.getX(), pos.getY()-pos_inferiore, pos.getZ()));
				BlockPos block_pos_inferiore_dietro;
				if(facing.getName().equals("north")){
					block_pos_inferiore_dietro = new BlockPos(new Vec3d(pos.getX(), pos.getY()-pos_inferiore, pos.getZ()+1));
				}else if(facing.getName().equals("south")){
					block_pos_inferiore_dietro = new BlockPos(new Vec3d(pos.getX(), pos.getY()-pos_inferiore, pos.getZ()-1));					
				}else if(facing.getName().equals("east")){
					block_pos_inferiore_dietro = new BlockPos(new Vec3d(pos.getX()-1, pos.getY()-pos_inferiore, pos.getZ()));					
				}else {
					block_pos_inferiore_dietro = new BlockPos(new Vec3d(pos.getX()+1, pos.getY()-pos_inferiore, pos.getZ()));						
				}
				Block blocco_inferiore_dietro = worldIn.getBlockState(block_pos_inferiore_dietro).getBlock();
				Block blocco_inferiore = worldIn.getBlockState(block_pos_inferiore).getBlock();				
				checkItemInInvetory(player, itemstack_da_cercare);
				// ogni volta che il blocco inferiore è aria
				while(blocco_inferiore instanceof BlockAir && !(blocco_inferiore_dietro instanceof BlockAir)){
					// se in inventario c'è una scaletta
					if(player.inventory.hasItemStack(itemstack_da_cercare)){
	                    if(checkItemInInvetory(player, itemstack_da_cercare))
	                    	worldIn.setBlockState(block_pos_inferiore, this.getDefaultState().withProperty(FACING, facing));	
						pos_inferiore++;
						block_pos_inferiore = new BlockPos(new Vec3d(pos.getX(), pos.getY()-pos_inferiore, pos.getZ()));
						blocco_inferiore = worldIn.getBlockState(block_pos_inferiore).getBlock();
						if(facing.getName().equals("north")){
							block_pos_inferiore_dietro = new BlockPos(new Vec3d(pos.getX(), pos.getY()-pos_inferiore, pos.getZ()+1));
						}else if(facing.getName().equals("south")){
							block_pos_inferiore_dietro = new BlockPos(new Vec3d(pos.getX(), pos.getY()-pos_inferiore, pos.getZ()-1));					
						}else if(facing.getName().equals("east")){
							block_pos_inferiore_dietro = new BlockPos(new Vec3d(pos.getX()-1, pos.getY()-pos_inferiore, pos.getZ()));					
						}else {
							block_pos_inferiore_dietro = new BlockPos(new Vec3d(pos.getX()+1, pos.getY()-pos_inferiore, pos.getZ()));						
						}
						blocco_inferiore_dietro = worldIn.getBlockState(block_pos_inferiore_dietro).getBlock();
					}else
						break;
				}
			}
		}
		
		
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
	}
	
	public boolean checkItemInInvetory(EntityPlayerMP player, ItemStack itemstack_da_cercare){
		if(player.inventory.hasItemStack(itemstack_da_cercare)){			
			ItemStack[] list_stacks = player.inventory.mainInventory;
			for(int i = 0; i <list_stacks.length; i++){
				if(list_stacks[i]!=null && list_stacks[i].getItem() == Item.getItemFromBlock(this)){
					ItemStack newStack = player.inventory.decrStackSize(i, list_stacks[i].stackSize-1);
					player.inventory.setInventorySlotContents(i, newStack);
					return true;
				}
			}			
		}
		return false;
	}

	/*
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.player



		super.onBlockAdded(worldIn, pos, state);
	}*/
}
