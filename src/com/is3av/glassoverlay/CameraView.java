package com.is3av.glassoverlay;



import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback
{
	private SurfaceHolder surfaceHolder = null;
	private Camera camera = null;
	private Paint paint = new Paint();

	@SuppressWarnings("deprecation")
	public CameraView(Context context) 
	{
		super(context);

		surfaceHolder = this.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	/*
	 * (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		camera = Camera.open();

		// Set the Hotfix for Google Glass
		this.setCameraParameters(camera);

		// Show the Camera display
		try 
		{
			camera.setPreviewDisplay(holder);
		} 
		catch (Exception e) 
		{
			this.releaseCamera();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder, int, int, int)
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
	{
		// Start the preview for surfaceChanged
		if (camera != null)
		{
			camera.startPreview();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		// Do not hold the camera during surfaceDestroyed - view should be gone
		this.releaseCamera();
	}

	/**
	 * Important HotFix for Google Glass (post-XE11) update
	 * @param camera Object
	 */
	public void setCameraParameters(Camera camera)
	{
		if (camera != null)
		{
			Parameters parameters = camera.getParameters();
			parameters.setPreviewFpsRange(30000, 30000);
			camera.setParameters(parameters);	
		}
	}

	/**
	 * Release the camera from use
	 */
	public void releaseCamera() 
	{
		if (camera != null) 
		{
			camera.release();
			camera = null;
		}
	}
	@Override
	protected void onDraw(Canvas canvas) {
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics(); 
		int screenWidth = metrics.widthPixels ;
		int screenHeight = metrics.heightPixels;
		paint.setAntiAlias(true);
		paint.setStrokeWidth(3);  
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.RED);
		paint.setTextSize(36);
		int thirdline = (int) (screenHeight/1.3); //calculation to draw the third horizontal line

		//horizontal lines
		canvas.drawLine(0, screenHeight/4, screenWidth,screenHeight/4, paint);
		canvas.drawLine(0,(screenHeight)/2,screenWidth,(screenHeight)/2, paint);
		canvas.drawLine(0, thirdline, screenWidth, thirdline, paint);
		//canvas.drawLine(0,(screenHeight*2)/3,screenWidth,(screenHeight*2)/3, paint);
		//canvas.drawLine(startX, startY, stopX, stopY, paint);
		//vertical lines
		canvas.drawLine(screenWidth/3, 0, screenWidth/3, screenHeight, paint);
		canvas.drawLine((screenWidth*2)/3, 0, (screenWidth*2)/3, screenHeight, paint);

		//Calculation of coordinates to place numbers
		//x -> width
		int x1 = screenWidth/6;
		int x2 = screenWidth/2;
		int x3 = (int)(screenWidth/1.2);
		//y-> height
		int y1 = screenHeight/8;
		int y2 = (int)(screenHeight/2.6);
		int y3 = (int)(screenHeight/1.6);
		int y4 = (int)((screenHeight/1.1)-30);
		//set text in the corresponding grid box
//		canvas.drawText(numbers.get(0).toString(), x1, y1, paint);
//		canvas.drawText(numbers.get(1).toString(), x2,y1, paint);
//		canvas.drawText(numbers.get(2).toString(), x3,y1, paint);
//		canvas.drawText(numbers.get(3).toString(), x1,y2, paint);
//		canvas.drawText(numbers.get(4).toString(), x2,y2, paint);
//		canvas.drawText(numbers.get(5).toString(), x3,y2, paint);
//		canvas.drawText(numbers.get(6).toString(), x1,y3, paint);
//		canvas.drawText(numbers.get(7).toString(), x2,y3, paint);
//		canvas.drawText(numbers.get(8).toString(), x3,y3, paint);
//		canvas.drawText(numbers.get(9).toString(), x2,y4+20, paint); 

	} 
}