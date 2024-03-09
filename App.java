import java.util.Scanner;

public class App {

    public void executar() {
        Scanner in = new Scanner(System.in);

        int nLinha = 0;
        int nPagina = 0;
        double stopWords = 0;
        double countTotal = 0;

        ArquivoTexto arquivo = new ArquivoTexto(); // objeto que gerencia o arquivo
        LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
        String l;
        ListaOrdenadaDePalavras lista = new ListaOrdenadaDePalavras();

        arquivo.open(in.nextLine());

        do // laco que passa em cada linha do arquivo
        {
            l = arquivo.getNextLine();
            if (l == null) // acabou o arquivo?
                break;
            nLinha++; // conta mais uma linha lida do arquivo
            if (nLinha == 40) // chegou ao fim da pagina?
            {
                nLinha = 0;
                nPagina++;
            }

            linha.setLine(l); // define o texto da linha
            do // laco que passa em cada palavra de uma linha
            {
                String palavra = linha.getNextWord(); // obtem a proxima palavra da linha

                if (palavra == null || palavra.trim().isEmpty())// acabou a linha
                {
                    break;
                }
                countTotal++;

                stopWords += leStopWords(palavra);
                if (leStopWords(palavra) == 0) {
                    lista.add(palavra.toLowerCase(), nPagina);
                }
            } while (true);

        } while (true);
        arquivo.close();

        int select = -1;
        do {
            menu();
            select = in.nextInt();
            in.nextLine();

            switch (select) {
                case 1:
                    lista.printAll();
                    System.out.println("-----------------------------------------------");
                    break;
                case 2:
                    System.out.println(stopWords);
                    System.out.println(countTotal);
                    double porcentagemStopWords = (stopWords / countTotal) * 100;
                    System.out.printf("Porcentual de stop words: %.2f%%\n", porcentagemStopWords);
                    System.out.println("-----------------------------------------------");
                    break;
                case 3:
                    System.out.printf("A palavra mais frequente é %S\n", lista.maisOcorrencias());
                    System.out.println("-----------------------------------------------");
                    break;
                case 4:
                    String aux = in.nextLine();
                    System.out.println(lista.paginasPalavra(aux));
                    System.out.println("-----------------------------------------------");

            }

        } while (select != 5);
    }

    private void menu() {
        System.out.println("[1] Exibir todo o índice remissivo");
        System.out.println("[2] Exibir o percentual de stopwords do texto");
        System.out.println("[3] Encontrar a palavra mais frequente");
        System.out.println("[4] Pesquisar páginas de uma palavra");
        System.out.println("[5] Encerrar o programa");
        System.out.println("-----------------------------------------------");
    }

    private int leStopWords(String palavra) {
        ArquivoTexto listaStopWordsEN = new ArquivoTexto();
        listaStopWordsEN.open("StopWords-EN.txt");
        do {

            String aux = listaStopWordsEN.getNextLine();

            if (aux == null) {
                break;
            }
            // System.out.println(aux);
            if (aux.equals(palavra.toLowerCase())) {
                return 1;
            }
        } while (true);
        return 0;
    }

}
