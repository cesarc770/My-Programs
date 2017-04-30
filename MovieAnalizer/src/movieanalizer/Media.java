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
public abstract class Media implements MediaInterface {
    
    int mID;
    String mName;
    int yearRelease;
    
    public Media(int mID, String mName, int yearRelease){
        
        this.mID = mID;
        this.mName = mName;
        this.yearRelease = yearRelease;
        
    }
    
}
