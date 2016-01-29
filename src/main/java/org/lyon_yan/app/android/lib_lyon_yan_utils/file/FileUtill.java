package org.lyon_yan.app.android.lib_lyon_yan_utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class FileUtill {
	private Context context = null;

	public FileUtill(Context context) {
		// TODO Auto-generated constructor stub
		setContext(context);
	}

	/**
	 * 从Assets复制文件到指定目录
	 * @param fileName
	 * @param toFile
	 * @return
	 */
	public File copyApkFromAssets(String fileName, File toFile) {
		try {
			return copyApkFromAssets(getContext().getAssets().open(fileName),toFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return toFile;
	}

	public File copyApkFromAssets(InputStream filein, File toFile){
		try {
			if(toFile.exists()){
				toFile.delete();
			}
			toFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(toFile);
			byte[] temp = new byte[1024];
			int i = 0;
			while ((i = filein.read(temp)) > 0) {
				fos.write(temp, 0, i);
			}
			fos.flush();
			fos.close();
			filein.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return toFile;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * 获取文件夹下的所有文件
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年10月26日 下午5:13:19
	 * @param dir
	 * @return
	 */
	public static List<File> getAllFiles(File dir) {
		List<File> filess = new ArrayList<File>();
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					// 这里面用了递归的算法
					filess.addAll(getAllFiles(file));
				} else {
					filess.add(file);
				}
			}
		}
		return filess;
	}

	/**
	 * 快速复制文件夹的所有内容至指定目录
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年10月26日 下午5:37:17
	 * @param from_path
	 * @param to_path
	 */
	public static void copyFiles(String from_path, String to_path) {
		File dir = new File(from_path);
		File to_dir = new File(to_path);
		if (to_dir.exists()) {
			to_dir.delete();
		}
		to_dir.mkdirs();
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				String temp = File.pathSeparator + file.getName();
				if (file.isDirectory()) {
					// 这里面用了递归的算法
					copyFiles(from_path + temp, to_path + temp);
				} else {
					fileChannelCopy(new File(from_path + temp), new File(
							to_path + temp));
				}
			}
		}
	}

	/**
	 * 
	 * 使用文件通道的方式复制文件
	 * 
	 * 
	 * 
	 * @param s
	 *            源文件
	 * 
	 * @param t
	 *            复制到的新文件
	 */

	public static void fileChannelCopy(File s, File t) {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 复制Assets指定目录资源至指定的目录
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年10月27日 上午9:33:26
	 * @param context
	 * @param to_path
	 * @param dirname
	 *            不允許为空或者“”
	 * @throws IOException
	 */
	public static void copyAssetDirToFiles(Context context, String to_path,
			String dirname) throws IOException {
		File dir = new File(to_path + "/" + dirname);
		if (dir.exists()) {
			dir.delete();
		}
		dir.mkdirs();
		String[] children;
		AssetManager assetManager = context.getAssets();
		children = assetManager.list(dirname);
		for (String child : children) {
			child = dirname + "/" + child;
			String[] grandChildren = assetManager.list(child);
			if (0 == grandChildren.length)
				copyAssetFileToFiles(context, to_path, child);
			else
				copyAssetDirToFiles(context, to_path, child);
		}
	}

	private static final String TAG = "org.lyon_yan.android.utils.file.FileUtill";

	/**
	 * 复制单个文件至指定目录
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年10月27日 上午9:39:42
	 * @param context
	 * @param to_path
	 * @param filename
	 * @throws IOException
	 */
	public static void copyAssetFileToFiles(Context context, String to_path,
			String filename) throws IOException {
		InputStream is = context.getAssets().open(filename);
		byte[] buffer = new byte[is.available()];
		is.read(buffer);
		is.close();
		String t = "/";
		File of = new File(
				to_path.lastIndexOf(t) == to_path.length() - 1 ? to_path 
						+ filename : to_path + t+ filename);
		if (of.exists()) {
			of.delete();
		}
		of.createNewFile();
		FileOutputStream os = new FileOutputStream(of);
		os.write(buffer);
		os.close();
	}

//	public static void copyFilesFromAsset(Context context){
////		context.getAssets().getLocales()
//	}
}
