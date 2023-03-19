package com.itisluiz.tabelaverdade.formulas;

import com.itisluiz.tabelaverdade.tokens.Token;
import com.itisluiz.tabelaverdade.tokens.Variavel;

import java.util.StringJoiner;

public class TabelaVerdade extends FormulaLogica
{
    private enum Linha
    {
        TOPO,
        CABECALHO,
        VALORES,
        SEPARADOR,
        BASE,
        INDICADOR_RAIZ
    }

    public TabelaVerdade(Tokens tokens)
    {
        super(tokens);
    }
    public TabelaVerdade(FormulaLogica formula)
    {
        super(formula);
    }
    public TabelaVerdade(String formula)
    {
        super(formula);
    }

    private char caractereValor(Token token)
    {
        if (token.precedencia() < 0)
            return ' ';

        return token.avaliar() ? 'V' : 'F';
    }

    private String avaliarValorLinha(Token token, Linha tipoLinha)
    {
        return switch (tipoLinha)
        {
            case CABECALHO -> token != null ? Character.toString(token.identificador()) : " ";
            case VALORES -> token != null ? Character.toString(this.caractereValor(token)) : " ";
            case INDICADOR_RAIZ -> token == this.raiz ? "▲" : "─";
            default -> "─";
        };
    }

    private String montarLinha(Linha tipoLinha)
    {
        StringJoiner linha;
        switch (tipoLinha)
        {
            case SEPARADOR -> linha = new StringJoiner("┼", "├", "┤");
            case BASE -> linha = new StringJoiner("┴", "└", "┘");
            case TOPO -> linha = new StringJoiner("┬", "┌", "┐");
            case INDICADOR_RAIZ -> linha = new StringJoiner("─", "├", "┤");
            default -> linha = new StringJoiner("│", "│", "│");
        }

        for (Variavel token : this.tokens.obterVariaveis())
            linha.add(this.avaliarValorLinha(token, tipoLinha));

        linha.add(avaliarValorLinha(null, tipoLinha));
        for (Token token : this.tokens.obterTokens())
            linha.add(avaliarValorLinha(token, tipoLinha));

        return linha.toString();
    }

    @Override
    public String toString()
    {
        StringJoiner linhas = new StringJoiner("\n");
        linhas.add(this.montarLinha(Linha.TOPO));
        linhas.add(this.montarLinha(Linha.CABECALHO));

        for (int permutacao = 0; permutacao < this.tokens.permutacaoMaxima(); ++permutacao)
        {
            this.tokens.permutarVariaveis(permutacao);
            linhas.add(this.montarLinha(Linha.SEPARADOR));
            linhas.add(this.montarLinha(Linha.VALORES));
        }

        linhas.add(this.montarLinha(Linha.BASE));
        linhas.add(this.montarLinha(Linha.INDICADOR_RAIZ));
        return linhas.toString();
    }
}
