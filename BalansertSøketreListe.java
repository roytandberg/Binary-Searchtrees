import java.util.ArrayList;
import java.util.Scanner;

public class BalansertSøketreListe {

    public ArrayList<Integer> finnMiderste(ArrayList<Integer> liste, ArrayList<Integer> nyListe) {
        int lengde = liste.size();
        if (lengde < 1) {
            return liste;
        }
        int midIndeks = (int)Math.floor(lengde/2);

        nyListe.add(liste.get(midIndeks));

        ArrayList<Integer> hoyreHalv = new ArrayList<>();
        for (int i = midIndeks + 1; i < lengde; i++) {
            hoyreHalv.add(liste.get(i));
        }
        finnMiderste(hoyreHalv, nyListe);

        ArrayList<Integer> venstreHalv = new ArrayList<>();
        for (int i = 0; i < midIndeks; i++) {
            venstreHalv.add(liste.get(i));
        }
        finnMiderste(venstreHalv, nyListe);

        return nyListe;
    }
    
    public void skrivUtListe(ArrayList<Integer> liste) {
        for (int tall : liste) {
            System.out.println(tall);
        }
    }

    public static void main(String[] args) {
        BalansertSøketreListe b = new BalansertSøketreListe();

        ArrayList<Integer> liste = new ArrayList<>();
        System.out.println("Skriv et tall lavere enn det du har gitt som input for å avslutte.");

        Scanner sc = new Scanner(System.in);
        int forste = sc.nextInt();
        liste.add(forste);
        int neste;
        boolean fortsett = true;

        while (fortsett) {          // Sjekker at neste tall som skrives er større enn det forrige tallet som ble gitt som input
            neste = sc.nextInt();

            if (neste > forste) {
                liste.add(neste);
                
                forste = sc.nextInt();
                if (forste > neste) {
                    liste.add(forste);
                }
                else {
                    fortsett = false;
                }
            }
            else {
                fortsett = false;
            }
        }

        ArrayList<Integer> nyListe = b.finnMiderste(liste, new ArrayList<>());

        System.out.println("");
        b.skrivUtListe(nyListe);

        sc.close();
    }
    
}
