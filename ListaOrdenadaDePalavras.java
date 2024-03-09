
/**
 * Esta classe guarda as palavra do indice remissivo em ordem alfabetica.
 * 
 * @author Isabel H. Manssour
 */

public class ListaOrdenadaDePalavras {

    // Classe interna
    private class Palavra {
        public String s;
        public ListaDeOcorrencias listaOcorrencias;
        public Palavra next;

        public Palavra(String str) {
            s = str;
            next = null;
            listaOcorrencias = new ListaDeOcorrencias();
        }

        // Metodos

    }

    // Atributos

    private Palavra head;
    private Palavra tail;
    private int count;
    private int countTotal;

    // Metodos

    public ListaOrdenadaDePalavras() {
        head = null;
        tail = null;
        count = 0;
        countTotal = 0;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public int size() {
        return count;
    }

    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    public void add(String palavra, int numPagina) {
        if (contains(palavra)) {
            getPalavraByString(palavra).listaOcorrencias.add(numPagina + 1);
        } else {

            Palavra p = new Palavra(palavra);

            if (head == null) {
                head = p;
                tail = p;
            } else {
                int index = 0;

                for (int i = 0; i < count; i++) {

                    if (palavra.compareTo(get(i)) < 0) {
                        index = i - 1;
                        if (index == -1) {
                            index = 0;
                        }
                        break;
                    }
                    if (i == count - 1) {
                        index = count;
                    }
                }
                // System.out.println(index);
                if (index == 0) {
                    p.next = head;
                    head = p;
                } else if (index == count) {
                    tail.next = p;
                    tail = p;
                } else {
                    Palavra ant = head;
                    for (int i = 0; i < index; i++) {
                        ant = ant.next;
                    }
                    p.next = ant.next;
                    ant.next = p;
                }
            }

            p.listaOcorrencias.add(numPagina + 1);
            count++;
        }
        countTotal++;
    }

    public int getCount() {
        return count;
    }

    public String get(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }

        if (index == count - 1)
            return tail.s;

        Palavra aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        return aux.s;
    }

    public boolean contains(String palavra) {
        Palavra aux = head;
        while (aux != null) {
            if (aux.s.equals(palavra)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    private Palavra getPalavraByString(String palavra) {
        Palavra aux = head;
        for (int i = 0; i < count; i++) {
            if (aux.s.equals(palavra)) {
                return aux;
            }
            aux = aux.next;
        }
        return null;
    }

    public String paginasPalavra(String palavra) {

        ListaDeOcorrencias aux1 = getPalavraByString(palavra).listaOcorrencias;
        String aux2 = "";
        for (int i = 0; i < aux1.getCount(); i++) {
            aux2 += aux1.get(i);
            if (i != aux1.getCount() - 1) {
                aux2 += ", ";
            } else {
                aux2 += ".";
            }
        }

        return aux2;
    }

    public void printAll() {
        Palavra aux1 = head;
        String aux2 = "";
        while (aux1 != null) {
            aux2 = aux1.s + ": ";
            for (int i = 0; i < aux1.listaOcorrencias.getCount(); i++) {
                aux2 += aux1.listaOcorrencias.get(i);
                if (i != aux1.listaOcorrencias.getCount() - 1) {
                    aux2 += ", ";
                } else {
                    aux2 += ".";
                }
            }
            System.out.println(aux2);
            aux1 = aux1.next;
        }
    }

    public String maisOcorrencias() {

        Palavra aux = head;
        int maior = -1;
        int maisUsada = 0;
        for (int i = 0; i < count; i++) {
            if (aux.listaOcorrencias.getCount() > maior) {
                maior = aux.listaOcorrencias.getCount();
                maisUsada = i;
            }
            aux = aux.next;
        }

        return get(maisUsada);
    }

    public double stopWords() {
        return 1;
    }

    public int getCountTotal() {
        return countTotal;
    }
}
