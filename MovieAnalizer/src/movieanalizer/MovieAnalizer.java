/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieanalizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author cesarcaceres
 */
public class MovieAnalizer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        List<Movie> movieasArrayList;
        List<Movie> movieasLinkedList;

        //read the movie data
        movieasArrayList = readInMovies("movies.txt");
        
        printMovies(movieasArrayList);
        
        movieasLinkedList = listOpsTime(movieasArrayList);
        
        //years of movie releases
        printUniqueMovieYears(movieasLinkedList);
        
        Movie toFind = searchMoviesbyName(movieasLinkedList, "Mask, The");
        
        searchMoviesbyMovie(movieasLinkedList, toFind);
        
        Deque<Movie> movieQ = new ArrayDeque<>();
        Movie toWatch = movieasLinkedList.get(100);
        System.out.println("Adding " + toWatch.getName());
        addMovieToQueue(movieQ, toWatch);
        toWatch = movieasLinkedList.get(200);
        System.out.println("Adding " + toWatch.getName());
        addMovieToQueue(movieQ, toWatch);
        toWatch = movieasLinkedList.get(300);
        System.out.println("Adding " + toWatch.getName());
        addMovieToQueue(movieQ, toWatch);
        System.out.println("Size of movie queue is: " + movieQ.size());
        
        watchMoviesinQ(movieQ);
        
        List<Rating> listofRatings;
        listofRatings = readInRating("rating.txt");
        System.out.println("read in ratings: " + listofRatings.size());
        avgRatingofMovie(listofRatings, 100);

        Map<Movie, Double> mapMovieRatings = createMovieMap(movieasLinkedList, listofRatings);
        createCatMap(mapMovieRatings);       
 
    }//end of main 

    public static List<Movie> readInMovies(String fileName) {

        List<Movie> movieasArrayList = new ArrayList<>();
        String readFromFile;
        String[] lineFromFile;
        int movieID;
        String movieName;
        int movieYear;
        boolean gAction;
        boolean gAdventure;
        boolean gAnimation;
        boolean gChildren;
        boolean gComedy;
        boolean gCrime;
        boolean gDocumentary;
        boolean gDrama;
        boolean gFantasy;
        boolean gFilmNoir;
        boolean gHorror;
        boolean gMusical;
        boolean gMystery;
        boolean gRomance;
        boolean gSciFi;
        boolean gThriller;
        boolean gWar;
        boolean gWestern;

        Movie newMovie;

        try (BufferedReader inputBuff = new BufferedReader(new FileReader(fileName))) {
            while ((readFromFile = inputBuff.readLine()) != null) {
                lineFromFile = readFromFile.split("\t");

                movieID = Integer.parseInt(lineFromFile[0].trim());
                movieName = lineFromFile[1].trim();
                movieYear = Integer.parseInt(lineFromFile[2].trim());
                gAction = Integer.parseInt(lineFromFile[3].trim()) == 1;
                gAdventure = Integer.parseInt(lineFromFile[4].trim()) == 1;
                gAnimation = Integer.parseInt(lineFromFile[5].trim()) == 1;
                gChildren = Integer.parseInt(lineFromFile[6].trim()) == 1;
                gComedy = Integer.parseInt(lineFromFile[7].trim()) == 1;
                gCrime = Integer.parseInt(lineFromFile[8].trim()) == 1;
                gDocumentary = Integer.parseInt(lineFromFile[9].trim()) == 1;
                gDrama = Integer.parseInt(lineFromFile[10].trim()) == 1;
                gFantasy = Integer.parseInt(lineFromFile[11].trim()) == 1;
                gFilmNoir = Integer.parseInt(lineFromFile[12].trim()) == 1;
                gHorror = Integer.parseInt(lineFromFile[13].trim()) == 1;
                gMusical = Integer.parseInt(lineFromFile[14].trim()) == 1;
                gMystery = Integer.parseInt(lineFromFile[15].trim()) == 1;
                gRomance = Integer.parseInt(lineFromFile[16].trim()) == 1;
                gSciFi = Integer.parseInt(lineFromFile[17].trim()) == 1;
                gThriller = Integer.parseInt(lineFromFile[18].trim()) == 1;
                gWar = Integer.parseInt(lineFromFile[19].trim()) == 1;
                gWestern = Integer.parseInt(lineFromFile[20].trim()) == 1;

                newMovie = new Movie(movieID, movieName, movieYear, gAction,
                        gAdventure, gAnimation, gChildren, gComedy, gCrime,
                        gDocumentary, gDrama, gFantasy, gFilmNoir, gHorror, gMusical,
                        gMystery, gRomance, gSciFi, gThriller, gWar, gWestern);

                movieasArrayList.add(newMovie);
            }

        } catch (FileNotFoundException fnf) {
            System.err.println("FNF exception");
        } catch (IOException ioe) {
            System.err.println("IO exception");
        }

        return movieasArrayList;

    }//end of readInMovies

    public static List<Rating> readInRating(String fileName) {
        Rating aRating;
        List<Rating> listR = new ArrayList<>();
        String readFromFile;
        String[] lineFromFile;
        int movieID;
        int userID;
        float score;
        long timeStamp;

        try {
            BufferedReader inputBuff = new BufferedReader(new FileReader(fileName));
            while ((readFromFile = inputBuff.readLine()) != null) {
                lineFromFile = readFromFile.split("\t");

                userID = Integer.parseInt(lineFromFile[0].trim());
                movieID = Integer.parseInt(lineFromFile[1].trim());
                score = Float.parseFloat(lineFromFile[2].trim());
                timeStamp = Long.parseLong(lineFromFile[3].trim());

                aRating = new Rating(userID, movieID, score, timeStamp);
                listR.add(aRating);
            }
        } catch (FileNotFoundException fnf) {
            System.err.println("FNF exception");
        } catch (IOException ioe) {
            System.err.println("IO exception");
        }
        return listR;
    }//end of readInRating

    public static List<Movie> listOpsTime(List<Movie> movieasArrayList) {

        //convert ArrayList to LinkedList
        List<Movie> movieasLinkedList = new LinkedList<>(movieasArrayList);
        System.out.println("Number of movies read and stored in ArrayList = "
                + movieasArrayList.size());
        System.out.println("NUmber of movies read and re-stored in LinkedList "
                + "= " + movieasLinkedList.size());

        //random access
        long startTime = System.nanoTime();
        movieasArrayList.get(movieasArrayList.size() - 1);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("time for random access of ArrayList = " + elapsedTime);

        startTime = System.nanoTime();
        movieasLinkedList.get(movieasLinkedList.size() - 1);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("time for random access of LinkedList = " + elapsedTime);

        //insertion at the end
        Movie newMovie = new Movie(10000, "NewMovie", 2017);
        startTime = System.nanoTime();
        movieasArrayList.add(newMovie);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("time to add at the end of ArrayList = " + elapsedTime);

        startTime = System.nanoTime();
        movieasLinkedList.add(newMovie);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("time to add at the end of LinkedList = " + elapsedTime);

        //delete from the end
        startTime = System.nanoTime();
        movieasArrayList.remove(newMovie);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("time to remove from the end of ArrayList = " + elapsedTime);

        startTime = System.nanoTime();
        movieasLinkedList.remove(newMovie);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("time to remove from the end of LinkedList = " + elapsedTime);

        //insertion in the middle
        startTime = System.nanoTime();
        movieasArrayList.add(1600, newMovie);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("time to add to middle of ArrayList = " + elapsedTime);

        startTime = System.nanoTime();
        movieasLinkedList.add(1600, newMovie);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("time to add to middle of LinkedList = " + elapsedTime);

        //delete from middle
        startTime = System.nanoTime();
        movieasArrayList.remove(newMovie);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("time to remove from the middle of ArrayList = " + elapsedTime);

        startTime = System.nanoTime();
        movieasLinkedList.remove(newMovie);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("time to remove from the middle of LinkedList = " + elapsedTime);

        return movieasLinkedList;
    }// end of listOpsTime

    public static void printUniqueMovieYears(List<Movie> movieasLinkedList) {

        Movie aMovie;
        int year;
        List<Integer> allReleaseYearsList = new LinkedList<>();

        Iterator<Movie> itMLL = movieasLinkedList.iterator();
        while (itMLL.hasNext()) {
            aMovie = itMLL.next();
            year = aMovie.getYear();
            allReleaseYearsList.add(year);
        }
        System.out.println("The number of all release year entries = "
                + allReleaseYearsList.size());

        System.out.println("\nPrinting all years from List");
        //Print all years sorted
        //Collections.sort(allReleaseYearsList);
        Iterator<Integer> it = allReleaseYearsList.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }

        //convert to HashSet
        Set<Integer> setofMovieYears = new HashSet<>(allReleaseYearsList);
        it = setofMovieYears.iterator();
        System.out.println("printing unsorted years from HashSet");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }

        //convert to TreeSet
        SortedSet<Integer> treesetofMovieYears = new TreeSet<>(allReleaseYearsList);
        System.out.println("The number of unique release years = "
                + setofMovieYears.size());
        System.out.println("The movie years range from "
                + Collections.min(allReleaseYearsList) + " to " + Collections.max(allReleaseYearsList));

        System.out.println("\nPrinting unique years from treeset sorted in"
                + " ascending order");
        it = treesetofMovieYears.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }

        //Printing reverse. Convert to List first and then reverse.
        List<Integer> reversedsetofMovieYears = new ArrayList<>(treesetofMovieYears);
        Collections.reverse(reversedsetofMovieYears);

        System.out.println("Printing unique sorted years from reversed ArrayList ");
        it = reversedsetofMovieYears.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println("\n\n");
    }//end of printUniqueMovieYears

    public static void printMovies(List<Movie> movieasList) {
        int i = 0;
        while (i < movieasList.size()) {
            System.out.println("movie ID: " + movieasList.get(i).getID() + " and"
                    + " year " + movieasList.get(i).getYear() + " and name "
                    + movieasList.get(i).getName());
            i++;
        }
    }// end of printMovies

    public static Movie searchMoviesbyName(List<Movie> movieasLinkedList, String movieToFind) {
        Movie aMovie;
        String name;
        int index;
        Movie movieFound = null;
        List<String> listNames = new ArrayList<>();
        Iterator<Movie> itMLL = movieasLinkedList.iterator();
        while (itMLL.hasNext()) {
            aMovie = itMLL.next();
            name = aMovie.getName();
            listNames.add(name);
            if (name.equals(movieToFind)) {
                movieFound = aMovie;
                System.out.println(aMovie.getID() + "\t" + aMovie.getName()
                        + "\t" + aMovie.getYear());
            }
        }
        return movieFound;
    }//end of searchMoviesbyName

    public static void searchMoviesbyMovie(List<Movie> movieasLinkedList, Movie movieToFind) {
        System.out.println("Before sort ");
        Iterator<Movie> it = movieasLinkedList.iterator();
        while (it.hasNext()) {
            System.out.print(it.next().getID() + " ");
        }
        System.out.println("");
        Collections.sort(movieasLinkedList);

        System.out.println("After sort ");
        it = movieasLinkedList.iterator();
        while (it.hasNext()) {
            System.out.print(it.next().getID() + " ");
        }
        System.out.println("");
        int index = Collections.binarySearch(movieasLinkedList, movieToFind);
        if (index >= 0) {
            System.out.println("");
            System.out.println("movie name " + movieasLinkedList.get(index).getName()
                    + " was found at index " + index);
        }
        if (movieasLinkedList.contains(movieToFind)) {
            System.out.println("Yes, it contains the movie");
        }
    }//end of searchMoviesbyMovie

    public static void addMovieToQueue(Deque<Movie> movieasAD, Movie toWatch) {
        movieasAD.add(toWatch);
        System.out.println("Movies in Queue are: ");
        int count = 0;
        while (count <= movieasAD.size()) {
            System.out.println("Movie in list: " + movieasAD.peek().getName());
            count++;
        }
    }//end of addMovieToQueue
    
    public static void watchMoviesinQ(Deque<Movie> movieasAD){
        Movie toWatch = null;
        int count = 0;
        while(!movieasAD.isEmpty()){
            System.out.println("Watching movie: " + movieasAD.poll().getName());
        }
    }//end of watchMoviesinQ
    
    public static double avgRatingofMovie(List<Rating> newRatingList, int mID){
        double sumScore = 0;
        int countRating = 0;
        
        for(Rating r : newRatingList){
            if(r.getmID() == mID){
                countRating++;
                sumScore += r.getScore();
            }
        }System.out.println("The average rating is: " + (double)(sumScore / (double) countRating));
        return 10.0;
    }//end of avgRatingofMovie
    
    public static Map<Movie, Double> createMovieMap(List<Movie> movieasLinkedList, List<Rating> newRatingsList) {
        Map<Movie, Double> movieRatingMap = new HashMap<>();

        double sumScore = 0;
        int countRating = 0;
        double avgScore = 0;
        for (Movie m : movieasLinkedList) {
            sumScore = 0;
            countRating = 0;
            avgScore = 0;
            for (Rating r : newRatingsList) {
                if (r.getmID() == m.getID()) {
                    countRating++;
                    sumScore += r.getScore();
                }
            }
            avgScore = sumScore / (double) countRating;
            movieRatingMap.put(m, avgScore);
            // movieRatingMap.put(m, avgScore);
        }
        System.out.println("size of map : " + movieRatingMap.size());
        //System.out.println("avg rating of movie 100 = " + movieRatingMap.get(100));

        return movieRatingMap;
        
    }//end of createMovieMap

    public static void createCatMap(Map<Movie, Double> movieRatingMap) {
        Map<MovieGenreEnum, Double> catRatingMap = new HashMap<>();
        double sumAScore = 0;
        int countARating = 0;
        double avgAScore = 0;
        double sumCScore = 0;
        int countCRating = 0;
        double avgCScore = 0;
        double sumRScore = 0;
        int countRRating = 0;
        double avgRScore = 0;

        for (Movie m : movieRatingMap.keySet()) {

            if (m.getAction()) {
                countARating++;
                sumAScore += movieRatingMap.get(m);
            } else if (m.getComedy()) {
                countCRating++;
                sumCScore += movieRatingMap.get(m);
            } else if (m.getRomance()) {
                countRRating++;
                sumRScore += movieRatingMap.get(m);
            }
        }
        avgAScore = sumAScore / (double) countARating;
        avgCScore = sumCScore / (double) countCRating;
        avgRScore = sumRScore / (double) countRRating;

        System.out.println("average rating of action films: " + avgAScore + " from " + countARating + " ratings");
        System.out.println("average rating of comedy films: " + avgCScore + " from " + countCRating + " ratings");
        System.out.println("average rating of romance films: " + avgRScore + " from " + countRRating + " ratings");

    }//end of createCatMap

}
