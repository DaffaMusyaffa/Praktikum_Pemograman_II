package view;

import model.User;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UserPdf {
    public void exportPdf(List<User> users) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.dir") + File.separator + "users.pdf"));

            document.open();

            float[] columnDefinitionSize = {33.33f, 33.33f, 33.33f};
            PdfPTable table = new PdfPTable(columnDefinitionSize);
            table.setWidthPercentage(100);

            Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

            table.addCell(new Phrase("No", font));
            table.addCell(new Phrase("Name", font));
            table.addCell(new Phrase("Email", font));

            int no = 1;
            for (User user : users) {
                table.addCell(new Phrase(String.valueOf(no++), font));
                table.addCell(new Phrase(user.getName(), font));
                table.addCell(new Phrase(user.getEmail(), font));
            }

            document.add(table);
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        } finally {
            document.close();
        }
    }
}
