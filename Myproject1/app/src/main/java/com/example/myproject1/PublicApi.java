package com.example.myproject1;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;


public class PublicApi {
    private static String authApiKey = "updbj%2BlRNiUy4C0QAPWo4RgnWH%2Bwfh3qm6lalt87wpSBQoiofuQl00IIn87lQ8hGmE6SFEKG%2FF9Z9c8kGlNazw%3D%3D"; // 공공데이터 사이트를 통해 발급받은 키
    public PublicApi() {
        try {
            apiParserSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MapPoint> apiParserSearch() throws Exception {
        URL url = new URL(getURLParam(null));

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        xpp.setInput(bis, "utf-8");

        String tag = null;
        int event_type = xpp.getEventType();

        ArrayList<MapPoint> mapPoint = new ArrayList<MapPoint>();

        String posx= null, posy=null;
        boolean bposy=false, bposx=false;

        while(event_type != XmlPullParser.END_DOCUMENT) {
            if (event_type == XmlPullParser.START_TAG) {
                tag = xpp.getName();
                if(tag.equals("latitude")) {
                    bposy = true;
                }
                if(tag.equals("longitude")){
                    bposx = true;
                }
            } else if (event_type == XmlPullParser.TEXT) {
                if(bposy == true) {
                    posy = xpp.getText();
                    bposy = false;
                }else if(bposx == true) {
                    posx = xpp.getText();
                    bposx = false;
                }
            }else if (event_type == XmlPullParser.END_TAG) {
                tag = xpp.getName();
                if (tag.equals("row")) {
                    MapPoint entity = new MapPoint();
                    entity.setLatitude(Double.valueOf(posy));
                    entity.setLongitude(Double.valueOf(posx));
                    mapPoint.add(entity);
                    System.out.println(mapPoint.size());
                }
            }
            event_type = xpp.next();
    }
System.out.println(mapPoint.size());

return mapPoint;
}

    private String getURLParam(String search) {
        String url = "http://openapi.jeonju.go.kr/rest/streetlamp/getStreetlamp?ServiceKey=" + authApiKey;
        return url;
    }
public static void main(String[] args) {
        new PublicApi();
        }
        }
