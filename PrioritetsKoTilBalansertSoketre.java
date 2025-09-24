import java.util.PriorityQueue;
import java.util.Scanner;

public class PrioritetsKoTilBalansertSoketre {

    public void skrivUtTilBalansertSoketre(PriorityQueue<Integer> p) {
        int size = p.size();
        if (size < 1) {
            return;
        }

        int mid = (int)Math.floor(size/2);
        PriorityQueue<Integer> p2 = new PriorityQueue<>();

        for (int i = 0; i < mid; i++) {
            p2.offer(p.poll());
        }
        System.out.println(p.poll());

        skrivUtTilBalansertSoketre(p);
        skrivUtTilBalansertSoketre(p2);
    }


    public static void main(String[] args) {
        PrioritetsKoTilBalansertSoketre p = new PrioritetsKoTilBalansertSoketre();
        PriorityQueue<Integer> prioritetsKo = new PriorityQueue<>();

        System.out.println("Skriv et tall lavere enn det du har gitt som input for å avslutte.");
        Scanner sc = new Scanner(System.in); 
        int forste = sc.nextInt();
        prioritetsKo.offer(forste);
        int neste;
        boolean fortsett = true;

        while (fortsett) {          // Sjekker at neste tall som skrives er større enn det forrige tallet som ble gitt som input
            neste = sc.nextInt();

            if (neste > forste) {
                prioritetsKo.offer(neste);
                
                forste = sc.nextInt();
                if (forste > neste) {
                    prioritetsKo.offer(forste);
                }
                else {
                    fortsett = false;
                }
            }
            else {
                fortsett = false;
            }
        }
        
        System.out.println("");
        p.skrivUtTilBalansertSoketre(prioritetsKo);

        sc.close();
        
    }
}
