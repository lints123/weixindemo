package com.example.weixindemo.comment.weatherClient;

import java.util.List;

public class WeatherInfo {

    public String getWeatherInfo(String cityName){
        /*实例化工厂WeatherWS  创建实例WeatherWSSoap  调用实例的方法getWeather()*/
        WeatherWS weatherWS = new WeatherWS();
        WeatherWSSoap weatherWSSoap = weatherWS.getWeatherWSSoap();

        /*响应信息*/
        StringBuffer sb = new StringBuffer();

        /*获取指定城市的天气预报*/
        ArrayOfString weatherInfo = weatherWSSoap.getWeather(cityName,null);
        List<String> listWeatherInfo = weatherInfo.getString();
        for(String str :  listWeatherInfo){
            if(!str.contains(".gif")){
                sb.append(str);
                sb.append("\n");
                System.out.println(str);
                System.out.println("------------------------");
            }
        }
        return sb.toString();
    }

}
