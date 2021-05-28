package com.example.finalprojectdanielasamuil;


public class UrlMapping {

    public static final String API_PATH = "/api";
    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";
    public static final String ENTITY = "/{id}";

    public static final String FITNESS_CLASSES = API_PATH + "/fitness-classes";
    public static final String SUBSCRIPTIONS = API_PATH + "/subscriptions";

    public static final String LOYALTY_UPDATES = FITNESS_CLASSES + ENTITY + "/loyalty-updates";
}
