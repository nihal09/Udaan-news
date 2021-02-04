package com.example.demo.controller;

import com.example.demo.dto.Article;
import com.example.demo.dto.NewsDTO;
import com.example.demo.dto.Source;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/news")
@CrossOrigin("*")
public class News {

    @GetMapping("/headlines")
    public ResponseEntity<String> getHeadline(@RequestParam String country) throws IOException, ParseException {

        URL obj = new URL("https://newsapi.org/v2/top-headlines?country=" + country + "&apiKey=54c1f290740748ee9ebecfb757183aac");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Gson g = new Gson();
            NewsDTO s1 = g.fromJson(response.toString(), NewsDTO.class);
            System.out.println(s1.getArticles());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            String dtoAsString = objectMapper.writeValueAsString(s1.getArticles());
            System.out.println(dtoAsString);
            /*
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response.toString());
            Iterator<String> keys = json.keySet().iterator();
            List<Article> s = new ArrayList<Article>();
            while (keys.hasNext()) {
                String key = keys.next();
                if (key.equals("articles")) {
                    // do something with jsonObject here
                    s = (List<Article>) json.get(key);
                }
            }
            System.out.println(s);

             */
            return ResponseEntity.ok(dtoAsString);
        } else {
            System.out.println("GET request not worked");
            return (ResponseEntity<String >) ResponseEntity.notFound();
        }

    }

    @GetMapping("/query")
    public ResponseEntity<List<Article>> getHeadlineByQuery(@RequestParam(required = true) String q) throws IOException, ParseException {
        URL obj = new URL("https://newsapi.org/v2/everything?q=" + q + "&apiKey=54c1f290740748ee9ebecfb757183aac");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response.toString());
            Iterator<String> keys = json.keySet().iterator();
            List<Article> s = new ArrayList<Article>();
            while (keys.hasNext()) {
                String key = keys.next();
                if (key.equals("articles")) {
                    // do something with jsonObject here
                    s = (List<Article>) json.get(key);
                }
            }
            System.out.println(s);
            return ResponseEntity.ok(s);
        } else {
            System.out.println("GET request not worked");
            return (ResponseEntity<List<Article>>) ResponseEntity.notFound();
        }
    }
    @GetMapping("/source")
    public ResponseEntity<List<Source>> getSources(@RequestParam String category) throws IOException, ParseException {
        URL obj = new URL("https://newsapi.org/v2/sources?category="+category+"&apiKey=54c1f290740748ee9ebecfb757183aac");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response.toString());
            Iterator<String> keys = json.keySet().iterator();
            List<Source> s = new ArrayList<Source>();
            while (keys.hasNext()) {
                String key = keys.next();
                if (key.equals("sources")) {
                    // do something with jsonObject here
                    s = (List<Source>) json.get(key);
                }
            }
            System.out.println(s);
            return ResponseEntity.ok(s);
        } else {
            System.out.println("GET request not worked");
            return (ResponseEntity<List<Source>>) ResponseEntity.notFound();
        }
    }
}

