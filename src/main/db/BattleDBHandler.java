package db;

import card.Card;
import card.Monstercard;
import card.Spellcard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BattleDBHandler {
    public void updateReadyForBattle(String username, boolean ready) {
        try {
            Connection conn = DBconnection.getInstance().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement("""
            UPDATE users SET ready_for_battle = ?
            WHERE username=?
            """);
            preparedStatement.setBoolean(1, ready);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateUserStats(String username, int won, int lost, int elo, int ratio) {
        try {
            Connection conn = DBconnection.getInstance().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement("""
            UPDATE users SET won = ?, lost = ?, elo = ?, lose_win_ratio = ?
            WHERE username=?
            """);
            preparedStatement.setInt(1, won);
            preparedStatement.setInt(2, lost);
            preparedStatement.setInt(3, elo);
            preparedStatement.setInt(4, ratio);
            preparedStatement.setString(5, username);
            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int selectUserWon(String username) {
        int value = -1;
        try {
            Connection conn = DBconnection.getInstance().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement("""
            SELECT won FROM users
            WHERE username=?
            """);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()) value = result.getInt(1);
            preparedStatement.close();
            conn.close();
            return value;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public int selectUserLost(String username) {
        int value = -1;
        try {
            Connection conn = DBconnection.getInstance().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement("""
            SELECT lost FROM users
            WHERE username=?
            """);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()) value = result.getInt(1);
            preparedStatement.close();
            conn.close();
            return value;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public String getOpponent() {
        String resultString = "";
        try {
            Connection conn = DBconnection.getInstance().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement("""
        SELECT username FROM users
        WHERE "ready_for_battle" = ?
        """);
            preparedStatement.setBoolean(1, true);
            ResultSet result = preparedStatement.executeQuery();
            // returns first found possible player
            if(result.next()) {
                resultString = result.getString(1);
            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultString;
    }

    public ArrayList<Card> getDeckAsCardObjects(String username) {
        ArrayList<Card> deckArray = new ArrayList<Card>();
        try {
            Connection conn = DBconnection.getInstance().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement("""
        SELECT * FROM cards
        WHERE "deck" = ? AND "user" = ?
        """);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setString(2, username);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                double damage = result.getDouble("damage");
                String element = result.getString("element");
                if (name.contains("Spell")){
                    deckArray.add(new Spellcard(id, name, damage, element));
                }else{
                    deckArray.add(new Monstercard(id, name, damage, element));
                }
            }
            preparedStatement.close();
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deckArray;
    }
}