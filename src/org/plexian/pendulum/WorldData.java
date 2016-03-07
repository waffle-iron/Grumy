package org.plexian.pendulum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.evilco.mc.nbt.tag.ITag;
import com.evilco.mc.nbt.tag.TagByte;
import com.evilco.mc.nbt.tag.TagCompound;
import com.evilco.mc.nbt.tag.TagDouble;
import com.evilco.mc.nbt.tag.TagInteger;
import com.evilco.mc.nbt.tag.TagLong;
import com.evilco.mc.nbt.tag.TagString;

public class WorldData {
	private long seed;
	private WorldType type;
	private String generatorOptions;
	private double spawnX;
	private double spawnY;
	private TagCompound playerData;
	private TagCompound dialogData;
	private String name;
	private World world;
	private int version;
	private boolean initialized;
	private GameMode gameMode;
	private HashMap<String, String> dialog = new HashMap();
	private List entities = new ArrayList();
	
	protected WorldData(){
		this.type = WorldType.CLASSIC;
		this.generatorOptions = "";
	}
	
	public WorldData(TagCompound tagCompound){
		this.type = WorldType.CLASSIC;
		this.generatorOptions = "";
		
		try{
			this.seed = tagCompound.getLong("RandomSeed");
			this.type = WorldType.getType(tagCompound.getString("GeneratorName"));
			this.generatorOptions = tagCompound.getString("GeneratorOptions");
			this.gameMode = GameMode.getById(tagCompound.getInteger("GameMode"));
			this.spawnX = tagCompound.getInteger("SpawnX");
			this.spawnY = tagCompound.getInteger("SpawnY");
			this.name = tagCompound.getString("Name");
			this.initialized = (tagCompound.getByte("Initialized") == 1 ? true : false);
			
			if(tagCompound.getCompound("Player") != null){
				this.playerData = tagCompound.getCompound("Player");
			}
			
			if(tagCompound.getCompound("Dialog") != null){
				this.dialogData = tagCompound.getCompound("Dialog");
				
				for(ITag tag : this.dialogData.getTags().values()){
					String location = "", message = "";
					
					if(tag instanceof TagCompound){
						if(((TagCompound) tag).getString("Location") != null){
							location = ((TagCompound) tag).getString("Location");
						}
						
						if(((TagCompound) tag).getString("Message") != null){
							message = ((TagCompound) tag).getString("Message");
						}
					}
					
					if(location != "" && message != ""){
						this.dialog.put(location, message);
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public WorldData(WorldData worldData){
		this.type = worldData.type;
		this.generatorOptions = "";
		this.seed = worldData.seed;
		this.type = worldData.type;
		this.generatorOptions = worldData.generatorOptions;
		this.gameMode = worldData.gameMode;
		this.spawnX = worldData.spawnX;
		this.spawnY = worldData.spawnY;
		this.name = worldData.name;
		this.playerData = worldData.playerData;
		this.initialized = worldData.initialized;
	}
	
	public static WorldType getWorldType(WorldData data){
		return data.type;
	}
	
	public static String getGeneratorOptions(WorldData data){
		return data.generatorOptions;
	}
	
	public static double getSpawnX(WorldData data){
		return data.spawnX;
	}
	
	public static double getSpawnY(WorldData data){
		return data.spawnY;
	}
	
	public static GameMode getGameMode(WorldData data){
		return data.gameMode;
	}
	
	public TagCompound getDataAsCompound(){
		TagCompound tagCompound = new TagCompound("");
		
		this.getDataAsCompoundAndAddPlayerData(tagCompound, this.playerData);
	
		return tagCompound;
	}
	
	public TagCompound getDataAsCompoundAndAddPlayerData(TagCompound compound){
		TagCompound tagCompound = new TagCompound("");
		
		this.getDataAsCompoundAndAddPlayerData(tagCompound, compound);
		
		return tagCompound;
	}
	
	private void getDataAsCompoundAndAddPlayerData(TagCompound compound, TagCompound add){
		compound.setTag(new TagLong("RandomSeed", this.seed));
		compound.setTag(new TagString("GeneratorName", this.type.toString()));
		compound.setTag(new TagString("GeneratorOptions", this.generatorOptions));
		compound.setTag(new TagInteger("GameMode", this.gameMode.getId()));
		compound.setTag(new TagDouble("SpawnX", this.spawnX));
		compound.setTag(new TagDouble("SpawnY", this.spawnY));
		compound.setTag(new TagString("Name", this.name));
		compound.setTag(new TagByte("Initialized", (this.initialized == true ? (byte)1 : (byte)0)));
		
		if(add != null){
			compound.setTag(add);
		}
	}
	
	public long getSeed(){
		return this.seed;
	}
	
	public double getSpawnX(){
		return this.spawnX;
	}
	
	public double getSpawnY(){
		return this.spawnY;
	}
	
	public TagCompound getPlayerData(){
		return this.playerData;
	}
	
	public void setSpawn(double x, double y){
		this.spawnX = x;
		this.spawnY = y;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String s){
		this.name = s;
	}
	
	public GameMode getGameMode(){
		return this.gameMode;
	}
	
	public void setGameMode(GameMode mode){
		this.gameMode = mode;
	}
	
	public WorldType getType(){
		return this.type;
	}
	
	public void setType(WorldType type){
		this.type = type;
	}
	
	public String getGeneratorOptions(){
		return this.generatorOptions;
	}
	
	public boolean isInitialized(){
		return this.initialized;
	}
	
	public void setInitialized(boolean initialized){
		this.initialized = initialized;
	}
	
	public TagCompound getDialogData(){
		return this.dialogData;
	}
	
	/*
	private HashMap<String, String> dialogMessages;
	private HashMap<Integer, String> entityPositions;
	private YAMLConfiguration config;
	
	public WorldData(World world, String name){
		this.world = world;
		this.name = name;
		this.config = world.fileManager.getConfiguration();
		this.dialogMessages = new HashMap<String, String>();
		this.entityPositions = new HashMap<Integer, String>();
		
		if(this.config.getRootSection().getMap("dialog") != null){
			Map<?, ?> tempMap = this.config.getRootSection().getMap("dialog");
			this.dialogMessages = (HashMap<String, String>)tempMap;
		}
		
		if(this.config.getRootSection().getMap("entities") != null){
			Map<?, ?> tempMap = this.config.getRootSection().getMap("entities");
			this.entityPositions = (HashMap<Integer, String>)tempMap;
		}
	}
	
	public void update(){
		for(Entity e : world.entityManager.getEntities()){
			this.entityPositions.put(e.getId(), e.getLocation().toString());
		}
		
		for(Dialog d : world.dialogManager.getAllDialog()){
			this.dialogMessages.put(d.getLocation().toString(), d.getMessage());
		}
	}
	
	public void dispose(){
		this.save();
	}
	
	public void save(){
		Game.LOG.info("Data: " + dialogMessages.values().size());
		this.config.getRootSection().put("world", name);
		this.config.getRootSection().put("dialog", dialogMessages);
		this.config.getRootSection().put("entities", entityPositions);
		
		this.config.save();
	}
	
	public Map<Integer, String> getEntites(){
		return this.entityPositions;
	}
	
	public Map<String, String> getDialog(){
		return this.dialogMessages;
	}
	
	public void addDialog(String loc, String message){
		this.dialogMessages.put(loc, message);
	}
	
	public void addEntiy(String loc, int id){
		this.entityPositions.put(id, loc);
	}*/
}
