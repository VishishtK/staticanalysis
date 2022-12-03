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
        // Un-named return types
        // String returnType = ctx.signature().result().type_().getText();
        // if (returnType.equals("string")) {
        // System.out.println(ctx.signature().result().getText());
        // }
        // if (ctx.signature().result() == null)
        // return;

        // if(ctx.IDENTIFIER().equals("AlphaUpload")){
        // System.out.println(ctx.getText());
        // }

        // if (ctx.signature().result().parameters() != null) {
        // System.out.println("Function " + ctx.IDENTIFIER().getText());
        // System.out.println(ctx.signature().result().parameters().getText());
        // }

        // if (ctx.signature().result().type_() != null) {
        // System.out.println("Function " + ctx.IDENTIFIER().getText());
        // System.out.println(ctx.signature().result().type_().getText());
        // }

    }

    @Override
    public void enterMethodDecl(GoParser.MethodDeclContext ctx) {
    }

    // @Override
    // public void enterVarDecl(GoParser.VarDeclContext ctx) {
    // List<List<TerminalNode>> varNameList = ctx.varSpec().stream()
    // .filter(var -> var.type_() != null)
    // .filter(var -> var.type_().getText().equals("string"))
    // .map(var -> var.identifierList().IDENTIFIER())
    // .collect(Collectors.toList());

    // List<String> criticalVars = new ArrayList<>();

    // for (List<TerminalNode> varNames : varNameList) {
    // criticalVars.addAll(
    // varNames.stream()
    // .filter(var -> isCriticalVariable(var.getText()))
    // .map(var -> var.getText())
    // .collect(Collectors.toList()));
    // }

    // if (criticalVars.size() > 0)
    // System.out.println("Var declaration " + criticalVars);
    // }

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
            // System.out.println("Struct " + ctx.IDENTIFIER().getText());
        }

    }

    @Override
    public void enterVarSpec(GoParser.VarSpecContext ctx) {
    }

    private boolean isCriticalVariable(String var) {
        Pattern pattern = Pattern.compile("(?:pass|key|crypt|imei|username|identifier|secret|token|auth)",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(var);
        return matcher.find();
    }

}
