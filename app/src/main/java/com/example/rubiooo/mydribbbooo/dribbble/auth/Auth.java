package com.example.rubiooo.mydribbbooo.dribbble.auth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by Jimmy on 2018/2/15.
 */

public class Auth {

    public static final int REQ_CODE = 100;

    private static final String KEY_CODE = "code";
    private static final String KEY_CLIENT_ID = "client_id";
    private static final String KEY_CLIENT_SECRET = "client_secret";
    private static final String KEY_REDIRECT_URI = "redirect_uri";
    private static final String KEY_SCOPE = "scope";
    private static final String KEY_ACCESS_TOKEN = "access_token";

    //use yours
    private static final String CLIENT_ID = "3f04f9365871243d1ec179342a2f41414ee5f6f29684104f0bd72dda579d8d46";

    //use yours
    private static final String CLIENT_SECRET = "5ed35ed9d5de53dd9a596d37dee2ddde1d3eae227585c82298cf9e8ec34c0f00";

    //see http://developer.dribbble.com/v1/oauth/#scopes
    private static final String SCOPE = "public+write";

    private static final String URI_AUTHORIZE = "https://dribbble.com/oauth/authorize";
    private static final String URI_TOKEN = "https://dribbble.com/oauth/token";

    public static final String REDIRECT_URI = "http://www.dribbbo.com";

    private static String getUriAuthorizeUrl() {
        String url = Uri.parse(URI_AUTHORIZE)
                .buildUpon()
                .appendQueryParameter(KEY_CLIENT_ID, CLIENT_ID)
                .build()
                .toString();
        //fix encode issue
        url += "&" + KEY_REDIRECT_URI + "=" + REDIRECT_URI;
        url += "&" + KEY_SCOPE + "=" + SCOPE;

        return url;
    }

    private static String getTokenUrl (String authCode) {
        return Uri.parse(URI_TOKEN)
                .buildUpon()
                .appendQueryParameter(KEY_CLIENT_ID, CLIENT_ID)
                .appendQueryParameter(KEY_CLIENT_SECRET, CLIENT_SECRET)
                .appendQueryParameter(KEY_CODE, authCode)
                .appendQueryParameter(KEY_REDIRECT_URI, REDIRECT_URI)
                .build()
                .toString();
    }

    public static void openAuthActivity(@NonNull Activity activity) {
        Intent intent =  new Intent(activity, AuthActivity.class);
        intent.putExtra(AuthActivity.KEY_URL, getUriAuthorizeUrl());

        activity.startActivityForResult(intent, REQ_CODE);
    }

    public static String fetchAccessToken(String authCode) {

    }

}
