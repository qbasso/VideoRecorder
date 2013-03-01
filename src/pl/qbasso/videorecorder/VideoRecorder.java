package pl.qbasso.videorecorder;

import android.app.Activity;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

public class VideoRecorder extends Activity implements SurfaceHolder.Callback {

	private RecordingController mController;
	private SurfaceView mSurfaceView;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message m) {
		}
	};
	SurfaceView surface;
	private OnClickListener button1Listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
		}
	};
	private OnClickListener button2Listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
		}
	};
	private OnInfoListener infoListener = new OnInfoListener() {

		@Override
		public void onInfo(MediaRecorder mr, int what, int extra) {
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mController = new RecordingController();
		setContentView(R.layout.video_recorder);
		FrameLayout l = (FrameLayout) findViewById(R.id.screen_content);
		findViewById(R.id.button1).setOnClickListener(button1Listener);
		findViewById(R.id.button2).setOnClickListener(button2Listener);
		mSurfaceView = new SurfaceView(this);
		mSurfaceView.getHolder().setType(
				SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mSurfaceView.getHolder().addCallback(this);
		mSurfaceView.setLayoutParams(new FrameLayout.LayoutParams(1, 1));
		l.addView(mSurfaceView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video_recorder, menu);
		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mController.init(holder);
		mController.startRecorder(infoListener, 20, 5000, "");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}

}
