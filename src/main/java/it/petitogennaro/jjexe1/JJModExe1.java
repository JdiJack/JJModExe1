package it.petitogennaro.jjexe1;

import it.petitogennaro.jjexe1.blocks.ModBlocks;
import it.petitogennaro.jjexe1.proxy.CommonProxy;
import it.petitogennaro.jjexe1.util.MyLogger;
import it.petitogennaro.jjexe1.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class JJModExe1{
	
	@Mod.Instance(Reference.MODID)
	public static JJModExe1 instance;
	
	@SidedProxy(serverSide = Reference.SERVER_PROXY_CLASS, clientSide = Reference.CLIENT_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event){
		proxy.preinit(event);
		MyLogger.getLogger().info("preinit");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.init(event);	
		MyLogger.getLogger().info("init");
	}

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {    	
    	proxy.postinit(e);
		MyLogger.getLogger().info("postinit");
    }
    
    public static CreativeTabs tab1 = new CreativeTabs("tab1"){
    	@Override
    	public Item getTabIconItem(){
    		return Item.getItemFromBlock(ModBlocks.block_scaletta);
    	}
    };
    
}
