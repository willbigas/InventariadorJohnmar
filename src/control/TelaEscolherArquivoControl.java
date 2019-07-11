package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Produto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.TelaEscolherArquivo;

/**
 *
 * @author William
 */
public class TelaEscolherArquivoControl {

    TelaEscolherArquivo telaEscolherArquivo;
    TelaConferenciaControl telaConferenciaControl;
    public static List<Produto> listProdutos = new ArrayList<>();
    Produto produto;

    public static String enderecoOrigem = "";
    public static String enderecoDestino = "";

    private static final int SKU = 0;
    private static final int NOME = 1;
    private static final int EAN13 = 2;
    private static final int DUN14 = 3;
    private static final int QTD_DUN14 = 4;
    private static final int LOCALIZACAO = 5;
    private static final int QTD_ESTOQUE = 6;

    private static final int NUMERO_COLUNAS = 7;

    public TelaEscolherArquivoControl() {
        telaEscolherArquivo = new TelaEscolherArquivo(this);
        telaEscolherArquivo.setLocationRelativeTo(null);
        telaEscolherArquivo.setVisible(true);
    }

    public void acionaAberturaDeArquivo() {
        /**
         * Janela de Abrir - JFileChooser - Para abrir arquivos em geral.
         */

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Arquivos xls - Excel 2007 ou superior", "xlsx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(telaEscolherArquivo);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String nomeArquivo = chooser.getSelectedFile().getName();
            enderecoOrigem = chooser.getSelectedFile().getPath();
            System.out.println("Endereco de Arquivo Origem : " + enderecoOrigem);
        }
    }

    public void acionaSalvamentoDeArquivo() {
        /**
         * Janela de Salvar - JFileChooser - Para salvar arquivos em geral.
         */

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Arquivos xls - Excel 2007 ou superior", "xlsx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(telaEscolherArquivo);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String nomeArquivo = chooser.getSelectedFile().getName();
            enderecoDestino = chooser.getSelectedFile().getPath();
            System.out.println("EnderecoArquivo de Destino :" + enderecoDestino);
        }

    }

    public void chamarTelaConferenciaAction() {
        telaConferenciaControl = new TelaConferenciaControl(listProdutos);
        telaConferenciaControl.chamarTelaConferencia();

    }

    public void lerArquivoPeloExcelAction() {
        try {
            FileInputStream arquivo = new FileInputStream(new File(
                    enderecoOrigem));

            XSSFWorkbook workbook = new XSSFWorkbook(arquivo);

            XSSFSheet sheetAlunos = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheetAlunos.iterator();
            Row proximaLinha = rowIterator.next();
            int contagemDeLinhas = sheetAlunos.getLastRowNum();
            short contagemDeColunas = proximaLinha.getLastCellNum();

            if (contagemDeColunas == 7) {
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    produto = new Produto();
                    listProdutos.add(produto);
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cell.getColumnIndex()) {
                            case SKU:
                                produto.setSku(cell.getStringCellValue());
                                break;
                            case NOME:
                                produto.setNome(cell.getStringCellValue());
                                break;
                            case EAN13:
                                produto.setEan13(pegaValorDaCelula(cell));
                                break;
                            case DUN14:
                                produto.setDun14(pegaValorDaCelula(cell));
                                break;
                            case QTD_DUN14:
                                produto.setQtdDun14(Integer.valueOf(pegaValorDaCelula(cell).replace(".", "")));
                                break;
                            case LOCALIZACAO:
                                produto.setLocalizacao(pegaValorDaCelula(cell));
                                break;
                            case QTD_ESTOQUE:
                                produto.setQtdEstoque(0);
                                break;
                        }
                    }
                }

            } else {

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    produto = new Produto();
                    listProdutos.add(produto);
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cell.getColumnIndex()) {
                            case SKU:
                                produto.setSku(cell.getStringCellValue());
                                break;
                            case NOME:
                                produto.setNome(cell.getStringCellValue());
                                break;
                            case EAN13:
                                produto.setEan13(pegaValorDaCelula(cell));
                                break;
                            case DUN14:
                                produto.setDun14(pegaValorDaCelula(cell));
                                break;
                            case QTD_DUN14:
                                produto.setQtdDun14(Integer.valueOf(pegaValorDaCelula(cell).replace(".", "")));
                                break;
                            case LOCALIZACAO:
                                produto.setLocalizacao(pegaValorDaCelula(cell));
                                break;
                            case QTD_ESTOQUE:
                                produto.setQtdEstoque(Integer.valueOf(pegaValorDaCelula(cell)));
                                break;
                        }
                    }
                }

            }
            
            

            for (int i = 0; i < listProdutos.size(); i++) {
                Produto get = listProdutos.get(i);
                get.setEan13(get.getEan13().replace(".", ""));
                get.setDun14(get.getDun14().replace(".", ""));
                get.setEan13(get.getEan13().substring(0, get.getEan13().length()-3));
                get.setDun14(get.getDun14().substring(0, get.getDun14().length()-3));
                listProdutos.set(i, get);
            }
            for (int i = 0; i < listProdutos.size(); i++) {
                Produto get = listProdutos.get(i);
                System.out.println("Um Produto lido : " + get);

            }
            arquivo.close();
            JOptionPane.showMessageDialog(telaEscolherArquivo, "Arquivo excel lido com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Arquivo Excel não está com os padrões corretos, verifique!");
        }
    }

    public void escreverArquivoParaExcel(List<Produto> produtos) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheetAlunos = workbook.createSheet("Alunos");

        int rownum = 0;
        for (Produto produto : produtos) {
            Row row = sheetAlunos.createRow(rownum++);
            int cellnum = 0;
            Cell celulaSKU = row.createCell(cellnum++);
            celulaSKU.setCellValue(produto.getSku());

            Cell celulaNome = row.createCell(cellnum++);
            celulaNome.setCellValue(produto.getNome());

            Cell celulaEAN13 = row.createCell(cellnum++);
            celulaEAN13.setCellValue(produto.getEan13());

            Cell celulaDUN14 = row.createCell(cellnum++);
            celulaDUN14.setCellValue(produto.getDun14());

            Cell celulaQtdDun14 = row.createCell(cellnum++);
            celulaQtdDun14.setCellValue(produto.getQtdDun14());

            Cell celulaLocalizacao = row.createCell(cellnum++);
            celulaLocalizacao.setCellValue(produto.getLocalizacao());

            if (produto.getQtdEstoque() == null) {
                Cell celulaQtdEstoque = row.createCell(cellnum++);
                celulaQtdEstoque.setCellValue(0);
            } else {
                Cell celulaQtdEstoque = row.createCell(cellnum++);
                celulaQtdEstoque.setCellValue(produto.getQtdEstoque());

            }

            if (produto.getQtdConferida() == null) {
                Cell celulaQtdConferida = row.createCell(cellnum++);
                celulaQtdConferida.setCellValue(0);
            } else {
                Cell celulaQtdConferida = row.createCell(cellnum++);
                celulaQtdConferida.setCellValue(produto.getQtdConferida());
            }

        }

        try {
            FileOutputStream out
                    = new FileOutputStream(new File(enderecoDestino + ".xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Arquivo Excel criado com sucesso!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro na edição do arquivo!");
        }

    }

    /**
     * Evaluate the formula of the given cell.
     *
     * @param workbook workbook (excel) for evaluating the cell formula
     * @param cell cell (excel)
     *
     * @return the value of the excel-call as string (the formula will be
     * executed)
     */
    static String pegaValorDaCelula(Cell cell) {

        switch (cell.getCellType()) {
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case STRING:
                return String.valueOf(cell.getStringCellValue());
            case BLANK:
                return "0";
            case _NONE:
                return "0";
            default:
                return null;
        }
    }

}
