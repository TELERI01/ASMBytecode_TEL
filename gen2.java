import static utils.Utilities.writeFile;
import org.objectweb.asm.*;

/**
 * CS 322 Assignment 3
 * Combo Class
 * @author Tyler Lericos 
 * @version 1.0
 */

public class gen2 {

    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program2", null, "java/lang/Object", null);

        // Constructor
        {
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }

        // Main method
        {
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            
            // Subtraction for floats
            mv.visitLdcInsn(25.5f);
            mv.visitVarInsn(Opcodes.FSTORE, 1);
            mv.visitLdcInsn(5.5f);
            mv.visitVarInsn(Opcodes.FSTORE, 3);
            mv.visitVarInsn(Opcodes.FLOAD, 1);
            mv.visitVarInsn(Opcodes.FLOAD, 3);
            mv.visitInsn(Opcodes.FSUB);
            mv.visitVarInsn(Opcodes.FSTORE, 5);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.FLOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(F)V", false);
            
            // Subtraction for doubles
            mv.visitLdcInsn(100.0);
            mv.visitVarInsn(Opcodes.DSTORE, 6);
            mv.visitLdcInsn(3.0);
            mv.visitVarInsn(Opcodes.DSTORE, 8);
            mv.visitVarInsn(Opcodes.DLOAD, 6);
            mv.visitVarInsn(Opcodes.DLOAD, 8);
            mv.visitInsn(Opcodes.DSUB);
            mv.visitVarInsn(Opcodes.DSTORE, 10);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.DLOAD, 10);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);

            // Subtraction for ints
            mv.visitLdcInsn(10);
            mv.visitVarInsn(Opcodes.ISTORE, 12);
            mv.visitLdcInsn(2);
            mv.visitVarInsn(Opcodes.ISTORE, 13);
            mv.visitVarInsn(Opcodes.ILOAD, 12);
            mv.visitVarInsn(Opcodes.ILOAD, 13);
            mv.visitInsn(Opcodes.ISUB);
            mv.visitVarInsn(Opcodes.ISTORE, 14);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 14);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b, "program2.class");

        System.out.println("Done!");
    }
}
