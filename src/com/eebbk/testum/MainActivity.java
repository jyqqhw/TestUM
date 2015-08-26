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
	
	// ����������Activity��������³�Ա����
	final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
	private Button mBtnShare;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mBtnShare = (Button) findViewById(R.id.btn_share);
		
		// ����������Activity��������³�Ա����
		final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
		// ���÷�������
		mController.setShareContent("This is a share function test!");
		// ���÷���ͼƬ, ����2ΪͼƬ��url��ַ
//		mController.setShareMedia(new UMImage(this, 
//		                                      "http://www.umeng.com/images/pic/banner_module_social.png"));
		// ���÷���ͼƬ������2Ϊ����ͼƬ����Դ����
//		mController.setShareMedia(new UMImage(this, R.drawable.ic_launcher));
		// ���÷���ͼƬ������2Ϊ����ͼƬ��·��(����·��)
		//mController.setShareMedia(new UMImage(getActivity(), 
//		                                BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));

		// ���÷�������
		//UMusic uMusic = new UMusic("http://sns.whalecloud.com/test_music.mp3");
		//uMusic.setAuthor("GuGu");
		//uMusic.setTitle("����֮��");
		// ������������ͼ
		//uMusic.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
		//mController.setShareMedia(uMusic);

		// ���÷�����Ƶ
		//UMVideo umVideo = new UMVideo(
//		          "http://v.youku.com/v_show/id_XNTE5ODAwMDM2.html?f=19001023");
		// ������Ƶ����ͼ
		//umVideo.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
		//umVideo.setTitle("������ữ����!");
		//mController.setShareMedia(umVideo);
		
		mController.getConfig().removePlatform( SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
		mBtnShare.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        // �Ƿ�ֻ���ѵ�¼�û����ܴ򿪷���ѡ��ҳ
		        mController.openShare(MainActivity.this, false);
		    }
		});
		
		
		//��������SSO handler
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		
		
		/*****************΢��ƽ̨��΢������Ȧ****************/
		String appID = "wx967daebe835fbeac";
		String appSecret = "5fa9e68ca3970e87a1f83e563c8dcbce";
		// ���΢��ƽ̨
		UMWXHandler wxHandler = new UMWXHandler(this,appID,appSecret);
		wxHandler.addToSocialSDK();
		// ���΢������Ȧ
		UMWXHandler wxCircleHandler = new UMWXHandler(this,appID,appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
		/*****************΢��ƽ̨��΢������Ȧ����****************/
		
		/*****************QQ���ѷ���****************/
		//����1Ϊ��ǰActivity������2Ϊ��������QQ���������APP ID������3Ϊ��������QQ���������APP kEY.
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, "100424468",
		                "c7394704798a158208a74ab60104f0ba");
		qqSsoHandler.addToSocialSDK();
		
		//����1Ϊ��ǰActivity������2Ϊ��������QQ���������APP ID������3Ϊ��������QQ���������APP kEY.
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this, "100424468",
		                "c7394704798a158208a74ab60104f0ba");
		qZoneSsoHandler.addToSocialSDK();
		
		/*****************QQ���ѷ������****************/
		
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
