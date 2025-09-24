import java.util.Scanner;

public class AVL {
    private Node rot = null;

    private class Node {
        private Node venstre;
        private Node hoyre;
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
            if (v.hoyre != null) {
                return insert(v.hoyre, x);
            }
            v.hoyre = new Node(x);
        }
        return balance(v);
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
            return contains(v.hoyre, x);
        }
        return false;
    }
    
    public int size(Node v) {
        if (v == null) {
            return 0;
        }

        int venstreSub = size(v.venstre);
        int hoyreSub = size(v.hoyre);

        return venstreSub + hoyreSub + 1;
    }

    public Node remove(Node v,int x) {
        if (v == null) {
            return null;
        }
        if (x == rot.element) {
            if (rot.venstre == null && rot.hoyre == null) {
                rot = null;
                return rot;
            }
            else if (rot.venstre == null || rot.hoyre == null) {
                if (rot.venstre != null) {
                    rot = rot.venstre;
                }
                else if (rot.hoyre != null) {
                    rot = rot.hoyre;
                }
            }
        }
        if (x < v.element) {
            v.venstre = remove(v.venstre, x);
            return v;
        }
        if (x > v.element) {
            v.hoyre = remove(v.hoyre, x);
            return v;
        }
        if (v.venstre == null) {
            return v.hoyre;
        }
        if (v.hoyre == null) {
            return v.venstre;
        }
        Node u = finnMinste(v.hoyre);
        v.element = u.element;
        v.hoyre = remove(v.hoyre, u.element);
        return balance(v);
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

    public int height(Node v) {
        int h = -1;
        if (v == null) {
            return h;
        }
        int venstreSub = height(v.venstre);
        int hoyreSub = height(v.hoyre);

        return Math.max(venstreSub, hoyreSub) + 1;
    }

    public Node rotateLeft(Node z) {
        Node y = z.hoyre;
        Node T_1 = y.venstre;

        y.venstre = z;
        z.hoyre = T_1;

        return y;
    }

    public Node rotateRight(Node z) {
        Node y = z.venstre;
        Node T_2 = y.hoyre;

        y.hoyre = z;
        z.venstre = T_2;

        return y;
    }

    public int hentBalansefaktor(Node v) {
        if (v == null) {
            return 0;
        }
        return height(v.venstre) - height(v.hoyre);
    }

    public Node balance(Node v) {
        if (hentBalansefaktor(v) < -1) {
            if (hentBalansefaktor(v.hoyre) > 0) {
                v.hoyre = rotateRight(v.hoyre);
            }
            return rotateLeft(v);
        }
        if (hentBalansefaktor(v) > 1) {
            if (hentBalansefaktor(v.venstre) < 0) {
                v.venstre = rotateLeft(v.venstre);
            }
            return rotateRight(v);
        }
        return v;
    }



    

    public static void main(String[] args) {
        BinaertSoketre set = new BinaertSoketre();
        Scanner sc = new Scanner(System.in);

        System.out.println("Hvor mange operasjoner ønsker du å gjøre?");
        int antOperasjoner = sc.nextInt();
        if (antOperasjoner < 1 || antOperasjoner > Math.pow(10, 6)) {
            System.out.println("Ugyldig input");
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

