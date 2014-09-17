package edu.ucuccs.pangasinandisastermap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
Button btnsos;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
btnsos=(Button)findViewById(R.id.button4);
		registerForContextMenu(btnsos);
	}

	public void clickmap(View v) {
		Intent intent = new Intent(MainActivity.this, MapActivity.class);
		startActivity(intent);
	}

	public void clickcamera(View v) {
		Intent intent = new Intent(MainActivity.this, CameraActivity.class);
		startActivity(intent);
	}

	public void clickpeople(View v) {
		Intent intent = new Intent(MainActivity.this, LisrtActivity.class);
		startActivity(intent);
	}
	public void clicksos(View v) {
		openContextMenu(v);
	}
	
	//ContextMenu start

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Disaster Response Force");
		menu.add(0, v.getId(), 0, "PNP");
		menu.add(0, v.getId(), 0, "BFP");
		menu.add(0, v.getId(), 0, "Red Cross");
		
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "PNP") {
			
		} else if (item.getTitle() == "BFP") {
			
		}else if (item.getTitle() == "Red Cross") {
			
		} else {
			return false;
		}
		return true;
	}
	//ContextMenu end

}
