import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=6d234f428279e60b405fc4ab0b1f5e3c");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()){
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        model.setPressure(main.getDouble("pressure"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon(obj.getString("icon"));
            model.setMain(obj.getString("main"));
        }

        JSONObject wind = object.getJSONObject("wind");
        model.setSpeed(wind.getDouble("speed"));

        return "City: " + model.getName() + "\n" +
                "Temperature " + model.getTemp() + "C" + "\n" +
                "Humidity " + model.getHumidity() + "%" + "\n" +
                "Pressure " + model.getPressure() + "mm" + "\n" +
                "Main " + model.getMain() + "\n" +
                "Speed " + model.getSpeed() + "m/s" + "\n" +
                "http://openweathermap.org/img/w/" + model.getIcon() + ".png" + "\n";
    }
}
