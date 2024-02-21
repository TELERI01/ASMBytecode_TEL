import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

/**
 * CS 322 Assignment 3
 * Combo Class
 * @author Tyler Lericos 
 * @version 1.0
 */

public class gen8 {

    public static void main(String[] args) {
        // Create class and main method
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program8", null, "java/lang/Object", null);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();

        // Relevant labels for jump instructions
        Label whileHeader = new Label();
        Label end = new Label();

        // Initialize counter variable for while loop
        mv.visitLdcInsn(1);
        mv.visitVarInsn(Opcodes.ISTORE, 1);

        // While loop body
        mv.visitLabel(whileHeader);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitLdcInsn(5); // We're counting up to 4, so loop while counter is less than 5
        mv.visitJumpInsn(Opcodes.IF_ICMPGE, end); // exit while loop if counter is greater than or equal to 5
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 1); // load the value of the counter
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

        // Increment counter variable and jump back to whileHeader
        mv.visitIincInsn(1, 1);
        mv.visitJumpInsn(Opcodes.GOTO, whileHeader);

        // Return out of main, and close the method visitor
        mv.visitLabel(end);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        // Write bytes created by above code to file; writeFile credit: Dr. Robert Kelly
        byte[] b = cw.toByteArray();
        writeFile(b, "program8.class");
    }
}
