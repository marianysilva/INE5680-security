package com.mycompany.mariany.mercia.lucas.t1;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Lucas Nascimento Falcão (17100915)
 * @author Mariany Ferreira da Silva (19200646)
 * @author Mércia de Souza Maguerroski Castilho (18105221)
 */

public class UserClient {
    private final Scanner scanner = new Scanner(System.in);

    private static void createQRCode(
        String codeData,
        String filePath,
        int height,
        int width
    )
            throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
            codeData,
            BarcodeFormat.QR_CODE,
            width,
            height
        );
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            MatrixToImageWriter.writeToStream(matrix, "png", out);
        }

    }

    public int getMenuSelectedOption() {
        System.out.println("Digite uma das opções:"
                + "\n0 - Encerar"
                + "\n1 - Cadastrar novo usuário"
                + "\n2 - Login"
        );
        int selectedOption = Integer.parseInt(this.scanner.nextLine());
        return selectedOption;
    } 

    public String[] getUserInputs (){
        String[] userInputs = new String[2];

        System.out.println("Digite o nome do usuário: ");
        userInputs[0] = scanner.nextLine();

        System.out.println("Digite a senha: ");
        userInputs[1] = scanner.nextLine();
        
        return userInputs;
    }

    public String get2FACode(String code){
        System.out.println(
            "Procure o arquivo matrixCode.png no diretorio do"
            + " projeto e leia o QR code para digitar o código"
        );
        try {
            createQRCode(code, "matrixCode.png", 246, 246);
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        System.out.println("Entre o código de autenticação: ");
        String codeInput = scanner.nextLine();
        return codeInput;
    }
    
    public void userNotFound(){
        System.out.println(
            "Usuário não encontrado, verifique os dados de entrada"
            + " e tente novamente"
        );
    }
    
    public void userCodeError(){
        System.out.println(
            "Código do usuário diferente do esperado, verifique seus dados"
            + " e tente novamente"
        );
    }
    
    public void loginSuccess(){
        System.out.println(
            "Login efetuado com sucesso"
        );    
    }
}
