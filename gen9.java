import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

/**
 * CS 322 Assignment 3
 * If Then Else
 * @author Tyler Lericos
 * @version 1.0
 */

 public class gen9
 {

    public static void main(String[] args) 
    {
        // Create class and main method
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program9", null, "java/lang/Object",null);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();

        // Relevant lables for jump instructions
        Label whileHeader = new Label();
        Label end = new Label();

        // Initialize and store scanner object
        mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
        mv.visitInsn(Opcodes.DUP);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;"); // load System.in onto stack
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
        mv.visitVarInsn(Opcodes.ASTORE, 0);

        // Create counter variable for while loop, and accumulator
        mv.visitLdcInsn(0);
        mv.visitVarInsn(Opcodes.ISTORE, 1);
        mv.visitLdcInsn(0);
        mv.visitVarInsn(Opcodes.ISTORE, 2);

        // While loop body
        mv.visitLabel(whileHeader);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitLdcInsn(3);
        mv.visitJumpInsn(Opcodes.IF_ICMPGE, end); // exit while loop if counter is greater than or equal to 3
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Please enter an integer to be added to the accumulator: ");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitInsn(Opcodes.IADD); // add input to the accumulator
        mv.visitVarInsn(Opcodes.ISTORE, 2);

        // Increment counter variable and jump back top
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitLdcInsn(1);
        mv.visitInsn(Opcodes.IADD);
        mv.visitVarInsn(Opcodes.ISTORE, 1);
        mv.visitJumpInsn(Opcodes.GOTO, whileHeader);
        
        // Print result of accumulation
        mv.visitLabel(end);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Final accumulator result: ");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

        // Return out of main, and close the method visitor
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        // Write bytes created by above code to file; writeFile credit: Dr. Robert Kelly
        byte[] b = cw.toByteArray();
        writeFile(b, "program9.class");

    } // end main

 } // end class
