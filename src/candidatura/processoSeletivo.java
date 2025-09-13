package candidatura;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class processoSeletivo {
    public static void main(String[] args) {
        // Chama o process de select e recede os candidatos aprovados
        String[] selecionados = selecaoCandidato();

        System.out.println("\nIniciando tentativa de contato com os candidatos selecionados...\n");

        // Percorre os candidatos selecionados e tenta contato com cada um
        for (String candidato : selecionados) {
            entrandoEmContato(candidato);
            System.out.println("--------------------------------");
        }
    }

    // tentativa de contato com um candidato
    static void entrandoEmContato(String candidato) {
        int tentativasRealizadas = 1;
        boolean continuarTentando = true;
        boolean atendeu = false;

        do {
            atendeu = atender();
            continuarTentando = !atendeu;

            if (continuarTentando)
                tentativasRealizadas++;
            else
                System.out.println("CONTATO REALIZADO COM SUCESSO!");

        } while (continuarTentando && tentativasRealizadas < 3);

        if (atendeu)
            System.out.printf("CONSEGUIMOS CONTATO COM %s NA %dª TENTATIVA.\n", candidato, tentativasRealizadas);
        else
            System.out.printf("NÃO CONSEGUIMOS CONTATO COM %s. NÚMERO MÁXIMO DE LIGAÇÕES REALIZADAS.\n", candidato);
    }

    static boolean atender() {
        return new Random().nextInt(3) == 1; // 1 chance em 3
    }

    // Realiza a seleção dos candidatos com base no salário pretendido
    static String[] selecaoCandidato() {
        String[] candidatos = {
                "FELIPE", "MARCIA", "JULIA", "PAULA", "AUGUSTO",
                "MONICA", "FABRICIO", "MIRELA", "DANIELA", "IRENE"
        };

        String[] selecionados = new String[5];
        int selecionadosCount = 0;
        int candidatoAtual = 0;
        double salarioBase = 2000.0;

        System.out.println("Iniciando processo de seleção de candidatos...\n");

        while (selecionadosCount < 5 && candidatoAtual < candidatos.length) {
            String candidato = candidatos[candidatoAtual];
            double salarioPretendido = valorPretendido();

            System.out.printf("O candidato %s solicitou este valor de salário: R$ %.2f\n", candidato, salarioPretendido);

            if (salarioPretendido <= salarioBase) {
                System.out.printf("O candidato %s foi selecionado para a vaga.\n\n", candidato);
                selecionados[selecionadosCount] = candidato;
                selecionadosCount++;
            } else {
                System.out.printf("O candidato %s não foi selecionado.\n\n", candidato);
            }
            candidatoAtual++;
        }

        return selecionados;
    }

    static double valorPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    }

    static void analisarCandidato(double salarioPretendido) {
        double salarioBase = 2000.0;
        if (salarioBase > salarioPretendido) {
            System.out.println("LIGAR PARA O CANDIDATO");
        } else if (salarioBase == salarioPretendido) {
            System.out.println("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
        } else {
            System.out.println("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDATOS");
        }
    }
}
