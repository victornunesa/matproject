package com.itisluiz.tabelaverdade.formulas;

import com.itisluiz.tabelaverdade.tokens.FabricaToken;
import com.itisluiz.tabelaverdade.tokens.Token;
import com.itisluiz.tabelaverdade.tokens.Variavel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Tokens
{
    private final ArrayList<Token> tokens = new ArrayList<>();
    private final ArrayList<Variavel> variaveis = new ArrayList<Variavel>();

    public Tokens() {}
    public Tokens(String formula)
    {
        for (char simbolo : formula.toCharArray())
            if (!Character.isWhitespace(simbolo))
                FabricaToken.criarToken(simbolo).inserirContainer(this);
    }

    public void inserirToken(Variavel token)
    {
        Variavel variavel = this.obterVariavel(token.identificador());
        if (variavel == null)
        {
            this.variaveis.add(token);
            this.ordenarVariaveis();
            variavel = token;
        }

        this.tokens.add(variavel);
    }

    public void inserirToken(Token token)
    {
        this.tokens.add(token);
    }

    private void ordenarVariaveis()
    {
        this.variaveis.sort(Comparator.comparingInt(Variavel::identificador));
    }

    public int permutacaoMaxima()
    {
        return 2 << (this.variaveis.size() - 1);
    }

    public void permutarVariaveis(int estado)
    {
        for (int indiceVariavel = 0; indiceVariavel < this.variaveis.size(); ++indiceVariavel)
            variaveis.get(this.variaveis.size() - indiceVariavel - 1).atribuirValor((estado & (1 << indiceVariavel)) == 0);
    }

    public int permutacaoAtual()
    {
        int estado = 0;
        for (int indiceVariavel = 0; indiceVariavel < this.variaveis.size(); ++indiceVariavel)
            if (!this.variaveis.get(this.variaveis.size() - indiceVariavel - 1).avaliar())
                estado |= (1 << indiceVariavel);

        return estado;
    }

    public Variavel obterVariavel(char identificador)
    {
        for (Variavel variavel : this.variaveis)
            if (identificador == variavel.identificador())
                return variavel;

        return null;
    }

    public List<Token> obterTokens()
    {
        return Collections.unmodifiableList(tokens);
    }
    public List<Variavel> obterVariaveis()
    {
        return Collections.unmodifiableList(variaveis);
    }
}
