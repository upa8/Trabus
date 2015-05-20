package com.example.trabus;


import navme.StartPage;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button CurrentLoc,NavMe,Bus;
    //ShowCity,Bus,Friends ;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InitializeVariables();
		ActionOnButtonClick();
		
	}

	private void ActionOnButtonClick() {
		// TODO Auto-generated method stub
		CurrentLoc.setOnClickListener(new View.OnClickListener() {
         	 
            @Override
            public void onClick(View view) {
            	Intent intent = new Intent(MainActivity.this,maps.OpenMap.class);
            	startActivity(intent);      
            }
        });
		NavMe.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,navme.StartPage.class);
            	startActivity(intent);      
           	
			}
		});
		/*
		ShowCity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		*/
		Bus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,bus.BusWebview.class);
            	startActivity(intent);      
           	
				
			}
		});
		/*
       Friends.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		*/


		
	}

	private void InitializeVariables() {
		// TODO Auto-generated method stub
		CurrentLoc = (Button) findViewById(R.id.CurrentLoc) ;
		NavMe = (Button) findViewById(R.id.NavMe) ;
	//	ShowCity = (Button) findViewById(R.id.ShowCity) ;
		Bus = (Button) findViewById(R.id.Bus) ;
	//	Friends = (Button) findViewById(R.id.Friends) ;
		
	}
}
