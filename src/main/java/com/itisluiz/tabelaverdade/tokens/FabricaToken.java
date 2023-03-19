package com.itisluiz.tabelaverdade.tokens;

import com.itisluiz.tabelaverdade.tokens.operacoes.*;

import java.util.StringJoiner;
import java.util.function.Supplier;

public enum FabricaToken
{
    VARIAVEL(null, null),
    PRECEDENCIA_ABRE(new char[]{'(', '['}, () -> new Precedencia(true)),
    PRECEDENCIA_FECHA(new char[]{')', ']'}, () -> new Precedencia(false)),
    NEGACAO(new char[]{'¬', '~'}, Negacao::new),
    CONJUNCAO(new char[]{'∧', '.'}, Conjuncao::new),
    DISJUNCAO(new char[]{'∨', '+'}, Disjuncao::new),
    IMPLICACAO(new char[]{'→', '>'}, Implicacao::new),
    BICONDICIONAL(new char[]{'↔', '='}, Bicondicional::new);

    public final char[] simbolos;
    private final Supplier<Token>  fornecedorToken;

    FabricaToken(char[] simbolos, Supplier<Token> fornecedorToken)
    {
        this.simbolos = simbolos;
        this.fornecedorToken = fornecedorToken;
    }

    public String descricao()
    {
        StringJoiner simbolos = new StringJoiner(", ");
        if (this.simbolos != null)
            for (char simbolo : this.simbolos)
                simbolos.add(Character.toString(simbolo));
        else
            return this.toString();

        return this.toString() + ": " + simbolos.toString();
    }

    public static FabricaToken identificarSimbolo(char simbolo)
    {
        for (FabricaToken tipo : FabricaToken.values())
        {
            if (tipo.simbolos == null)
            {
                if (Character.isLetter(simbolo))
                    return FabricaToken.VARIAVEL;

                continue;
            }

            for (char tipoSimbolo : tipo.simbolos)
            {
                if (tipoSimbolo == simbolo)
                    return tipo;
            }
        }

        return null;
    }

    public static Token criarToken(char simbolo)
    {
        FabricaToken fabrica = identificarSimbolo(simbolo);

        if (fabrica == null)
            throw new TokenDesconhecido(simbolo);
        else if (fabrica == VARIAVEL)
            return new Variavel(simbolo);

        return fabrica.fornecedorToken.get();
    }
}
