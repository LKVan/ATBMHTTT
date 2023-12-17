//package api;
//
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.util.Scanner;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONValue;
//
//import contanst.MyAddress;
//import model.api.Date;
//import model.api.District;
//import model.api.Province;
//import model.api.Transport;
//import model.api.Ward;
//import support.DateTime;
//
//
//
//public class LogicticAPI {
//	
//	private final String email = "phuong@1234";
//	private final String password ="12345678";
//	
//	private static LogicticAPI instance;
//	
//	private String token_access;
//	
//	
//	private LogicticAPI() {
//		try {
//			token_access ="Bearer " + login(email, password);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	//Phuong thuc goi Api login vao tai khoan
//	private String login(String userName,String password) throws IOException {
//		URL url = new URL("http://140.238.54.136/api/auth/login");
//		String urlParameters  ="email="+email +"&password="+password;
//		byte[] postData    = urlParameters.getBytes(StandardCharsets.UTF_8);
//		int    postDataLength = postData.length;
//
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty( "charset", "utf-8");
//		conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
//		conn.setUseCaches( false );
//		conn.setDoOutput(true);
//		conn.setInstanceFollowRedirects( false );
//		
//		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//		wr.write( postData );
//		wr.flush();
//		wr.close();
//
//		conn.connect();
//		  String inline = "";
//
//		//Getting the response code
//		int responsecode = conn.getResponseCode();
//		
//		if (responsecode != 200) {
//		    throw new RuntimeException("HttpResponseCode: " + responsecode);
//		} else {
//		  
//		  
//		    Scanner scanner = new Scanner(conn.getInputStream());
//		  
//		   //Ghi du lieu tu json
//		    while (scanner.hasNext()) {
//		       inline += scanner.nextLine();
//		    }
//
//		    
//		    //Close the scanner
//		    scanner.close();
//
//
//		}
//		String access_token = "";
//		if(!inline.equals("")) {
//			Object obj = JSONValue.parse(inline);
//	        JSONObject jsonObject = (JSONObject) obj;
//	        //Lay accesstoken
//	       access_token = (String) jsonObject.get("access_token");
//		}
//		
//		
//		return access_token;
//	}
//	
//	//Phuong thuc lay ra tat ca cac tinh thanh pho theo API
//	
//	public JSONArray getAllProvince() throws IOException {
//		URL url = new URL("http://140.238.54.136/api/province");
//
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Authorization", token_access);
//		
//		
//		conn.connect();
//		String inline = "";
//
//		//Getting the response code
//		int responsecode = conn.getResponseCode();
//		
//		if (responsecode != 200) {
//		    throw new RuntimeException("HttpResponseCode: " + responsecode);
//		} else {
//		  
//		  
//		    Scanner scanner = new Scanner(conn.getInputStream());
//		  
//		   //Ghi du lieu tu file json vao chuoi
//		    while (scanner.hasNext()) {
//		       inline += scanner.nextLine();
//		    }
//
//		    
//		    //Close the scanner
//		    scanner.close();
//
//		}
//		JSONArray result = null;
//		if(!inline.equals("")) {
//			Object obj = JSONValue.parse(inline);
//			JSONObject jsonObject = (JSONObject) obj;
//			JSONObject convert = (JSONObject) jsonObject.get("original");
//	        result=  (JSONArray)convert.get("data");
//		}
//		
//		
//		return result;
//	}
//	
//	
//	//Phuong thuc lay ra tat ca cac huyen theo ID cua tinh/thanh pho
//	public JSONArray getAllDitristOfProvinceByID(String id) throws IOException {
//		URL url = new URL("http://140.238.54.136/api/district?provinceID="+id);
//
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Authorization", token_access);
//		
//		conn.connect();
//		 String inline = "";
//
//		//Getting the response code
//		int responsecode = conn.getResponseCode();
//		
//		if (responsecode != 200) {
//		    throw new RuntimeException("HttpResponseCode: " + responsecode);
//		} else {
//		  
//		  
//		    Scanner scanner = new Scanner(conn.getInputStream());
//		  
//		   //Write all the JSON data into a string using a scanner
//		    while (scanner.hasNext()) {
//		       inline += scanner.nextLine();
//		    }
//
//		    
//		    //Close the scanner
//		    scanner.close();
//
////		    //Using the JSON simple library parse the string into a json object
////		    JSONParser parse = new JSONParser(inline);
//
//		    //Get the required data using its key
//		}
//		JSONArray result = null;
//		if(!inline.equals("")) {
//			Object obj = JSONValue.parse(inline);
//			JSONObject jsonObject = (JSONObject) obj;
//			JSONObject convert = (JSONObject) jsonObject.get("original");
//	        result=  (JSONArray)convert.get("data");
//		}
//		
//		
//		return result;
//	}
//	
//	//Phuong thuc goi API lay ra cac xa
//	public JSONArray getAllWardOfDistristByID(String id) throws IOException {
//		URL url = new URL("http://140.238.54.136/api/ward?districtID="+id);
//
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Authorization", token_access);
//		
//		conn.connect();
//		 String inline = "";
//
//		//Getting the response code
//		int responsecode = conn.getResponseCode();
//		
//		if (responsecode != 200) {
//		    throw new RuntimeException("HttpResponseCode: " + responsecode);
//		} else {
//		  
//		  
//		    Scanner scanner = new Scanner(conn.getInputStream());
//		  
//		   //Write all the JSON data into a string using a scanner
//		    while (scanner.hasNext()) {
//		       inline += scanner.nextLine();
//		    }
//
//		    
//		    //Close the scanner
//		    scanner.close();
//
////		    //Using the JSON simple library parse the string into a json object
////		    JSONParser parse = new JSONParser(inline);
//
//		    //Get the required data using its key
//		}
//		JSONArray result = null;
//		if(!inline.equals("")) {
//			Object obj = JSONValue.parse(inline);
//			JSONObject jsonObject = (JSONObject) obj;
//			JSONObject convert = (JSONObject) jsonObject.get("original");
//	        result=  (JSONArray)convert.get("data");
//		}
//		
//		
//		return result;
//	}
//	
//	//Phuong thuc tra ve Thong tin tinh theo ten
//	
//	public Province getProvinceByName(String name) throws IOException {
//		JSONArray array = getAllProvince();
//		Province province = null;
//		System.out.println(array.size());
//		
//		for (Object object : array) {
//			JSONObject o = (JSONObject)object;
//			System.out.println(o.toJSONString());
//			if(name.contains(o.get("ProvinceName").toString())) {
//				province = new Province(o.get("ProvinceID").toString(), o.get("ProvinceName").toString());
//			}
//		}
//		return province;
//	}
//	//Phuong thuc lay ra ten Huyen theo Ten va id cua thanh pho
//	public District getDistrictByName(String name,String province_id) throws IOException {
//		JSONArray array = getAllDitristOfProvinceByID(province_id);
//		District district = null;
//		System.out.println(array.size());
//		
//		for (Object object : array) {
//			JSONObject o = (JSONObject) object;
//			if(name.contains(o.get("DistrictName").toString())) {
//				district = new District(o.get("DistrictID").toString(), o.get("DistrictName").toString(),o.get("ProvinceID").toString());
//			}
//		}
//		return district;
//	}
//	
//	//Phuong thuc lay ra xa theo ten va id cua huyen
//	public Ward getWardByName(String name,String district_id) throws IOException {
//		JSONArray array = getAllWardOfDistristByID(district_id);
//		Ward ward = null;
//		System.out.println(array.size());
//		
//		for (Object object : array) {
//			JSONObject o = (JSONObject) object;
//			if(name.contains(o.get("WardName").toString()) || o.get("WardName").toString().contains(name)) {
//				ward = new Ward(o.get("WardCode").toString(), o.get("WardName").toString(),o.get("DistrictID").toString());
//			}
//		}
//		return ward;
//	}
//	
//	
//	//Phuong thuc tra ve ngay giao du kien
//	
//	public Date getDateSend(String from_district_id,String form_ward_id,String to_district_id,String to_ward_id,int height,int length,int width,int weigth ) throws IOException {
//		Date date = null;
//		
//		String urlParameters  = "from_district_id="+from_district_id+"&from_ward_id="+form_ward_id+"&to_district_id=" +to_district_id
//				+"&to_ward_id="+to_ward_id+"&height="+height+"&length="+height+"&width="+width+"&weight="+weigth;
//		
//		//Method Post phai gui du lieu theo tung byte
//		byte[] postData    = urlParameters.getBytes(StandardCharsets.UTF_8);
//		int    postDataLength = postData.length;
//		
//		URL url = new URL("http://140.238.54.136/api/leadTime");
//
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
//		conn.setRequestProperty( "charset", "utf-8");
//		conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
//		conn.setRequestProperty("Authorization", token_access);
//		conn.setUseCaches( false );
//		conn.setDoOutput(true);
//		conn.setInstanceFollowRedirects( false );
//		
////		conn.getOutputStream().write(postData);
//		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//		wr.write( postData );
//		wr.flush();
//		wr.close();
//
//		
//		conn.connect();
//		String inline = "";
//		 
//
//
//		//Getting the response code
//		int responsecode = conn.getResponseCode();
//		
//		if (responsecode != 200) {
//		    throw new RuntimeException("HttpResponseCode: " + responsecode);
//		} else {
//		  
//		  
//		    Scanner scanner = new Scanner(conn.getInputStream());
//		  
//		   //Write all the JSON data into a string using a scanner
//		    while (scanner.hasNext()) {
//		       inline += scanner.nextLine();
//		    }
//
//		    
//		    //Close the scanner
//		    scanner.close();
//
////		    //Using the JSON simple library parse the string into a json object
////		    JSONParser parse = new JSONParser(inline);
//
//		    //Get the required data using its key
//		}
//		if(!inline.equals("")) {
//			Object obj = JSONValue.parse(inline);
//			JSONObject jsonObject = (JSONObject) obj;
//			JSONArray convert = (JSONArray) jsonObject.get("data");
//			for (Object object : convert) {
//				JSONObject o = (JSONObject) object;
//				date = DateTime.formatDate(o.get("formattedDate").toString());
//			}
//		}		
//		
//		return date;
//		
//		
//	}
//	
//	//Phuong thuc lay tien theo uoc tinh ngay giao
//	public int getPrice(String from_district_id,String form_ward_id,String to_district_id,String to_ward_id,int height,int length,int width,int weigth ) throws IOException {
//		int price = 0;
//		
//		String urlParameters  = "from_district_id="+from_district_id+"&from_ward_id="+form_ward_id+"&to_district_id=" +to_district_id
//				+"&to_ward_id="+to_ward_id+"&height="+height+"&length="+length+"&width="+width+"&weight="+weigth;
//		byte[] postData    = urlParameters.getBytes(StandardCharsets.UTF_8);
//		int    postDataLength = postData.length;
//		
//		URL url = new URL("http://140.238.54.136/api/calculateFee");
//
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
//		conn.setRequestProperty( "charset", "utf-8");
//		conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
//		conn.setRequestProperty("Authorization", token_access);
//		conn.setUseCaches( false );
//		conn.setDoOutput(true);
//		conn.setInstanceFollowRedirects( false );
//		
//		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//		wr.write( postData );
//		wr.flush();
//		wr.close();
//
//		
//		conn.connect();
//		String inline = "";
//		 
//
//
//		//Getting the response code
//		int responsecode = conn.getResponseCode();
//		
//		if (responsecode != 200) {
//		    throw new RuntimeException("HttpResponseCode: " + responsecode);
//		} else {
//		  
//		  
//		    Scanner scanner = new Scanner(conn.getInputStream());
//		  
//		    while (scanner.hasNext()) {
//		       inline += scanner.nextLine();
//		    }
//
//		    
//		    scanner.close();
//
//		}
//		if(!inline.equals("")) {
//			Object obj = JSONValue.parse(inline);
//			JSONObject jsonObject = (JSONObject) obj;
//
//
//			if(jsonObject == null){
//				return  0;
//			}else {
//				JSONArray convert = (JSONArray) jsonObject.get("data");
//				for (Object object : convert) {
//					JSONObject o = (JSONObject) object;
//					price = Integer.parseInt(o.get("service_fee").toString());
//				}
//			}
//
//		}		
//		
//		return price;
//		
//		
//	}
//	
//	//Phuong thuc dang ki giao hang
//	
//	public Transport registerTransport(String from_district_id,String form_ward_id,String to_district_id,String to_ward_id,int height,int length,int width,int weigth ) throws IOException {
//	
//		Transport transport = null;
//		String urlParameters  = "from_district_id="+from_district_id+"&from_ward_id="+form_ward_id+"&to_district_id=" +to_district_id
//				+"&to_ward_id="+to_ward_id+"&height="+height+"&length="+length+"&width="+width+"&weight="+weigth;
//		byte[] postData    = urlParameters.getBytes(StandardCharsets.UTF_8);
//		int    postDataLength = postData.length;
//		
//		URL url = new URL("http://140.238.54.136/api/registerTransport");
//
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
//		conn.setRequestProperty( "charset", "utf-8");
//		conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
//		conn.setRequestProperty("Authorization", token_access);
//		conn.setUseCaches( false );
//		conn.setDoOutput(true);
//		conn.setInstanceFollowRedirects( false );
//		
//		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//		wr.write( postData );
//		wr.flush();
//		wr.close();
//
//		
//		conn.connect();
//		String inline = "";
//		 
//
//
//		//Getting the response code
//		int responsecode = conn.getResponseCode();
//		
//		if (responsecode != 200 && responsecode != 201) {
//		    throw new RuntimeException("HttpResponseCode: " + responsecode);
//		} else {
//		  
//		  
//		    Scanner scanner = new Scanner(conn.getInputStream());
//		  
//		    while (scanner.hasNext()) {
//		       inline += scanner.nextLine();
//		    }
//
//		    
//		    scanner.close();
//
//		}
//		if(!inline.equals("")) {
//			Object obj = JSONValue.parse(inline);
//			JSONObject jsonObject = (JSONObject) obj;
//			if(jsonObject == null){
//				Object newObj =  JSONValue.parse(inline.split("#54")[1].substring(7));
//				jsonObject = (JSONObject) newObj;
//				if(jsonObject == null){
//					return null;
//				}else {
//					JSONObject convert = (JSONObject) jsonObject.get("Transport");
//
////			int status = Integer.parseInt(convert.get("active").toString());
//					int status = convert.get("active").toString().equals("true") ? 1:0;
//					transport = new Transport(convert.get("id").toString(), Integer.parseInt(convert.get("fee").toString()), DateTime.formatDate(convert.get("created_at").toString()), DateTime.formatDate(convert.get("updated_at").toString()));
//					transport.setActive(status == 1 ? true :false);
//				}
//			}else {
//				JSONObject convert = (JSONObject) jsonObject.get("Transport");
//
////			int status = Integer.parseInt(convert.get("active").toString());
//				int status = convert.get("active").toString().equals("true") ? 1:0;
//				transport = new Transport(convert.get("id").toString(), Integer.parseInt(convert.get("fee").toString()), DateTime.formatDate(convert.get("created_at").toString()), DateTime.formatDate(convert.get("updated_at").toString()));
//				transport.setActive(status == 1 ? true :false);
//			}
//		}
//		return transport;
//			
//	}
//
//
//	public static LogicticAPI getInstance() {
//		if(instance == null) {
//			instance = new LogicticAPI();
//		}
//		return instance;
//	}
//	
//
//	
//	public static void main(String[] args) throws IOException {
////		System.out.println(login("phuong@1234", "12345678"));
//		
//		LogicticAPI logic = LogicticAPI.getInstance();
//		
////		System.out.println(logic.getAllProvince().toJSONString());
////		
////		System.out.println(logic.getProvinceByName("Tỉnh Bến Tre"));
//		
////		System.out.println(logic.getAllDitristOfProvinceByID("201").toJSONString());
//		System.out.println(logic.getDistrictByName("Huyện Chợ Lách", "213"));
//		
////		System.out.println(logic.getAllWardOfDistristByID("3158"));
////		System.out.println(logic.getPrice("2264", "90816", "2270", "231013", 1, 100, 100, 100));
////		System.out.println(logic.registerTransport("2264", "90816", "2270", "231013", 10, 100, 100, 100));
//		
////		System.out.println("Ward:" +logic.getWardByName(MyAddress.WARD, "3158").getName());
//
//	}
//	
//
//}
