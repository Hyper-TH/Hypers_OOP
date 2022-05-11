package c20361521;

import ddf.minim.*;
import ddf.minim.analysis.*;

import ie.tudublin.*;
import processing.core.PApplet.*;
 
public class ExhibitB extends Visual 
{
    MyVisuals mv;

    public ExhibitB(MyVisuals mv)
    {
        this.mv = mv;
    }


    // Variables qui définissent les "zones" du spectre
    // Par exemple, pour les basses, on prend seulement les premières 4% du spectre total
    float specLow = (float) 0.03; // 3%
    float specMid = (float) 0.125;  // 12.5%
    float specHi = (float) 0.20;   // 20%

    // Il reste donc 64% du spectre possible qui ne sera pas utilisé. 
    // Ces valeurs sont généralement trop hautes pour l'oreille humaine de toute facon.

    // Valeurs de score pour chaque zone
    float scoreLow = 0;
    float scoreMid = 0;
    float scoreHi = 0;

    // Valeur précédentes, pour adoucir la reduction
    float oldScoreLow = scoreLow;
    float oldScoreMid = scoreMid;
    float oldScoreHi = scoreHi;

    // Valeur d'adoucissement
    float scoreDecreaseRate = 25;

    // Cubes qui apparaissent dans l'espace
    int nbCubes;
    Cube[] cubes;

    //Lignes qui apparaissent sur les cotés
    int nbMurs = 500;
    Mur[] murs;
    
    public void setup()
    {
        //Un cube par bande de fréquence
        nbCubes = (int)(fft.specSize()*specHi);
        cubes = new Cube[nbCubes];
        
        //Autant de murs qu'on veux
        murs = new Mur[nbMurs];

        //Créer tous les objets
        //Créer les objets cubes
        for (int z = 0; z < nbCubes; z++) 
        {
            cubes[z] = new Cube(); 
        }
        
        //Créer les objets murs
        //Murs gauches
        for (int a = 0; a < nbMurs; a += 4) 
        {
            murs[a] = new Mur(0, mv.height/2, 10, mv.height); 
        }
        
        //Murs droits
        for (int b = 1; b < nbMurs; b += 4) 
        {
            murs[b] = new Mur(mv.width, mv.height/2, 10, mv.height); 
        }
        
        //Murs bas
        for (int c = 2; c < nbMurs; c += 4) 
        {
            murs[c] = new Mur(mv.width/2, mv.height, mv.width, 10); 
        }
        
        //Murs haut
        for (int d = 3; d < nbMurs; d += 4) 
        {
            murs[d] = new Mur(mv.width/2, 0, mv.width, 10); 
        }
    } // end setup()
    
