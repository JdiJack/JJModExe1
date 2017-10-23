package it.petitogennaro.jjexe1.blocks;

import java.util.Random;

import it.petitogennaro.jjexe1.JJModExe1;
import it.petitogennaro.jjexe1.util.MyLogger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockReplicatore extends Block{

	private static final AxisAlignedBB AABB = new AxisAlignedBB(0D, 0D, 0.D, 1D, 1D, 1D); //boundingbox

	public BlockReplicatore(String unlocalizedName, String registryName){
		super(Material.ROCK);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.setCreativeTab(JJModExe1.tab1);
		this.setHardness(3); 
		this.setResistance(3);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
		return AABB;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return Item.getItemFromBlock(this);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state){
		return false;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		BlockPos block_pos_superiore = new BlockPos(new Vec3d(pos.getX(), pos.getY()+1, pos.getZ()));	
		if(worldIn.isBlockPowered(pos))
			worldIn.setBlockState(block_pos_superiore, state);
		else{
			if(worldIn.getBlockState(block_pos_superiore).getBlock() instanceof BlockReplicatore)
				worldIn.setBlockState(block_pos_superiore, Blocks.AIR.getDefaultState());	
		}							
		super.neighborChanged(state, worldIn, pos, blockIn);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(worldIn.isBlockPowered(pos)){
			BlockPos block_pos_superiore = new BlockPos(new Vec3d(pos.getX(), pos.getY()+1, pos.getZ()));
			worldIn.setBlockState(block_pos_superiore, state);
		}
		super.onBlockAdded(worldIn, pos, state);
	}

	@Override
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
		BlockPos block_pos_inferiore = new BlockPos(new Vec3d(pos.getX(), pos.getY()-1, pos.getZ()));
		Block blocco_inferiore = worldIn.getBlockState(block_pos_inferiore).getBlock();
		if(blocco_inferiore instanceof BlockReplicatore)
			return 1000000000;
		else
			return super.getBlockHardness(blockState, worldIn, pos);
	}

	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		BlockPos block_pos_superiore = new BlockPos(new Vec3d(pos.getX(), pos.getY()+1, pos.getZ()));	
		worldIn.setBlockState(block_pos_superiore, Blocks.AIR.getDefaultState());										
		super.breakBlock(worldIn, pos, state);
	}
}
