import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Parser.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import util.*;
import AST.*;
import Frontend.*;
import util.Scope.globalScope;
import util.error.error;

public class Main {
    public static void main(String[] args) throws IOException {
//        String filename = "testcases/optim/sha_1.mx";
//        InputStream input = new FileInputStream(filename);
//        OutputStream IROut = new FileOutputStream("output.ll");
//        OutputStream output = new FileOutputStream("output.s");
        InputStream input = System.in;
        OutputStream output = System.out;

        try {
            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parseTreeRoot = parser.program();
            globalScope gScope = new globalScope(null);
            ASTBuilder astBuilder = new ASTBuilder();
            ProgramNode ast = (ProgramNode) astBuilder.visit(parseTreeRoot);
            new SymbolCollector(gScope).visit(ast);
            new SemanticChecker(gScope).visit(ast);

        } catch (error e) {
            System.out.println(e.message);
            System.exit(1);
        }
    }
}
