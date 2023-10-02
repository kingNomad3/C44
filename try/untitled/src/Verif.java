
    public class Verif {

        public void premiereMethode ( String convertie ) {
            try
            {
                deuxiemeMethode ( convertie, 1 );
                System.out.println ( "1. Dans le bloc try de premiereMethode" );
                deuxiemeMethode ( convertie, 0 );
                System.out.println ( "2. Dans le bloc try de premiereMethode" );
            }
            catch ( NumberFormatException nfe )
            {
                System.out.println ("3." + convertie + " n'est pas convertible en int" );
            }

            finally
            {
                System.out.println ( "4. Rendu au finally de premiereMethode" );
            }
        }
        public void deuxiemeMethode ( String str, int nombre )
        {
            int valeur = Integer.parseInt ( str );
            System.out.println ( "5. str en nombre entier donne : " + valeur );
            System.out.println ( "6." + valeur + " /" + nombre + " = " + (valeur / nombre));
        }


        public void main(String[] args) {

            Verif exemple = new Verif ();
            try
            {
                exemple.premiereMethode ( "asdf" );
                System.out.println ( "7. Dans le main après asdf " );
                exemple.premiereMethode ( "-1" );
                System.out.println ( "8. main après –1" );
            }
            catch ( Exception e )
            {
                System.out.println ( "9. Il y a une exception dans le main" );
            }

            exemple.premiereMethode ( "56" );
            System.out.println ( "10. Dernière ligne de code" );
        }
    }


