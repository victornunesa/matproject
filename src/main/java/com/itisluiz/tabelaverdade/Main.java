package com.itisluiz.tabelaverdade;

import com.itisluiz.tabelaverdade.formulas.*;
import com.itisluiz.tabelaverdade.tokens.*;

import java.util.Scanner;

class Main
{
    public static void main(String[] args)
    {
        System.out.println("Calculadora de fórmulas lógicas por Luiz");
        System.out.println("-- Operações permitidas ---------------");
        for (FabricaToken operacao : FabricaToken.values())
            System.out.println(operacao.descricao());

        System.out.println("--------------------------------------");

        Scanner sin = new Scanner(System.in);
        while (true)
        {
            System.out.print("Insira uma fórmula: ");
            try
            {
                TabelaVerdade tabela = new TabelaVerdade(sin.nextLine());
                System.out.println("Tabela verdade:");
                System.out.println(tabela);

                System.out.print("Razão-verdade: " + Math.round(tabela.relacaoVerdade() * 100) + "%, ");
                System.out.println(tabela.tipoLogico());
            }
            catch (FormulaNaoReconhecida e)
            {
                // Fórmula não reconhecida encerra programa
                break;
            }
            catch (TokenDesconhecido e)
            {
                System.out.println("Token desconhecido: " + e.getMessage());
            }
            catch (FormulaMalFormada e)
            {
                System.out.println("Fórmula mal formada: " + e.getMessage());
            }
        }
    }
}