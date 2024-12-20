package view;

import model.User;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UserPdf {

    public void exportPdf(List<User> users) {
        System.out.println(users.size());
        Document document = new Document(PageSize.A4);

        try {
            // Inisialisasi PDF Writer
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(System.getProperty("user.dir") + File.separator + "users.pdf"));

            // Membuka dokumen
            document.open();

            // Mendefinisikan ukuran kolom tabel
            float[] columnDefinitionSize = {10F, 45F, 45F};

            // Membuat tabel
            PdfPTable table = new PdfPTable(columnDefinitionSize);
            table.setWidthPercentage(100); // Mengatur lebar tabel
            table.setSpacingBefore(10); // Spasi sebelum tabel

            // Menambahkan header tabel
            PdfPCell header;
            header = new PdfPCell(new Phrase("No", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(header);

            header = new PdfPCell(new Phrase("Name", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(header);

            header = new PdfPCell(new Phrase("Email", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(header);

            // Menambahkan data pengguna ke tabel
            int no = 1;
            for (User user : users) {
                table.addCell(new Phrase(String.valueOf(no++)));
                table.addCell(new Phrase(user.getName()));
                table.addCell(new Phrase(user.getEmail()));
            }

            // Menambahkan tabel ke dokumen
            document.add(table);

        } catch (DocumentException | IOException ex) {
            System.err.println("Error while generating PDF: " + ex.getMessage());
        } finally {
            // Menutup dokumen
            document.close();
        }
    }
}
