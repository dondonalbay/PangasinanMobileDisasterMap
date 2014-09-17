package edu.ucuccs.pangasinandisastermap;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class CameraHolder extends SurfaceView implements SurfaceHolder.Callback {
	Camera cam;
	SurfaceHolder sfh;
	
	
	public CameraHolder(Context context, Camera camera) {
		super(context);
		// TODO Auto-generated constructor stub
		
		cam=camera;
		sfh=getHolder();
		sfh.addCallback(this);
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		try {
			cam.setPreviewDisplay(holder);
			cam.startPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		cam.stopPreview();
	}

	
}
