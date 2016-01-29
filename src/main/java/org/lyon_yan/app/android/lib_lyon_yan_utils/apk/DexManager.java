package org.lyon_yan.app.android.lib_lyon_yan_utils.apk;

import java.util.HashMap;

import android.content.Context;
import dalvik.system.DexClassLoader;

public class DexManager extends DexClassLoader{
    //创建一个插件加载器集合，对固定的dex使用固定的加载器可以防止多个加载器同时加载一个dex造成的错误。
    private static final HashMap<String, DexManager> pluginLoader = new HashMap<String, DexManager>();

	public DexManager(String dexPath, String optimizedDirectory,
			String libraryPath, ClassLoader parent) {
		super(dexPath, optimizedDirectory, libraryPath, parent);
		// TODO Auto-generated constructor stub
	}
	
	/**
     * 返回dexPath对应的加载器
     */
    public static DexManager getClassLoader(String dexPath, Context context,
            ClassLoader parent) {
    	DexManager dexManager = pluginLoader.get(dexPath);
        if (dexManager == null) {
            // 获取到app的启动路径
            final String dexOutputPath = context
                    .getDir("dex", Context.MODE_PRIVATE).getAbsolutePath();
            dexManager = new DexManager(dexPath, dexOutputPath, null, parent);
            pluginLoader.put(dexPath, dexManager);
        }
        return dexManager;
    }

}
