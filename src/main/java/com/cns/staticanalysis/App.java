package com.cns.staticanalysis;

import java.util.regex.Pattern;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.cns.grammar.JavaLexer;
import com.cns.grammar.JavaParser;
import com.cns.grammar.JavaParserBaseListener;
import com.cns.grammar.JavaParser.TypeDeclarationContext;

public class App {
    public static void main(String[] args) {
        TargetSDK sdk = new TargetSDK("/Users/vishy/Desktop/CNS-Project-SDK/dropbox-sdk-java/", ".java");
        sdk.Analyse();
    }
}

/**
 * Listener class which verifies that abstract class must have name starting
 * with Abstract. Errors will be printed to console.
 *
 */
class ClassNameOfAbstractClassListener extends JavaParserBaseListener {

    // Walk method will call this method when it comes across a token which is a
    // class declaration.
    @Override
    public void enterTypeDeclaration(TypeDeclarationContext ctx) {
        // Fetch class name from the token.
        String className = ctx.classDeclaration().identifier().getText();

        // Check if the class from this token is abstract.
        boolean isAbstract = ctx.classOrInterfaceModifier().stream().anyMatch(m -> m.ABSTRACT() != null);

        // Regex pattern to verify name starts with word Abstract
        Pattern format = Pattern.compile("^Abstract.+$");
        boolean matching = format.matcher(className).find();
        System.out.println("\nClass Name = " + className + " | Is Abstract? = " + isAbstract);

        // Perform checks & print result in console.
        if (isAbstract && !matching) {
            System.out.println("Problem: Abstract class but name does not start with 'Abstract'");
        } else if (!isAbstract && matching) {
            System.out.println("Problem: Not an Abstract class but name starts with 'Abstract'");
        } else {
            System.out.println("Class declaration is all good.");
        }
    }
}
