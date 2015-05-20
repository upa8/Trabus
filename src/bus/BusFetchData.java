package bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.trabus.R;
import common.AlertDialogManager;
import common.ConnectionDetector;
import common.JSONParser;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class BusFetchData extends ListActivity  {
    String SourceFromBusActivity;
    String tag = "Log";
    String DestinationFromBusActivity;
    ConnectionDetector cd;
    AlertDialogManager alert ;
 	private ProgressDialog pDialog;
 	JSONParser jsonParser = new JSONParser();
 	JSONArray Bus_Info_Array = null;
 	ArrayList<HashMap<String, String>> Bus_Info_List;
 //	private static final String URL_BUS_INFO = "http://10.0.2.2/Trabus/BusFetchData.php";
	private static final String URL_BUS_INFO = "http://10.0.2.2/Trabus/fetch.php";
 	
 	private static final String TAG_BUS_INFORMATION_ARRAY = "BusInformationArray";
 	private static final String TAG_BUS_ID ="busid";
 	private static final String TAG_FROM_TIME = "fromtime";
 	private static final String TAG_TO_TIME = "totime";
 	private static final String TAG_ROUTE_NUM = "routenum";
 	
 	   
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bus_fetch_data);
		CheckConnection();
		InitializeVariables();
		GetDataFromBusActivity();
		new LoadAllBus().execute();
		DisplayListViewInformation();	
		
	}
	class LoadAllBus extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(BusFetchData.this);
			pDialog.setMessage("Loading  Info ");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting song json and parsing
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			// post album id, song id as GET parameters
		//	params.add(new BasicNameValuePair("source", SourceFromBusActivity));
		//	params.add(new BasicNameValuePair("destination", DestinationFromBusActivity));
			Log.d(tag,"Log Before Request  " + SourceFromBusActivity + "  "+DestinationFromBusActivity+" " );
			// getting JSON string from URL
			JSONObject json = jsonParser.makeHttpRequest(URL_BUS_INFO, "GET", params);

			// Check your log cat for JSON reponse
			Log.d("Bus info: ", json.toString());

			try {
				Bus_Info_Array = json.getJSONArray(TAG_BUS_INFORMATION_ARRAY);
				
				if (Bus_Info_Array != null) {
					// looping through All Category
					Log.d(tag,"This Is After Fetching Data");
					for (int i = 0; i < Bus_Info_Array.length(); i++) {
						JSONObject c = Bus_Info_Array.getJSONObject(i);

						String BusId = c.getString(TAG_BUS_ID);
						String FromTime = c.getString(TAG_FROM_TIME);
						String ToTime = c.getString(TAG_TO_TIME);
						String RouteNum = c.getString(TAG_ROUTE_NUM);
					    
						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_BUS_ID, BusId);
						map.put(TAG_FROM_TIME, FromTime);
						map.put(TAG_TO_TIME, ToTime);
						map.put(TAG_ROUTE_NUM, RouteNum);
						  
						// adding HashList to ArrayList
						Bus_Info_List.add(map);
			

			}
					}
				} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting song information
			pDialog.dismiss();
			
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					
					ListAdapter adapter = new SimpleAdapter(
							BusFetchData.this, Bus_Info_List,
							R.layout.bus_items_information, new String[] {
								//	TAG_ID,
									TAG_BUS_ID,
									TAG_FROM_TIME,
									TAG_TO_TIME,
									TAG_ROUTE_NUM
									
									//TAG_TOTAL_COMPANIES 
									}, new int[] {
								//	R.id.category_id, 
									R.id.BusId,
									R.id.FromTime,
									R.id.ToTime,
									R.id.RouteNum 
									});
					
					// updating listview
					setListAdapter(adapter);
			//setTitle(Company_Name);
				}
			});

		}

	}

	private void DisplayListViewInformation() {
		// TODO Auto-generated method stub
		ListView lv = getListView();
		lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				Intent i = new Intent(getApplicationContext(), FetchIndividualBusInfo.class);
				String BusId = ((TextView) view.findViewById(R.id.BusId)).getText().toString();				
				i.putExtra("BusId", BusId);
				startActivity(i);
			}
		});	
		
	}

	private void InitializeVariables() {
		// TODO Auto-generated method stub
		Bus_Info_List = new ArrayList<HashMap<String, String>>();
		
		
	}

	private void CheckConnection() {
		// TODO Auto-generated method stub
		cd = new ConnectionDetector(getApplicationContext());
		 
        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(BusFetchData.this, "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }
        
		
	}

	private void GetDataFromBusActivity() {
		// TODO Auto-generated method stub
		Intent i = getIntent();
		SourceFromBusActivity = i.getStringExtra("source");
		DestinationFromBusActivity = i.getStringExtra("destination");
	    
	}


}
