package com.kh.weather.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherController {
	
	private final String SERVICE_KEY = "서비스키";

	@ResponseBody
	@RequestMapping(value="weather.do", produces="application/json;charset=UTF-8")
	public String mediumTermForecast(String location) throws IOException {
		
		// 현재 날짜 및 시간에 따라 tmFc 값 설정
		String tmFc = getTmFc();
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICE_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode(location, "UTF-8")); /*11B10101 서울, 11B20201 인천 등 ( 별첨엑셀자료 참고)*/
        urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode("202410111800", "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력- YYYYMMDD0600(1800) 최근 24시간 자료만 제공*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        
        return sb.toString();
	}
		// 현재 날짜를 기반으로 tmFc 값 계산
		private String getTmFc() {
			// 현재 시각을 기준으로 06:00 또는 18:00 설정
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat hourFormat = new SimpleDateFormat("HH");

			String baseDate = dateFormat.format(now); // 오늘 날짜
			int currentHour = Integer.parseInt(hourFormat.format(now));
			
			// 현재 시간이 00:00에서 06:00 사이일 경우, 어제 날짜의 18:00 데이터를 사용
		    if (currentHour < 6) {
		        // 어제 날짜 계산
		        Calendar calendar = Calendar.getInstance();
		        calendar.setTime(now);
		        calendar.add(Calendar.DATE, -1); // 하루 전
		        baseDate = dateFormat.format(calendar.getTime()); // 어제 날짜
		        return baseDate + "1800"; // 어제 18시 기준
		    }

			// 현재 시간이 18시 이후면 그날 18:00, 아니면 06:00
			String baseTime = currentHour >= 18 ? "1800" : "0600";

			return baseDate + baseTime;
		
	}

}
