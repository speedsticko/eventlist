package eventlist.etl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class JsonStreamLoader {

    private static final Type EVENT_LIST_TYPE = new TypeToken<List<EventRecord>>(){}.getType();

    public List<EventRecord> Load(InputStream jsonInputStream) {

        Gson gson = new Gson();
        JsonReader reader = null;
        
        try {
            reader = new JsonReader(new InputStreamReader(jsonInputStream, "UTF-8"));
            reader.setLenient(true);
            List<EventRecord> data = gson.fromJson(reader, EVENT_LIST_TYPE);
            return data;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
