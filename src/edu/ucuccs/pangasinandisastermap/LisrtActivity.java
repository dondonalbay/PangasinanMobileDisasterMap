package edu.ucuccs.pangasinandisastermap;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LisrtActivity extends Activity {
ListView list_Disaster;
ArrayList<HashMap<String,String>> arr_hasp= new ArrayList<HashMap<String,String>>(); 
SimpleAdapter sim_adapter;
private String[] drawerListViewItems;
private DrawerLayout drawerLayout;
private ListView drawerListView;
private ActionBarDrawerToggle actionBarDrawerToggle;
@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lisrt);
		list_Disaster=(ListView)findViewById(R.id.list);
		HashMap<String,String> hash_map= new HashMap<String,String>();
		hash_map.put("header", "Reported");
		hash_map.put("report", "Description");
		arr_hasp.add(hash_map);
		hash_map.put("header", "Reported");
		hash_map.put("report", "Description");
		arr_hasp.add(hash_map);
		hash_map.put("header", "Reported");
		hash_map.put("report", "Description");
		arr_hasp.add(hash_map);
		hash_map.put("header", "Reported");
		hash_map.put("report", "Description");
		arr_hasp.add(hash_map);
		hash_map.put("header", "Reported");
		hash_map.put("report", "Description");
		arr_hasp.add(hash_map);
		hash_map.put("header", "Reported");
		hash_map.put("report", "Description");
		arr_hasp.add(hash_map);
		hash_map.put("header", "Reported");
		hash_map.put("report", "Description");
		arr_hasp.add(hash_map);
		hash_map.put("header", "Reported");
		hash_map.put("report", "Description");
		arr_hasp.add(hash_map);
		sim_adapter= new SimpleAdapter(this, arr_hasp,R.layout.row_list, new String[] {"header", "report"}, new int[]{R.id.txtheader, R.id.txtreport});
		list_Disaster.setAdapter(sim_adapter);
		
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
    		Intent intent= new Intent(LisrtActivity.this, MainActivity.class);
    		startActivity(intent);
    	}else if(position==1){
    		Intent intent= new Intent(LisrtActivity.this, CameraActivity.class);
    		startActivity(intent);
    	}else if(position==2){
    		Intent intent= new Intent(LisrtActivity.this, LisrtActivity.class);
    		startActivity(intent);
    	}

    }
}

	
}
