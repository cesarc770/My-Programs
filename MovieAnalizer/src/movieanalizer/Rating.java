/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieanalizer;

/**
 *
 * @author cesarcaceres
 */
public class Rating {
    
    private int mID;
    private int uID;
    double score;
    long stamp;
    
    public Rating(int userID, int movieID, double mScore, long time){
        mID = movieID;
        uID = userID;
        score = mScore;
        stamp = time;        
    }
    
    public int getmID(){
        return mID;
    }
    
    public double getScore(){
        return score;
    }
    
    public int getuID(){
        return uID;
    }
    
}
