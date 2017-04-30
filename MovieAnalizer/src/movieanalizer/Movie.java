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
public class Movie extends Media implements Comparable<Movie>{
    
    private boolean isAction;
    private boolean isAdventure;
    private boolean isAnimation;
    private boolean isChildren;
    private boolean isComedy;
    private boolean isCrime;
    private boolean isDocumentary;
    private boolean isDrama;
    private boolean isFantasy;
    private boolean isFilmNoir;
    private boolean isHorror;
    private boolean isMusical;
    private boolean isMystery;
    private boolean isRomance;
    private boolean isSciFi;
    private boolean isThriller;
    private boolean isWar;
    private boolean isWestern;
    
    public Movie(int mID, String mName, int yearRelease){
        
        super(mID, mName, yearRelease);
    }
    
      public Movie(int mID, String mName, int yearRelease, boolean isAction,
              boolean isAdventure, boolean isAnimation, boolean isChildren, 
              boolean isComedy, boolean isCrime, boolean isDocumentary,
              boolean isDrama, boolean isFantasy, boolean isFilmNoir, 
              boolean isHorror, boolean isMusical, boolean isMystery, 
              boolean isRomance, boolean isSciFi, boolean isThriller, boolean 
              isWar, boolean isWestern){
        
        super(mID, mName, yearRelease);
        this.isAction = isAction;
        this.isAdventure = isAdventure;
        this.isAnimation = isAnimation;
        this.isChildren = isChildren;
        this.isComedy = isComedy;
        this.isCrime = isCrime;
        this.isDocumentary = isDocumentary;
        this.isDrama = isDrama;
        this.isFantasy = isFantasy;
        this.isFilmNoir = isFilmNoir;
        this.isHorror = isHorror;
        this.isMusical = isMusical;
        this.isMystery = isMystery;
        this.isRomance = isRomance;
        this.isSciFi = isSciFi;
        this.isThriller = isThriller;
        this.isWar = isWar;
        this.isWestern = isWestern;
        
    }

    @Override
    public int getID() {
        return mID;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public int getYear() {
        return yearRelease;
    }

    @Override
    
   //public int compareTo(Movie anotherMovie){
   //   return (this.mID - anotherMovie.getID());
   //}
    
    public int compareTo(Movie anotherMovie) {
        return((this.mName).compareTo(anotherMovie.getName()));        
    }
    
     public boolean getAction() {return isAction;}
     public boolean getAdventure() {return isAdventure;}
     public boolean getAnimation() {return isAnimation;}
     public boolean getChildren() {return isChildren;}
     public boolean getComedy() {return isComedy;}
     public boolean getCrime() {return isCrime;}
     public boolean getDocumentary() {return isDocumentary;}
     public boolean getDrama() {return isDrama;}
     public boolean getFantasy() {return isFantasy;}
     public boolean getFilmNoir() {return isFilmNoir;}
     public boolean getHorror() {return isHorror;}
     public boolean getMusical() {return isMusical;}
     public boolean getMystery() {return isMystery;}
     public boolean getRomance() {return isRomance;}
     public boolean getSciFi() {return isSciFi;}
     public boolean getThriller() {return isThriller;}
     public boolean getWar() {return isWar;}
     public boolean getWestern() {return isWestern;}
    
    
}
