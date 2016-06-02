package com.akinola.alajo.API;

import android.os.AsyncTask;

import com.akinola.alajo.objects.Company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Akinola on 5/14/2016.
 * It is the base class that handles communicaton
 */
public class Alajo {
    private String url;
    private String authorization;
    private HashMap<String, String> postDataParams;

    private List<Company> companies;

    public Alajo() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public HashMap<String, String> getPostDataParams() {
        return postDataParams;
    }

    public void setPostDataParams(HashMap<String, String> postDataParams) {
        this.postDataParams = postDataParams;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String consumeGet() {
        String response = "";

        try {
            HttpURLConnection httpConnection = (HttpURLConnection) new URL(this.url).openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setReadTimeout(30000);
            httpConnection.setConnectTimeout(30000);

            httpConnection.setRequestProperty("Authorization", this.authorization);
            //httpConnection.setRequestProperty("Accept-Charset", charset);

            //getting the response code
            int responseCode = httpConnection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                //Reading the  Get response
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                response = "unauthorized";
            } else {
                response = "";

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return response;
    }

    public String consumePost() {
        String response = "";
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) new URL(this.url).openConnection();
            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(true); // Triggers POST.
            httpConnection.setRequestMethod("POST");
            httpConnection.setReadTimeout(30000);
            httpConnection.setConnectTimeout(30000);
            httpConnection.setRequestProperty("Authorization", this.authorization);

            //encoding the requests
            String hh = getPostDataString(this.postDataParams);
            //System.out.println(hh);

            //writing the requests
            OutputStream os = httpConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(hh);
            writer.flush();
            writer.close();
            os.close();

            //getting the response code
            int responseCode = httpConnection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {

                //Reading the response
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;

    }

    public String Login() {
        String response = "";

        return response;

    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }


    public class QueryLogin extends AsyncTask<Void, String, Boolean>{
        private String response="";

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
                httpConnection.setDoInput(true);
                httpConnection.setDoOutput(true); // Triggers POST.
                httpConnection.setRequestMethod("POST");
                httpConnection.setReadTimeout(30000);
                httpConnection.setConnectTimeout(30000);

                //encoding the requests
                String hh = getPostDataString(postDataParams);
                //System.out.println(hh);

                //writing the requests
                OutputStream os = httpConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(hh);
                writer.flush();
                writer.close();
                os.close();

                //getting the response code
                int responseCode = httpConnection.getResponseCode();
                //System.out.println("Response code: "+responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    //Reading the response
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else {
                    response = "";

                }

                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
