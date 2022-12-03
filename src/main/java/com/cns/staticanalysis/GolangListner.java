package com.cns.staticanalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.TerminalNode;

import com.cns.grammar.GoParser;
import com.cns.grammar.GoParserBaseListener;

public class GoLangListner extends GoParserBaseListener {

    @Override
    public void enterFunctionDecl(GoParser.FunctionDeclContext ctx) {
        if (ctx.signature().result() == null) {
            return;
        }
        if (!isCriticalVariable(ctx.IDENTIFIER().getText())) {
            return;
        }

        if (ctx.signature().result().parameters() != null) {
            List<GoParser.ParameterDeclContext> paramsStrings = ctx.signature().result().parameters().parameterDecl()
                    .stream()
                    .filter(p -> p.type_().getText().equals("string"))
                    .collect(Collectors.toList());

            if (paramsStrings.size() > 0)
                System.out.println("Function Return Params " + ctx.IDENTIFIER().getText());
        }

        if (ctx.signature().result().type_() != null) {
            if (ctx.signature().result().type_().getText().equals("string")) {
                System.out.println("Function Return Type " + ctx.IDENTIFIER().getText());
            }
        }

    }

    @Override
    public void enterMethodDecl(GoParser.MethodDeclContext ctx) {
        if (ctx.signature().result() == null) {
            return;
        }
        if (!isCriticalVariable(ctx.IDENTIFIER().getText())) {
            return;
        }

        if (ctx.signature().result().parameters() != null) {
            List<GoParser.ParameterDeclContext> paramsStrings = ctx.signature().result().parameters().parameterDecl()
                    .stream()
                    .filter(p -> p.type_().getText().equals("string"))
                    .collect(Collectors.toList());

            if (paramsStrings.size() > 0)
                System.out.println("Method Return Params " + ctx.IDENTIFIER().getText());
        }

        if (ctx.signature().result().type_() != null) {
            if (ctx.signature().result().type_().getText().equals("string")) {
                System.out.println("Method Return Type " + ctx.IDENTIFIER().getText());
            }
        }
    }

    @Override
    public void enterStructType(GoParser.StructTypeContext ctx) {
        List<List<TerminalNode>> identifiers = ctx.fieldDecl()
                .stream()
                .filter(var -> var.type_() != null)
                .filter(f -> f.type_().getText().equals("string"))
                .map(f -> f.identifierList().IDENTIFIER())
                .collect(Collectors.toList());

        List<String> criticalVars = new ArrayList<>();

        for (List<TerminalNode> varNames : identifiers) {
            criticalVars.addAll(
                    varNames.stream()
                            .filter(var -> isCriticalVariable(var.getText()))
                            .map(var -> var.getText())
                            .collect(Collectors.toList()));
        }

        if (criticalVars.size() > 0)
            System.out.println("Struct feild " + criticalVars);

    }

    @Override
    public void enterTypeSpec(GoParser.TypeSpecContext ctx) {
        if (ctx.type_().typeLit() != null
                && ctx.type_().typeLit().structType() != null
                && isCriticalVariable(ctx.IDENTIFIER().getText())) {
            System.out.println("Struct " + ctx.IDENTIFIER().getText());
        }

    }

    @Override
    public void enterVarSpec(GoParser.VarSpecContext ctx) {
        if (ctx.type_() == null || !ctx.type_().getText().equals("string")) {
            return;
        }

        List<String> criticalVars = ctx.identifierList().IDENTIFIER().stream()
                .filter(i -> isCriticalVariable(i.getText()))
                .map(i -> i.getText())
                .collect(Collectors.toList());

        if (criticalVars.size() > 0)
            System.out.println("Var " + criticalVars);
    }

    private boolean isCriticalVariable(String var) {
        Pattern pattern = Pattern.compile("(?:pass|key|crypt|imei|username|identifier|secret|token|auth)",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(var);
        return matcher.find();
    }

}
