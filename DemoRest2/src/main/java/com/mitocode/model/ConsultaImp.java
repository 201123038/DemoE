package com.mitocode.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mitocode.webhose.WebhoseIOClient;

public class ConsultaImp {

	
    WebhoseIOClient webhoseClient = WebhoseIOClient.getInstance("f18ac7c4-d25c-44fc-9ad6-ccaa83064c0a");
    Map<String, String> queries = new HashMap<String, String>();
    Map<String, String> resultados = new HashMap<String, String>();
 
    
    
    
    public Map<String, String> consultar(
    		boolean latino,
    		String text,
    		int sortby,
    		boolean news,
    		boolean blogs,
    		boolean discussions,
    		boolean reviews,
    		String language,
    		String country,
    		String location,
    		int category,
    		String date
    		) {
    	
    	//busqueda entre comillas o simple
    	String str="", strSearch="";
    	String[] texts = text.split(";");
    	strSearch+="(";
    	for (int l=0; l<texts.length;l++) {
    		String p=texts[l];
    		if(texts[l].contains(" "))	p="\""+texts[l]+"\"";
    			
    		if(l==0)    		strSearch+=p;
    		else	    		strSearch+=" OR "+p;
    	
    	}
    	strSearch+=") ";

    	str+=strSearch;
    	
    	String site="";
    	
    	//IF news is selected
    	boolean sites=false;
    	if(news==true) {			
        	site+="(";
    		site+="site_type:news";			sites=true;
    	}
    	if(blogs==true&&sites==true) {
    		site+=" OR site_type:blogs";		
    	}else if(blogs==true){
        	site+="(";
    		site+="site_type:blogs";		sites=true;	
    	}
    	if(discussions==true&&sites==true) {	
    		site+=" OR site_type:discussions";	
    	}else if(discussions==true) {
        	site+="(";
    		site+="site_type:discussions";	sites=true;
    	}
    	if(site.contains("("))    	site+=") ";
    	
    	str+=site;
    	
    	String lang="";
    	
    	//IF language changed valued
    	if(!language.equals("")) {
	    	lang+="(";
    		String[] languages = language.split(";");
	    	for (int lg=0; lg<languages.length;lg++) {
	    		if(lg==0)    		lang+="language:"+languages[lg];
	    		else	    		lang+=" OR language:"+languages[lg];
	    	}
	    	lang+=") ";
    	}
    	
    	str+=lang;
    	
    	String cts="";
    	//IF country changed valued
    	if(!country.equals("")) {
	    	cts+="(";
	    	String[] countries = country.split(";");
	    	for (int ct=0; ct<countries.length;ct++) {
	    		if(ct==0)    		cts+="thread.country:"+countries[ct];
	    		else	    		cts+=" OR thread.country:"+countries[ct];
	    	}
	    	cts+=") ";
    	}

    	str+=cts;
    	
    	String loc="";
    	//Add locations
    	if(!location.equals("")) {
	    	loc+="(";
	    	String[] locations = location.split(";");
	    	for (int l=0; l<locations.length;l++) {
	    		if(l==0)    		loc+="location:"+locations[l];
	    		else	    		loc+=" OR location:"+locations[l];
	    	}
	    	loc+=") ";
    	}    	
    	str+=loc;
    	//IF category changed valued
    	//	str+= site_category: value
    	//DATE*


    	if(sortby>0) {
    		switch(sortby){
    		case 1://crawl date
    			queries.put("sort", "crawled");
    			break;
    		case 2: //relevancy
    			queries.put("sort", "relevancy");
    			break;
    		case 3://Published
    			queries.put("sort", "published");
    			break;
    		case 4: //Thread Published
    			queries.put("sort", "thread.published");
    			break;
    		case 5://Facebook Likes
    			queries.put("sort", "social.facebook.likes");
    			break;
    		case 6://Facebook Shares
    			queries.put("sort", "social.facebook.shares");
    			break;
    		case 7://Facebook Comments Count
    			queries.put("sort", "social.facebook.comments");
    			break;
    		case 8://Google Plus Shares
    			queries.put("sort", "social.gplus.shares");
    			break;
    		case 9://Pinterest Shares
    			queries.put("sort", "social.pinterest.shares");
    			break;    		
    		case 10://Linkedin Shares
    			queries.put("sort", "social.linkedin.shares");
    			break;
    		case 11://Stumbledupon Shares
    			queries.put("sort", "social.stumbledupon.shares");
    			break;
    		case 12://VK Shares
    			queries.put("sort", "social.vk.shares");
    			break;
    		case 13://Replies Count
    			queries.put("sort", "replies_count");
    			break;
    		case 14://Participants Count
    			queries.put("sort", "participants_count");
    			break;
    		case 15://Spam Score
    			queries.put("sort", "spam_score");
    			break;
    		case 16://Performance Score
    			queries.put("sort", "performance_score");
    			break;
    		case 17://Domain Rank
    			queries.put("sort", "domain_rank");
    			break;
    		case 18://Post Order in Thread
    			queries.put("sort", "ord_in_thread");
    			break;
    		case 19://Rating
    			queries.put("sort", "rating");
    			break;    			
    		}
    	}
    	String str2="";
    	str2=strSearch+lang+cts;
    	

    	JsonElement result = null;
  	   	JsonElement result_reviews=null;
  	   	JsonArray postArray=null;
    
  	 try {
    	if(sites==true) {
        	System.out.println(str);
        	queries.put("q", str);
        	result = webhoseClient.query("filterWebData", queries);
    	    System.out.println(result.getAsJsonObject().get("totalResults"));
    	    postArray = result.getAsJsonObject().getAsJsonArray("posts");
        
    	    for(JsonElement o  : postArray) {
    	    	resultados.put((o.getAsJsonObject().get("title").toString().replace('\"', ' ')),(o.getAsJsonObject().get("url").toString()));
//    	        System.out.println(o.getAsJsonObject().get("title"));  // Print title	    
    	    }    		
    	}

	    if(reviews==true) {
	    	System.out.println(str2);
	    	queries.put("q", str2);
	    	result_reviews = webhoseClient.query("reviewFilter", queries);
		    System.out.println(result_reviews.getAsJsonObject().get("totalResults"));
    	    postArray = result_reviews.getAsJsonObject().getAsJsonArray("posts");
            
/*    	    for(JsonElement o  : postArray) {
    	    	resultados.put((o.getAsJsonObject().get("title").toString().replace('\"', ' ')),(o.getAsJsonObject().get("url").toString()));
    	        System.out.println(o.getAsJsonObject().get("title"));  // Print title	    
    	    }    		
	*/    }
//	    System.out.println("con reviews "+resultados.size());    	

	    
    } catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return resultados;
    }
/*	public void prueba() {
		
		 WebhoseIOClient webhoseClient = WebhoseIOClient.getInstance("f18ac7c4-d25c-44fc-9ad6-ccaa83064c0a");

		    Map<String, String> queries = new HashMap<String, String>();
		    queries.put("q", "(Guatemala) (site_type:news OR site_type:blogs OR site_type:discussions) (language:english OR language:spanish)");
		    queries.put("ts", "1551327959181");
		    queries.put("sort", "crawled");
		    
		// Fetch query result

		    JsonElement result;
			try {
				result = webhoseClient.query("filterWebContent", queries);
			    System.out.println(result.getAsJsonObject().get("totalResults"));     // Print posts count
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
*/
}
