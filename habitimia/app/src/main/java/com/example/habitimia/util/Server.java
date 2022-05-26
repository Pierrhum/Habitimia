package com.example.habitimia.util;

import android.os.StrictMode;
import android.widget.Toast;

import com.example.habitimia.data.model.AdventurerClass;
import com.example.habitimia.data.model.Daily;
import com.example.habitimia.data.model.Day;
import com.example.habitimia.data.model.Guild;
import com.example.habitimia.data.model.Message;
import com.example.habitimia.data.model.OwnerType;
import com.example.habitimia.data.model.Quest;
import com.example.habitimia.data.model.Repetition;
import com.example.habitimia.data.model.Statistics;
import com.example.habitimia.data.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static String IPAddress = "http://10.192.94.214:8080/";
    public static JSONObject sendRequest(String endpoint, String params) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        int duration = Toast.LENGTH_SHORT;
        HttpURLConnection urlConnection = null;
        URL url = null;
        try {
            url = new URL(IPAddress + endpoint
                    + "?" + params); // 10.0.2.2 10.192.94.54
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            urlConnection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setDoOutput(true);
        try {
            urlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        if (br == null)
            return null;
        String line = "";
        while (true) {
            try {
                if ((line = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(line + "\n");
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = sb.toString();
        JSONObject jsonData = null;
        try {
            jsonData = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public static JSONArray sendRequestForList(String endpoint, String params) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        int duration = Toast.LENGTH_SHORT;
        HttpURLConnection urlConnection = null;
        URL url = null;
        try {
            url = new URL(IPAddress + endpoint
                    + "?" + params); // 10.0.2.2 10.192.94.54
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            urlConnection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setDoOutput(true);
        try {
            urlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        if (br == null)
            return null;
        String line = "";
        while (true) {
            try {
                if ((line = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(line + "\n");
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = sb.toString();
        JSONArray jsonData = null;
        try {
            jsonData = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public static User login(String username, String password){
        String request_params = "username=" + username
                + "&" +
                "password=" +password;
        JSONObject response = Server.sendRequest("login", request_params);

        if (response == null){
            return null;
        }
        User user = new User(response);

        return user;
    }

    public static User register(String username, String email, String password, String avatar){
        String request_params = "username=" + username
                + "&" +
                "password=" +password
                + "&" +
                "email=" +email
                + "&" +
                "avatar=" +avatar
                ;
        JSONObject response = Server.sendRequest("register", request_params);

        if (response == null){
            return null;
        }
        User user = new User(response);

        return user;
    }

    public static User updateUserHP(User user, Long HP){
        String request_params = "userId=" + user.getId().toString()
                + "&" +
                "HP=" +HP;
        JSONObject response = Server.sendRequest("update-user", request_params);

        if (response == null){
            return null;
        }
        User updated_user = new User(response);

        return updated_user;
    }

    public static Quest createQuest(User user,Quest quest){
        String request_params ="ownerId=" + user.getId()
                + "&" +
                "ownerType=" + OwnerType.User
                + "&" +
                "name=" +quest.getName()
                + "&" +
                "details=" +quest.getDetails()
                + "&" +
                "difficulty=" +quest.getDifficulty()
                ;
        JSONObject response = Server.sendRequest("create-quest", request_params);

        if (response == null){
            return null;
        }
        Quest new_quest = new Quest(response);

        return new_quest;
    }

    public static Quest createQuest(Guild guild,Quest quest){
        String request_params ="ownerId=" + guild.getId()
                + "&" +
                "ownerType=" + OwnerType.Guild
                + "&" +
                "name=" +quest.getName()
                + "&" +
                "details=" +quest.getDetails()
                + "&" +
                "difficulty=" +quest.getDifficulty()
                ;
        JSONObject response = Server.sendRequest("create-quest", request_params);

        if (response == null){
            return null;
        }
        Quest new_quest = new Quest(response);

        return new_quest;
    }

    public static Quest updateQuest(Quest quest){
        String request_params = "questId=" + quest.getId();
        if (quest.getName() != null){
            request_params += "&" +
                    "name=" + quest.getName();
        }
        if (quest.getDetails() != null){
            request_params += "&" +
                    "details=" + quest.getDetails();
        }
        if (quest.getDifficulty() != null){
            request_params += "&" +
                    "difficulty=" + quest.getDifficulty();
        }
        JSONObject response = Server.sendRequest("update-quest", request_params);

        if (response == null){
            return null;
        }
        Quest new_quest = new Quest(response);

        return new_quest;
    }

    public static List<Quest> getQuests(User user){
        String request_params = "ownerId=" + user.getId()
                + "&" +
                "ownerType=" + OwnerType.User;                ;
        JSONArray response = Server.sendRequestForList("all-quests", request_params);

        if (response == null){
            return null;
        }
        List<Quest> quests = new ArrayList<>();
        try {
            for(int i = 0; i < response.length(); i++){
                JSONObject quest = response.getJSONObject(i);
                quests.add(new Quest(quest));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return quests;
    }

    public static List<Quest> getQuests(Guild guild){
        String request_params = "ownerId=" + guild.getId()
                + "&" +
                "ownerType=" + OwnerType.Guild;                ;
        JSONArray response = Server.sendRequestForList("all-quests", request_params);

        if (response == null){
            return null;
        }
        List<Quest> quests = new ArrayList<>();
        try {
            for(int i = 0; i < response.length(); i++){
                JSONObject quest = response.getJSONObject(i);
                quests.add(new Quest(quest));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return quests;
    }


    public static void deleteQuest(String questId){
        String request_params = "questId=" + questId;
        JSONObject response = Server.sendRequest("remove-quest", request_params);

        return ;
    }

    public static List<Daily> getAllDailies(User user){
        String request_params = "userId=" + user.getId();                ;
        JSONArray response = Server.sendRequestForList("all-dailies", request_params);

        if (response == null){
            return null;
        }
        List<Daily> dailies = new ArrayList<>();
        try {
            for(int i = 0; i < response.length(); i++){
                JSONObject daily = response.getJSONObject(i);
                dailies.add(new Daily(daily));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dailies;
    }

    public static List<Daily> getDailiesForDay(User user, Day day){
        String request_params = "userId=" + user.getId()
                                + "&" +
                                "day=" + day;;                ;
        JSONArray response = Server.sendRequestForList("all-dailies-for-day", request_params);

        if (response == null){
            return null;
        }
        List<Daily> dailies = new ArrayList<>();
        try {
            for(int i = 0; i < response.length(); i++){
                JSONObject daily = response.getJSONObject(i);
                dailies.add(new Daily(daily));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dailies;
    }

    public static Daily createDaily(User user, Daily daily, List<Day> days) {
        String request_params = "userId=" + user.getId();
        if (daily.getName() != null){
            request_params += "&" +
                    "name=" + daily.getName();
        }
        if (daily.getDetails() != null){
            request_params += "&" +
                    "details=" + daily.getDetails();
        }
        if (daily.getDifficulty() != null){
            request_params += "&" +
                    "difficulty=" + daily.getDifficulty();
        }
        request_params += "&" +
                "days=";
        if (days != null && days.size() > 0){

            for (Day day:days){
                request_params += day + ",";
            }
            request_params = request_params.substring(0, request_params.length() - 1);
        }

        JSONObject response = Server.sendRequest("create-daily", request_params);

        if (response == null){
            return null;
        }
        Daily new_daily = null;
        try {

            new_daily = new Daily(response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new_daily;

    }
    public static Daily updateDaily(Daily daily,
                                   List<Day> days){
        String request_params = "dailyId=" + daily.getId();
        if (daily.getName() != null){
            request_params += "&" +
                    "name=" + daily.getName();
        }
        if (daily.getDetails() != null){
            request_params += "&" +
                    "details=" + daily.getDetails();
        }
        if (daily.getDifficulty() != null){
            request_params += "&" +
                    "difficulty=" + daily.getDifficulty();
        }
        request_params += "&" +
                "days=";
        if (days != null && days.size() > 0){

            for (Day day:days){
                request_params += day + ",";
            }
            request_params = request_params.substring(0, request_params.length() - 1);
        }
        JSONObject response = null;
        try {
            response = Server.sendRequest("update-daily", request_params);
        }catch (Exception e){}


        if (response == null){
            return null;
        }
        Daily new_daily = null;
        try {

            new_daily = new Daily(response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new_daily;
    }

    public static void deleteDaily(String dailyId){
        String request_params = "dailyId=" + dailyId;
        JSONObject response = Server.sendRequest("remove-daily", request_params);

        return ;
    }

    public static List<User> getUsers(){

        JSONArray response = Server.sendRequestForList("all-users", "");

        if (response == null){
            return null;
        }

        List<User> users = new ArrayList<>();
        try {
            for(int i = 0; i < response.length(); i++){
                JSONObject user = response.getJSONObject(i);
                users.add(new User(user));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static List<User> getAllUsersForGuild(Guild guild){
        String request_params = "guildId=" + guild.getId();                ;
        JSONArray response = Server.sendRequestForList("all-guild-members", request_params);

        if (response == null){
            return null;
        }
        List<User> users = new ArrayList<>();
        try {
            for(int i = 0; i < response.length(); i++){
                JSONObject user = response.getJSONObject(i);
                users.add(new User(user));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static List<Message> getMessagesForPastWeek(Guild guild){
        String request_params = "guildId=" + guild.getId();                ;
        JSONArray response = Server.sendRequestForList("all-messages-for-past-week", request_params);

        if (response == null){
            return null;
        }
        List<Message> messages = new ArrayList<>();
        try {
            for(int i = 0; i < response.length(); i++){
                JSONObject message = response.getJSONObject(i);
                messages.add(new Message(message));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public static List<Message> getMessagesForPastDay(Guild guild){
        String request_params = "guildId=" + guild.getId();                ;
        JSONArray response = Server.sendRequestForList("all-messages-for-today", request_params);

        if (response == null){
            return null;
        }
        List<Message> messages = new ArrayList<>();
        try {
            for(int i = 0; i < response.length(); i++){
                JSONObject message = response.getJSONObject(i);
                messages.add(new Message(message));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public static Message getLastMessage(Guild guild){
        String request_params = "guildId=" + guild.getId();
        JSONObject response = Server.sendRequest("last-message", request_params);

        if (response == null){
            return null;
        }
        Message messages =new Message(response);

        return messages;
    }

    public static Message addMessage(Message m) {
        String request_params = "guildId=" + m.getUser().getGuild().getId()
                + "&" +
                "userId=" + m.getUser().getId()
                      +  "&" +
                "text=" + m.getText() ;
        JSONObject response = Server.sendRequest("add-message", request_params);

        if (response == null){
            return null;
        }
        Message message =new Message(response);

        return message;
    }
}
