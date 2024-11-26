package com.mgbooking.client;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class InstantAdapter extends TypeAdapter<Instant> {
    private static final DateTimeFormatter FORMATTER=DateTimeFormatter.ISO_INSTANT;
    @Override
    public void write(JsonWriter out, Instant value) throws IOException {
    if(value==null){
        out.nullValue();
    }else{
        out.value(FORMATTER.format(value));
    }
    }

    @Override
    public Instant read(JsonReader in) throws IOException {
        if (in.peek().name().equals("NULL")) {
            in.nextNull(); // Handle null values
            return null;
        } else {
            String instantStr = in.nextString();
            return Instant.parse(instantStr);
        }
    }
}
