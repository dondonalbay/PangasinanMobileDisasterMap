package edu.ucuccs.pangasinandisastermap;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import edu.ucuccs.pangasinandisastermap.AlertDialogRadio.AlertPositiveListener;

public class MapActivity  extends Activity implements AlertPositiveListener {
GoogleMap pangaMap;
private static final LatLng pangasinanLoc = new LatLng(15.944110400000000000,
		 120.435763100000030000);
int position = 0;
private String[] drawerListViewItems;
private DrawerLayout drawerLayout;
private ListView drawerListView;

private ActionBarDrawerToggle actionBarDrawerToggle;
ActionBar actionBar = null;
ArrayList<HashMap<String,String>> arr_hasp= new ArrayList<HashMap<String,String>>(); 
@SuppressLint("NewApi") @Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		
        location();
        
        
        OnClickListener listener = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				/** Getting the fragment manager */
				
				FragmentManager manager = getFragmentManager();
				
				/** Instantiating the DialogFragment class */
				AlertDialogRadio alert = new AlertDialogRadio();
				
				/** Creating a bundle object to store the selected item's index */
				Bundle b  = new Bundle();
				
				/** Storing the selected item's index in the bundle object */
				b.putInt("position", position);
				
				/** Setting the bundle object to the dialog fragment object */
				alert.setArguments(b);
				
				/** Creating the dialog fragment object, which will in turn open the alert dialog window */
				alert.show(manager, "alert_dialog_radio");			
				
			}
		};      
        
        /** Getting the reference of the button from the main layout */
        Button btn = (Button) findViewById(R.id.setting);
        
        /** Setting a button click listener for the choose button */
        btn.setOnClickListener(listener);
        
        
        
        //get list items from strings.xml
        drawerListViewItems = getResources().getStringArray(R.array.items);
        // get ListView defined in activity_main.xml
        drawerListView = (ListView) findViewById(R.id.left_drawer);
        
        // Set the adapter for the list view
        
        
        // Set the adapter for the list view
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_listview_item, drawerListViewItems));
        
       
        
 
        // Set the adapter for the list view
        
 
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
	 
    /** Defining button click listener for the OK button of the alert dialog window */
    @Override
    public void onPositiveClick(int position) {
    	this.position = position;
    	
		if(listmaptype.code[this.position].equals("Normal")){
    		pangaMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    	
    	}else if(listmaptype.code[this.position].equals("Satellite")){
    		pangaMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    	}else if(listmaptype.code[this.position].equals("Hybrid")){
    		pangaMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    	}else if(listmaptype.code[this.position].equals("Terrain")){
    		pangaMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    	}
    	 	
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
        		Intent intent= new Intent(MapActivity.this, MainActivity.class);
        		startActivity(intent);
        	}else if(position==1){
         		Intent intent= new Intent(MapActivity.this, CameraActivity.class);
         		startActivity(intent);
         	}else if(position==2){
         		Intent intent= new Intent(MapActivity.this, LisrtActivity.class);
         		startActivity(intent);
         	}
        	
            
            drawerLayout.closeDrawer(drawerListView);
 
        }
    }
        
    

private void location(){
	pangaMap = ((MapFragment) getFragmentManager().findFragmentById(
			R.id.map)).getMap();
	pangaMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

	CameraPosition cam = new CameraPosition.Builder().target(pangasinanLoc)
			.zoom(9).bearing(50).tilt(30).build();
	pangaMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam));
	
	pangaMap.getUiSettings().setZoomControlsEnabled(false); 
	pangaMap.getUiSettings().setScrollGesturesEnabled(false);
}
public void clicklist(View v){
	Intent intent= new Intent(MapActivity.this, LisrtActivity.class);
	startActivity(intent);
}
public void clickcamera(View v){
	Intent intent= new Intent(MapActivity.this, CameraActivity.class);
	startActivity(intent);
}

public void clicksetting(View v){
	
}


	
}
