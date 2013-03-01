package pl.qbasso.videorecorder;

import java.io.IOException;
import java.util.List;

import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * controller for android camera object
 * 
 * @author qba
 * 
 */
public class RecordingController {

	private SurfaceHolder mHolder;
	private MediaRecorder mRecorder;
	private Camera mCamera;
	private Point mPreviewSize;

	public RecordingController() {
		mRecorder = new MediaRecorder();
	}

	public void init(SurfaceHolder holder) {
		mCamera = Camera.open();
		try {
			if (mCamera != null) {
				mHolder = holder;
				mCamera.setPreviewDisplay(holder);
				Camera.Parameters p = mCamera.getParameters();
				mPreviewSize = findBiggestPreviewSize(p
						.getSupportedPreviewSizes());
				p.setPreviewSize(mPreviewSize.x, mPreviewSize.y);
				mCamera.setParameters(p);
				mCamera.startPreview();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startRecorder(OnInfoListener infoListener, int frameRate,
			int maxDuration, String fileName) {
		mRecorder.reset();
		mCamera.unlock();
		mRecorder.setOnInfoListener(infoListener);
		mRecorder.setCamera(mCamera);
		mRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
		mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
		mRecorder.setVideoFrameRate(frameRate);
		mRecorder.setVideoSize(mPreviewSize.x, mPreviewSize.y);
		mRecorder.setOutputFile(fileName);
		mRecorder.setPreviewDisplay(mHolder.getSurface());
		mRecorder.setMaxDuration(maxDuration);
		try {
			mRecorder.prepare();
			mRecorder.start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopRecorder() {
		mRecorder.stop();
		mRecorder.reset();
		mCamera.lock();
	}

	public void releaseRecorder() {
		mRecorder.release();
		mCamera.stopPreview();
		mCamera.release();
	}

	private Point findBiggestPreviewSize(List<Size> availablePreviewSizes) {
		int bestX = 0;
		int bestY = 0;
		for (Size size : availablePreviewSizes) {
			int newX = 0;
			int newY = 0;
			try {
				newX = size.width;
				newY = size.height;
			} catch (NumberFormatException nfe) {
				Log.e("error finding preview", "number format exception for:"
						+ newX + " " + newY);
				continue;
			}
			if (newX > bestX && newY > bestY) {
				bestX = newX;
				bestY = newY;
			}
		}
		if (bestX > 0 && bestY > 0) {
			return new Point(bestX, bestY);
		}
		return null;
	}

}
