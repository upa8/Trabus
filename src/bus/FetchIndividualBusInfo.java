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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class FetchIndividualBusInfo extends ListActivity{
	String tag;
	ConnectionDetector cd;
    AlertDialogManager alert ;
 	private ProgressDialog pDialog;
 	JSONParser jsonParser = new JSONParser();
 	JSONArray Individual_Bus_Info_Array = null;
 	ArrayList<HashMap<String, String>> Individual_Bus_Info_List;
 	String BusIdFromBusActivity;
 //	private static final String URL_BUS_INFO = "http://10.0.2.2/Trabus/BusFetchData.php";
	private static final String URL_BUS_INFO = "http://10.0.2.2/Trabus/IndividualBusInfoFetch.php";
 	
 	private static final String TAG_BUS_INFORMATION_ARRAY = "businfoarray";
 	private static final String TAG_BUS_ID ="busid";
 	private static final String TAG_ESTIMATED_TIME = "estime";
 	private static final String TAG_CURRENT_TIME = "currenttime";
 	private static final String TAG_PLACE_ID = "placeid";
 		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.individual_bus_listview);
		CheckConnection();
		InitializeVariables();
		GetDataFromFetchBusActivity();
		new LoadIndividualBusInfo().execute();
		DisplayListViewInformation();	
		

 		
 		}
 		class LoadIndividualBusInfo extends AsyncTask<String, String, String> {

 			/**
 			 * Before starting background thread Show Progress Dialog
 			 * */
 			@Override
 			protected void onPreExecute() {
 				super.onPreExecute();
 				pDialog = new ProgressDialog(FetchIndividualBusInfo.this);
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
 				
 				params.add(new BasicNameValuePair("busid", BusIdFromBusActivity));
 				JSONObject json = jsonParser.makeHttpRequest(URL_BUS_INFO, "GET", params);

 				// Check your log cat for JSON reponse
 				Log.d("Bus info: ", json.toString());

 				try {
 					Individual_Bus_Info_Array = json.getJSONArray(TAG_BUS_INFORMATION_ARRAY);
 					
 					if (Individual_Bus_Info_Array != null) {
 						// looping through All Category
 						Log.d(tag,"This Is After Fetching Data");
 						for (int i = 0; i < Individual_Bus_Info_Array.length(); i++) {
 							JSONObject c = Individual_Bus_Info_Array.getJSONObject(i);

 							String BusId = c.getString(TAG_BUS_ID);
 							String EstimatedTime = c.getString(TAG_ESTIMATED_TIME);
 							String CurrentTime = c.getString(TAG_CURRENT_TIME);
 							String PlaceId = c.getString(TAG_PLACE_ID);
 						    
 							// creating new HashMap
 							HashMap<String, String> map = new HashMap<String, String>();

 							// adding each child node to HashMap key => value
 							map.put(TAG_BUS_ID, BusId);
 							map.put(TAG_ESTIMATED_TIME, EstimatedTime);
 							map.put(TAG_CURRENT_TIME, CurrentTime);
 							map.put(TAG_PLACE_ID, PlaceId);
 							  
 							// adding HashList to ArrayList
 							Individual_Bus_Info_List.add(map);
 				

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
 								FetchIndividualBusInfo.this, Individual_Bus_Info_List,
 								R.layout.individual_bus_info_items, new String[] {
 									//	TAG_ID,
 										TAG_BUS_ID,
 										TAG_ESTIMATED_TIME,
 										TAG_CURRENT_TIME,
 										TAG_PLACE_ID
 										
 										//TAG_TOTAL_COMPANIES 
 										}, new int[] {
 									//	R.id.category_id, 
 										R.id.IndividualBusId,
 										R.id.EstimatedTime,
 										R.id.CurrentTime,
 										R.id.PlaceId 
 										});
 						
 						// updating listview
 						setListAdapter(adapter);
 					}
 				});

 			}

 		}

 		
		private void DisplayListViewInformation() {
			// TODO Auto-generated method stub
			ListView lv = getListView();

			
		}
		private void GetDataFromFetchBusActivity() {
			// TODO Auto-generated method stub
			Intent i = getIntent();
			BusIdFromBusActivity = i.getStringExtra("BusId");
			
		}
		private void InitializeVariables() {
			// TODO Auto-generated method stub
			Individual_Bus_Info_List = new ArrayList<HashMap<String, String>>();
			
			
		}
		private void CheckConnection() {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			cd = new ConnectionDetector(getApplicationContext());
			 
	        // Check if Internet present
	        if (!cd.isConnectingToInternet()) {
	            // Internet Connection is not present
	            alert.showAlertDialog(FetchIndividualBusInfo.this, "Internet Connection Error",
	                    "Please connect to working Internet connection", false);
	            // stop executing code by return
	            return;
	        }
	        
			
		}
 }
