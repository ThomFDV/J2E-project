package com.example.J2Eproject.gif;

import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@Data
@Accessors(chain = true)
public class Gif {
    @Id
    private ObjectId _id;
    private String url;
    private String name;
}
