import java.util.Scanner;

public class BinaertSoketre {
    private Node rot = null;

    private class Node {
        private Node venstre;
        private Node høyre;
        private int element;

        private Node(int tall) {
            element = tall;
        }
    }

    public Node insert(Node v, int x) {
        if (rot == null) {
            rot = new Node(x);
        }
        else if (x < v.element) {
            if (v.venstre != null) {
                return insert(v.venstre, x);
            }
            v.venstre = new Node(x);
        }
        else if (x > v.element) {
            if (v.høyre != null) {
                return insert(v.høyre, x);
            }
            v.høyre = new Node(x);
        }
        return v;
    }

    public boolean contains(Node v, int x) {
        if (v == null) {
            return false;
        }
        if (v.element == x) {
            return true;
        }
        if (x < v.element) {
            return contains(v.venstre, x);
        }
        if (x > v.element) {
            return contains(v.høyre, x);
        }
        return false;
    }
    
    public int size(Node v) {
        if (v == null) {
            return 0;
        }

        int venstreSub = size(v.venstre);
        int høyreSub = size(v.høyre);

        return venstreSub + høyreSub + 1;
    }

    public Node remove(Node v,int x) {
        if (v == null) {
            return null;
        }
        if (x == rot.element) {
            if (rot.venstre == null && rot.høyre == null) {
                rot = null;
                return rot;
            }
            else if (rot.venstre == null || rot.høyre == null) {
                if (rot.venstre != null) {
                    rot = rot.venstre;
                }
                else if (rot.høyre != null) {
                    rot = rot.høyre;
                }
            }
        }
        if (x < v.element) {
            v.venstre = remove(v.venstre, x);
            return v;
        }
        if (x > v.element) {
            v.høyre = remove(v.høyre, x);
            return v;
        }
        if (v.venstre == null) {
            return v.høyre;
        }
        if (v.høyre == null) {
            return v.venstre;
        }
        Node u = finnMinste(v.høyre);
        v.element = u.element;
        v.høyre = remove(v.høyre, u.element);
        return v;
    }

    public Node finnMinste(Node n) {
        if (n.venstre == null) {
            return n;
        }
        return finnMinste(n.venstre);
    }

    public Node hentRot() {
        return rot;
    }

    public static void main(String[] args) {
        BinaertSoketre set = new BinaertSoketre();
        Scanner sc = new Scanner(System.in);

        System.out.println("Hvor mange operasjoner ønsker du å gjøre?");
        int antOperasjoner = sc.nextInt();
        if (antOperasjoner <= 1 || antOperasjoner >= Math.pow(10, 6)) {
            System.out.println("Ugyldig input. \nAntall operasjoner må være mellom 1 og 10^6");
            System.exit(1);
        }

        for (int i = 0; i <= antOperasjoner; i++) {
            String[] deler = sc.nextLine().split(" ");

            if ((deler[0].toLowerCase()).equals("insert")) {
                set.insert(set.hentRot(), Integer.parseInt(deler[1]));
            }
            else if ((deler[0].toLowerCase()).equals("contains")) {
                System.out.println(set.contains(set.hentRot(), Integer.parseInt(deler[1])));
            }
            else if ((deler[0].toLowerCase()).equals("size")) {
                System.out.println(set.size(set.hentRot()));
            }
            else if ((deler[0].toLowerCase()).equals("remove")) {
                set.remove(set.hentRot(), Integer.parseInt(deler[1]));
            }

        }
        sc.close();

        
    
    
    }
}

