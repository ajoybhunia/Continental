package org.tw.continental.model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hotels")
public class Hotel {
    @Id
    private Integer id;
    @NonNull
    private final String name;
    private final String city;

    public Hotel(int id, @NonNull String name, String city) {
        this.id = id;
        this.city = city;
        this.name = name;
    }
}
