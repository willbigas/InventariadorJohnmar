/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariadorjohnmar;

import control.TelaEscolherArquivoControl;
import javax.swing.JOptionPane;
import uteis.InterfaceJanela;

/**
 *
 * @author William
 */
public class InventariadorJohnmar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            InterfaceJanela.MudaSwingParaPadraoDoSO();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Não consegui alterar o componente do swing para seu sistema operacional , chame o programador!");
        }
        TelaEscolherArquivoControl controlArquivo = new TelaEscolherArquivoControl();
        
        
    }
    
}
