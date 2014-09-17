package edu.ucuccs.pangasinandisastermap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import edu.ucuccs.pangasinandisastermap.AlertDialogRadio.AlertPositiveListener;

public class CameraActivity extends Activity implements AlertPositiveListener {
	 FrameLayout holdpic;
	 CameraHolder camhold;
	 Camera localcam;
	 Button take;
	 private String[] drawerListViewItems;
	    private DrawerLayout drawerLayout;
	    private ListView drawerListView;
	    private ActionBarDrawerToggle actionBarDrawerToggle;
 @SuppressLint("NewApi") @Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		holdpic=(FrameLayout)findViewById(R.id.frame);
		take=(Button)findViewById(R.id.btnclick);
		localcam=getInstanceCamera();
		camhold= new CameraHolder(this, localcam);
		holdpic.addView(camhold);
		
		File mkedir= Environment.getExternalStorageDirectory();
		File nedir=new File(mkedir + "/don");
		nedir.mkdir();
		
		 //get list items from strings.xml
	        drawerListViewItems = getResources().getStringArray(R.array.items);
	        // get ListView defined in activity_main.xml
	        drawerListView = (ListView) findViewById(R.id.left_drawer);
	 
	        // Set the adapter for the list view
	        drawerListView.setAdapter(new ArrayAdapter<String>(this,
	                R.layout.drawer_listview_item, drawerListViewItems));
	 
	        // 2. App Icon 
	        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	 
	        // 2.1 create ActionBarDrawerToggle
	        actionBarDrawerToggle = new ActionBarDrawerToggle(
	                this,                  /* host Activity */
	                drawerLayout,         /* DrawerLayout object */
	                R.drawable.navilist,  /* nav drawer icon to replace 'Up' caret */
	                R.string.drawer_open,  /* "open drawer" description */
	                R.string.drawer_close  /* "close drawer" description */
	                );
	 
	        // 2.2 Set actionBarDrawerToggle as the DrawerListener
	        drawerLayout.setDrawerListener(actionBarDrawerToggle);
	 
	        // 2.3 enable and show "up" arrow
	        getActionBar().setDisplayHomeAsUpEnabled(true); 
	 
	        // just styling option
	        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
	 
	        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
		
	}
 @Override
 protected void onPostCreate(Bundle savedInstanceState) {
     super.onPostCreate(savedInstanceState);
     // Sync the toggle state after onRestoreInstanceState has occurred.
      actionBarDrawerToggle.syncState();
 }

 @Override
 public void onConfigurationChanged(Configuration newConfig) {
     super.onConfigurationChanged(newConfig);
     actionBarDrawerToggle.onConfigurationChanged(newConfig);
 }

 @Override
 public boolean onOptionsItemSelected(MenuItem item) {

      // call ActionBarDrawerToggle.onOptionsItemSelected(), if it returns true
     // then it has handled the app icon touch event

     if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
         return true;
     }
     return super.onOptionsItemSelected(item);
 }

 private class DrawerItemClickListener implements ListView.OnItemClickListener {
     @Override
     public void onItemClick(AdapterView parent, View view, int position, long id) {
    	 if(position==0){
     		Intent intent= new Intent(CameraActivity.this, MainActivity.class);
     		startActivity(intent);
     	}else if(position==1){
     		Intent intent= new Intent(CameraActivity.this, CameraActivity.class);
     		startActivity(intent);
     	}else if(position==2){
     		Intent intent= new Intent(CameraActivity.this, LisrtActivity.class);
     		startActivity(intent);
     	}

     }
 }
	        
	private PictureCallback callback= new PictureCallback(){

		@SuppressLint("SimpleDateFormat")
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			SimpleDateFormat datefilename = new SimpleDateFormat("yyyyMMdd_hhmmss");
	        String newfilename = datefilename.format(new Date()).replace(" ","");
			File f= new File(Environment.getExternalStorageDirectory() + "/don", "IMG_"+ newfilename+".jpg");
			FileOutputStream fos;
			try {
				fos= new FileOutputStream(f);
				fos.write(data);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	};	
	
	private final ShutterCallback shuttercallback= new ShutterCallback(){

		@Override
		public void onShutter() {
			// TODO Auto-generated method stub
			AudioManager mgr= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			mgr.playSoundEffect(AudioManager.FLAG_PLAY_SOUND);
			
			
		}
		
	};
	
	public void clickbtn(View v){
		localcam.takePicture(shuttercallback, null, callback);
		
	}
	

	public static Camera getInstanceCamera() {
		// TODO Auto-generated method stub
		Camera c=null;
		try{
			c=Camera.open();
		}catch(Exception e){
			
		}
		
		
		return c;
	}
	@Override
	public void onPositiveClick(int position) {
		// TODO Auto-generated method stub
		
	}
	

	
}
