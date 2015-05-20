package logo;

import com.example.trabus.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
public class Logo extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {    
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loadscreen);
		
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2000);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				finally {
				//	Intent secondAct = new Intent("com.example.trabus.MAINACTIVITY");  
					Intent secondAct = new Intent(Logo.this,com.example.trabus.MainActivity.class);
					startActivity(secondAct);  
				}
			}
		};
		timer.start();  
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
