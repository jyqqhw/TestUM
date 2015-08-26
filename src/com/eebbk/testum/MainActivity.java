package com.eebbk.testum;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	// 首先在您的Activity中添加如下成员变量
	final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
	private Button mBtnShare;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mBtnShare = (Button) findViewById(R.id.btn_share);
		
		// 首先在您的Activity中添加如下成员变量
		final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
		// 设置分享内容
		mController.setShareContent("This is a share function test!");
		// 设置分享图片, 参数2为图片的url地址
//		mController.setShareMedia(new UMImage(this, 
//		                                      "http://www.umeng.com/images/pic/banner_module_social.png"));
		// 设置分享图片，参数2为本地图片的资源引用
//		mController.setShareMedia(new UMImage(this, R.drawable.ic_launcher));
		// 设置分享图片，参数2为本地图片的路径(绝对路径)
		//mController.setShareMedia(new UMImage(getActivity(), 
//		                                BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));

		// 设置分享音乐
		//UMusic uMusic = new UMusic("http://sns.whalecloud.com/test_music.mp3");
		//uMusic.setAuthor("GuGu");
		//uMusic.setTitle("天籁之音");
		// 设置音乐缩略图
		//uMusic.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
		//mController.setShareMedia(uMusic);

		// 设置分享视频
		//UMVideo umVideo = new UMVideo(
//		          "http://v.youku.com/v_show/id_XNTE5ODAwMDM2.html?f=19001023");
		// 设置视频缩略图
		//umVideo.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
		//umVideo.setTitle("友盟社会化分享!");
		//mController.setShareMedia(umVideo);
		
		mController.getConfig().removePlatform( SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
		mBtnShare.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        // 是否只有已登录用户才能打开分享选择页
		        mController.openShare(MainActivity.this, false);
		    }
		});
		
		
		//设置新浪SSO handler
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		
		
		/*****************微信平台和微信朋友圈****************/
		String appID = "wx967daebe835fbeac";
		String appSecret = "5fa9e68ca3970e87a1f83e563c8dcbce";
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(this,appID,appSecret);
		wxHandler.addToSocialSDK();
		// 添加微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(this,appID,appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
		/*****************微信平台和微信朋友圈结束****************/
		
		/*****************QQ好友分享****************/
		//参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, "100424468",
		                "c7394704798a158208a74ab60104f0ba");
		qqSsoHandler.addToSocialSDK();
		
		//参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this, "100424468",
		                "c7394704798a158208a74ab60104f0ba");
		qZoneSsoHandler.addToSocialSDK();
		
		/*****************QQ好友分享结束****************/
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