    void render()
    {
        //Faire avancer la chanson. On draw() pour chaque "frame" de la chanson...
        // fft.forward(ab.mix);
        fft.forward(ab);
        
        //Calcul des "scores" (puissance) pour trois catégories de son
        //D'abord, sauvgarder les anciennes valeurs
        oldScoreLow = scoreLow;
        oldScoreMid = scoreMid;
        oldScoreHi = scoreHi;
        
        //Réinitialiser les valeurs
        scoreLow = 0;
        scoreMid = 0;
        scoreHi = 0;
        
        //Calculer les nouveaux "scores"
        for(int e = 0; e < fft.specSize()*specLow; e++)
        {
            scoreLow += fft.getBand(e);
        }
        
        for(int f = (int)(fft.specSize()*specLow); f < fft.specSize()*specMid; f++)
        {
            scoreMid += fft.getBand(f);
        }
        
        for(int g = (int)(fft.specSize()*specMid); g < fft.specSize()*specHi; g++)
        {
            scoreHi += fft.getBand(g);
        }
        
        //Faire ralentir la descente.
        if (oldScoreLow > scoreLow) 
        {
            scoreLow = oldScoreLow - scoreDecreaseRate;
        }
        
        if (oldScoreMid > scoreMid) 
        {
            scoreMid = oldScoreMid - scoreDecreaseRate;
        }
        
        if (oldScoreHi > scoreHi) 
        {
            scoreHi = oldScoreHi - scoreDecreaseRate;
        }
        
        //Volume pour toutes les fréquences à ce moment, avec les sons plus haut plus importants.
        //Cela permet à l'animation d'aller plus vite pour les sons plus aigus, qu'on remarque plus
        float scoreGlobal = (float) (0.66 * scoreLow + 0.8 * scoreMid + 1 * scoreHi);
        
        //Couleur subtile de background
        // background(scoreLow/100, scoreMid/100, scoreHi/100);
        
        //Cube pour chaque bande de fréquence
        for(int h = 0; h < nbCubes; h++)
        {
            //Valeur de la bande de fréquence
            float bandValue = fft.getBand(h);
            
            //La couleur est représentée ainsi: rouge pour les basses, vert pour les sons moyens et bleu pour les hautes. 
            //L'opacité est déterminée par le volume de la bande et le volume global.
            cubes[h].display(scoreLow, scoreMid, scoreHi, bandValue, scoreGlobal);
        }
        
        //Murs lignes, ici il faut garder la valeur de la bande précédent et la suivante pour les connecter ensemble
        float previousBandValue = fft.getBand(0);
        
        //Distance entre chaque point de ligne, négatif car sur la dimension z
        float dist = -25;
        
        //Multiplier la hauteur par cette constante
        float heightMult = 2;
        
        //Pour chaque bande
        for(int j = 1; j < fft.specSize(); j++)
        {
            //Valeur de la bande de fréquence, on multiplie les bandes plus loins pour qu'elles soient plus visibles.
            float bandValue = fft.getBand(j)*(1 + (j/50));
            
            //Selection de la couleur en fonction des forces des différents types de sons
            mv.stroke(100 + scoreLow, 100 + scoreMid, 100 + scoreHi, 255 - j);
            mv.strokeWeight(1 + (scoreGlobal/100));
            
            //ligne inferieure gauche
            mv.line(0, mv.height-(previousBandValue*heightMult), dist*(j-1), 0, mv.height-(bandValue*heightMult), dist*j);
            mv.line((previousBandValue*heightMult), mv.height, dist*(j-1), (bandValue*heightMult), mv.height, dist*j);
            mv.line(0, mv.height-(previousBandValue*heightMult), dist*(j-1), (bandValue*heightMult), mv.height, dist*j);
            
            //ligne superieure gauche
            mv.line(0, (previousBandValue*heightMult), dist*(j-1), 0, (bandValue*heightMult), dist*j);
            mv.line((previousBandValue*heightMult), 0, dist*(j-1), (bandValue*heightMult), 0, dist*j);
            mv.line(0, (previousBandValue*heightMult), dist*(j-1), (bandValue*heightMult), 0, dist*j);
            
            //ligne inferieure droite
            mv.line(mv.width, height-(previousBandValue*heightMult), dist*(j-1), mv.width, height-(bandValue*heightMult), dist*j);
            mv.line(mv.width-(previousBandValue*heightMult), height, dist*(j-1), mv.width-(bandValue*heightMult), height, dist*j);
            mv.line(mv.width, height-(previousBandValue*heightMult), dist*(j-1), mv.width-(bandValue*heightMult), height, dist*j);
            
            //ligne superieure droite
            mv.line(mv.width, (previousBandValue*heightMult), dist*(j-1), mv.width, (bandValue*heightMult), dist*j);
            mv.line(mv.width-(previousBandValue*heightMult), 0, dist*(j-1), mv.width-(bandValue*heightMult), 0, dist*j);
            mv.line(mv.width, (previousBandValue*heightMult), dist*(j-1), mv.width-(bandValue*heightMult), 0, dist*j);

            //Sauvegarder la valeur pour le prochain tour de boucle
            previousBandValue = bandValue;
        }
        
        //Murs rectangles
        for(int i = 0; i < nbMurs; i++)
        {
            //On assigne à chaque mur une bande, et on lui envoie sa force.
            float intensity = fft.getBand(i%((int)(fft.specSize()*specHi)));
            murs[i].display(scoreLow, scoreMid, scoreHi, intensity, scoreGlobal);
        }
    } // end draw()

    //Classe pour les cubes qui flottent dans l'espace
    public class Cube 
    {
        //Position Z de "spawn" et position Z maximale
        float startingZ = -10000;
        float maxZ = 1000;
        
        //Valeurs de positions
        float x, y, z;
        float rotX, rotY, rotZ;
        float sumRotX, sumRotY, sumRotZ;
        
