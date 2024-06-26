package com.example.activitylifecycle.base;

import android.app.Application;
import android.util.Log;

import com.example.activitylifecycle.sharedpreferences.DataKeeper;


/**基础Application
 * @author Lemon
 * @see #init
 * @use extends BaseApplication 或 在你的Application的onCreate方法中BaseApplication.init(this);
 */
public class BaseApplication extends Application {
	private static final String TAG = "BaseApplication";

	public BaseApplication() {
	}
	
	private static Application instance;
	public static Application getInstance() {
		return instance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "项目启动 >>>>>>>>>>>>>>>>>>>> \n\n");
		
		init(this);

//		CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/OpenSans-Light.ttf").setFontAttrId(R.attr.fontPath).build());

	}

	/**初始化方法
	 * @param application
	 * @must 调用init方法且只能调用一次，如果extends BaseApplication会自动调用
	 */
	public static void init(Application application) {
		instance = application;
		if (instance == null) {
			Log.e(TAG, "\n\n\n\n\n !!!!!! 调用BaseApplication中的init方法，instance不能为null !!!" +
					"\n <<<<<< init  instance == null ！！！ >>>>>>>> \n\n\n\n");
		}
		
		DataKeeper.init(instance);
//		SettingUtil.init(instance);
	}

}
