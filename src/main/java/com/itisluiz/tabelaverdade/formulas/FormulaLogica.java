package com.itisluiz.tabelaverdade.formulas;

import com.itisluiz.tabelaverdade.tokens.Token;
import com.itisluiz.tabelaverdade.tokens.Variavel;

import java.util.StringJoiner;

public class FormulaLogica
{
    protected Tokens tokens;
    protected Token raiz;

    public enum TipoLogico
    {
        TAUTOLOGIA,
        CONTRADICAO,
        CONTINGENCIA
    }

    public FormulaLogica(Tokens tokens)
    {
        this.tokens = tokens;
        this.raiz = NotacaoPolonesa.calcularRaiz(tokens.obterTokens());

        if (this.raiz == null)
            throw new FormulaNaoReconhecida("Fórmula vazia fornecida");
    }

    public FormulaLogica(FormulaLogica formula)
    {
        this.tokens = formula.tokens;
        this.raiz = formula.raiz;
    }

    public FormulaLogica(String formula)
    {
        this(new Tokens(formula));
    }

    public Variavel obterVariavel(char identificador)
    {
        return this.tokens.obterVariavel(identificador);
    }

    public boolean avaliar() { return this.raiz.avaliar(); }

    public float relacaoVerdade()
    {
        int estadoOriginal = this.tokens.permutacaoAtual();
        int verdades = 0;

        for (int permutacao = 0; permutacao < this.tokens.permutacaoMaxima(); ++permutacao)
        {
            this.tokens.permutarVariaveis(permutacao);
            if (this.raiz.avaliar())
                ++verdades;
        }

        this.tokens.permutarVariaveis(estadoOriginal);
        return (float)verdades / this.tokens.permutacaoMaxima();
    }

    public TipoLogico tipoLogico()
    {
        float relacao = this.relacaoVerdade();

        if (Float.compare(relacao, 1.0f) == 0)
            return TipoLogico.TAUTOLOGIA;
        else if (Float.compare(relacao, 0.0f) == 0)
            return TipoLogico.CONTRADICAO;

        return TipoLogico.CONTINGENCIA;
    }

    @Override
    public String toString()
    {
        StringJoiner formula = new StringJoiner(" ");

        for (Token token : this.tokens.obterTokens())
            formula.add(Character.toString(token.identificador()));

        return formula.toString();
    }
}
