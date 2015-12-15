package com.example.bajian.leaguesharplogindemo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String URL_LOGIN = "https://www.joduska.me/login";
    private static final String URL_INDEX = "https://www.joduska.me/forum/index.php?app=core&module=global&section=login&do=process";
    private static final String URL_FORUM = "https://www.joduska.me/forum/";
    private static final String UNAME = "Mcmillian";
    private static final String PWD = "6047012";
    private static final String LOGIN_SUCC = "You are now signed in";
    private static final String WebCer = "-----BEGIN CERTIFICATE-----\n" +
            "MIID9jCCA52gAwIBAgIRAO2WNmxosbGsdcT390sj7DEwCgYIKoZIzj0EAwIwgZIx\n" +
            "CzAJBgNVBAYTAkdCMRswGQYDVQQIExJHcmVhdGVyIE1hbmNoZXN0ZXIxEDAOBgNV\n" +
            "BAcTB1NhbGZvcmQxGjAYBgNVBAoTEUNPTU9ETyBDQSBMaW1pdGVkMTgwNgYDVQQD\n" +
            "Ey9DT01PRE8gRUNDIERvbWFpbiBWYWxpZGF0aW9uIFNlY3VyZSBTZXJ2ZXIgQ0Eg\n" +
            "MjAeFw0xNTEwMjUwMDAwMDBaFw0xNjEwMjMyMzU5NTlaMGwxITAfBgNVBAsTGERv\n" +
            "bWFpbiBDb250cm9sIFZhbGlkYXRlZDEhMB8GA1UECxMYUG9zaXRpdmVTU0wgTXVs\n" +
            "dGktRG9tYWluMSQwIgYDVQQDExtzc2wzMTM4NjUuY2xvdWRmbGFyZXNzbC5jb20w\n" +
            "WTATBgcqhkjOPQIBBggqhkjOPQMBBwNCAATqj0AWcsGbnEauSZw2glF3O2aHKTE5\n" +
            "sjlA5ScQ1gXbzMrJ/NLHnqsx9Bdx87mmHBJuSvNAnqiogxvRbDxNMWogo4IB9zCC\n" +
            "AfMwHwYDVR0jBBgwFoAUQAlhZ/C8g3FP3hIILG/U1Ct2PZYwHQYDVR0OBBYEFDwL\n" +
            "nyRHt7t2HWxzmIvE26+zC5A9MA4GA1UdDwEB/wQEAwIHgDAMBgNVHRMBAf8EAjAA\n" +
            "MB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEFBQcDAjBPBgNVHSAESDBGMDoGCysG\n" +
            "AQQBsjEBAgIHMCswKQYIKwYBBQUHAgEWHWh0dHBzOi8vc2VjdXJlLmNvbW9kby5j\n" +
            "b20vQ1BTMAgGBmeBDAECATBWBgNVHR8ETzBNMEugSaBHhkVodHRwOi8vY3JsLmNv\n" +
            "bW9kb2NhNC5jb20vQ09NT0RPRUNDRG9tYWluVmFsaWRhdGlvblNlY3VyZVNlcnZl\n" +
            "ckNBMi5jcmwwgYgGCCsGAQUFBwEBBHwwejBRBggrBgEFBQcwAoZFaHR0cDovL2Ny\n" +
            "dC5jb21vZG9jYTQuY29tL0NPTU9ET0VDQ0RvbWFpblZhbGlkYXRpb25TZWN1cmVT\n" +
            "ZXJ2ZXJDQTIuY3J0MCUGCCsGAQUFBzABhhlodHRwOi8vb2NzcC5jb21vZG9jYTQu\n" +
            "Y29tMEAGA1UdEQQ5MDeCG3NzbDMxMzg2NS5jbG91ZGZsYXJlc3NsLmNvbYIMKi5q\n" +
            "b2R1c2thLm1lggpqb2R1c2thLm1lMAoGCCqGSM49BAMCA0cAMEQCIHSYCCujIs4j\n" +
            "CF5P1x9ilzEZ63NfI7a3302Qk4Fkiw6KAiBBQO37BkupaNNWQU1UONDhFe535Vh1\n" +
            "zEVCGsREiw/C4A==\n" +
            "-----END CERTIFICATE-----";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        setSupportActionBar(toolbar);
/*
        OkHttpClientManager.getInstance()
                .setCertificates(new Buffer()
                        .writeUtf8(WebCer)
                        .inputStream());*/

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //没做处理，登录过一次就会有cookie，重复登录没做判断，会跳到主页去的
                getAuthKey();
            }
        });

    }
//auth_key=880ea6a14ea49e853634fbdc5015a024&
// referer=https%3A%2F%2Fwww.joduska.me%2Fforum%2F&rememberMe=1&ips_username=Mcmillian&ips_password=6047012
    private void getAuthKey() {
        new OkHttpRequest.Builder().url(URL_LOGIN+"?r="+Math.random()).get(new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                System.out.println("onError"+e.getMessage());
            }

            @Override
            public void onResponse(String response) {
//                System.out.println(response);
                String authKey=StringUtil.getStringMiddle(response,"<input type=\"hidden\" name=\"auth_key\" value=\"",
                        "\">");
                System.out.println("authKey"+authKey);
                login(authKey);

            }

        });
    }

    private void login(String authKey){
        Map<String, String> params = new HashMap<String, String>();
        params.put("auth_key", authKey);
        params.put("referer", "https%3A%2F%2Fwww.joduska.me%2Fforum%2F&rememberMe=1");
        params.put("rememberMe", "1");
        params.put("ips_username", UNAME);
        params.put("ips_password", PWD);
        new OkHttpRequest.Builder().url(URL_INDEX+"&r="+Math.random()).params(params).post(new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                System.out.println("onError"+e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                System.out.println(" login onResponse");
                System.out.println(response);
                if (response.contains(LOGIN_SUCC))
                    Toast.makeText(MainActivity.this,"login succ,please click testLogin btn", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"login failed", Toast.LENGTH_LONG).show();

            }


        });

    }

    public void testIsLogin(final View v){
        new OkHttpRequest.Builder().url(URL_FORUM).get(new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                System.out.println("onError"+e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                String name=StringUtil.getStringMiddle(response,"https://www.joduska.me/forum/user/","/");
                if (!"".equals(name) && name.contains(UNAME.toLowerCase()))
                    Snackbar.make(v,"login user is"+name,Snackbar.LENGTH_LONG).show();
                else
                    Snackbar.make(v,"not login",Snackbar.LENGTH_LONG).show();
            }
        });
    }
/*

    public void testIsLogin(final View v){
        new OkHttpRequest.Builder().url(URL_FORUM).get(new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
//                System.out.println(response);
                String name=StringUtil.getStringMiddle(response,"https://www.joduska.me/forum/user/","/");
                if (!"".equals(name) && name.contains(UNAME.toLowerCase()))
                    Snackbar.make(v,"login user is"+name,Snackbar.LENGTH_LONG).show();
                else
                    Snackbar.make(v,"not login",Snackbar.LENGTH_LONG).show();
            }
        });
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
