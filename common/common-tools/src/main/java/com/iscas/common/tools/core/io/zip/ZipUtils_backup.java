package com.iscas.common.tools.core.io.zip;


import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: DataDong
 * @Descrition: zip压缩包处理工具类
 * @Date: Create in 2018/9/5 19:10
 * @Modified By:
 */
@SuppressWarnings("ALL")
public class ZipUtils_backup {
	private static final int  BUFFER_SIZE = 4096;

	private ZipUtils_backup(){}

	/**
	 * 递归压缩方法
	 * @param sourceFile 源文件
	 * @param zos        zip输出流
	 * @param name       压缩后的名称
	 * @param keepDirStructure  是否保留原来的目录结构,true:保留目录结构;
	 *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
	 * @throws Exception
	 */
	private static void compress(File sourceFile, ZipOutputStream zos, String name,boolean keepDirStructure) throws Exception{
		byte[] buf = new byte[BUFFER_SIZE];
		if(sourceFile.isFile()){
			// 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
			zos.putNextEntry(new ZipEntry(name));
			// copy文件到zip输出流中
			int len;
			FileInputStream in = new FileInputStream(sourceFile);
			while ((len = in.read(buf)) != -1){
				zos.write(buf, 0, len);
			}

			// Complete the entry
			zos.closeEntry();
			in.close();
		} else {
			File[] listFiles = sourceFile.listFiles();

			if(listFiles == null || listFiles.length == 0){
				// 需要保留原来的文件结构时,需要对空文件夹进行处理
				if(keepDirStructure){
					// 空文件夹的处理
					zos.putNextEntry(new ZipEntry(name + "/"));
					// 没有文件，不需要文件的copy
					zos.closeEntry();
				}
			}else {
				for (File file : listFiles) {
					// 判断是否需要保留原来的文件结构
					if (keepDirStructure) {
						// 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
						// 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
						compress(file, zos, name + "/" + file.getName(),keepDirStructure);
					} else {
						compress(file, zos, file.getName(),keepDirStructure);
					}
				}
			}
		}
	}

	/**
	 * 压缩文件或文件夹,压缩后的文件名由源文件决定
	 * @param sourcePath 源文件路径
	 * @param destinationPath 压缩包存放路径，是否已斜杠结尾均可
	 * @return 处理后的压缩包绝对路径
	 */
	public static String toZip(String sourcePath, String destinationPath ) throws Exception {
		/*参数预处理*/
		if(StringUtils.isEmpty(sourcePath) ){
			throw new Exception(String.format("parameter is null or empty"));
		}

		String sourceFileName = sourcePath.substring(1+Math.max(sourcePath.lastIndexOf("/"),sourcePath.lastIndexOf("\\")));
		int index = sourceFileName.indexOf(".");
		String destinationFileName = (index>=0?sourceFileName.substring(0,index):sourceFileName)+".zip";
		return toZip(sourcePath, destinationPath, destinationFileName);
	}

	/**
	 * 压缩文件或文件夹
	 * @param sourcePath 源文件路径
	 * @param destinationPath 压缩包存放路径，是否已斜杠结尾均可
	 * @param destinationFileName 压缩包名字，带不带后缀名均可
	 * @return 处理后的压缩包绝对路径
	 */
	@SuppressWarnings("AlibabaUndefineMagicConstant")
	public static String toZip(String sourcePath, String destinationPath, String destinationFileName)
		throws Exception {
		/*参数预处理*/
		if(StringUtils.isEmpty(sourcePath) || StringUtils.isEmpty(destinationPath) || StringUtils.isEmpty(destinationFileName)){
			throw new Exception(String.format("parameter is null or empty"));
		}

		File sourceFile = new File(sourcePath);
		if(!sourceFile.exists()){
			throw new Exception(String.format("file: '%s' not exist", sourcePath));
		}
		String sourceFileName = sourcePath.substring(1+Math.max(sourcePath.lastIndexOf("/"),sourcePath.lastIndexOf("\\")));

		if(!destinationPath.endsWith("/") && !destinationPath.endsWith("\\")){
			destinationPath += File.separator;
		}

		if(!destinationFileName.endsWith(".zip")){
			destinationFileName += ".zip";
		}


		/*压缩*/
		ZipOutputStream zip = null;
		String result = null;
		try {

			File destinationFile = new File(destinationPath);
			destinationFile.mkdir();

			result= destinationPath+destinationFileName;
			zip = new ZipOutputStream(new FileOutputStream(result));
			zip.setEncoding("GBK");
			compress(sourceFile,zip, sourceFileName, true);

			zip.closeEntry();
			zip.close();
			zip = null;
		}catch (IOException e){
			e.printStackTrace();
			throw e;
		}finally {
			/*确保文件被释放*/
			try {
				if(zip != null) {
					zip.closeEntry();
					zip.close();
				}
			} catch (IOException e1) {
			}
		}

		return result;
	}

//	public static void  main(String[] args) throws Exception {
//		System.out.println(toZip("F:/软件所/2018年04月人员分工/分工.xlsx","f:"));
//	}
}
