package com.vynilbox.song;

import com.vynilbox.abstracts.DAO;
import com.vynilbox.exceptions.NoResultsException;
import com.vynilbox.user.User;

import java.sql.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * This class intend to be used as the song Data Access Object,
 * i.e., it will make the process of doing queries into the database and
 * returning it results.
 *
 * @author Gabriel Brum e Mario Lucio
 * @version 1.0
 * @since 1.0
 */
public class SongDAO extends DAO {

    /**
     * Constructor that receives the connection and establish it
     * to allows the database communication
     * @param connection database connection
     */
    public SongDAO(Connection connection) {
        super(connection);
    }

    /**
     * Get all songs registered into the table 'songs'.
     *
     * @return ArrayList with all the songs found
     */
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songArrayList = new ArrayList<>();

        String selectCommand = "SELECT * FROM songs;";

        try {
            Statement sSelect = connection.createStatement();
            ResultSet querySelect = sSelect.executeQuery(selectCommand);

            while(querySelect.next()) {
                String[] genres = querySelect.getString(4).split("#");
                int[] genresInt = new int[genres.length];
                for(int i=0;i<genres.length;i++) {
                    genresInt[i] = parseInt(genres[i]);
                }

                String[] artists = querySelect.getString(5).split("#");
                int[] artistsInt = new int[artists.length];
                for(int i=0;i<genres.length;i++) {
                    artistsInt[i] = parseInt(artists[i]);
                }
                songArrayList.add(new Song(
                        querySelect.getInt(1),
                        querySelect.getString(2),
                        querySelect.getString(3),
                        querySelect.getString(4),
                        artistsInt
                ));
            }
            return songArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        throw new NullPointerException();
    }

    /**
     * Get all songs name and put it into
     * a string array.
     * @return string array with the name of all songs
     */
    public String[] getAllSongsToString(){
        ArrayList<Song> songList = getAllSongs();
        String[] songArray = new String[songList.size()];

        for(int i=0;i<songList.size();i++) {
            songArray[i] = songList.get(i).getName();
        }

        return songArray;
    }

    /**
     * Get all the favorite songs of the user.
     * @param user User object used into the search
     * @return ArrayList with all favorite songs of the user
     */
    public ArrayList<Song> getAllSongs(User user) {
        ArrayList<Song> songsList = new ArrayList<>();

        for(int id : user.getFavSongs()) {
            for(Song song : getAllSongs()) {
                if(id == song.getId())
                    songsList.add(song);
            }
        }

        return songsList;
    }

    /**
     * Get the name of the artists of some specific song.
     * @param song Song that desires to get artists.
     * @return ArrayList with the desired artists.
     */
    public ArrayList<String> getArtistsNames(Song song) {
        ArrayList<Integer> idList = (ArrayList) song.getArtists();
        ArrayList<String> artistsList = new ArrayList<>();

        for(int i=0 ; i<idList.size() ; i++) {
            String getArtistsCommand = "SELECT name FROM artists WHERE id = "+ idList.get(i) +";";
            try {
                Statement sSelectArtist = connection.createStatement();
                ResultSet querySelect = sSelectArtist.executeQuery(getArtistsCommand);

                while(querySelect.next()) {
                    artistsList.add(querySelect.getString(1));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return artistsList;
    }

    /**
     * Get a song by its id.
     * It uses the method to 'getAllSongs'.
     * @param id Song id.
     * @return Song object which has the id.
     * @throws NoResultsException In case of not having a song with the id searched.
     */
    public Song getSongById(int id) throws NoResultsException {
        for(Song song : getAllSongs()) {
            if(id == song.getId())
                return song;
        }
        throw new NoResultsException();
    }

    /**
     * Get the song by its name or, at least, the beginning of it.
     * @param sentence Search sentence.
     * @return Song object.
     * @throws NoResultsException In case of there isn't a result.
     */
    public Song getSongByName(String sentence) throws NoResultsException{
        String getSongCommand = "SELECT * FROM songs WHERE name LIKE '"+ sentence +"%' ;";

        try {
            Statement sGetSong = connection.createStatement();
            ResultSet queryGetSong = sGetSong.executeQuery(getSongCommand);

            if(queryGetSong.next()) {
                return getSongById(queryGetSong.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        throw new NoResultsException();
    }

    /**
     * Update the song determined by the id with the data
     * passed as parameter.
     * @param songRegisterData Dataset with new song data.
     * @param id Song id.
     */
    public void alterSong(SongRegisterData songRegisterData, int id) {
        String alterCommand = "UPDATE `vynilbox_database`.`songs` SET " +
                "`name` = '"+ songRegisterData.name() +"', " +
                "`description` = '"+ songRegisterData.description() +"'" +
                "WHERE `id` = "+ id +";";

        try {
            PreparedStatement psAlterSong = connection.prepareStatement(alterCommand);
            psAlterSong.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
