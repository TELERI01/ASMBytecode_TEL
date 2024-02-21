import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

/**
 * CS 322 Assignment 3
 * Combo Class
 * @author Tyler Lericos 
 * @version 1.0
 */

public class gen5 {

    public static void main(String[] args) {
        // Create class and main method
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program5", null, "java/lang/Object", null);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();

        // Print the string
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Hello, world!"); // Load the string onto the stack
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        // Return out of main, and close the method visitor
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        // Write bytes created by above code to file; writeFile credit: Dr. Robert Kelly
        byte[] b = cw.toByteArray();
        writeFile(b, "program5.class");
    }
}