        //Constructeur
        Cube() {
            //Faire apparaitre le cube à un endroit aléatoire
            x = random(0, width);
            y = random(0, height);
            z = random(startingZ, maxZ);
            
            //Donner au cube une rotation aléatoire
            rotX = random(0, 1);
            rotY = random(0, 1);
            rotZ = random(0, 1);
        }
        
        void display(float scoreLow, float scoreMid, float scoreHi, float intensity, float scoreGlobal) 
        {
            //Sélection de la couleur, opacité déterminée par l'intensité (volume de la bande)
            // color displayColor = color(scoreLow*0.67, scoreMid*0.67, scoreHi*0.67, intensity*5);
            // fill(displayColor, 255);
            
            mv.fill(255); // Alternative?

            //Couleur lignes, elles disparaissent avec l'intensité individuelle du cube
            // color strokeColor = color(255, 150-(20*intensity));
            // stroke(strokeColor);
            mv.stroke(255);
            mv.strokeWeight(1 + (scoreGlobal/300));
            
            //Création d'une matrice de transformation pour effectuer des rotations, agrandissements
            pushMatrix();
            
            //Déplacement
            translate(x, y, z);
            
            //Calcul de la rotation en fonction de l'intensité pour le cube
            sumRotX += intensity*(rotX/1000);
            sumRotY += intensity*(rotY/1000);
            sumRotZ += intensity*(rotZ/1000);
            
            //Application de la rotation
            rotateX(sumRotX);
            rotateY(sumRotY);
            rotateZ(sumRotZ);
            
            //Création de la boite, taille variable en fonction de l'intensité pour le cube
            box(100+(intensity/2));
            
            //Application de la matrice
            popMatrix();
            
            //Déplacement Z
            z+= (1+(intensity/5)+(pow((scoreGlobal/150), 2)));
            
            //Replacer la boite à l'arrière lorsqu'elle n'est plus visible
            if (z >= maxZ) {
            x = random(0, width);
            y = random(0, height);
            z = startingZ;
            }
        } 
    } // end Cube class


    //Classe pour afficher les lignes sur les cotés
    public class Mur 
    {
        //Position minimale et maximale Z
        float startingZ = -10000;
        float maxZ = 50;
        
        //Valeurs de position
        float x, y, z;
        float sizeX, sizeY;
        
        //Constructeur
        Mur(float x, float y, float sizeX, float sizeY) 
        {
            //Faire apparaitre la ligne à l'endroit spécifié
            this.x = x;
            this.y = y;
            //Profondeur aléatoire
            this.z = random(startingZ, maxZ);  
            
            //On détermine la taille car les murs au planchers ont une taille différente que ceux sur les côtés
            this.sizeX = sizeX;
            this.sizeY = sizeY;
        }
        
        //Fonction d'affichage
        void display(float scoreLow, float scoreMid, float scoreHi, float intensity, float scoreGlobal) 
        {
            //Couleur déterminée par les sons bas, moyens et élevé
            //Opacité déterminé par le volume global
            // color displayColor = color(scoreLow*0.67, scoreMid*0.67, scoreHi*0.67, scoreGlobal);
            
            //Faire disparaitre les lignes au loin pour donner une illusion de brouillard
            // fill(displayColor, ((scoreGlobal-5)/1000)*(255+(z/25)));
            
            mv.fill(255);  // Alternative?

            noStroke();
            
            //Première bande, celle qui bouge en fonction de la force
            //Matrice de transformation
            pushMatrix();
            
            //Déplacement
            translate(x, y, z);
            
            //Agrandissement
            if (intensity > 100) intensity = 100;
            scale(sizeX*(intensity/100), sizeY*(intensity/100), 20);
            
            //Création de la "boite"
            box(1);
            popMatrix();
            
            //Deuxième bande, celle qui est toujours de la même taille
            // displayColor = color(scoreLow*0.5, scoreMid*0.5, scoreHi*0.5, scoreGlobal);
            // fill(displayColor, (scoreGlobal/5000)*(255+(z/25)));
            
            fill(255); // Alternative?
            
            //Matrice de transformation
            pushMatrix();
            
            //Déplacement
            translate(x, y, z);
            
            //Agrandissement
            scale(sizeX, sizeY, 10);
            
            //Création de la "boite"
            box(1);
            popMatrix();
            
            //Déplacement Z
            z+= (pow((scoreGlobal/150), 2));
            if (z >= maxZ) {
            z = startingZ;  
            }
        }
    } // end Mur class
} // end Main class
