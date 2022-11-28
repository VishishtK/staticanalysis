package com.cns.staticanalysis;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.cns.grammar.JavaParser;
import com.cns.grammar.JavaParserBaseListener;

public class JavaListner extends JavaParserBaseListener {

    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        String returnType = ctx.typeTypeOrVoid().getText();
        if (returnType.equals("String")) {
            if (isCriticalVariable(ctx.identifier().getText()))
                System.out.println("<Method> " + returnType + " " + ctx.identifier().getText());
        }
    }

    @Override
    public void enterMethodBody(JavaParser.MethodBodyContext ctx) {
    }

    @Override
    public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
        String fieldType = ctx.typeType().getText();
        if (fieldType.equals("String")) {

            List<String> list = ctx.variableDeclarators().variableDeclarator()
                    .stream()
                    .filter(x -> isCriticalVariable(x.variableDeclaratorId().getText()))
                    .map(x -> x.getText()).collect(Collectors.toList());

            if (list.size() > 0)
                System.out.println("<Field> " + fieldType + " " + list);
        }

        if (fieldType.equals("StringBuilder")) {
            List<String> list = ctx.variableDeclarators().variableDeclarator()
                    .stream()
                    .filter(x -> isCriticalVariable(x.variableDeclaratorId().getText()))
                    .map(x -> x.getText()).collect(Collectors.toList());

            if (list.size() > 0)
                System.out.println("<Field StringBuilder >  " + fieldType + " " + list);
        }

        if (fieldType.equals("char[]")) {
            List<String> list = ctx.variableDeclarators().variableDeclarator()
                    .stream()
                    .filter(x -> isCriticalVariable(x.variableDeclaratorId().getText()))
                    .map(x -> x.getText()).collect(Collectors.toList());

            if (list.size() > 0)
                System.out.println("<Field char[] >  " + fieldType + " " + list);
        }
    }

    @Override
    public void enterLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx) {
        String fieldType = ctx.typeType().getText();
        if (fieldType.equals("String")) {

            List<String> list = ctx.variableDeclarators().variableDeclarator()
                    .stream()
                    .filter(x -> isCriticalVariable(x.variableDeclaratorId().getText()))
                    .map(x -> x.getText()).collect(Collectors.toList());

            if (list.size() > 0)
                System.out.println("<Local Var> " + fieldType + " " + list);
        }

        if (fieldType.equals("StringBuilder")) {
            List<String> list = ctx.variableDeclarators().variableDeclarator()
                    .stream()
                    .filter(x -> isCriticalVariable(x.variableDeclaratorId().getText()))
                    .map(x -> x.getText()).collect(Collectors.toList());

            if (list.size() > 0)
                System.out.println("<Local Var StringBuilder >  " + fieldType + " " + list);
        }

        if (fieldType.equals("char[]")) {
            List<String> list = ctx.variableDeclarators().variableDeclarator()
                    .stream()
                    .filter(x -> isCriticalVariable(x.variableDeclaratorId().getText()))
                    .map(x -> x.getText()).collect(Collectors.toList());

            if (list.size() > 0)
                System.out.println("<Local Var char[] >  " + fieldType + " " + list);
        }
    }

    private boolean isCriticalVariable(String var) {
        Pattern pattern = Pattern.compile("(?:pass|key|crypt|imei|username|identifier|secret|token|auth)",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(var);
        return matcher.find();
    }

}
