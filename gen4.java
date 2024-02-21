import static utils.Utilities.writeFile;
import org.objectweb.asm.*;

/**
 * CS 322 Assignment 3
 * Combo Class
 * @author Tyler Lericos 
 * @version 1.0
 */

public class gen4 {

    public static void main(String[] args) {
        // Create class and main method
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program4", null, "java/lang/Object", null);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();
        mv.visitLdcInsn(40);
        mv.visitVarInsn(Opcodes.ISTORE, 1);
        mv.visitLdcInsn(60);
        mv.visitVarInsn(Opcodes.ISTORE, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        Label intComparisonFalse = new Label();
        Label intComparisonEnd = new Label();
        
        mv.visitJumpInsn(Opcodes.IF_ICMPGE, intComparisonFalse);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitJumpInsn(Opcodes.GOTO, intComparisonEnd);
        mv.visitLabel(intComparisonFalse);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitLabel(intComparisonEnd);

        mv.visitVarInsn(Opcodes.ISTORE, 5);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 5);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        
        // Compare doubles
        mv.visitLdcInsn(40.0);
        mv.visitVarInsn(Opcodes.DSTORE, 6);
        mv.visitLdcInsn(60.0);
        mv.visitVarInsn(Opcodes.DSTORE, 8);
        mv.visitVarInsn(Opcodes.DLOAD, 6);
        mv.visitVarInsn(Opcodes.DLOAD, 8);
        Label doubleComparisonFalse = new Label();
        Label doubleComparisonEnd = new Label();
        
        mv.visitInsn(Opcodes.DCMPG);
        mv.visitJumpInsn(Opcodes.IFGE, doubleComparisonFalse);
        mv.visitVarInsn(Opcodes.DLOAD, 8);
        mv.visitJumpInsn(Opcodes.GOTO, doubleComparisonEnd);
        mv.visitLabel(doubleComparisonFalse);
        mv.visitVarInsn(Opcodes.DLOAD, 6);
        mv.visitLabel(doubleComparisonEnd);
        
        mv.visitVarInsn(Opcodes.DSTORE, 10);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.DLOAD, 10);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);

        // Compare longs
        mv.visitLdcInsn(4000000000L);
        mv.visitVarInsn(Opcodes.LSTORE, 12);
        mv.visitLdcInsn(6000000000L);
        mv.visitVarInsn(Opcodes.LSTORE, 14);
        mv.visitVarInsn(Opcodes.LLOAD, 12);
        mv.visitVarInsn(Opcodes.LLOAD, 14);
        Label longComparisonFalse = new Label();
        Label longComparisonEnd = new Label();
        
        mv.visitInsn(Opcodes.LCMP);
        mv.visitJumpInsn(Opcodes.IFGE, longComparisonFalse);
        mv.visitVarInsn(Opcodes.LLOAD, 14);
        mv.visitJumpInsn(Opcodes.GOTO, longComparisonEnd);
        mv.visitLabel(longComparisonFalse);
        mv.visitVarInsn(Opcodes.LLOAD, 12);
        mv.visitLabel(longComparisonEnd);
        
        mv.visitVarInsn(Opcodes.LSTORE, 16);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.LLOAD, 16);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);

        // Return out of main, and close the method visitor
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0); // Update this line as appropriate
        mv.visitEnd();

        // Write bytes created by above code to file; writeFile credit: Dr. Robert Kelly
        byte[] b = cw.toByteArray();
        writeFile(b, "program4.class");
    }
}
