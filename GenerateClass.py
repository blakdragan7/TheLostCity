import xml.etree.ElementTree as ET
import sys,os

def getStringForItem(classname,package):
	return 	"package " + package + ".items;\n\n"\
			"import net.minecraft.creativetab.CreativeTabs;\n"\
			"import net.minecraft.item.Item;\n\n"\
			"public class "+classname+" extends Item\n"\
			"{\n"\
			"	public "+classname+"(String unlocalizedName) {\n"\
			"		super();\n\n"\
			"		this.setUnlocalizedName(unlocalizedName);\n"\
			"		this.setCreativeTab(CreativeTabs.tabMisc);\n"\
			"	}\n"\
			"}\n"
			
def getStringForBlock(classname,package):
	return 	"package " + package + ".blocks;\n\n"\
			"import net.minecraft.block.Block;\n"\
			"import net.minecraft.block.material.Material;\n"\
			"import net.minecraft.creativetab.CreativeTabs;\n\n"\
			"public class "+classname+" extends Block{\n"\
			"	public "+classname+" (String unlocal) {\n"\
			"		super(Material.rock);\n"\
			"		this.setUnlocalizedName(unlocal);\n"\
			"		this.setCreativeTab(CreativeTabs.tabMisc);\n"\
			"	}\n"\
			"}"

def main():
	if len(sys.argv) != 4:
		print 'Usage ' + sys.argv[0] + ' *path/to/config.xml* *(Block | Item)* *(classname)*'
		return
	
	type = sys.argv[2]
	classname = sys.argv[3]
	
	print 'Loading Config File '
	
	xml = ET.parse(sys.argv[1])
	if xml is None:
		print 'could not find ' + sys.argv[1]

	root = xml.getroot()
	
	if root is None:
		print 'Invalid XML File !!';
		return
		
	package = root.findall('package')[0]
	rootFolder = root.findall('root_folder')[0]
	useLoaderClass = root.findall('use_loader')[0]
	modID = root.findall('mod_id')[0]
	
	itemLoaderClass = None
	blockLoaderClass = None
	useLoader = False
	if useLoaderClass.text == 'True':
		useLoader = True
		itemLoaderClass = root.findall('item_loader')[0]
		blockLoaderClass = root.findall('block_loader')[0]
		
		if itemLoaderClass is None or blockLoaderClass is None:
			print 'Invalid XML File !!';
			return 
		
	
	if package is None or rootFolder is None or useLoaderClass is None:
		print 'Invalid XML File !!';
		return 
		
	packagePath = package.text.replace('.','/')
	
	if type == 'Block':
		print 'generating files and paths for Block ' + classname
		path = rootFolder.text +'/java/'+packagePath+'/blocks'
		print path,os.path.exists(path)
		if not os.path.exists(path):
			os.makedirs(path)
		classFile = open(path+'/'+classname+'.java','w')
		if classFile is None:
			print ' Could Not Create Class File !!'
		classFile.write(getStringForBlock(classname,package.text))
		classFile.close()
		
	if type == 'Item':
		print 'generating files and paths for Item ' + classname
		
		path = rootFolder.text +'/java/'+packagePath+'/items'
		print path,os.path.exists(path)
		if not os.path.exists(path):
			os.makedirs(path)
		classFile = open(path+'/'+classname+'.java','w')
		if classFile is None:
			print ' Could Not Create Class File !!'
		classFile.write(getStringForItem(classname,package.text))
		classFile.close()
	

if __name__ == '__main__':
	main()