package bus;

import java.util.ArrayList;

import com.example.trabus.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class BusActivtiy extends Activity{
	Button Search ;
	
	AutoCompleteTextView SourceView,DestinationView ;
	ArrayAdapter<String> AdapterForAutoCompleteListViewSource,AdapterForAutoCompleteListViewDestination;
//	ArrayList<String> PlacesToSearch ;
	static final String[] PlacesToSearch = new String[] { "SITABULDI","BHOLE PETROL PAMP",
		"TRAFIC PARCK","DHARAMPETH","SHANKAR NAGAR","GANDHI NAGAR","AMBAZARI T- POINT","SUBHASH NAGAR",
		"G.H. RAISONI VIDYANIKETAN","T-POINT","SIM TAKLI","YASHODA NAGAR",
		"GADGE NAGAR","HINGNA NAKA","BALAJI NAGAR",	"MAHINDRA COMPANY",	"CRPF GATE"	,"V. I. P. FACTORY",
		"IC CHOWK"	,"ELECTRONIC ZONE",	"RAJIV NAGAR","YCCE COLLEGE","WANADONGRI","MAHAJAN WADI",	
		"JINNING PRESS","HINGNA RAIPUR","HINGNA"
    };

	String Source,Destination;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bus);
	//	AddElementsInArrayList();
		InitializeVariables();
		SetAdapter();
		SelectTest();
		ActionOnButtonClick();
		
	}
/*
	private void AddElementsInArrayList() {
		// TODO Auto-generated method stub
		
		PlacesToSearch = new ArrayList<String>();
		PlacesToSearch.add("SITABULDI");
		PlacesToSearch.add("BHOLE PETROL PAMP");
		PlacesToSearch.add("TRAFIC PARCK");
		PlacesToSearch.add("HINGNA RAIPUR");
		PlacesToSearch.add("HINGNA");
	}
*/
	private void SelectTest() {
		// TODO Auto-generated method stub
		SourceView.setOnItemClickListener(new OnItemClickListener() {
		   
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
			       Source = (String)arg0.getItemAtPosition(arg2);
		//	       Source.replaceAll(" ", "%20");   
			       
				  
				
			}
		});
		DestinationView.setOnItemClickListener(new OnItemClickListener() {
			   
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Destination = (String)arg0.getItemAtPosition(arg2);
			//    Destination.replaceAll(" ", "%20");   
				  
				
			}
		});
		
	}

	private void SetAdapter() {
		// TODO Auto-generated method stub
		AdapterForAutoCompleteListViewSource = new ArrayAdapter<String>(BusActivtiy.this,
				android.R.layout.simple_dropdown_item_1line, PlacesToSearch);
		SourceView.setAdapter(AdapterForAutoCompleteListViewSource);
		//SourceView.getEditableText().toString();
		AdapterForAutoCompleteListViewDestination = new ArrayAdapter<String>(BusActivtiy.this,
				android.R.layout.simple_dropdown_item_1line, PlacesToSearch);
		DestinationView.setAdapter(AdapterForAutoCompleteListViewDestination);

		
	}

	private void ActionOnButtonClick() {
		// TODO Auto-generated method stub
		Search.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View view) {
            	Intent intent = new Intent(BusActivtiy.this,BusFetchData.class);
            	intent.putExtra("source",  Source );
            	intent.putExtra("destination", Destination );
            	
             	startActivity(intent);      
            }
        });
		
		
	}

	private void InitializeVariables() {
		// TODO Auto-generated method stub
		Search = (Button) findViewById(R.id.SearchBus);
		SourceView = (AutoCompleteTextView) findViewById(R.id.AutocompleteSource);
		DestinationView = (AutoCompleteTextView) findViewById(R.id.AutocompleteDestination);
	}


}
