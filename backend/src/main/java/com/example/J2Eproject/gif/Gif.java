package com.example.J2Eproject.gif;

import com.example.J2Eproject.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Gif {
    private String url;
    private String name;
    private List<User> users;
}
