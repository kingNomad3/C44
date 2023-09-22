package com.example.annex8;

public class Placement 
{
private double montant;
private int nbMois;

private static final double INTERET = 0.024/12;  // 12 mois, taux d interet annuel
                                                    // peut ecrire exception tout cours et ou ajouter une virgule et en ajotuer NegativeMontantException, ...,...
  public Placement( double montant, int nbMois) throws NegativeMontantException  // indique explicitement qu'une exception peut être lancée
  {
    if (montant <= 0){
      throw new NegativeMontantException(montant);

    }
    this.montant = montant;
    this.nbMois =   nbMois;
  }



  public double calculerMontantFinal ()
  {
  double total = montant;
  for ( int i = 0; i < nbMois ; i ++ )
    total = total + total * INTERET;
  return total;
  }
  
}